package subject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import admin.AdminMain;
import curriculum.CurriculumData;
import instructor.InstructorMain;
import net.proteanit.sql.DbUtils;
import student.StudentMain;

//Purpose : Displaying all the subject in given curriculum and sem

@SuppressWarnings("serial")
public class SubjectPanel extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private JComboBox<String> curriculumnamecombo;
	private JComboBox<String> semoryearcombo;
	private JButton addsubject;
	private String Curriculumcode[];
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel selectcurriculumlabel;
	private JLabel selectsemlabel;
	private JLabel headerlabel;
	private JButton backbutton;
	
	public SubjectPanel(AdminMain am)
	{
		this();
	}
	public SubjectPanel(StudentMain sm)
	{
		this();
		curriculumnamecombo.setVisible(false);
		semoryearcombo.setVisible(false);
		addsubject.setVisible(false);
		selectsemlabel.setVisible(false);
		selectcurriculumlabel.setVisible(false);
	
		scrollPane.setVisible(true);
		headerlabel.setBounds(10, 0, 1096, 183);
		headerlabel.setText(" Subjects");
		scrollPane.setBounds(10, headerlabel.getY()+headerlabel.getHeight()+20, 1096, this.getHeight()-headerlabel.getHeight()-20);
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		this.createtablemodel(sm.s.getCurriculumCode(),sm.s.getSemorYear() );
		
	}
	public SubjectPanel(InstructorMain fm)
	{
		this();
		curriculumnamecombo.setVisible(false);
		semoryearcombo.setVisible(false);
		addsubject.setVisible(false);
		selectsemlabel.setVisible(false);
		selectcurriculumlabel.setVisible(false);
	
		scrollPane.setVisible(true);
		headerlabel.setBounds(10, 0, 1096, 183);
		headerlabel.setText(" Subjects");
		scrollPane.setBounds(10, headerlabel.getY()+headerlabel.getHeight()+20, 1096, this.getHeight()-headerlabel.getHeight()-20);
		headerlabel.setHorizontalAlignment(JLabel.LEFT);
		this.createtablemodel(fm.f.getCurriculumCode(),fm.f.getSemorYear() );
		
	}
	public SubjectPanel(InstructorMain fm,JComponent lastpanel)
	{
		this(fm);
		headerlabel.setLayout(null);
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10,141, 88, 36);
		headerlabel.add(backbutton);
		
		backbutton.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
	public SubjectPanel(StudentMain sm,JComponent lastpanel)
	{
		this(sm);
		headerlabel.setLayout(null);
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		headerlabel.add(backbutton);
		
		backbutton.addActionListener(e->
		{
			this.setVisible(false);
			lastpanel.setVisible(true);
		});
	}
			
	private SubjectPanel()
	{
		new Timer(100,this);
//		timer.start();
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		headerlabel = new JLabel("Subject Management");
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headerlabel.setBounds(10, 0, 1096, 66);
		headerlabel.setOpaque(true);
		add(headerlabel);
		
		selectcurriculumlabel = new JLabel("Select Curriculum  ");
		selectcurriculumlabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectcurriculumlabel.setForeground(Color.DARK_GRAY);
		selectcurriculumlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		selectcurriculumlabel.setBounds(30, 89, 248, 42);
		add(selectcurriculumlabel);
		
		 curriculumnamecombo = new JComboBox<String>(new CurriculumData().getCurriculumName());
		curriculumnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		curriculumnamecombo.setBackground(Color.WHITE);
		curriculumnamecombo.setFocusable(false);
		curriculumnamecombo.addActionListener(this);
		curriculumnamecombo.setBounds(300, 88, 806, 42);
		add(curriculumnamecombo);
		
		selectsemlabel = new JLabel("Select Semester/Year  ");
		selectsemlabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectsemlabel.setBackground(Color.DARK_GRAY);
		selectsemlabel.setForeground(Color.DARK_GRAY);
		selectsemlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		selectsemlabel.setBounds(30, 165, 248, 40);
		add(selectsemlabel);
		
		 semoryearcombo = new JComboBox<String>();
		 semoryearcombo.setMaximumRowCount(16);
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		semoryearcombo.setBackground(Color.WHITE);
		semoryearcombo.setFocusable(false);
		semoryearcombo.addActionListener(this);
		semoryearcombo.setBounds(300, 165, 806, 42);
		add(semoryearcombo);
		
		Curriculumcode=new CurriculumData().getCurriculumcode();
		
		addsubject = new JButton("Add New Subject");
		addsubject.setBorder(new EmptyBorder(0, 0, 0, 0));
		addsubject.addActionListener(this);
		addsubject.setForeground(new Color(255, 255, 255));
		addsubject.setBackground(new Color(32, 178, 170));
		addsubject.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addsubject.setBounds(937, 242, 169, 37);
		addsubject.setVisible(false);
		addsubject.setFocusable(false);
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(addsubject);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 311, 1096, 361);
		for(Component c:scrollPane.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		table.setBackground(Color.white);
		table.setRowHeight(40);
		
		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setDragEnabled(false);
		
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);		
		
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		if(curriculumnamecombo.getSelectedIndex()==0 ||semoryearcombo.getSelectedIndex()==0)
		{
			scrollPane.setVisible(false);
		}
			if(e.getSource()==curriculumnamecombo)
			{
				
				
				scrollPane.setVisible(false);
				addsubject.setVisible(false);
				if(curriculumnamecombo.getSelectedIndex()==0)
				{
					semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
				}
				else
				{
				 String curriculum=(String) curriculumnamecombo.getSelectedItem();

				 semoryearcombo.setModel(new DefaultComboBoxModel<String>(new CurriculumData().getSemorYear(curriculum)));
				}
			 
			}
			 if(e.getSource()==semoryearcombo)
			 {
				if(curriculumnamecombo.getSelectedIndex()>0 && semoryearcombo.getSelectedIndex()>0)
				{
				 scrollPane.setVisible(true);
				 int sem=semoryearcombo.getSelectedIndex();
				 int index=curriculumnamecombo.getSelectedIndex();
				 this.createtablemodel(Curriculumcode[index-1], sem);
					
				}
				else if(semoryearcombo.getSelectedIndex()==0)
				{
					scrollPane.setVisible(false);
					addsubject.setVisible(false);
				}
				
				else
				{
					scrollPane.setVisible(false);
				}
					
				 
			 }
			 if(curriculumnamecombo.getSelectedIndex()>0 && semoryearcombo.getSelectedIndex()>0)
			 {
				 addsubject.setVisible(true);
			 }

			 if(e.getSource()==addsubject)
			 {
				 int sem=semoryearcombo.getSelectedIndex();
				 int index=curriculumnamecombo.getSelectedIndex();
				 AddSubjectDialog sd=new AddSubjectDialog(Curriculumcode[index-1],sem,table);
				 sd.setLocationRelativeTo(null);
				 sd.setVisible(true);
				 
				 scrollPane.setVisible(true);
				
			 }
	}
	public void createtablemodel(String curriculumcode,int sem)
	{
		 ResultSet st=new SubjectData().getSubjectinfo(curriculumcode,sem);
		 table.setModel(DbUtils.resultSetToTableModel(st));
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		 table.getColumnModel().getColumn(0).setMaxWidth(200);
		
			table.getColumnModel().getColumn(1).setMaxWidth(400);
			table.getColumnModel().getColumn(2).setMaxWidth(200);
			table.getColumnModel().getColumn(3).setMaxWidth(200);
			table.getColumnModel().getColumn(4).setMaxWidth(200);
			table.getColumnModel().getColumn(5).setMaxWidth(200);
			
	}

}
