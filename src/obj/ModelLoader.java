/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gsta4786
 */
public class ModelLoader {
    public static Model loadModel(String path) {
        try {
            Scanner modelInput = new Scanner(new File(path));
            ArrayList<Vertex3f> vertecies = new ArrayList<Vertex3f>();
            ArrayList<Integer> faces = new ArrayList<Integer>();
            ArrayList<Vertex3f> normals = new ArrayList<Vertex3f>();
            ArrayList<Vertex2f> uvs = new ArrayList<Vertex2f>();
            
            while(modelInput.hasNextLine()) {
                String line = modelInput.nextLine();
                if(line.startsWith("v ")) {
                    String[] split = line.split(" ");
                    vertecies.add(new Vertex3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                }else if(line.startsWith("vn ")) {
                    String[] split = line.split(" ");
                    normals.add(new Vertex3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                }else if(line.startsWith("f ")) {
                    String[] split = line.split(" ");
                    faces.add(Integer.parseInt(split[1].split("/")[0])-1);
                    faces.add(Integer.parseInt(split[1].split("/")[1])-1);
                    faces.add(Integer.parseInt(split[1].split("/")[2])-1);
                    faces.add(Integer.parseInt(split[2].split("/")[0])-1);
                    faces.add(Integer.parseInt(split[2].split("/")[1])-1);
                    faces.add(Integer.parseInt(split[2].split("/")[2])-1);
                    faces.add(Integer.parseInt(split[3].split("/")[0])-1);
                    faces.add(Integer.parseInt(split[3].split("/")[1])-1);
                    faces.add(Integer.parseInt(split[3].split("/")[2])-1);
                }else if(line.startsWith("vt ")) {
                    String[] split = line.split(" ");
                    uvs.add(new Vertex2f(Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                }
            }
            
            return new Model(vertecies, uvs, normals, faces);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
