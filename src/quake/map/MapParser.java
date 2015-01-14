/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.map;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Octalus
 */
public class MapParser {
    public static Map parseMap(String mapPath) {
        Scanner mapReader = new Scanner(MapParser.class.getResourceAsStream(mapPath));
    
        ArrayList<Vertex> vertecies = new ArrayList<Vertex>();
        ArrayList<LineDef> lineDefs = new ArrayList<LineDef>();
        ArrayList<Polygon> polygons = new ArrayList<Polygon>();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        ArrayList<String> texture_paths = new ArrayList<String>();
        ArrayList<Node> nodes = new ArrayList<Node>();
        
        mapReader.nextLine();
        String line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            String[] split = line.split(",");
            vertecies.add(new Vertex(Float.parseFloat(split[0]), Float.parseFloat(split[1])));
            line = mapReader.nextLine();
        }
        
        mapReader.nextLine();
        line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            String[] split = line.split(",");
            lineDefs.add(new LineDef(vertecies.get(Integer.parseInt(split[0])), vertecies.get(Integer.parseInt(split[1]))));
            line = mapReader.nextLine();
        }
        
        mapReader.nextLine();
        line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            String[] split = line.split(",");
            ArrayList<Vertex> v = new ArrayList<Vertex>();
            for(String s: split)
                v.add(vertecies.get(Integer.parseInt(s)));
            polygons.add(new Polygon(v));
            line = mapReader.nextLine();
        }
        
        mapReader.nextLine();
        line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            String[] split = line.split(",");
            planes.add(new Plane(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2])));
            line = mapReader.nextLine();
        }
        
         mapReader.nextLine();
        line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            texture_paths.add(line);
            line = mapReader.nextLine();
        }
        
        mapReader.nextLine();
        line = mapReader.nextLine();
        
        while(!line.equals("end")) {
            String[] split = line.split(",");
            ArrayList<LineDef> sides = new ArrayList<LineDef>();
            for(int i = 1; i < split.length-6; i++)
                sides.add(lineDefs.get(Integer.parseInt(split[i])));
            nodes.add(new Node(polygons.get(Integer.parseInt(split[0])), sides, planes.get(Integer.parseInt(split[split.length-6])), planes.get(Integer.parseInt(split[split.length-5])), split[split.length-4].equals("1"), split[split.length-3].equals("1"), Integer.parseInt(split[split.length-2]), Integer.parseInt(split[split.length-1])));
            line = mapReader.nextLine();
        }
        
        mapReader.close();
        
        return new Map(nodes, texture_paths);
    }
}
