package restart.com.bandara.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import restart.com.bandara.R;
import restart.com.bandara.dao.models.Promo;
import restart.com.bandara.utils.Constant;
import restart.com.bandara.utils.FileUtil;

/**
 * Created by Debam on 2/19/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Promo, Long> promos;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, Constant.DAO.DATABASE_NAME, null, Constant.DAO.DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        createAll();
    }

    private void createAll() {

        try {
            TableUtils.createTableIfNotExists(connectionSource, Promo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        dropAll();
        createAll();
    }

    private void dropAll() {

        try {
            TableUtils.dropTable(connectionSource, Promo.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<Promo, Long> getPromos() throws SQLException {
        if (promos == null) {
            promos = getDao(Promo.class);
        }
        return promos;
    }

    public int countPromo() {
        try {
            return (int) getPromos().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
