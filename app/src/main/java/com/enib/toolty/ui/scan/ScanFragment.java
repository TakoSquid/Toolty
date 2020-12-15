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

    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final Button camBtn = root.findViewById(R.id.camButton);
        imageView = root.findViewById(R.id.imageView);

        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(ScanFragment.this);

                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.setPrompt("Scannez un QR code ou un code bar !");
                intentIntegrator.initiateScan();
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );

        if(intentResult.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getActivity()
            );

            builder.setTitle("Résultat du scan");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        }
    }
}