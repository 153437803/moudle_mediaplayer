package lib.kalu.mediaplayer.core.component;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.util.LogUtil;

public class ComponentMenu extends RelativeLayout implements ComponentApiMenu {
    public ComponentMenu(Context context) {
        super(context);
        inflate();

        findViewById(R.id.module_mediaplayer_component_menu_tab_episode).setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                updateTabSelected(R.id.module_mediaplayer_component_menu_tab_episode);
            }
        });
        findViewById(R.id.module_mediaplayer_component_menu_tab_speed).setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                updateTabSelected(R.id.module_mediaplayer_component_menu_tab_speed);
            }
        });
        findViewById(R.id.module_mediaplayer_component_menu_tab_scale).setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                updateTabSelected(R.id.module_mediaplayer_component_menu_tab_scale);
            }
        });

        RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
        tabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateTabCheckedChange(false);
            }
        });

        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
        int itemCount = itemGroup.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View childAt = itemGroup.getChildAt(i);
            if (null == childAt)
                continue;
            childAt.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int focusId = v.getId();
                    updateItemSelected(focusId);
                }
            });
        }
    }

    @Override
    public int initLayoutId() {
        return R.layout.module_mediaplayer_component_menu;
    }

    @Override
    public int initViewIdRoot() {
        return R.id.module_mediaplayer_component_menu_root;
    }

    @Override
    public void callEvent(int playState) {
        switch (playState) {
            case PlayerType.EventType.COMPLETE:
                hide();
                break;
        }
    }

    @Override
    public void onUpdateProgress(boolean isFromUser, long trySeeDuration, long position, long duration) {
        try {
            long timeMillis = getTimeMillis();
            if (timeMillis <= 0L)
                throw new Exception();
            long currentTimeMillis = System.currentTimeMillis();
            long cast = currentTimeMillis - timeMillis;
            if (cast <= 4000L)
                throw new Exception();
            hide();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // keycode_dpad_down
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
            try {
                boolean componentShowing = isComponentShowing();
                if (componentShowing) {
                    View focus = findFocus();
                    if (null == focus)
                        throw new Exception("warning: focus null");
                    int focusId = focus.getId();
                    if (focusId == R.id.module_mediaplayer_component_menu_tab_episode || focusId == R.id.module_mediaplayer_component_menu_tab_speed || focusId == R.id.module_mediaplayer_component_menu_tab_scale)
                        throw new Exception("warning: can't down");
                    RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
                    int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
                    tabGroup.findViewById(checkedRadioButtonId).requestFocus();
                } else {
                    show();
                    updateTabCheckedChange(true);
                    updateTimeMillis();
                }
                return true;
            } catch (Exception e) {
            }
        }
        // keycode_dpad_up
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
            try {
                boolean componentShowing = isComponentShowing();
                if (!componentShowing)
                    throw new Exception("warning: componentShowing false");
                updateTimeMillis();
                View focus = findFocus();
                int focusId = focus.getId();

                // 画面
                if (focusId == R.id.module_mediaplayer_component_menu_tab_scale) {
                    try {
                        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
                        int checkedRadioButtonId = itemGroup.getCheckedRadioButtonId();
                        itemGroup.findViewById(checkedRadioButtonId).requestFocus();
                    } catch (Exception e) {
                    }
                }
                // 倍速
                else if (focusId == R.id.module_mediaplayer_component_menu_tab_speed) {
                    try {
                        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
                        int checkedRadioButtonId = itemGroup.getCheckedRadioButtonId();
                        itemGroup.findViewById(checkedRadioButtonId).requestFocus();
                    } catch (Exception e) {
                    }
                }
                // 选集
                else if (focusId == R.id.module_mediaplayer_component_menu_tab_episode) {
                    try {
                        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
                        int itemCount = itemGroup.getChildCount();
                        for (int i = 0; i < itemCount; i++) {
                            View childAt = itemGroup.getChildAt(i);
                            if (null == childAt)
                                continue;
                            boolean selected = childAt.isSelected();
                            if (!selected)
                                continue;
                            childAt.requestFocus();
                            break;
                        }
                    } catch (Exception e) {
                    }
                }
                return true;
            } catch (Exception e) {
            }
        }
        // keycode_dpad_left
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
            try {
                boolean componentShowing = isComponentShowing();
                if (!componentShowing)
                    throw new Exception("warning: showing false");
                updateTimeMillis();
                View focus = findFocus();
                int focusId = focus.getId();

                // 选集
                if (focusId == R.id.module_mediaplayer_component_menu_tab_episode) {
                    RadioButton radioButton = findViewById(R.id.module_mediaplayer_component_menu_tab_episode);
                    return radioButton.isEnabled();
                }
                // 倍速
                else if (focusId == R.id.module_mediaplayer_component_menu_tab_speed) {
                    RadioButton radioButton = findViewById(R.id.module_mediaplayer_component_menu_tab_episode);
                    return !radioButton.isEnabled();
                }
                //
                else if (focusId == R.id.module_mediaplayer_component_menu_item_n0) {
                    RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
                    int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_episode) {
                        scrollEpisode(KeyEvent.KEYCODE_DPAD_LEFT);
                    }
                    return true;
                }
            } catch (Exception e) {
            }
        }
        // keycode_dpad_right
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            try {
                boolean showing = isComponentShowing();
                if (!showing)
                    throw new Exception("warning: showing false");
                updateTimeMillis();
                View focus = findFocus();
                int focusId = focus.getId();
                // 画面
                if (focusId == R.id.module_mediaplayer_component_menu_tab_scale) {
                    return true;
                }

                RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
                int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
                // 画面
                if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_scale) {
                    try {
                        int[] ints = getResources().getIntArray(R.array.module_mediaplayer_array_scales);
                        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
                        RadioButton radioButton = (RadioButton) itemGroup.getChildAt(ints.length - 1);
                        int buttonId = radioButton.getId();
                        if (focusId != buttonId)
                            throw new Exception();
                        return true;
                    } catch (Exception e) {
                    }
                }
                // 倍速
                else if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_speed) {
                    try {
                        int[] ints = getResources().getIntArray(R.array.module_mediaplayer_array_speeds);
                        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
                        RadioButton radioButton = (RadioButton) itemGroup.getChildAt(ints.length - 1);
                        int buttonId = radioButton.getId();
                        if (focusId != buttonId)
                            throw new Exception();
                        return true;
                    } catch (Exception e) {
                    }
                }
                // 选集
                else if (focusId == R.id.module_mediaplayer_component_menu_item_n9) {
                    scrollEpisode(KeyEvent.KEYCODE_DPAD_RIGHT);
                    return true;
                }
            } catch (Exception e) {
            }
        }
        // keycode_back
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            boolean componentShowing = isComponentShowing();
            if (componentShowing) {
                hide();
                return true;
            }
        }
        // keycode_dpad_center
        else if (event.getAction() == KeyEvent.ACTION_DOWN && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            try {
                boolean componentShowing = isComponentShowing();
                if (!componentShowing)
                    throw new Exception("warning: componentShowing false");
                updateTimeMillis();
                View focus = findFocus();
                if (null == focus)
                    throw new Exception("warning: focus null");

                int focusId = focus.getId();
                RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
                int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
                // 画面比例
                if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_scale) {
                    toggleScale(focusId);
                }
                // 倍速
                else if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_speed) {
                    toggleSpeed(focusId);
                }
                // 选集
                else {
                    toggleEpisode(focusId);
                }

                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public void scrollEpisode(int action) {

        try {
            View focus = findFocus();
            if (null == focus)
                throw new Exception("error: null == focus");
            if (!(focus instanceof RadioButton))
                throw new Exception("error: focus not RadioButton");
            Object tag = focus.getTag();
            if (null == tag)
                throw new Exception("error: null == tag");

            int index = (int) tag;
            if (action == KeyEvent.KEYCODE_DPAD_LEFT && index <= 0)
                throw new Exception("error: index <= 0");

            int episodeItemCount = getEpisodeItemCount();
            if (action == KeyEvent.KEYCODE_DPAD_RIGHT && index + 1 >= episodeItemCount)
                throw new Exception("error: index+1 >= " + episodeItemCount + ", index = " + index);

            RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
            int itemCount = itemGroup.getChildCount();

            int start;
            if (action == KeyEvent.KEYCODE_DPAD_LEFT) {
                start = index - 1;
            } else if (action == KeyEvent.KEYCODE_DPAD_RIGHT) {
                start = index - itemCount + 2;
            } else {
                start = -1;
            }
            if (start == -1)
                throw new Exception("error: start = -1");

            // 选集角标
            int loaction = getEpisodeFlagLoaction();
            int vipResourceId = getEpisodeFlagVipResourceId();
            int freeResourceId = getEpisodeFlagFreeResourceId();
            int freeItemCount = getEpisodeFreeItemCount();

            int episodePlayingIndex = getEpisodePlayingIndex();
            for (int i = 0; i < itemCount; i++) {
                RadioButton radioButton = (RadioButton) itemGroup.getChildAt(i);
                if (null == radioButton)
                    continue;
                int position = i + start;

                ViewGroup flagGroup = findViewById(R.id.module_mediaplayer_component_menu_flag_group);
                ImageView flagImage = (ImageView) flagGroup.getChildAt(i);

                if (freeItemCount < 0) {
                    flagImage.setImageResource(freeResourceId);
                } else if (position < freeItemCount) {
                    flagImage.setImageResource(freeResourceId);
                } else {
                    flagImage.setImageResource(vipResourceId);
                }

                radioButton.setTag(position);
                radioButton.setText(String.valueOf(position + 1));
                radioButton.setChecked(position == episodePlayingIndex);
            }

        } catch (Exception e) {
            LogUtil.log("ComponentMenu => scrollEpisode => " + e.getMessage());
        }
    }

    @Override
    public void hide() {
        clearTimeMillis();
        superCallEvent(false, true, PlayerType.EventType.COMPONENT_MENU_HIDE);
        ComponentApiMenu.super.hide();
    }

    @Override
    public void show() {
        superCallEvent(false, true, PlayerType.EventType.COMPONENT_MENU_SHOW);
        ComponentApiMenu.super.show();
    }

    @Override
    public void updateTabCheckedChange(boolean requestFocus) {
        // 选项
        try {
            boolean hasEpisode;
            try {
                int episodeItemCount = getEpisodeItemCount();
                if (episodeItemCount <= 0)
                    throw new Exception("warning: episodeItemCount " + episodeItemCount);
                int episodePlayingIndex = getEpisodePlayingIndex();
                if (episodePlayingIndex < 0)
                    throw new Exception("warning: episodePlayingIndex " + episodePlayingIndex);
                hasEpisode = true;
            } catch (Exception e) {
                hasEpisode = false;
            }

            RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
            int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == -1) {
                checkedRadioButtonId = hasEpisode ? R.id.module_mediaplayer_component_menu_tab_episode : R.id.module_mediaplayer_component_menu_tab_speed;
            }

            int tabCount = tabGroup.getChildCount();
            for (int i = 0; i < tabCount; i++) {
                RadioButton radioButton = (RadioButton) tabGroup.getChildAt(i);
                radioButton.setVisibility(i == 0 && !hasEpisode ? View.GONE : View.VISIBLE);
            }

            tabGroup.check(checkedRadioButtonId);

            // 焦点
            if (requestFocus) {
                findViewById(checkedRadioButtonId).requestFocus();
            }

        } catch (Exception e) {
            LogUtil.log("ComponentMenu => updateTabCheckedChange => Exception1 " + e.getMessage());
        }

        // 功能
        try {

            RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
            int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();

            RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
            int itemCount = itemGroup.getChildCount();

            // 倍速
            int[] speeds = getSpeedTypes();
            // 画面
            int[] scales = getScaleTypes();

            // 选集
            int episodePlayingIndex = getEpisodePlayingIndex();
            int episodeItemCount = getEpisodeItemCount();
            int num = episodePlayingIndex / itemCount;
            int start = num * itemCount;
            if (episodeItemCount > itemCount) {
                int length = start + itemCount;
                if (length > episodeItemCount) {
                    start -= Math.abs(length - episodeItemCount);
                }
            }

            // 选集角标
            int loaction = getEpisodeFlagLoaction();
            int vipResourceId = getEpisodeFlagVipResourceId();
            int freeResourceId = getEpisodeFlagFreeResourceId();
            int freeItemCount = getEpisodeFreeItemCount();

            for (int i = 0; i < itemCount; i++) {


                ViewGroup flagGroup = findViewById(R.id.module_mediaplayer_component_menu_flag_group);
                ImageView flagImage = (ImageView) flagGroup.getChildAt(i);
                flagImage.setImageDrawable(null);

                RadioButton radioButton = (RadioButton) itemGroup.getChildAt(i);
                // 倍速
                if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_speed) {


                    radioButton.setSelected(false);
                    radioButton.setEnabled(i < speeds.length);
                    radioButton.setVisibility(i < speeds.length ? View.VISIBLE : View.INVISIBLE);

                    if (i < speeds.length) {
                        float speed = getSpeed();
                        radioButton.setChecked(speed == speeds[i]);
                    } else {
                        radioButton.setChecked(false);
                    }
                    if (i < speeds.length) {
                        radioButton.setTag(speeds[i]);
                        if (speeds[i] == PlayerType.SpeedType._0_5) {
                            radioButton.setText("0.5");
                        } else if (speeds[i] == PlayerType.SpeedType._1_5) {
                            radioButton.setText("1.5");
                        } else if (speeds[i] == PlayerType.SpeedType._2_0) {
                            radioButton.setText("2.0");
                        } else if (speeds[i] == PlayerType.SpeedType._2_5) {
                            radioButton.setText("2.5");
                        } else if (speeds[i] == PlayerType.SpeedType._3_0) {
                            radioButton.setText("3.0");
                        } else if (speeds[i] == PlayerType.SpeedType._3_5) {
                            radioButton.setText("3.5");
                        } else if (speeds[i] == PlayerType.SpeedType._4_0) {
                            radioButton.setText("4.0");
                        } else if (speeds[i] == PlayerType.SpeedType._4_5) {
                            radioButton.setText("4.5");
                        } else if (speeds[i] == PlayerType.SpeedType._5_0) {
                            radioButton.setText("5.0");
                        } else {
                            radioButton.setText("1.0");
                        }
                    } else {
                        radioButton.setText("");
                    }
                }
                // 画面比例
                else if (checkedRadioButtonId == R.id.module_mediaplayer_component_menu_tab_scale) {
                    // radioButton.clearFlag();
                    radioButton.setSelected(false);
                    radioButton.setEnabled(i < scales.length);
                    radioButton.setVisibility(i < scales.length ? View.VISIBLE : View.INVISIBLE);

                    if (i < scales.length) {
                        int videoScaleType = getVideoScaleType();
                        radioButton.setChecked(videoScaleType == scales[i]);
                    } else {
                        radioButton.setChecked(false);
                    }

                    if (i < scales.length) {
                        radioButton.setTag(scales[i]);
                        if (scales[i] == PlayerType.ScaleType.REAL) {
                            radioButton.setText("原始");
                        } else if (scales[i] == PlayerType.ScaleType.FULL) {
                            radioButton.setText("全屏");
                        } else if (scales[i] == PlayerType.ScaleType._1_1) {
                            radioButton.setText("1:1");
                        } else if (scales[i] == PlayerType.ScaleType._4_3) {
                            radioButton.setText("4:3");
                        } else if (scales[i] == PlayerType.ScaleType._5_4) {
                            radioButton.setText("5:4");
                        } else if (scales[i] == PlayerType.ScaleType._16_9) {
                            radioButton.setText("16:9");
                        } else if (scales[i] == PlayerType.ScaleType._16_10) {
                            radioButton.setText("16:10");
                        } else {
                            radioButton.setText("自动");
                        }
                    } else {
                        radioButton.setText("");
                    }
                }
                // 选集
                else {

                    if (i < episodeItemCount) {
                        radioButton.setEnabled(true);
                        radioButton.setVisibility(View.VISIBLE);

                        int position = i + start;

                        if (freeItemCount < 0) {
                            flagImage.setImageResource(freeResourceId);
                        } else if (position < freeItemCount) {
                            flagImage.setImageResource(freeResourceId);
                        } else {
                            flagImage.setImageResource(vipResourceId);
                        }

                        radioButton.setTag(position);
                        radioButton.setText(String.valueOf(position + 1));
                        radioButton.setChecked(position == episodePlayingIndex);
                        radioButton.setSelected(position == episodePlayingIndex);
                    } else {
                        radioButton.setEnabled(false);
                        radioButton.setVisibility(View.GONE);

                        radioButton.setTag(num);
                        radioButton.setText("");
                        radioButton.setChecked(false);
                        radioButton.setSelected(false);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.log("ComponentMenu => updateTabCheckedChange => Exception2 " + e.getMessage());
        }
    }

    @Override
    public void toggleScale(int focusId) {

        // 选中状态
        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
        int itemCount = itemGroup.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            RadioButton radioButton = (RadioButton) itemGroup.getChildAt(i);
            radioButton.setChecked(false);
        }
        RadioButton radioButton = (RadioButton) itemGroup.findViewById(focusId);
        radioButton.setChecked(true);

        try {
            int tag = (int) radioButton.getTag();
            setVideoScaleType(tag);
            setSpeed(tag);
        } catch (Exception e) {
        }
    }

    @Override
    public void toggleSpeed(int focusId) {

        // 选中状态
        RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
        int itemCount = itemGroup.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            RadioButton radioButton = (RadioButton) itemGroup.getChildAt(i);
            radioButton.setChecked(false);
        }
        RadioButton radioButton = (RadioButton) itemGroup.findViewById(focusId);
        radioButton.setChecked(true);

        try {
            int tag = (int) radioButton.getTag();
            setSpeed(tag);
        } catch (Exception e) {
        }
    }

    @Override
    public void toggleEpisode(int focusId) {
        try {

            RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
            RadioButton radioButton = itemGroup.findViewById(focusId);
            Object tag = radioButton.getTag();
            if (null == tag)
                throw new Exception("error: tag null");
            int index = (int) tag;
            int episodePlayingIndex = getEpisodePlayingIndex();
            if (index == episodePlayingIndex)
                throw new Exception("warning: index == episodePlayingIndex");

            int itemCount = itemGroup.getChildCount();
            for (int i = 0; i < itemCount; i++) {
                RadioButton childAt = (RadioButton) itemGroup.getChildAt(i);
                if (null == childAt)
                    continue;

                Object tag1 = childAt.getTag();
                if (null == tag1)
                    continue;
                int position = (int) tag1;
                childAt.setSelected(position == index);
                childAt.setChecked(position == index);
            }

            // 2
            hide();
            stop();
            clickEpisode(index);
        } catch (Exception e) {
            LogUtil.log("ComponentMenu => clickEpisode => Exception " + e.getMessage());
        }
    }

    @Override
    public void updateTabSelected(int viewId) {
        updateTimeMillis();
        RadioGroup radioGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
        radioGroup.check(viewId);
    }

    @Override
    public void updateItemSelected(int viewId) {
        try {
            RadioGroup tabGroup = findViewById(R.id.module_mediaplayer_component_menu_tab_group);
            int checkedRadioButtonId = tabGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId != R.id.module_mediaplayer_component_menu_tab_episode)
                throw new Exception();
            RadioGroup itemGroup = findViewById(R.id.module_mediaplayer_component_menu_item_group);
            int itemCount = itemGroup.getChildCount();
            for (int i = 0; i < itemCount; i++) {
                View childAt = itemGroup.getChildAt(i);
                if (null == childAt)
                    continue;
                int focusId = childAt.getId();
                childAt.setSelected(focusId == viewId);
            }
        } catch (Exception e) {
        }
    }
}
