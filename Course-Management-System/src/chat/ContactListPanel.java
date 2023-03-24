package chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import admin.Admin;
import admin.AdminData;
import admin.AdminMain;
import curriculum.CurriculumData;
import general.ImageUtil;
import instructor.Instructor;
import instructor.InstructorData;
import instructor.InstructorMain;
import student.Student;
import student.StudentData;
import student.StudentMain;

@SuppressWarnings("serial")
//Purpose : To display all the contacts of the given user
public class ContactListPanel extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	int total=0;
	int totalmessages=0;
	public  ArrayList<JPanel> contactlist;
	public static ArrayList<ContactInfo> contactinfo;
	ChatMainPanel chatmainpanel;
	public int location=0;
	public String userid="";
	ChatData chatdata=new ChatData();
	Timer timer=new Timer(2000,this);
	Image messagecount =null;
	

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(330,location);
	}
	private ContactListPanel()
	{
		setLayout(null);
		setBackground(Color.WHITE);
		setSize(330,705);
		contactinfo=new ArrayList<ContactInfo>();
		contactlist=new ArrayList<JPanel>();
		try
		{
			messagecount=ImageIO.read(new File("./assets/messagecount.png"));
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	public ContactListPanel(AdminMain am,ChatMainPanel chatmainpanel) {

		this();
		this.chatmainpanel=chatmainpanel;
		userid="Admin";
		getAllcontacts();
		timer.start();
		
		
	}
	public ContactListPanel(InstructorMain fm,ChatMainPanel chatmainpanel) {
		
		this();
		this.chatmainpanel=chatmainpanel;
		userid=fm.f.getInstructorId()+"";
		getContacts(fm.f.getCurriculumCode(),fm.f.getSemorYear(),""," and instructorid!="+fm.f.getInstructorId()+"");
		timer.start();
		
		
	}
	public ContactListPanel(StudentMain sm,ChatMainPanel chatmainpanel) {
		
		this();
		this.chatmainpanel=chatmainpanel;
		userid=sm.s.getUserId();
		getContacts(sm.s.getCurriculumCode(),sm.s.getSemorYear()," and s.userid!='"+sm.s.getUserId()+"' ","");
		timer.start();
		
		
	}
	//For Instructor or Student
	public void getContacts(String curriculumcode,int sem,String slastcondition,String flastcondition)
	{
		//Adding groups
		{
					Admin a=new AdminData().getAdminData();
					addLabel("Groups");
					{
						int members=new StudentData().getTotalStudentInCurriculum(curriculumcode, sem)+new InstructorData().getTotalFaculaty(curriculumcode, sem)+1;
						if(members>1)
						{
							
							Group group=new Group();
							group.setCurriculumCode(curriculumcode);
							group.setSem(sem);
							group.setMembers(members);
							group.setImage(a.getProfilePic());
							String semoryear=new CurriculumData().getsemoryear(curriculumcode);
							group.setGroupName(curriculumcode+" "+semoryear+"-"+sem+" Official Group");
							
							JPanel contactlistpanel=createPanel(a.getProfilePic(),group.getGroupName(),group.getGroupName(),false);
							contactlistpanel.setName(group.getGroupName()+"#"+group.getGroupName()+"#"+total);
							contactlistpanel.setLocation(0, location);
							location+=60;
							total++;
							contactlist.add(contactlistpanel);
							add(contactlistpanel);
							
							
							ContactInfo cf=new ContactInfo();
							cf.setGroup(group);
							contactinfo.add(cf);
							
						}
					}
					if(!flastcondition.isEmpty())
					{
						int members=new InstructorData().getTotalFaculaty(curriculumcode, sem);
						if(members>1)
						{
							
							Group group=new Group();
							group.setCurriculumCode(curriculumcode);
							group.setSem(sem);
							group.setMembers(members);
							group.setImage(a.getProfilePic());
							String semoryear=new CurriculumData().getsemoryear(curriculumcode);
							group.setGroupName(curriculumcode+" "+semoryear+"-"+sem+" Faculties Group");
							
							JPanel contactlistpanel=createPanel(a.getProfilePic(),group.getGroupName(),group.getGroupName(),false);
							contactlistpanel.setName(group.getGroupName()+"#"+group.getGroupName()+"#"+total);
							contactlistpanel.setLocation(0, location);
							location+=60;
							total++;
							contactlist.add(contactlistpanel);
							add(contactlistpanel);
							
							
							ContactInfo cf=new ContactInfo();
							cf.setGroup(group);
							contactinfo.add(cf);
							
						}
					}
					if(!slastcondition.isEmpty())
					{
						int members=new StudentData().getTotalStudentInCurriculum(curriculumcode, sem);
						if(members>1)
						{
							
							Group group=new Group();
							group.setCurriculumCode(curriculumcode);
							group.setSem(sem);
							group.setMembers(members);
							group.setImage(a.getProfilePic());
							String semoryear=new CurriculumData().getsemoryear(curriculumcode);
							group.setGroupName(curriculumcode+" "+semoryear+"-"+sem+" Students Group");
							
							JPanel contactlistpanel=createPanel(a.getProfilePic(),group.getGroupName(),group.getGroupName(),false);
							contactlistpanel.setName(group.getGroupName()+"#"+group.getGroupName()+"#"+total);
							contactlistpanel.setLocation(0, location);
							location+=60;
							total++;
							contactlist.add(contactlistpanel);
							add(contactlistpanel);
							
							
							ContactInfo cf=new ContactInfo();
							cf.setGroup(group);
							contactinfo.add(cf);
							
						}
					}
				
			
		}
		//Adding  Admin contact
		{
			addLabel("Admin");
			Admin a=new AdminData().getAdminData();
			JPanel contactlistpanel=createPanel(a.getProfilePic(),"Principal","Admin",a.getActiveStatus());
			contactlistpanel.setName("Principal-Admin"+"#"+"Admin"+"#"+total);
			total++;
			contactlistpanel.setLocation(0, location);
			location+=60;
			contactlist.add(contactlistpanel);
			add(contactlistpanel);
			ContactInfo cf=new ContactInfo();
			cf.setAdmin(new AdminData().getAdminData());
			contactinfo.add(cf);
		}
		
		//Adding Faculties
		{
			
				ArrayList<Instructor> flist=new InstructorData().getTotalInstructor(" where curriculumcode='"+curriculumcode+"' and semoryear="+sem+flastcondition);
				if(flist.size()>0)
				{
					addLabel("Faculties");
				}
				for(Instructor f: flist)
				{
					JPanel contactlistpanel=createPanel(f.getProfilePic(),f.getInstructorName()+"-"+f.getInstructorId(),f.getInstructorId()+"",f.getActiveStatus());
					contactlistpanel.setName(f.getInstructorName()+"-"+f.getInstructorId()+" instructor#"+f.getInstructorId()+"#"+total);
					total++;
					contactlistpanel.setLocation(0, location);
					location+=60;
					contactlist.add(contactlistpanel);
					add(contactlistpanel);
					ContactInfo cf=new ContactInfo();
					cf.setInstructor(f);
					contactinfo.add(cf);
				}
		}
		
//		Adding Students
		{
			
			ArrayList<Student> slist=new StudentData().getStudentsDetails(" where curriculumcode='"+curriculumcode+"' and semoryear="+sem+slastcondition);
			if(slist.size()>0)
			{
				addLabel("Students");
			}
			for(Student s: slist)
			{
				JPanel contactlistpanel=createPanel(s.getProfilePic(),s.getFullName()+"-"+s.getUserId(),s.getUserId(),s.getActiveStatus());
				contactlistpanel.setName(s.getFullName()+"-"+s.getUserId()+" student#"+s.getUserId()+"#"+total);
				total++;
				contactlistpanel.setLocation(0, location);
				location+=60;
				contactlist.add(contactlistpanel);
				add(contactlistpanel);
				ContactInfo cf=new ContactInfo();
				cf.setStudent(s);
				contactinfo.add(cf);
			}
			
		}	
	}
	
	//For Admin
	public void getAllcontacts()
	{

		//Adding groups
		{
			String[] str=new CurriculumData().getCurriculumcode();
			Admin a=new AdminData().getAdminData();
			boolean added=false;		
			for(String curriculum:str)
			{
				
				
				int sem=new CurriculumData().getTotalsemoryear(new CurriculumData().getcurriculumname(curriculum));
				String semoryear=new CurriculumData().getsemoryear(curriculum);
				for(int i=1; i<=sem; i++)
				{
					
					int members=new StudentData().getTotalStudentInCurriculum(curriculum, i)+new InstructorData().getTotalFaculaty(curriculum, i)+1;
					if(members>1)
					{
						if(!added)
						{
							addLabel("Groups");	
							added=true;
						}
						Group group=new Group();
						group.setCurriculumCode(curriculum);
						group.setSem(i);
						group.setMembers(members);
						group.setImage(a.getProfilePic());
						group.setGroupName(curriculum+" "+semoryear+"-"+i+" Official Group");
						
					JPanel contactlistpanel=createPanel(a.getProfilePic(),group.getGroupName(),group.getGroupName(),false);
					contactlistpanel.setName(group.getGroupName()+"#"+group.getGroupName()+"#"+total);
					contactlistpanel.setLocation(0, location);
					location+=60;
					total++;
					contactlist.add(contactlistpanel);
					add(contactlistpanel);
					ContactInfo cf=new ContactInfo();
					cf.setGroup(group);
					
					contactinfo.add(cf);
					}
				}
			}
		}
		
		//Adding Faculties
		{
			
				ArrayList<Instructor> flist=new InstructorData().getTotalInstructor("");
				if(flist.size()>0)
				{
					addLabel("Faculties");
					
				}
				for(Instructor f: flist)
				{
					JPanel contactlistpanel=createPanel(f.getProfilePic(),f.getInstructorName()+"-"+f.getInstructorId(),f.getInstructorId()+"",f.getActiveStatus());
					contactlistpanel.setName(f.getInstructorName()+"-"+f.getInstructorId()+"instructor#"+f.getInstructorId()+"#"+total);
					contactlistpanel.setLocation(0, location);
					location+=60;
					total++;
					contactlist.add(contactlistpanel);
					add(contactlistpanel);
					ContactInfo cf=new ContactInfo();
					cf.setInstructor(f);
					contactinfo.add(cf);
				}
		}
		
//		Adding Students
		{
			
			ArrayList<Student> slist=new StudentData().getStudentsDetails("");
			if(slist.size()>0)
			{
				addLabel("Students");
			}
			for(Student s: slist)
			{
				JPanel contactlistpanel=createPanel(s.getProfilePic(),s.getFullName()+"-"+s.getUserId(),s.getUserId(),s.getActiveStatus());
				contactlistpanel.setName(s.getFullName()+"-"+s.getUserId()+" student#"+s.getUserId()+"#"+total);
				contactlistpanel.setLocation(0, location);
				location+=60;
				total++;
				contactlist.add(contactlistpanel);
				add(contactlistpanel);
				ContactInfo cf=new ContactInfo();
				cf.setStudent(s);
				contactinfo.add(cf);
			}
			
		}	
		
	}
	public void addLabel(String name)
	{
		JLabel grouplabel=new JLabel(name);
		grouplabel.setFont(new Font("Segoe UI",Font.PLAIN,17));
		grouplabel.setForeground(new Color(25,25,25));
		grouplabel.setBackground(new Color(245,245,245));
		grouplabel.setBounds(0, location,330,25);
		location+=25;
		grouplabel.setOpaque(true);
		grouplabel.setBorder(new EmptyBorder(3,15,3,240));
		add(grouplabel);
	}
	public JPanel createPanel(Image image,String username,String userid,boolean isactive)
	{
		JPanel usernamepanel=new JPanel();
		usernamepanel.setLayout(null);
		usernamepanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		usernamepanel.setSize(330,60);
		usernamepanel.setBackground(Color.white);
		usernamepanel.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							chatmainpanel.searchfield.setFocusable(false);
							JPanel panel=(JPanel) e.getSource();
						
							for(JPanel p:contactlist)
							{
								p.setBackground(Color.white);
								for(Component c:p.getComponents())
								{
									if(c.getName()!=null && c.getName().equals("lastmessage"))
									{
										if(!c.getForeground().equals(Color.DARK_GRAY))
										{
											c.setForeground(Color.gray);
										}
									}
									else if(c.getName()!=null &&c.getName().equals("messagetime")&&c.getForeground().equals(new Color(30,178,170)))
									{
										
										c.setForeground(Color.gray);
									}
									else if(c.getName()!=null&&c.getName().equals("username"))
									{
										c.setForeground(Color.DARK_GRAY);
									}
									else if(c.getName()!=null && c.getName().equals("messagetime"))
									{
										c.setForeground(Color.gray);
									}
									
								}
							}
							panel.setBackground(new Color(30,178,170));
							for(Component c:panel.getComponents())
							{
								c.setForeground(Color.white);
								if(c.getName()!=null && c.getName().equals("lastmessage"))
								{
									c.setFont(new Font("Segoe UI",Font.PLAIN,13));
								}
								if(c.getName()!=null && c.getName().equals("totalnewmessages"))
								{
									c.setVisible(false);
								}
								
							}
							StringTokenizer str=new StringTokenizer(panel.getName(),"#");
							str.nextToken();
							str.nextToken();
							int pos=Integer.parseInt(str.nextToken());
							ContactInfo cf=contactinfo.get(pos);
							String s=cf.getClassName();
							if(s.equals("Student"))
							{
								chatmainpanel.chatinfopanel.setData(cf.getStudent());
								chatmainpanel.chatpanel.setToUserData(s,cf.getStudent().getUserId(), cf.getStudent().getFullName()+"-"+cf.getStudent().getUserId(), cf.getStudent().getProfilePic(), cf.getStudent().getLastLogin(),cf.getStudent().getActiveStatus());
							}
							else if(s.equals("Instructor"))
							{
								chatmainpanel.chatinfopanel.setData(cf.getInstructor());
								chatmainpanel.chatpanel.setToUserData(s,cf.getInstructor().getInstructorId()+"",cf.getInstructor().getInstructorName()+"-"+cf.getInstructor().getInstructorId(),cf.getInstructor().getProfilePic(),cf.getInstructor().getLastLogin(),cf.getInstructor().getActiveStatus());
							}
							else if(s.equals("Group"))
							{
								chatmainpanel.chatinfopanel.setData(cf.getGroup());
								chatmainpanel.chatpanel.setToUserData(s,cf.getGroup().getGroupName(),cf.getGroup().getGroupName(),cf.getGroup().getImage(), cf.getGroup().getMembers()+" Members",false);
							}
							else
							{
								chatmainpanel.chatinfopanel.setData(cf.getAdmin());	
								chatmainpanel.chatpanel.setToUserData(s,"Admin", "Principal", cf.getAdmin().getProfilePic() ,cf.getAdmin().getLastLogin(),cf.getAdmin().getActiveStatus());
							}
						}
					}
					public void mouseEntered(MouseEvent e)
					{
						JPanel panel=(JPanel) e.getSource();
						if(panel.getBackground()==Color.white)
						{
						panel.setBackground(new Color(245,245,245));
						}
					}
					public void mouseExited(MouseEvent e)
					{
						JPanel panel=(JPanel) e.getSource();
						if(panel.getBackground().equals(new Color(245,245,245)))
						{
							panel.setBackground(Color.white);
						}
					}
					
					
				});
		
		
		BufferedImage profilepic=ImageUtil.toBufferedImage(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		profilepic=ImageUtil.makeRoundedCorner(profilepic, 50);
		JLabel profilepiclabel=new JLabel(new ImageIcon(profilepic));
		profilepiclabel.setLocation(10, 5);
		profilepiclabel.setSize(50, 50);
		JLabel onlinestatus = new JLabel(new ImageIcon("./assets/onlinestatus.png"));
		onlinestatus.setBounds(45, 40, 15, 15);
		usernamepanel.add(onlinestatus);
		onlinestatus.setName("onlinestatus");
		onlinestatus.setVisible(false);
		if(isactive)
		{
			onlinestatus.setVisible(true);
		}
		usernamepanel.add(profilepiclabel);
	
		
		JLabel usernamelabel=new JLabel(username);
		usernamelabel.setName("username");
		usernamelabel.setFont(new Font("Segoe UI",Font.BOLD,15));
		usernamelabel.setLocation(70, 5);
		usernamelabel.setSize(180, 30);
		usernamepanel.add(usernamelabel);
		
		JLabel messagetimelabel=new JLabel();
		messagetimelabel.setForeground(new Color(30,178,170));
		messagetimelabel.setFont(new Font("Segoe UI",Font.PLAIN,10));
		messagetimelabel.setLocation(250, 6);
		messagetimelabel.setName("messagetime");
		messagetimelabel.setSize(70, 30);
		messagetimelabel.setHorizontalAlignment(JLabel.RIGHT);
		usernamepanel.add(messagetimelabel);
		
		
		
		JLabel totalnewmessages=new JLabel();
		totalnewmessages.setLocation(290, 30);
		totalnewmessages.setText("0");
		totalnewmessages.setName("totalnewmessages");
		totalnewmessages.setSize(60,22);
		totalnewmessages.setFont(new Font("Arial",Font.BOLD,10));
		totalnewmessages.setForeground(Color.white);
		totalnewmessages.setHorizontalTextPosition(JLabel.CENTER);
		totalnewmessages.setVerticalTextPosition(JLabel.CENTER);
		usernamepanel.add(totalnewmessages);
		
		
		JLabel lastlabel=new JLabel();
		lastlabel.setForeground(Color.gray);
		lastlabel.setName("lastmessage");
		lastlabel.setFont(new Font("Segoe UI",Font.PLAIN,13));
		if(!userid.isEmpty())
		{
			NewMessage newmessage=chatdata.getNewMessages(this.userid,userid);
			if(newmessage.total==0)
			{
				messagetimelabel.setForeground(Color.gray);
				lastlabel.setFont(new Font("Segoe UI",Font.PLAIN,13));
				lastlabel.setForeground(Color.gray);
				totalnewmessages.setVisible(false);
				messagetimelabel.setText(newmessage.messagetime);
			}
			else
			{
				lastlabel.setFont(new Font("Segoe UI",Font.BOLD,13));
				lastlabel.setForeground(Color.DARK_GRAY);
				totalnewmessages.setVisible(true);
				totalnewmessages.setText(newmessage.total+"");
				messagetimelabel.setText(newmessage.messagetime);
				if(newmessage.total>99)
				{
					totalnewmessages.setIcon(new ImageIcon(messagecount.getScaledInstance(24+totalnewmessages.getText().length(), 24, Image.SCALE_SMOOTH)));
				}
				else
				{
					totalnewmessages.setIcon(new ImageIcon(messagecount));
				}
			}
			
			lastlabel.setText(newmessage.message);
		}
		else 
		{
			lastlabel.setText("Start new Conversion");
		}
		lastlabel.setBackground(Color.white);
		lastlabel.setSize(220, 30);
		lastlabel.setLocation(70, 25);
		usernamepanel.add(lastlabel);
		
	
		return usernamepanel;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		for(int i=0; i<contactinfo.size(); i++)
		{
			ContactInfo cf=contactinfo.get(i);
			String s=cf.getClassName();
			JPanel panel=contactlist.get(i);
			if(s.equals("Student"))
			{
				cf.getStudent().setActiveStatus(new StudentData().isActive(cf.getStudent().getUserId()));
				cf.getStudent().setLastLogin(new StudentData().getLastLogin(cf.getStudent().getUserId()));
				for(Component c:panel.getComponents())
				{
					if(c.getName()!=null&&c.getName().equals("onlinestatus"))
					{
						if(cf.getStudent().getActiveStatus())
						{
							c.setVisible(true);
						}
						else
						{
							c.setVisible(false);
						}
						if(panel.getBackground().equals(new Color(30,178,170)))
						{
							chatmainpanel.chatinfopanel.setData(cf.getStudent());
							chatmainpanel.chatpanel.setToUserData("Student",cf.getStudent().getUserId(), cf.getStudent().getFullName()+"-"+cf.getStudent().getUserId(), cf.getStudent().getProfilePic(), cf.getStudent().getLastLogin(),cf.getStudent().getActiveStatus());
						}
					}
				}
			}
			else if(s.equals("Instructor"))
			{
				cf.getInstructor().setActiveStatus(new InstructorData().isActive(cf.getInstructor().getInstructorId()+""));
				cf.getInstructor().setLastLogin(new InstructorData().getLastLogin(cf.getInstructor().getInstructorId()+""));
				
				for(Component c:panel.getComponents())
				{
					if(c.getName()!=null&&c.getName().equals("onlinestatus"))
					{
						if(cf.getInstructor().getActiveStatus())
						{
							c.setVisible(true);
						}
						else
						{
							c.setVisible(false);
						}
						if(panel.getBackground().equals(new Color(30,178,170)))
						{
							chatmainpanel.chatinfopanel.setData(cf.getInstructor());
							chatmainpanel.chatpanel.setToUserData("Instructor",cf.getInstructor().getInstructorId()+"",cf.getInstructor().getInstructorName()+"-"+cf.getInstructor().getInstructorId(),cf.getInstructor().getProfilePic(),cf.getInstructor().getLastLogin(),cf.getInstructor().getActiveStatus());
						}
						
					}
				}
			}
			else  if(s.equals("Admin"))
			{
				cf.getAdmin().setActiveStatus(new AdminData().isActive());
				cf.getAdmin().setLastLogin(new AdminData().getLastLogin());
				for(Component c:panel.getComponents())
				{
					if(c.getName()!=null&&c.getName().equals("onlinestatus"))
					{
						if(cf.getAdmin().getActiveStatus())
						{
							c.setVisible(true);
						}
						else
						{
							c.setVisible(false);
						}
						if(panel.getBackground().equals(new Color(30,178,170)))
						{
							chatmainpanel.chatinfopanel.setData(cf.getAdmin());	
							chatmainpanel.chatpanel.setToUserData("Admin","Admin", "Principal", cf.getAdmin().getProfilePic() ,cf.getAdmin().getLastLogin(),cf.getAdmin().getActiveStatus());
						}
						
					}
				}
			}
			else if(s.equals("Group"))
			{
				if(panel.getBackground().equals(new Color(30,178,170)))
				{
					chatmainpanel.chatinfopanel.setData(cf.getGroup());
					chatmainpanel.chatpanel.setToUserData("Group",cf.getGroup().getGroupName(),cf.getGroup().getGroupName(),cf.getGroup().getImage(), cf.getGroup().getMembers()+" Members",false);
				}
			}
		}
		for(JPanel p:contactlist)
		{
			StringTokenizer str=new StringTokenizer(p.getName(),"#");
			str.nextToken();
			String userid=str.nextToken();
			NewMessage	newmessage=chatdata.getNewMessages(this.userid,userid);
			for(Component c:p.getComponents())
			{
				
//				
				{
					if(c.getName()!=null && c.getName().equals("lastmessage"))
					{
						
						if(newmessage.total!=0)
						{
							c.setFont(new Font("Segoe UI",Font.BOLD,13));
							c.setForeground(Color.DARK_GRAY);
						}
						((JLabel) c).setText(newmessage.message);
					}
					if(c.getName()!=null&&c.getName().equals("messagetime"))
					{ 
						
						
						if(newmessage.total!=0)
						{
							c.setForeground(new Color(20,178,170));
						}
						((JLabel) c).setText(newmessage.messagetime);
					
					}
					if(c.getName()!=null&&c.getName().equals("totalnewmessages"))
					{ 
						
						
						
						if(newmessage.total!=0)
						{
							((JLabel) c).setText(newmessage.total+"");
							c.setVisible(true);
							c.setForeground(Color.white);
							if(newmessage.total>99)
							{
								((JLabel) c).setIcon(new ImageIcon(messagecount.getScaledInstance(24+((JLabel) c).getText().length(), 24, Image.SCALE_SMOOTH)));
							}
							else
							{
								((JLabel) c).setIcon(new ImageIcon(messagecount));
							}
						}
						
					}
				}
			}
			
			if(p.getBackground().equals(new Color(30,178,170)))
			{
				for(Component c:p.getComponents())
				{
					c.setForeground(Color.white);
					if(c.getName()!=null && c.getName().equals("lastmessage"))
					{
						c.setFont(new Font("Segoe UI",Font.PLAIN,13));
					}
					if(c.getName()!=null && c.getName().equals("totalnewmessages"))
					{
						c.setVisible(false);
					}
				}
			}
		}
		this.repaint();
	}
	public void filterContact(String search)
	{
		this.removeAll();
		this.setVisible(false);
		location=0;
		boolean group=false;
		boolean students=false;
		boolean faculties=false;
		for(JPanel p:contactlist)
		{
			StringTokenizer str=new StringTokenizer(p.getName(),"#");
			String contactname=str.nextToken();
			contactname=contactname.toLowerCase();
			search=search.trim();
			search=search.toLowerCase();
			if(contactname.contains(search))
			{
				if(contactname.contains("group")&&!group)
				{
					addLabel("Groups");
					group=true;
				}
				if(contactname.contains("student")&&!contactname.contains("group")&&!students)
				{
					addLabel("Students");
					students=true;
				}
				if(contactname.contains("instructor")&&!contactname.contains("group")&&!faculties)
				{
					addLabel("Faculties");
					faculties=true;
				}
				if(contactname.contains("admin")&&contactname.contains("principal"))
				{
					addLabel("Admin");
					
				}
				add(p);
				p.setLocation(0, location);
				location+=60;
				
				
			}
			
			
		}
		this.setVisible(true);
		
		
	}
}
class ContactInfo
{
	private Instructor instructor;
	private Student student;
	private Group group;
	private Admin admin;
	
	public void setInstructor(Instructor instructor)
	{
			this.instructor=instructor;
	}
	public void setStudent(Student student)
	{
		this.student=student;
	}
	public void setGroup(Group group)
	{
		this.group=group;
	}
	public void setAdmin(Admin admin)
	{
		this.admin=admin;
	}

	public Instructor getInstructor()
	{
		return instructor;
	}
	public Student getStudent()
	{
		return student;
	}
	public Group getGroup()
	{
		return group;
	}
	public Admin getAdmin()
	{
		return admin;
	}
	public String getClassName()
	{
		if(student!=null)
		{
			return "Student";
		}
		else if(instructor!=null)
		{
			return "Instructor";
		}
		else if(group!=null) 
		{
			return "Group";
		}
		else if(admin!=null)
		{
			return "Admin";
		}
			
		return null;
	}
}
