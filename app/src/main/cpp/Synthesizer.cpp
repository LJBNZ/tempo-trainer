//
// Created by Logan on 13/06/2021.
//

#include "Synthesizer.h"
#include "SineWaveGenerator.cpp"

void Synthesizer::addWave(waveType waveShape, float frequency, float amplitude, float sweep=0.0,
                          float rise=0.0) {
    switch (waveShape) {
        case waveType::sine:
            waves_.push_back(new SineWaveGenerator(frequency, amplitude, sweep=sweep, rise=rise));
            break;
        case waveType::sawTooth:
            // TODO
            break;
        case waveType::square:
            // TODO
            break;
        case waveType::triangle:
            // TODO
            break;
        default:
            break;
    }
}

void Synthesizer::clearWaves() {
    waves_.clear();
}

void Synthesizer::render(float *audioData, int32_t numFrames) {
    for (int i = 0; i < waves_.size(); i++) {
        WaveGenerator* gen = waves_[i];
        gen->renderWave(audioData, numFrames, sampleRate);
    }
}

void Synthesizer::setSampleRate(int32_t sampleRate) {
    this->sampleRate = sampleRate;
}
