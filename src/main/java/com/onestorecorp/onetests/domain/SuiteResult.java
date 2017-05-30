package com.onestorecorp.onetests.domain;

import lombok.Data;

@Data
public class SuiteResult {

	int total;
	int success;
	int failure;

}
