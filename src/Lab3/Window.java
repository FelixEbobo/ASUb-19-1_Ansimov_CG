package Lab3;

import Lab2.TextureLoader;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.SplittableRandom;

public class Window extends JFrame implements KeyListener{

    protected static float xView, yView;
    protected static float zView;
    public static float aView = 45f;
    protected static String label = String.format("x: %f y: %f z: %f aView: %f", xView, yView, zView, aView);
    private static Texture chessBoardText;
    protected static FPSAnimator animator;

    private static Texture grassText;

    Window() {
        super("Frame CG");
        GLProfile.initSingleton();
        GLProfile.initProfiles(GLProfile.getDefaultDevice());
        var glprofile = GLProfile.getDefault();
        System.out.println(glprofile.getName() + " " + glprofile.isGL4bc());
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        GLCanvas glcanvas = new GLCanvas( glcapabilities );


        glcanvas.addGLEventListener( new GLEventListener() {

            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {

            }

            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
//                gl.glEnable(GL2.GL_CULL_FACE);
                gl.glEnable(GL2.GL_COLOR_MATERIAL);
                gl.glEnable(GL2.GL_NORMALIZE);
                gl.glEnable(GL2.GL_LIGHTING);
                gl.glEnable(GL2.GL_LIGHT0);
                gl.glLightiv(GL2.GL_LIGHT0, GL2.GL_POSITION, new int[] { -1, 1, -1, 0 },0);
                gl.glEnable(GL2.GL_FOG);
                gl.glFogfv(GL2.GL_FOG_COLOR, new float[] { .9f, .9f, 1, 0 }, 0);
                gl.glFogf(GL2.GL_FOG_DENSITY, .05f);
                gl.glClearColor(.9f, .9f, 1, 0);

                grassText = TextureLoader.load(gl, "Grass.png");
            }

            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
                gl.glEnable(GL.GL_TEXTURE_2D);
                gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
                DrawClass.display(glautodrawable, glcanvas.getWidth(), glcanvas.getHeight());

            }


        });


        this.add( glcanvas );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 640, 480);
        this.setVisible( true );
        this.addKeyListener(this);
        animator = new FPSAnimator(glcanvas, 50, true);
        animator.start();
    }

    public static void main( String [] args ) {
        new Window();
    }


    public static Texture getChessBoardText() {
        return chessBoardText;
    }

    public static Texture getGrassText() { return grassText;}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            zView += 1;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            zView -= 1;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xView += 1;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xView -= 1;
        else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            yView += 2;
        } else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            yView -= 2;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            xView = 0;
            yView = 0;
            zView = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("You achieved comedy");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
