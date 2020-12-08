package com.enib.toolty.ui.gps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GPSViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mPhoneNumber;

    public GPSViewModel() {
        mText = new MutableLiveData<>();
        mPhoneNumber = new MutableLiveData<>();

        mText.setValue("This is home fragment");
        mPhoneNumber.setValue("51234");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getPhoneNumber() { return mPhoneNumber; }

    public void setPhoneNumber(String s) { mPhoneNumber.setValue(s); }
}