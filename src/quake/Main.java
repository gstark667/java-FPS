/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
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
    //----------- Variables added for Lighting Test -----------//
	public static FloatBuffer matSpecular;
	public static FloatBuffer lightPosition;
	public static FloatBuffer whiteLight; 
	public static FloatBuffer lModelAmbient;
    //----------- END: Variables added for Lighting Test -----------//
        
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
            Display.setFullscreen(true);
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
        
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        
        //----------- Variables & method calls added for Lighting Test -----------//
	initLightArrays();
	glShadeModel(GL_SMOOTH);
	glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);		// sets specular material color
	glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);		// sets shininess
		
	glLight(GL_LIGHT0, GL_POSITION, lightPosition);		// sets light position
	glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);		// sets specular light to white
	glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);		// sets diffuse light to white
	glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);	// global ambient light 
		
	glEnable(GL_LIGHTING);					// enables lighting
	glEnable(GL_LIGHT0);					// enables light0
		
	glEnable(GL_COLOR_MATERIAL);				// enables opengl to use glColor3f to define material color
	glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);      // tell opengl glColor3f effects the ambient and diffuse properties of material
        //----------- END: Variables & method calls added for Lighting Test -----------//
    }
    
    public static void initLightArrays() {
	matSpecular = BufferUtils.createFloatBuffer(4);
	matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
	lightPosition = BufferUtils.createFloatBuffer(4);
	lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
		
	whiteLight = BufferUtils.createFloatBuffer(4);
	whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
	lModelAmbient = BufferUtils.createFloatBuffer(4);
        lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
    }
    
    public static void gameLoop() throws IOException {
        m = MapParser.parseMap("/res/simple_map.bsp");
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            glPushMatrix();
            
            Player.update(0.2f);
            
            Player.render();
            m.Render();
            
            //-----starting light code-----//
            lightPosition = BufferUtils.createFloatBuffer(4);
            lightPosition.put(5.0f).put(5.0f).put(5.0f).put(1.0f).flip();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
            //-----ending light code-----//
            
            glPopMatrix();
            
            Display.update();
            Display.sync(60);
        }
    }
    
    public static void destroyDisplay() {
        Display.destroy();
    }
}
