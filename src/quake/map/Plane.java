/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class Plane {
    float x, y, z;
    
    public Plane(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void render() {
        glBegin(GL_QUADS);
        
        glVertex3f(-1, getHeight(-1, -1), -1);
        glVertex3f(-1, getHeight(-1, 1), 1);
        glVertex3f(1, getHeight(1, 1), 1);
        glVertex3f(1, getHeight(1, -1), -1);
        
        glEnd();
    }
    
    public float getHeight(float xp, float yp) {
        return xp*x + yp*y + z;
    }
}
