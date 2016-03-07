/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitgame.Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.text.AbstractDocument.Content;

/**
 *
 * @author Tobias
 */
public class Sprites {
	
    public static BufferedImage[][] MENUBG = load("/Menu/menubg.jpg", 640, 480);
    public static BufferedImage[][] BAR = load("/HUD/bar.gif", 128, 16);

    public static BufferedImage[][] PLAYER = load("/Sprites/player.png", 32, 32);
    public static BufferedImage[][] FOX_DOWN = load("/Sprites/FoxDown.png", 14, 41);
    
    public static BufferedImage[][] CARROT = load("/Menu/carrotarrow.png", 32, 32);
    public static BufferedImage[][] SPARKLE = load("/Sprites/sparkle.gif", 16, 16);
    public static BufferedImage[][] ITEMS = load("/Sprites/items.gif", 16, 16);

    public static BufferedImage[][] font = load("/HUD/font.gif", 8, 8);

    public static BufferedImage[][] load(String s, int w, int h) {
        BufferedImage[][] ret;
        try {
            BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
            int width = spritesheet.getWidth() / w;
            int height = spritesheet.getHeight() / h;
            ret = new BufferedImage[height][width];
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
                }
            }
            return ret;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error loading graphics.");
            System.exit(0);
        }
        return null;
    }
	
}
