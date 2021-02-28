package Lab1;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.JFrame;

public class OneTriangleAWT {

    public static void main( String [] args ) {
        GLProfile glprofile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );

        glcanvas.addGLEventListener( new GLEventListener() {

            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                OneTriangle.reshape( glautodrawable, x, y, width, height );
            }

            @Override
            public void init( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                OneTriangle.display( glautodrawable);
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
