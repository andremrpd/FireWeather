#include <jni.h>
#include <string>
#include "encode-lib.cpp"

extern "C"

JNIEXPORT jstring JNICALL
Java_com_andredina_fireweather_repository_WeatherRepository_getAppID(
        JNIEnv *env, jobject thiz) {
    std::string encoded = "YWYxNDNmOTMwMjYwOTJhMDZmMzA0OGFlMWFmYWM1NmY=";
    std::string decoded = decode(encoded);
    return env->NewStringUTF(decoded.c_str());
}

