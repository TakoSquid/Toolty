package com.enib.toolty.ui.gps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.enib.toolty.MainActivity;
import com.enib.toolty.R;
import com.enib.toolty.listener.MessageListener;
import com.enib.toolty.listener.SmsListener;

public class GPSFragment extends Fragment implements MessageListener {

    private GPSViewModel GPSViewModel;
    private String TAG = GPSFragment.class.getName();
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GPSViewModel = new ViewModelProvider(requireActivity()).get(GPSViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn = root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(GPSViewModel.getPhoneNumber().getValue(), "://gps");
            }
        });

        final EditText editText = root.findViewById(R.id.lost_phone_number);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                GPSViewModel.setPhoneNumber(
                        editable.toString()
                );
            }
        });

        SmsListener.bindListener(this);
        return root;
    }

    public void sendSMS(String phoneNumber, String msg) {
        final int PERMISSION_REQUEST_CODE = 1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (root.getContext().checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.
                    PERMISSION_DENIED) {
                Log.d(" permission ", " permission denied to SEND_SMS - requesting it ");
                String[] permissions = {android.Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
        Toast.makeText(root.getContext(), "SMS sent.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void messageReceived(SmsMessage message) {
        String code = message.getMessageBody().substring(0,  6);
        Log.d(TAG, "Code (://gps): " + code);
        if (code.equals("://gps"))
        {
            Toast.makeText(root.getContext(), "SMS requesting location received !", Toast.LENGTH_LONG).show();
            if (GPSViewModel.getLocation().getValue() == null) {
                Toast.makeText(root.getContext(), "No location data available", Toast.LENGTH_LONG).show();
            }
            else {
                String msg = "Longitude: " + GPSViewModel.getLocation().getValue().getLongitude() + ";"
                        + "Latitude :" + GPSViewModel.getLocation().getValue().getLatitude() + ";"
                        + "Altitude :" + GPSViewModel.getLocation().getValue().getAltitude();
                sendSMS(message.getOriginatingAddress(), "My location is : " + msg);
            }
        }
    }
}