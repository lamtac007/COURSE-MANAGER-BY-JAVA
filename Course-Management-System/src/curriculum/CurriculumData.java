package curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import general.DataBaseConnection;
import general.Notification;
import general.NotificationData;
import general.TimeUtil;


//Purpose : Handling all the data related to curriculum

public class CurriculumData
{
	
	static Connection con=DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public int addCurriculum(String curriculumcode,String curriculumname,String semoryear,int totalyearorsem)
	{
		int result=0;
		try 
		{
		String query="insert into curriculums values(?,?,?,?,?)";
		PreparedStatement pr=con.prepareStatement(query);
		pr.setInt(1,0);
		pr.setString(2, curriculumcode.toUpperCase());
		pr.setString(3, curriculumname);
		pr.setString(4, semoryear);
		pr.setInt(5, totalyearorsem);
		result=pr.executeUpdate();
			
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public  ResultSet getCurriculuminfo()
	{
	
		ResultSet st=null;
		try
		{
			String query="select c.sr_no as 'Index no.',c.curriculumcode as 'Curriculum Code' ,c.curriculumname as 'Curriculum Name',(select count(*) from subject where subject.curriculumcode=c.curriculumcode) as 'Subjects' ,(select count(*) from students where students.curriculumcode=c.curriculumcode) as 'Students',concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Year' from curriculums c;";
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
	public int getTotalCurriculum()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select * from curriculums");
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
		
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	
	
	
	public String[] getCurriculumName()
	{
				String curriculumname[];
				int i=0;
				curriculumname=new String[getTotalCurriculum()+1];
				curriculumname[i++]="---Select Curriculum---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select curriculumname from curriculums");
					
					
					while(st.next())
					{
						curriculumname[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return curriculumname;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return curriculumname;
		
	}
	public int getRollTotalCurriculum()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select curriculumname from curriculums where curriculumcode Not IN(select distinct curriculumcode from rollgenerator)");             
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
			st.close();
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	public String[] getRollCurriculumName()
	{
				String curriculumname[];
				int i=0;
				curriculumname=new String[getRollTotalCurriculum()+1];
				curriculumname[i++]="---select---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select curriculumname from curriculums where curriculumcode NOT IN(select distinct curriculumcode from rollgenerator)");
					
					
					while(st.next())
					{
						curriculumname[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return curriculumname;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return curriculumname;
		
	}
	public String[] getSemorYear(String Curriculumname)
	{
		String query="select semoryear, totalsemoryear from curriculums where curriculumname='"+Curriculumname+"'";
		String totalsem[]=new String[1];
		totalsem[0]="---Select Sem/Year---";
		if(!Curriculumname.contains("--select--"))
		{
			try
			{
				Statement pr=con.createStatement();
				ResultSet st=pr.executeQuery(query);
				st.next();
				String semoryear=st.getString(1);
				int totalsemoryear=st.getInt(2);	
				
				totalsem=new String[totalsemoryear+1];
				if(semoryear.contains("sem"))
				{
					semoryear="Semester";
				}
				else
				{
					semoryear="Year";
				}
				totalsem[0]="---Select "+semoryear+"---";
				for(int i=1; i<=totalsemoryear; i++)
				{
					totalsem[i]=semoryear+" "+i;
				}
				pr.close();
				st.close();
				return totalsem;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		return totalsem;
		
	}
	public String[] getCurriculumcode()
	{
		String curriculumcode[]=new String[this.getTotalCurriculum()];
		String query="select curriculumcode from curriculums";
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			int i=0;
			while(st.next())
			{
				curriculumcode[i++]=st.getString(1);
			}
			pr.close();
			st.close();
			
			return curriculumcode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return curriculumcode;
		
		
	}
	public String getCurriculumcode(String curriculumname)
	{
		String query="select curriculumcode from curriculums where curriculumname='"+curriculumname+"'";
		String curriculumcode=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				curriculumcode=st.getString(1);
			
				pr.close();
				st.close();
			return curriculumcode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return curriculumcode;
	}
	public String getsemoryear(String curriculumcode)
	{
		String query="select semoryear from curriculums where curriculumcode='"+curriculumcode+"'";
		String semoryear=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				semoryear=st.getString(1);
			
				pr.close();
				st.close();
			return semoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return semoryear;
	}
	public String getcurriculumname(String curriculumcode)
	{
		String query="select curriculumname from curriculums where curriculumcode='"+curriculumcode+"'";
		String curriculumname=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				curriculumname=st.getString(1);
			
				pr.close();
				st.close();
			return curriculumname;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return curriculumname;
	}
	public int getTotalsemoryear(String curriculumname)
	{
		String query="select totalsemoryear from curriculums where curriculumname='"+curriculumname+"'";
		int totalsemoryear=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			while(st.next())
			{
			totalsemoryear=st.getInt(1);
			}
			pr.close();
			st.close();
			
			return totalsemoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalsemoryear;
	}
	public boolean isCurriculumCodeExist(String curriculumcode)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from curriculums where curriculumcode='"+curriculumcode+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isCurriculumNameExist(String curriculumname)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from curriculums where curriculumname='"+curriculumname+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDeclared(String curriculumcode,int semoryear)
	{
		boolean isdeclared=false;
		try
		{
			String query="select isdeclared from result where curriculumcode='"+curriculumcode+"' and semoryear="+semoryear;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
			isdeclared=rs.getBoolean(1);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return isdeclared;
	}
	public ArrayList<Curriculum> getCurriculumsForDeclareResult(String curriculumname)
	{
		ArrayList<Curriculum> list=new ArrayList<Curriculum>();
		try
		{
			String query="select curriculumname,curriculumcode,totalsemoryear from curriculums where curriculumname='"+curriculumname+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				int totalsem=rs.getInt(3);
				for(int i=0; i<totalsem; i++)
				{
					Curriculum curriculum=new Curriculum();
					curriculum.setCurriculumName(rs.getString(1));
					curriculum.setCurriculumCode(rs.getString(2));
					curriculum.setSemorYear((i+1));
					curriculum.setIsDeclared(isDeclared(rs.getString(2),(i+1)));
					list.add(curriculum);
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public int updateResult(Curriculum c)
	{
		int result=0;
		try
		{
			String query="update result set isdeclared="+c.getIsDeclared()+" where curriculumcode='"+c.getCurriculumCode()+"' and semoryear="+c.getSemorYear();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp) 
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void declareResult(Curriculum c)
	{
		try
		{		if(c.getIsDeclared())
				{
					Notification n=new Notification();
					n.setUserProfile("Student");
					n.setCurriculumCode(c.getCurriculumCode());
					n.setSemorYear(c.getSemorYear());
					n.setTitle("Result");
					n.setUserId("Admin");
					n.setMessage("Your result is declared. now you can see your marksheet.");
					n.setTime(TimeUtil.getCurrentTime());
					new NotificationData().addNotification(n);
					n.setMessage( c.getCurriculumCode()+" "+getsemoryear(c.getCurriculumCode())+"-"+c.getSemorYear()+" result is declared. now you can see student's marksheet.");
					n.setUserProfile("Instructor");
					new NotificationData().addNotification(n);
				}
				if(updateResult(c)==0)
				{
				String query="insert into result values(?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setString(1,c.getCurriculumCode());
				pr.setInt(2, c.getSemorYear());
				pr.setBoolean(3, c.getIsDeclared());
				pr.executeUpdate();
				}
				
				
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
}

