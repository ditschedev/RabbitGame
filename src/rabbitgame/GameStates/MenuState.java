/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.text.AbstractDocument;
import rabbitgame.Manager.Animation;
import rabbitgame.Manager.GameStateManager;
import rabbitgame.Manager.Sprites;

/**
 *
 * @author Tobias
 */
public class MenuState extends GameState {
	
	private BufferedImage bg;
        private BufferedImage start;
        private BufferedImage quit;
	private BufferedImage carrot;
        private BufferedImage[] fox;
        
        //protected Animation animation;
	//protected int currentAnimation;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Sprites.MENUBG[0][0];
		carrot = Sprites.CARROT[0][0];
            try {
                getMenuPoints();
                //fox = Sprites.FOX_DOWN[0];
                //animation = new Animation();
                //animation.setFrames(fox);
                //animation.setDelay(7);
                //JukeBox.load("/SFX/collect.wav", "collect");
                //JukeBox.load("/SFX/menuoption.wav", "menuoption");
            } catch (IOException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        private void getMenuPoints() throws IOException{
            start = ImageIO.read(MenuState.class.getResourceAsStream("/Menu/start.png"));
            quit = ImageIO.read(MenuState.class.getResourceAsStream("/Menu/quit.png"));
        }
	
	public void update() {
		handleInput();
                //animation.update();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, gsm.getWidth(), gsm.getHeight());
                g.drawImage(bg, 0, 0, gsm.getWidth(), gsm.getHeight(), null);
		g.setColor(Color.white);
		g.drawImage(start, 50, 86, 60, 16, null);
                g.drawImage(quit, 50, 106, 60, 16, null);
                //g.drawImage(animation.getImage(), 25, 86, 13, 32, null);
                if(currentOption == 0) g.drawImage(carrot, 25, 86, 16, 16, null);
		else if(currentOption == 1) g.drawImage(carrot, 25, 106, 16, 16, null);
		
	}
	
	public void handleInput() {
            if(gsm.getInputManager().down && currentOption < options.length - 1) {
                currentOption++;
                System.out.println(currentOption);
            }
            if(gsm.getInputManager().up && currentOption > 0) {
                currentOption--;
                System.out.println(currentOption);
            }
            if(gsm.getInputManager().enter) {
                selectOption();
            }
	}
	
	private void selectOption() {
		if(currentOption == 0) {
                    gsm.setState(GameStateManager.LEVEL_1);
		}
		if(currentOption == 1) {
                    System.exit(0);
		}
	}
	
}
