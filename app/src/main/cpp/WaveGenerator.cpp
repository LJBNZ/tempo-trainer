//
// Created by logan.beard on 29/06/2021.
//

#include "WaveGenerator.h"

WaveGenerator::WaveGenerator(float frequency, float amplitude, float sweep, float rise) {
    frequency_ = frequency;
    amplitude_ = amplitude;
    sweep_ = sweep;
    rise_ = rise;
}

void WaveGenerator::renderWave(float *audioData, int32_t numFrames, int32_t sampleRate) {};

double WaveGenerator::waveFunction() {
    return 0.0;
};

void WaveGenerator::setSampleRate_(int32_t sampleRate) {};