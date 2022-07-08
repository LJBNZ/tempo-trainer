package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import com.example.tempo_trainer.audio.AudioManager;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Double> backswingDuration;
    private MutableLiveData<Double> downswingDuration;
    private MutableLiveData<Double> tempoRatio;
    private MutableLiveData<Boolean> running;
    private AudioManager audioManager;

    public double minTempoRatio = 2.0;
    public double maxTempoRatio = 4.0;
    public double defaultTempoRatio = 3.0;
    public double tempoStepResolution = 0.1;
    public int numTempoSteps = (int) ((maxTempoRatio - minTempoRatio) / tempoStepResolution);
    public int defaultTempoStep = (int) ((defaultTempoRatio - minTempoRatio) / tempoStepResolution);

    public double minDownswingTime = 0.2;
    public double maxDownswingTime = 0.5;
    public double defaultDownswingTime = 0.35;
    public double downswingStepResolution = 0.005;
    public int numDownswingSteps = (int) ((maxDownswingTime - minDownswingTime) / downswingStepResolution);
    public int defaultDownswingStep = (int) ((defaultDownswingTime - minDownswingTime) / downswingStepResolution);

    public MainViewModel() {
        audioManager = new AudioManager();
    }

    static {
        // load our built native audio engine shared library .dll
        System.loadLibrary("native-audioengine");
    }

    public MutableLiveData<Boolean> getRunning() {
        if (running == null) {
            running = new MutableLiveData<Boolean>(false);
        }
        return running;
    }

    public MutableLiveData<Double> getBackswingTime() {
        if (backswingDuration == null) {
            backswingDuration = new MutableLiveData<Double>(calculateBackswingTime());
        }
        return backswingDuration;
    }

    public MutableLiveData<Double> getDownswingTime() {
        if (downswingDuration == null) {
            downswingDuration = new MutableLiveData<Double>(defaultDownswingTime);
        }
        return downswingDuration;
    }

    public MutableLiveData<Double> getTempoRatio() {
        if (tempoRatio == null) {
            tempoRatio = new MutableLiveData<Double>(defaultTempoRatio);
        }
        return tempoRatio;
    }

    private double calculateBackswingTime() {
        return getDownswingTime().getValue() * getTempoRatio().getValue();
    }

    public void setDownswingStep(int downswingStep) {
        double downswingTime = minDownswingTime + (double) downswingStep * downswingStepResolution;
        getDownswingTime().setValue(downswingTime);
        double newBackswingTime = calculateBackswingTime();
        getBackswingTime().setValue(newBackswingTime);
        debugPrint();
    }

    public void setTempoStep(int tempoStep) {
        double ratio = minTempoRatio + (double) tempoStep * tempoStepResolution;
        getTempoRatio().setValue(ratio);
        double newBackswingTime = calculateBackswingTime();
        getBackswingTime().setValue(newBackswingTime);
        debugPrint();
    }

    private void debugPrint() {
        System.out.printf(Locale.getDefault(),
                "bs: %f, ds: %f, ratio: %f",
                getBackswingTime().getValue(),
                getDownswingTime().getValue(),
                getTempoRatio().getValue());
    }

    public void forceStop() {
        getRunning().setValue(false);
    }

    public void toggleRunning() {
        boolean newRunningStatus = !getRunning().getValue();
        getRunning().setValue(newRunningStatus);
        if (newRunningStatus) {
            audioManager.start();
        } else {
            audioManager.stop();
        }
    }
}