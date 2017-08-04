package truecaller.hosseinabbasi.ir.truecaller.di.component;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import truecaller.hosseinabbasi.ir.truecaller.MvpApp;
import truecaller.hosseinabbasi.ir.truecaller.data.DataManager;
import truecaller.hosseinabbasi.ir.truecaller.di.ApplicationContext;
import truecaller.hosseinabbasi.ir.truecaller.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)/* $ */
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
