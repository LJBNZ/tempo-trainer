package com.example.tempo_trainer.audio;


public class AudioManager {
    // The interface between the App logic and the C++ Audio Engine (via JNI bridge)

    boolean running = false;

    public void start() {
        running = true;
        produceTone();
    }

    public void stop() {
        running = false;
        JNIStopAudio();

    }

    private void produceTone() {
        while (running) {
            // do sound
        }
    }

    // JNI Events

    public native void JNIStartAudio();

    public native void JNIStopAudio();

    public native void JNIAddWave(int waveShape, float frequency, float amplitude, float sweep, float rise);

    public native void JNIClearWaves();

}
