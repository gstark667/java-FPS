/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import quake.map.Map;
import quake.map.MapParser;
import quake.player.FPSCamera;

/**
 *
 * @author Octalus
 */
public class Main {
    public static Map m;
    static Texture t;
    static Texture t2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Start:7.5189915,10.672977:7.5189915,9.672977
        Line:0.0,0.0:0.0,10.0
        Line:0.0,10.0:10.0,10.0
        Line:10.0,10.0:10.0,0.0
        Line:10.0,0.0:0.0,0.0*/
        System.out.println(quake.map.Util.lineIntersect(0, 10, 10, 10, 7.5189915f, 9.672977f, 7.518991f, 11.672977f));
        initDisplay();
        initGL();
        gameLoop();
        destroyDisplay();
    }
    
    public static void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initGL() {
        glMatrixMode(GL_PROJECTION);
        gluPerspective(90, (float)800/600, 0.01f, 10000);
        //glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 600, -600);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
        
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
    }
    
    public static void gameLoop() throws IOException {
        t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/grass.png"), GL_NEAREST);
        t2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/dirt.png"), GL_NEAREST);
        m = MapParser.parseMap("/res/map.bsp");
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            glBindTexture(GL_TEXTURE_2D, t2.getTextureID());
            
            FPSCamera.update(0.1f);
            
            FPSCamera.render();
            m.Render();
            
            glColor3f(0,0,1);
            
            glBindTexture(GL_TEXTURE_2D, 0);
            
            Display.update();
            Display.sync(60);
        }
    }
    
    public static void destroyDisplay() {
        Display.destroy();
    }
}
