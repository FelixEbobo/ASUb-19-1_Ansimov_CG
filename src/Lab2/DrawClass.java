package Lab2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

public class DrawClass {
    public static void reshape(GLAutoDrawable drawable, int x, int y, int width, int
            height) {
        var gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(90.0f, h, 1.0, 20.0);
        glu.gluLookAt(0, 0, 4, 0.4f, 0.2f, 0, 0, 1, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        drawCube(gl, 1, 0, 1, .3f);
        drawCube(gl, -2, 0, 3, .3f);

    }

    public static void drawCube(GL2 gl, int x, int y, int z, float scale) {
        //  1
        gl.glPushMatrix();
        gl.glScaled(scale, scale, scale);
        gl.glTranslated(x, y, z);

        long millis = System.currentTimeMillis();
        int cycle = 3600;
        double deg = millis % cycle * 360d / cycle;

        gl.glRotated(deg, 1, 1, 1);
        gl.glEnable(GL.GL_TEXTURE_2D);
        {
            gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.textureSet[0].getTextureObject());
            gl.glBegin(GL2.GL_QUADS);

//        gl.glColor3f(1f, 1f, 1f);
            gl.glNormal3d(0, 0, -1);

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

            gl.glNormal3d(0, 0, 1);
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

            gl.glNormal3d(0,0,1);

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
            gl.glNormal3d(0, 0, 0);

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
            gl.glNormal3d(0, 0, 0);

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
