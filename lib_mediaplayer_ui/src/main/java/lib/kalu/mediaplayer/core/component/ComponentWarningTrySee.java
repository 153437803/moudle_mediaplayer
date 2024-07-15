package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.args.StartArgs;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.util.LogUtil;
import lib.kalu.mediaplayer.util.TimeUtil;
import lib.kalu.mediaplayer.widget.player.PlayerView;
import lib.kalu.mediaplayer.widget.seek.SeekBar;

/**
 * 试看
 */
public class ComponentWarningTrySee extends RelativeLayout implements ComponentApiWarningTrySee {

    public ComponentWarningTrySee(Context context) {
        super(context);
        inflate();
    }

    @Override
    public int initLayoutIdComponentRoot() {
        return R.id.module_mediaplayer_component_warning_try_see_root;
    }

    @Override
    public int initLayoutIdText() {
        return R.id.module_mediaplayer_component_warning_try_see_title;
    }

    @Override
    public int initLayoutId() {
        return R.layout.module_mediaplayer_component_warning_try_see;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // keycode_enter || keycode_dpad_center
        if (event.getAction() == KeyEvent.ACTION_DOWN && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER)) {
            try {
                long trySeeDuration = getTrySeeDuration();
                if (trySeeDuration <= 0L)
                    throw new Exception("warning: trySeeDuration <= 0L");
                boolean trySeeFinish = isTrySeeFinish();
                if (trySeeFinish)
                    throw new Exception("waring: trySeeFinish true");
                toggle();
                return true;
            } catch (Exception e) {
                LogUtil.log("ComponentWarningTrySee => dispatchKeyEvent => Exception " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public void callEvent(int playState) {
        switch (playState) {
            case PlayerType.StateType.VIDEO_RENDERING_START:
                LogUtil.log("ComponentWarningTrySee => VIDEO_RENDERING_START => playState = " + playState);
                try {
                    long trySeeDuration = getTrySeeDuration();
                    if (trySeeDuration <= 0L)
                        throw new Exception("warning: trySee false");
                    boolean componentShowing = isComponentShowing();
                    if (componentShowing)
                        throw new Exception("warning: componentShowing true");
                    String mediaTitle = getTitle();
                    setComponentText(mediaTitle + " 试看开始...");
                    show();
                } catch (Exception e) {
                }
                break;
            case PlayerType.StateType.TRY_SEE_FINISH:
                LogUtil.log("ComponentWarningTrySee => TRY_SEE_FINISH => playState = " + playState);
                try {
                    long trySeeDuration = getTrySeeDuration();
                    if (trySeeDuration <= 0L)
                        throw new Exception("warning: trySee false");
                    boolean componentShowing = isComponentShowing();
                    if (!componentShowing)
                        throw new Exception("warning: componentShowing false");
                    setTrySeeFinish(true);
                    String mediaTitle = getTitle();
                    setComponentText(mediaTitle + " 试看结束...");
                } catch (Exception e) {
                }
                break;
        }
    }

    @Override
    public void onUpdateProgress(boolean isFromUser, long max, long position, long duration) {
        LogUtil.log("ComponentWarningTrySee => onUpdateProgress");

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
            SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_warning_try_see_seekbar);
            seekBar.setProgress((int) position);
            seekBar.setMax((int) (max > 0 ? max : duration));
        } catch (Exception e) {
            LogUtil.log("ComponentWarningTrySee => onUpdateProgress => Exception " + e.getMessage());
        }
    }

    @Override
    public void show() {
        ComponentApiWarningTrySee.super.show();

        try {
            long duration = getDuration();
            if (duration <= 0)
                throw new Exception("warning: duration<=0");
            long position = getPosition();
            long trySeeDuration = getTrySeeDuration();
            SeekBar seekBar = findViewById(R.id.module_mediaplayer_component_warning_try_see_seekbar);
            seekBar.setProgress((int) position);
            seekBar.setMax((int) (trySeeDuration > 0L ? trySeeDuration : duration));
        } catch (Exception e) {
        }
    }

    @Override
    public void pause() {
        ComponentApiWarningTrySee.super.pause();

        try {
            ImageView imageView = findViewById(R.id.module_mediaplayer_component_warning_try_see_state);
            imageView.setImageResource(R.drawable.module_mediaplayer_ic_pause);
        } catch (Exception e) {
        }
    }

    @Override
    public void resume() {
        ComponentApiWarningTrySee.super.resume();

        try {
            ImageView imageView = findViewById(R.id.module_mediaplayer_component_warning_try_see_state);
            imageView.setImageResource(R.drawable.module_mediaplayer_ic_resume);
        } catch (Exception e) {
        }
    }
}
