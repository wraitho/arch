package com.wraitho.api;

import android.content.Context;

import com.google.gson.Gson;
import com.wraitho.api.util.Hasher;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * API MODULE that provides API Network calls related dependencies like retrofit
 */
@Module(includes = ApiClientsModule.class)
public class ApiModule {

	private static final String BASE_URL = "https://gateway.marvel.com";
	private static final String API_VERSION = "v1";
	private static final String PATH_TO_PUBLIC = "public";
	private static final int TIMEOUT = 5000;

	@Provides @Singleton
	Retrofit providesRetrofit(OkHttpClient okHttpClient, Gson gson) {
		return new Retrofit.Builder()
				.baseUrl(String.format("%s/%s/%s/", BASE_URL, API_VERSION, PATH_TO_PUBLIC))
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.createWithScheduler(Schedulers.io()))
				.build();
	}

	@Provides @Singleton
	Gson providesGson(){
		return new Gson();
	}

	@Provides @Singleton
	OkHttpClient providesOkHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor,
									  AuthenticationInterceptor authenticationInterceptor) {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.addInterceptor(new HeaderInterceptor())
				.addInterceptor(authenticationInterceptor)
				.addInterceptor(httpLoggingInterceptor)
				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS);

		if (cache != null) {
			builder.cache(cache);
		}

		return builder.build();
	}

	@Provides @Singleton
	AuthenticationInterceptor providesAutheticationInterceptor(Context context) {
		return new AuthenticationInterceptor(context.getString(R.string.apikey_public),
				context.getString(R.string.apikey_private), new Hasher());
	}

	@Provides @Singleton
	Cache providesCache(Context context) {

		File appCacheDirectory = context.getExternalCacheDir() != null ?
				context.getExternalCacheDir() :
				context.getCacheDir();

		if (appCacheDirectory != null) {
			File httpCacheDirectory = new File(appCacheDirectory.getAbsolutePath(), "HttpCache");
			return new Cache(httpCacheDirectory, 10 * 1024 * 1024);
		}

		return null;
	}

	@Provides @Singleton
	HttpLoggingInterceptor providesHttpLoggingInterceptor() {
		return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
	}

}
