package com.enib.toolty.ui.Pedometer;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.enib.toolty.R;

import static android.content.Context.SENSOR_SERVICE;

public class PedometerFragment extends Fragment {

    private PedometerViewModel pedometerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Link this fragment with pedometer view model owned by the main activity
        pedometerViewModel = new ViewModelProvider(requireActivity()).get(PedometerViewModel.class);

        // Retrieve text fields
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final TextView stepView = root.findViewById(R.id.step_label);

        // Auto update fields
        pedometerViewModel.getStep().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                stepView.setText(String.valueOf(integer));
            }
        });

        // Reset button creation & callback
        final Button button = root.findViewById(R.id.btn_reset);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pedometerViewModel.reset();
            }
        });

        return root;
    }



}