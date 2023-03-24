package student;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import general.DataBaseConnection;
import general.Notification;
import general.NotificationData;
import general.TimeUtil;
import subject.SubjectData;

//Purpose : Handling all the data related to student

public class StudentData 
{
	
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	
	public int addStudent(Student s)
	{
		int result=0;
		String query="insert into students values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
		
			//Adding notification of new student
			{
				Notification n=new Notification();
				n.setUserProfile("Student");
				n.setCurriculumCode(s.getCurriculumCode());
				n.setSemorYear(s.getSemorYear());
				n.setTitle("New Student");
				n.setUserId(s.generateUserId());
				n.setMessage(s.getFullName()+" ("+s.getRollNumber()+") has taken admission in your class.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificationData().addNotification(n);
				n.setUserProfile("Instructor");
				new NotificationData().addNotification(n);
			}
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, s.getCurriculumCode());
			pr.setInt(2,s.getSemorYear());
			pr.setLong(3,s.getRollNumber());
			pr.setString(4, s.getOptionalSubject());
			pr.setString(5,s.getFirstName());
			pr.setString(6, s.getLastName());
			pr.setString(7, s.getEmailId());
			pr.setString(8, s.getContactNumber());
			pr.setString(9,s.getBirthDate());
			pr.setString(10, s.getGender());
			pr.setString(11, s.getState());
			pr.setString(12, s.getCity());
			pr.setString(13,s.getFatherName());
			pr.setString(14,s.getFatherOccupation());
			pr.setString(15, s.getMotherName());
			pr.setString(16, s.getMotherOccupation());
			pr.setBytes(17, s.getProfilePicInBytes());
			pr.setInt(18, 0);//sr no 
			pr.setString(19, "");//lastlogin
			pr.setString(20, s.generateUserId());//userid
			pr.setString(21, s.getBirthDate());//password
			pr.setBoolean(22,false);//activestatus
			pr.setString(23,s.generateAdmissionDate() );
			 result=pr.executeUpdate();
		
			pr.close();
			return result;
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	public int deleteMarksData(Student s)
	{
		int result=0;
		String query="delete from marks where curriculumcode='"+s.getCurriculumCode()+"' and semoryear="+s.getSemorYear()+" and rollnumber="+s.getRollNumber();
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public int deleteAttandanceData(Student s)
	{
		int result=0;
		String query="delete from attandance where curriculumcode='"+s.getCurriculumCode()+"' and semoryear="+s.getSemorYear()+" and rollnumber="+s.getRollNumber();
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public int deleteUsersHistory(Student s)
	{
		int result=0;
		String query="delete from users where userid='"+s.getUserId()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public int deleteChatHistory(Student s)
	{
		int result=0;
		String query="delete from chat where touserid='"+s.getUserId()+"' or fromuserid='"+s.getUserId()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			if(result>0)
			{
				this.reArrangeChatSrNoColumn();
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void reArrangeChatSrNoColumn()
	{
		
		try
		{
			String query="alter table chat drop sr_no;";
			PreparedStatement pr=con.prepareStatement(query);
			pr.executeUpdate();
			query="alter table chat add sr_no int primary key auto_increment first";
			pr=con.prepareStatement(query);
			pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public int deleteNotificationHistory(Student s)
	{
		int result=0;
		String query="delete from notification where userid='"+s.getUserId()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public int deleteOldOptionalSubjectMarks(Student s)
	{
		int result=0;
		try
		{
			String query="delete from marks where curriculumcode='"+s.getCurriculumCode()+"' and semoryear="+s.getSemorYear()+" and rollnumber="+s.getRollNumber()+" and subjectname='"+s.getOptionalSubject()+"'";
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public int updateStudentData(Student sold,Student s)
	{
		int result=0;
		String query="update students set Curriculumcode=?,semoryear=?,rollnumber=?,optionalsubject=?,firstname=?,lastname=?,emailid=?,contactnumber=?,dateofbirth=?,gender=?,state=?,city=?,fathername=?,fatheroccupation=?,mothername=?,motheroccupation=?,profilepic=?,lastlogin=?,activestatus=?,userid=? where curriculumcode='"+sold.getCurriculumCode()+"' and semoryear="+sold.getSemorYear()+" and rollnumber="+sold.getRollNumber();
	
		//if curriculum or sem or rollnumber is changed 
		if(!s.getCurriculumCode().equals(sold.getCurriculumCode()) || s.getSemorYear()!=sold.getSemorYear() || s.getRollNumber()!=sold.getRollNumber())
		{
			
			
			//Adding notification 
			{
				Notification n=new Notification();
				n.setUserProfile("Student");
				n.setCurriculumCode(s.getCurriculumCode());
				n.setSemorYear(s.getSemorYear());
				n.setTitle("New Student");
				n.setUserId(s.generateUserId());
				n.setMessage(s.getFullName()+" ("+s.getRollNumber()+") has taken admission in your class.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificationData().addNotification(n);
				n.setUserProfile("Instructor");
				new NotificationData().addNotification(n);
			}
			//deleting all the data of student from this curriculum
			this.deleteMarksData(sold);
			this.deleteAttandanceData(sold);
			this.deleteUsersHistory(sold);
			this.deleteNotificationHistory(sold);
			this.deleteChatHistory(sold);
		}
		
		if(!s.getOptionalSubject().equals(sold.getOptionalSubject()))
		{
			this.deleteOldOptionalSubjectMarks(sold);
		}
		try
		{
		
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, s.getCurriculumCode());
			pr.setInt(2,s.getSemorYear());
			pr.setLong(3,s.getRollNumber());
			pr.setString(4, s.getOptionalSubject());
			pr.setString(5,s.getFirstName());
			pr.setString(6, s.getLastName());
			pr.setString(7, s.getEmailId());
			pr.setString(8, s.getContactNumber());
			pr.setString(9,s.getBirthDate());
			pr.setString(10, s.getGender());
			pr.setString(11, s.getState());
			pr.setString(12, s.getCity());
			pr.setString(13,s.getFatherName());
			pr.setString(14,s.getFatherOccupation());
			pr.setString(15, s.getMotherName());
			pr.setString(16, s.getMotherOccupation());
			pr.setBytes(17, s.getProfilePicInBytes());
			pr.setString(18, s.getLastLogin());
			pr.setBoolean(19, s.getActiveStatus());
			pr.setString(20,s.generateUserId());
			 result=pr.executeUpdate();
		
		
			pr.close();
			return result;
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

	public int getTotalStudentInCurriculum(String Curriculumcode,int sem)
	{
		int rollnumber=0;
	
		String query="select rollnumber from students where curriculumcode='"+Curriculumcode+"' and semoryear="+sem;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				rollnumber++;
			}
			st.close();
			rs.close();
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public String[] getRollNumber(String Curriculumcode,int sem)
	{
		String rollnumber[]=null;
		int i=0;
		String query="select rollnumber from students where curriculumcode='"+Curriculumcode+"' and semoryear="+sem+" order by rollnumber asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			long num;
			rollnumber=new String[this.getTotalStudentInCurriculum(Curriculumcode, sem)+1];
			rollnumber[i++]="---Select Rollnumber---";
			while(rs.next())
			{
				num=rs.getLong(1);
				rollnumber[i++]=num+"";
				
			}
			return rollnumber;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return rollnumber;
	}
	public ResultSet getStudentinfo(String condition)
	{
		ResultSet rs=null;
		String query="select s.curriculumcode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',c.curriculumname as 'Curriculum Name',concat(c.semoryear,'-',s.semoryear) as 'Sem/Year' from students s ,curriculums c where s.curriculumcode=c.curriculumcode "+condition+" order by s.curriculumcode asc,s.semoryear asc,s.rollnumber asc";
		
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet searchStudent(String query)
	{

		query+=" order by s.curriculumcode asc,s.semoryear asc,s.rollnumber asc";
		ResultSet rs=null;
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public Student getStudentDetails(String Curriculumcode,int sem,long rollnumber)
	{
		Student s=new Student();
		
		String query=" select * from students where curriculumcode='"+Curriculumcode+"' and semoryear="+sem+" and rollnumber="+rollnumber;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			s.setCurriculumCode(rs.getString(1));
			s.setSemorYear(rs.getInt(2));
			s.setRollNumber(rs.getLong(3));
			s.setOptionalSubject(rs.getString(4));
			s.setFirstName(rs.getString(5));
			s.setLastName(rs.getString(6));
			s.setEmailId(rs.getString(7));
			s.setContactNumber(rs.getString(8));
			s.setBirthDate(rs.getString(9));
			s.setGender(rs.getString(10));
			s.setState(rs.getString(11));
			s.setCity(rs.getString(12));
			s.setFatherName(rs.getString(13));
			s.setFatherOccupation(rs.getString(14));
			s.setMotherName(rs.getString(15));
			s.setMotherOccupation(rs.getString(16));
			s.setProfilePic(rs.getBytes(17));
			s.setSrNo(rs.getInt(18));
			s.setLastLogin(rs.getString(19));
			s.setUserId(rs.getString(20));
			s.setPassword(rs.getString(21));
			s.setActiveStatus(rs.getBoolean(22));
			s.setAdmissionDate(rs.getString(23));

			return s;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public int addStudentTheoryMarks(Marks m)
	{
		int result=0;
		try
		{
			
			String query="update marks set theorymarks="+m.getTheoryMarks()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getRollNumber();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			if(result==0)
			{
				query="insert into marks values(?,?,?,?,?,?,?)";
				pr=con.prepareStatement(query);
				pr.setString(1, m.getCurriculumCode());
				pr.setInt(2, m.getSemorYear());
				pr.setString(3, m.getSubjectCode());
				pr.setString(4, m.getSubjectName());
				pr.setLong(5, m.getRollNumber());
				pr.setInt(6, m.getTheoryMarks());
				
				pr.setInt(7,Types.NULL);
				result=pr.executeUpdate();	
			}
		}
		catch(Exception exp)
		{
			
		}
		return result;
		
	}

	public int addStudentPracticalMarks(Marks m)
	{
		int result=0;
		try
		{
			String query="update marks set practicalmarks="+m.getPracticalMarks()+" where subjectcode='"+m.getSubjectCode()+"' and rollnumber="+m.getRollNumber();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			if(result==0)
			{
				query="insert into marks values(?,?,?,?,?,?,?)";
				pr=con.prepareStatement(query);
				pr.setString(1, m.getCurriculumCode());
				pr.setInt(2, m.getSemorYear());
				pr.setString(3, m.getSubjectCode());
				pr.setString(4, m.getSubjectName());
				pr.setLong(5, m.getRollNumber());
				pr.setInt(6, Types.NULL);
				pr.setInt(7,m.getPracticalMarks());
				result=pr.executeUpdate();	
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public ArrayList<Marks> getStudentTheoryMarksDetails(String Curriculumcode,int sem,String subjectname)
	{
		ResultSet rs=null;		
		ArrayList<Marks> marks=new ArrayList<Marks>();
		String subjectcode=new SubjectData().getSubjectCode(Curriculumcode, sem, subjectname);
		String query="select distinct s.firstname,s.rollnumber,subject.subjectname,subject.theorymarks,m.theorymarks from students s left join marks m on s.rollnumber=m.rollnumber and m.subjectcode='"+subjectcode+"',subject where s.curriculumcode='"+Curriculumcode+"' and s.semoryear="+sem+" and subject.subjectcode='"+subjectcode+"'  order by s.rollnumber asc";
		String subjecttype=new SubjectData().checkCoreorOptional(subjectcode);
		if(!subjecttype.equals("core"))
		{
			query="select distinct s.firstname,s.rollnumber,subject.subjectname,subject.theorymarks,m.theorymarks from students s left join marks m on s.rollnumber=m.rollnumber and m.subjectcode='"+subjectcode+"',subject where s.optionalsubject=subject.subjectname  and s.curriculumcode='"+Curriculumcode+"' and s.semoryear="+sem+" and subject.subjectcode='"+subjectcode+"'  order by s.rollnumber asc";

		}
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Marks m=new Marks();
				m.setRollNumber(rs.getLong(2));
				m.setStudentName(rs.getString(1));
				m.setSubjectName(rs.getString(3));
				m.setMaxTheoryMarks(rs.getInt(4));
				m.setTheoryMarks(rs.getInt(5));
				marks.add(m);
				
			}
			st.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return marks;
	}
	public ArrayList<Marks> getStudentPracticalMarksDetails(String Curriculumcode,int sem,String subjectname)
	{
		ResultSet rs=null;		
		ArrayList<Marks> marks=new ArrayList<Marks>();
		String subjectcode=new SubjectData().getSubjectCode(Curriculumcode, sem, subjectname);
		String query="select distinct s.firstname,s.rollnumber,subject.subjectname,subject.practicalmarks,m.practicalmarks from students s left join marks m on s.rollnumber=m.rollnumber and m.subjectcode='"+subjectcode+"',subject where s.curriculumcode='"+Curriculumcode+"' and s.semoryear="+sem+" and subject.subjectcode='"+subjectcode+"' order by s.rollnumber asc";
		String subjecttype=new SubjectData().checkCoreorOptional(subjectcode);
		if(!subjecttype.equals("core"))
		{
			query="select distinct s.firstname,s.rollnumber,subject.subjectname,subject.practicalmarks,m.practicalmarks from students s left join marks m on s.rollnumber=m.rollnumber and m.subjectcode='"+subjectcode+"',subject where s.optionalsubject=subject.subjectname  and s.curriculumcode='"+Curriculumcode+"' and s.semoryear="+sem+" and subject.subjectcode='"+subjectcode+"'  order by s.rollnumber asc";

		}
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Marks m=new Marks();
				m.setRollNumber(rs.getLong(2));
				m.setStudentName(rs.getString(1));
				m.setSubjectName(rs.getString(3));
				m.setMaxPracticalMarks(rs.getInt(4));
				m.setPracticalMarks(rs.getInt(5));
				marks.add(m);
				
			}
			st.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return marks;
	}
	public Student getStudentDetails(int row)
	{
		Student s=new Student();
		String query="select * from students where sr_no="+row;
		try
		{
		
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			s.setCurriculumCode(rs.getString(1));
			s.setSemorYear(rs.getInt(2));
			s.setRollNumber(rs.getLong(3));
			s.setOptionalSubject(rs.getString(4));
			s.setFirstName(rs.getString(5));
			s.setLastName(rs.getString(6));
			s.setEmailId(rs.getString(7));
			s.setContactNumber(rs.getString(8));
			s.setBirthDate(rs.getString(9));
			s.setGender(rs.getString(10));
			s.setState(rs.getString(11));
			s.setCity(rs.getString(12));
			s.setFatherName(rs.getString(13));
			s.setFatherOccupation(rs.getString(14));
			s.setMotherName(rs.getString(15));
			s.setMotherOccupation(rs.getString(16));
			s.setProfilePic(rs.getBytes(17));
			s.setSrNo(rs.getInt(18));
			s.setLastLogin(rs.getString(19));
			s.setUserId(rs.getString(20));
			s.setPassword(rs.getString(21));
			s.setActiveStatus(rs.getBoolean(22));
			s.setAdmissionDate(rs.getString(23));
			return s;
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public Student getStudentDetailsByUserId(String userid)
	{
		Student s=new Student();
		String query="select * from students where userid='"+userid+"'";
		try
		{
		
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
				rs.next();
				s.setCurriculumCode(rs.getString(1));
				s.setSemorYear(rs.getInt(2));
				s.setRollNumber(rs.getLong(3));
				s.setOptionalSubject(rs.getString(4));
				s.setFirstName(rs.getString(5));
				s.setLastName(rs.getString(6));
				s.setEmailId(rs.getString(7));
				s.setContactNumber(rs.getString(8));
				s.setBirthDate(rs.getString(9));
				s.setGender(rs.getString(10));
				s.setState(rs.getString(11));
				s.setCity(rs.getString(12));
				s.setFatherName(rs.getString(13));
				s.setFatherOccupation(rs.getString(14));
				s.setMotherName(rs.getString(15));
				s.setMotherOccupation(rs.getString(16));
				s.setProfilePic(rs.getBytes(17));
				s.setSrNo(rs.getInt(18));
				s.setLastLogin(rs.getString(19));
				s.setUserId(rs.getString(20));
				s.setPassword(rs.getString(21));
				s.setActiveStatus(rs.getBoolean(22));
				s.setAdmissionDate(rs.getString(23));
				
			return s;
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public int getTotalStudents()
	{
		int totalstudent=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from students");
			while(rs.next())
			{
				totalstudent++;
			}
			rs.close();
			st.close();
			return totalstudent;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalstudent;
	}

	public Marks getOptionalSubjectMarks(String curriculumcode,int semoryear,long rollnumber)
	{
		try
		{
			Marks m=new Marks();
			m.setCurriculumCode(curriculumcode);
			m.setSemorYear(semoryear);
			m.setRollNumber(rollnumber);
			String scode=this.getOptionalSubjectCode(curriculumcode,semoryear,rollnumber);
			if(scode==null)
			{
				return null;
			}
			String query="select su.subjectcode,su.subjectname,su.theorymarks,m.theorymarks,su.practicalmarks,m.practicalmarks from subject su left join marks m on m.subjectcode='"+scode+"' and m.rollnumber="+rollnumber+" where su.subjectcode='"+scode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			m.setSubjectCode(rs.getString(1));
			m.setSubjectName(rs.getString(2));
			m.setMaxTheoryMarks(rs.getInt(3));
			m.setTheoryMarks(rs.getInt(4));
			m.setMaxPracticalMarks(rs.getInt(5));
			m.setPracticalMarks(rs.getInt(6));
			m.setRollNumber(rollnumber);
			m.setStudentName(getStudentName(curriculumcode+"-"+semoryear+"-"+rollnumber));
			return m;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public String getOptionalSubjectCode(String curriculumcode,int semoryear,long rollnumber)
	{
		String osub=null;
		
		try
		{
			String query="select optionalsubject from students where userid='"+curriculumcode+"-"+semoryear+"-"+rollnumber+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			osub=rs.getString(1);
			osub=new SubjectData().getSubjectCode(curriculumcode, semoryear, osub);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return osub;
		
	}
	public ArrayList<Marks> getMarkssheetOfStudent(String curriculumcode,int sem,long rollnumber)
	{
			ArrayList<Marks> list=new ArrayList<Marks>();
			String query="select su.subjectcode,su.subjectname,su.theorymarks,m.theorymarks,su.practicalmarks,m.practicalmarks from subject su left join marks m on su.subjectname=m.subjectname and m.rollnumber="+rollnumber+" and m.semoryear="+sem+" and m.curriculumcode='"+curriculumcode+"' where su.curriculumcode='"+curriculumcode+"' and su.semoryear="+sem+" and su.subjecttype='core' order by su.subjectcode asc";
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int index=1;
				while(rs.next())
				{
					Marks m=new Marks();
					
					m.setSrNo(index);
					m.setSubjectCode(rs.getString(1));
					m.setSubjectName(rs.getString(2));
					m.setMaxTheoryMarks(rs.getInt(3));
					m.setTheoryMarks(rs.getInt(4));
					m.setMaxPracticalMarks(rs.getInt(5));
					m.setPracticalMarks(rs.getInt(6));
					m.setRollNumber(rollnumber);
					m.setStudentName(getStudentName(curriculumcode+"-"+sem+"-"+rollnumber));
					index++;
					list.add(m);
				}
				{
					Marks m=getOptionalSubjectMarks(curriculumcode,sem,rollnumber);
					if(m!=null)
					{
					m.setSrNo(index);
					list.add(m);
					}
				}
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return list;
	}
	
	public boolean isSubmitted(String subjectcode,String date)
	{
		try
		{
			String query=" select count(*) from attandance where "
					+ "subjectcode='"+subjectcode
					+ "'"
					+ " and date='"+date
					+ "'";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getInt(1)>0?true:false;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Marks> getMarksheetReportBySubject(Marks marks)
	{
		ArrayList<Marks> list=new ArrayList<Marks>();
		try
		{
			String query="select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name'"
					+ ",(select theorymarks from marks where "
					+ "subjectcode='"+marks.getSubjectCode()
					+ "' and rollnumber=s.rollnumber) as 'Theory'"
					+ ",(select practicalmarks from marks where"
					+ " subjectcode='"+marks.getSubjectCode()
					+ "' and rollnumber=s.rollnumber) as 'Practical',"
					+ "(select theorymarks from subject where subjectcode='"+marks.getSubjectCode()
					+ "') as 'Total theory',(select practicalmarks from subject where subjectcode='"+marks.getSubjectCode()
					+ "') as 'Total Practical' from students s left join marks m on s.rollnumber=m.rollnumber where s.curriculumcode='"+marks.getCurriculumCode()
					+ "' and s.semoryear="+marks.getSemorYear();
			String subjecttype=new SubjectData().checkCoreorOptional(marks.getSubjectCode());
			if(!subjecttype.equals("core"))
			{
				query+=" and s.optionalsubject='"+marks.getSubjectName()+"'";
			}
			query+=" order by s.rollnumber asc";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Marks m=new Marks();
				m.setRollNumber(rs.getLong(1));
				m.setStudentName(rs.getString(2));
				m.setSubjectName(marks.getSubjectName());
				m.setTheoryMarks(rs.getInt(3));
				m.setPracticalMarks(rs.getInt(4));
				m.setMaxTheoryMarks(rs.getInt(5));
				m.setMaxPracticalMarks(rs.getInt(6));
				
				list.add(m);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Marks> getMarksheetReportByClass(Marks marks)
	{
		ArrayList<Marks> list=new ArrayList<Marks>();
		try
		{
			String query="select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name',(select sum(theorymarks) from marks where curriculumcode=s.curriculumcode and semoryear=s.semoryear and rollnumber=s.rollnumber) as 'Theory',(select sum(practicalmarks) from marks where curriculumcode=s.curriculumcode and semoryear=s.semoryear  and rollnumber=s.rollnumber) as 'Practical',(select sum(theorymarks) from subject where curriculumcode=s.curriculumcode and semoryear=s.semoryear and subjecttype='core') as 'Total theory',(select sum(practicalmarks) from subject where curriculumcode=s.curriculumcode and semoryear=s.semoryear and  subjecttype='core' ) as 'Total Practical' from students s left join marks m on s.rollnumber=m.rollnumber where s.curriculumcode='"+marks.getCurriculumCode()+"' and s.semoryear="+marks.getSemorYear();	
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Marks m=new Marks();
				String subjectcode=this.getOptionalSubjectCode(marks.getCurriculumCode(), marks.getSemorYear(), rs.getLong(1));
				int maxpracticalmarks=new SubjectData().getMaxTheoryMarksOfSubject(subjectcode);
				int maxtheorymarks=new SubjectData().getMaxPracticalMarksOfSubject(subjectcode);
				
				m.setRollNumber(rs.getLong(1));
				m.setStudentName(rs.getString(2));
				m.setSubjectName(marks.getSubjectName());
				m.setTheoryMarks(rs.getInt(3));
				m.setPracticalMarks(rs.getInt(4));
				m.setMaxTheoryMarks(rs.getInt(5)+maxtheorymarks);
				m.setMaxPracticalMarks(rs.getInt(6)+maxpracticalmarks);
				
				list.add(m);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
		
	}
	
	public boolean checkPassword(String userid,String password)
	{
		boolean result=false;
		try
		{
			String query="select count(*) from students where userid='"+userid+"' and password='"+password+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			if(rs.getInt(1)>0)
			{
				result=true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public ArrayList<Student> getStudentsDetails(String condition)
	{
			ArrayList<Student> list=new ArrayList<Student>();
			String query="select * from students s "+condition+" order by s.curriculumcode asc,s.semoryear asc,s.rollnumber asc";
			try
			{
			
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				
				while(rs.next())
				{
					Student s=new Student();
					s.setCurriculumCode(rs.getString(1));
					s.setSemorYear(rs.getInt(2));
					s.setRollNumber(rs.getLong(3));
					s.setOptionalSubject(rs.getString(4));
					s.setFirstName(rs.getString(5));
					s.setLastName(rs.getString(6));
					s.setEmailId(rs.getString(7));
					s.setContactNumber(rs.getString(8));
					s.setBirthDate(rs.getString(9));
					s.setGender(rs.getString(10));
					s.setState(rs.getString(11));
					s.setCity(rs.getString(12));
					s.setFatherName(rs.getString(13));
					s.setFatherOccupation(rs.getString(14));
					s.setMotherName(rs.getString(15));
					s.setMotherOccupation(rs.getString(16));
					s.setProfilePic(rs.getBytes(17));
					s.setSrNo(rs.getInt(18));
					s.setLastLogin(rs.getString(19));
					s.setUserId(rs.getString(20));
					s.setPassword(rs.getString(21));
					s.setActiveStatus(rs.getBoolean(22));
					s.setAdmissionDate(rs.getString(23));
					list.add(s);
				}
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
	}
	public boolean isActive(String userid)
	{
		try
		{
			String query="select activestatus from students where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			boolean active=rs.getBoolean(1);
			st.close();
			rs.close();
			return active;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	
	public String getStudentName(String userid)
	{
		String name="";
		try
		{
			String query="select concat(firstname,' ',lastname) from students where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			name=rs.getString(1);
			
			rs.close();
			st.close();
		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return name;
	}
	public int setActiveStatus(boolean activestatus,String userid)
	{
		int result=0;
		try
		{
			String query="update students set activestatus="+activestatus+" where userid='"+userid+"'";
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public String getLastLogin(String userid)
	{
		try
		{
			String query="select lastlogin from students where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return "deleted";
		}
	}
	public Image getProfilePic(String userid)
	{
		Image image=null;
		try
		{
			String query="select profilepic from students where userid='"+userid+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			byte[] imagedata=rs.getBytes(1);
			image=Toolkit.getDefaultToolkit().createImage(imagedata);
			rs.close();
			st.close();
		}
		catch(Exception exp)
		{
		exp.printStackTrace();	
		}
		return image;
	}
	public int changePassword(String userid,String password)
	{
		try
		{
			String query="update students set password='"+password+"' where userid='"+userid+"'";
			PreparedStatement pr=con.prepareStatement(query);
			return pr.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return 0;
	}
	
}

