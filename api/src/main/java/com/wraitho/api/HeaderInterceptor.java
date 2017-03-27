package com.wraitho.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

	public HeaderInterceptor() {}

	@Override
	public Response intercept(Chain chain) throws IOException {

		Request requestWithHeader =
				chain.request()
						.newBuilder()
						.addHeader("Content-Type", "application/json")
						.build();

		return chain.proceed(requestWithHeader);

	}

}
