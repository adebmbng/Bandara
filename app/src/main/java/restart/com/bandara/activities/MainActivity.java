package restart.com.bandara.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import restart.com.bandara.MyApplication;
import restart.com.bandara.R;
import restart.com.bandara.adapters.PromoAdapter;
import restart.com.bandara.dao.DatabaseHelper;
import restart.com.bandara.dao.models.Promo;
import restart.com.bandara.presenters.promo.PromoPresenter;
import restart.com.bandara.presenters.promo.PromoPresenters;
import restart.com.bandara.presenters.promo.PromoView;
import restart.com.bandara.services.APIService;

public class MainActivity extends AppCompatActivity implements PromoView{
    private static final String TAG = "MainActivity";
    private TextView textViewSearch;
    private LinearLayoutManager lLayout;
    private PromoPresenter presenters;
    private Context ctx;

    private ProgressDialog progressDialog;

    private List<Promo> mList;
    private PromoAdapter adapter;

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.recycler_view)RecyclerView rView;
    @BindView(R.id.btn_book)Button btnBook;

    @Inject
    SharedPreferences sp;

    @Inject
    APIService api;

    @Inject
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ((MyApplication) getApplication()).getDc().inject(this);
        setPresenter(presenters);

        lLayout = new LinearLayoutManager(MainActivity.this);
        rView.setLayoutManager(lLayout);

        Log.d(TAG, db.countPromo()+"<--");
        if(db.countPromo()==0){
            presenters.start();
        } else {
            presenters.getPromoDb();
        }


    }

    @OnClick(R.id.btn_book)
    void gotoBook(){
        Intent i = new Intent(MainActivity.this, ConfirmationActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);
            return true;
        } else if(id == R.id.history){
            Intent i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadPromo() {
        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();
    }

    @Override
    public void onFinishedLoading(List<Promo> list) {
        progressDialog.dismiss();
        if(list!=null) {
            mList = list;
            adapter = new PromoAdapter(MainActivity.this, mList);
            rView.setAdapter(adapter);
        } else {
            onFailedLoading("Tidak ada Promo");
        }
    }

    @Override
    public void onFailedLoading(String msg) {
        progressDialog.dismiss();
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(PromoPresenter presenter) {
        if(presenter!=null){
            presenters = presenter;
        } else {
            presenters = new PromoPresenters(this, api, sp, db);
        }
    }
}
