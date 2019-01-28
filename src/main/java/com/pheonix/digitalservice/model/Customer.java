package com.pheonix.digitalservice.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@NonNull
    private Name name;
	
	@NonNull
	private Identity identity;
	
	
    @NonNull
    private Contact contact;

    @NonNull
    private List<Address> address;

}
