package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Case extends Domain {

	private String title, description;

	private Request request;
	private Response response;

	@Indexed
	private String serviceId;

//	@DBRef(lazy = true)
	private Service service;

	public Service getService() {
		return service;
	}

	@Indexed
	private String hostId;

	@DBRef(lazy = true)
	private Host host;

	@Indexed
	private String environmentId;

	@DBRef(lazy = true)
	private Environment environment;

	private List<String> exclusions;

	public Case() {
	}

	public Case(String id) {
		this.id = id;
	}

	public Case clone() {
		Case newCase = new Case();
		newCase.setTitle(title);
		newCase.setDescription(description);
		newCase.setRequest(request);
		newCase.setResponse(response);
		newCase.setServiceId(serviceId);
		newCase.setEnvironmentId(environmentId);
		newCase.setHostId(hostId);
		newCase.setExclusions(exclusions);
		return newCase;
	}

}