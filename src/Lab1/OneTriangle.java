package Lab1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;

public class OneTriangle {
    public static void reshape(GLAutoDrawable drawable, int x, int y, int width, int
            height) {
        var gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        glu.gluLookAt(0, 0, 4, 0.4f, 0.2f, 0, 0, 1, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glRotatef(3f, 1, 1, 0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glBegin(GL2.GL_POLYGON);

            // Green
            gl.glColor3f(0f, 1f, 0.0f);
            gl.glVertex3f(0f, 0f, -1f);

            // Key Color
            gl.glColor3f(0f, 0.0f, 0.0f);
            gl.glVertex3f(1f, 0f, -1f);

            // Red
            gl.glColor3f(1f, 0f, 0f);
            gl.glVertex3f(1f, 1f, -1f);

            // Yellow
            gl.glColor3f(1f, 1f, 0f);
            gl.glVertex3f(0f, 1f, -1f);

            // White
            gl.glColor3f(1f, 1f, 1f);
            gl.glVertex3f(0f, 1f, 0f);

            // Cyan
            gl.glColor3f(0f, 1f, 1f);
            gl.glVertex3f(0f, 0f, 0f);

            // Blue
            gl.glColor3f(0f, 0f, 1f);
            gl.glVertex3f(1f, 0f, 0f);

            // Key Color
            gl.glColor3f(0f, 0.0f, 0.0f);
            gl.glVertex3f(1f, 0f, -1f);

        gl.glEnd();
        gl.glBegin(GL2.GL_POLYGON);

            // Magenta
            gl.glColor3f(1f, 0f, 1f);
            gl.glVertex3f(1f, 1f, 0f);

            // White
            gl.glColor3f(1f, 1f, 1f);
            gl.glVertex3f(0f, 1f, 0f);

            // Cyan
            gl.glColor3f(0f, 1f, 1f);
            gl.glVertex3f(0f, 0f, 0f);

            // Blue
            gl.glColor3f(0f, 0f, 1f);
            gl.glVertex3f(1f, 0f, 0f);

            // Key Color
            gl.glColor3f(0f, 0.0f, 0.0f);
            gl.glVertex3f(1f, 0f, -1f);

            // Red
            gl.glColor3f(1f, 0f, 0f);
            gl.glVertex3f(1f, 1f, -1f);

            // Yellow
            gl.glColor3f(1f, 1f, 0f);
            gl.glVertex3f(0f, 1f, -1f);

            // White
            gl.glColor3f(1f, 1f, 1f);
            gl.glVertex3f(0f, 1f, 0f);

        gl.glEnd();
    }
}
