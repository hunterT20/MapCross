package vn.aqtsoft.mapcross.util;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetDialogUtil {
    public static void onSuccess(Context context , String alert, SweetAlertDialog.OnSweetClickListener onSweetClickListener){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Thành công!")
                .setContentText(alert)
                .setConfirmClickListener(onSweetClickListener)
                .show();
    }

    public static void onWarning(Context context, String alert, SweetAlertDialog.OnSweetClickListener OK, SweetAlertDialog.OnSweetClickListener NO){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Cảnh báo!")
                .setContentText(alert)
                .setConfirmClickListener(OK)
                .setCancelClickListener(NO)
                .show();
    }
}
