package restart.com.bandara;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lenovo on 2/16/2017.
 */

public class RecyclerViewAdapterPromo extends RecyclerView.Adapter<RecyclerViewHoldersPromo> {
    private List<ItemObjectPromo> itemList;
    private Context context;

    public RecyclerViewAdapterPromo(Context context, List<ItemObjectPromo> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHoldersPromo onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHoldersPromo rcvp = new RecyclerViewHoldersPromo(layoutView);
        return rcvp;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoldersPromo holder, int position){
        holder.promoName.setText(itemList.get(position).getName());
        holder.promoPhoto.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }
}
