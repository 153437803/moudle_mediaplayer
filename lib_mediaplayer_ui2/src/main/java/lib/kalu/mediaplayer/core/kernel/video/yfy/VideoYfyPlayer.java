package lib.kalu.mediaplayer.core.kernel.video.yfy;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.SurfaceHolder;

import androidx.annotation.FloatRange;

import com.otvcloud.kdds.YPlayerCore;
import com.otvcloud.kdds.play.IPlayerCore;
import com.otvcloud.kdds.play.OnAuthorizeResultListener;
import com.otvcloud.kdds.play.OnVideoCompleteListener;
import com.otvcloud.kdds.play.OnVideoErrorListener;
import com.otvcloud.kdds.play.OnVideoInfoListener;

import org.json.JSONObject;

import lib.kalu.mediaplayer.config.player.PlayerType;
import lib.kalu.mediaplayer.core.kernel.video.VideoBasePlayer;
import lib.kalu.mediaplayer.core.kernel.video.VideoKernelApiEvent;
import lib.kalu.mediaplayer.core.player.video.VideoPlayerApi;
import lib.kalu.mediaplayer.util.MPLogUtil;


public final class VideoYfyPlayer extends VideoBasePlayer {

    private long mSeek = 0L; // 快进
    private long mMax = 0L; // 试播时常
    private boolean mLoop = false; // 循环播放
    private boolean mLive = false;
    private boolean mMute = false;

    private YPlayerCore mYPlayerCore = null;
    private boolean mPlayWhenReady = true;
    private boolean mPrepared = false;

    public VideoYfyPlayer(VideoPlayerApi playerApi, VideoKernelApiEvent eventApi) {
        super(playerApi, eventApi);
    }


    
    @Override
    public VideoYfyPlayer getPlayer() {
        return this;
    }

    @Override
    public void releaseDecoder(boolean isFromUser, boolean isMainThread) {
        MPLogUtil.log("VideoAndroidPlayer => releaseDecoder => mYPlayerCore = " + mYPlayerCore + ", isFromUser = " + isFromUser);
        try {
            if (null == mYPlayerCore)
                throw new Exception("mMediaPlayer error: null");
            if (isFromUser) {
                setEvent(null);
            }
            release(isMainThread);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => releaseDecoder => " + e.getMessage());
        }
    }

    @Override
    public void createDecoder( Context context,  boolean logger,  int seekParameters) {
        MPLogUtil.log("VideoAndroidPlayer => createDecoder => mYPlayerCore = " + mYPlayerCore);
        try {
            releaseDecoder(false, true);
            mYPlayerCore = new YPlayerCore();
            setVolume(1F, 1F);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => createDecoder => " + e.getMessage());
        }
    }

    @Override
    public void startDecoder( Context context,  String url,  boolean prepareAsync) {
        MPLogUtil.log("VideoAndroidPlayer => startDecoder => mYPlayerCore = " + mYPlayerCore + ", url = " + url + ", prepareAsync = " + prepareAsync);
        try {
            if (null == mYPlayerCore)
                throw new Exception("mMediaPlayer error: null");
            if (url == null || url.length() == 0)
                throw new IllegalArgumentException("url error: " + url);
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_LOADING_START);
            initListener();
            mYPlayerCore.init(context, "河南", "829735915415928832");

            //    accessToken表示对应用户。 如果未登录传"0"
//    contentId表示内容ID， 如获取不到， 传入"0"。
//    playType表示播放模式； 直播设置为"Live"； 回看设置为" Review"； 时移设置为
//     "timeshift"。
//    startTime填写播放的开始时间， 格式： yyyyMMddHHmmss； 试看和回看传递
//    endTime填写播放的结束时间， 格式： yyyyMMddHHmmss； 回看传递
//    playbillId回看的id， 回看传递
//    playbillName回看名称， 回看传递
            JSONObject object = new JSONObject();
            object.putOpt("accessToken", 0);
            object.putOpt("contentId", url);
            object.putOpt("playType", "Live");
            String string = object.toString();
            mYPlayerCore.prepare("", string);

        } catch (IllegalArgumentException e) {
            MPLogUtil.log("VideoAndroidPlayer => startDecoder => " + e.getMessage());
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_LOADING_STOP);
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_ERROR_URL);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => startDecoder => " + e.getMessage());
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_LOADING_STOP);
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_ERROR_PARSE);
        }
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
    }

    /**
     * MediaPlayer视频播放器监听listener
     */
    private void initListener() {
        try {
            if (null == mYPlayerCore)
                throw new Exception("mYPlayerCore error: null");
//            mYPlayerCore.setOnAuthorizeResultListener(mOnAuthorizeResultListener);
            mYPlayerCore.setOnErrorListener(onErrorListener);
            mYPlayerCore.setOnVideoCompleteListener(onCompletionListener);
            mYPlayerCore.setOnVideoInfoListener(onInfoListener);
//            mYPlayerCore.setOnBufferingUpdateListener(onBufferingUpdateListener);
//            mYPlayerCore.setOnPreparedListener(mOnPreparedListener);
//            mYPlayerCore.setOnSeekCompleteListener(mOnSeekCompleteListener);
//            mYPlayerCore.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => initListener => " + e.getMessage());
        }
    }

//    /**
//     * 用于播放raw和asset里面的视频文件
//     */
//    @Override
//    public void setDataSource(AssetFileDescriptor fd) {
//        try {
//            mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
//        } catch (Exception e) {
//            MPLogUtil.log("VideoAndroidPlayer => " + e.getMessage());
//        }
//    }

    @Override
    public void release(boolean isMainThread) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            mMediaPlayer.setOnErrorListener(null);
            mMediaPlayer.setOnCompletionListener(null);
            mMediaPlayer.setOnInfoListener(null);
            mMediaPlayer.setOnBufferingUpdateListener(null);
            mMediaPlayer.setOnPreparedListener(null);
            mMediaPlayer.setOnSeekCompleteListener(null);
            mMediaPlayer.setOnVideoSizeChangedListener(null);
            mMediaPlayer.setSurface(null);
            if (isMainThread) {
                mMediaPlayer.reset();
                mMediaPlayer.release();
                mMediaPlayer = null;
                mPrepared = false;
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mMediaPlayer.reset();
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                        mPrepared = false;
                    }
                }).start();
            }
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => release => " + e.getMessage());
        }
    }

    /**
     * 播放
     */
    @Override
    public void start() {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            mMediaPlayer.start();
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => start => " + e.getMessage());
        }
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        try {
            if (!mPrepared)
                throw new Exception("mPrepared warning: false");
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            mMediaPlayer.pause();
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => pause => " + e.getMessage());
        }
    }

    /**
     * 停止
     */
    @Override
    public void stop(boolean isMainThread) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            if (isMainThread) {
                mMediaPlayer.stop();
                mPrepared = false;
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mMediaPlayer.stop();
                        mPrepared = false;
                    }
                }).start();
            }
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => stop => " + e.getMessage());
        }
    }

    /**
     * 是否正在播放
     */
    @Override
    public boolean isPlaying() {
        try {
            if (!mPrepared)
                throw new Exception("mPrepared warning: false");
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            return mMediaPlayer.isPlaying();
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => isPlaying => " + e.getMessage());
            return false;
        }
    }

    /**
     * 调整进度
     */
    @Override
    public void seekTo(long seek) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            if (seek < 0)
                throw new Exception("seek error: " + seek);
            if (!mPrepared) {
                long position = getPosition();
                if (position > 0) {
                    onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_BUFFERING_START);
                }
            }

            long duration = getDuration();
            if (seek > duration) {
                MPLogUtil.log("VideoAndroidPlayer => seekTo => seek = " + seek + ", duration = " + duration);
                stop(true);
                release(true);
                onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_ERROR_SEEK_TIME);
            } else {
                MPLogUtil.log("VideoAndroidPlayer => seekTo => succ");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                mMediaPlayer.seekTo(seek, MediaPlayer.SEEK_CLOSEST);
                    mMediaPlayer.seekTo((int) seek);
                } else {
                    mMediaPlayer.seekTo((int) seek);
                }
            }
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => seekTo => " + e.getMessage());
        }
    }

    /**
     * 获取当前播放的位置
     */
    @Override
    public long getPosition() {
        try {
            if (!mPrepared)
                throw new Exception("mPrepared warning: false");
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            int currentPosition = mMediaPlayer.getCurrentPosition();
            if (currentPosition < 0)
                throw new Exception("currentPosition warning: " + currentPosition);
            return currentPosition;
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => getPosition => " + e.getMessage());
            return -1L;
        }
    }

    /**
     * 获取视频总时长
     */
    @Override
    public long getDuration() {
        try {
            if (!mPrepared)
                throw new Exception("mPrepared warning: false");
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            int duration = mMediaPlayer.getDuration();
            if (duration <= 0)
                throw new Exception("duration warning: " + duration);
            return duration;
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => getDuration => " + e.getMessage());
            return -1L;
        }
    }

    @Override
    public void setSurface( Surface surface, int w, int h) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            if (null == surface)
                throw new Exception("surface error: null");
            mMediaPlayer.setSurface(surface);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => setSurface => " + e.getMessage());
        }
    }

    @Override
    public void setPlayWhenReady(boolean playWhenReady) {
        this.mPlayWhenReady = playWhenReady;
    }

    @Override
    public boolean isPlayWhenReady() {
        return mPlayWhenReady;
    }

    @Override
    @FloatRange(from = 1F, to = 4F)
    public float getSpeed() {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                throw new Exception("only support above Android M");
            PlaybackParams playbackParams = mMediaPlayer.getPlaybackParams();
            if(null == playbackParams)
                throw new Exception("playbackParams error: null");
            float speed = playbackParams.getSpeed();
            if (speed < 1f)
                throw new Exception("speed error: " + speed);
            return speed;
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => getSpeed => " + e.getMessage());
            return 1F;
        }
    }

    @Override
    public boolean setSpeed(@FloatRange(from = 1F, to = 4F) float speed) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                throw new Exception("only support above Android M");
            PlaybackParams playbackParams = mMediaPlayer.getPlaybackParams();
            if (null != playbackParams) {
                playbackParams = new PlaybackParams();
            }
            playbackParams.setSpeed(speed);
            mMediaPlayer.setPlaybackParams(playbackParams);
            return true;
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => setSpeed => " + e.getMessage());
            return false;
        }
    }

    private OnVideoErrorListener onErrorListener = new OnVideoErrorListener() {

        @Override
        public void onError(IPlayerCore mp, int what, int extra) {
            try {
                MPLogUtil.log("VideoAndroidPlayer => onError => what = " + what);
                if (what == -38) {
                    throw new Exception("what warning: " + what);
                } else if (what == -10005) {
                    throw new Exception("what warning: " + what);
                } else {
                    onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_LOADING_STOP);
                    onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_ERROR_PARSE);
                }
            } catch (Exception e) {
                MPLogUtil.log("VideoAndroidPlayer => onError => " + e.getMessage());
            }
        }
    };

    private OnVideoCompleteListener onCompletionListener = new OnVideoCompleteListener() {
        @Override
        public void onVideoComplete(IPlayerCore iPlayerCore) {
            MPLogUtil.log("VideoAndroidPlayer => onCompletion =>");
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_VIDEO_END);
        }
    };

    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {

        }
    };

    private OnVideoInfoListener onInfoListener = new OnVideoInfoListener() {

        @Override
        public void onInfo(IPlayerCore iPlayerCore, int what, int i1) {
            MPLogUtil.log("VideoAndroidPlayer => onInfo => what = " + what);
            if (what ==3) {
                onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_LOADING_STOP);
                onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_VIDEO_START);
            } else if (what == 701) {
                onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_BUFFERING_START);
            } else if (what == 702) {
                onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_BUFFERING_STOP);
            }
        }
    };

    private MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            onEvent(PlayerType.KernelType.ANDROID, PlayerType.EventType.EVENT_BUFFERING_STOP);
            try {
                MPLogUtil.log("VideoAndroidPlayer => onSeekComplete =>");
                start();
            } catch (Exception e) {
                MPLogUtil.log("VideoAndroidPlayer => onSeekComplete => " + e.getMessage());
            }
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mPrepared = true;
            start();
        }
    };

    private MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            try {
                if (null == mp)
                    throw new Exception("mp error: null");
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();
                MPLogUtil.log("VideoAndroidPlayer => onVideoSizeChanged => videoWidth = " + videoWidth + ", videoHeight = " + videoHeight);
                if (videoWidth < 0 || videoHeight < 0)
                    throw new Exception("videoWidth error: " + videoWidth + ", videoHeight error: " + videoHeight);
                onUpdateSizeChanged(PlayerType.KernelType.ANDROID, videoWidth, videoHeight, PlayerType.RotationType.Rotation_0);
            } catch (Exception e) {
                MPLogUtil.log("VideoAndroidPlayer => onVideoSizeChanged => " + e.getMessage());
            }
        }
    };

    /****************/

    @Override
    public void setVolume(float v1, float v2) {
        try {
            if (null == mMediaPlayer)
                throw new Exception("mMediaPlayer error: null");
            float value;
            if (isMute()) {
                value = 0F;
            } else {
                value = Math.max(v1, v2);
            }
            if (value > 1f) {
                value = 1f;
            }
            mMediaPlayer.setVolume(value, value);
        } catch (Exception e) {
            MPLogUtil.log("VideoAndroidPlayer => setVolume => " + e.getMessage());
        }
    }

    @Override
    public boolean isMute() {
        return mMute;
    }

    @Override
    public void setMute(boolean v) {
        mMute = v;
        setVolume(v ? 0f : 1f, v ? 0f : 1f);
    }

    @Override
    public long getSeek() {
        return mSeek;
    }

    @Override
    public void setSeek(long seek) {
        if (seek < 0)
            return;
        mSeek = seek;
    }

    @Override
    public long getMax() {
        return mMax;
    }

    @Override
    public void setMax(long max) {
        if (max < 0)
            return;
        mMax = max;
    }

    @Override
    public boolean isLive() {
        return mLive;
    }

    @Override
    public void setLive( boolean live) {
        this.mLive = live;
    }

    @Override
    public void setLooping(boolean loop) {
        this.mLoop = loop;
    }

    @Override
    public boolean isLooping() {
        return mLoop;
    }

    @Override
    public boolean isPrepared() {
        return mPrepared;
    }
}