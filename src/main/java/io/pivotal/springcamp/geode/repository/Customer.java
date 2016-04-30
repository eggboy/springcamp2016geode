package io.pivotal.springcamp.geode.repository;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Jay Lee on 4/20/16.
 */


public class Customer implements Serializable{

    @Id
    private Long customerId;
    private String address;
    private String lastName;
    private String firstName;

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Customer customer = (Customer) o;

        if (getCustomerId() != null ?
            !getCustomerId().equals(customer.getCustomerId()) :
            customer.getCustomerId() != null)
            return false;
        if (getAddress() != null ?
            !getAddress().equals(customer.getAddress()) :
            customer.getAddress() != null)
            return false;
        if (getLastName() != null ?
            !getLastName().equals(customer.getLastName()) :
            customer.getLastName() != null)
            return false;
        return getFirstName() != null ?
            getFirstName().equals(customer.getFirstName()) :
            customer.getFirstName() == null;

    }

    @Override public int hashCode() {
        int result = getCustomerId() != null ? getCustomerId().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Customer{" +
            "customerId=" + customerId +
            ", address='" + address + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            '}';
    }
}
