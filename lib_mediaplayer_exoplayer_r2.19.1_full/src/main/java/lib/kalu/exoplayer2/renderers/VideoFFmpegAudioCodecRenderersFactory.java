package lib.kalu.exoplayer2.renderers;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.ArrayList;

import lib.kalu.exoplayer2.util.ExoLogUtil;

public class VideoFFmpegAudioCodecRenderersFactory extends BaseRenderersFactory {

    public VideoFFmpegAudioCodecRenderersFactory(Context context) {
        super(context);
        ExoLogUtil.log("BaseRenderersFactory => BaseVideoFFmpegAudioMediaCodecRenderersFactory =>");
    }

    protected int initRendererMode() {
        return DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON;
    }

    @Override
    protected void addAudioFFmpegRenderers(@NonNull ArrayList<Renderer> out) {
    }

    @Override
    protected void addVideoCodecRenderers(@NonNull Context context, @ExtensionRendererMode int extensionRendererMode, @NonNull MediaCodecSelector mediaCodecSelector, @NonNull boolean enableDecoderFallback, @NonNull Handler eventHandler, @NonNull VideoRendererEventListener eventListener, @NonNull long allowedVideoJoiningTimeMs, @NonNull ArrayList<Renderer> out) {
    }
}