package truecaller.hosseinabbasi.ir.truecaller.di.component;

import dagger.Component;
import truecaller.hosseinabbasi.ir.truecaller.di.PerActivity;
import truecaller.hosseinabbasi.ir.truecaller.di.module.ActivityModule;
import truecaller.hosseinabbasi.ir.truecaller.ui.main.MainActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
