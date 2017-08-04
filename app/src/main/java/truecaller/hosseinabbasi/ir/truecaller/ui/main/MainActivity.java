package truecaller.hosseinabbasi.ir.truecaller.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import truecaller.hosseinabbasi.ir.truecaller.R;
import truecaller.hosseinabbasi.ir.truecaller.ui.base.BaseActivity;
import retrofit2.Retrofit;


public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    Retrofit retrofit;

    @BindView(R.id.btnRun)
    Button btnRun;

    @BindView(R.id.txt10thCharacter)
    TextView txt10thCharacter;

    @BindView(R.id.txtEvery10thCharacter)
    TextView txtEvery10thCharacter;

    @BindView(R.id.txtWordCounter)
    TextView txtWordCounter;

    @BindView(R.id.txtWordCounterTruecaller)
    TextView txtWordCounterTruecaller;

    private static Context mContext;

    public static Intent getStartIntent(Context context) {
        mContext = context;
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void setUp() {
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onRunClicked();
            }
        });
    }

    @Override
    public void filltxt10thCharacter(String data) {
        txt10thCharacter.setText(data);
    }

    @Override
    public void filltxtEvery10thCharacter(String data) {
        txtEvery10thCharacter.setText(data);
    }

    @Override
    public void filltxtWordCounter(HashMap data) {
        StringBuilder sBuilder = new StringBuilder();

        Set keys = data.keySet();

        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            Integer value = (Integer)data.get(key);
            sBuilder.append(key)
                    .append(" : ")
                    .append(value)
                    .append("\n");
        }

        txtWordCounter.append(sBuilder);
        String tc = ("Truecaller").toLowerCase();
        txtWordCounterTruecaller.setText(tc + " : " + ((data.get(tc) != null) ? data.get(tc) : 0));
    }
}
