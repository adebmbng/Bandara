package restart.com.bandara.presenters.promo;

import android.content.SharedPreferences;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import restart.com.bandara.R;
import restart.com.bandara.dao.DatabaseHelper;
import restart.com.bandara.dao.models.Promo;
import restart.com.bandara.services.APIService;

/**
 * Created by Debam on 2/19/17.
 */

public class PromoPresenters implements PromoPresenter{

    private PromoView view;
    private APIService api;
    private SharedPreferences sp;
    private DatabaseHelper db;
    private ExecutorService executorService;

    public PromoPresenters(PromoView view, APIService api, SharedPreferences sp, DatabaseHelper db) {
        this.view = view;
        this.api = api;
        this.sp = sp;
        this.db = db;
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getPromo() {
        List<Promo> allItems = new ArrayList<>();
        allItems.add(new Promo("Promo 10%", R.drawable.newyork));
        allItems.add(new Promo("Promo 15%", R.drawable.canada));
        allItems.add(new Promo("Promo 20%", R.drawable.uk));
        allItems.add(new Promo("Promo 25%", R.drawable.germany));
        allItems.add(new Promo("Promo 30%", R.drawable.sweden));

        view.onFinishedLoading(allItems);
        executorService.execute(new buildPromo(allItems));
    }

    @Override
    public void getPromoDb() {
        view.onLoadPromo();
        List<Promo> promos = null;
        try {
            promos = db.getDao(Promo.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.onFinishedLoading(promos);
    }

    @Override
    public void start() {
        view.onLoadPromo();
        if(db.countPromo()==0){
            getPromo();
        } else {
            getPromoDb();
        }

    }

    public class buildPromo implements Runnable {
        private List<Promo> promos;

        public buildPromo(List<Promo> promos) {
            this.promos = promos;
        }

        @Override
        public void run() {
            try {
                for (Promo p : promos) {
                    db.getDao(restart.com.bandara.dao.models.Promo.class).createOrUpdate(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
