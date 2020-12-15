package com.enib.toolty.ui.Pedometer;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PedometerViewModel extends ViewModel {
    private MutableLiveData<Integer> mStep;
    private int mStepReference;
    private boolean mNeedsReference;

    public PedometerViewModel() {

        mStep = new MutableLiveData<>();
        mStep.setValue(0);

        mStepReference = -1;
        mNeedsReference = true;
    }

    public LiveData<Integer> getStep() {
        return mStep;
    }

    public void setStepCounter(Integer amount) {

        if(mNeedsReference) {
            mStepReference = amount;
            mNeedsReference = false;
        }

        int delta = amount - mStepReference;

        Log.d("PedometerModel", "Called ! Reference : " + mStepReference + " amount : " + amount + " delta : " + delta);

        mStep.setValue(delta);
    }

    public void reset() {
        mStep.setValue(0);
        mNeedsReference = true;
    }
}