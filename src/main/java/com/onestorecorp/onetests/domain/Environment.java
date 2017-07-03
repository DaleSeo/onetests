package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Environment extends Domain {

	private String code, title, host, description;

	private Map<String, String> variables;

	private String serviceId;

	public Environment() {
	}

	public Environment(String id) {
		this.id = id;
	}

}
