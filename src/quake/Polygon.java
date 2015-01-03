/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class Polygon {
    ArrayList<Vertex> vertecies;
    
    public Polygon(ArrayList<Vertex> vertecies) {
        this.vertecies = vertecies;
    }
    
    public void render() {
        glBegin(GL_POLYGON);
            for(Vertex v: vertecies)
                glVertex3f(v.x, v.y, 0);
        glEnd();
    }
    
    public boolean contains(float x, float y) {
        int left = 0;
        for(int i = 0; i < vertecies.size(); i++) {
            Vertex v0 = vertecies.get(i);
            Vertex v1;
            try {
                v1 = vertecies.get(i+1);
            }catch (Exception e) {
                v1 = vertecies.get(0);
            }
            
            if((y > v0.y && y < v1.y)) {
                float p = (v1.y - y) / (v1.y-v0.y);
                glVertex3f(v1.x - (p * (v1.x - v0.x)), y, 0);
                if(v1.x - (p * (v1.x - v0.x)) < x)
                    left++;
                System.out.println("Intersect: " + y);
            }else if((y < v0.y && y > v1.y)) {
                float p = (v1.y - y) / (v1.y-v0.y);
                glVertex3f(v1.x - (p * (v1.x - v0.x)), y, 0);
                if(v1.x - (p * (v1.x - v0.x)) < x)
                    left++;
                System.out.println("Intersect: " + y);
            }
        }
        if(left % 2 == 1) {
            glVertex3f(x, y, 0);
            System.out.println("Real Intersection");
        }
        return false;
    }
}
