package truecaller.hosseinabbasi.ir.truecaller;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import javax.inject.Inject;

import truecaller.hosseinabbasi.ir.truecaller.data.DataManager;
import truecaller.hosseinabbasi.ir.truecaller.di.component.ApplicationComponent;
import truecaller.hosseinabbasi.ir.truecaller.di.component.DaggerApplicationComponent;
import truecaller.hosseinabbasi.ir.truecaller.di.module.ApplicationModule;


public class MvpApp extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
