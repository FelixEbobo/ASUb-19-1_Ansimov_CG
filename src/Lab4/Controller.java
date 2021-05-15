package Lab4;

import java.awt.event.*;

public class Controller implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private int mousePreviousX;
    private int mousePreviousY;

    private static final double toRadian = Math.PI / 180;
    private static final double toDegree = 180 / Math.PI;

    public static boolean yawLeft, yawRight, up, down, forward, back, rollRight, rollLeft;

    public static double speed = 1;

    public static boolean spectator = true;

    private static float distance = 30;

    private static final float pull = (float) 0.1;
    private static final float pullAngle = (float) 0.3;

    public Controller() {
    }

    public static void controll() {
        double sin;
        double cos;
        double angle;
        if (spectator) {
            if (yawLeft || yawRight || forward || back) {
                angle = toRadian * Window.yAngle;
                sin = Math.sin(angle) * speed;
                cos = Math.cos(angle) * speed;
                if (yawRight) {
                    Window.xView += cos;
                    Window.zView -= sin;
                }
                if (yawLeft) {
                    Window.xView -= cos;
                    Window.zView += sin;
                }
                if (forward) {
                    Window.xView -= sin;
                    Window.zView -= cos;
                }
                if (back) {
                    Window.xView += sin;
                    Window.zView += cos;
                }
            }
            if (up || down) {
                if (up) {
                    Window.yView += speed;
                }
                if (down) {
                    Window.yView -= speed;
                }
            }
        } else {
            Window.helicopter.accel.mult(0);
            Window.helicopter.accelAngle.mult(0);

            angle = toRadian * Window.helicopter.angles.y;
            sin = Math.sin(angle);
            cos = Math.cos(angle);
            double cosX = Math.cos(-Window.xAngle * toRadian + Math.PI * 0.5);
            double sinX = Math.sin(-Window.xAngle * toRadian + Math.PI * 0.5);
            double cosY = Math.cos(-Window.yAngle * toRadian - Math.PI * 0.5);
            double sinY = Math.sin(-Window.yAngle * toRadian - Math.PI * 0.5);
            Window.xView = Window.helicopter.position.x - (float) (distance * sinX * cosY);
            Window.yView = Window.helicopter.position.y - (float) (distance * cosX);
            Window.zView = Window.helicopter.position.z - (float) (distance * sinX * sinY);

            Vector3 direction = new Vector3(0, 0, 0);

            // Рыскание вправо
            if (yawRight) {
                Window.helicopter.accelAngle.y = -(float) pullAngle;
            }
            // Рыскание влево
            if (yawLeft) {
                Window.helicopter.accelAngle.y = (float) pullAngle;
            }
            if (rollRight) {
                Window.helicopter.angles.z += (20 - Window.helicopter.angles.z) * 0.1;
                direction.x += -(float) cos;
                direction.z += (float) sin;
            }
            if (rollLeft) {
                Window.helicopter.angles.z += (-20 - Window.helicopter.angles.z) * 0.1;
                direction.x += (float) cos;
                direction.z += -(float) sin;
            }
            if (forward) {
                Window.helicopter.angles.x += (30 - Window.helicopter.angles.x) * 0.1;
                direction.x += (float) sin;
                direction.z += (float) cos;
            }
            if (back) {
                Window.helicopter.angles.x += (-30 - Window.helicopter.angles.x) * 0.1;
                direction.x += -(float) sin;
                direction.z += -(float) cos;
            }
            if (up) {
                direction.y += (float) 1;
            }
            if (down) {
                direction.y += -(float) 1;
            }
            if (!forward && !back) {
                Window.helicopter.angles.x *= 0.9;
            }
            if (!rollRight && !rollLeft) {
                Window.helicopter.angles.z *= 0.9;
            }
            direction.normalize();
            direction.mult(pull);
            Window.helicopter.accel = direction;
        }
    }

    //KeyListener
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_D) {
            yawRight = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            yawLeft = true;
        }
        if (keyCode == KeyEvent.VK_W) {
            forward = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            back = true;
        }
        if (keyCode == KeyEvent.VK_SHIFT) {
            up = true;
        }
        if (keyCode == KeyEvent.VK_CONTROL) {
            down = true;
        }
        if (keyCode == KeyEvent.VK_Q) {
            rollLeft = true;
        }
        if (keyCode == KeyEvent.VK_E) {
            rollRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_D) {
            yawRight = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            yawLeft = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            forward = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            back = false;
        }
        if (keyCode == KeyEvent.VK_SHIFT) {
            up = false;
        }
        if (keyCode == KeyEvent.VK_CONTROL) {
            down = false;
        }
        if (keyCode == KeyEvent.VK_Q) {
            rollLeft = false;
        }
        if (keyCode == KeyEvent.VK_E) {
            rollRight = false;
        }
        // Режим наблюдателя / посадка в вёртолёт
        if (keyCode == KeyEvent.VK_F) {
            spectator = !spectator;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        Window.xAngle += (mousePreviousY - e.getY()) * 0.4;
        Window.yAngle += (mousePreviousX - e.getX()) * 0.4;
        mousePreviousX = e.getX();
        mousePreviousY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mousePreviousX = e.getX();
        mousePreviousY = e.getY();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            distance++;
        } else {
            distance--;
        }
    }

}
