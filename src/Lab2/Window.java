package Lab2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import org.w3c.dom.Text;

import javax.swing.*;

public class Window {

    public static void main( String [] args ) {
        GLProfile glprofile = GLProfile.getDefault();
//        GLProfile glprofile = GLProfile.get(GLProfile.GL4);
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        GLCanvas glcanvas = new GLCanvas( glcapabilities );

        glcanvas.addGLEventListener( new GLEventListener() {

            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                DrawClass.reshape( glautodrawable, x, y, width, height );
            }

            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
                gl.glEnable(GL.GL_CULL_FACE);
                TextureLoader.loadCube();
            }

            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
                gl.glEnable(GL.GL_TEXTURE_2D);
                gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
                DrawClass.display( glautodrawable);
            }
        });


        var frame = new JFrame("Basic Frame");
        frame.add( glcanvas );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 640, 480);
        frame.setVisible( true );
        final FPSAnimator animator = new FPSAnimator(glcanvas, 60,true);
        animator.start();

    }
}
