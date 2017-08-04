package truecaller.hosseinabbasi.ir.truecaller.ui.main;

import java.util.HashMap;
import java.util.List;

import truecaller.hosseinabbasi.ir.truecaller.ui.base.MvpView;


public interface MainMvpView extends MvpView {
    void filltxt10thCharacter(String data);
    void filltxtEvery10thCharacter(String data);
    void filltxtWordCounter(HashMap data);
}
