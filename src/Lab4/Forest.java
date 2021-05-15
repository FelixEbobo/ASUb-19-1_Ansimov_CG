package Lab4;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Forest {

    public Texture texture;

    public float[] x;
    public float[] y;
    public float[] z;

    public float width;
    public float height;

    public Forest() {
        width = 15;
        height = 33;
    }

    public void setForest(float[] x, float[] y, float[] z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void drawForest(GL2 gl) {
        gl.glColor3d(1, 1, 1);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        for (int i = 0; i < x.length; i++) {
            gl.glPushMatrix();
            gl.glTranslatef(x[i], y[i], z[i]);
            gl.glRotated(Window.yAngle, 0, 1, 0);
            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0.0f, 0.0f);    gl.glVertex2f(width, height);
            gl.glTexCoord2f(0.0f, 1.0f);    gl.glVertex2f(width, 0);
            gl.glTexCoord2f(1.0f, 1.0f);    gl.glVertex2f(-width, 0);
            gl.glTexCoord2f(1.0f, 0.0f);    gl.glVertex2f(-width, height);
            gl.glEnd();
            gl.glPopMatrix();
        }
    }
}
