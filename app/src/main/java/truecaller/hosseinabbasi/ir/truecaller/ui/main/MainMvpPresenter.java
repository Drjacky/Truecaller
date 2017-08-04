package truecaller.hosseinabbasi.ir.truecaller.ui.main;


import truecaller.hosseinabbasi.ir.truecaller.di.PerActivity;
import truecaller.hosseinabbasi.ir.truecaller.ui.base.MvpPresenter;


@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onRunClicked();
}
