/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import quake.map.Vertex;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class LineDef {
    Vertex v0, v1;
    boolean direction;
    public LineDef(Vertex v0, Vertex v1, boolean direction) {
        this.v0 = v0;
        this.v1 = v1;
        this.direction = direction;
    }
    
    public void render() {
        glVertex3f(v0.x, 0, v0.y);
        glVertex3f(v1.x, 0, v1.y);
    }
}
