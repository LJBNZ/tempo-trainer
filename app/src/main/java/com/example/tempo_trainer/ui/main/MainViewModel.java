package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Double> backswingDuration;
    private MutableLiveData<Double> downswingDuration;
    private MutableLiveData<Double> tempoRatio;
    private MutableLiveData<Boolean> running;

    public Double minRatio = 1.0;
    public Double maxRatio = 5.0;
    public Double defaultRatio = 3.0;

    public Double minSwingPartDuration = 0.1;
    public Double maxSwingPartDuration = 5.0;

    public Double defaultBackswingDuration = 0.750;
    public Double defaultDownswingDuration = 0.250;

    public MutableLiveData<Boolean> getRunning() {
        if (running == null) {
            running = new MutableLiveData<Boolean>(false);
        }
        return running;
    }
    public MutableLiveData<Double> getBackswingDuration() {
        if (backswingDuration == null) {
            backswingDuration = new MutableLiveData<Double>(defaultBackswingDuration);
        }
        return backswingDuration;
    }
    public MutableLiveData<Double> getDownswingDuration() {
        if (downswingDuration == null) {
            downswingDuration = new MutableLiveData<Double>(defaultDownswingDuration);
        }
        return downswingDuration;
    }
    public MutableLiveData<Double> getTempoRatio() {
        if (tempoRatio == null) {
            tempoRatio = new MutableLiveData<Double>(defaultRatio);
        }
        return tempoRatio;
    }

    public void setBackswingDuration(Double duration) {
        // TODO error checking
        Double ratio = getTempoRatio().getValue();
        getBackswingDuration().setValue(duration);
        Double newDownswingDuration = duration / ratio;
        getDownswingDuration().setValue(newDownswingDuration);
        debugPrint();
    }

    public void setDownswingDuration(Double duration) {
        // TODO error checking
        Double ratio = getTempoRatio().getValue();
        getDownswingDuration().setValue(duration);
        Double newBackswingDuration = duration * ratio;
        getBackswingDuration().setValue(newBackswingDuration);
        debugPrint();
    }

    public void setTempoStep(int tempoStep) {
        // TODO error checking
        Double ratio = minRatio + (double) tempoStep / 10;  // convert steps to ratio
        Double downswingDuration = getDownswingDuration().getValue();
        Double newBackswingDuration = downswingDuration * ratio;
        getBackswingDuration().setValue(newBackswingDuration);
        debugPrint();
    }

    private void debugPrint() {
        System.out.printf(Locale.getDefault(),
                "bs: %f, ds: %f, ratio: %f",
                getBackswingDuration().getValue(),
                getDownswingDuration().getValue(),
                getTempoRatio().getValue());
    }

    public void forceStop() {
        getRunning().setValue(false);
    }

    public void toggleRunning() {
        getRunning().setValue(!getRunning().getValue());
    }
}