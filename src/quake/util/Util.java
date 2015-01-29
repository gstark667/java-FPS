/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quake.util;

import java.awt.geom.Line2D;

/**
 *
 * @author Octalus
 */
public class Util {
    public static boolean lineIntersect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        return Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
    }
}
