/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.Manager;

import java.awt.Graphics2D;
import rabbitgame.GamePanel;
import rabbitgame.GameStates.GameState;
import rabbitgame.GameStates.Level1State;
import rabbitgame.GameStates.MenuState;
import rabbitgame.GameStates.PauseState;
import rabbitgame.Handler.InputHandler;

/**
 *
 * @author Tobias
 */
public class GameStateManager {
    private boolean paused;
    private PauseState pauseState;

    private GameState[] gameStates;
    private int currentState;
    private int previousState;

    public static final int NUM_STATES = 4;
    public static final int INTRO = 0;
    public static final int MENU = 1;
    public static final int LEVEL_1 = 2;
    public static final int LEVEL_2 = 3;
    public static final int GAMEOVER = 4;
    
    private GamePanel gp;
    
    public GameStateManager(GamePanel gp) {
        this.gp = gp;
        paused = false;
        pauseState = new PauseState(this);

        gameStates = new GameState[NUM_STATES];
        setState(MENU);

    }
    
    public void setState(int i) {
        previousState = currentState;
        unloadState(previousState);
        currentState = i;
        if(i == INTRO) {
        //        gameStates[i] = new IntroState(this);
                gameStates[i].init();
        }
        else if(i == MENU) {
                gameStates[i] = new MenuState(this);
                gameStates[i].init();
        }
        else if(i == LEVEL_1) {
                gameStates[i] = new Level1State(this);
                gameStates[i].init();
        } else if(i == LEVEL_2) {
        //        gameStates[i] = new PlayState(this);
                gameStates[i].init();
        }
        else if(i == GAMEOVER) {
        //        gameStates[i] = new GameOverState(this);
                gameStates[i].init();
        }
    } 
    
    public void unloadState(int i) {
        gameStates[i] = null;
    } 

    public void setPaused(boolean b) {
        paused = b;
    }
    
    public void update() {
        if(paused) {
            pauseState.update();
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }

    public void draw(Graphics2D g) {
        if(paused) {
            pauseState.draw(g);
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].draw(g);
        }
    }
    
    public InputHandler getInputManager(){
        return this.gp.input;
    }
    
    public int getHeight(){
        return gp.getHeight();
    }
    
    public int getWidth(){
        return gp.getWidth();
    }
    
}
