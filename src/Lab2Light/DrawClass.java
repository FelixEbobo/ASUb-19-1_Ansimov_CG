package Lab2Light;

import Lab2.TextureLoader;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;
import java.nio.Buffer;
import java.nio.CharBuffer;

public class DrawClass {
    protected static float dy1, dy2 = .3f;
    protected static float y1, y2 = 0;
    protected static boolean fall = false;
    protected static boolean falled = false;

    private static float fx;
    private static float fy;
    private static float fz;
    private static double fdeg;
    private static double deg;

    public static void reshape(GLAutoDrawable drawable, int x, int y, int width, int
            height) {
        var gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);

    }

    public static void display(GLAutoDrawable drawable, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        final var h = width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(Window.aView, h, 1.0, 60);

        glu.gluLookAt(Window.xView, Window.yView, 4, 0, 0, 0, 0, 4, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE);

        if (fall && y2 > -9) {
            y1 -= dy1; y2 -= dy2;
        } else if (y2 <= -9 && fall) {
            falled = true;
            fall = false;
            fdeg = deg;
            fdeg = Math.floor(deg / 90) * 90;
        }
            drawCube(gl, 3, y2, -1, .3f);
            drawCube(gl, -1, y2, 2, .3f);


        gl.glRasterPos2d(-1.5f, 1.3);
        var renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 18));
        renderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
        renderer.draw(Window.label, 24, drawable.getSurfaceHeight() - 24);
        renderer.endRendering();

        drawPlane(gl, 0, -3, 0, 0);

    }

    public static void drawPlane(GL2 gl, int x, int y, int z, float scale) {

        gl.glPushMatrix();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, Window.getChessBoardText().getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        gl.glScaled(.3, .3, .3);

        gl.glColor3f(1, 1, 1);
        gl.glNormal3d(0, 1, 0);

        gl.glTexCoord2d(5, 5);
        gl.glVertex3f(1 * 25, y, 1 * 25);

        gl.glTexCoord2d(5, 0);
        gl.glVertex3f(1 * 25, y, -1 * 25);

        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(-1 * 25, y, -1 * 25);

        gl.glTexCoord2d(0, 5);
        gl.glVertex3f(-1 * 25, y, 1 * 25);

        gl.glEnd();
        gl.glPopMatrix();
        gl.glFlush();
        gl.glDisable(GL.GL_TEXTURE_2D);
    }

    public static void drawCube(GL2 gl, float x, float y, float z, float scale) {
        //  1
        gl.glPushMatrix();
        gl.glScaled(scale, scale, scale);

        gl.glTranslated(x, y, z);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        long millis = System.currentTimeMillis();
        int cycle = 360;
        deg = millis % cycle * 360d / cycle;

        if (fall) {
            gl.glRotated(deg, 1, 1, 1);
        }
        if (falled) {
            gl.glRotated(fdeg, 0, 0, 1);
        }

        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[0].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glColor3f(1f, 1f, 1f);
            gl.glNormal3d(0, 0, 1);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(- 1,  1, - 1);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(- 1,  -1, - 1);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(1, -1, - 1);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(1, 1, - 1);

            gl.glEnd();
        }
        //  2
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[1].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glNormal3d(-1, 0, 0);
//            gl.glColor3f(1, 1, 1);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(- 1, - 1, - 1);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(- 1, 1, - 1);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(- 1,  1, - 3);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(- 1, - 1, - 3);

            gl.glEnd();
        }
        //  5
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[4].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glNormal3d(1, 0, 0);
//        gl.glColor3f(1, 1, 1);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(1, 1, - 1);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(1, - 1, - 1);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(1, - 1, - 3);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(1, 1, - 3);

            gl.glEnd();
        }
        //  6
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[5].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glNormal3d(0,0,-1);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(1, 1, - 3);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(1, - 1, - 3);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(- 1, - 1, - 3);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(- 1, 1, - 3);
            gl.glEnd();
        }
        // 3
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[2].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glColor3f(1f, 1f, 1f);
            gl.glNormal3d(0, 1, 0);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(1, 1, - 3);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(- 1, 1, - 3);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(- 1, 1, - 1);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(1, 1, - 1);

            gl.glEnd();
        }
        // 4
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[3].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

            gl.glColor3f(1f, 1f, 1f);
            gl.glNormal3d(0, -1, 0);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(1, - 1, - 1);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(- 1, - 1, - 1);

            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(- 1,- 1, - 3);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(1, - 1, - 3);

            gl.glEnd();
        }
        gl.glPopMatrix();
        gl.glFlush();
        gl.glDisable(GL.GL_TEXTURE_2D);
    }
}
