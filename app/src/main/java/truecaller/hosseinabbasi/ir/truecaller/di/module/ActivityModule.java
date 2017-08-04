package truecaller.hosseinabbasi.ir.truecaller.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import truecaller.hosseinabbasi.ir.truecaller.data.network.ApiEndPoint;
import truecaller.hosseinabbasi.ir.truecaller.di.ActivityContext;
import truecaller.hosseinabbasi.ir.truecaller.di.PerActivity;
import truecaller.hosseinabbasi.ir.truecaller.ui.main.MainMvpPresenter;
import truecaller.hosseinabbasi.ir.truecaller.ui.main.MainMvpView;
import truecaller.hosseinabbasi.ir.truecaller.ui.main.MainPresenter;
import truecaller.hosseinabbasi.ir.truecaller.utils.rx.AppSchedulerProvider;
import truecaller.hosseinabbasi.ir.truecaller.utils.rx.SchedulerProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView>
                                                               presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(ApiEndPoint.ENDPOINT_TRUECALLER)
                .build();

        return retrofit;
    }
}
