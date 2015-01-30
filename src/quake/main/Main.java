/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.Model;
import obj.ModelLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import quake.map.BufferTools;
import quake.map.Map;
import quake.map.MapParser;
import quake.player.Player;

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
        initDisplay();
        initGL();
        gameLoop();
        destroyDisplay();
    }
    
    public static void initDisplay() {
        try {
            Display.setDisplayMode(Display.getAvailableDisplayModes()[0]);
            Display.setFullscreen(false);
            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initGL() {
        glMatrixMode(GL_PROJECTION);
        gluPerspective(90, (float)Display.getWidth()/Display.getHeight(), 0.01f, 10000);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
        
        glEnable(GL_SMOOTH);
        
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{1f, 1f, 1f, 1f}));
        glLight(GL_LIGHT0, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(new float[]{1, 1, 1, 1}));
        glLight(GL_LIGHT0, GL_SPECULAR, BufferTools.asFlippedFloatBuffer(new float[]{1, 0, 0, 1}));
        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 0}));
    }
    
    public static void gameLoop() throws IOException {
        m = MapParser.parseMap("/res/simple_map.bsp");
        //Model monkey = ModelLoader.loadModel("src/res/lowpoly.obj");
        Model map = ModelLoader.loadModel("src/res/castle.obj");
        t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/texture.png"), GL_NEAREST);
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            glPushMatrix();
            
            Player.update(0.2f);
            Player.render();
            
            glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0.8f, 0.5f, 0.3f, 0}));
            
            glBindTexture(GL_TEXTURE_2D, t.getTextureID());
            
            //monkey.renderFollow(2.5f, 2, 5);
            map.render(0, 0, 0);
            
            glPopMatrix();
            
            Display.update();
            Display.sync(60);
        }
    }
    
    public static void destroyDisplay() {
        Display.destroy();
    }
}
