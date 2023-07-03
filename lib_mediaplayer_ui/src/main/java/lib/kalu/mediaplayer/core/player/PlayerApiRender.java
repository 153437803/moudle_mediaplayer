package lib.kalu.mediaplayer.core.player;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.config.player.PlayerBuilder;
import lib.kalu.mediaplayer.config.player.PlayerManager;
import lib.kalu.mediaplayer.config.player.PlayerType;
import lib.kalu.mediaplayer.core.render.RenderApi;
import lib.kalu.mediaplayer.core.render.RenderFactoryManager;
import lib.kalu.mediaplayer.util.MPLogUtil;

interface PlayerApiRender extends PlayerApiBase {

    default void clearSuface() {
        try {
            RenderApi render = getRender();
            render.clearCanvas();
        } catch (Exception e) {
        }
    }

    default void updateSuface() {
        try {
            RenderApi render = getRender();
            render.updateCanvas();
        } catch (Exception e) {
        }
    }

    default String screenshot() {
        try {
            RenderApi render = getRender();
            return render.screenshot();
        } catch (Exception e) {
            return null;
        }
    }

    default void requestFocusFull() {
        try {
            ViewGroup decorView = findDecorView((View) this);
            if (null == decorView)
                throw new Exception("decorView error: null");
            View focus = decorView.findFocus();
            if (null == focus)
                throw new Exception("focus error: null");
            ((View) this).setTag(R.id.module_mediaplayer_window_id, focus);
            ((View) ((View) this).getParent()).setFocusable(true);
            ((View) this).setFocusable(true);
            ((View) this).requestFocus();
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => requestFocusFull => " + e.getMessage());
        }
    }

    default void cleanFocusFull() {
        try {
            Object tag = ((View) this).getTag(R.id.module_mediaplayer_window_id);
            if (null == tag)
                throw new Exception("tag error: null");
            ((View) this).setTag(R.id.module_mediaplayer_window_id, null);
            ((View) ((View) this).getParent()).setFocusable(false);
            ((View) this).setFocusable(false);
            ((View) tag).requestFocus();
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => cleanFocusFull => " + e.getMessage());
        }
    }

    default boolean resetRender() {
        try {
            PlayerBuilder config = PlayerManager.getInstance().getConfig();
            if (null == config)
                throw new Exception("config warning: null");
            int render = config.getRender();
            if (render != PlayerType.RenderType.SURFACE_VIEW)
                throw new Exception("render warning: not SURFACE_VIEW");
            int kernel = config.getKernel();
            if (kernel != PlayerType.KernelType.IJK_MEDIACODEC && kernel != PlayerType.KernelType.EXO_V1 && kernel != PlayerType.KernelType.EXO_V2)
                throw new Exception("kernel waring: not ijk_mediacodec or exo_v1 or exo_v2");
            if (!(this instanceof PlayerApiKernel))
                throw new Exception("render warning: not instanceof PlayerApiKernel");
            createRender(true);
            ((PlayerApiKernel) this).attachRender();
            updateRenderBuffer(kernel == PlayerType.KernelType.IJK_MEDIACODEC ? 4000 : 400);
            return true;
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => resetRender => " + e.getMessage());
            return false;
        }
    }

    default boolean startFull() {
        try {
            boolean isPhoneWindow = isParentEqualsPhoneWindow();
            if (isPhoneWindow)
                throw new Exception("always full");
            requestFocusFull();
            boolean b = switchToDecorView(true);
            if (b) {
                resetRender();
                callWindowEvent(PlayerType.WindowType.FULL);
            }
            return b;
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => startFull => " + e.getMessage());
            return false;
        }
    }

    default boolean stopFull() {
        try {
            boolean isFull = isFull();
            if (!isFull)
                throw new Exception("not full");
            boolean b = switchToPlayerLayout();
            if (b) {
                resetRender();
                callWindowEvent(PlayerType.WindowType.NORMAL);
                cleanFocusFull();
            }
            return b;
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => stopFull => " + e.getMessage());
            return false;
        }
    }

    default boolean startFloat() {
        try {
            boolean isPhoneWindow = isParentEqualsPhoneWindow();
            if (isPhoneWindow)
                throw new Exception("always Float");
            boolean switchToDecorView = switchToDecorView(false);
            if (switchToDecorView) {
                resetRender();
                callWindowEvent(PlayerType.WindowType.FLOAT);
            }
            return switchToDecorView;
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => startFloat => " + e.getMessage());
            return false;
        }
    }

    default boolean stopFloat() {
        try {
            boolean isFloat = isFloat();
            if (!isFloat)
                throw new Exception("not Float");
            boolean switchToPlayerLayout = switchToPlayerLayout();
            if (switchToPlayerLayout) {
                resetRender();
                callWindowEvent(PlayerType.WindowType.NORMAL);
            }
            return switchToPlayerLayout;
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => stopFloat => " + e.getMessage());
            return false;
        }
    }

    default void setScaleType(@PlayerType.ScaleType.Value int scaleType) {
        try {
            // 1
            RenderApi render = getRender();
            render.setScaleType(scaleType);
            // 2
            PlayerManager.getInstance().getConfig().newBuilder().setScaleType(scaleType).build();
        } catch (Exception e) {
        }
    }

    default void setVideoSize(int width, int height) {
        try {
            RenderApi render = getRender();
            render.setVideoSize(width, height);
        } catch (Exception e) {
        }
    }

    default void setVideoRotation(int rotation) {
        try {
            if (rotation == -1)
                return;
            RenderApi render = getRender();
            render.setVideoRotation(rotation);
        } catch (Exception e) {
        }
    }

    /**
     * 设置镜像旋转，暂不支持SurfaceView
     *
     * @param enable
     */
    default void setMirrorRotation(boolean enable) {
        try {
            RenderApi render = getRender();
            render.setScaleX(enable ? -1 : 1);
        } catch (Exception e) {
        }
    }

    default void showReal() {
        try {
            ViewGroup viewGroup = getBaseVideoViewGroup();
            viewGroup.setVisibility(View.VISIBLE);
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
    }

    default void hideReal() {
        try {
            ViewGroup viewGroup = getBaseVideoViewGroup();
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                child.setVisibility(View.INVISIBLE);
            }
            viewGroup.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
        }
    }

    default RenderApi searchRender() {
        try {
            ViewGroup group = getBaseVideoViewGroup();
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = group.getChildAt(i);
                if (null == view)
                    continue;
                if (!(view instanceof RenderApi))
                    continue;
                return (RenderApi) view;
            }
            throw new Exception("not find");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => searchRender => " + e.getMessage());
            return null;
        }
    }

    default void setRender(@PlayerType.RenderType int v) {
        try {
            PlayerManager.getInstance().setRender(v);
        } catch (Exception e) {
        }
    }

    default void createRender(boolean reset) {
        try {
            // 1
            releaseRender(reset);
            // 2
            int type = PlayerManager.getInstance().getConfig().getRender();
            setRender(RenderFactoryManager.createRender(getBaseContext(), type));
            // 3
            addRender();
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => createRender => " + e.getMessage());
        }
    }

    default void releaseRender(boolean reset) {
        try {
            // 1
            releaseRenderListener();
            // 2
            if (reset)
                throw new Exception("reset warning: true");
            removeRender();
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => releaseRender => " + e.getMessage());
        }
    }

    default void updateRenderBuffer(int delayMillis) {
        try {
            RenderApi render = searchRender();
            if (null == render)
                throw new Exception("render warning: null");
            render.updateBuffer(delayMillis);
            MPLogUtil.log("PlayerApiRender => updateRenderBuffer => succ");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => updateRenderBuffer => " + e.getMessage());
        }
    }

    default void addRenderListener() {
        try {
            // 1
            RenderApi render = searchRender();
            if (null == render)
                throw new Exception("render warning: null");
            render.addListener();
            MPLogUtil.log("PlayerApiRender => addRenderListener => succ");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => addRenderListener => " + e.getMessage());
        }
    }

    default void releaseRenderListener() {
        try {
            // 1
            RenderApi render = searchRender();
            if (null == render)
                throw new Exception("render warning: null");
            render.releaseListener();
            MPLogUtil.log("PlayerApiRender => releaseRenderListener => succ");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => releaseRenderListener => " + e.getMessage());
        }
    }

    default void addRender() {
        try {
            RenderApi render = getRender();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            render.setLayoutParams(params);
            ViewGroup viewGroup = getBaseVideoViewGroup();
            viewGroup.addView((View) render, 0);
            MPLogUtil.log("PlayerApiRender => addRender => succ");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => addRender => " + e.getMessage());
        }
    }

    default void removeRender() {
        try {
            ViewGroup group = getBaseVideoViewGroup();
            if (null == group)
                throw new Exception("group error: null");
            int childCount = group.getChildCount();
            if (childCount <= 0)
                throw new Exception("childCount warning: " + childCount);
            group.removeAllViews();
            MPLogUtil.log("PlayerApiRender => removeRender => succ");
        } catch (Exception e) {
            MPLogUtil.log("PlayerApiRender => removeRender => " + e.getMessage());
        }
    }

    RenderApi getRender();

    void setRender(@NonNull RenderApi render);

    void checkReal();
}
