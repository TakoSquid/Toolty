package com.enib.toolty.listener;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * A broadcast receiver who listens for incoming SMS
 */

public class SmsListener extends BroadcastReceiver {

    private static MessageListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        for (final SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            mListener.messageReceived(smsMessage);
        }
    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}
