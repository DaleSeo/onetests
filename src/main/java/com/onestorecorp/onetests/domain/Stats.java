package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.NonNull;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Data
public class Stats {

	@NonNull
	private long numCalls;
	@NonNull
	private long numCases;
	@NonNull
	private long numSuites;

}
