file(GLOB SYNTHESIZER_SOURCE_FILES src/main/cpp/*.cpp)
file(GLOB SYNTHESIZER_HEADER_FILES src/main/cpp/*.h)

cmake_minimum_required(VERSION 3.4.1)

add_library(Synthesizer
            SHARED
            ${SYNTHESIZER_SOURCE_FILES}
            ${SYNTHESIZER_HEADER_FILES}
)

include_directories(src/main/cpp)