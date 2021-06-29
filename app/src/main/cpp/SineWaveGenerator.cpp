//
// Created by logan.beard on 29/06/2021.
//

#include "WaveGenerator.h"
#include <math.h>

#define TWO_PI (M_PI * 2)

class SineWaveGenerator: public WaveGenerator {
    double waveFunction(double phase) {
        return sin(phase) * amplitude_;
    }
};