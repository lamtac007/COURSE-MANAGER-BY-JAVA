package instructor;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import general.DataBaseConnection;
import general.Notification;
import general.NotificationData;
import general.TimeUtil;

//Purpose : Handling all the data related to instructor
public class InstructorData 
{
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getTotalInstructor()
	{
		int totalf=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from instructors");
			rs.next();
			totalf=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalf;
	}
	public int getTotalFaculaty(String curriculumcode,int sem)
	{
		int totalf=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from instructors where curriculumcode='"+curriculumcode+"' and semoryear="+sem);
			rs.next();
			totalf=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalf;
	}
	public int getInstructor(String curriculumcode,int sem)
	{
		int f=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from instructors where curriculumcode='"+curriculumcode+"' and semoryear="+sem);
			rs.next();
			f=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	public int createInstructorID()
	{
		int id=101;
		try
		{
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from instructors");
			rs.next();
			id=id+rs.getInt(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return id;
		
	}
	public ResultSet getInstructorInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select instructorid as 'Instructor ID',instructorname as 'Instructor Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from instructors  "+condition+" order by instructorid";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public ResultSet searchInstructor(String query)
	{
		ResultSet rs=null;
		query+=" order by instructorid";
		try
		{
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean isActive(String instructorid)
	{
		try
		{
			String query="select activestatus from instructors where instructorid="+instructorid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getBoolean(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public String getInstructorName(String instructorid)
	{
		try
		{
			String query="select instructorname from instructors where instructorid="+instructorid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return "";
	}
	public int addInstructorData(Instructor f)
	{
		int result=0;
		String query="insert into instructors values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, f.getInstructorId());
			pr.setString(2,f.getInstructorName());
			pr.setString(3,f.getState());
			pr.setString(4,f.getCity());
			pr.setString(5,f.getEmailId());
			pr.setString(6,f.getContactNumber());
			pr.setString(7,f.getQualification());
			pr.setString(8,f.getExperience());
			pr.setString(9,f.getBirthDate());
			pr.setString(10,f.getGender());
			pr.setBytes(11,f.getProfilePicInBytes());
			pr.setString(12, "Not Assigned");
			pr.setInt(13, 0);
			pr.setString(14, "Not Assigned");
			pr.setString(15, "Not Assigned");
			pr.setInt(16, 0);
			pr.setString(17, null);
			pr.setString(18, f.getBirthDate());
			pr.setBoolean(19, f.getActiveStatus());
			pr.setString(20, f.generateJoinedDate());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	public int updateInstructorData(Instructor fold,Instructor f)
	{
		int result=0;
		String query="update instructors set instructorid=? , instructorname=? ,state=? , city=? , emailid=? , contactnumber=? , qualification=? , experience=? , birthdate=? , gender=? , profilepic=?,lastlogin=?,activestatus=? where instructorid="+fold.getInstructorId();
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, f.getInstructorId());
			pr.setString(2,f.getInstructorName());
			pr.setString(3,f.getState());
			pr.setString(4,f.getCity());
			pr.setString(5,f.getEmailId());
			pr.setString(6,f.getContactNumber());
			pr.setString(7,f.getQualification());
			pr.setString(8,f.getExperience());
			pr.setString(9,f.getBirthDate());
			pr.setString(10,f.getGender());
			pr.setBytes(11,f.getProfilePicInBytes());
			pr.setString(12, f.getLastLogin());
			pr.setBoolean(13, f.getActiveStatus());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public Instructor getInstructorInfo(int row)
	{
		Instructor f=new Instructor();
		String query="select * from instructors where sr_no="+row;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setInstructorId(rs.getInt(1));
			f.setInstructorName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCurriculumCode(rs.getString(12));
			f.setSemorYear(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			st.close();
		
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	public Instructor getInstructorInfobyId(int instructorid)
	{
		Instructor f=new Instructor();
		String query="select * from instructors where instructorid="+instructorid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setInstructorId(rs.getInt(1));
			f.setInstructorName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCurriculumCode(rs.getString(12));
			f.setSemorYear(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			
			st.close();
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	public ArrayList<Instructor> getTotalInstructor(String condition)
	{
		ArrayList<Instructor> list=new ArrayList<Instructor>();
		
		String query="select * from instructors"+condition+" order by instructorid asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Instructor f=new Instructor();
				f.setInstructorId(rs.getInt(1));
				f.setInstructorName(rs.getString(2));
				f.setState(rs.getString(3));
				f.setCity(rs.getString(4));
				f.setEmailId(rs.getString(5));
				f.setContactNumber(rs.getString(6));
				f.setQualification(rs.getString(7));
				f.setExperience(rs.getString(8));
				f.setBirthDate(rs.getString(9));
				f.setGender(rs.getString(10));
				f.setProfilePic(rs.getBytes(11));
				f.setCurriculumCode(rs.getString(12));
				f.setSemorYear(rs.getInt(13));
				f.setSubject(rs.getString(14));
				f.setPosition(rs.getString(15));
				f.setLastLogin(rs.getString(17));
				f.setPassword(rs.getString(18));
				f.setActiveStatus(rs.getBoolean(19));
				f.setJoinedDate(rs.getString(20));
				list.add(f);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	
	public Instructor getInstructorInfobyUserId(String instructorid)
	{
		Instructor f=new Instructor();
		instructorid=instructorid.replaceAll("\\s", "");
		String query="select * from instructors where instructorid="+instructorid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			f.setInstructorId(rs.getInt(1));
			f.setInstructorName(rs.getString(2));
			f.setState(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmailId(rs.getString(5));
			f.setContactNumber(rs.getString(6));
			f.setQualification(rs.getString(7));
			f.setExperience(rs.getString(8));
			f.setBirthDate(rs.getString(9));
			f.setGender(rs.getString(10));
			f.setProfilePic(rs.getBytes(11));
			f.setCurriculumCode(rs.getString(12));
			f.setSemorYear(rs.getInt(13));
			f.setSubject(rs.getString(14));
			f.setPosition(rs.getString(15));
			f.setLastLogin(rs.getString(17));
			f.setPassword(rs.getString(18));
			f.setActiveStatus(rs.getBoolean(19));
			f.setJoinedDate(rs.getString(20));
			st.close();
			return f;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return f;
	}
	
	public int assignSubject(Instructor fold,Instructor f)
	{
		int result=0;
		try
		{
			
			if(!fold.getSubject().equals(f.getSubject())||!fold.getCurriculumCode().equals(f.getCurriculumCode())||fold.getSemorYear()!=f.getSemorYear()||!fold.getPosition().equals(f.getPosition()))
			{
				this.deleteNotificationHistory(f);
				Notification n=new Notification();
				n.setUserProfile("Student");
				n.setCurriculumCode(f.getCurriculumCode());
				n.setSemorYear(f.getSemorYear());
				n.setTitle("Subject Instructor");
				n.setUserId(f.getInstructorId()+"");
				n.setMessage(f.getInstructorName()+" is your "+f.getSubject()+" subject's new "+f.getPosition()+".");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificationData().addNotification(n);
				n.setMessage(f.getInstructorName()+" is new "+f.getPosition()+" in "+f.getSubject()+" subject.");
				n.setUserProfile("Instructor");
				new NotificationData().addNotification(n);
				
			}
			
			String query="update instructors set curriculumcode='"+f.getCurriculumCode()+"',semoryear="+f.getSemorYear()+",subject='"+f.getSubject()+"',position='"+f.getPosition()+"' where instructorid="+f.getInstructorId();
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
	public int deleteNotificationHistory(Instructor f)
	{
		int result=0;
		String query="delete from notification where userid='"+f.getInstructorId()+"'";
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
	public ResultSet getInstructorSubjectInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select instructorid as 'Instructor ID',instructorname as 'Instructor Name',curriculumcode as 'Class',semoryear as 'Sem/Year',subject as 'Subject',position as 'Position' from instructors "+condition+" order by instructorid asc";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean checkPassword(String instructorid,String password)
	{
		boolean result=false;
		try
		{
			
			if(instructorid.isEmpty()||instructorid.equalsIgnoreCase(" Enter instructor user-id"))
			{
				JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password","Error",JOptionPane.ERROR_MESSAGE);
				result=false;
			}
			else
			{
				String query="select count(*) from instructors where instructorid="+instructorid+" and password='"+password+"'";
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
			
			
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	public void setActiveStatus(boolean activestatus,int instructorid)
	{
		try
		{
			String query="update instructors set activestatus="+activestatus+" where instructorid="+instructorid;
			PreparedStatement pr=con.prepareStatement(query);
			pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public String getLastLogin(String userid)
	{
		try
		{
			String query="select lastlogin from instructors where instructorid="+userid+"";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public Image getProfilePic(String userid)
	{
		Image image=null;
		try
		{
			String query="select profilepic from instructors where instructorid="+userid;
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
			String query="update instructors set password='"+password+"' where instructorid="+userid;
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

