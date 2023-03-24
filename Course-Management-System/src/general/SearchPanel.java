package general;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import admin.AdminMain;
import curriculum.CurriculumData;
import instructor.Instructor;
import instructor.InstructorData;
import instructor.InstructorMain;
import instructor.ViewInstructorPanel;
import net.proteanit.sql.DbUtils;
import student.Student;
import student.StudentData;
import student.StudentMain;
import student.ViewStudentPanel;


//Purpose : For searching student of instructor


@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener {

	private JTable table;
	private JScrollPane tableviewscroll;
	private JTextField searchfield;
	private JComboBox<String> curriculumnamecombo;
	private JComboBox<String> semoryearcombo;
	private JComboBox<String> studentandinstructorcombo;

	private JButton searchbutton;
	/**
	 * Create the panel.
	 */
	public SearchPanel(AdminMain am)
	{
		this();
		table.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
					if(studentandinstructorcombo.getSelectedIndex()==0)
					{
						JTable t=(JTable) e.getSource();
						int row=t.getSelectedRow();
						String curriculumcode=table.getValueAt(row,0)+"";
						String  strsem=table.getValueAt(row, 4)+"";
						int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
						String strroll=table.getValueAt(row, 1)+"";
						long rollnumber=Long.parseLong(strroll);
						Student s=new StudentData().getStudentDetails(curriculumcode,sem,rollnumber);
						
						am.viewstudentpanel=new ViewStudentPanel(s,am,am.searchpanel);
						am.viewstudentpanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
					else 
					{
						JTable t=(JTable) e.getSource();
						int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
						Instructor f=new InstructorData().getInstructorInfobyId(fid);
						
						am.viewinstructorpanel=new ViewInstructorPanel(f,am,am.searchpanel);
						am.viewinstructorpanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewinstructorpanel.setLocation(am.panelx,am.panely);
						am.viewinstructorpanel.setVisible(true);
						am.viewinstructorpanel.setFocusable(true);
						am.contentPane.add(am.viewinstructorpanel);
					}
				}
			}
		});
		
		
	}
	public SearchPanel(InstructorMain fm)
	{
		this();
		curriculumnamecombo.setSelectedItem(new CurriculumData().getcurriculumname(fm.f.getCurriculumCode()));
		curriculumnamecombo.setEnabled(false);
		 curriculumnamecombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 semoryearcombo.setSelectedIndex(fm.f.getSemorYear());
		 semoryearcombo.setEnabled(false);
		 semoryearcombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 this.createtablemodel();
		 table.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
						if(studentandinstructorcombo.getSelectedIndex()==0)
						{
							JTable t=(JTable) e.getSource();
							int row=t.getSelectedRow();
							String curriculumcode=table.getValueAt(row,0)+"";
							String  strsem=table.getValueAt(row, 4)+"";
							int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
							String strroll=table.getValueAt(row, 1)+"";
							long rollnumber=Long.parseLong(strroll);
							Student s=new StudentData().getStudentDetails(curriculumcode,sem,rollnumber);
							
							fm.viewstudentpanel=new ViewStudentPanel(s,fm,fm.searchpanel);
							fm.viewstudentpanel.setVisible(true);
							fm.searchpanel.setVisible(false);
							fm.viewstudentpanel.setLocation(fm.panelx,0);
							fm.viewstudentpanel.setVisible(true);
							fm.viewstudentpanel.setFocusable(true);
							fm.contentPane.add(fm.viewstudentpanel);
						}
						else 
						{
							JTable t=(JTable) e.getSource();
							int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
							Instructor f=new InstructorData().getInstructorInfobyId(fid);
							
							fm.viewinstructorpanel=new ViewInstructorPanel(f,fm,fm.searchpanel);
							fm.viewinstructorpanel.setVisible(true);
							fm.searchpanel.setVisible(false);
							fm.viewinstructorpanel.setLocation(fm.panelx,fm.panely);
							fm.viewinstructorpanel.setVisible(true);
							fm.viewinstructorpanel.setFocusable(true);
							fm.contentPane.add(fm.viewinstructorpanel);
						}
					}
				}
			});
			
	}
	public SearchPanel(StudentMain sm)
	{
		this();
		curriculumnamecombo.setSelectedItem(new CurriculumData().getcurriculumname(sm.s.getCurriculumCode()));
		curriculumnamecombo.setEnabled(false);
		 curriculumnamecombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 semoryearcombo.setSelectedIndex(sm.s.getSemorYear());
		 semoryearcombo.setEnabled(false);
		 semoryearcombo.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public void paint(Graphics g) {
		            setForeground(Color.BLACK);
		            setBackground(Color.WHITE);
		            super.paint(g);
		        }
		    });
		 this.createtablemodel();
		 table.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
						if(studentandinstructorcombo.getSelectedIndex()==0)
						{
							JTable t=(JTable) e.getSource();
							int row=t.getSelectedRow();
							String curriculumcode=table.getValueAt(row,0)+"";
							String  strsem=table.getValueAt(row, 4)+"";
							int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
							String strroll=table.getValueAt(row, 1)+"";
							long rollnumber=Long.parseLong(strroll);
							Student s=new StudentData().getStudentDetails(curriculumcode,sem,rollnumber);
							
							sm.viewstudentpanel=new ViewStudentPanel(s,sm,sm.searchpanel);
							sm.viewstudentpanel.setVisible(true);
							sm.searchpanel.setVisible(false);
							sm.viewstudentpanel.setLocation(sm.panelx,0);
							sm.viewstudentpanel.setVisible(true);
							sm.viewstudentpanel.setFocusable(true);
							sm.contentPane.add(sm.viewstudentpanel);
						}
						else 
						{
							JTable t=(JTable) e.getSource();
							int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
							Instructor f=new InstructorData().getInstructorInfobyId(fid);
							
							sm.viewinstructorpanel=new ViewInstructorPanel(f,sm,sm.searchpanel);
							sm.viewinstructorpanel.setVisible(true);
							sm.searchpanel.setVisible(false);
							sm.viewinstructorpanel.setLocation(sm.panelx,sm.panely);
							sm.viewinstructorpanel.setVisible(true);
							sm.viewinstructorpanel.setFocusable(true);
							sm.contentPane.add(sm.viewinstructorpanel);
						}
					}
				}
			});
			
	}
	public SearchPanel() {
		this.setName("Search Panel");
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		tableviewscroll = new JScrollPane();
		tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewscroll.setBounds(10, 194, 1096, 500);
		for(Component c : tableviewscroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(tableviewscroll);
		
		
		
		table = new JTable();
		
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
		table.getTableHeader().setBackground(new Color(32,178,170));
		table.getTableHeader().setForeground(Color.white);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.setModel(DbUtils.resultSetToTableModel(new StudentData().getStudentinfo("")));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);	
		tableviewscroll.setViewportView(table);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		JLabel lblStudentManagement = new JLabel("Search");
		lblStudentManagement.setIcon(null);
		lblStudentManagement.setBounds(10, 38, 224, 44);
		panel.add(lblStudentManagement);
		lblStudentManagement.setBackground(new Color(32, 178, 170));
		lblStudentManagement.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentManagement.setForeground(Color.WHITE);
		lblStudentManagement.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentManagement.setOpaque(true);
		
		studentandinstructorcombo = new JComboBox<String>();
		studentandinstructorcombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Students", "Instructors"}));
		this.arrangeStudentTable();
		studentandinstructorcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		studentandinstructorcombo.setBounds(10, 128, 205, 40);
		studentandinstructorcombo.addActionListener(this);
		panel.add(studentandinstructorcombo);
		
		String curriculumname[]=new CurriculumData().getCurriculumName();
		curriculumname[0]="All Curriculums";
		curriculumnamecombo = new JComboBox<String>(curriculumname);
		
		curriculumnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		curriculumnamecombo.setBounds(225, 128, 255, 40);
		curriculumnamecombo.addActionListener(this);
		
		panel.add(curriculumnamecombo);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		semoryearcombo.setBounds(490, 128, 214, 40);
		semoryearcombo.addActionListener(this);
		panel.add(semoryearcombo);
		
		searchfield = new HintTextField("Search");
		searchfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		searchfield.setForeground(Color.DARK_GRAY);
		searchfield.setBounds(714, 129, 248, 40);
		panel.add(searchfield);
		searchfield.setColumns(10);
		
		searchbutton = new JButton();
		searchbutton.setForeground(new Color(0, 139, 139));
		searchbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		searchbutton.setText("Search");
		searchbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchbutton.setBackground(new Color(255, 255, 255));
		searchbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchbutton.setIcon(new ImageIcon("./assets/search.png"));
		searchbutton.setBounds(972, 129, 114, 40);
		searchbutton.addActionListener(this);
		searchbutton.setFocusable(false);
		panel.add(searchbutton);
		   

	}

	
	public void arrangeStudentTable()
	{
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(300);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
	public void arrangeInstructorTable()
	{
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		DefaultTableCellRenderer cellrenderer=new DefaultTableCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
			
		if(e.getSource()==curriculumnamecombo)
		{
			
			
			if(curriculumnamecombo.getSelectedIndex()==0)
			{
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				
			}
			
			else
			{
			 String curriculum=(String) curriculumnamecombo.getSelectedItem();
			 String semoryear[]=new CurriculumData().getSemorYear(curriculum);
			 semoryear[0]="All "+semoryear[1].substring(0,semoryear[1].indexOf(' ')); 
			 semoryearcombo.setModel(new DefaultComboBoxModel<String>(semoryear));
			}
		 
		}
		if(e.getSource()==searchbutton)
		{
			
			createtablemodel();
		}
	
	}
	public void createtablemodel()
	{
		String searchtext=searchfield.getText().trim();
		if(studentandinstructorcombo.getSelectedIndex()==0)
		{
			String defaultquery="select s.curriculumcode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',c.curriculumname as 'Curriculum Name',concat(c.semoryear,'-',s.semoryear) as 'Sem/Year' from students s left join curriculums c on s.curriculumcode=c.curriculumcode ";
			String query=defaultquery;
			if(curriculumnamecombo.getSelectedIndex()>0)
			{
				String curriculumcode=new CurriculumData().getCurriculumcode(curriculumnamecombo.getSelectedItem()+"");
				query+=" where s.curriculumcode='"+curriculumcode+"'";
				if(semoryearcombo.getSelectedIndex()>0)
				{
					query+=" and s.semoryear="+semoryearcombo.getSelectedIndex();
				}
				
			}
			
			if(!searchtext.isEmpty())
			{
				String searchquery="s.firstname like '"+searchtext+"%' or s.lastname like '"+searchtext+"%' or s.rollnumber like '"+searchtext+"%' ";
				if(!query.contains("where"))
				{
					query+="where "+searchquery;
				}
				else
				{
					query+=" and ( "+searchquery+" ) ";
				}
			
			}
			table.setModel(DbUtils.resultSetToTableModel(new StudentData().searchStudent(query)));
			this.arrangeStudentTable();
		}
		else if(studentandinstructorcombo.getSelectedIndex()==1)
		{
			String defaultquery="select instructorid as 'Instructor ID',instructorname as 'Instructor Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from faculties f ";
			String query=defaultquery;
			if(curriculumnamecombo.getSelectedIndex()>0)
			{
				String curriculumcode=new CurriculumData().getCurriculumcode(curriculumnamecombo.getSelectedItem()+"");
				query+=" where f.curriculumcode='"+curriculumcode+"'";
				if(semoryearcombo.getSelectedIndex()>0)
				{
					query+=" and f.semoryear="+semoryearcombo.getSelectedIndex();
				}
				
			}
			if(!searchtext.equals("Search")&&!searchtext.isEmpty())
			{
				String searchquery=" f.instructorname like '"+searchtext+"%' or f.instructorid like '"+searchtext+"%' ";
				if(!query.contains("where"))
				{
					query+="where "+searchquery;
				}
				else
				{
					query+=" and ( "+searchquery+" ) ";
				}
			
			}
			table.setModel(DbUtils.resultSetToTableModel(new InstructorData().searchInstructor(query)));
			this.arrangeInstructorTable();
		}
	}
}
