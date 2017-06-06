package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Case extends Domain {

	private String title, description;

	private Request request;
	private Response response;

	@Indexed
	private String serviceId;

	@DBRef(lazy = true)
	private Service service;

	private List<String> exclusions;

}