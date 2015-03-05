/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quake.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslated;
import quake.main.Main;
import quake.projectile.Projectile;

/**
 *
 * @author gavinstark
 */
public class Player{
    static float x_position = 0, y_position = 0, z_position = 0;
    static float x_velocity = 0, y__velocity = 0, z_velocity = 0;
    static float yaw, pitch = 0;
    static float time = 0;
    static float input_x = 0;
    static float input_y = 0;
    
    public static void setPosition(float new_x_position, float new_y_position, float new_z_position) {
        x_position = new_x_position;
        y_position = new_y_position;
        z_position = new_z_position;
    }
    
    public static float getXPositon() {
        return x_position;
    }
    
    public static float getYPositon() {
        return y_position;
    }
    
    public static float getZPositon() {
        return z_position;
    }
    
    public static void update(float speed) {
        if(Mouse.isGrabbed()) {
            yaw += Mouse.getDX()*0.5;
            
            float mouse_delta_y = Mouse.getDY();
            if(mouse_delta_y > 0 && pitch > -90)
                pitch -= mouse_delta_y*0.5;
            else if(mouse_delta_y < 0 && pitch < 90)
                pitch -= mouse_delta_y*0.5;
        }
        
        double angle = Math.toRadians(yaw);
        
        input_x = 0;
        input_y = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            input_y -= speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            input_y += speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            input_x -= speed;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            input_x += speed;
        }
        
        float f = Main.m.fallDistance(x_position, z_position, y_position-0.5f);
        if(f > 0) {
            y_position += f;
            y__velocity = 0;
        }else if(f < -0.2) {
            y_position += 0.01f;
        }
        
        float c = Main.m.fallDistance(x_position, z_position, y_position);
        if(c > 0)
            y__velocity = -0.01f;
        
        y_position += y__velocity;
        
        if(f > -0.2 && Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            y__velocity = 0.15f;
            //yv = 0.3f;
            input_y *= 2;
        }else if(f < -0.2) {
            y__velocity -= 0.01f;
        }
        
        z_velocity *= 0.9f;
        x_velocity *= 0.9f;
        
        if(Math.abs(z_velocity) < 5)
            z_velocity += (float)(input_y*Math.cos(angle) + input_x*Math.sin(angle))*0.1f;
        if(Math.abs(x_velocity) < 5)
            x_velocity += (float)(input_y*Math.sin(angle+3.14) + input_x*Math.cos(angle))*0.1f;
        
        
        
        if(Main.m.canMove(x_position, z_position, x_position, z_position+0.5f, y_position-0.35f)) {
            if(z_velocity > 0) {
                z_position += z_velocity;
            }
        }else if(z_velocity > 0) {
            z_velocity = 0;
        }
        
        if(Main.m.canMove(x_position, z_position, x_position, z_position-0.5f, y_position-0.35f)) {
            if(z_velocity < 0) {
                z_position += z_velocity;
            }
        }else if(z_velocity < 0) {
            z_velocity = 0;
        }
        
        if(Main.m.canMove(x_position, z_position, x_position+0.5f, z_position, y_position-0.35f)) {
            if(x_velocity > 0) {
                x_position += x_velocity;
            }
        }else if(x_velocity > 0) {
            x_velocity = 0;
        }
        
        if(Main.m.canMove(x_position, z_position, x_position-0.5f, z_position, y_position-0.35f)) {
            if(x_velocity < 0) {
                x_position += x_velocity;
            }
        }else if(x_velocity < 0) {
            x_velocity = 0;
        }
        
        if(Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true); 
            Main.pl.add(new Projectile(x_position+0.5f, y_position, z_position, (float)Math.cos(Math.toRadians(yaw-90)), (float)Math.sin(Math.toRadians(-pitch)), (float)Math.sin(Math.toRadians(yaw-90))));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            Mouse.setGrabbed(false);
        
        time+=0.1f;
        
        Main.out.println("pt:" + x_position + "," + y_position + "," + z_position);
    }
    
    public static void render() {
        glTranslated(Math.sin(time)*(0.01f+Math.abs(input_y)+Math.abs(input_x)), Math.sin(time*2)*(0.005f+Math.abs(input_y)+Math.abs(input_x)), 0);
        glRotated(pitch, 1, 0, 0);
        glRotated(yaw, 0, 1, 0);
        glTranslated(-x_position, -y_position-0.5, -z_position);
        
    }
}
