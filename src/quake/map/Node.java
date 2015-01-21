/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import java.util.ArrayList;
import javax.vecmath.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import quake.Main;

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
    int wall_texture;
    int floor_texture;
    
    public Node(Polygon p, ArrayList<LineDef> l, Plane floor, Plane celing, boolean fv, boolean cv, int wall_texture, int floor_texture) {
        this.p = p;
        this.l = l;
        this.floor = floor;
        this.celing = celing;
        this.fv = fv;
        this.cv = cv;
        this.wall_texture = wall_texture;
        this.floor_texture = floor_texture;
    }
    
    public Vector3f getNormal(float x0, float y0, float x1, float y1) {
        float m = (y1-y0)/(x1-x0);
        double a = Math.atan(m);
        float x = (float)Math.cos(a);
        float y = (float)Math.sin(a);
        float z = 0;
        return new Vector3f(x,y,z);
    }
    
    public void render() {
        Main.m.setTexture(wall_texture);
        glColor3f(1, 1, 1);
        glBegin(GL_QUADS);
        for(LineDef line: l) {
            Vector3f v = getNormal(line.v0.x, line.v0.y, line.v1.x, line.v1.y);
            glNormal3f(v.x, v.y, v.z);
            glTexCoord2f(0, 0);
            glVertex3f(line.v0.x, floor.getHeight(line.v0.x, line.v0.y), line.v0.y);
            glTexCoord2f((line.v0.x+line.v0.y)-(line.v1.x+line.v1.y), 0);
            glVertex3f(line.v1.x, floor.getHeight(line.v1.x, line.v1.y), line.v1.y);
            glTexCoord2f((line.v0.x+line.v0.y)-(line.v1.x+line.v1.y), 1);
            glVertex3f(line.v1.x, celing.getHeight(line.v1.x, line.v1.y), line.v1.y);
            glTexCoord2f(0, 1);
            glVertex3f(line.v0.x, celing.getHeight(line.v0.x, line.v0.y), line.v0.y);
        }
        glEnd();
        Main.m.setTexture(floor_texture);
        glColor3f(0.7f, 0.7f, 0.7f);
        if(fv) {
            glBegin(GL_POLYGON);
            for(Vertex v: p.vertecies) {
                glTexCoord2f(v.x, v.y);
                glVertex3f(v.x, floor.getHeight(v.x,v.y), v.y);
            }
            glEnd();
        }
        if(cv) {
            glBegin(GL_POLYGON);
            for(Vertex v: p.vertecies) {
                glTexCoord2f(v.x, v.y);
                glVertex3f(v.x, celing.getHeight(v.x,v.y), v.y);
            }
            glEnd();
        }
    }
}
