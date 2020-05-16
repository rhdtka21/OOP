// KeyListenerTest.java                          Written by Seok Jungwoo 2015003209

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class KeyListenerTest extends JFrame {
    //private JPanel contentPane = new JPanel();
    //private static JLabel listener= new JLabel();
    public JTextField mainWindow = new JTextField(10);
    public KeyListenerTest() {

        super("Cat Dog Mouse Game");
        addKeyListener(new MyKeyListener());

        setSize(-1, -1);
        setVisible(true);
        toFront();
        requestFocus();


    }

    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    CatDogMouse.KEYINPUT = 'N';
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    CatDogMouse.KEYINPUT = 'S';
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    CatDogMouse.KEYINPUT = 'W';
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    CatDogMouse.KEYINPUT = 'E';
                    break;
                case KeyEvent.VK_U:
                    CatDogMouse.KEYINPUT = 'U';
                    break;
            }
        }
    }

    public void finProg(){
        setVisible(false);
    }
}
