package com.mehbub.neo.customer;

public class Customer {
    //customerName String - L100
    //customerContact String - L100
    //customerActive Boolean - L1
    //customerDeliveryAddress String - L500
    private Integer customerId;
    private String customerName;
    private String customerContact;
    private String customerDeliveryAddress;
    private Boolean customerActive;

    public Customer(Integer customerId, String customerName, String customerContact, String customerDeliveryAddress,
            Boolean customerActive) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerDeliveryAddress = customerDeliveryAddress;
        this.customerActive = customerActive;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerDeliveryAddress() {
        return customerDeliveryAddress;
    }

    public void setCustomerDeliveryAddress(String customerDeliveryAddress) {
        this.customerDeliveryAddress = customerDeliveryAddress;
    }

    public Boolean getCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(Boolean customerActive) {
        this.customerActive = customerActive;
    }

    
    
}
