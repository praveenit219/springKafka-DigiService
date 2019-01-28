package com.pheonix.digitalservice.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.pheonix.digitalservice.model.Address;
import com.pheonix.digitalservice.model.AddressType;
import com.pheonix.digitalservice.model.Contact;
import com.pheonix.digitalservice.model.Customer;
import com.pheonix.digitalservice.model.DigitalService;
import com.pheonix.digitalservice.model.Identity;
import com.pheonix.digitalservice.model.Name;
import com.pheonix.digitalservice.model.Phone;
import com.pheonix.digitalservice.model.PhoneType;

public class SampleData {
	
	public static List<DigitalService> createDigitalServiceApplication() {
		
		
		
		
		List<DigitalService> dgslist = new ArrayList<>();

        // New Customer 1
        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("John");
        name.setMiddleName("S.");
        name.setLastName("Doe");
        name.setSuffix("Jr.");
        
        Phone phone = new Phone();
        phone.setCountryCode(65);
        phone.setPhoneNumber(12341234);
        phone.setType(PhoneType.MOBILE);
        

        Contact contact = new Contact();
        contact.setPhone(phone);
        contact.setEmail("john.doe@internet.com");

       
        List<Address> addressList = new ArrayList<>();

        Address address = new Address();
        address.setType(AddressType.PERMENANT);
        address.setDescription("My PERMANANT address");
        address.setAddress1("123 Oak Street");
        address.setCity("Sunrise");
        address.setState("INDIA");
        address.setPostalCode("123456");

        addressList.add(address);

        address = new Address();
        address.setType(AddressType.RESIDENTIAL);
        address.setDescription("My RESIDENTIAL address");
        address.setAddress1("878 Central");
        address.setCity("Sunrise");
        address.setState("SINGAPORE");
        address.setPostalCode("125678");

        addressList.add(address);
        
        Identity identity = new Identity();
        identity.setIdentityId(name.getFirstName()+"ID1"+name.getLastName());
        
        Customer customer = new Customer();
        customer.setAddress(addressList);
        customer.setContact(contact);
        customer.setIdentity(identity);
        customer.setName(name);
        
        DigitalService dgs1 = new DigitalService();
        String uuid = UUID.randomUUID().toString();
        dgs1.setId(uuid);
        dgs1.setApp_no("DGS_APP_REG"+UUID.randomUUID());
        dgs1.setAgencyId("AGS_OFC_345");
        dgs1.setAgencyName("Registration Officer");
        dgs1.setAppStatus("PENDING_APPROVAL");
        dgs1.setCallerId(customer.getIdentity().getIdentityId());
        dgs1.setCustomerDetails(customer);
        dgs1.setCreatedAt(new Date());
        dgs1.setSvcName("APP_REGISTRATION");
        
        dgslist.add(dgs1);
        
        
        return dgslist;
		
	}

}
