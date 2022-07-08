//
// Created by logan.beard on 30/06/2021.
//

#include <iostream>
#include <jni.h>
#include "AudioEngine.h"
#include "Synthesizer.h"

static AudioEngine *audioEngine = new AudioEngine();

extern "C" {

    JNIEXPORT void JNICALL
    Java_com_example_tempo_1trainer_audio_AudioManager_JNIStartAudio(JNIEnv *env, jobject /* this */) {
        audioEngine->start();
    }

    JNIEXPORT void JNICALL
    Java_com_example_tempo_1trainer_audio_AudioManager_JNIStopAudio(JNIEnv *env, jobject /* this */) {
        audioEngine->stop();
    }

    JNIEXPORT void JNICALL
    Java_com_example_tempo_1trainer_audio_AudioManager_JNIAddWave(JNIEnv *env, jobject /* this */,
                                                                     jint waveShape,
                                                                     jfloat waveFrequency,
                                                                     jfloat waveAmplitude,
                                                                     jfloat waveSweepRate,
                                                                     jfloat waveRiseRate) {
        audioEngine->addWave((waveType) waveShape,
                             waveFrequency,
                             waveAmplitude,
                             waveSweepRate,
                             waveRiseRate);
    }

    JNIEXPORT void JNICALL
    Java_com_example_tempo_1trainer_audio_AudioManager_JNIClearWaves(JNIEnv *env, jobject /* this */) {
        audioEngine->clearWaves();
    }

}