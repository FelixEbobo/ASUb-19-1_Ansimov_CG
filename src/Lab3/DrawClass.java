package Lab3;

import Lab2.TextureLoader;
import Lab3.Window;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

public class DrawClass {
    private static int mapSize = 256;
    private static int stepSize = 1;
    private static byte[] pHeightMap = TerrainLoader.load("Terraria.raw", mapSize);

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
        glu.gluPerspective(Lab3.Window.aView, h, 1.0, 60);

        glu.gluLookAt(Lab3.Window.xView, 0, 4, Window.CameraX, Lab3.Window.yView, Window.CameraZ, 0, 4, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, Window.getGrassText().getTextureObject());
        drawLab3(gl);


    }

    public static void drawLab3(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (var x = 0; x < mapSize; x+=stepSize)
            for (var z = 0; z < mapSize; z+=stepSize)
            {
                var y1 = -getHeight(x,z)/10;
                var y2 = -getHeight(x+stepSize,z)/10;
                var y3 = -getHeight(x+stepSize,z+stepSize)/10;
                var y4 = -getHeight(x,z+stepSize)/10;
                var x1 = 100*x/mapSize-50;

                var x2 = 100*(x+16)/mapSize-50;
                var z1 = 100*z/mapSize-50;
                var z2 = 100*(z+16)/mapSize-50;
                gl.glTexCoord2d(0, 0); gl.glVertex3d(x1, y1, z1);
                gl.glTexCoord2d(1, 0); gl.glVertex3d(x2, y2, z1);
                gl.glTexCoord2d(1, 1); gl.glVertex3d(x2, y3, z2);
                gl.glTexCoord2d(1, 1); gl.glVertex3d(x2, y3, z2);
                gl.glTexCoord2d(0, 1); gl.glVertex3d(x1, y4, z2);
                gl.glTexCoord2d(0, 0); gl.glVertex3d(x1, y1, z1);
            }
        gl.glEnd();
    }

    static double getHeight(int x, int z) {
        int mapX = x % mapSize;
        int mapZ = z % mapSize;
        return (pHeightMap[mapX + (mapZ * mapSize)] & 0xFF);
    }

    public static void drawPlane(GL2 gl, int x, int y, int z, float scale) {

        gl.glPushMatrix();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, Window.getGrassText().getTextureObject());
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
}
