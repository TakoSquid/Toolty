package com.enib.toolty.listener;

import android.telephony.SmsMessage;

public interface MessageListener {
    /**
     * To call this method when new message received and send back
     * @param message Message
     */
    void messageReceived(SmsMessage message);
}
