
package Lab4;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;


public class Helicopter {

    // ������ ��������
    private final Object body;
    // ������ �������� �����
    private final Object mainScrew;
    // ������ �������� �����
    private final Object rollScrew;

    private static final double toRadian = Math.PI / 180;
    private static final double toDegree = 180 / Math.PI;
    
    // FPS
    public final int FPS = 60;
    
    public Vector3 position;
    public Vector3 angles;

    // ���������� ��������� ���������
 
    // �����
    public final float mass = 11000; // 11 000 ��
    // ������������ �������� ������������ ��������
    public final float maxSpeedY = 20; // 20 �/�
    
    // ��������
    public Vector3 speed;
    // ���������
    public Vector3 accel;

    public Vector3 speedAngle;
    // ������� ���������
    public Vector3 accelAngle;
    
    // ����
    public static float pull = (float) 0;
    // �������� �������� ������������ ��� ��������
    public static float pullAngle = (float) 0.3;

    public Helicopter(GL2 gl) {
        body = new Object("Helicopter.obj");
        mainScrew = new Object("MainScrew.obj");
        // ���� �������� �����
        mainScrew.position.x = (float) 0.05961;
        mainScrew.position.y = (float) 3.58183;
        mainScrew.position.z = (float) -0.07208;
        mainScrew.angles.x = (float) -3;

        rollScrew = new Object("RollScrew.obj");
        // ���� �������� �����
        rollScrew.position.x = (float) -0.51373;
        rollScrew.position.y = (float) 3.06563;
        rollScrew.position.z = (float) -10.9899;

        Texture texture = Lab2.TextureLoader.load(gl, "texture.jpg");
        body.setTexture(texture);
        mainScrew.setTexture(texture);
        rollScrew.setTexture(texture);

        position = new Vector3(0, 0, 0);
        angles = new Vector3(0, 0, 0);

        speed = new Vector3(0, 0, 0);
        accel = new Vector3(0, 0, 0);

        speedAngle = new Vector3(0, 0, 0);
        accelAngle = new Vector3(0, 0, 0);
    }
    
    public void physImpact() {
        /* ��������� �� ����������� */
        position.add(speed);
        speed.add(accel);
        // 0.1633 = 9.8 �/�2 / 60 (FPS)
        speed.y -= 9.8 / FPS * 0.21;
        // �������������
        speed.mult(0.95);
        // ��������� �� ����
        angles.add(speedAngle);
        speedAngle.add(accelAngle);
        speedAngle.mult(0.7);

        mainScrew.angles.y += 8 * Math.PI ;//5 ------ 8*Pi
        mainScrew.angles.y += (speed.y > 0) ? 3 : -3; //3

        rollScrew.angles.x += 40 * Math.PI;//10 ------ 40*Pi
        rollScrew.angles.x += (speedAngle.y > 0) ? 3 : -3; //3
        rollScrew.angles.x += (speed.y > 0) ? 2 : -2; //2
    }

    public void collision(float height) {
        if (height > position.y - 0.3656) {
            position.y += height - position.y + 0.3656;
            speed.y = 0;
        }
    }

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(position.x, position.y, position.z);
        gl.glRotated(angles.y, 0, 1, 0);
        gl.glRotated(angles.x, 1, 0, 0);
        gl.glRotated(angles.z, 0, 0, 1);

        body.draw(gl);
        mainScrew.draw(gl);
        rollScrew.draw(gl);

        gl.glPopMatrix();
    }

}
