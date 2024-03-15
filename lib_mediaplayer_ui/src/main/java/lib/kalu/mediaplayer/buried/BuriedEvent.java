package lib.kalu.mediaplayer.buried;


public interface BuriedEvent {

    void onPlaying(String url, long position, long duration);

    void onExit(String url, long position, long duration);

    void onCompletion(String url, long position, long duration);

    void onError(String url, long position, long duration);

    void onPause(String url, long position, long duration);

    void onResume(String url, long position, long duration);

    void onSeek(String url, long position, long duration);

    void onWindowFull(String url, long position, long duration);

    void onWindowFloat(String url, long position, long duration);

    void onWindowSimple(String url, long position, long duration);
}