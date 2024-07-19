package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

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
                startInitMsg(repeatCount, KeyEvent.KEYCODE_DPAD_RIGHT);
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
                startDelayedMsg(KeyEvent.KEYCODE_DPAD_RIGHT);
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception3 " + e.getMessage());
            }
        }
        // seekRewind => start
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
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
                startInitMsg(repeatCount, KeyEvent.KEYCODE_DPAD_LEFT);
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
                startDelayedMsg(KeyEvent.KEYCODE_DPAD_LEFT);
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentSeek => dispatchKeyEvent => Exception5 " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public void seekToPosition(int keyCode, int position) {
        try {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                superCallEvent(false, true, PlayerType.StateType.FAST_FORWARD_STOP);
                seekTo(position);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                superCallEvent(false, true, PlayerType.StateType.FAST_REWIND_STOP);
                seekTo(position);
            }
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => seekToPosition => " + e.getMessage());
        }
    }

    @Override
    public void callEvent(int playState) {
        switch (playState) {
            case PlayerType.StateType.FAST_FORWARD_START:
            case PlayerType.StateType.FAST_REWIND_START:
                LogUtil.log("ComponentSeek => callEventListener => show => playState = " + playState);
                show();
                break;
            case PlayerType.StateType.FAST_FORWARD_STOP:
            case PlayerType.StateType.FAST_REWIND_STOP:
                LogUtil.log("ComponentSeek => callEventListener => gone => playState = " + playState);
                hide();
                break;
            case PlayerType.StateType.INIT:
            case PlayerType.StateType.ERROR:
            case PlayerType.StateType.END:
                onUpdateProgress(false, 0, 0, 0);
                break;
            case PlayerType.StateType.SEEK_FINISH:
                try {
                    long position = getPosition();
                    long duration = getDuration();
//            LogUtil.log("ComponentSeek => show => position = " + TimeUtil.formatTimeMillis(position) + ", duration = " + TimeUtil.formatTimeMillis(duration));

                    lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
                    seekBar.setMax((int) duration);
                    seekBar.setProgress((int) position);
                    seekBar.setPlayPosition((int) position);
                } catch (Exception e) {
                }
                break;
        }
    }

    @Override
    public void callWindow(int windowState) {
        switch (windowState) {
            default:
                hide();
                break;
        }
    }

    @Override
    public void onUpdateProgress(boolean isFromUser, long max, long position, long duration) {
        LogUtil.log("ComponentSeek => onUpdateProgress => isFromUser = " + isFromUser + ", max = " + max + ", position = " + position + ", duration = " + duration);

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

            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
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

    @Override
    public void setUserTouch(boolean status) {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
            if (null == seekBar)
                throw new Exception("seekBar error: null");
            seekBar.setTag(status ? true : null);
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => setUserTouch => " + e.getMessage());
        }
    }

    @Override
    public lib.kalu.mediaplayer.widget.seek.SeekBar findSeekBar() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_seek_sb);
            if (null == seekBar)
                throw new Exception("seekBar error: null");
            return seekBar;
        } catch (Exception e) {
//            LogUtil.log("ComponentSeek => findSeekBar => " + e.getMessage());
            return null;
        }
    }

    @Override
    public int getSeekBarMax() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
            return seekBar.getMax();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getSeekBarProgress() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
            return seekBar.getProgress();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void initSeekBarChangeListener() {
        try {
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
            if (null == seekBar)
                throw new Exception("warning: null == seekBar");
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    setUserTouch(true);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    setUserTouch(false);
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
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
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
            setUserTouch(true);
            ComponentApiSeek.super.show();
            // 2
            setTag(R.id.module_mediaplayer_component_seek_sb, true);
            // 3
            long position = getPosition();
            long duration = getDuration();
            lib.kalu.mediaplayer.widget.seek.SeekBar seekBar = findSeekBar();
            seekBar.setMax((int) duration);
            seekBar.setProgress((int) position);
            seekBar.setPlayPosition((int) position);
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => show => Exception " + e.getMessage());
        }
    }

    @Override
    public void hide() {
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
            setUserTouch(false);
            ComponentApiSeek.super.hide();
            // 2
            setTag(R.id.module_mediaplayer_component_seek_sb, false);
        } catch (Exception e) {
            LogUtil.log("ComponentSeek => show => Exception " + e.getMessage());
        }
    }
}
