package vn.aqtsoft.mapcross.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.model.Store;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailFragment extends Fragment {
    @BindView(R.id.edtSDT) EditText edtSDT;
    @BindView(R.id.edtDiaChi) EditText edtDiaChi;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtNoiDung) EditText edtNoiDung;

    public StoreDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        ButterKnife.bind(this,view);

        addViews();
        return view;
    }

    private void addViews() {
        Gson gson = new Gson();
        String json = SharePreferenceUtil.getStore(getActivity());
        Store store = gson.fromJson(json, Store.class);

        edtDiaChi.setText(store.getDiaChi());
        edtEmail.setText(store.getEmail());
        edtNoiDung.setText(store.getNoiDung());
        edtSDT.setText(store.getSDT());
    }

}
