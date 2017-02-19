package restart.com.bandara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import restart.com.bandara.R;
import restart.com.bandara.dao.models.Promo;

/**
 * Created by lenovo on 2/16/2017.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.Holder> {
    private List<Promo> itemList;
    private Context context;

    public PromoAdapter(Context context, List<Promo> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        return new Holder(layoutView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position){
        holder.promoName.setText(itemList.get(position).getName());
        holder.promoPhoto.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    /**
     * Created by lenovo on 2/16/2017.
     */

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.promo_name) TextView promoName;
        @BindView(R.id.promo_photo) ImageView promoPhoto;

        public Holder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
