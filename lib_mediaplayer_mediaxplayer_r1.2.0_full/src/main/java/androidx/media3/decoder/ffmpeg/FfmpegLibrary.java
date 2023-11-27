/*
 * Copyright (C) 2016 The Android Open Source Project
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
package androidx.media3.decoder.ffmpeg;

import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import lib.kalu.mediax.util.ExoLogUtil;

@UnstableApi /**
 * Configures and queries the underlying native library.
 */
public final class FfmpegLibrary {

    static {
        System.loadLibrary("exoplayer-ffmpeg");
        MediaLibraryInfo.registerModule("goog.exo.ffmpeg");
    }

    private static @MonotonicNonNull String version;
    private static int inputBufferPaddingSize = C.LENGTH_UNSET;

    private FfmpegLibrary() {
    }


    /**
     * Returns whether the underlying library is available, loading it if necessary.
     */
    public static boolean isAvailable() {
        return true;
    }

    /**
     * Returns the version of the underlying library if available, or null otherwise.
     */
    @Nullable
    public static String getVersion() {
        if (!isAvailable()) {
            return null;
        }
        if (version == null) {
            version = ffmpegGetVersion();
        }
        return version;
    }

    /**
     * Returns the required amount of padding for input buffers in bytes, or {@link C#LENGTH_UNSET} if
     * the underlying library is not available.
     */
    public static int getInputBufferPaddingSize() {
        if (!isAvailable()) {
            return C.LENGTH_UNSET;
        }
        if (inputBufferPaddingSize == C.LENGTH_UNSET) {
            inputBufferPaddingSize = ffmpegGetInputBufferPaddingSize();
        }
        return inputBufferPaddingSize;
    }

    /**
     * Returns whether the underlying library supports the specified MIME type.
     *
     * @param mimeType The MIME type to check.
     */
    public static boolean supportsFormat(String mimeType) {
        if (!isAvailable()) {
            return false;
        }
        @Nullable String codecName = getCodecName(mimeType);
        if (codecName == null) {
            return false;
        }
        if (!ffmpegHasDecoder(codecName)) {
            return false;
        }
        return true;
    }

    @Nullable
    static String getCodecName(String mimeType) {
        return getCodecName(false, mimeType);
    }

    /**
     * Returns the name of the FFmpeg decoder that could be used to decode the format, or {@code null}
     * if it's unsupported.
     */
    @Nullable
    static String getCodecName(boolean hasVideo, String mimeType) {
        ExoLogUtil.log("FfmpegLibrary => mimeType = " + mimeType);

        if (hasVideo) {
            switch (mimeType) {
                case MimeTypes.AUDIO_AAC:
                    return "aac";
                case MimeTypes.AUDIO_MPEG:
                case MimeTypes.AUDIO_MPEG_L1:
                case MimeTypes.AUDIO_MPEG_L2:
                    return "mp3";
                case MimeTypes.AUDIO_AC3:
                    return "ac3";
                case MimeTypes.AUDIO_E_AC3:
                case MimeTypes.AUDIO_E_AC3_JOC:
                    return "eac3";
                case MimeTypes.AUDIO_TRUEHD:
                    return "truehd";
                case MimeTypes.AUDIO_DTS:
                case MimeTypes.AUDIO_DTS_HD:
                    return "dca";
                case MimeTypes.AUDIO_VORBIS:
                    return "vorbis";
                case MimeTypes.AUDIO_OPUS:
                    return "opus";
                case MimeTypes.AUDIO_AMR_NB:
                    return "amrnb";
                case MimeTypes.AUDIO_AMR_WB:
                    return "amrwb";
                case MimeTypes.AUDIO_FLAC:
                    return "flac";
                case MimeTypes.AUDIO_ALAC:
                    return "alac";
                case MimeTypes.AUDIO_MLAW:
                    return "pcm_mulaw";
                case MimeTypes.AUDIO_ALAW:
                    return "pcm_alaw";
                case MimeTypes.VIDEO_H264:
                    return "h264";
                case MimeTypes.VIDEO_H265:
                    return "hevc";
                case MimeTypes.VIDEO_MPEG2:
                    return "mpeg2video";
                default:
                    return null;
            }
        } else {
            switch (mimeType) {
                case MimeTypes.AUDIO_AAC:
                    return "aac";
                case MimeTypes.AUDIO_MPEG:
                case MimeTypes.AUDIO_MPEG_L1:
                case MimeTypes.AUDIO_MPEG_L2:
                    return "mp3";
                case MimeTypes.AUDIO_AC3:
                    return "ac3";
                case MimeTypes.AUDIO_E_AC3:
                case MimeTypes.AUDIO_E_AC3_JOC:
                    return "eac3";
                case MimeTypes.AUDIO_TRUEHD:
                    return "truehd";
                case MimeTypes.AUDIO_DTS:
                case MimeTypes.AUDIO_DTS_HD:
                    return "dca";
                case MimeTypes.AUDIO_VORBIS:
                    return "vorbis";
                case MimeTypes.AUDIO_OPUS:
                    return "opus";
                case MimeTypes.AUDIO_AMR_NB:
                    return "amrnb";
                case MimeTypes.AUDIO_AMR_WB:
                    return "amrwb";
                case MimeTypes.AUDIO_FLAC:
                    return "flac";
                case MimeTypes.AUDIO_ALAC:
                    return "alac";
                case MimeTypes.AUDIO_MLAW:
                    return "pcm_mulaw";
                case MimeTypes.AUDIO_ALAW:
                    return "pcm_alaw";
                case MimeTypes.VIDEO_H264:
                    return "h264";
                default:
                    return null;
            }
        }
    }

    public static native void ffmpegLogger(boolean enable);

    private static native String ffmpegGetVersion();

    private static native int ffmpegGetInputBufferPaddingSize();

    private static native boolean ffmpegHasDecoder(String codecName);
}
