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
}
