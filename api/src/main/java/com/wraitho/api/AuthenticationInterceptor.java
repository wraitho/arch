package com.wraitho.api;

import com.wraitho.api.util.Hasher;

import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

	private final String apiKeyPublic;
	private final String apiKeyPrivate;
	private final Hasher hasher;

	AuthenticationInterceptor(String apiKeyPublic, String apiKeyPrivate, Hasher hasher) {
		this.apiKeyPublic = apiKeyPublic;
		this.apiKeyPrivate = apiKeyPrivate;
		this.hasher = hasher;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		// Getting the timestamp
		Date now = new Date();
		String timestamp = String.valueOf(now.getTime());

		Request originalRequest = chain.request();
		HttpUrl originalHttpUrl = originalRequest.url();

		String hash = hasher.md5Hash(timestamp + apiKeyPrivate + apiKeyPublic);

		HttpUrl url = originalHttpUrl.newBuilder()
				.addQueryParameter("apikey", apiKeyPublic)
				.addEncodedQueryParameter("ts", timestamp)
				.addEncodedQueryParameter("hash", hash)
				.build();

		return chain.proceed(originalRequest.newBuilder().url(url).build());
	}
}
