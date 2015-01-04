/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class Map {
    ArrayList<Node> nodes;
    
    public Map(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }
    
    public void Render() {
        for(Node n: nodes)
            n.render();
    }
    
    public boolean canFall(float x, float y, float z) {
        for(Node n: nodes) {
            if(n.p.contains(x, y)) {
                if(n.fv) {
                    float fh = z -n.floor.getHeight(x, y);
                    if(fh < 0 && fh > -0.2)
                        return false;
                }
                if(n.cv) {
                    float ch = z -n.celing.getHeight(x, y);
                    if(ch < 0.1 && ch > -0.1)
                        return false;
                }
            }
        }
        return true;
    }
    
    public float fallDistance(float x, float y, float z) {
        float c = -1;
        for(Node n: nodes) {
            if(n.p.contains(x, y)) {
                if(n.fv) {
                    float fh = n.floor.getHeight(x, y)-z;
                    if(Math.abs(fh) < Math.abs(c))
                        c = fh;
                }
                if(n.cv) {
                    float ch = n.celing.getHeight(x, y)-z;
                    if(Math.abs(ch) < Math.abs(c))
                        c = ch;
                }
            }
        }
        return c;
    }
}
