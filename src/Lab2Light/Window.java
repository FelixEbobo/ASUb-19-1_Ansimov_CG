package Lab2Light;

import Lab2.TextureLoader;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements KeyListener {

    protected static float xView, yView;
    protected static float zView;
    public static float aView = 45f;
    protected static String label = String.format("x: %f y: %f z: %f aView: %f", xView, yView, zView, aView);
    private static Texture chessBoardText;
    protected static FPSAnimator animator;

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
                DrawClass.reshape( glautodrawable, x, y, width, height );
            }

            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
                gl.glEnable(GL2.GL_CULL_FACE);
                gl.glEnable(GL2.GL_COLOR_MATERIAL);
                gl.glEnable(GL2.GL_NORMALIZE);
                gl.glEnable(GL2.GL_LIGHTING);
                gl.glEnable(GL2.GL_LIGHT0);
                gl.glLightiv(GL2.GL_LIGHT0, GL2.GL_POSITION, new int[] { -1, 1, -1, 0 },0);
                gl.glEnable(GL2.GL_FOG);
                gl.glFogfv(GL2.GL_FOG_COLOR, new float[] { 0, 0, 0, 1 }, 0);
                gl.glFogf(GL2.GL_FOG_DENSITY, .1f);
                gl.glClearColor(.53f,.81f,.92f,0);

                chessBoardText = TextureLoader.load(gl,"ChessBoard.png");
                TextureLoader.loadCube(gl);
            }

            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                var gl = glautodrawable.getGL().getGL2();
                gl.glEnable(GL.GL_TEXTURE_2D);
                gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
                DrawClass.display( glautodrawable, glcanvas.getWidth(), glcanvas.getHeight());
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            yView += .5;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            yView -= .5;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xView -= .5;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xView += .5;
        else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            aView -= 2;
        } else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            aView += 2;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            aView = 45f;
            xView = 0;
            yView = 0;
            DrawClass.y1 = 0;
            DrawClass.y2 = 0;
            DrawClass.falled = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            DrawClass.y1 = 0;
            DrawClass.y2 = 0;
            DrawClass.falled = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("You achieved comedy");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            DrawClass.fall = !DrawClass.fall;
            System.out.println(DrawClass.fall);
        }
        label = String.format("x: %f y: %f z: %f aView: %f", xView, yView, zView, aView);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static Texture getChessBoardText() {
        return chessBoardText;
    }
}
