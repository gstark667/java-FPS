/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class Main {

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
            Display.setDisplayMode(new DisplayMode(600, 400));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initGL() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 600, -600);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
    }
    
    public static void gameLoop() {
        Map m = MapParser.parseMap("/res/map.bsp");
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            glScalef(10,10,10);
            m.Render();
            
            glColor3f(0,0,1);
            glBegin(GL_POINTS);
            m.polygons.get(0).contains(Mouse.getX()/10.0f, Mouse.getY()/10.0f);
            glEnd();
            
            Display.update();
            Display.sync(60);
        }
    }
    
    public static void destroyDisplay() {
        Display.destroy();
    }
}
