package com.developervisits.oauth2.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public class ApiBinding {

	protected RestTemplate restTemplate;

	public ApiBinding(String accessToken) {
		this.restTemplate = new RestTemplate();
		
		if(accessToken !=null && !accessToken.isEmpty()) {
			restTemplate.getInterceptors().add(getBearerInterceptor(accessToken));
		} else {
			restTemplate.getInterceptors().add(getNoTokenInterceptor());
		}
	}

	private ClientHttpRequestInterceptor getBearerInterceptor(String accessToken) {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add("Authorization", "Bearer " + accessToken);
				System.out.println("Setting up headers :" +accessToken); 
				System.out.println(request.getURI().toURL());
				ClientHttpResponse response = execution.execute(request, body);
				StringBuilder inputStringBuilder = new StringBuilder();
		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
		        String line = bufferedReader.readLine();
		        while (line != null) {
		            inputStringBuilder.append(line);
		            inputStringBuilder.append('\n');
		            line = bufferedReader.readLine();
		        }
		        System.out.println("Response:"+ inputStringBuilder);
		        return response;
			}
		};
	}

	private ClientHttpRequestInterceptor getNoTokenInterceptor() {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				throw new IllegalStateException("Access token is missing.");
			}
		};
	}
}
