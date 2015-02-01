/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import static org.lwjgl.opengl.GL11.*;

import quake.util.Vertex2f;
import quake.util.Vertex3f;
import java.util.ArrayList;
import quake.player.Player;

/**
 *
 * @author gsta4786
 */
public class Model {
    Vertex3f[] verticies;
    Vertex2f[] uvs;
    Vertex3f[] normals;
    int[] faces;
    
    public Model(ArrayList<Vertex3f> verticies, ArrayList<Vertex2f> uvs, ArrayList<Vertex3f> normals, ArrayList<Integer> faces) {
        this.verticies = new Vertex3f[verticies.size()];
        this.uvs = new Vertex2f[uvs.size()];
        this.normals = new Vertex3f[normals.size()];
        this.faces = new int[faces.size()];
        verticies.toArray(this.verticies);
        uvs.toArray(this.uvs);
        normals.toArray(this.normals);
        for(int i = 0; i < faces.size(); i++)
            this.faces[i] = faces.get(i);
    }
    
    public void render(float x, float y, float z) {
        glPushMatrix();
        glTranslatef(x, y, z);
        glColor3f(1, 1, 1);
        glBegin(GL_TRIANGLES);
            for(int i = 0; i < faces.length; i += 3) {
                Vertex3f vertex = verticies[faces[i]];
                Vertex2f uv = uvs[faces[i+1]];
                Vertex3f normal = normals[faces[i+2]];
                glNormal3f(normal.x, normal.y, normal.z);
                glTexCoord2f(uv.y, uv.x);
                glVertex3f(vertex.x, vertex.y, vertex.z);
            }
        glEnd();
        glPopMatrix();
    }
    
    public void renderFollow(float x, float y, float z) {
        glPushMatrix();
        glTranslatef(x, y, z);
        double xd = x - Player.x;
        double zd = z - Player.z;
        glRotated(-Math.toDegrees(Math.atan2(zd, xd))-90, 0, 1, 0);
        glRotated(Math.toDegrees(Math.atan2(y-Player.y, Math.sqrt(xd*xd + zd*zd))), 1, 0, 0);
        glColor3f(1, 1, 1);
        glBegin(GL_TRIANGLES);
            for(int i = 0; i < faces.length; i += 3) {
                Vertex3f vertex = verticies[faces[i]];
                Vertex2f uv = uvs[faces[i+1]];
                Vertex3f normal = normals[faces[i+2]];
                glNormal3f(normal.x, normal.y, normal.z);
                glTexCoord2f(uv.x, uv.y);
                glVertex3f(vertex.x, vertex.y, vertex.z);
            }
        glEnd();
        glPopMatrix();
    }
}
