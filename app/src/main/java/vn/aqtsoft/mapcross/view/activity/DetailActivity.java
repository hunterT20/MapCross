package vn.aqtsoft.mapcross.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.view.fragment.StoreDetailFragment;
import vn.aqtsoft.mapcross.view.fragment.UserDetailFragment;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        addViews();
    }

    private void addViews() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String value_detail = SharePreferenceUtil.getDetail(this);
        if (value_detail.equals("0")){
            callFragment(new UserDetailFragment(),"Thông tin user");
        }else {
            callFragment(new StoreDetailFragment(),"Thông tin cửa hàng");
        }
    }

    public void callFragment(Fragment fragment, String title){
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(title);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frmContent, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
