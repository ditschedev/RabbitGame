/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import rabbitgame.Handler.InputHandler;
import rabbitgame.Manager.GameStateManager;

/**
 *
 * @author Tobias
 */
public class GamePanel extends JPanel implements Runnable{

	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;
        
        // input handler
        public InputHandler input;
	
	// game state manager
	private GameStateManager gsm;
	
	// constructor
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
                input = new InputHandler();
                addKeyListener(input);
	}
	
	// ready to display
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	// run new thread
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	// initializes fields
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, 1);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager(this);
	}
	
	// updates game
	private void update() {
		gsm.update();
		input.tick();
	}
	
	// draws game
	private void draw() {
		gsm.draw(g);
	}
	
	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
    
}
