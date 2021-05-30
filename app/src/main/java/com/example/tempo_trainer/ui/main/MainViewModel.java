package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Double> backswingDuration;
    private MutableLiveData<Double> downswingDuration;
    private MutableLiveData<Double> tempoRatio;
    private MutableLiveData<Boolean> running;

    public MutableLiveData<Boolean> getRunning() {
        if (running == null) {
            running = new MutableLiveData<Boolean>(false);
        }
        return running;
    }
    public MutableLiveData<Double> getBackswingDuration() {
        if (backswingDuration == null) {
            backswingDuration = new MutableLiveData<Double>(0.75);
        }
        return backswingDuration;
    }
    public MutableLiveData<Double> getDownswingDuration() {
        if (downswingDuration == null) {
            downswingDuration = new MutableLiveData<Double>(0.25);
        }
        return downswingDuration;
    }
    public MutableLiveData<Double> getTempoRatio() {
        if (tempoRatio == null) {
            tempoRatio = new MutableLiveData<Double>(3.0) {
            };
        }
        return tempoRatio;
    }

    public void toggleRunning() {
        getRunning().setValue(!getRunning().getValue());
    }
}