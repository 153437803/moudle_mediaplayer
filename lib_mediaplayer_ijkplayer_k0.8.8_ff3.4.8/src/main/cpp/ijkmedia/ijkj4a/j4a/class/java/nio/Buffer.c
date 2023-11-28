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

#include "Buffer.h"

typedef struct J4AC_java_nio_Buffer {
    jclass id;
} J4AC_java_nio_Buffer;
static J4AC_java_nio_Buffer class_J4AC_java_nio_Buffer;

int J4A_loadClass__J4AC_java_nio_Buffer(JNIEnv *env) {
    int ret = -1;
    const char *J4A_UNUSED(name)      = NULL;
    const char *J4A_UNUSED(sign)      = NULL;
    jclass
    J4A_UNUSED(class_id) = NULL;
    int J4A_UNUSED(api_level) = 0;

    if (class_J4AC_java_nio_Buffer.id != NULL)
        return 0;

    sign = "java/nio/Buffer";
    class_J4AC_java_nio_Buffer.id = J4A_FindClass__asGlobalRef__catchAll(env, sign);
    if (class_J4AC_java_nio_Buffer.id == NULL)
        goto fail;

    J4A_ALOGD("J4ALoader: OK: '%s' loaded\n", "java.nio.Buffer");
    ret = 0;
    fail:
    return ret;
}
