package com.pheonix.digitalservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteHistory {

	@NonNull
    private Date noteSubmittedDate;
	
	@NonNull
	private String notes;
	
	
	
	
	
}
