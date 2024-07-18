package lib.kalu.mediaplayer.core.render.glsurface;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

final class GLRender implements GLSurfaceView.Renderer {
    private final List<GLDrawer> drawers = new ArrayList<GLDrawer>();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f, 0f, 0f, 0f);
        //开启混合，即半透明
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        int[] textureIds = GLTool.getInstance().createTextureIds(drawers.size());
        for (int i = 0; i < textureIds.length; i++) {
            drawers.get(i).setTextureID(textureIds[i]);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        for (GLDrawer drawer : drawers) {
            drawer.setWorldSize(width, height);
        }

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓冲和深度缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        for (int i = 0; i < drawers.size(); i++) {
            drawers.get(i).draw();
        }
    }

    /**
     * 添加渲染器
     *
     * @param drawer
     */
    public void addDrawer(GLDrawer drawer) {
        drawers.add(drawer);
    }
}
