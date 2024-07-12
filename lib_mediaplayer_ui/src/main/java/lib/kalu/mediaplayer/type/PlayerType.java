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
    @interface CacheType {

        int DOWNLOAD = 1_001;
        int NONE = 1_002;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {CacheType.DOWNLOAD, CacheType.NONE})
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
        int STATE_INIT = 3_001; // 播放未开始，即将进行
        int STATE_SEEK_PLAY_RECORD = 3_002; // 续播
        int STATE_SEEK_START = 3_003; // 开始快进
        int STATE_SEEK_FINISH = 3_004; // 结束快进
        int STATE_CLEAN = 3_005; //
        int STATE_LOADING_START = 3_006; // 开始转圈
        int STATE_LOADING_STOP = 3_007; // 停止转圈(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
        int STATE_KERNEL_STOP = 3_008;
        int STATE_KERNEL_RESUME = 3_009; // 开始播放
        int STATE_VIDEO_RENDERING_START = 3_010; // 出画面 视频首帧
        int STATE_START = 3_011; // 开始播放
        int STATE_START_RETRY = 3_012; // 开始播放
        int STATE_START_SEEK = 3_013; // 开始播放
        int STATE_START_PLAY_WHEN_READY_PAUSE = 3_014; // 开始播放
        int STATE_END = 3_015; // 播放完成
        int STATE_TRY_TO_SEE_FINISH = 3_016; // 试看完成
        int STATE_PAUSE = 3_017; // 暂停播放
        int STATE_RESUME = 3_019; // 恢复播放
        int STATE_RESTAER = 3_021; // 重播一次
        int STATE_CLOSE = 3_022; // 暂停播放
        int STATE_BUFFERING_TIMEOUT = 3_023; // 开始缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
        int STATE_BUFFERING_START = 3_024; // 开始缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
        int STATE_BUFFERING_STOP = 3_025; // 停止缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
        int STATE_START_ABORT = 3_026; // 开始播放中止
        int STATE_ONCE_LIVE = 3_027; // 即将开播
        int STATE_FAST_FORWARD_START = 3_028; // 快进
        int STATE_FAST_FORWARD_STOP = 3_029; // 快进
        int STATE_FAST_REWIND_START = 3_030; // 快进
        int STATE_FAST_REWIND_STOP = 3_031; // 快进

        int STATE_ERROR = 3_032; // 错误
        int STATE_COMPONENT_SEEK_SHOW = 3_034; // 显示进度条
        int STATE_RELEASE = 3_035;
        int STATE_RELEASE_EXCEPTION = 3_036;

        int STATE_FULL_START = 3_037;
        int STATE_FULL_SUCC = 3_038;
        int STATE_FULL_FAIL = 3_039;
        int STATE_FLOAT_START = 3_040;
        int STATE_FLOAT_SUCC = 3_041;
        int STATE_FLOAT_FAIL = 3_042;


        int STATE_COMPONENT_MENU_SHOW = 3_043;
        int STATE_COMPONENT_MENU_HIDE = 3_044;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({
                STATE_INIT,
                STATE_SEEK_PLAY_RECORD,
                STATE_SEEK_START,
                STATE_SEEK_FINISH,
                STATE_FULL_START,
                STATE_FULL_SUCC,
                STATE_FULL_FAIL,
                STATE_FLOAT_START,
                STATE_FLOAT_SUCC,
                STATE_FLOAT_FAIL,
                STATE_FAST_FORWARD_START,
                STATE_FAST_FORWARD_STOP,
                STATE_FAST_REWIND_START,
                STATE_FAST_REWIND_STOP,
                STATE_CLEAN,
                STATE_KERNEL_STOP,
                STATE_KERNEL_RESUME,
                STATE_VIDEO_RENDERING_START,
                STATE_START,
                STATE_START_RETRY,
                STATE_START_SEEK,
                STATE_START_PLAY_WHEN_READY_PAUSE,
                STATE_PAUSE,
                STATE_RESUME,
                STATE_RESTAER,
                STATE_CLOSE,
                STATE_BUFFERING_TIMEOUT,
                STATE_BUFFERING_START,
                STATE_BUFFERING_STOP,
                STATE_LOADING_STOP,
                STATE_TRY_TO_SEE_FINISH,
                STATE_END,
                STATE_START_ABORT,
                STATE_LOADING_START,
                STATE_ONCE_LIVE,
                STATE_ERROR,
                STATE_RELEASE,
                STATE_RELEASE_EXCEPTION,
                STATE_COMPONENT_SEEK_SHOW,
                STATE_COMPONENT_MENU_SHOW,
                STATE_COMPONENT_MENU_HIDE})
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
        int SCREEN_SCALE_AUTO = 4_001; // 自适应屏幕，可能存在黑边
        int SCREEN_SCALE_FULL = 4_002; // 画面拉甚至全屏, 可能变形
        int SCREEN_SCALE_REAL = 4_003; // 视频原始尺寸, 可能存在黑边
        int SCREEN_SCALE_16_9 = 4_004; // 画面拉16：9, 可能变形
        int SCREEN_SCALE_4_3 = 4_005;  // 画面拉4：3, 可能变形
        int SCREEN_SCALE_DEFAULT = SCREEN_SCALE_AUTO; // 默认

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({SCREEN_SCALE_AUTO,
                SCREEN_SCALE_FULL,
                SCREEN_SCALE_REAL,
                SCREEN_SCALE_16_9,
                SCREEN_SCALE_4_3,
                SCREEN_SCALE_DEFAULT})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface RotationType {
        int Rotation_0 = 5_001;
        int Rotation_90 = 5_002;
        int Rotation_180 = 5_003;
        int Rotation_270 = 5_004;
        int Rotation_Default = Rotation_0;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({Rotation_0,
                Rotation_90,
                Rotation_180,
                Rotation_270,
                Rotation_Default})
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
        int EXO_V1 = 6_002; // exo
        int EXO_V2 = 6_003; // exo
        int MEDIA_V3 = 6_004; // media3
        int IJK = 6_005; // ijk
        int IJK_MEDIACODEC = 6_006; // ijk_mediacodec
        int VLC = 6_007; // vlc
        int FFPLAYER = 6_008; // ffplayer

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({IJK,
                IJK_MEDIACODEC,
                ANDROID,
                EXO_V1,
                EXO_V2,
                MEDIA_V3,
                VLC,
                FFPLAYER})
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

        @IntDef({TEXTURE_VIEW,
                SURFACE_VIEW,
                GL_SURFACE_VIEW})
        @Retention(RetentionPolicy.SOURCE)
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface ExoSeekType {

        int EXO_SEEK_DEFAULT = 9_001;
        int EXO_SEEK_CLOSEST_SYNC = 9_002;
        int EXO_SEEK_PREVIOUS_SYNC = 9_003;
        int EXO_SEEK_NEXT_SYNC = 9_004;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                ExoSeekType.EXO_SEEK_DEFAULT,
                ExoSeekType.EXO_SEEK_CLOSEST_SYNC,
                ExoSeekType.EXO_SEEK_PREVIOUS_SYNC,
                ExoSeekType.EXO_SEEK_NEXT_SYNC})
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface ExoFFmpegType {
        int EXO_RENDERER_ONLY_MEDIACODEC = 10_001;
        int EXO_RENDERER_ONLY_MEDIACODEC_AUDIO = 10_002;
        int EXO_RENDERER_ONLY_MEDIACODEC_VIDEO = 10_003;
        int EXO_RENDERER_ONLY_FFMPEG = 10_004;
        int EXO_RENDERER_ONLY_FFMPEG_AUDIO = 10_005;
        int EXO_RENDERER_ONLY_FFMPEG_VIDEO = 10_006;
        int EXO_RENDERER_VIDEO_MEDIACODEC_AUDIO_FFMPEG = 10_007;
        int EXO_RENDERER_VIDEO_FFMPEG_AUDIO_MEDIACODEC = 10_008;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef(value = {
                ExoFFmpegType.EXO_RENDERER_ONLY_MEDIACODEC,
                ExoFFmpegType.EXO_RENDERER_ONLY_MEDIACODEC_AUDIO,
                ExoFFmpegType.EXO_RENDERER_ONLY_MEDIACODEC_VIDEO,
                ExoFFmpegType.EXO_RENDERER_ONLY_FFMPEG,
                ExoFFmpegType.EXO_RENDERER_ONLY_FFMPEG_AUDIO,
                ExoFFmpegType.EXO_RENDERER_ONLY_FFMPEG_VIDEO,
                ExoFFmpegType.EXO_RENDERER_VIDEO_MEDIACODEC_AUDIO_FFMPEG,
                ExoFFmpegType.EXO_RENDERER_VIDEO_FFMPEG_AUDIO_MEDIACODEC})
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
        int EVENT_ERROR_URL = 7_001;
        int EVENT_ERROR_RETRY = 7_002;
        int EVENT_ERROR_SOURCE = 7_003;
        int EVENT_ERROR_PARSE = 7_004;
        int EVENT_ERROR_NET = 7_005;
        int EVENT_ERROR_BUFFERING_TIMEOUT = 7_006;
        int EVENT_LOADING_START = 7_007; // 开始转圈
        int EVENT_LOADING_START_IGNORE = 7_008; // 开始转圈
        int EVENT_LOADING_STOP = 7_009; // 停止转圈(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放
        // 播放结束
        //        // 开始渲染视频画面
//        int EVENT_VIDEO_SEEK_RENDERING_START = IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START;
//        // 开始渲染视频画面
//        int EVENT_AUDIO_SEEK_RENDERING_START = IMediaPlayer.MEDIA_INFO_AUDIO_SEEK_RENDERING_START;
//        // 开始渲染视频画面
//        int EVENT_AUDIO_RENDERING_START = IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START;
        // 首帧画面
        int EVENT_VIDEO_RENDERING_START = 7_010;
        // 视频开播
        int EVENT_VIDEO_START = 7_011;
        int EVENT_VIDEO_END = 7_012;
//        int EVENT_VIDEO_START_RETRY = 7_011;
//        int EVENT_VIDEO_START_SEEK = IMediaPlayer.MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE;
        //        int EVENT_VIDEO_SEEK_COMPLETE_B = IMediaPlayer.MEDIA_INFO_VIDEO_SEEK_RENDERING_START;
        //        int EVENT_VIDEO_STOP = IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;
//        int EVENT_VIDEO_END = IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;

        // 缓冲开始
//        int EVENT_OPEN_INPUT = IMediaPlayer.MEDIA_INFO_OPEN_INPUT;
        // 缓冲开始
        int EVENT_BUFFERING_START = 7_013;
        // 缓冲结束
        int EVENT_BUFFERING_STOP = 7_014;
        int EVENT_SEEK_START = 7_015;
        int EVENT_SEEK_FINISH = 7_016;
        int EVENT_SEEK_PLAY_RECORD = 7_017;
        // 视频旋转信息
//        int EVENT_VIDEO_ROTATION_CHANGED = IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED;
//        int EVENT_AUDIO_DECODED_START = IMediaPlayer.MEDIA_INFO_AUDIO_DECODED_START;
//        int EVENT_VIDEO_DECODED_START = IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({
                EVENT_ERROR_URL,
                EVENT_ERROR_RETRY,
                EVENT_ERROR_SOURCE,
                EVENT_ERROR_PARSE,
                EVENT_ERROR_NET,
                EVENT_ERROR_BUFFERING_TIMEOUT,
//                EVENT_OPEN_INPUT,
                EVENT_LOADING_START,
                EVENT_LOADING_START_IGNORE,
                EVENT_LOADING_STOP,
//                EVENT_VIDEO_SEEK_RENDERING_START,
//                EVENT_AUDIO_SEEK_RENDERING_START,
//                EVENT_AUDIO_RENDERING_START,
                EVENT_VIDEO_RENDERING_START,
                EVENT_VIDEO_START,
//                EVENT_VIDEO_START_RETRY,
//                EVENT_VIDEO_START_SEEK,
                EVENT_VIDEO_END,
//                EVENT_VIDEO_STOP,
//                EVENT_VIDEO_END,
                EVENT_BUFFERING_START,
                EVENT_BUFFERING_STOP,
                EVENT_SEEK_START,
                EVENT_SEEK_FINISH,
                EVENT_SEEK_PLAY_RECORD,
//                EVENT_VIDEO_ROTATION_CHANGED,
//                EVENT_AUDIO_DECODED_START,
//                EVENT_VIDEO_DECODED_START
        })
        @interface Value {
        }
    }

    @Documented
    @Retention(CLASS)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
    @interface SpeedType {
        int Speed_0_5 = 8_001;
        int Speed_1_0 = 8_002;
        int Speed_1_5 = 8_003;
        int Speed_2_0 = 8_004;
        int Speed_2_5 = 8_005;
        int Speed_3_0 = 8_006;
        int Speed_3_5 = 8_007;
        int Speed_4_0 = 8_008;
        int Speed_4_5 = 8_009;
        int Speed_5_0 = 8_010;
        int Speed_Default = Speed_1_0;

        @Documented
        @Retention(CLASS)
        @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE})
        @IntDef({Speed_0_5,
                Speed_1_0,
                Speed_1_5,
                Speed_2_0,
                Speed_2_5,
                Speed_3_0,
                Speed_3_5,
                Speed_4_0,
                Speed_4_5,
                Speed_5_0,
                Speed_Default})
        @interface Value {
        }
    }
}