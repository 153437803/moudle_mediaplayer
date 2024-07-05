package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.util.LogUtil;
import lib.kalu.mediaplayer.util.TimeUtil;

/**
 * 续播,提示
 */
public class ComponentWarningPlayRecord extends RelativeLayout implements ComponentApi {
    public ComponentWarningPlayRecord(Context context) {
        super(context);
        inflate();
        setEnabled(false);
    }

    @Override
    public int initLayoutId() {
        return R.layout.module_mediaplayer_component_warning_play_record;
    }

    @Override
    public void callEventListener(int playState) {

        switch (playState) {
            // 续播快进
            case PlayerType.StateType.STATE_SEEK_PLAY_RECORD:
                LogUtil.log("ComponentWarningPlayRecord => playState = " + playState);
                try {
                    long seek = getPlayerView().getSeek();
                    if (seek <= 0)
                        throw new Exception();
                    setTag(seek);
                } catch (Exception e) {
                    setTag(null);
                }
                break;
            // 续播开播
            case PlayerType.StateType.STATE_START:
                LogUtil.log("ComponentWarningPlayRecord => playState = " + playState);
                try {
                    Object tag = getTag();
                    if (null == tag)
                        throw new Exception();
                    show();
                } catch (Exception e) {
                    hide();
                }
                break;
        }
    }

    @Override
    public void show() {
        ComponentApi.super.show();

        try {
            Object tag = getTag();
            if (null == tag)
                throw new Exception();
            long seek = (long) tag;
            String optString = TimeUtil.formatTimeMillis(seek);
            String string = getResources().getString(R.string.module_mediaplayer_string_play_record, optString);
            setComponentText(string);
        } catch (Exception e) {
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        }, 2000);
    }

    @Override
    public void hide() {
        ComponentApi.super.hide();

        try {
            ViewGroup viewGroup = (ViewGroup) getParent();
            viewGroup.removeView(this);
        } catch (Exception e) {
        }
    }

    @Override
    public int initLayoutIdText() {
        return R.id.module_mediaplayer_component_warning_play_record_root;
    }

    @Override
    public int initLayoutIdComponentRoot() {
        return R.id.module_mediaplayer_component_warning_play_record_root;
    }
}
