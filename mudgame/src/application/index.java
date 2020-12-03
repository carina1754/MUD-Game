package application;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class index extends JFrame{
    
	static int[] width = {900, 285};
    static int[] height = {300, 100, 62};
    static String ststr = new String("123123");
    static String rostr = new String("232323");
    static String insstr1 = new String("← : 왼쪽 이동  → : 오른쪽 이동");    
    static String insstr2 = new String("↑ : 위로 이동  ↓ : 아래로 이동 ");
	public static void main(String []args){
	BevelBorder border = new BevelBorder(BevelBorder.RAISED);
	border=new BevelBorder(BevelBorder.RAISED);
	
	JFrame frame = new JFrame("2019253071 안정수");
	JPanel pn = new JPanel();
	pn.setLayout(null);
	
	JLabel status = new JLabel(ststr,JLabel.CENTER);
	JLabel round = new JLabel(rostr,JLabel.CENTER);
	JLabel instruction = new JLabel("<html>← &nbsp; : &nbsp; 왼쪽 &nbsp; 이동 &nbsp;&nbsp; → &nbsp; : &nbsp; 오른쪽 &nbsp; 이동 "
									+ "<br>" + "↑ &nbsp; : &nbsp; 위로 &nbsp; 이동 &nbsp;&nbsp; ↓ &nbsp; : &nbsp; 아래로 &nbsp; 이동 ",JLabel.CENTER);
	
	instruction.setBounds(900, 161, width[1], height[1]);
	round.setBounds(900, 100, width[1], height[2]);
	status.setBounds(900, 0, width[1], height[1]);
	
	pn.add(status);
	pn.add(round);
	pn.add(instruction);
	
	status.setBorder(border);
	round.setBorder(border);
	instruction.setBorder(border);
	
	round.setText(String.valueOf(ststr));
	frame.setContentPane(pn);
	frame.setSize(1200, 300);
	frame.setVisible(true);
	frame.setResizable(false);
	}
}
