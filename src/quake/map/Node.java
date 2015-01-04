/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.GLUtessellator;

/**
 *
 * @author Octalus
 */
public class Node {
    Polygon p;
    ArrayList<LineDef> l;
    Plane floor;
    Plane celing;
    boolean fv, cv;
    
    public Node(Polygon p, ArrayList<LineDef> l, Plane floor, Plane celing, boolean fv, boolean cv) {
        this.p = p;
        this.l = l;
        this.floor = floor;
        this.celing = celing;
        this.fv = fv;
        this.cv = cv;
    }
    
    public void render() {
        
        
        
        glColor3f(1, 1, 1);
        glBegin(GL_QUADS);
        for(LineDef line: l) {
            glVertex3f(line.v0.x, floor.getHeight(line.v0.x, line.v0.y), line.v0.y);
            glVertex3f(line.v1.x, floor.getHeight(line.v1.x, line.v1.y), line.v1.y);
            glVertex3f(line.v1.x, celing.getHeight(line.v1.x, line.v1.y), line.v1.y);
            glVertex3f(line.v0.x, celing.getHeight(line.v0.x, line.v0.y), line.v0.y);
        }
        glEnd();
        
        glColor3f(0, 0, 1);
        if(fv) {
            glBegin(GL_POLYGON);
            for(Vertex v: p.vertecies)
                glVertex3f(v.x, floor.getHeight(v.x,v.y), v.y);
            glEnd();
        }
        if(cv) {
            glBegin(GL_POLYGON);
            for(Vertex v: p.vertecies)
                glVertex3f(v.x, celing.getHeight(v.x,v.y), v.y);
            glEnd();
        }
    }
}
