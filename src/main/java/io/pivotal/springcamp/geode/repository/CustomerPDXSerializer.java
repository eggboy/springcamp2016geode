package io.pivotal.springcamp.geode.repository;

import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializer;
import com.gemstone.gemfire.pdx.PdxWriter;

/**
 * Created by Jay Lee on 4/21/16.
 */
public class CustomerPDXSerializer implements PdxSerializer {
    @Override public boolean toData(Object o, PdxWriter pdxWriter) {
        if (o instanceof Customer) {
            Customer customer = (Customer)o;
            pdxWriter.writeLong("customerId", customer.getCustomerId());
            pdxWriter.writeString("firstName", customer.getFirstName());
            pdxWriter.writeString("lastName", customer.getLastName());
            pdxWriter.writeString("address", customer.getAddress());

            return true;
        } else {
            return false;
        }
    }

    @Override public Object fromData(Class<?> aClass, PdxReader pdxReader) {
        if(!aClass.equals(Customer.class)) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerId(pdxReader.readLong("customerId"));
        customer.setFirstName(pdxReader.readString("firstName"));
        customer.setLastName(pdxReader.readString("lastName"));
        customer.setAddress(pdxReader.readString("address"));

        return customer;
    }
}
