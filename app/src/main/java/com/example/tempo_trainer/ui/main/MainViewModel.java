package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private float backswing_speed;
    private float downswing_speed;
    private float tempo_ratio;
    private boolean running;

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}