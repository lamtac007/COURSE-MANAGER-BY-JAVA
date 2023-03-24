package general;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import admin.Admin;
import curriculum.CurriculumData;
import instructor.Instructor;
import instructor.InstructorData;
import student.Student;
import student.StudentData;
import subject.SubjectData;

//Purpose : Home Page

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private JPanel homeheaderpanel;
	private JLabel totalstudentlabel, totalinstructorslabel, totalcurriculumlabel, totallectureslabel;
	public JLabel lastloginlabel;
	private JLabel timedifflabel;
	private JLabel welcomelabel;
	private JLabel totalnotificationlabel;
	private JPanel notificationpanel;
	private JPanel curriculumspanel;
	private JPanel instructorspanel;
	private JPanel studentspanel;
	int pos[]= {20,294,568,842};
	private JPanel subjectpanel;
	/**
	 * Create the panel.
	 */
	private HomePanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		
		
				

		notificationpanel = new JPanel();
		notificationpanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		notificationpanel.setBounds(20, 244, 253, 247);
		add(notificationpanel);
		notificationpanel.setBackground(new Color(255, 255, 255));
		notificationpanel.setLayout(null);
		notificationpanel.setVisible(false);

		totalnotificationlabel = new JLabel("0");
		totalnotificationlabel.setForeground(new Color(128, 128, 128));
		totalnotificationlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalnotificationlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalnotificationlabel.setBounds(10, 174, 233, 35);
		notificationpanel.add(totalnotificationlabel);

		JLabel lblNotification = new JLabel("Notification");
		lblNotification.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNotification.setForeground(new Color(128, 128, 128));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setHorizontalTextPosition(JLabel.CENTER);
		lblNotification.setVerticalTextPosition(JLabel.BOTTOM);
		lblNotification.setBounds(10, 31, 233, 142);
		notificationpanel.add(lblNotification);
		lblNotification.setIcon(new ImageIcon(".//assets//notificationhomepage.png"));
		
		curriculumspanel = new JPanel();
		curriculumspanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		curriculumspanel.setBounds(20, 244, 253, 247);
		add(curriculumspanel);
		curriculumspanel.setBackground(new Color(255, 255, 255));
		curriculumspanel.setLayout(null);
		
		totalcurriculumlabel = new JLabel("0");
		totalcurriculumlabel.setForeground(new Color(128, 128, 128));
		totalcurriculumlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalcurriculumlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalcurriculumlabel.setBounds(10, 174, 233, 35);
		curriculumspanel.add(totalcurriculumlabel);
		
		JLabel lblCurriculums = new JLabel("Curriculums");
		lblCurriculums.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblCurriculums.setForeground(new Color(128, 128, 128));
		lblCurriculums.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurriculums.setHorizontalTextPosition(JLabel.CENTER);
		lblCurriculums.setVerticalTextPosition(JLabel.BOTTOM);
		lblCurriculums.setBounds(10, 31, 233, 142);
		curriculumspanel.add(lblCurriculums);
		lblCurriculums.setIcon(new ImageIcon(".//assets//curriculumshomepage.png"));

		studentspanel = new JPanel();
		studentspanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		studentspanel.setLayout(null);
		studentspanel.setBackground(Color.WHITE);
		studentspanel.setBounds(294, 244, 253, 247);
		add(studentspanel);

		totalstudentlabel = new JLabel("0");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totalstudentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalstudentlabel.setForeground(Color.GRAY);

		totalstudentlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalstudentlabel.setBounds(10, 174, 233, 35);
		studentspanel.add(totalstudentlabel);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setForeground(Color.GRAY);
		lblStudents.setIcon(null);
		lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudents.setBounds(10, 32, 233, 144);
		lblStudents.setHorizontalTextPosition(JLabel.CENTER);
		lblStudents.setVerticalTextPosition(JLabel.BOTTOM);
		studentspanel.add(lblStudents);
		lblStudents.setIcon(new ImageIcon(".//assets//studenthomepage.png"));
		
		instructorspanel = new JPanel();
		instructorspanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		instructorspanel.setLayout(null);
		instructorspanel.setBackground(Color.WHITE);
		instructorspanel.setBounds(568, 244, 253, 247);
		add(instructorspanel);

		totalinstructorslabel = new JLabel("0");
		totalinstructorslabel.setBackground(Color.WHITE);
		totalinstructorslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalinstructorslabel.setForeground(Color.GRAY);
		totalinstructorslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalinstructorslabel.setBounds(10, 174, 233, 35);
		instructorspanel.add(totalinstructorslabel);

		JLabel lblInstructors = new JLabel("Instructors");
		lblInstructors.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructors.setForeground(Color.GRAY);
		lblInstructors.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblInstructors.setBounds(10, 34, 233, 140);
		lblInstructors.setHorizontalTextPosition(JLabel.CENTER);
		lblInstructors.setVerticalTextPosition(JLabel.BOTTOM);
		instructorspanel.add(lblInstructors);
		lblInstructors.setIcon(new ImageIcon(".//assets//instructorhomepage.png"));
		
		subjectpanel = new JPanel();
		subjectpanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		subjectpanel.setLayout(null);
		subjectpanel.setBackground(Color.WHITE);
		subjectpanel.setBounds(842, 244, 253, 247);
		add(subjectpanel);

		totallectureslabel = new JLabel("0");

		totallectureslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totallectureslabel.setForeground(Color.GRAY);
		totallectureslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totallectureslabel.setBounds(10, 174, 233, 35);
		subjectpanel.add(totallectureslabel);

		JLabel lblLectures = new JLabel("Subjects");
		lblLectures.setHorizontalAlignment(SwingConstants.CENTER);
		lblLectures.setForeground(Color.GRAY);
		lblLectures.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblLectures.setBounds(10, 40, 233, 141);
		lblLectures.setIconTextGap(10);
		lblLectures.setHorizontalTextPosition(JLabel.CENTER);
		lblLectures.setVerticalTextPosition(JLabel.BOTTOM);
		subjectpanel.add(lblLectures);
		try {
			Image image=ImageIO.read(new File(".//assets//subjects2.png"));
			lblLectures.setIcon(new ImageIcon(image.getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();         
		}

		homeheaderpanel = new JPanel();
		homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		homeheaderpanel.setBackground(new Color(32, 178, 170));
		homeheaderpanel.setLayout(null);
		homeheaderpanel.setBounds(10, 0, 1096, 279);
		add(homeheaderpanel);

		welcomelabel = new JLabel("Welcome");
		welcomelabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setBounds(10, 11, 1076, 45);
		homeheaderpanel.add(welcomelabel);

		JLabel lblHome = new JLabel("Home Page");
		lblHome.setIcon(null);
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblHome.setBounds(10, 97, 377, 45);
		homeheaderpanel.add(lblHome);

		lastloginlabel = new JLabel("Last Login : First Login");
		lastloginlabel.setBackground(Color.WHITE);
		lastloginlabel.setForeground(Color.WHITE);
		lastloginlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastloginlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastloginlabel.setBounds(20, 47, 1066, 30);
		homeheaderpanel.add(lastloginlabel);

		timedifflabel = new JLabel("");
		timedifflabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timedifflabel.setForeground(Color.WHITE);
		timedifflabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		timedifflabel.setBackground(Color.WHITE);
		timedifflabel.setBounds(599, 75, 486, 19);
		homeheaderpanel.add(timedifflabel);

	}

	public HomePanel(Admin a) {
		this();
		totalinstructorslabel.setText(new InstructorData().getTotalInstructor() + "");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totalcurriculumlabel.setText(new CurriculumData().getTotalCurriculum() + "");
		welcomelabel.setText("Welcome Adminstrator");
		totallectureslabel.setText(new SubjectData().getTotalSubject() + "");
	}

	public HomePanel(Instructor f) {
		this();
		totalinstructorslabel.setText(new InstructorData().getInstructor(f.getCurriculumCode(), f.getSemorYear()) + "");
		totalstudentlabel.setText(new StudentData().getTotalStudentInCurriculum(f.getCurriculumCode(), f.getSemorYear()) + "");
		totalnotificationlabel.setText(""+new NotificationData().getUnreadNotification(f.getInstructorId()+"", "Instructor", f.getCurriculumCode(), f.getSemorYear(),f.getJoinedDate()));
		curriculumspanel.setVisible(false);
		notificationpanel.setVisible(true);
		welcomelabel.setText("Welcome " + f.getInstructorName());
		totallectureslabel.setText(new SubjectData().getTotalSubjectinCurriculum(f.getCurriculumCode(), f.getSemorYear()) + "");

		studentspanel.setLocation(pos[0], studentspanel.getY());
		instructorspanel.setLocation(pos[1], instructorspanel.getY());
		subjectpanel.setLocation(pos[2], subjectpanel.getY());
		notificationpanel.setLocation(pos[3], notificationpanel.getY());
		
	}

	public HomePanel(Student s) {
		this();
		totalinstructorslabel.setText(new InstructorData().getInstructor(s.getCurriculumCode(), s.getSemorYear()) + "");
		totalstudentlabel.setText(new StudentData().getTotalStudentInCurriculum(s.getCurriculumCode(), s.getSemorYear()) + "");
		
		totalnotificationlabel.setText(""+new NotificationData().getUnreadNotification(s.getUserId()+"", "Student", s.getCurriculumCode(), s.getSemorYear(),s.getAdmissionDate()));
		curriculumspanel.setVisible(false);
		notificationpanel.setVisible(true);
		welcomelabel.setText("Welcome " +s.getFullName());
		totallectureslabel.setText(new SubjectData().getTotalSubjectinCurriculum(s.getCurriculumCode(), s.getSemorYear()) + "");
		studentspanel.setLocation(pos[0], studentspanel.getY());
		instructorspanel.setLocation(pos[1], instructorspanel.getY());
		subjectpanel.setLocation(pos[2], subjectpanel.getY());
		notificationpanel.setLocation(pos[3], notificationpanel.getY());
	}

	public void setLastLogin(String lastlogin) {
		if (lastlogin == null || lastlogin.isEmpty()) {
			this.lastloginlabel.setText("last login : First Time");
		} else {
			this.lastloginlabel.setText("last login : " + lastlogin);
//		this.timedifflabel.setText(TimeUtil.getDateDifference(lastlogin));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
