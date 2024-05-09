package lib.kalu.mediaplayer.util.m3u8.inter;


/**
 * desc  : 加载监听器
 */
public interface DownLoadListener {
    /**
     * 开始的时候回调
     */
    void onStart();

    /**
     * 错误的时候回调
     *
     * @param errorMsg
     */
    void onError(Throwable errorMsg);

    /**
     * 下载完成的时候回调
     */
    void onCompleted();
}