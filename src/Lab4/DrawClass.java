package Lab4;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

import static Lab4.Window.*;

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
        glu.gluPerspective(Window.aView, h, 1.0, 60);

//        glu.gluLookAt(0, Window.yView, 3, 0, 0, 0, 0, 1, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, Window.getGrassText().getTextureObject());
        int zV = (int) ((Window.zView-(Window.zView%105))/105);
        int xV = (int) ((Window.xView-(Window.xView%105))/105);

        drawLab3(gl, 0, 2 * mapSize,
                 0, - mapSize * zV);

//        drawLab3(gl,  - mapSize * xV, mapSize - mapSize * xV, - mapSize - mapSize * zV, - mapSize * zV);

//        drawLab3(gl, - mapSize - mapSize * xV,  - mapSize * xV, - mapSize - mapSize * zV, - mapSize * zV);

//        drawLab3(gl, mapSize - mapSize * xV, 2 * mapSize - mapSize * xV, - mapSize * zV, mapSize - mapSize * zV);

//        drawLab3(gl,  - mapSize * xV, mapSize - mapSize * xV, - mapSize * zV, mapSize - mapSize * zV);

//        drawLab3(gl, - mapSize - mapSize * xV,  - mapSize * xV, - mapSize * zV, mapSize - mapSize * zV);

//        drawLab3(gl, mapSize - mapSize * xV, 2 * mapSize - mapSize * xV, mapSize - mapSize * zV, 2 * mapSize - mapSize * zV);

//        drawLab3(gl,  - mapSize * xV, mapSize - mapSize * xV, mapSize - mapSize * zV, 2 * mapSize - mapSize * zV);

//        drawLab3(gl, - mapSize - mapSize * xV,  - mapSize * xV, mapSize - mapSize * zV, 2 * mapSize - mapSize * zV);

        controller.controll();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glRotated(-Window.xAngle, 1, 0, 0);
        gl.glRotated(-Window.yAngle, 0, 1, 0);
        gl.glRotated(-Window.zAngle, 0, 0, 1);

        gl.glDisable(GL.GL_DEPTH_TEST);
        Window.skybox.draw(gl);
        gl.glEnable(GL.GL_DEPTH_TEST);

        gl.glTranslated(-xView, -yView, -zView);

        forest.drawForest(gl);

        helicopter.draw(gl);
        helicopter.physImpact();
        helicopter.accel.z = (float) 0;

        gl.glFlush();

    }

    public static void drawLab3(GL2 gl, int initialPoint1, int breakingPoint1, int initialPoint2, int breakingPoint2) {
        gl.glBegin(GL2.GL_TRIANGLES);
        for (var x = initialPoint1; x < breakingPoint1; x+=stepSize)
            for (var z = initialPoint2; z < breakingPoint2; z += stepSize)
            {
                var y1 = -getHeight(x,z)/10;
                var y2 = -getHeight(x+stepSize,z)/10;
                var y3 = -getHeight(x+stepSize,z+stepSize)/10;
                var y4 = -getHeight(x,z+stepSize)/10;
                var x1 = 100*x/mapSize-50;
                var x2 = 100*(x+16)/mapSize-50;
                var z1 = 100*z/mapSize-50;
                var z2 = 100*(z+16)/mapSize-50;
                gl.glTexCoord2d(0, 0); gl.glVertex3d(x1 + xView, y1, z1 + zView);
                gl.glTexCoord2d(1, 0); gl.glVertex3d(x2 + xView, y2, z1 + zView);
                gl.glTexCoord2d(1, 1); gl.glVertex3d(x2 + xView, y3, z2 + zView);
                gl.glTexCoord2d(1, 1); gl.glVertex3d(x2 + xView, y3, z2 + zView);
                gl.glTexCoord2d(0, 1); gl.glVertex3d(x1 + xView, y4, z2 + zView);
                gl.glTexCoord2d(0, 0); gl.glVertex3d(x1 + xView, y1, z1 + zView);
            }
        gl.glEnd();
    }

    static double getHeight(int x, int z) {
        int mapX = Math.abs(x) % mapSize;
        int mapZ = Math.abs(z) % mapSize;
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
