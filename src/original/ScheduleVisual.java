package original;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class ScheduleVisual extends JPanel{
	protected ArrayList<Course> courses;
	protected int dayWidth = 150;
	public ScheduleVisual(ArrayList<Course> c){
		courses = c;
		
		this.setSize(800, 850);
		this.setVisible(true);
		repaint();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		// reserve 25px for days of the week
		g2.drawString("Monday", 110, 20);
		g2.drawString("Tuesday", 260, 20);
		g2.drawString("Wednesday", 410, 20);
		g2.drawString("Thursday", 560, 20);
		g2.drawString("Friday", 710, 20);
		// reserve 100px for time
		for (int i = 8; i < 23; i++){
			g2.drawString(i+":00", 10, (i-7)*Course.SCALE+25);
		}
		
		for (int c = 0; c < courses.size(); c++){
			for (int d = 0; d < 5; d++){
				if (courses.get(c).isVisible() && courses.get(c).onDay(d)){
					g2.setColor(new Color((float)0.0, (float)0.8, (float)0.0, (float)0.7));
					g2.fillRect(100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+25,// subtract 7, cause nothing starts before 8
							dayWidth-10, (int)((courses.get(c).getEndTime()-courses.get(c).getStartTime())*Course.SCALE));
					g2.setColor(Color.black);
					g2.drawRect(100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+25,// subtract 7, cause nothing starts before 8
							dayWidth-10, (int)((courses.get(c).getEndTime()-courses.get(c).getStartTime())*Course.SCALE));
					
					g2.drawString(courses.get(c).getName(), 100 + (d*dayWidth), (int)((courses.get(c).getStartTime()-7.0)*Course.SCALE)+50);
				}
			}
			
			
			
			
		}
	}
}
