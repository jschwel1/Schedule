package original;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Class_List extends JPanel implements ActionListener, FocusListener{
	ArrayList<Course> courseList;
	
	ArrayList<Checkbox> visible;
	ArrayList<JTextField> course;
	ArrayList<JTextField> sTime;
	ArrayList<JTextField> eTime;
	ArrayList<JTextField> days;
	JButton addRow;
	JButton delRow;
	JButton create;
	JButton importData;
	JButton exportData;

	JTextArea area = new JTextArea();
	
	int rows;
	
	
	
	public Class_List(){
		this.setSize(800,800);
		this.setVisible(true);
		
		courseList = new ArrayList<Course>();
		visible = new ArrayList<Checkbox>();
		course = new ArrayList<JTextField>();
		sTime = new ArrayList<JTextField>();
		eTime = new ArrayList<JTextField>();
		days = new ArrayList<JTextField>();
		
		addRow = new JButton("add row");
		addRow.setActionCommand("addRow");
		addRow.addActionListener(this);
		
		delRow = new JButton ("Remove Row");
		delRow.setActionCommand("del row");
		delRow.addActionListener(this);
		
		create = new JButton("Create");
		create.setActionCommand("create");
		create.addActionListener(this);
		
		importData = new JButton("Import");
		importData.setActionCommand("import");
		importData.addActionListener(this);
		
		exportData = new JButton("Export");
		exportData.setActionCommand("export");
		exportData.addActionListener(this);
		
		rows = 3;
		
		
		for(int r = 0; r < rows; r++){
			visible.add(new Checkbox("visible", true));
			course.add(new JTextField(""));
			sTime.add(new JTextField(""));
			eTime.add(new JTextField(""));
			days.add(new JTextField(""));
			
			visible.get(r).addFocusListener(this);
			course.get(r).addFocusListener(this);
			sTime.get(r).addFocusListener(this);
			eTime.get(r).addFocusListener(this);
			days.get(r).addFocusListener(this);
			
		}
		visible.get(0).setState(false);
		visible.get(0).setEnabled(false);
		visible.get(0).setLabel(" ");
		course.get(0).setText("Course");
		course.get(0).setEditable(false);
		sTime.get(0).setText("Start Time (24hr--0000)");
		sTime.get(0).setEditable(false);
		eTime.get(0).setText("End Time (24hr -- 0000");
		eTime.get(0).setEditable(false);
		days.get(0).setText("Days (MTWRF)");
		days.get(0).setEditable(false);
		
		
		this.setLayout(new GridLayout(rows+1, 5));
		for(int r = 0; r < rows; r++){
			this.add(visible.get(r));
			this.add(course.get(r));
			this.add(sTime.get(r));
			this.add(eTime.get(r));
			this.add(days.get(r));
		}
		this.add(addRow);
		this.add(delRow);
		this.add(create);
		this.add(importData);
		this.add(exportData);
		
		this.validate();
		this.revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		 String cmd = "";
		 try{
			 cmd = e.getActionCommand();
		 }catch(Exception ex){
			 System.err.println("no action command");
		 }
		 System.out.println(cmd);
		 if (cmd.equals(addRow.getActionCommand())){
			 rows++;
			 visible.add(new Checkbox("visible", true));
				course.add(new JTextField(""));
				sTime.add(new JTextField(""));
				eTime.add(new JTextField(""));
				days.add(new JTextField(""));
				this.removeAll();
				this.setLayout(new GridLayout(rows+1, 5));
				for(int r = 0; r < rows; r++){
					this.add(visible.get(r));
					this.add(course.get(r));
					this.add(sTime.get(r));
					this.add(eTime.get(r));
					this.add(days.get(r));
				}
				this.add(addRow);
				this.add(delRow);
				this.add(create);
				this.add(importData);
				this.add(exportData);
				
				this.revalidate();
		 }
		 
		 if (cmd.equals(delRow.getActionCommand())){
			 if (rows>2){
				 rows--;
//				 visible.remove(visible.size()-1);
//					course.remove(course.size()-1);
//					sTime.remove(sTime.size()-1);
//					eTime.remove(eTime.size()-1);
//					days.remove(days.size()-1);
					
					
//						this.remove(visible.get(visible.size()-1));
//						this.remove(course.get(course.size()-1));
//						this.remove(sTime.get(sTime.size()-1));
//						this.remove(eTime.get(eTime.size()-1));
//						this.remove(days.get(days.size()-1));
//					 visible.remove(visible.size()-1);
				 int idx = rows;

				 this.setLayout(new GridLayout(rows+1, 5));
					this.remove(visible.get(idx));
					this.remove(course.get(idx));
					this.remove(sTime.get(idx));
					this.remove(eTime.get(idx));
					this.remove(days.get(idx));
					course.remove(idx);
					sTime.remove(idx);
					eTime.remove(idx);
					days.remove(idx);
					
					
//					this.remove(visible.get(1));
//					this.remove(course.get(1));
//					this.remove(sTime.get(1));
//					this.remove(eTime.get(1));
//					this.remove(days.get(1));
						this.setLayout(new GridLayout(rows+1, 5));
						
//					this.add(addRow);
//					this.add(delRow);
//					this.add(create);
//					
					this.revalidate();
			 }
		 }
		 
		 
		 if (cmd.equals(create.getActionCommand())){
			 courseList.clear();
			 for (int i =1; i < rows; i++){
				 try{
					 if (visible.get(i).getState())
						 courseList.add(new Course(course.get(i).getText(), sTime.get(i).getText(), eTime.get(i).getText(), days.get(i).getText(), visible.get(i).getState()));
				 }catch(Exception ex){
					 JOptionPane.showMessageDialog(this, "ERROR at row " + i + "(name: "+course.get(i).getText() +")! Please uncheck an incomplete or erroneous rows");
					 i = rows;
				 }
			 }
		 	
			 JFrame f2 = new JFrame("Schedule visual");
			 Container w2 = f2.getContentPane();
			 f2.setSize(800, 800);
//			 f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 f2.setVisible(true);
			 w2.add(new ScheduleVisual(courseList));
			 System.out.println(courseList);
		 }
		 if (cmd.equals("import")){
			 JFrame f = new JFrame("Import schedule data");
			 Container w = f.getContentPane();
			 JButton analyzeData = new JButton("Import");
			 analyzeData.addActionListener(this);
			 analyzeData.setActionCommand("analyze");
			 f.setSize(500,500);
			 f.setVisible(true);
			 w.setLayout(new GridLayout(2,1));
			 w.add(area);
			 w.add(analyzeData);
			 
		 }
		 if (cmd.equals("analyze")){
			 Scanner s = new Scanner(area.getText());
			 String ncourse, nstart, nend, ndays;
			 s.useDelimiter("\t");
			 while (s.hasNext()){
				 ncourse = s.next();
				 nstart = s.next();
				 nend = s.next();
				 ndays = s.nextLine();
				 
				 rows++;
				 visible.add(new Checkbox("visible", true));
					course.add(new JTextField(ncourse));
					sTime.add(new JTextField(nstart));
					eTime.add(new JTextField(nend));
					days.add(new JTextField(ndays));
					this.removeAll();
					this.setLayout(new GridLayout(rows+1, 5));
					for(int r = 0; r < rows; r++){
						this.add(visible.get(r));
						this.add(course.get(r));
						this.add(sTime.get(r));
						this.add(eTime.get(r));
						this.add(days.get(r));
					}
					this.add(addRow);
					this.add(delRow);
					this.add(create);
					this.add(importData);
					this.add(exportData);
					
					this.revalidate();
				 
			 }
		 }
		 if (cmd.equals("export")){
			 JFrame f = new JFrame("Export schedule data");
			 Container w = f.getContentPane();
			 f.setSize(500,20*rows);
			 f.setVisible(true);
			 w.setLayout(new GridLayout(1,1));
			 w.add(area);
			 // get data
			 String data = "";
			 for (int r = 1; r < course.size(); r++){
				 data += course.get(r).getText() + "\t\t";
				 data += sTime.get(r).getText() + "\t";
				 data += eTime.get(r).getText() + "\t";
				 data += days.get(r).getText() + "\t";
				 data += "\n";
			 }
			 area.setText(data);
			 
		 }
	}


	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent arg0) {
		
	}
	
	
}
