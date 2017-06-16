package com.onestorecorp.onetests.component.rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Component
public class RestErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;
	}

}
