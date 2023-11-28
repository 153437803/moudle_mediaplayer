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

#ifndef J4A__tv_danmaku_ijk_media_player_IjkMediaPlayer__H
#define J4A__tv_danmaku_ijk_media_player_IjkMediaPlayer__H

#include "ijkj4a/j4a/j4a_base.h"

jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__get(JNIEnv *env, jobject thiz);
jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__get__catchAll(JNIEnv *env, jobject thiz);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__set(JNIEnv *env, jobject thiz, jlong value);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__set__catchAll(JNIEnv *env, jobject thiz, jlong value);
jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__get(JNIEnv *env, jobject thiz);
jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__get__catchAll(JNIEnv *env, jobject thiz);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__set(JNIEnv *env, jobject thiz, jlong value);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__set__catchAll(JNIEnv *env, jobject thiz, jlong value);
jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__get(JNIEnv *env, jobject thiz);
jlong J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__get__catchAll(JNIEnv *env, jobject thiz);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__set(JNIEnv *env, jobject thiz, jlong value);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__set__catchAll(JNIEnv *env, jobject thiz, jlong value);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__postEventFromNative(JNIEnv *env, jobject weakThiz, jint what, jint arg1, jint arg2, jobject obj);
void J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__postEventFromNative__catchAll(JNIEnv *env, jobject weakThiz, jint what, jint arg1, jint arg2, jobject obj);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec(JNIEnv *env, jobject weakThiz, jstring mimeType, jint profile, jint level);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__catchAll(JNIEnv *env, jobject weakThiz, jstring mimeType, jint profile, jint level);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asGlobalRef__catchAll(JNIEnv *env, jobject weakThiz, jstring mimeType, jint profile, jint level);
const char *J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asCBuffer(JNIEnv *env, jobject weakThiz, jstring mimeType, jint profile, jint level, char *out_buf, int out_len);
const char *J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asCBuffer__catchAll(JNIEnv *env, jobject weakThiz, jstring mimeType, jint profile, jint level, char *out_buf, int out_len);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString(JNIEnv *env, jobject weakThiz, const char *mimeType_cstr__, jint profile, jint level);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__catchAll(JNIEnv *env, jobject weakThiz, const char *mimeType_cstr__, jint profile, jint level);
jstring J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asGlobalRef__catchAll(JNIEnv *env, jobject weakThiz, const char *mimeType_cstr__, jint profile, jint level);
const char *J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer(JNIEnv *env, jobject weakThiz, const char *mimeType_cstr__, jint profile, jint level, char *out_buf, int out_len);
const char *J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer__catchAll(JNIEnv *env, jobject weakThiz, const char *mimeType_cstr__, jint profile, jint level, char *out_buf, int out_len);
jboolean J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onNativeInvoke(JNIEnv *env, jobject weakThiz, jint what, jobject args);
jboolean J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onNativeInvoke__catchAll(JNIEnv *env, jobject weakThiz, jint what, jobject args);
int J4A_loadClass__J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer(JNIEnv *env);

#define J4A_HAVE_SIMPLE__J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer

#define J4AC_IjkMediaPlayer__mNativeMediaPlayer__get J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__get
#define J4AC_IjkMediaPlayer__mNativeMediaPlayer__get__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__get__catchAll
#define J4AC_IjkMediaPlayer__mNativeMediaPlayer__set J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__set
#define J4AC_IjkMediaPlayer__mNativeMediaPlayer__set__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaPlayer__set__catchAll
#define J4AC_IjkMediaPlayer__mNativeMediaDataSource__get J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__get
#define J4AC_IjkMediaPlayer__mNativeMediaDataSource__get__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__get__catchAll
#define J4AC_IjkMediaPlayer__mNativeMediaDataSource__set J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__set
#define J4AC_IjkMediaPlayer__mNativeMediaDataSource__set__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeMediaDataSource__set__catchAll
#define J4AC_IjkMediaPlayer__mNativeAndroidIO__get J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__get
#define J4AC_IjkMediaPlayer__mNativeAndroidIO__get__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__get__catchAll
#define J4AC_IjkMediaPlayer__mNativeAndroidIO__set J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__set
#define J4AC_IjkMediaPlayer__mNativeAndroidIO__set__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__mNativeAndroidIO__set__catchAll
#define J4AC_IjkMediaPlayer__postEventFromNative J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__postEventFromNative
#define J4AC_IjkMediaPlayer__postEventFromNative__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__postEventFromNative__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec
#define J4AC_IjkMediaPlayer__onSelectCodec__asCBuffer J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asCBuffer
#define J4AC_IjkMediaPlayer__onSelectCodec__asCBuffer__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asCBuffer__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec__asGlobalRef__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__asGlobalRef__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec__withCString J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString
#define J4AC_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer
#define J4AC_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asCBuffer__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec__withCString__asGlobalRef__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__asGlobalRef__catchAll
#define J4AC_IjkMediaPlayer__onSelectCodec__withCString__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onSelectCodec__withCString__catchAll
#define J4AC_IjkMediaPlayer__onNativeInvoke J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onNativeInvoke
#define J4AC_IjkMediaPlayer__onNativeInvoke__catchAll J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer__onNativeInvoke__catchAll
#define J4A_loadClass__J4AC_IjkMediaPlayer J4A_loadClass__J4AC_tv_danmaku_ijk_media_player_IjkMediaPlayer

#endif//J4A__tv_danmaku_ijk_media_player_IjkMediaPlayer__H
