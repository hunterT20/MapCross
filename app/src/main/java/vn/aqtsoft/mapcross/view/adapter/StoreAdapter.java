package vn.aqtsoft.mapcross.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.model.Store;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.view.activity.DetailActivity;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private List<Store> storeList;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public StoreAdapter(List<Store> userList, Context context) {
        this.storeList = userList;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public StoreAdapter.StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_store,parent,false);
        return new StoreAdapter.StoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoreAdapter.StoreViewHolder holder, int position) {
        final Store store = storeList.get(position);

        holder.txtvStoreName.setText(store.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreferenceUtil.setStore(context,store);
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        private TextView txtvStoreName, txtvDate;
        StoreViewHolder(View itemView) {
            super(itemView);
            txtvStoreName = itemView.findViewById(R.id.txtvStoreName);
            txtvDate = itemView.findViewById(R.id.txtvDate);
        }
    }
}
