package vn.aqtsoft.mapcross.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.view.fragment.AccountFragment;
import vn.aqtsoft.mapcross.view.fragment.ListFragment;
import vn.aqtsoft.mapcross.view.fragment.MapFragment;
import vn.aqtsoft.mapcross.view.fragment.StoreFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar)             Toolbar toolbar;
    @BindView(R.id.drawer_layout)       DrawerLayout drawer;
    @BindView(R.id.nav_view)            NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        addViews();
    }

    private void addViews() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        setMenu();
    }

    private void setMenu(){
        String permission = SharePreferenceUtil.getPermission(this);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_SignIn).setVisible(false);
        callFragment(new MapFragment(),"Địa điểm");

        /*Permission:
        * -1: Chưa đăng nhập
        * 0: Admin
        * 1: Người dùng*/
        switch (permission){
            case "-1":
                menu.findItem(R.id.nav_SignIn).setVisible(true);
                menu.findItem(R.id.nav_AccEdit).setVisible(false);
                menu.findItem(R.id.StoreManager).setVisible(false);
                menu.findItem(R.id.Admin).setVisible(false);
                break;
            case "0":
                menu.findItem(R.id.StoreManager).setVisible(false);
                menu.findItem(R.id.AccManager).setVisible(false);
                SharePreferenceUtil.setDetail(this,"1");
                callFragment(new ListFragment(),"Danh sách quán");
                break;
            case "1":
                menu.findItem(R.id.Admin).setVisible(false);
                callFragment(new StoreFragment(),"Thông tin quán");
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_AccEdit) {
            callFragment(new AccountFragment(),"Thông tin tài khoản");
        } else if (id == R.id.nav_ThongTinQuan) {
            callFragment(new StoreFragment(),"Thông tin quán");
        } else if (id == R.id.nav_Logout) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_Map) {
            callFragment(new MapFragment(), "Địa điểm");
        }else if (id == R.id.nav_SignIn) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_listUser){
            SharePreferenceUtil.setDetail(this,"0");
            callFragment(new ListFragment(),"Quản lý user");
        }else if (id == R.id.nav_listStore){
            SharePreferenceUtil.setDetail(this,"1");
            callFragment(new ListFragment(),"Quản lý cửa hàng");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callFragment(Fragment fragment, String title){
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(title);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frmContent, fragment)
                .commit();
    }
}
