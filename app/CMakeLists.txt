file(GLOB AUDIOENGINE_SOURCE_FILES src/main/cpp/*.cpp)
file(GLOB AUDIOENGINE_HEADER_FILES src/main/cpp/*.h)

cmake_minimum_required(VERSION 3.4.1)

add_library(native-audioengine
            SHARED
            ${AUDIOENGINE_SOURCE_FILES}
            ${AUDIOENGINE_HEADER_FILES}
)

include_directories(src/main/cpp)

target_link_libraries(native-audioengine
                      aaudio
                      android
                      log)