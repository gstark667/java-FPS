/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Octalus
 */
public class Map {
    ArrayList<Node> nodes;
    ArrayList<Texture> textures;
    
    public Map(ArrayList<Node> nodes, ArrayList<String> texture_paths) {
        this.nodes = nodes;
        textures = new ArrayList<Texture>();
        for(String path: texture_paths)
            try {
                textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + path), GL11.GL_NEAREST));
            } catch (IOException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setTexture(int i) {
        glBindTexture(GL_TEXTURE_2D, textures.get(i).getTextureID());
    }
    
    public void Render() {
        for(Node n: nodes)
            n.render();
        
        setTexture(0);
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
    
    public boolean canMove(float x0, float y0, float x1, float y1, float z) {
        for(Node n: nodes) {
            if(n.floor.getHeight(x0, y0) < z && n.celing.getHeight(x0, y0) > z) {
                for(LineDef l: n.l) {
                    if(Util.lineIntersect(x0, y0, x1, y1, l.v0.x, l.v0.y, l.v1.x, l.v1.y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
