package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.util.LogUtil;


public class ComponentSeek extends RelativeLayout implements ComponentApiSeek {

    public ComponentSeek(Context context) {
        super(context);
        inflate();
    }

    @Override
    public int initViewIdRoot() {
        return R.id.module_mediaplayer_component_seek_root;
    }

    @Override
    public int initLayoutId() {
        return R.layout.module_mediaplayer_component_seek;
    }

    @Override
    public void onUpdateProgress(boolean isFromUser, long max, long position, long duration) {
//        LogUtil.log("ComponentSeek => onUpdateProgress => isFromUser = " + isFromUser + ", max = " + max + ", position = " + position + ", duration = " + duration);

        // 进度条
        try {
            boolean componentShowing = isComponentShowing();
            if (!componentShowing)
                throw new Exception("warning: componentShowing false");
            if (position < 0) {
                position = 0;
            }
            if (duration < 0) {
                duration = 0;
            }

            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
            // 拖动进度条
            if (isFromUser) {
                long playPosition = getPosition();
                seekBar.setPlayPosition(playPosition);
                seekBar.setProgress((int) position);
            } else {
                long playPosition = seekBar.getPlayPosition();
                if (playPosition <= 0) {
                    seekBar.setProgress((int) position);
                }
            }
            seekBar.setMax((int) (max > 0 ? max : duration));
        } catch (Exception e) {
        }
    }

//    @Override
//    public int getSeekBarMax() {
//        try {
//            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
//            return seekBar.getMax();
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//
//    @Override
//    public int getSeekBarProgress() {
//        try {
//            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
//            return seekBar.getProgress();
//        } catch (Exception e) {
//            return 0;
//        }
//    }

    @Override
    public void initSeekBarChangeListener() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
            if (null == seekBar)
                throw new Exception("warning: null == seekBar");
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    seekToStopTrackingTouch();
                }
            });
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => initSeekBarChangeListener => " + e.getMessage());
        }
    }

    @Override
    public void seekToStopTrackingTouch() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
            if (null == seekBar)
                throw new Exception("warning: null == seekBar");
            long max = seekBar.getMax();
            long playPosition = seekBar.getPlayPosition();
            seekBar.clearPlayPosition();
            if (playPosition > max) {
                playPosition = max;
            } else if (playPosition <= 0) {
                playPosition = 0;
            }
            seekTo(playPosition);
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => seekToStopTrackingTouch => " + e.getMessage());
        }
    }

    @Override
    public void show() {
        try {
            boolean bufferingShowing = isComponentShowing(ComponentApiBuffering.class);
            if (bufferingShowing)
                throw new Exception("warning: ComponentApiBuffering true");
            boolean warningPlayInfoShowing = isComponentShowing(ComponentApiWarningPlayInfo.class);
            if (warningPlayInfoShowing)
                throw new Exception("warning: ComponentApiWarningPlayInfo true");
            boolean menuShowing = isComponentShowing(ComponentApiMenu.class);
            if (menuShowing)
                throw new Exception("warning: ComponentApiMenu true");
            // 1
            ComponentApiSeek.super.show();
            // 2
            long position = getPosition();
            long duration = getDuration();
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
            seekBar.setMax((int) duration);
            seekBar.setProgress((int) position);
            seekBar.setPlayPosition((int) position);
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => show => Exception " + e.getMessage());
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        // 试看
        try {
            long trySeeDuration = getTrySeeDuration();
            if (trySeeDuration > 0L)
                throw new Exception("warning: trySee true");
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception1 " + e.getMessage());
            return false;
        }

        // seekForward => start
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            try {
                boolean bufferingShowing = isComponentShowing(ComponentApiBuffering.class);
                if (bufferingShowing)
                    throw new Exception("warning: ComponentApiBuffering true");
                boolean warningPlayInfoShowing = isComponentShowing(ComponentApiWarningPlayInfo.class);
                if (warningPlayInfoShowing)
                    throw new Exception("warning: ComponentApiWarningPlayInfo true");
                boolean menuShowing = isComponentShowing(ComponentApiMenu.class);
                if (menuShowing)
                    throw new Exception("warning: ComponentApiMenu true");
                int repeatCount = event.getRepeatCount();
                actionDown(repeatCount, KeyEvent.KEYCODE_DPAD_RIGHT);
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception2 " + e.getMessage());
            }
        }
        // seekForward => stop
        else if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            try {
                boolean menuShowing = isComponentShowing(ComponentApiMenu.class);
                if (menuShowing)
                    throw new Exception("warning: ComponentApiMenu true");
                actionUp();
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception3 " + e.getMessage());
            }
        }
        // seekRewind => start
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
            try {
                boolean bufferingShowing = isComponentShowing(ComponentApiBuffering.class);
                LogUtil.log("ComponentSeek => dispatchKeyEvent => KEYCODE_DPAD_LEFT => bufferingShowing = " + bufferingShowing);
                if (bufferingShowing)
                    throw new Exception("warning: ComponentApiBuffering true");
                boolean warningPlayInfoShowing = isComponentShowing(ComponentApiWarningPlayInfo.class);
                LogUtil.log("ComponentSeek => dispatchKeyEvent => KEYCODE_DPAD_LEFT => warningPlayInfoShowing = " + warningPlayInfoShowing);
                if (warningPlayInfoShowing)
                    throw new Exception("warning: ComponentApiWarningPlayInfo true");
                boolean menuShowing = isComponentShowing(ComponentApiMenu.class);
                LogUtil.log("ComponentSeek => dispatchKeyEvent => KEYCODE_DPAD_LEFT => menuShowing = " + menuShowing);
                if (menuShowing)
                    throw new Exception("warning: ComponentApiMenu true");
                int repeatCount = event.getRepeatCount();
                actionDown(repeatCount, KeyEvent.KEYCODE_DPAD_LEFT);
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception4 " + e.getMessage());
            }
        }
        // seekRewind => stop
        else if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
            try {
                boolean menuShowing = isComponentShowing(ComponentApiMenu.class);
                if (menuShowing)
                    throw new Exception("warning: ComponentApiMenu true");
                actionUp();
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception5 " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public void callEvent(int playState) {
        switch (playState) {
            case PlayerType.EventType.SEEK_START_FORWARD:
                LogUtil.log("ComponentSeek => callEvent => SEEK_START_FORWARD");
                show();
                break;
            case PlayerType.EventType.SEEK_START_REWIND:
                LogUtil.log("ComponentSeek => callEvent => SEEK_START_REWIND");
                show();
                break;
            case PlayerType.EventType.SEEK_FINISH:
                LogUtil.log("ComponentSeek => callEvent => SEEK_FINISH");
                hide();
                break;
            case PlayerType.EventType.INIT:
            case PlayerType.EventType.ERROR:
            case PlayerType.EventType.COMPLETE:
                onUpdateProgress(false, 0, 0, 0);
                break;
        }
    }

    @Override
    public void hide() {
        try {
            boolean componentShowing = isComponentShowing();
            if (!componentShowing)
                throw new Exception("warning: componentShowing false");
            ComponentApiSeek.super.hide();
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => hide => Exception " + e.getMessage());
        }
    }

    /**********/

    private Handler mHandlerSeekUp = null;

    @Override
    public void actionDown(int repeatCount, int keyCode) {

        if (null != mHandlerSeekUp) {
            mHandlerSeekUp.removeCallbacksAndMessages(null);
            mHandlerSeekUp = null;
        }

        try {
            long duration = getDuration();
            if (duration <= 0)
                throw new Exception("warning: duration <=0");
            // click
            if (repeatCount == 0) {
                lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
                int progress = seekBar.getProgress();
                if (progress >= duration)
                    throw new Exception("warning: range >= duration");
                // >=2H 2 * 60 * 60 * 1000
                if (duration >= 7200000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 8000 : -8000);
                }
                // >=1H 60*60*1000
                else if (duration >= 3600000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 4000 : -4000);
                }
                // >=30MIN 30*60*1000
                else if (duration >= 1800000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 1000 : -1000);
                }
                // 10MIN 10*60*1000
                else if (duration >= 600000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 400 : -400);
                }
                // 时间太短了
                else {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 100 : -100);
                }
                if (progress >= duration) {
                    progress = (int) duration;
                }

                show();
                long trySeeDuration = getTrySeeDuration();
                onUpdateProgress(true, trySeeDuration, progress, duration);
            }
            // long click
            else {
                lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
                int progress = seekBar.getProgress();
                if (progress >= duration)
                    throw new Exception("warning: progress > duration");
                // >=2H 2 * 60 * 60 * 1000
                if (duration >= 7200000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 60000 : -60000);
                }
                // >=1H 60*60*1000
                else if (duration >= 3600000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 30000 : -30000);
                }
                // >=30MIN 30*60*1000
                else if (duration >= 1800000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 10000 : -10000);
                }
                // 10MIN 10*60*1000
                else if (duration >= 600000) {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 5000 : -5000);
                }
                // 时间太短了
                else {
                    progress += (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ? 1000 : -1000);
                }
                if (progress >= duration) {
                    progress = (int) duration;
                }
                long trySeeDuration = getTrySeeDuration();
                onUpdateProgress(true, trySeeDuration, progress, duration);
            }
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => startInitMsg => Exception " + e.getMessage());
        }
    }

    @Override
    public void actionUp() {
        try {
            if (null == mHandlerSeekUp) {
                mHandlerSeekUp = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if (msg.what == 20001) {
                            hide();
                            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
                            int progress = seekBar.getProgress();
                            seekTo(progress);
                        }
                    }
                };
            }
            mHandlerSeekUp.sendEmptyMessageDelayed(20001, 1000);
        } catch (Exception e) {
        }
    }
}
