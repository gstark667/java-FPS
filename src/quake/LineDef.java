/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class LineDef {
    Vertex v0, v1;
    
    public LineDef(Vertex v0, Vertex v1) {
        this.v0 = v0;
        this.v1 = v1;
        System.out.println("LineDef: " + v0.x + "," + v0.y + " " + v1.x + "," + v1.y);
    }
    
    public void render() {
        glVertex3f(v0.x, v0.y, 0);
        glVertex3f(v1.x, v1.y, 0);
    }
}
