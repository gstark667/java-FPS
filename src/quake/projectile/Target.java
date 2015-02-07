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
    
    public void render() {
        if(hit)
            glColor3f(1, 0, 0);
        else
            glColor3f(0, 1, 0);
        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        glBegin(GL_QUADS);
            glVertex3f(x, y, z);
            glVertex3f(x, y+1, z);
            glVertex3f(x+1, y+1, z);
            glVertex3f(x+1, y, z);
        glEnd();
    
        glEnable(GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
    }
}
