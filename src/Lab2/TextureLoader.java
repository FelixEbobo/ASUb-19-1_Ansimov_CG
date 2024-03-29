package Lab2;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;

public class TextureLoader {

    public static Texture[] textureSet = {null, null, null, null, null, null};
    private static String path = System.getProperty("user.dir") + "/Media/";

    public static Texture load(GL2 gl, String filename) {
        Texture texture = null;
        try {
            texture = TextureIO.newTexture(new File(path + filename), false);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);

        } catch (Exception e) {
            System.out.printf("%s \n %s%n", e.toString(), filename);
        }
        return texture;
    }

    public static void loadCube(GL2 gl) {
        for (int i = 1; i < 7; i++) {
            textureSet[i - 1] = TextureLoader.load(gl,"Cube" + i + ".png");
        }
    }
}
