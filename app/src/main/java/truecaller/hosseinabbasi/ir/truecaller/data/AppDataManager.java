package truecaller.hosseinabbasi.ir.truecaller.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import truecaller.hosseinabbasi.ir.truecaller.data.network.ApiHelper;
import truecaller.hosseinabbasi.ir.truecaller.di.ApplicationContext;
import retrofit2.http.Path;


@Singleton
public class AppDataManager implements DataManager, ApiHelper {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<String> getContent() {
        return mApiHelper.getContent();
    }
}
