package vn.aqtsoft.mapcross.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.model.User;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtPass) EditText edtPass;
    @BindView(R.id.edtDateCreate) EditText edtDateCreate;

    public UserDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this,view);

        addViews();
        return view;
    }

    private void addViews() {
        Gson gson = new Gson();
        String json = SharePreferenceUtil.getUser(getActivity());
        User user = gson.fromJson(json, User.class);

        edtUserName.setText(user.getUserName());
        edtPass.setText(user.getPass());
        edtDateCreate.setText(user.getDateCreate());
    }

    @OnClick(R.id.btnSave)
    public void SaveClick(){
        Toast.makeText(getActivity(), "Save success!!!", Toast.LENGTH_SHORT).show();
    }

}
