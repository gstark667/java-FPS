/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quake.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import quake.Main;

/**
 *
 * @author gavinstark
 */
public class FPSCamera {
    static float x, y, z;
    static float r, p;
    
    static {
        x = 1;
        y = 1;
        z = 1;
        r = 0;
        p = 0;
    }
    
    public static void update(float speed) {
        if(Mouse.isGrabbed()) {
            r += Mouse.getDX()*0.5;
            p -= Mouse.getDY()*0.5;
        }
        
        double angle = Math.toRadians(r);
        
        float dx = 0;
        float dz = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            dz -= speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            dz += speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            dx -= speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            dx += speed;
        }
        
        float f = Main.m.fallDistance(x, z, y-0.5f);
        System.out.println(f);
        if(f > 0) {
            y += f;
        }else if(f < -0.2) {
            y -= speed;
        }
        
        /*
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            y += speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
            y -= speed;
        }
        */
        
        z += dz*Math.cos(angle) + dx*Math.sin(angle);
        x += dz*Math.sin(angle+3.14) + dx*Math.cos(angle);
        
        if(Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            Mouse.setGrabbed(false);
    }
    
    public static void render() {
        glRotated(p, 1, 0, 0);
        glRotated(r, 0, 1, 0);
        glTranslated(-x, -y, -z);
    }
}
