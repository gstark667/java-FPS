/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake;

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
        
        mapReader.close();
        
        return new Map(vertecies, lineDefs, polygons);
    }
}
