package com.pheonix.digitalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	@NonNull
    private Phone phone;
	
	@NonNull
    private String email;
	
	@NonNull
    private AddressType mailingAddress;

}
