package instructor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import admin.AdminMain;
import curriculum.CurriculumData;
import general.ChangePasswordDialog;
import student.StudentMain;
import subject.AssignSubjectDialog;

//Purpose : Displaying all the details of  instructor

@SuppressWarnings("serial")
public class ViewInstructorPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public JComponent lastpanel;
	private JButton assignsubjectbutton;
	private JButton backbutton;
	private JButton editdetailsbutton;
	/**
	 * @wbp.parser.constructor
	 */
	private ViewInstructorPanel(Instructor f)
	{
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 188);
		add(panel);
		panel.setLayout(null);
		JLabel lblDisplayingStudentDetails = new JLabel(f.getInstructorName());
		
		lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
		lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblDisplayingStudentDetails.setBounds(661, 11, 415, 44);
		panel.add(lblDisplayingStudentDetails);
		
		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setBounds(919, 141, 153, 35);
		
		panel.add(editdetailsbutton);
		
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		panel.add(backbutton);
		
		JLabel lblLastLogin = new JLabel("Last Login : ");
		if(f.getLastLogin()==null||f.getLastLogin().isEmpty())
		{
			lblLastLogin.setText("Last Login : No Login");
		}
		else 
		{
			lblLastLogin.setText("Last Login : "+f.getLastLogin());
		}
		lblLastLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastLogin.setForeground(Color.WHITE);
		lblLastLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastLogin.setBounds(719, 57, 357, 19);
		panel.add(lblLastLogin);
		
		JLabel lblStudentDetails = new JLabel("Instructor Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);
		
		assignsubjectbutton = new JButton("Assign Subject");
		assignsubjectbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		assignsubjectbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		assignsubjectbutton.setFocusable(false);
		assignsubjectbutton.setForeground(new Color(0, 139, 139));
		assignsubjectbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		assignsubjectbutton.setBackground(Color.WHITE);
		assignsubjectbutton.setBounds(743, 141, 153, 35);
		panel.add(assignsubjectbutton);
		
		JLabel instructoridlbl = new JLabel("Instructor ID   ");
		instructoridlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		instructoridlbl.setBackground(new Color(255, 255, 255));
		instructoridlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		instructoridlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		instructoridlbl.setOpaque(true);
		instructoridlbl.setBounds(309, 66+150, 274, 48);
		add(instructoridlbl);
		
		JLabel instructornamelbl = new JLabel("Instructor Name   ");
		instructornamelbl.setOpaque(true);
		instructornamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		instructornamelbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		instructornamelbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		instructornamelbl.setBackground(Color.WHITE);
		instructornamelbl.setBounds(309, 113+150, 274, 48);
		add(instructornamelbl);
		
		JLabel lblAddress = new JLabel("Address   ");
		lblAddress.setOpaque(true);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(309, 160+150, 274, 48);
		add(lblAddress);
		
		JLabel lblEmailId = new JLabel("Email ID  ");
		lblEmailId.setOpaque(true);
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setBackground(Color.WHITE);
		lblEmailId.setBounds(309, 207+150, 274, 48);
		add(lblEmailId);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth ");
		lblDateOfBirth.setOpaque(true);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDateOfBirth.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDateOfBirth.setBackground(Color.WHITE);
		lblDateOfBirth.setBounds(309, 254+150, 274, 48);
		add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number ");
		lblContactNumber.setOpaque(true);
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblContactNumber.setBackground(Color.WHITE);
		lblContactNumber.setBounds(309, 300+150, 274, 48);
		add(lblContactNumber);
		
		JLabel qualificationlbl = new JLabel("Qualification   ");
		qualificationlbl.setOpaque(true);
		qualificationlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		qualificationlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		qualificationlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		qualificationlbl.setBackground(Color.WHITE);
		qualificationlbl.setBounds(20, 359+150, 291, 48);
		add(qualificationlbl);
		
		JLabel curriculumlbl = new JLabel("Curriculum   ");
		curriculumlbl.setOpaque(true);
		curriculumlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		curriculumlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		curriculumlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		curriculumlbl.setBackground(Color.WHITE);
		curriculumlbl.setBounds(20, 405+150, 291, 48);
		add(curriculumlbl);
		
		JLabel semoryearlbl = new JLabel("Semester/Year    ");
		semoryearlbl.setOpaque(true);
		semoryearlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		semoryearlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		semoryearlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		semoryearlbl.setBackground(Color.WHITE);
		semoryearlbl.setBounds(582, 405+150, 239, 48);
		add(semoryearlbl);
		
		JLabel lblsubject = new JLabel("Subject    ");
		lblsubject.setOpaque(true);
		lblsubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsubject.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblsubject.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblsubject.setBackground(Color.WHITE);
		lblsubject.setBounds(20, 452+150, 291, 48);
		add(lblsubject);
		
		JLabel positionlbl = new JLabel("Position    ");
		positionlbl.setOpaque(true);
		positionlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		positionlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		positionlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		positionlbl.setBackground(Color.WHITE);
		positionlbl.setBounds(582, 452+150, 239, 48);
		add(positionlbl);
		
		JLabel instructoridlabel = new JLabel("  "+f.getInstructorId());
		instructoridlabel.setOpaque(true);
		instructoridlabel.setHorizontalAlignment(SwingConstants.LEFT);
		instructoridlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		instructoridlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		instructoridlabel.setBackground(Color.WHITE);
		instructoridlabel.setBounds(582, 66+150, 523, 48);
		add(instructoridlabel);
		
		JLabel instructornamelabel = new JLabel("  "+f.getInstructorName());
		instructornamelabel.setOpaque(true);
		instructornamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		instructornamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		instructornamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		instructornamelabel.setBackground(Color.WHITE);
		instructornamelabel.setBounds(582, 113+150, 523, 48);
		add(instructornamelabel);
		
		JLabel addresslabel = new JLabel("  "+f.getAddress());
		addresslabel.setOpaque(true);
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		addresslabel.setBackground(Color.WHITE);
		addresslabel.setBounds(582, 160+150, 523, 48);
		add(addresslabel);
		
		JLabel emailidlabel = new JLabel("  "+f.getEmailId());
		emailidlabel.setOpaque(true);
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		emailidlabel.setBackground(Color.WHITE);
		emailidlabel.setBounds(582, 207+150, 523, 48);
		add(emailidlabel);
		
		JLabel dateofbirthlabel = new JLabel("  "+f.getBirthDate());
		dateofbirthlabel.setOpaque(true);
		dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateofbirthlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		dateofbirthlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		dateofbirthlabel.setBackground(Color.WHITE);
		dateofbirthlabel.setBounds(582, 254+150, 523, 48);
		add(dateofbirthlabel);
		
		JLabel contactnumberlabel = new JLabel("  "+f.getContactNumber());
		contactnumberlabel.setOpaque(true);
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contactnumberlabel.setBackground(Color.WHITE);
		contactnumberlabel.setBounds(582, 300+150, 523, 48);
		add(contactnumberlabel);
		
		JLabel qualificationlabel = new JLabel("  "+f.getQualification());
		qualificationlabel.setOpaque(true);
		qualificationlabel.setHorizontalAlignment(SwingConstants.LEFT);
		qualificationlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		qualificationlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		qualificationlabel.setBackground(Color.WHITE);
		qualificationlabel.setBounds(309, 359+150, 274, 48);
		add(qualificationlabel);
		
		JLabel curriculumnamelabel = new JLabel();
		if(f.getCurriculumCode()==null ||f.getCurriculumCode().equals("Not Assigned"))
		{
		curriculumnamelabel.setText("  "+f.getCurriculumCode());	
		}
		else
		{
		curriculumnamelabel.setText("  "+f.getCurriculumName());	
		}
		
		curriculumnamelabel.setOpaque(true);
		curriculumnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		curriculumnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		curriculumnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		curriculumnamelabel.setBackground(Color.WHITE);
		curriculumnamelabel.setBounds(309, 405+150, 274, 48);
		add(curriculumnamelabel);
		
		JLabel semoryearlabel = new JLabel();
		if(f.getCurriculumCode()==null || f.getCurriculumCode().equals("Not Assigned") )
		{
			semoryearlabel.setText("  Not Assigned");
		}
		else
		{
			semoryearlabel.setText("  "+new CurriculumData().getsemoryear(f.getCurriculumCode())+"-"+f.getSemorYear()+" "+" ("+f.getCurriculumCode()+")");
		}
		semoryearlabel.setOpaque(true);
		semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
		semoryearlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		semoryearlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		semoryearlabel.setBackground(Color.WHITE);
		semoryearlabel.setBounds(820, 405+150, 285, 48);
		add(semoryearlabel);
		
		JLabel subjectlabel = new JLabel("  "+f.getSubject());
		subjectlabel.setOpaque(true);
		subjectlabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		subjectlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		subjectlabel.setBackground(Color.WHITE);
		subjectlabel.setBounds(309, 452+150, 274, 48);
		add(subjectlabel);
		
		JLabel postionlabel = new JLabel("  "+f.getPosition());
		postionlabel.setOpaque(true);
		postionlabel.setHorizontalAlignment(SwingConstants.LEFT);
		postionlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		postionlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		postionlabel.setBackground(Color.WHITE);
		postionlabel.setBounds(820, 452+150, 285, 48);
		add(postionlabel);
		
		JLabel profilepiclabel = new JLabel();
		profilepiclabel.setBounds(20, 66+150, 250, 270);
		add(profilepiclabel);
				profilepiclabel.setIcon(new ImageIcon(f.getProfilePic(250, 270)));
				profilepiclabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
				profilepiclabel.setOpaque(true);
				profilepiclabel.setBackground(new Color(240, 248, 255));
				profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
				JLabel lblsemoryear = new JLabel("Experience   ");
				lblsemoryear.setOpaque(true);
				lblsemoryear.setHorizontalAlignment(SwingConstants.RIGHT);
				lblsemoryear.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
				lblsemoryear.setBorder(new LineBorder(new Color(192, 192, 192)));
				lblsemoryear.setBackground(Color.WHITE);
				lblsemoryear.setBounds(582, 359+150, 239, 48);
				add(lblsemoryear);
				
				JLabel experiencelabel = new JLabel("  "+f.getExperience());
				experiencelabel.setOpaque(true);
				experiencelabel.setHorizontalAlignment(SwingConstants.LEFT);
				experiencelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
				experiencelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
				experiencelabel.setBackground(Color.WHITE);
				experiencelabel.setBounds(820, 359+150, 285, 48);
				add(experiencelabel);
				
				
	}
	public ViewInstructorPanel(Instructor f, AdminMain am,JComponent lastpanel)
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		JLabel lblJoinedDate = new JLabel("Joined Date    ");
		lblJoinedDate.setOpaque(true);
		lblJoinedDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJoinedDate.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblJoinedDate.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblJoinedDate.setBackground(Color.WHITE);
		lblJoinedDate.setBounds(20, 649, 291, 48);
		add(lblJoinedDate);
		
		JLabel joineddatelabel = new JLabel("  "+f.getJoinedDate());
		joineddatelabel.setOpaque(true);
		joineddatelabel.setHorizontalAlignment(SwingConstants.LEFT);
		joineddatelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		joineddatelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		joineddatelabel.setBackground(Color.WHITE);
		joineddatelabel.setBounds(309, 649, 274, 48);
		add(joineddatelabel);
		
		JLabel lblPassword = new JLabel("Password    ");
		lblPassword.setOpaque(true);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(582, 649, 239, 48);
		add(lblPassword);
		
		JLabel passwordlabel = new JLabel("  "+f.getPassword());
		passwordlabel.setOpaque(true);
		passwordlabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		passwordlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		passwordlabel.setBackground(Color.WHITE);
		passwordlabel.setBounds(820, 649, 285, 48);
		add(passwordlabel);
		
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewinstructorpanel.setVisible(false);
				if(lastpanel.getName().equals("Instructor Panel"))
				{
					if(am.instructorpanel.viewbutton.getText().equals("Photo View"))
					{
						am.instructorpanel.createtablemodel();
					}
					else
					{
						am.instructorpanel.createphotoviewpanel();
					}
					am.instructorpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					am.searchpanel.createtablemodel();
					am.searchpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Users Panel"))
				{
					am.userspanel.createtablemodel();
					am.userspanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				AddInstructorDialog ad=new AddInstructorDialog(am,f);
				ad.setLocationRelativeTo(null);
				ad.setVisible(true);
				
			}
	
		}
		
		);
		assignsubjectbutton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				AssignSubjectDialog as=new AssignSubjectDialog(f,am);
				as.setLocation(450, 100);
				as.setVisible(true);
				
			}
		});
		
	}
	public ViewInstructorPanel(Instructor f, InstructorMain fm, JComponent lastpanel) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setVisible(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fm.viewinstructorpanel.setVisible(false);
				if(lastpanel.getName().equals("Instructor Panel"))
				{
					if(fm.instructorpanel.viewbutton.getText().equals("Photo View"))
					{
						fm.instructorpanel.createtablemodel();
					}
					else
					{
						fm.instructorpanel.createphotoviewpanel();
					}
					fm.instructorpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					fm.searchpanel.createtablemodel();
					fm.searchpanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
	}
	public ViewInstructorPanel(Instructor f, InstructorMain fm) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setText("Change Password");
		backbutton.setVisible(false);
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
					ChangePasswordDialog cp=new ChangePasswordDialog(f);
					cp.setLocationRelativeTo(null);
					cp.setVisible(true);
				
			}
	
		}
		);

	}
	
	
	public ViewInstructorPanel(Instructor f, StudentMain sm, JComponent lastpanel)
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setVisible(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sm.viewinstructorpanel.setVisible(false);
				if(lastpanel.getName().equals("Instructor Panel"))
				{
					if(sm.instructorpanel.viewbutton.getText().equals("Photo View"))
					{
						sm.instructorpanel.createtablemodel();
					}
					else
					{
						sm.instructorpanel.createphotoviewpanel();
					}
					sm.instructorpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					sm.searchpanel.createtablemodel();
					sm.searchpanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
	}
}
