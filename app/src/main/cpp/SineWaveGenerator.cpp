//
// Created by logan.beard on 29/06/2021.
//

#include "WaveGenerator.h"
#include <math.h>

#define TWO_PI (M_PI * 2)

class SineWaveGenerator: public WaveGenerator {
    public:
        SineWaveGenerator(float frequency, float amplitude, float sweep=0.0, float rise=0.0)
                : WaveGenerator(frequency, amplitude, sweep, rise) {}

        double waveFunction() {
            double fn = sin(phase_) * amplitude_;
            phase_ += phaseIncrement_;
            if (phase_ > TWO_PI) {
                phase_ -= TWO_PI;  // wrap-around
            }
            return fn;
        }

        void setSampleRate(int32_t sampleRate) {
            phaseIncrement_ = (TWO_PI * frequency_) / (double) sampleRate;
        }
};