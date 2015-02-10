/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quake.projectile;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author gsta4786
 */
public class Target {
    float x, y, z;
    boolean hit;
    
    public Target(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        hit = false;
    }
    
    public void update(float bx, float by, float bz) {
        float dx = x - bx;
        float dy = y - by;
        float dz = z - bz;
        if(Math.sqrt(dx*dx + dy*dy + dz*dz) < 0.5) {
            System.out.println("Hit");
            hit = true;
        }
    }
    
    public void render() {
        if(hit)
            glColor3f(1, 0, 0);
        else
            glColor3f(0, 1, 0);
        
        glPushMatrix();
        
        glTranslatef(x, y, z);
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        glBegin(GL_QUADS);
            glVertex3f(-0.5f, -0.5f, 0);
            glVertex3f(-0.5f, 0.5f, 0);
            glVertex3f(0.5f, 0.5f, 0);
            glVertex3f(0.5f, -0.5f, 0);
        glEnd();
    
        hit = false;
        
        glEnable(GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
        
        glPopMatrix();
    }
}
