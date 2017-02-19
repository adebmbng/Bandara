package restart.com.bandara.presenters.promo;

import java.util.List;

import restart.com.bandara.BaseView;
import restart.com.bandara.dao.models.Promo;

/**
 * Created by Debam on 2/19/17.
 */

public interface PromoView extends BaseView<PromoPresenter> {
    void onLoadPromo();
    void onFinishedLoading(List<Promo> promos);
    void onFailedLoading(String msg);
}
