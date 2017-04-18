cmake_minimum_required(VERSION 3.4.1)

add_library(jnicallback SHARED
            jnicallback.c)

target_link_libraries(jnicallback
                      android
                      log)
