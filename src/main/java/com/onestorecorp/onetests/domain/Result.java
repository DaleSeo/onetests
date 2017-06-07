package com.onestorecorp.onetests.domain;

import lombok.Data;

@Data
public class Result {

	private boolean passed;
	private boolean statusEqual;
	private boolean headersEqual;
	private boolean bodyEqual;

}
