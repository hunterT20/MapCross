package vn.aqtsoft.mapcross.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import vn.aqtsoft.mapcross.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {


    public UserDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
