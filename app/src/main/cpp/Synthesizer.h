//
// Created by Logan on 13/06/2021. Based on Google Codelab 'Making Waves'.
//

#ifndef TEMPO_TRAINER_SYNTHESIZER_H
#define TEMPO_TRAINER_SYNTHESIZER_H

#include <vector>
#include "WaveGenerator.h"
#include <stdint.h>

enum waveType {sine, sawTooth, square, triangle};

class Synthesizer {
public:
    int32_t sampleRate = 0;
    void addWave(waveType waveShape, float frequency, float amplitude, float sweep, float rise);
    void clearWaves();
    void render(float *audioData, int32_t numFrames);
    void setSampleRate(int32_t sampleRate);

private:
    std::vector<WaveGenerator*> waves_;
};


#endif //TEMPO_TRAINER_SYNTHESIZER_H
