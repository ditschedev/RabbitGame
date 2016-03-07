/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.GameStates;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import rabbitgame.Entity.Enemy;
import rabbitgame.Entity.Player;
import rabbitgame.GamePanel;
import rabbitgame.Manager.GameStateManager;
import rabbitgame.World.TileMap;

/**
 *
 * @author Tobias
 */
public class Level1State extends GameState {
	
	// player
	private Player player;
        private ArrayList<Enemy> enemies;
	
	// tilemap
	private TileMap tileMap;
	
	// diamonds
	//private ArrayList<Diamond> diamonds;
	
	// items
	//private ArrayList<Item> items;
	
	// sparkles
	//private ArrayList<Sparkle> sparkles;
	
	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 
	
	// hud
	//private Hud hud;
	
	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;
	
	// transition box
	private ArrayList<Rectangle> boxes;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		// create lists
		//diamonds = new ArrayList<Diamond>();
		//sparkles = new ArrayList<Sparkle>();
		//items = new ArrayList<Item>();
		
		// load map
		tileMap = new TileMap(16);
		//tileMap.loadTiles("/Tilesets/testtileset.gif");
                tileMap.loadTiles("/Tilesets/tilemap.png");
		tileMap.loadMap("/Maps/1.map");
		
		// create player
		player = new Player(tileMap);
		
		// fill lists
		//populateDiamonds();
		//populateItems();
		
		// initialize player
		player.setTilePosition(4, 2);
		//player.setTotalDiamonds(diamonds.size());
		
		// set up camera position
		sectorSize = GamePanel.WIDTH;
		//xsector = player.getx() / sectorSize;
		//ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		
		// load hud
		//hud = new Hud(player, diamonds);
		
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();
			
	}
        
        private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Enemy s;
		s = new Enemy(tileMap);
                s.setTilePosition(2, 2);
                enemies.add(s);
		
	}
	
	/*private void populateDiamonds() {
		
		Diamond d;
		
		d = new Diamond(tileMap);
		d.setTilePosition(20, 20);
		d.addChange(new int[] { 23, 19, 1 });
		d.addChange(new int[] { 23, 20, 1 });
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(12, 36);
		d.addChange(new int[] { 31, 17, 1 });
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(28, 4);
		d.addChange(new int[] {27, 7, 1});
		d.addChange(new int[] {28, 7, 1});
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(4, 34);
		d.addChange(new int[] { 31, 21, 1 });
		diamonds.add(d);
		
		d = new Diamond(tileMap);
		d.setTilePosition(28, 19);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(35, 26);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(38, 36);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(27, 28);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(20, 30);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(14, 25);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(4, 21);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(9, 14);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(4, 3);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(20, 14);
		diamonds.add(d);
		d = new Diamond(tileMap);
		d.setTilePosition(13, 20);
		diamonds.add(d);
		
	}
	
	private void populateItems() {
		
		Item item;
		
		item = new Item(tileMap);
		item.setType(Item.AXE);
		item.setTilePosition(26, 37);
		items.add(item);
		
		item = new Item(tileMap);
		item.setType(Item.BOAT);
		item.setTilePosition(12, 4);
		items.add(item);
		
	} */
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();
	
		
		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		
		if(oldxs != xsector || oldys != ysector) {
		//	JukeBox.play("mapmove");
		}
		
		if(tileMap.isMoving()) return;
		
		// update player
                player.update();
		
                // update all enemies
		for(int i = 0; i < enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    e.update();
		}
                
		// update diamonds
	/*	for(int i = 0; i < diamonds.size(); i++) {
			
			Diamond d = diamonds.get(i);
			d.update();
			
			// player collects diamond
			if(player.intersects(d)) {
				
				// remove from list
				diamonds.remove(i);
				i--;
				
				// increment amount of collected diamonds
				player.collectedDiamond();
				
				// play collect sound
				JukeBox.play("collect");
				
				// add new sparkle
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(d.getx(), d.gety());
				sparkles.add(s);
				
				// make any changes to tile map
				ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if(ali.size() != 0) {
					JukeBox.play("tilechange");
				}
				
			}
		}
		
		// update sparkles
		for(int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if(s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}
		
		// update items
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				JukeBox.play("collect");
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}
	*/	
	} 
	
	public void draw(Graphics2D g) {
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
                
                for(Enemy e : enemies) {
			e.draw(g);
		}
		
		// draw diamonds
	/*	for(Diamond d : diamonds) {
			d.draw(g);
		}
		
		// draw sparkles
		for(Sparkle s : sparkles) {
			s.draw(g);
		}
		
		// draw items
		for(Item i : items) {
			i.draw(g);
		} */
		
		// draw hud
		//hud.draw(g);
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}
		
	}
	
	public void handleInput() {
		if(gsm.getInputManager().esc) {
			gsm.setPaused(true);
		}
		if(blockInput) return;
		if(gsm.getInputManager().left) player.setLeft();
		if(gsm.getInputManager().right) player.setRight();
		if(gsm.getInputManager().up) player.setUp();
		if(gsm.getInputManager().down) player.setDown();
		if(gsm.getInputManager().enter) player.setAction();
	} 
	
	//===============================================
	
	private void eventStart() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}
	
	private void eventFinish() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if(eventTick > 33) {
			if(!true) {
				
				gsm.setState(GameStateManager.GAMEOVER);
			}
		}
	}
	
}
