package vn.aqtsoft.mapcross.view.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import vn.aqtsoft.mapcross.R;
import vn.aqtsoft.mapcross.util.SharePreferenceUtil;
import vn.aqtsoft.mapcross.util.SweetDialogUtil;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.Stack)       ConstraintLayout Stack;
    @BindView(R.id.SignIn)      ConstraintLayout SignIn;
    @BindView(R.id.SignUp)      ConstraintLayout SignUp;
    @BindView(R.id.edtUserNameL)    EditText edtUserNameL;
    @BindView(R.id.edtPassL)        EditText edtPassL;
    /*Nếu
    * step = 1: SignIn
    * step = 2: SignUp*/
    int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /*@OnClick(R.id.btnNoAcc)
    public void NoAccClick(){
        SharePreferenceUtil.setPermission(this,"-1");
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnHaveAcc)
    public void HaveAccClick(){
        step = 1;
        Stack.setVisibility(View.GONE);
        SignIn.setVisibility(View.VISIBLE);
    }*/

    @OnClick(R.id.btnSigUp)
    public void SignUpClick(){
        step = 1;
        SignUp.setVisibility(View.GONE);
        SignIn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnLoginFace)
    public void LoginFaceClick(){
        Toast.makeText(this, "Face login coming soon...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnLoginGmail)
    public void LoginGmailClick(){
        Toast.makeText(this, "Gmail login coming soon...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txtvSignUp)
    public void DangKiClick(){
        step = 2;
        SignIn.setVisibility(View.GONE);
        SignUp.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnLogin)
    public void LoginClick(){
        String user = "thanhtuan";
        String admin = "admin";
        String pass = "123";

        if (edtUserNameL.getText().toString().equals(user)){
            if (edtPassL.getText().toString().equals(pass)){
                SharePreferenceUtil.setPermission(this,"1");
                SweetDialogUtil.onSuccess(this, "Đăng nhập thành công!", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }else {
                Toast.makeText(this, "Password không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }else if(edtUserNameL.getText().toString().equals(admin)){
            if (edtPassL.getText().toString().equals(pass)){
                SharePreferenceUtil.setPermission(this,"0");
                SweetDialogUtil.onSuccess(this, "Đăng nhập thành công!", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }else {
                Toast.makeText(this, "Password không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Tên đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        switch (step){
            case 1:
                SweetDialogUtil.onWarning(this, "Bạn muốn thoát ứng dụng?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        finish();
                    }
                }, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
                break;
            /*case 1:
                step = 0;
                SignIn.setVisibility(View.GONE);
                Stack.setVisibility(View.VISIBLE);
                break;*/
            case 2:
                step = 1;
                SignUp.setVisibility(View.GONE);
                SignIn.setVisibility(View.VISIBLE);
        }
    }
}
