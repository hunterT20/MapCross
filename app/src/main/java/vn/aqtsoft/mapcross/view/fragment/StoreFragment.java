package vn.aqtsoft.mapcross.view.fragment;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.aqtsoft.mapcross.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {
    @BindView(R.id.NoStore)
    ConstraintLayout NoStore;
    @BindView(R.id.Create)
    ConstraintLayout Create;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnCreate)
    public void CreateStore(){
        NoStore.setVisibility(View.GONE);
        Create.setVisibility(View.VISIBLE);
    }

}
