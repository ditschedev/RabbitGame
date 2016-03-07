/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame;

import javax.swing.JFrame;

/**
 *
 * @author Tobias
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Diamond Hunter");
		
        window.add(new GamePanel());

        window.setResizable(false);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
