package com.games.gobigorgohome;

import javax.swing.*;
import java.awt.*;

public class PlayerBody extends JPanel {
   private boolean[] muscleGroups;

   public PlayerBody(boolean[] muscleGroups){
      this.muscleGroups = muscleGroups;
   }

   public void setPanelSize(int width, int height){
      this.setSize(width, height);
   }

   @Override
   public void paintComponent(Graphics graphics){
      super.paintComponent(graphics);
      setBackground(Color.black);
      drawPlayer(graphics);
   }

   public void drawPlayer(Graphics graphics){
      graphics.setColor(Color.GREEN);
      graphics.drawOval(50, 50, 50, 50);
      if(muscleGroups[1]){
         graphics.setColor(Color.RED);
         graphics.drawLine(75, 100, 75, 200);
      }
      else{
         graphics.setColor(Color.GREEN);
         graphics.drawLine(75, 100, 75, 200);
      }
      if(muscleGroups[0]){
         graphics.setColor(Color.RED);
      }
      else{
         graphics.setColor(Color.GREEN);
      }
      graphics.drawLine(75, 200, 100, 250);
      graphics.drawLine(75, 200, 50, 250);

      if(muscleGroups[5]){
         graphics.setColor(Color.RED);
         graphics.drawLine(25, 125, 125, 125);
      }
      else{
         graphics.setColor(Color.GREEN);
         graphics.drawLine(25, 125, 125, 125);
      }
      if(muscleGroups[2]){
         drawChest(graphics, 50, 127, 50, 25, Color.RED);
      }
      else{
         drawChest(graphics, 50, 125, 50, 25, Color.GREEN);
      }
      if(muscleGroups[3]){
         drawCore(graphics, 62, 155, 25, 40, Color.RED);
      }
      else{
         drawCore(graphics, 62, 155, 25, 40, Color.GREEN);
      }
      if(muscleGroups[4]){
         drawShoulders(graphics, 50, 113, 50, 10, Color.RED);
      }
      else{
         drawShoulders(graphics, 50, 113, 50, 10, Color.GREEN);
      }
   }

   public void drawChest(Graphics graphics, int x, int y, int width, int height, Color color){
      graphics.setColor(color);
      graphics.drawLine(x, y, x, y + height);
      graphics.drawLine(x, y, x + width, y);
      graphics.drawLine(x, y + height, x + width, y + height);
      graphics.drawLine(x + width, y, x + width, y + height);
   }

   public void drawCore(Graphics graphics, int x, int y, int width, int height, Color color){
      graphics.setColor(color);
      graphics.drawLine(x, y, x, y + height);
      graphics.drawLine(x, y, x + width, y);
      graphics.drawLine(x, y + height, x + width, y + height);
      graphics.drawLine(x + width, y, x + width, y + height);
      graphics.drawLine(x, y + height/3, x + width, y + height/3);
      graphics.drawLine(x, y + (height * 2)/3, x + width, y + (height * 2)/3);
   }

   public void drawShoulders(Graphics graphics, int x, int y, int width, int height, Color color){
      graphics.setColor(color);
      graphics.drawLine(x, y, x, y + height);
      graphics.drawLine(x, y, x + width, y);
      graphics.drawLine(x, y + height, x + width, y + height);
      graphics.drawLine(x + width, y, x + width, y + height);
   }


}