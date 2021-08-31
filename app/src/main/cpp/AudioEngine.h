//
// Created by logan.beard on 30/06/2021.
//

#ifndef TEMPO_TRAINER_AUDIOENGINE_H
#define TEMPO_TRAINER_AUDIOENGINE_H

#include <aaudio/AAudio.h>
#include "Synthesizer.h"

class AudioEngine {
public:
    bool start();
    void stop();
    void restart();

    void addWave(waveType waveShape, float frequency, float amplitude, float sweep, float rise);
    void clearWaves();

private:
    Synthesizer synthesizer_;
    AAudioStream *stream_;
};

#endif //TEMPO_TRAINER_AUDIOENGINE_H
