//
// Created by Logan on 13/06/2021.
//

#include "Synthesizer.h"
#include "SineWaveGenerator.cpp"

void Synthesizer::render(float *audioData, int waveShape, int32_t numFrames) {
    switch (waveShape) {
        case waveType::sine:
            renderSineWave_(audioData, numFrames);
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
    }
}

void Synthesizer::setSampleRate(int32_t sampleRate) {
    sampleRate = sampleRate;
}

void Synthesizer::renderSineWave_(float *audioData, int32_t numFrames) {
    SineWaveGenerator* sw = new SineWaveGenerator(500.0, 1.0);
    sw->setSampleRate(sampleRate);

    for (int i = 0; i < numFrames; i++) {
        audioData[i] = (float) sw->waveFunction();
    }
}