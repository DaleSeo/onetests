package com.onestorecorp.onetests.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Service {

	@Id
	private String id;
	private String code;
	private String name;
	private String description;
	private List<String> hosts;

}
