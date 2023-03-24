package chat;

import java.awt.Image;

//Purpose : To binding all the data related to group
class Group
{
	private Image groupimage;
	private String groupname;
	private int members;
	private String curriculumcode;
	private int semoryear;
	public void setImage(Image image)
	{
		this.groupimage=image;
	}
	public void setGroupName(String groupname)
	{
		this.groupname=groupname;
	}
	public void setCurriculumCode(String curriculumcode)
	{
		this.curriculumcode=curriculumcode;
	}
	public void setSem(int semoryear)
	{
		this.semoryear=semoryear;
	}
	public void setMembers(int members)
	{
		this.members=members;
	}
	public Image getImage()
	{
		return groupimage;
	}
	public String getGroupName()
	{
		return groupname;
	}
	public String getCurriculumCode()
	{
		return curriculumcode;
	}
	public int getSemorYear()
	{
		return semoryear;
	}
	public int getMembers()
	{
		return members; 
	}
			
}