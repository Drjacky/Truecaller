package truecaller.hosseinabbasi.ir.truecaller.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import truecaller.hosseinabbasi.ir.truecaller.data.AppDataManager;
import truecaller.hosseinabbasi.ir.truecaller.data.DataManager;
import truecaller.hosseinabbasi.ir.truecaller.data.network.ApiHelper;
import truecaller.hosseinabbasi.ir.truecaller.data.network.AppApiHelper;
import truecaller.hosseinabbasi.ir.truecaller.di.ApplicationContext;


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}
