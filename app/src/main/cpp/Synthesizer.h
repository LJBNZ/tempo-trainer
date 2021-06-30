//
// Created by Logan on 13/06/2021. Based on Google Codelab 'Making Waves'.
//

#ifndef TEMPO_TRAINER_SYNTHESIZER_H
#define TEMPO_TRAINER_SYNTHESIZER_H

#include <stdint.h>

enum waveType {sine, sawTooth, square, triangle};

class Synthesizer {
public:
    int32_t sampleRate = 0;
    void render(float *audioData, int waveShape, int32_t numFrames);
    void setSampleRate(int32_t sampleRate);

private:
    void renderSineWave_(float *audioData, int32_t numFrames);
//    void renderSawToothWave_(float *audioData, int32_t numFrames);
//    void renderSquareWave_(float *audioData, int32_t numFrames);
//    void renderTriangleWave_(float *audioData, int32_t numFrames);
};


#endif //TEMPO_TRAINER_SYNTHESIZER_H
