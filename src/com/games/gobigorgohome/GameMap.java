package com.games.gobigorgohome;
import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel{
    private String currentRoom;


    public GameMap(String currentRoom){
       this.currentRoom = currentRoom;
    };


    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        setBackground(Color.BLACK);
        if(currentRoom.equals("machines")){
            drawRoom(graphics, "machines", 100, 20, 150, 60, Color.RED);
        }
        else{
            drawRoom(graphics, "machines", 100, 20, 150, 60, Color.GREEN);
        }
        if(currentRoom.equals("managers office")){
            drawRoom(graphics, "managers office", 300, 20, 150, 60, Color.RED);
        }
        else{
            drawRoom(graphics, "managers office", 300, 20, 150, 60, Color.GREEN);
        }
        if(currentRoom.equals("free weights")){
            drawRoom(graphics, "free weights", 100, 120, 150, 60, Color.RED);
        }
        else{
            drawRoom(graphics, "free weights", 100, 120, 150, 60, Color.GREEN);
        }
        if(currentRoom.equals("yoga studio")){
            drawRoom(graphics, "yoga studio", 300, 120, 150, 60, Color.RED);
        }
        else{
            drawRoom(graphics, "yoga studio", 300, 120, 150, 60, Color.GREEN);
        }
        if(currentRoom.equals("front desk")){
            drawRoom(graphics, "front desk", 100, 200, 350, 60, Color.RED);
        }
        else{
            drawRoom(graphics, "front desk", 100, 200, 350, 60, Color.GREEN);
        }
    }

    //Takes in the x, y coordinates and draws 4 lines that create a rectangle/square
    public void drawRoom(Graphics graphics, String roomName, int x, int y, int width, int height, Color color){
        graphics.setColor(color);
        graphics.drawLine(x, y, x, y + height);
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x, y + height, x + width, y + height);
        graphics.drawLine(x + width, y, x + width, y + height);
        graphics.setColor(Color.ORANGE);
        graphics.drawString(roomName, x + width/4, y + height/2);
    }
}
