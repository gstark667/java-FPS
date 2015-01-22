/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class Vertex {
    float x, y;
    
    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render() {
        glVertex3f(x, 0, y);
    }
}
