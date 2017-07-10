package vn.aqtsoft.mapcross.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;

public class FlashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        Thread welcome = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    SharePreferenceUtil.setPermission(getApplication(),"-1");
                    Intent intent = new Intent(FlashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        welcome.start();
    }
}
