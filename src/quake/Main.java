/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import java.util.ArrayList;
import quake.map.MapParser;
import quake.map.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import quake.player.FPSCamera;
import quake.player.FallyThingy;

/**
 *
 * @author Octalus
 */
public class Main {
    public static Map m;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    }
    
    public static void gameLoop() {
        m = MapParser.parseMap("/res/map_1.bsp");
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            FPSCamera.update(0.1f);
            
            FPSCamera.render();
            m.Render();
            
            glColor3f(0,0,1);
            glBegin(GL_POINTS);
            glEnd();
            
            Display.update();
            Display.sync(60);
        }
    }
    
    public static void destroyDisplay() {
        Display.destroy();
    }
}
