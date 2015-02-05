/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.projectile;

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
    
    public void update() {
        x += xv;
        y += yv;
        z += zv;
        
        if(!Main.m.canMove(x, z, x + xv + xv, z + zv + zv, y)) {
            xv = 0;
            yv = 0;
            zv = 0;
        }
    }
    
    public void render() {
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        glPointSize(20);
        glBegin(GL_POINTS);
            glColor3f(1, 0, 0);
            glVertex3f(x + xv*100, y + yv*100, z + zv*100);
            glColor3f(1, 1, 1);
            glVertex3f(x, y, z);
        glEnd();
        glEnable(GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
    }
}
