package version2_0;
import java.awt.Color;


public class Course {
	public static int SCALE = 50; // 1hr = 50 pixels high
	boolean[] day;
	double start;
	double end;
	boolean visible;
	String name, dayString;
	Color color;
	
	/**
	 * Course constructor (Randomly assigns color);
	 * @param course - title of course
	 * @param startTime - time class starts in 24 hr time (1:15 --> 1315, 8:30 = 0830);
	 * @param endTime - time class ends in 24 hr time
	 * @param days - days of the week (MTWRF, leave out letters not relevant)
	 * @param vis - make visible?
	 */
	public Course(String course, String startTime, String endTime, String days, boolean vis){
		this(course, startTime, endTime, days, vis, new Color((int)(Math.random()*150),(int)(Math.random()*150),(int)(Math.random()*150)));
	}
	/**
	 * Course constructor
	 * @param course - title of course
	 * @param startTime - time class starts in 24 hr time (1:15 --> 1315, 8:30 = 0830);
	 * @param endTime - time class ends in 24 hr time
	 * @param days - days of the week (MTWRF, leave out letters not relevant)
	 * @param vis - make visible?
	 * @param c - color
	 */
	public Course(String course, String startTime, String endTime, String days, boolean vis, Color c){
		day = new boolean[5];
		name = course;
		visible = vis;
		dayString = days;
		// get times from strings
		// startTime
		// we'll assume formatting is correct for now
		for (int i = 0; i < startTime.length(); i++){
			if (startTime.charAt(i) == ':'){
				startTime = startTime.substring(0, i) + startTime.substring(i+1, startTime.length());
			}
		}
		if (startTime.length() < 4){
			startTime = "0"+startTime;
		}
		while(startTime.length() < 4){
			startTime+="0";
		}
		
		for (int i = 0; i < endTime.length(); i++){
			if (endTime.charAt(i) == ':'){
				endTime = endTime.substring(0, i) + endTime.substring(i+1, endTime.length());
			}
		}
		if (endTime.length() < 4){
			endTime = "0"+endTime;
		}
		while(endTime.length() < 4){
			endTime+="0";
		}
		// formatting should be fixed now
		
		double hour = Integer.parseInt(startTime.substring(0,2));
		double min = Integer.parseInt(startTime.substring(2,4));
		start = hour+(min/60.0);
		
		// endTime
		hour = Integer.parseInt(endTime.substring(0,2));
		min = Integer.parseInt(endTime.substring(2,4));
		end = hour+(min/60.0);
		
		// get days
		for (int i = 0; i < 5; i++){
			day[i] = false;
		}
		
		for (int i = 0; i < days.length(); i++){
			switch(days.toUpperCase().charAt(i)){
			case 'M':
				day[0] = true;
				break;
			case 'T':
				day[1] = true;
				break;
			case 'W':
				day[2] = true;
				break;
			case 'R':
				day[3]= true;
				break;
			case 'F':
				day[4] = true;
				break;
			}
		}
	}
	
	// getters/setters
	/**
	 * is this class on a certain day of the week?
	 * @param d - day of the week (0=M, 1=T, 2=W, 3=R, 4=F)
	 * @return true if class is on that day
	 */
	public boolean onDay(int d){
		return day[d];
	}
	public boolean isVisible(){
		return visible;
	}
	public double getStartTime(){
		return start;
	}
	public String getStartTimeText(){
		String s="";
		double min;
		s+=(int)start + ":";
		min = (double)(start-(int)start);
		min *= 60;
		s+=min;
		
		return s;
	}
	public double getEndTime(){
		return end;
	}
	public String getEndTimeText(){
		String s="";
		double min;
		s+=(int)end + ":";
		min = (double)(end-(int)end);
		System.out.println(min);
		min *= 60.0;
		System.out.println(min);
		s+=min;
		
		
		return s;
	}
	public Color getColor(){
		return color;
	}
	public String getName(){
		return name;
	}
	public String getExportName(){
		String s = getName();
		while (s.length() < 23){
			s+=" ";
		}
		return s.substring(0,23);
	}
	
	public String toString(){
		return name + "\t" + start+ "\t" + end + "\t" + visible;  
	}
}
