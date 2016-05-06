/*
 * Program
 */


import java.awt.Container;

import javax.swing.JFrame;


public class Schedule_Main_3 {
	public static void main(String[] args){
		JFrame frame = new JFrame("Schedule");
		Container window= frame.getContentPane();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		window.add(new Class_List());
		
		window.validate();
		
	}
}
