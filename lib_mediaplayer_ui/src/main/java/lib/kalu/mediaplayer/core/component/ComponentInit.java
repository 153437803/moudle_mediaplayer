
package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.util.LogUtil;

public class ComponentInit extends RelativeLayout implements ComponentApi {

    public ComponentInit( Context context) {
        super(context);
        inflate();
    }

    @Override
    public int initLayoutId() {
        return R.layout.module_mediaplayer_component_init;
    }

    @Override
    public int initLayoutIdComponentRoot() {
        return R.id.module_mediaplayer_component_init_root;
    }

    @Override
    public void callEvent(int playState) {
        switch (playState) {
            case PlayerType.StateType.STATE_INIT:
                LogUtil.log("ComponentInit[show] => playState = " + playState);
                show();
                break;
            default:
                LogUtil.log("ComponentInit[gone] => playState = " + playState);
                hide();
                break;
        }
    }

    @Override
    public int initLayoutIdComponentBackground() {
        return R.id.module_mediaplayer_component_init_bg;
    }
}