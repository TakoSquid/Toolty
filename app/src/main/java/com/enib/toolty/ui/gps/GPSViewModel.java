package com.enib.toolty.ui.gps;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GPSViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mPhoneNumber;
    private MutableLiveData<Location> mLocation;

    public GPSViewModel() {
        mText = new MutableLiveData<>();
        mPhoneNumber = new MutableLiveData<>();
        mLocation = new MutableLiveData<>();

        mText.setValue("This is home fragment");
        mPhoneNumber.setValue("51234");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getPhoneNumber() { return mPhoneNumber; }

    public void setPhoneNumber(String s) { mPhoneNumber.setValue(s); }

    public void setLocation(Location loc)
    {
        mLocation.setValue(loc);
    }

    public LiveData<Location> getLocation() { return mLocation; }
}