package com.kanad.health;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DialogTimeoutListener implements DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    private static final int AUTO_DISMISS_MILLIS = (int) (1.5 * 60 * 1000);
    private CountDownTimer mCountDownTimer;

    @Override
    public void onShow(final DialogInterface dialog) {
        final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
        final CharSequence positiveButtonText = defaultButton.getText();
        mCountDownTimer = new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 60000) {
                    defaultButton.setText(String.format(
                            Locale.getDefault(), "%s (%d:%02d)",
                            positiveButtonText,
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished % 60000)
                    ));
                } else {
                    defaultButton.setText(String.format(
                            Locale.getDefault(), "%s (%d)",
                            positiveButtonText,
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                    ));
                }
                defaultButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished % 60000)!=0){
                            Toast.makeText(((AlertDialog) dialog).getContext(), "Please wait, till timer ends", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFinish() {
                if (((AlertDialog) dialog).isShowing()) {
                    Toast.makeText(((AlertDialog) dialog).getContext(), "Request Timeout, Retry.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mCountDownTimer.cancel();
    }
}