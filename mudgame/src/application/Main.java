package application;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends JFrame{
 JLabel la = new JLabel("@");
 MyPanel panel = new MyPanel(); // �г� ��ü
 
 public Main(){
  setTitle("����Ű�� �����̱�");
  setSize(300,300); // âũ��
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����ÿ�Ȱ
  this.setContentPane(panel); // �г� ����
  setVisible(true); // â�г� �����ֱ� ����
  panel.requestFocus(); // ��Ŀ�� ����
 }
 
 class MyPanel extends JPanel{
  public MyPanel(){
   setLayout(null); // �η��̾ƿ�
   la.setSize(20,20);
   la.setLocation(50,50);
   add(la);
   addKeyListener(new MyKeyListener()); //������ ����
  }
 }
 
 class MyKeyListener extends KeyAdapter{
  public void keyPressed(KeyEvent e){
   int key = e.getKeyCode();
   switch(key){   
   case KeyEvent.VK_UP:
    if(la.getY() <= 0)
     return;
    else
     la.setLocation(la.getX(),la.getY()-10);
    break;
    
   case KeyEvent.VK_DOWN:
    if(la.getY()+la.getHeight()+10 > panel.getHeight())
     return;
    else
     la.setLocation(la.getX(),la.getY()+10);
    break;
    
   case KeyEvent.VK_LEFT:
    if(la.getX() <= 0)
     return;
    else
     la.setLocation(la.getX()-10,la.getY());
    break;
    
   case KeyEvent.VK_RIGHT:
    if(la.getX() + la.getWidth()+10 > panel.getWidth())
     return;
    else
     la.setLocation(la.getX()+10,la.getY());
    break;
   }
  }
 } 
 
 public static void main(String args[]){
  new Main();
 }
 
}

