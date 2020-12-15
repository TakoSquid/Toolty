package com.enib.toolty.ui.scan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.enib.toolty.MainActivity;
import com.enib.toolty.R;
import com.enib.toolty.ui.Pedometer.PedometerViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import static com.google.zxing.integration.android.IntentIntegrator.forSupportFragment;

public class ScanFragment extends Fragment {

    private ScanViewModel scanViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final Button camBtn = root.findViewById(R.id.camButton);

        // Callback for the camera button
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating Intent (from dependency zxing ! Git for this project here : https://github.com/journeyapps/zxing-android-embedded)
                IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(ScanFragment.this);

                // Configuration
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.setPrompt("Scan a QR code or a code bar !");

                // Starting intent !
                intentIntegrator.initiateScan();
            }
        });

        return root;
    }

    // Activity ended callback
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Retrieve and parse activity result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );

        // If result contains usable content
        if(intentResult.getContents() != null) {

            // Creating a popup with the result
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getActivity()
            );

            // Configuration
            builder.setTitle("Scan result");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // Displaying it
            builder.show();
        }
    }
}