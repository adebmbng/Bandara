package restart.com.bandara;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 2/16/2017.
 */

public class RecyclerViewHoldersPromo extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView promoName;
    public ImageView promoPhoto;

    public RecyclerViewHoldersPromo(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        promoName = (TextView)itemView.findViewById(R.id.promo_name);
        promoPhoto = (ImageView)itemView.findViewById(R.id.promo_photo);
    }

    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(), "Anda memilih promo ke-" + (getPosition()+1), Toast.LENGTH_SHORT).show();
    }
}
