package lib.kalu.mediaplayer.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import org.json.JSONArray;

import java.util.LinkedList;

import lib.kalu.mediaplayer.R;
import lib.kalu.mediaplayer.core.component.ComponentMenu;
import lib.kalu.mediaplayer.type.PlayerType;
import lib.kalu.mediaplayer.args.StartArgs;
import lib.kalu.mediaplayer.core.component.ComponentApi;
import lib.kalu.mediaplayer.core.component.ComponentComplete;
import lib.kalu.mediaplayer.core.component.ComponentError;
import lib.kalu.mediaplayer.core.component.ComponentInit;
import lib.kalu.mediaplayer.core.component.ComponentLoading;
import lib.kalu.mediaplayer.core.component.ComponentBuffering;
import lib.kalu.mediaplayer.core.component.ComponentPause;
import lib.kalu.mediaplayer.core.component.ComponentSeek;
import lib.kalu.mediaplayer.core.component.ComponentTry;
import lib.kalu.mediaplayer.listener.OnPlayerEventListener;
import lib.kalu.mediaplayer.listener.OnPlayerProgressListener;
import lib.kalu.mediaplayer.listener.OnPlayerWindowListener;
import lib.kalu.mediaplayer.util.LogUtil;
import lib.kalu.mediaplayer.widget.player.PlayerLayout;

/**
 * @description: 横屏全屏视频播放器
 * @date: 2021-05-25 10:37
 */
public final class TestActivity extends Activity {


    public static final int RESULT_CODE = 31001;

    public static final String INTENT_LIVE = "intent_live"; // live

    public static final String INTENT_MAX = "intent_max"; // max

    public static final String INTENT_SEEK = "intent_seek"; // 快进

    public static final String INTENT_DATA = "intent_data"; // 外部传入DATA

    public static final String INTENT_URL = "intent_url"; // 视频Url

    public static final String INTENT_SRT = "intent_srt"; // 字幕Url

    public static final String INTENT_TIME_BROWSING = "intent_time_browsing"; // 视频浏览时长

    public static final String INTENT_TIME_LENGTH = "intent_time_length"; // 视频总时长

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_mediaplayer_test);

        initComponent();
        initPlayer();
        startPlayer();

        // 音轨信息
        findViewById(R.id.module_mediaplayer_test_button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                JSONArray trackInfo = playerLayout.getTrackInfo();
                LogUtil.log("trackInfo = " + trackInfo);
            }
        });
        // 音轨信息2
        findViewById(R.id.module_mediaplayer_test_button22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                playerLayout.switchTrack(1);
            }
        });
        // 音轨信息3
        findViewById(R.id.module_mediaplayer_test_button23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                playerLayout.switchTrack(2);
            }
        });
        // 倍速
        findViewById(R.id.module_mediaplayer_test_button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                float speed = playerLayout.getSpeed();
                Toast.makeText(getApplicationContext(), "speed = " + speed, Toast.LENGTH_SHORT).show();
                if (speed > 1F) {
                    playerLayout.setSpeed(1f);
                } else {
                    playerLayout.setSpeed(1.5f);
                }
            }
        });
        // 信息
        findViewById(R.id.module_mediaplayer_test_button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
//                VideoIjkPlayer videoIjkPlayer = playerLayout.getKernel();
//                IjkTrackInfo[] trackInfo = videoIjkPlayer.getTrackInfo();
//                String s = new Gson().toJson(trackInfo);
//                MPLogUtil.log("TestActivity => onClick => "+s);
            }
        });
        // 跳转
        findViewById(R.id.module_mediaplayer_test_button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                intent.putExtra(TestActivity.INTENT_URL, getUrl());
                startActivity(intent);
            }
        });
        // 换台
        findViewById(R.id.module_mediaplayer_test_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlayer();
            }
        });
        // 全屏
        findViewById(R.id.module_mediaplayer_test_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFull();
            }
        });
        // 浮动
        findViewById(R.id.module_mediaplayer_test_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFloat();
            }
        });
        findViewById(R.id.module_mediaplayer_test_button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                playerLayout.showComponent(ComponentSeek.class);
            }
        });
        findViewById(R.id.module_mediaplayer_test_button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
                playerLayout.hideComponent(ComponentSeek.class);
            }
        });
    }

    private void initComponent() {
        LinkedList<ComponentApi> componentApis = new LinkedList<>();
        // menu
        ComponentMenu menu = new ComponentMenu(getApplicationContext());
        componentApis.add(menu);
        // loading
        ComponentLoading loading = new ComponentLoading(getApplicationContext());
        loading.setComponentText(R.string.module_mediaplayer_string_load);
        componentApis.add(loading);
        // seek
        ComponentSeek seek = new ComponentSeek(getApplicationContext());
        seek.initSeekBarChangeListener();
        componentApis.add(seek);
        // complete
        ComponentComplete end = new ComponentComplete(getApplicationContext());
        componentApis.add(end);
        // error
        ComponentError error = new ComponentError(getApplicationContext());
        componentApis.add(error);
        // net
        ComponentBuffering speed = new ComponentBuffering(getApplicationContext());
        componentApis.add(speed);
        // init
        ComponentInit init = new ComponentInit(getApplicationContext());
        componentApis.add(init);
        // pause
        ComponentPause pause = new ComponentPause(getApplicationContext());
        componentApis.add(pause);
        // try
        ComponentTry trys = new ComponentTry(getApplicationContext());
        componentApis.add(trys);
        // insert-component
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.addAllComponent(componentApis);
    }

    private void initPlayer() {
        // playerLayout
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.setOnPlayerWindowListener(new OnPlayerWindowListener() {
            @Override
            public void onWindow(int state) {
                switch (state) {
                    case PlayerType.WindowType.NORMAL:
                        //普通模式
                        break;
                    case PlayerType.WindowType.FULL:
                        //全屏模式
                        break;
                    case PlayerType.WindowType.FLOAT:
                        //小屏模式
                        break;
                }
            }
        });
        playerLayout.setOnPlayerProgressListener(new OnPlayerProgressListener() {
            @Override
            public void onProgress(long position, long duration) {
            }
        });
        playerLayout.setOnPlayerEventListener(new OnPlayerEventListener() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onRestart() {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onResume() {

            }

            @Override
            public void onEvent(int state) {
                LogUtil.log("onPlayStateChanged => state = " + state);

                switch (state) {
                    case PlayerType.StateType.STATE_INIT:
                        //播放未开始，初始化
                        break;
                    case PlayerType.StateType.STATE_START_ABORT:
                        //开始播放中止
                        break;
                    case PlayerType.StateType.STATE_LOADING_START:
                        //播放准备中
                        break;
                    case PlayerType.StateType.STATE_LOADING_STOP:
                        //播放准备就绪
                        break;
                    case PlayerType.StateType.STATE_ERROR:
                        //播放错误
                        break;
                    case PlayerType.StateType.STATE_BUFFERING_START:
                        //正在缓冲
                        break;
                    case PlayerType.StateType.STATE_START:
                        //正在播放
                        break;
                    case PlayerType.StateType.STATE_PAUSE:
                        //暂停播放
                        break;
                    case PlayerType.StateType.STATE_BUFFERING_STOP:
                        //暂停缓冲
                        break;
                    case PlayerType.StateType.STATE_END:
                        //播放完成
                        break;
                }
            }
        });
    }

    private String getUrl() {
        return getIntent().getStringExtra(INTENT_URL);
    }

    private void startPlayer() {

        String url = getUrl();
        if (null == url || url.length() == 0) {
            onBackPressed();
            return;
        }

        long seek = getIntent().getLongExtra(INTENT_SEEK, 0);
        if (seek <= 0) {
            seek = getIntent().getIntExtra(INTENT_SEEK, 0);
        }
        long max = getIntent().getLongExtra(INTENT_MAX, 0);
        if (max <= 0) {
            max = getIntent().getIntExtra(INTENT_MAX, 0);
        }
        boolean live = getIntent().getBooleanExtra(INTENT_LIVE, false);
        LogUtil.log("TestActivity => onCreate => seek = " + seek + ", max = " + max + ", live = " + live + ", url = " + url);

        StartArgs.Builder builder = new StartArgs.Builder();
        builder.setSeek(seek);
        builder.setMax(max);
        builder.setLive(live);
        PlayerLayout videoLayout = findViewById(R.id.module_mediaplayer_test_video);
        videoLayout.start(builder.build(), url);
    }

    private void startFull() {
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.startFull();
    }

    private void stopFull() {
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.stopFull();
    }

    private void startFloat() {
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.startFloat();
    }

    private void stopFloat() {
        PlayerLayout playerLayout = findViewById(R.id.module_mediaplayer_test_video);
        playerLayout.stopFloat();
    }

    @Override
    public void finish() {

        PlayerLayout videoLayout = findViewById(R.id.module_mediaplayer_test_video);
        long browsing = videoLayout.getPosition() / 1000;
        if (browsing < 0) {
            browsing = 0;
        }
        long duration = videoLayout.getDuration() / 1000;
        if (duration < 0) {
            duration = 0;
        }
        String extra = getIntent().getStringExtra(INTENT_DATA);
        Intent intent = new Intent();
        intent.putExtra(INTENT_DATA, extra);
        intent.putExtra(INTENT_TIME_LENGTH, duration);
        intent.putExtra(INTENT_TIME_BROWSING, browsing);
        setResult(RESULT_CODE, intent);
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerLayout videoLayout = findViewById(R.id.module_mediaplayer_test_video);
        videoLayout.resume(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PlayerLayout videoLayout = findViewById(R.id.module_mediaplayer_test_video);
        videoLayout.pause(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayerLayout videoLayout = findViewById(R.id.module_mediaplayer_test_video);
        videoLayout.release(true);
    }
}