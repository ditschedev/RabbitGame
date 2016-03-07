/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Tobias
 */
public class InputHandler implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right, arrup, arrdown, atk, enter, f1, esc, j;

    public InputHandler(){
            keys = new boolean[256];
    }

    public void tick(){
            up = keys[KeyEvent.VK_W];
            down = keys[KeyEvent.VK_S];
            left = keys[KeyEvent.VK_A];
            right = keys[KeyEvent.VK_D];
            atk = keys[KeyEvent.VK_SPACE];
            enter = keys[KeyEvent.VK_ENTER];
            f1 = keys[KeyEvent.VK_M];
            esc = keys[KeyEvent.VK_ESCAPE];
            j = keys[KeyEvent.VK_J];
            arrup = keys[KeyEvent.VK_UP];
            arrdown = keys[KeyEvent.VK_DOWN];
    }

    @Override
    public void keyPressed(KeyEvent e) {
            keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
            keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}
