package com.enib.toolty.ui.scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.enib.toolty.R;
import com.enib.toolty.ui.Pedometer.PedometerViewModel;

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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        if(bitmap == null) {return;}

        imageView.setImageBitmap(bitmap);
    }
}