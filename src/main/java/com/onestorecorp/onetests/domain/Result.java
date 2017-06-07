package com.onestorecorp.onetests.domain;

import lombok.Data;

@Data
public class Result {

	boolean passed;
	boolean statusEqual;
	boolean headersEqual;
	boolean bodyEqual;

}
