package general;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import curriculum.CurriculumData;
import instructor.Instructor;
import instructor.InstructorData;
import instructor.InstructorPanel;
import instructor.ViewInstructorPanel;
import student.Student;
import student.StudentData;
import student.StudentPanel;
import student.ViewStudentPanel;


//Purpose : To displaying students and faculties image
@SuppressWarnings("serial")
public class PhotoViewPanel extends JPanel {
	int xpos[];
	JPanel panel[][];
	JLabel profilepiclabel[][];
	JLabel namelabel[][];
	JLabel degreelabel[][];
	int totalfaculties=-1;
	int totalstudents=-1;
	int maxphotosinrow=3;
	int incrementx=0;
	int incrementy=0;
	InstructorPanel fp;
	StudentPanel sp;
	/**
	 * Create the panel.
	 * 
	 */
	 
	@Override
	public Dimension getPreferredSize()
	{
		int n=0;
		if(totalfaculties!=-1)
		{
		  n=totalfaculties;
		}
		if(totalstudents!=-1)
		{
			n=totalstudents;
		}
		 int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		 if(row==1)
		 {
			 return new Dimension(xpos[maxphotosinrow-1]+xpos[1]-xpos[0],incrementy+20);
		 }
		 
	    return new Dimension( 1116,row*(incrementy));
	}
	public PhotoViewPanel(InstructorPanel instructorPanel,int maxphoto) {
		
		this.maxphotosinrow=maxphoto;
		this.setFocusable(true);
		this.fp=instructorPanel;
		xpos=new int[maxphotosinrow];
		incrementx=(4*270)/maxphotosinrow;
		int start=20;
		
		for(int i=0; i<maxphotosinrow; i++)
		{
			xpos[i]=start;
			start+=(incrementx);
		}
		 incrementy=incrementx+50;
		totalfaculties=fp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalfaculties;
		  int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		  int lastcolumn=n%maxphotosinrow==0?maxphotosinrow:n%maxphotosinrow;
	
		  panel=new JPanel[row][maxphotosinrow];
		  profilepiclabel=new JLabel[row][maxphotosinrow];
		  namelabel=new JLabel[row][maxphotosinrow];
		  degreelabel=new JLabel[row][maxphotosinrow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxphotosinrow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
					int fid=Integer.parseInt(fp.table.getValueAt(index-1, 0)+"");
					Instructor f=new InstructorData().getInstructorInfobyId(fid);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementx-20, incrementy-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(f.getInstructorName());
				  add(panel[i][j]);
				  panel[i][j].setLayout(null);
				  panel[i][j].setName(f.getInstructorId()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
				  {
					  @Override
			  			public void mousePressed(MouseEvent e)
			  			{
						  	if(e.getButton()==MouseEvent.BUTTON1)
						  	{
			  					
			  					Instructor f=new InstructorData().getInstructorInfobyId(Integer.parseInt(e.getComponent().getName()));
								if(fp.am!=null)
								{
									fp.am.viewinstructorpanel = new ViewInstructorPanel(f, fp.am, fp);
									fp.am.viewinstructorpanel.setVisible(true);
									fp.am.instructorpanel.setVisible(false);
									fp.am.viewinstructorpanel.setLocation(fp.am.panelx, fp.am.panely);
									fp.am.viewinstructorpanel.setVisible(true);
									fp.am.viewinstructorpanel.setFocusable(true);
									fp.am.contentPane.add(fp.am.viewinstructorpanel);
								}
								else if(fp.fm!=null)
								{
									fp.fm.viewinstructorpanel = new ViewInstructorPanel(f, fp.fm, fp);
									fp.fm.viewinstructorpanel.setVisible(true);
									fp.fm.instructorpanel.setVisible(false);
									fp.fm.viewinstructorpanel.setLocation(fp.fm.panelx, fp.fm.panely);
									fp.fm.viewinstructorpanel.setVisible(true);
									fp.fm.viewinstructorpanel.setFocusable(true);
									fp.fm.contentPane.add(fp.fm.viewinstructorpanel);
								}
								else if(fp.sm!=null)
								{
									fp.sm.viewinstructorpanel = new ViewInstructorPanel(f, fp.sm, fp);
									fp.sm.viewinstructorpanel.setVisible(true);
									fp.sm.instructorpanel.setVisible(false);
									fp.sm.viewinstructorpanel.setLocation(fp.sm.panelx, fp.sm.panely);
									fp.sm.viewinstructorpanel.setVisible(true);
									fp.sm.viewinstructorpanel.setFocusable(true);
									fp.sm.contentPane.add(fp.sm.viewinstructorpanel);
								}
			  				}
			  			}
				  }
				  );
				
				  
				  profilepiclabel[i][j]=new JLabel();
				  profilepiclabel[i][j].setBounds(0, 0, panel[i][j].getWidth()-10,panel[i][j].getHeight()-60);
				  profilepiclabel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  profilepiclabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  profilepiclabel[i][j].setText("image");
				  profilepiclabel[i][j].setIcon(new ImageIcon(f.getProfilePic(profilepiclabel[i][j].getWidth()+((maxphotosinrow*10)/4+1),profilepiclabel[i][j].getHeight())));
				  
				
				  panel[i][j].add(profilepiclabel[i][j]);

				  namelabel[i][j]=new JLabel();
				  namelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  namelabel[i][j].setText(f.getInstructorName());
				  namelabel[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  namelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+5,panel[i][j].getWidth(), 22);
				  panel[i][j].add(namelabel[i][j]);
				  degreelabel[i][j]=new JLabel();
				  degreelabel[i][j].setVerticalAlignment(SwingConstants.TOP);
				  if(f.getPosition().equals("Not Assigned"))
				  {
				  degreelabel[i][j].setText("");
				  }
				  else
				  {
					  degreelabel[i][j].setText(f.getPosition());
				  }
				  degreelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  degreelabel[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  degreelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(degreelabel[i][j]);
				  index++;
				  if(index>totalfaculties)
				  {
					  break;
				  }
			  }
			  column+=incrementy;
		  }

	}
	public PhotoViewPanel(StudentPanel sp,int maxphoto)
	{
		this.maxphotosinrow=maxphoto;
		this.sp=sp;
		xpos=new int[maxphotosinrow];
		 incrementx=(4*270)/maxphotosinrow;
		int start=20;
		
		for(int i=0; i<maxphotosinrow; i++)
		{
			xpos[i]=start;
			start+=(incrementx);
		}
		incrementy=incrementx+50;
		totalstudents=sp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalstudents;
		  int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		  int lastcolumn=n%maxphotosinrow==0?maxphotosinrow:n%maxphotosinrow;
	
		  panel=new JPanel[row][maxphotosinrow];
		  profilepiclabel=new JLabel[row][maxphotosinrow];
		  namelabel=new JLabel[row][maxphotosinrow];
		  degreelabel=new JLabel[row][maxphotosinrow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxphotosinrow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
				  
					String curriculumcode=sp.table.getValueAt(index-1,0)+"";
					String  strsem=sp.table.getValueAt(index-1, 4)+"";
					int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
					String strroll=sp.table.getValueAt(index-1, 1)+"";
					long rollnumber=Long.parseLong(strroll);
					Student s=new StudentData().getStudentDetails(curriculumcode,sem,rollnumber);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementx-20, incrementy-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(s.getFullName());
				  add(panel[i][j]);
				  panel[i][j].setName(s.getSrNo()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
						  {
					  			@Override
					  			public void mousePressed(MouseEvent e)
					  			{
					  				if (e.getButton() == MouseEvent.BUTTON1)
					  				{
					  					Student s=new StudentData().getStudentDetails(Integer.parseInt(e.getComponent().getName()));
					  					
					  					if(sp.am!=null)
					  					{
						  					sp.am.viewstudentpanel=new ViewStudentPanel(s,sp.am,sp);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.studentpanel.setVisible(false);
						  					sp.am.viewstudentpanel.setLocation(sp.am.panelx,sp.am.panely);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.viewstudentpanel.setFocusable(true);
						  					sp.am.contentPane.add(sp.am.viewstudentpanel);
					  					}
					  					else  if(sp.fm!=null)
					  					{
					  						sp.fm.viewstudentpanel=new ViewStudentPanel(s,sp.fm,sp);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.studentpanel.setVisible(false);
						  					sp.fm.viewstudentpanel.setLocation(sp.fm.panelx,sp.fm.panely);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.viewstudentpanel.setFocusable(true);
						  					sp.fm.contentPane.add(sp.fm.viewstudentpanel);
					  					}
					  					else if(sp.sm!=null)
					  					{
					  						sp.sm.viewstudentpanel=new ViewStudentPanel(s,sp.sm,sp);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.studentpanel.setVisible(false);
						  					sp.sm.viewstudentpanel.setLocation(sp.sm.panelx,sp.sm.panely);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.viewstudentpanel.setFocusable(true);
						  					sp.sm.contentPane.add(sp.sm.viewstudentpanel);
					  					}
					  					
					  				}
					  			}
						  }
						  );
				  panel[i][j].setLayout(null);
				
				  
				  profilepiclabel[i][j]=new JLabel();
				  profilepiclabel[i][j].setBounds(0, 0, panel[i][j].getWidth(),panel[i][j].getHeight()-60);
				  profilepiclabel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  profilepiclabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  profilepiclabel[i][j].setText("image");
				  profilepiclabel[i][j].setIcon(new ImageIcon(s.getProfilePic(profilepiclabel[i][j].getWidth()+((maxphotosinrow*10)/4+1),profilepiclabel[i][j].getHeight())));
				  
				
				  panel[i][j].add(profilepiclabel[i][j]);

				  namelabel[i][j]=new JLabel();
				  namelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  namelabel[i][j].setText(s.getFullName());
				
				  namelabel[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  namelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+3,panel[i][j].getWidth(), 22);
				  panel[i][j].add(namelabel[i][j]);
				  degreelabel[i][j]=new JLabel();
				  degreelabel[i][j].setVerticalAlignment(SwingConstants.TOP);
				  degreelabel[i][j].setText(new CurriculumData().getsemoryear(s.getCurriculumCode())+"-"+s.getSemorYear()+" "+" ("+s.getCurriculumCode()+")");
				  degreelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  degreelabel[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  degreelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(degreelabel[i][j]);
				  index++;
				  if(index>totalstudents)
				  {
					  break;
				  }
			  }
			  column+=incrementy;
		  }
		  

	}
	public int changeNameFont()
	  {
		return maxphotosinrow<4?22:maxphotosinrow<8?17:maxphotosinrow<12?13:10;
	  }
	public int changeDegreeFont()
	  {
		return maxphotosinrow<4?18:maxphotosinrow<8?14:maxphotosinrow<12?13:10;
	  }
	
	


}
