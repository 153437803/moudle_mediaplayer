package lib.kalu.mediaplayer.core.kernel.video.ijk;




import lib.kalu.mediaplayer.core.kernel.video.VideoKernelApiEvent;
import lib.kalu.mediaplayer.core.kernel.video.VideoKernelFactory;
import lib.kalu.mediaplayer.core.player.video.VideoPlayerApi;



public class VideoIjkPlayerFactory implements VideoKernelFactory<VideoIjkPlayer> {

    private boolean mUseMediaCodec;

    private VideoIjkPlayerFactory(boolean useMediaCodec) {
        mUseMediaCodec = useMediaCodec;
    }

    public static VideoIjkPlayerFactory build(boolean useMediaCodec) {
        return new VideoIjkPlayerFactory(useMediaCodec);
    }

    @Override
    public VideoIjkPlayer createKernel() {
        return new VideoIjkPlayer(mUseMediaCodec);
    }
}
