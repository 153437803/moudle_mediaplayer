# kalu
-dontwarn lib.kalu.media3.**
-keep class lib.kalu.media3.ffmpeg.*{
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.media3.util.MediaLogUtil{
    public <methods>;
}

# rtmp client
-keep class lib.kalu.media3.rtmp.RtmpClient {
    *;
}

# guave
-dontwarn com.google.**
-keep class com.google.common.**
-keep class com.google.thirdparty.publicsuffix.**
-keep class com.google.errorprone.annotations.**

# midi
-dontwarn com.jsyn.**
-dontwarn com.softsynth.**
-keep class com.jsyn.**
-keep class com.softsynth.**

# exoplayer
-keep class androidx.media3.exoplayer.** {
    public <fields>;
    public <methods>;
}
-dontnote androidx.media3.decoder.vp9.LibvpxVideoRenderer
-keepclassmembers class androidx.media3.decoder.vp9.LibvpxVideoRenderer {
  <init>(long, android.os.Handler, androidx.media3.exoplayer.video.VideoRendererEventListener, int);
}
-dontnote androidx.media3.decoder.av1.Libgav1VideoRenderer
-keepclassmembers class androidx.media3.decoder.av1.Libgav1VideoRenderer {
  <init>(long, android.os.Handler, androidx.media3.exoplayer.video.VideoRendererEventListener, int);
}
-dontnote androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer
-keepclassmembers class androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer {
  <init>(long, android.os.Handler, androidx.media3.exoplayer.video.VideoRendererEventListener, int);
}
-dontnote androidx.media3.decoder.opus.LibopusAudioRenderer
-keepclassmembers class androidx.media3.decoder.opus.LibopusAudioRenderer {
  <init>(android.os.Handler, androidx.media3.exoplayer.audio.AudioRendererEventListener, androidx.media3.exoplayer.audio.AudioSink);
}
-dontnote androidx.media3.decoder.flac.LibflacAudioRenderer
-keepclassmembers class androidx.media3.decoder.flac.LibflacAudioRenderer {
  <init>(android.os.Handler, androidx.media3.exoplayer.audio.AudioRendererEventListener, androidx.media3.exoplayer.audio.AudioSink);
}
-dontnote androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer
-keepclassmembers class androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer {
  <init>(android.os.Handler, androidx.media3.exoplayer.audio.AudioRendererEventListener, androidx.media3.exoplayer.audio.AudioSink);
}
-dontnote androidx.media3.decoder.midi.MidiRenderer
-keepclassmembers class androidx.media3.decoder.midi.MidiRenderer {
  <init>(android.content.Context);
}
-dontnote androidx.media3.exoplayer.dash.offline.DashDownloader
-keepclassmembers class androidx.media3.exoplayer.dash.offline.DashDownloader {
  <init>(androidx.media3.common.MediaItem, androidx.media3.datasource.cache.CacheDataSource$Factory, java.util.concurrent.Executor);
}
-dontnote androidx.media3.exoplayer.hls.offline.HlsDownloader
-keepclassmembers class androidx.media3.exoplayer.hls.offline.HlsDownloader {
  <init>(androidx.media3.common.MediaItem, androidx.media3.datasource.cache.CacheDataSource$Factory, java.util.concurrent.Executor);
}
-dontnote androidx.media3.exoplayer.smoothstreaming.offline.SsDownloader
-keepclassmembers class androidx.media3.exoplayer.smoothstreaming.offline.SsDownloader {
  <init>(androidx.media3.common.MediaItem, androidx.media3.datasource.cache.CacheDataSource$Factory, java.util.concurrent.Executor);
}
-dontnote androidx.media3.exoplayer.dash.DashMediaSource$Factory
-keepclasseswithmembers class androidx.media3.exoplayer.dash.DashMediaSource$Factory {
  <init>(androidx.media3.datasource.DataSource$Factory);
}
-dontnote androidx.media3.exoplayer.hls.HlsMediaSource$Factory
-keepclasseswithmembers class androidx.media3.exoplayer.hls.HlsMediaSource$Factory {
  <init>(androidx.media3.datasource.DataSource$Factory);
}
-dontnote androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory
-keepclasseswithmembers class androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory {
  <init>(androidx.media3.datasource.DataSource$Factory);
}
-dontnote androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory
-keepclasseswithmembers class androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory {
  <init>();
}
-dontnote androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory
-keepclasseswithmembers class androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory {
  <init>(androidx.media3.common.VideoFrameProcessor$Factory);
}
-dontnote androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder
-keepclasseswithmembers class androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder {
  androidx.media3.effect.DefaultVideoFrameProcessor$Factory build();
}

# extractor
-dontnote androidx.media3.decoder.flac.FlacExtractor
-keepclassmembers class androidx.media3.decoder.flac.FlacExtractor {
  <init>(int);
}
-dontnote androidx.media3.decoder.flac.FlacLibrary
-keepclassmembers class androidx.media3.decoder.flac.FlacLibrary {
  public static boolean isAvailable();
}
-dontnote androidx.media3.decoder.midi.MidiExtractor
-keepclassmembers class androidx.media3.decoder.midi.MidiExtractor {
  <init>();
}
-dontwarn org.checkerframework.**
-dontwarn kotlin.annotations.jvm.**
-dontwarn javax.annotation.**

# common
-dontwarn org.checkerframework.**
-dontwarn kotlin.annotations.jvm.**
-dontwarn javax.annotation.**
-dontwarn java.lang.ClassValue
-dontwarn java.lang.SafeVarargs
-dontwarn javax.lang.model.element.Modifier
-dontwarn sun.misc.Unsafe
-dontwarn com.google.errorprone.annotations.**
-dontwarn com.google.j2objc.annotations.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keepclassmembernames class com.google.common.base.Function { *; }
-keep class androidx.media3.common.** {
    public <fields>;
    public <methods>;
}

# datasource
-keep class androidx.media3.datasource.** {
    public <fields>;
    public <methods>;
}

# datasource rtmp
-keepclassmembers class androidx.media3.datasource.RawResourceDataSource {
  public static android.net.Uri buildRawResourceUri(int);
}
-dontnote androidx.media3.datasource.rtmp.RtmpDataSource
-keepclassmembers class androidx.media3.datasource.rtmp.RtmpDataSource {
  <init>();
}

# datasource okhttp
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**

# decoder av1
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class androidx.media3.decoder.VideoDecoderOutputBuffer {
  *;
}

# decoder ffmpeg
-keep class androidx.media3.decoder.ffmpeg.FfmpegLibrary {
    *;
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep, includedescriptorclasses class androidx.media3.decoder.ffmpeg.FfmpegAudioDecoder {
  private java.nio.ByteBuffer growOutputBuffer(androidx.media3.decoder.SimpleDecoderOutputBuffer, int);
}

# decoder flac
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class androidx.media3.decoder.flac.FlacDecoderJni {
    *;
}
-keep class androidx.media3.extractor.FlacStreamMetadata {
    *;
}
-keep class androidx.media3.extractor.metadata.flac.PictureFrame {
    *;
}

# decoder opus
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class androidx.media3.decoder.SimpleDecoderOutputBuffer {
    *;
}

# decoder vp9
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class androidx.media3.decoder.VideoDecoderOutputBuffer {
    *;
}

#-keep class xx.xx.xx.*   本包下的类名保持
#-keep class xx.xx.xx.**  把本包和所含子包下的类名都保持
#-keep class xx.xx.xx.**{*;} 把本包和所含子包下的类名都保持，同时保持里面的内容不被混淆
#-keep class xx.xx.xx{*;} 保持类名，同时保持里面的内容不被混淆
