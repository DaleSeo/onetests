package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Environment {

	@DBRef(lazy = true)
	Service service;

	String code, title, description;

	Map<String, String> variables;

}
