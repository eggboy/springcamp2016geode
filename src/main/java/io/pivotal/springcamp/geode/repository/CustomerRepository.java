package io.pivotal.springcamp.geode.repository;

import org.springframework.data.gemfire.mapping.Region;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jay Lee on 4/20/16.
 */
@Repository
@Region("Customer")
public interface CustomerRepository extends GemfireRepository<Customer, Long>{
}
