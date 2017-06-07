package com.onestorecorp.onetests.component;

import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Result;

public interface ResponseEqualer {

	public Result equals(Response expected, Response actual);

}
