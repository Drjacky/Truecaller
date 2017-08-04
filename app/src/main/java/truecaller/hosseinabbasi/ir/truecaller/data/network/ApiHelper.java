package truecaller.hosseinabbasi.ir.truecaller.data.network;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("support")
    Observable<String> getContent();
}
