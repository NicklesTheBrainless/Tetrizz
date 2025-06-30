package base.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean wJustPressed, aJustPressed, sJustPressed, dJustPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: wJustPressed = true;
            case KeyEvent.VK_A: aJustPressed = true;
            case KeyEvent.VK_S: sJustPressed = true;
            case KeyEvent.VK_D: dJustPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }



    // reset variables
    public void update() {
        wJustPressed = false;
        aJustPressed = false;
        sJustPressed = false;
        dJustPressed = false;
    }

}
