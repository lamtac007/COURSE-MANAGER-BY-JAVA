package subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import general.DataBaseConnection;
import general.Notification;
import general.NotificationData;
import general.TimeUtil;

//Purpose : Handling all the data related to subject

public class SubjectData 
{

	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public String checkCoreorOptional(String subjectcode)
	{
		String type="core";
		try
		{
			String query="select subjecttype from subject where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			
			type=rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return type;
	}
	public boolean isExist(String curriculumcode,int semoryear,String subjectname)
	{
		try
		{
			String query="select subjectname from subject where curriculumcode='"+curriculumcode+"' and semoryear="+semoryear+" and subjectname='"+subjectname+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.first())
			{
				return true;
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public int getMaxTheoryMarksOfSubject(String subjectcode)
	{
		int marks=0;
		try
		{
			String query="select theorymarks from subject where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			marks=rs.getInt(1);
			st.close();
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return marks;
	}
	public int getMaxPracticalMarksOfSubject(String subjectcode)
	{
		int marks=0;
		try
		{
			String query="select practicalmarks from subject where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			marks=rs.getInt(1);
			st.close();
			rs.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return marks;
	}
	public int getTotalSubject()
	{
		int totalsubject=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from subject");
			while(rs.next())
			{
				totalsubject++;
			}
			rs.close();
			st.close();
			return totalsubject;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalsubject;
	}
	public String createSubjectcode(String Curriculumcode,int sem)
	{
		int code=101;
		String subjectcode=Curriculumcode+sem+code;
		try
		{
			String query="select curriculumcode,semoryear from subject where curriculumcode='"+Curriculumcode+"' and semoryear="+sem;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				code++;
			}
			subjectcode=Curriculumcode+sem+code;
			rs.close();
			st.close();
			return subjectcode;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return subjectcode;
	}
	public int addSubject(Subject su)
	{
		String query="insert into subject values(?,?,?,?,?,?,?)";
		int result=0;
		try
		{
			
			PreparedStatement pr=con.prepareStatement(query);
			pr.setString(1, su.getSubjectCode());
			pr.setString(2, su.getSubjectName());
			pr.setString(3, su.getCurriculumCode());
			pr.setInt(4, su.getSemorYear());
			pr.setString(5, su.getSubjectType());
			pr.setInt(6, su.getMaxTheoryMarks());
			pr.setInt(7,su.getMaxPracticalMarks());
			result=pr.executeUpdate();
			
			//Adding notification of new subject
			{
				Notification n=new Notification();
				n.setUserProfile("Student");
				n.setCurriculumCode(su.getCurriculumCode());
				n.setUserId("Admin");
				n.setSemorYear(su.getSemorYear());
				n.setTitle("New Subject");
				n.setMessage(su.getSubjectName()+" ("+su.getSubjectCode()+") is your new subject.");
				n.setTime(TimeUtil.getCurrentTime());
				new NotificationData().addNotification(n);
				n.setMessage(su.getSubjectName()+" ("+su.getSubjectCode()+") is new subject in your class");
				n.setUserProfile("Instructor");
				new NotificationData().addNotification(n);
			}
			
			pr.close();
			
			return result;
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	public String getSubjectName(String subjectcode)
	{
		String subjectname=null;
		try
		{
			String query="select subjectname from subject where subjectcode='"+subjectcode+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			subjectname=rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return subjectname;
	}
	public  ResultSet getSubjectinfo(String curriculumcode,int sem)
	{
	
		ResultSet st=null;
		try
		{
			String query="select subjectcode as 'Subject Code',subjectname as 'Subject Name',semoryear as 'Sem/Year',subjecttype as 'Subject Type',theorymarks as 'Theory Marks',practicalmarks as 'Practical Marks' from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem;
			PreparedStatement pr=con.prepareStatement(query);
			st=pr.executeQuery();
			return st;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return st;
	}
	public String[] getOptionalSubject(String curriculumcode,int sem)
	{
		int totaloptionalsubject=this.gettotalOptionalSubject(curriculumcode, sem);
		if(totaloptionalsubject>0)
		{
		String[] subject=new String[totaloptionalsubject+1];
		subject[0]="---Select Optional Subject---";
		String query="select subjectname from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem+" and subjecttype='optional'";
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int i=1;
				while(rs.next())
				{
					subject[i++]=rs.getString(1);
				}
				rs.close();
				st.close();
				return subject;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			
		}
		return null;
	}
	public int gettotalOptionalSubject(String curriculumcode,int sem)
	{
		int totalopsubject=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select subjectname from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem+" and subjecttype='optional'");
			while(rs.next())
			{
				totalopsubject++;
			}
			rs.close();
			st.close();
			return totalopsubject;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalopsubject;
	}
	public String[] getSubjectinCurriculum(String curriculumcode,int sem)
	{
		int totalsubjectincurriculum=this.getTotalSubjectinCurriculum(curriculumcode, sem);
		if(totalsubjectincurriculum>0)
		{
		String[] subject=new String[totalsubjectincurriculum+1];
		subject[0]="---Select Subject---";
		String query="select subjectname from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem;
			try
			{
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				int i=1;
				while(rs.next())
				{
					subject[i++]=rs.getString(1);
				}
				rs.close();
				st.close();
				return subject;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			
		}
		return null;
	}
	public int getTotalSubjectinCurriculum(String curriculumcode,int sem)
	{
		int totalsubjectincurriculum=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select subjectname from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem);
			while(rs.next())
			{
				totalsubjectincurriculum++;
			}
			
			rs.close();
			st.close();
			return totalsubjectincurriculum;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalsubjectincurriculum;
	}
	public String getSubjectCode(String curriculumcode,int sem,String subjectname)
	{
		String subcode=null;
		String query="select subjectcode from subject where curriculumcode='"+curriculumcode+"' and semoryear="+sem+" and subjectname='"+subjectname+"'";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			subcode=rs.getString(1);
			
		}
		catch(Exception exp)
		{
			return null;
		}
		
		return subcode;
		
	}
}
