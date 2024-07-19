package lib.kalu.mediaplayer.type;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(CLASS)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
public @interface PlayerType {

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface NetType {
        int HTTP = 2_001;
        int EXO_OKHTTP = 2_002;
        int DEFAULT = HTTP;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({HTTP, EXO_OKHTTP, DEFAULT})
        @interface Value {
        }
    }

    /**
     * 播放模式
     * 普通模式，小窗口模式，正常模式三种其中一种
     */
    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface WindowType {
        //普通模式
        int NORMAL = 2_001;
        //全屏模式
        int FULL = 2_002;
        //窗口模式
        int FLOAT = 2_003;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({NORMAL, FULL, FLOAT})
        @interface Value {
        }
    }

    /**
     * 播放状态，主要是指播放器的各种状态
     */
    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface StateType {
        int INIT = 3_001; // 播放未开始，即将进行
        int SEEK_START = 3_002; // 开始快进
        int SEEK_FINISH = 3_003; // 结束快进
        int CLEAN = 3_004; //
        int LOADING_START = 3_005; // 开始转圈
        int LOADING_STOP = 3_006; // 停止转圈(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
        int KERNEL_STOP = 3_007;
        int KERNEL_RESUME = 3_008; // 开始播放
        int VIDEO_RENDERING_START = 3_009; // 出画面 视频首帧
        int VIDEO_RENDERING_START_SEEK = 3_010; // 续播
        int START = 3_011; // 开始播放
        int START_PLAY_WHEN_READY_YES = 3_012; // 开始播放
        int START_PLAY_WHEN_READY_NO = 3_013; // 开始播放
        int START_RETRY = 3_014; // 开始播放
        int START_SEEK = 3_015; // 开始播放
        int END = 3_016; // 播放完成
        int TRY_SEE_FINISH = 3_017; // 试看完成
        int PAUSE = 3_018; // 暂停播放
        int RESUME = 3_019; // 恢复播放
        int RESTAER = 3_020; // 重播一次
        int CLOSE = 3_021; // 暂停播放
        int BUFFERING_TIMEOUT = 3_022; // 开始缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
        int BUFFERING_START = 3_023; // 开始缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
        int BUFFERING_STOP = 3_024; // 停止缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
        int START_ABORT = 3_025; // 开始播放中止
        int ONCE_LIVE = 3_026; // 即将开播
        int FAST_FORWARD_START = 3_027; // 快进
        int FAST_FORWARD_STOP = 3_028; // 快进
        int FAST_REWIND_START = 3_029; // 快进
        int FAST_REWIND_STOP = 3_030; // 快进

        int ERROR = 3_031; // 错误
        int COMPONENT_SEEK_SHOW = 3_032; // 显示进度条
        int RELEASE = 3_033;
        int RELEASE_EXCEPTION = 3_034;

        int FULL_START = 3_035;
        int FULL_SUCC = 3_036;
        int FULL_FAIL = 3_037;
        int FLOAT_START = 3_038;
        int FLOAT_SUCC = 3_039;
        int FLOAT_FAIL = 3_040;


        int COMPONENT_MENU_SHOW = 3_041;
        int COMPONENT_MENU_HIDE = 3_042;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({
                INIT,
                SEEK_START,
                SEEK_FINISH,
                FULL_START,
                FULL_SUCC,
                FULL_FAIL,
                FLOAT_START,
                FLOAT_SUCC,
                FLOAT_FAIL,
                FAST_FORWARD_START,
                FAST_FORWARD_STOP,
                FAST_REWIND_START,
                FAST_REWIND_STOP,
                CLEAN,
                KERNEL_STOP,
                KERNEL_RESUME,
                VIDEO_RENDERING_START,
                VIDEO_RENDERING_START_SEEK,
                START,
                START_PLAY_WHEN_READY_YES,
                START_PLAY_WHEN_READY_NO,
                START_RETRY,
                START_SEEK,
                PAUSE,
                RESUME,
                RESTAER,
                CLOSE,
                BUFFERING_TIMEOUT,
                BUFFERING_START,
                BUFFERING_STOP,
                LOADING_STOP,
                TRY_SEE_FINISH,
                END,
                START_ABORT,
                LOADING_START,
                ONCE_LIVE,
                ERROR,
                RELEASE,
                RELEASE_EXCEPTION,
                COMPONENT_SEEK_SHOW,
                COMPONENT_MENU_SHOW,
                COMPONENT_MENU_HIDE})
        @interface Value {
        }
    }

    /**
     * 播放视频缩放类型
     */
    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface ScaleType {
        int AUTO = 4_001;  // 自适应屏幕，可能存在黑边
        int FULL = 4_002;  // 画面拉甚至全屏, 可能变形
        int REAL = 4_003;  // 视频原始尺寸, 可能存在黑边
        int _16_9 = 4_004;  // 画面拉伸16：9, 可能变形
        int _16_10 = 4_005; // 画面拉伸16：10, 可能变形
        int _5_4 = 4_006;   // 画面拉伸5：4, 可能变形
        int _4_3 = 4_007;   // 画面拉伸4：3, 可能变形
        int _1_1 = 4_008;   // 画面拉伸1:1, 可能变形
        int DEFAULT = AUTO; // 默认

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({AUTO,
                FULL,
                REAL,
                _16_9,
                _16_10,
                _5_4,
                _4_3,
                _1_1,
                DEFAULT})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface RotationType {
        int _0 = 5_001;
        int _90 = 5_002;
        int _180 = 5_003;
        int _270 = 5_004;
        int DEFAULT = _0;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({_0,
                _90,
                _180,
                _270,
                DEFAULT})
        @interface Value {
        }
    }

    /**
     * 通过注解限定类型
     */
    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface KernelType {
        int ANDROID = 6_001; // MediaPlayer，基于原生自带的播放器控件
        int EXO_V2 = 6_002; // exoplayer2
        int MEDIA_V3 = 6_003; // androidx media
        int IJK = 6_004; // ijk
        int VLC = 6_005; // vlc
        int FFPLAYER = 6_006; // ffmpeg
        int DEFAULT = ANDROID;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({IJK,
                ANDROID,
                EXO_V2,
                MEDIA_V3,
                VLC,
                FFPLAYER,
                DEFAULT})
        @interface Value {
        }
    }

    /**
     * 通过注解限定类型
     */
    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface RenderType {
        int TEXTURE_VIEW = 8_001;
        int SURFACE_VIEW = 8_002;
        int GL_SURFACE_VIEW = 8_003;
        int DEFAULT = SURFACE_VIEW;

        @IntDef({TEXTURE_VIEW,
                SURFACE_VIEW,
                GL_SURFACE_VIEW,
                DEFAULT})
        @Retention(RetentionPolicy.SOURCE)
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface DecoderType {
        int DEFAULT = 10_000;
        int EXO_ALL_CODEC = 10_001;
        int EXO_ALL_FFMPEG = 10_004;
        int EXO_ONLY_AUDIO_CODEC = 10_002;
        int EXO_ONLY_VIDEO_CODEC = 10_003;
        int EXO_ONLY_AUDIO_FFMPEG = 10_005;
        int EXO_ONLY_VIDEO_FFMPEG = 10_006;
        int EXO_VIDEO_CODEC_AUDIO_FFMPEG = 10_007;
        int EXO_VIDEO_FFMPEG_AUDIO_CODEC = 10_008;
        int IJK_ALL_CODEC = 10_009;
        int IJK_ALL_FFMPEG = 10_010;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                DecoderType.DEFAULT,
                DecoderType.EXO_ALL_CODEC,
                DecoderType.EXO_ALL_FFMPEG,
                DecoderType.EXO_ONLY_AUDIO_CODEC,
                DecoderType.EXO_ONLY_VIDEO_CODEC,
                DecoderType.EXO_ONLY_AUDIO_FFMPEG,
                DecoderType.EXO_ONLY_VIDEO_FFMPEG,
                DecoderType.EXO_VIDEO_CODEC_AUDIO_FFMPEG,
                DecoderType.EXO_VIDEO_FFMPEG_AUDIO_CODEC,
                DecoderType.IJK_ALL_CODEC,
                DecoderType.IJK_ALL_FFMPEG,})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface SeekType {

        int DEFAULT = 9_001;
        int EXO_CLOSEST_SYNC = 9_002;
        int EXO_PREVIOUS_SYNC = 9_003;
        int EXO_NEXT_SYNC = 9_004;
        int EXO_EXACT = 9_005;
        int ANDROID_SEEK_PREVIOUS_SYNC = 9_006;
        int ANDROID_SEEK_NEXT_SYNC = 9_007;
        int ANDROID_SEEK_CLOSEST_SYNC = 9_008;
        int ANDROID_SEEK_CLOSEST = 9_009;
        int IJK_SEEK_FASTSEEK = 9_010;
        int IJK_SEEK_NOBUFFER = 9_011;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                SeekType.DEFAULT,
                SeekType.EXO_CLOSEST_SYNC,
                SeekType.EXO_PREVIOUS_SYNC,
                SeekType.EXO_NEXT_SYNC,
                SeekType.EXO_EXACT,
                SeekType.ANDROID_SEEK_PREVIOUS_SYNC,
                SeekType.ANDROID_SEEK_NEXT_SYNC,
                SeekType.ANDROID_SEEK_CLOSEST_SYNC,
                SeekType.ANDROID_SEEK_CLOSEST,
                SeekType.IJK_SEEK_FASTSEEK,
                SeekType.IJK_SEEK_NOBUFFER,
        })
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface SchemeType {

        String RTMP = "rtmp";
        String RTSP = "rtsp";
        String _MPD = ".mpd";
        String _M3U = ".m3u";
        String _M3U8 = ".m3u8";
        String _MATCHES = ".*\\.ism(l)?(/manifest(\\(.+\\))?)?";

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @StringDef(value = {
                SchemeType.RTMP,
                SchemeType.RTSP,
                SchemeType._MPD,
                SchemeType._M3U,
                SchemeType._M3U8,
                SchemeType._MATCHES})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface EventType {
        int ERROR_URL = 7_001;
        int ERROR_RETRY = 7_002;
        int ERROR_SOURCE = 7_003;
        int ERROR_PARSE = 7_004;
        int ERROR_NET = 7_005;
        int ERROR_BUFFERING_TIMEOUT = 7_006;
        int LOADING_START = 7_007; // 开始转圈
        int LOADING_START_IGNORE = 7_008; // 开始转圈
        int LOADING_STOP = 7_009; // 停止转圈(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放
        // 播放结束
        //        // 开始渲染视频画面
//        int VIDEO_SEEK_RENDERING_START = IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START;
//        // 开始渲染视频画面
//        int AUDIO_SEEK_RENDERING_START = IMediaPlayer.MEDIA_INFO_AUDIO_SEEK_RENDERING_START;
//        // 开始渲染视频画面
//        int AUDIO_RENDERING_START = IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START;
        // 首帧画面
        int VIDEO_RENDERING_START = 7_010;
        int VIDEO_RENDERING_START_SEEK = 7_011;
        // 视频开播
        int VIDEO_START = 7_012;
        int VIDEO_START_PLAY_WHEN_READY_YES = 7_013;
        int VIDEO_START_PLAY_WHEN_READY_NO = 7_014;
        int VIDEO_END = 7_015;
//        int VIDEO_START_RETRY = 7_011;
//        int VIDEO_START_SEEK = IMediaPlayer.MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE;
        //        int VIDEO_SEEK_COMPLETE_B = IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START;
        //        int VIDEO_STOP = IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;
//        int VIDEO_END = IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;

        // 缓冲开始
//        int OPEN_INPUT = IMediaPlayer.MEDIA_INFO_OPEN_INPUT;
        // 缓冲开始
        int BUFFERING_START = 7_016;
        // 缓冲结束
        int BUFFERING_STOP = 7_017;
        int SEEK_START = 7_018;
        int SEEK_FINISH = 7_019;

        // 视频旋转信息
//        int VIDEO_ROTATION_CHANGED = IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED;
//        int AUDIO_DECODED_START = IMediaPlayer.MEDIA_INFO_AUDIO_DECODED_START;
//        int VIDEO_DECODED_START = IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({
                ERROR_URL,
                ERROR_RETRY,
                ERROR_SOURCE,
                ERROR_PARSE,
                ERROR_NET,
                ERROR_BUFFERING_TIMEOUT,
//                OPEN_INPUT,
                LOADING_START,
                LOADING_START_IGNORE,
                LOADING_STOP,
//                VIDEO_SEEK_RENDERING_START,
//                AUDIO_SEEK_RENDERING_START,
//                AUDIO_RENDERING_START,
                VIDEO_RENDERING_START,
                VIDEO_RENDERING_START_SEEK,
                VIDEO_START,
                VIDEO_START_PLAY_WHEN_READY_YES,
                VIDEO_START_PLAY_WHEN_READY_NO,
//                VIDEO_START_RETRY,
//                VIDEO_START_SEEK,
                VIDEO_END,
//                VIDEO_STOP,
//                VIDEO_END,
                BUFFERING_START,
                BUFFERING_STOP,
                SEEK_START,
                SEEK_FINISH,
//                VIDEO_ROTATION_CHANGED,
//                AUDIO_DECODED_START,
//                VIDEO_DECODED_START
        })
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface SpeedType {
        int _0_5 = 8_001;
        int _1_0 = 8_002;
        int _1_5 = 8_003;
        int _2_0 = 8_004;
        int _2_5 = 8_005;
        int _3_0 = 8_006;
        int _3_5 = 8_007;
        int _4_0 = 8_008;
        int _4_5 = 8_009;
        int _5_0 = 8_010;
        int DEFAULT = _1_0;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({_0_5,
                _1_0,
                _1_5,
                _2_0,
                _2_5,
                _3_0,
                _3_5,
                _4_0,
                _4_5,
                _5_0,
                DEFAULT})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface CacheType {

        int DEFAULT = 9_001;
        int EXO_OPEN = 9_002;
        int EXO_CLOSE = 9_003;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {CacheType.DEFAULT, CacheType.EXO_OPEN, CacheType.EXO_CLOSE})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface CacheLocalType {

        int INNER = 10_001;
        int EXTERNAL = 10_002;
        int DEFAULT = INNER;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                CacheLocalType.EXTERNAL,
                CacheLocalType.INNER,
                CacheLocalType.DEFAULT})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface CacheSizeType {

        int AUTO = 11_001;
        int _100 = 11_002;
        int _200 = 11_003;
        int _400 = 11_004;
        int _800 = 11_005;
        int DEFAULT = AUTO;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                CacheSizeType.AUTO,
                CacheSizeType.DEFAULT,
                CacheSizeType._100,
                CacheSizeType._200,
                CacheSizeType._400,
                CacheSizeType._800,
        })
        @interface Value {
        }
    }
}