package com.enib.toolty.ui.gps;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.enib.toolty.R;

public class GPSFragment extends Fragment {

    private GPSViewModel GPSViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GPSViewModel =
                ViewModelProviders.of(this).get(GPSViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn = root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(
                        "STATE",
                        GPSViewModel.getPhoneNumber().getValue()
                );
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
        return root;
    }
}