//
// Created by logan.beard on 30/06/2021.
//

#include <jni.h>
#include "AudioEngine.h"

static AudioEngine *audioEngine = new AudioEngine();

extern "C" {

    JNIEXPORT void JNICALL
    Java_com_example_tempo_trainer_MainViewModel_toggleRunning(JNIEnv *env, jobject /* this */) {
        audioEngine->start();
    }

//    JNIEXPORT void JNICALL
//    Java_com_example_tempo_trainer_MainViewModel_stopTone(JNIEnv *env, jobject /* this */) {
//        audioEngine->stop();
//    }

}