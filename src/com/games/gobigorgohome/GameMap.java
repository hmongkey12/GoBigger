package com.games.gobigorgohome;
import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel{

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        setBackground(Color.black);
        //drawRoom() will use the string to name the rooms
        drawRoom(graphics, "machines", 50, 10, 200, 100);
        drawRoom(graphics, "managers office", 290, 110, 200, 100);
        drawRoom(graphics, "free weights", 10, 220, 180, 120);
        drawRoom(graphics, "yoga studio", 230, 260, 200, 80);
        drawRoom(graphics, "front desk", 50, 400, 300, 80);

    }

    //Takes in the x, y coordinates and draws 4 lines that create a rectangle/square
    public void drawRoom(Graphics graphics, String roomName, int x, int y, int width, int height){
        graphics.setColor(Color.YELLOW);
        graphics.drawLine(x, y, x, y + height);
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x, y + height, x + width, y + height);
        graphics.drawLine(x + width, y, x + width, y + height);
        graphics.setColor(Color.GREEN);
        graphics.drawString(roomName, x + width/3, y + height/2);
    }
}
