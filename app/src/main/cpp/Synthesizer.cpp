//
// Created by Logan on 13/06/2021.
//

#include "Synthesizer.h"
#include "WaveGenerator.h"

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

void Synthesizer::renderSineWave_(float *audioData, int32_t numFrames) {
}