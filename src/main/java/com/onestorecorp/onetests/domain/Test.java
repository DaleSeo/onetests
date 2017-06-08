package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Test {

	@Id
	private long id;
	private String title;

}
