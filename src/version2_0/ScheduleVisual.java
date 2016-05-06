package version2_0;
/*
 * File: ScheduleVisual
 * Written by: Jacob Schwell
 * This is the part of the schedule program that creates the visual schedule.
 * 
 * schedule
 * 
 * Update: 11/8/2014
 * 	- Added vertical and horizontal lines
 * 	- Adjusted the screen size (here and in Class_List.java) to better fit the schedule
 * 
 * Update: 11/10/2014
 * 	- If two classes overlap, set their colors to transparent red
 * 
 * next update:
 * - Times on blocks
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class ScheduleVisual extends JPanel implements ActionListener{
	protected ArrayList<Course> courses;
	protected Rectangle[][] courseBlock;
	protected int dayWidth = 150;
	protected boolean startScreen = true;
	protected Timer clock = new Timer(30, this);
	protected long openTime;
	private int loadTime = 8000; // milliseconds

	Font startUpL1, startUpL2, dayFont, timeFont;
	
	public ScheduleVisual(ArrayList<Course> c, boolean fl){
		courses = c;
		clock.start();
		openTime = System.currentTimeMillis();
		courseBlock = new Rectangle[5][courses.size()];
		buildMatrix();
		
		startUpL1 = new Font(Font.SERIF, Font.BOLD, 30);
		startUpL2 = new Font(Font.SERIF, Font.BOLD, 20);
		dayFont = new Font(Font.SANS_SERIF,Font.BOLD, 20);
		timeFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		
		if (fl)
			loadTime = 6000;
		else
			loadTime = 1000;
		
		this.setSize(845, 950);
		this.setVisible(true);
		repaint();
	}
	
	public void buildMatrix(){
		
		for (int c = 0; c < courses.size(); c++){
			for (int d = 0; d < 5; d++){
				if (courses.get(c).isVisible() && courses.get(c).onDay(d)){
					courseBlock[d][c] = new Rectangle(95 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+25,// subtract 7, cause nothing starts before 8
							dayWidth, (int)((courses.get(c).getEndTime()-courses.get(c).getStartTime())*Course.SCALE));
					
				}
				else
					courseBlock[d][c] = new Rectangle(0,0,0,0);
			}
		}		
	}
	
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		boolean overlap;
		g2.setColor(new Color(200,200,200));
		g2.fillRect(0,0,900,900);
		g2.setColor(new Color(20,20,20));
		// reserve 25px for days of the week
		g2.setFont(dayFont);
		g2.drawString("Monday", 110, 20);
		g2.drawLine(95,0,95,900);
		g2.drawString("Tuesday", 260, 20);
		g2.drawLine(245,0,245,900);
		g2.drawString("Wednesday", 410, 20);
		g2.drawLine(395,0,395,900);
		g2.drawString("Thursday", 560, 20);
		g2.drawLine(545,0,545,900);
		g2.drawString("Friday", 710, 20);
		g2.drawLine(695,0,695,900);
		g2.drawLine(845,0,845,900);
		// reserve 100px for time

		g2.setFont(timeFont);
		for (int i = 8; i < 25; i++){
			g2.setColor(new Color(20,20,20));
			g2.drawString(i+":00", 10, (i-7)*Course.SCALE+25);
			// horizontal time lines
			g2.setColor(new Color((float).4,(float).4,(float).4,(float).5));
			g2.drawLine(95,(i-7)*Course.SCALE+25, 845,(i-7)*Course.SCALE+25);
			
		}
		
		for (int c = 0; c < courses.size(); c++){
			overlap = false;
			for (int d = 0; d < 5; d++){
				if (courses.get(c).isVisible() && courses.get(c).onDay(d)){
					// set color, then check for overlaping courses
					g2.setColor(new Color((float)0.0, (float)0.8, (float)0.0, (float).7));
					
					for (int row = 0; row < c; row++){
						if (courseBlock[d][c].intersects(courseBlock[d][row])){
							overlap = true;
							System.out.println("a - " + c + "   " + d);
						}
					}
					for (int row = c+1; row < courses.size(); row++){
						if (courseBlock[d][c].intersects(courseBlock[d][row])){
							overlap = true;
							System.out.println("a - " + c + "   " + d);
						}
					}
					// if courses overlapped, set the color to red
					if (overlap)
						g2.setColor(new Color((float).8, (float).0, (float).0, (float).5));
					
					
					
					
					g2.fill(courseBlock[d][c]);
					g2.setColor(Color.black);
					g2.draw(courseBlock[d][c]);
					
//					g2.fillRect(100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+25,// subtract 7, cause nothing starts before 8
//							dayWidth-10, (int)((courses.get(c).getEndTime()-courses.get(c).getStartTime())*Course.SCALE));
//					g2.setColor(Color.black);
//					g2.drawRect(100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+25,// subtract 7, cause nothing starts before 8
//							dayWidth-10, (int)((courses.get(c).getEndTime()-courses.get(c).getStartTime())*Course.SCALE));
					
					
					// change x value from  100 + (d*dayWidth) to  105 + (d*dayWidth)
					g2.drawString(courses.get(c).getName(), 102 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+50);
//					g2.drawString(courses.get(c).getStartTimeText() + " - ", 100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+80);
//					g2.drawString(courses.get(c).getEndTimeText()+"", 130 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+100);
				}
			}	
		}
		
		// startUp screen
		if (startScreen){
			double r = (double)((System.currentTimeMillis()-openTime)/(double)loadTime)*30.0;
			double gr = (double)((System.currentTimeMillis()-openTime)/(double)loadTime)*150.0;
			double b = (double)((System.currentTimeMillis()-openTime)/(double)loadTime)*30.0;
			g2.setColor(Color.black);
			g2.fillRect(0,0,900,900);
			g2.setColor(new Color((int)r,(int)gr,(int)b));
			g2.setFont(startUpL1);
			g2.drawString("Schedule Key is loading your schedule", 150, 300);
			g2.setFont(startUpL2);
			g2.drawString("Brought to you by Jacob Schwell", 200, 350);
			
			double l = (double)((System.currentTimeMillis()-openTime)/(double)loadTime)*145.0;
			g2.setColor(new Color((int)r,(int)b,(int)gr));
			g2.fillRect((int)((845.0-l)/2.0), 500, (int)l, 40);
			g2.setColor(Color.gray);
			g2.drawRect(350, 500, 145, 40);
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(System.currentTimeMillis() - openTime);
		if (System.currentTimeMillis() - openTime > loadTime){
			startScreen = false;
			clock.stop();
			repaint();
		}
		repaint();
	}
}
