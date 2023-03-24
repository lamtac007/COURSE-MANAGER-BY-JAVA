package instructor;

import curriculum.CurriculumData;
import general.Person;
import general.TimeUtil;


//Purpose : Binding all the data of instructor
public class Instructor extends  Person
{
	
	private int instructorid;
	private String instructorname;
	private String qualification;
	private String experience;
	private String subject;
	private String position;
	private String joineddate;
	
	public void setJoinedDate(String joineddate)
	{
		this.joineddate=joineddate;
	}
	public void setInstructorId(int instructorid)
	{
		this.instructorid=instructorid;
	}
	public void setInstructorName(String instructorname)
	{
		this.instructorname=instructorname;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public void setExperience(String experience)
	{
		this.experience=experience;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public void setPosition(String position)
	{
		this.position=position;
	}
	public String getCurriculumName()
	{
		return new CurriculumData().getcurriculumname(this.getCurriculumCode());
	}
	public String getInstructorName()
	{
		return instructorname;
	}
	
	public int getInstructorId()
	{
		return instructorid;
	}
	
	public String getQualification()
	{
		return qualification;
	}
	public String getPosition()
	{
		return position;
	}
	public String getExperience()
	{
		return experience;
	}
	public String getSubject()
	{
		return subject;
	}
	public String getJoinedDate()
	{
		return joineddate;
	}
	public String generateJoinedDate()
	{
		joineddate=TimeUtil.getCurrentTime();
		return joineddate;
	}
	
}