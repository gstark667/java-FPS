/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class Map {
    ArrayList<Vertex> vertecies;
    ArrayList<LineDef> lineDefs;
    ArrayList<Polygon> polygons;
    
    public Map(ArrayList<Vertex> vertecies, ArrayList<LineDef> lineDefs, ArrayList<Polygon> polygons) {
        this.vertecies = vertecies;
        this.lineDefs = lineDefs;
        this.polygons = polygons;
    }
    
    public void Render() {
        glColor3f(1, 1, 1);
        for(Polygon p: polygons) {
            p.render();
        }
        
        glColor3f(0, 1, 0);
        glBegin(GL_LINES);
        for(LineDef l: lineDefs) {
            l.render();
        }
        glEnd();
        
        glColor3f(1, 0, 0);
        glBegin(GL_POINTS);
        for(Vertex v: vertecies) {
            v.render();
        }
        glEnd();
    }
}
