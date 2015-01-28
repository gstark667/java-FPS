/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import quake.player.Player;

/**
 *
 * @author gsta4786
 */
public class Model {
    ArrayList<Vertex3f> verticies;
    ArrayList<Vertex3f> normals;
    ArrayList<Integer> faces;
    
    public Model(ArrayList<Vertex3f> verticies, ArrayList<Vertex3f> normals, ArrayList<Integer> faces) {
        this.verticies = verticies;
        this.normals = normals;
        this.faces = faces;
    }
    
    public void render(float x, float y, float z) {
        //glDisable(GL_DEPTH_TEST);
        glBindTexture(GL_TEXTURE_2D, 0);
        glPushMatrix();
        glTranslatef(x, y, z);
        //double xd = x - Player.x;
        //double zd = z - Player.z;
        //glRotated(-Math.toDegrees(Math.atan2(zd, xd))-90, 0, 1, 0);
        //glRotated(Math.toDegrees(Math.atan2(y-Player.y, Math.sqrt(xd*xd + zd*zd))), 1, 0, 0);
        glColor3f(1, 1, 1);
        glBegin(GL_TRIANGLES);
            for(int i = 0; i < faces.size(); i += 2) {
                Vertex3f vertex = verticies.get(faces.get(i));
                Vertex3f normal = normals.get(faces.get(i+1));
                glNormal3f(normal.x, normal.y, normal.z);
                glVertex3f(vertex.x, vertex.y, vertex.z);
            }
        glEnd();
        glPopMatrix();
        //glEnable(GL_DEPTH_TEST);
    }
}
