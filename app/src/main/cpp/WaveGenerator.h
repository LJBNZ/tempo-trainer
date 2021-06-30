//
// Created by logan.beard on 29/06/2021.
//

#ifndef TEMPO_TRAINER_WAVEGENERATOR_H
#define TEMPO_TRAINER_WAVEGENERATOR_H

#include <stdint.h>

class WaveGenerator {
public:
    WaveGenerator(float frequency, float amplitude, float sweep=0.0, float rise=0.0);
    void renderWave(float *audioData, int32_t numFrames);
    virtual double waveFunction(double phase);
    virtual void setSampleRate(int32_t sampleRate);

protected:
    double phase_ = 0.0;
    double phaseIncrement_ = 0.0;
    float frequency_;
    float amplitude_;
    float sweep_;
    float rise_;
};

#endif //TEMPO_TRAINER_WAVEGENERATOR_H
