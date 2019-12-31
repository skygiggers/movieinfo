package rs.tafilovic.movieinfo.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Semsudin Tafilovic
 * Base Controller for Retrofit API calls
 * Here Retrofit and GSON instances are created
 * All Specific controllers must extends from this class
 */
public abstract class BaseController {

    Retrofit retrofit = null;

    BaseController() {
        create();
    }

    private void create() {
        Gson gson = new GsonBuilder()
            .setLenient()
            .create();

        // init retrofit with base url, http client, and gson converter
        retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getClient())
            .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder builder = request.url().newBuilder();

                //add api_key parameter
                HttpUrl url = builder.addQueryParameter("api_key", "f2abe899aca398633b6ad6d2b6f12fb9").build();

                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();
    }
}
