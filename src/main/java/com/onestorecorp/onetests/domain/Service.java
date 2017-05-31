package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Service {

	@Id
	private String id;
	private String code;
	private String name;
	private String description;
	private List<String> hosts;

}
