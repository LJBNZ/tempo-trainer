//
// Created by logan.beard on 29/06/2021.
//

#include "WaveGenerator.h"
#include <math.h>
#include <iostream>
#include <android/log.h>

#define TWO_PI (M_PI * 2)

class SineWaveGenerator: public WaveGenerator {
    public:
        SineWaveGenerator(float frequency, float amplitude, float sweep=0.0, float rise=0.0)
                : WaveGenerator(frequency, amplitude, sweep, rise) {}

        void renderWave(float *audioData, int32_t numFrames, int32_t sampleRate) override {
            setSampleRate_(sampleRate);

            std::string values = "\nSTART OF SEQUENCE:\n";
            for (int i = 0; i < numFrames; i++) {
                auto ampValue = (float) waveFunction();
                values += std::to_string(ampValue) + "\n";
                audioData[i] += ampValue;
            }
            values += "END OF SEQUENCE\n\n";
            __android_log_write(ANDROID_LOG_INFO, "sinewaveseq", const_cast<char*>(values.c_str()));
        }

    void setSampleRate_(int32_t sampleRate) override {
        phaseIncrement_ = (TWO_PI * frequency_) / (double) sampleRate;
    }

    double waveFunction() override {
            double fn = sin(phase_) * amplitude_;
            phase_ += phaseIncrement_;
            if (phase_ > TWO_PI) {
                phase_ -= TWO_PI;  // wrap-around
            }
            return fn;
        }

};