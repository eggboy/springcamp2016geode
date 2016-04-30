package io.pivotal.springcamp;

import io.pivotal.springcamp.geode.repository.Customer;
import io.pivotal.springcamp.geode.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.gemfire.config.annotation.web.http.EnableGemFireHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SpringBootApplication
public class GeodetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeodetestApplication.class, args);
	}
}

@EnableGemFireHttpSession
@ImportResource(value = "client-cache.xml")
@SuppressWarnings("unused")
class GemFireCacheClientXmlConfiguration {
}

@Component
class InitialLoading implements CommandLineRunner {

	//@Autowired GemFireOperationsSessionRepository sessionRepository;

	@Autowired CustomerRepository customerRepository;

	@Override public void run(String... strings) throws Exception {


		for(int i = 0;i<1000;i++) {
			Customer customer = new Customer();

			customer.setCustomerId(new Long(i));
			customer.setFirstName("Jay"+i);
			customer.setLastName("Lee");
			customer.setAddress("Seoul");

			customerRepository.save(customer);
		}

		System.out.println("Size :" + customerRepository.count());
	}
}

@RestController
class GeodeRestController {

	@Autowired CustomerRepository customerRepository;

	@RequestMapping("/name/{id}")
	public Customer getCustomer(@PathVariable("id") Long customerId) {

		return customerRepository.findOne(customerId);
	}

	@RequestMapping("/geode")
	public String hello(HttpSession session) {

		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId == null) {
			sessionId = session.getId();
		}
		session.setAttribute("sessionId", sessionId);
		return sessionId;
	}
}
/*

@Component
class TomcatGeodeCustomizer implements TomcatContextCustomizer {

	@Override public void customize(Context context) {
		context.getManager().getSessionIdGenerator().setSessionIdLength(16);
	}
}
*/
