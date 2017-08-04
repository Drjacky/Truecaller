package truecaller.hosseinabbasi.ir.truecaller.ui.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import truecaller.hosseinabbasi.ir.truecaller.data.DataManager;
import truecaller.hosseinabbasi.ir.truecaller.data.network.ApiHelper;
import truecaller.hosseinabbasi.ir.truecaller.ui.base.BasePresenter;
import truecaller.hosseinabbasi.ir.truecaller.utils.rx.SchedulerProvider;
import retrofit2.Retrofit;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         Retrofit retrofit) {
        super(dataManager, schedulerProvider, compositeDisposable, retrofit);
    }


    @Override
    public void onRunClicked() {
        //getMvpView().showLoading();
        Observable<String> m10thCharacterObservable = getRetrofit().create(ApiHelper.class).getContent();
        Observable<String> mEvery10thCharacterObservable = getRetrofit().create(ApiHelper.class).getContent();
        Observable<String> mWordCounterObservable = getRetrofit().create(ApiHelper.class).getContent();

        m10thCharacterObservable
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        tenCharacter(s);
                    }
                });

        mEvery10thCharacterObservable
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        every10thCharacter(s);
                    }
                });

        mWordCounterObservable
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        wordCounter(s);
                    }
                });
    }

    private void tenCharacter(String s) {
        String sTemp = s.substring(9, 10);
        getMvpView().filltxt10thCharacter(!sTemp.equalsIgnoreCase(" ") ? sTemp : "(This is a Space character)");
    }

    private void every10thCharacter(String s) {
        StringBuilder sBuilder = new StringBuilder();
        int i = 9;
        int length = s.length();

        while (i < length) {
            sBuilder.append(s.charAt(i));
            sBuilder.append(' ');
            i += 10;
        }

        getMvpView().filltxtEvery10thCharacter(sBuilder.toString());
    }

    private void wordCounter(String s) {
        Log.wtf("3",s);
        HashMap<String,Integer> wordsWithCounts = new HashMap<>();
        String[] words = s.split("[\\s\\t\\n\\r]+");

        for (String str : words) {
            String sTemp = str.toLowerCase();
            if (wordsWithCounts.containsKey(sTemp))
                wordsWithCounts.put(sTemp, wordsWithCounts.get(sTemp) + 1);
            else
                wordsWithCounts.put(sTemp, 1);
        }

        getMvpView().filltxtWordCounter(wordsWithCounts);
    }
}
