package curriculum;

public class Curriculum {

	private String curriculumcode;
	private int semoryear;
	private boolean isdeclared;
	private String curriculumname;
	public void setCurriculumName(String curriculumname)
	{
		this.curriculumname=curriculumname;
	}
	public void setCurriculumCode(String curriculumcode)
	{
		this.curriculumcode=curriculumcode;
	}
	public void setSemorYear(int semoryear)
	{
		this.semoryear=semoryear;
	}
	public void setIsDeclared(boolean isdeclared)
	{
		this.isdeclared=isdeclared;
	}
	public String getCurriculumName()
	{
		return curriculumname!=null?curriculumname:new CurriculumData().getcurriculumname(curriculumcode);
	}
	public String getCurriculumCode()
	{
		return curriculumcode;
	}
	public int getSemorYear()
	{
		return semoryear;
	}
	public boolean getIsDeclared()
	{
		return isdeclared;
	}
}
