package com.technologies.gimick.stories.utils;

/*
All the alert boxes used in the project is declared in this class
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.activities.LoginSignUpActivity;
import com.technologies.gimick.stories.databinding.ForgetPassBinding;
import com.technologies.gimick.stories.databinding.ResetPassBinding;

/**

 */
public class UiAlertDialog {


    public static void showAltertDialog(Context ctx, String title, String message) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNeutralButton("OK", null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAltertDialogForLogin(final Activity activity, final Context ctx, String title, String message) {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.finish();
                    activity.startActivity(new Intent(ctx, LoginSignUpActivity.class));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void forgetPassDialog(Activity act, final Context context, String title) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = act.getLayoutInflater();
            final ForgetPassBinding binding = DataBindingUtil.inflate(inflater, R.layout.forget_pass, null, false);
            dialogBuilder.setView(binding.getRoot());
            dialogBuilder.setTitle(title);
//        dialogBuilder.setMessage("Enter text below");
            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String email = binding.tilForgetPass.getEditText().getText().toString();
                    new ForgetPasswordApi(email, context);

                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetPassDialog(Activity act, final Context context, String title) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = act.getLayoutInflater();
            final ResetPassBinding binding = DataBindingUtil.inflate(inflater, R.layout.reset_pass, null, false);
            dialogBuilder.setView(binding.getRoot());
            dialogBuilder.setTitle(title);
//        dialogBuilder.setMessage("Enter text below");
            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String email = binding.tilForgetPass.getEditText().getText().toString();
                    String oldPass = binding.tilOldPass.getEditText().getText().toString();
                    String newPass = binding.tilNewPass.getEditText().getText().toString();
                    String oldPassEnc = PasswordEncrypter.getInstance().getEncryptedPassword(oldPass + Constants.SALT_KEY);
                    String newPassEnc = PasswordEncrypter.getInstance().getEncryptedPassword(newPass + Constants.SALT_KEY);
                    new ResetPasswordApi(context).resetPassword(email, oldPassEnc, newPassEnc);

                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
