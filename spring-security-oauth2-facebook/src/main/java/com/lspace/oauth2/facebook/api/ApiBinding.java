package com.lspace.oauth2.facebook.api;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public abstract class ApiBinding {

	protected RestTemplate restTemplate;
	
	public ApiBinding(String accessToken) {
		this.restTemplate = new RestTemplate();
		if(accessToken != null) {
			this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
		} else {
			this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
		}
	}

	/**
	 * Intercepting client request and adding Authorization Bearer token before hitting facebook provider to fetch profile/post or any details from 
	 * facebook.
	 *  @param accessToken
	 * @return
	 */
	private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add("Authorization", "Bearer " + accessToken);
				System.out.println("Setting up headers :" +accessToken);
				return execution.execute(request, body);
			}
		};
	}
	
	private ClientHttpRequestInterceptor getNoTokenInterceptor() {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				throw new IllegalStateException("Can't access Facebook without an access token!");
			}
		};
	}
	
	
}
