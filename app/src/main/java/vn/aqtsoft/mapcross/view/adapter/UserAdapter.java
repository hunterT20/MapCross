package vn.aqtsoft.mapcross.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.model.User;
import vn.aqtsoft.mapcross.view.activity.DetailActivity;
import vn.aqtsoft.mapcross.view.activity.MainActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        final User user = userList.get(position);

        holder.txtvUserName.setText(user.getUserName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtvUserName;
        UserViewHolder(View itemView) {
            super(itemView);
            txtvUserName = itemView.findViewById(R.id.txtvUserName);
        }
    }
}
