/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.player;

import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import quake.Main;

/**
 *
 * @author Octalus
 */
public class FallyThingy {
    float x, y, z;
    
    public FallyThingy(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void update() {
        
        
        //if(Main.m.canFall(x, y, z))
            //z-=0.1;
    }
    
    public void render() {
        glColor3f(0,1,0);
        glBegin(GL_POINTS);
        glVertex3f(x, z, y);
        glEnd();
    }
}
