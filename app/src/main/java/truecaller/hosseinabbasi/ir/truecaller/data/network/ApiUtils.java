package truecaller.hosseinabbasi.ir.truecaller.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    public static ApiHelper getJsonPlaceHolderService() {
        return getClient(ApiEndPoint.ENDPOINT_TRUECALLER).create(ApiHelper.class);
    }

    public static Retrofit getClient(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }
}
