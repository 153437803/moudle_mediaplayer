/*
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * https://github.com/Bilibili/jni4android
 * This file is automatically generated by jni4android, do not modify.
 */

#ifndef J4A__lib_kalu_ijkplayer_misc_IAndroidIO__H
#define J4A__lib_kalu_ijkplayer_misc_IAndroidIO__H

#include "j4a/j4a_base.h"

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open(JNIEnv *env, jobject thiz, jstring url);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__catchAll(JNIEnv *env, jobject thiz,
                                                                      jstring url);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__withCString(JNIEnv *env, jobject thiz,
                                                                         const char *url_cstr__);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__withCString__catchAll(JNIEnv *env,
                                                                                   jobject thiz,
                                                                                   const char *url_cstr__);

jint
J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__read(JNIEnv *env, jobject thiz, jbyteArray buffer,
                                                       jint size);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__read__catchAll(JNIEnv *env, jobject thiz,
                                                                      jbyteArray buffer, jint size);

jlong
J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__seek(JNIEnv *env, jobject thiz, jlong offset,
                                                       jint whence);

jlong J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__seek__catchAll(JNIEnv *env, jobject thiz,
                                                                       jlong offset, jint whence);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__close(JNIEnv *env, jobject thiz);

jint J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__close__catchAll(JNIEnv *env, jobject thiz);

int J4A_loadClass__J4AC_lib_kalu_ijkplayer_misc_IAndroidIO(JNIEnv *env);

#define J4A_HAVE_SIMPLE__J4AC_lib_kalu_ijkplayer_misc_IAndroidIO

#define J4AC_IAndroidIO__open J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open
#define J4AC_IAndroidIO__open__catchAll J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__catchAll
#define J4AC_IAndroidIO__open__withCString J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__withCString
#define J4AC_IAndroidIO__open__withCString__catchAll J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__open__withCString__catchAll
#define J4AC_IAndroidIO__read J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__read
#define J4AC_IAndroidIO__read__catchAll J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__read__catchAll
#define J4AC_IAndroidIO__seek J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__seek
#define J4AC_IAndroidIO__seek__catchAll J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__seek__catchAll
#define J4AC_IAndroidIO__close J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__close
#define J4AC_IAndroidIO__close__catchAll J4AC_lib_kalu_ijkplayer_misc_IAndroidIO__close__catchAll
#define J4A_loadClass__J4AC_IAndroidIO J4A_loadClass__J4AC_lib_kalu_ijkplayer_misc_IAndroidIO

#endif//J4A__lib_kalu_ijkplayer_misc_IAndroidIO__H
