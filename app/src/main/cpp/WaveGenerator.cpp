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

double WaveGenerator::waveFunction(double phase) {};

void WaveGenerator::setSampleRate(int32_t sampleRate) {};