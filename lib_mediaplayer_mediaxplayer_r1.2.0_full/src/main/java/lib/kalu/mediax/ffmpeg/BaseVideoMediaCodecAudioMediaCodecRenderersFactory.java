package lib.kalu.mediax.ffmpeg;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.MediaCodecAudioRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

import java.util.ArrayList;;

import lib.kalu.mediax.util.ExoLogUtil;

public class BaseVideoMediaCodecAudioMediaCodecRenderersFactory extends BaseRenderersFactory {

    public BaseVideoMediaCodecAudioMediaCodecRenderersFactory(Context context) {
        super(context);
        ExoLogUtil.log("BaseRenderersFactory => BaseOnlyMediaCodecRenderersFactory =>");
    }

    @Override
    protected void addFFmpegAudioRenderers(@NonNull ArrayList<Renderer> out) {
    }

    @Override
    protected void addFFmpegVideoRenderers(long allowedJoiningTimeMs, @Nullable Handler eventHandler, @Nullable VideoRendererEventListener eventListener, int maxDroppedFramesToNotify, @NonNull ArrayList<Renderer> out) {
    }
}