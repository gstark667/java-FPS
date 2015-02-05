/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quake.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslated;
import quake.main.Main;
import quake.projectile.Projectile;

/**
 *
 * @author gavinstark
 */
public class Player{
    public static float x, y, z;
    static float xv, yv, zv;
    static float r, p;
    static float t;
    static float dx = 0;
    static float dz = 0;
    static {
        x = 1;
        y = 6;
        z = 1;
        r = 0;
        p = 0;
        t = 0;
    }
    
    public static void update(float speed) {
        if(Mouse.isGrabbed()) {
            r += Mouse.getDX()*0.5;
            
            float yd = Mouse.getDY();
            if(yd > 0 && p > -90)
                p -= yd*0.5;
            else if(yd < 0 && p < 90)
                p -= yd*0.5;
        }
        
        double angle = Math.toRadians(r);
        
        dx = 0;
        dz = 0;
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
        if(f > 0) {
            y += f;
            yv = 0;
        }else if(f < -0.2) {
            y += 0.01f;
        }
        
        float c = Main.m.fallDistance(x, z, y);
        if(c > 0)
            yv = -0.01f;
        
        y += yv;
        
        if(f > -0.2 && Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            yv = 0.15f;
            //yv = 0.3f;
            dz *= 2;
        }else if(f < -0.2) {
            yv -= 0.01f;
        }
        
        zv *= 0.9f;
        xv *= 0.9f;
        
        if(Math.abs(zv) < 5)
            zv += (float)(dz*Math.cos(angle) + dx*Math.sin(angle))*0.1f;
        if(Math.abs(xv) < 5)
            xv += (float)(dz*Math.sin(angle+3.14) + dx*Math.cos(angle))*0.1f;
        
        
        
        if(Main.m.canMove(x, z, x, z+0.5f, y-0.35f)) {
            if(zv > 0) {
                z += zv;
            }
        }else if(zv > 0) {
            zv = 0;
        }
        
        if(Main.m.canMove(x, z, x, z-0.5f, y-0.35f)) {
            if(zv < 0) {
                z += zv;
            }
        }else if(zv < 0) {
            zv = 0;
        }
        
        if(Main.m.canMove(x, z, x+0.5f, z, y-0.35f)) {
            if(xv > 0) {
                x += xv;
            }
        }else if(xv > 0) {
            xv = 0;
        }
        
        if(Main.m.canMove(x, z, x-0.5f, z, y-0.35f)) {
            if(xv < 0) {
                x += xv;
            }
        }else if(xv < 0) {
            xv = 0;
        }
        
        if(Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
            System.out.println(r);
            Main.pl.add(new Projectile(x, y+1, z, (float)Math.cos(Math.toRadians(r-90)), (float)Math.sin(Math.toRadians(-p)), (float)Math.sin(Math.toRadians(r-90))));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            Mouse.setGrabbed(false);
        
        t+=0.1f;
    }
    
    public static void render() {
        glTranslated(Math.sin(t)*(0.01f+Math.abs(dz)+Math.abs(dx)), Math.sin(t*2)*(0.005f+Math.abs(dz)+Math.abs(dx)), 0);
        glRotated(p, 1, 0, 0);
        glRotated(r, 0, 1, 0);
        glTranslated(-x, -y-0.5, -z);
        
    }
}