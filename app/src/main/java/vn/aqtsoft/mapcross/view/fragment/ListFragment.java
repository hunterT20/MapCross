package vn.aqtsoft.mapcross.view.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.model.Store;
import vn.aqtsoft.mapcross.model.User;
import vn.aqtsoft.mapcross.util.RecyclerViewUtil;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.view.adapter.StoreAdapter;
import vn.aqtsoft.mapcross.view.adapter.UserAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    @BindView(R.id.rcvList)
    RecyclerView rcvList;

    private List<User> userList;
    private List<Store> storeList;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,view);

        userList = new ArrayList<>();
        storeList = new ArrayList<>();
        if (getActivity() == null) return view;
        RecyclerViewUtil.setupRecyclerView(rcvList,new UserAdapter(getActivity(),userList),getActivity());
        initData();

        return view;
    }

    private void initData() {
        if (SharePreferenceUtil.getDetail(getActivity()).equals("0")){
            User user1 = new User("thanhtuan","123","10/07/2017");
            User user2 = new User("trongquyen","123","10/07/2017");

            userList.add(user1);
            userList.add(user2);

            setListUser();
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(),
                    R.mipmap.icon);
            Store store1 = new Store("HTGame","01669384803","180 Cao Lo","Gioi thieu demo",bitmap,"thanhtuan.2295@outlook.com");
            storeList.add(store1);

            setListStore();
        }
    }

    private void setListUser() {
        UserAdapter adapter = new UserAdapter(getActivity(),userList);
        rcvList.setAdapter(adapter);
    }

    private void setListStore() {
        StoreAdapter adapter = new StoreAdapter(storeList,getActivity());
        rcvList.setAdapter(adapter);
    }
}
