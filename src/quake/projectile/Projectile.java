/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.projectile;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static org.lwjgl.opengl.GL11.*;
import quake.main.Main;

/**
 *
 * @author Octalus
 */
public class Projectile {
    public float x, y, z;
    public float xv, yv, zv;
    
    public Projectile(float x, float y, float z, float xv, float yv, float zv) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xv = xv;
        this.yv = yv;
        this.zv = zv;
    } 
   
    public boolean update() {
        if(!Main.m.canMove(x, z, x + xv, z + zv, y) || Main.m.floorCollide(x, y, z, xv, yv, zv)) {
            return true;
        }
        
        x += xv;
        y += yv;
        z += zv;
        
        return false;
    }
    
    public void render() {
        //glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        glBegin(GL_LINES);
            glColor3f(1, 0, 0);
            glVertex3f(x, y, z);
            glColor3f(1, 0.5f, 0);
            glVertex3f(x+xv, y+yv, z+zv);
        glEnd();
        glEnable(GL_LIGHTING);
        //glEnable(GL_DEPTH_TEST);
    }
}
