/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.GameStates;

import java.awt.Graphics2D;
import javax.swing.text.AbstractDocument.Content;
import rabbitgame.Manager.GameStateManager;

/**
 *
 * @author Tobias
 */
public class PauseState extends GameState {
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
            g.fillRect(0, 0, 256, 256);
		
	}
	public void handleInput() {
		if(gsm.getInputManager().esc) {
			gsm.setPaused(false);
			//JukeBox.resumeLoop("music1");
		}
		if(gsm.getInputManager().f1) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}
