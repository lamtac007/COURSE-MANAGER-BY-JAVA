package curriculum;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import general.HintTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


//Purpose : Dialog for adding new curriculum

@SuppressWarnings("serial")
public class AddCurriculumDialog extends JDialog implements ActionListener
{

	private JTextField curriculumcodefield;
	private JTextField curriculumnamefield;
	private JTextField totalsemoryearfield;
	private JComboBox<String> semoryearcombo;
	private JLabel lblError;
	private CurriculumPanel curriculumpanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCurriculumDialog dialog = new AddCurriculumDialog();
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddCurriculumDialog(CurriculumPanel curriculumpanel)
	{
		this();
		this.curriculumpanel=curriculumpanel;
	}
	public AddCurriculumDialog() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 476, 452);
		getContentPane().setLayout(null);
		
		JLabel lblAddNewCurriculum = new JLabel("Add New Curriculum");
		lblAddNewCurriculum.setForeground(new Color(255, 255, 255));
		lblAddNewCurriculum.setBackground(new Color(32, 178, 170));
		lblAddNewCurriculum.setOpaque(true);
		lblAddNewCurriculum.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewCurriculum.setBounds(0, 0, 473, 55);
		getContentPane().add(lblAddNewCurriculum);
		
		JLabel lblCurriculumCode = new JLabel("Curriculum Code ");
		lblCurriculumCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCurriculumCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCurriculumCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurriculumCode.setBounds(10, 79, 139, 24);
		lblCurriculumCode.setFocusable(true);
		getContentPane().add(lblCurriculumCode);
		
		JLabel lblCurriculumName = new JLabel("Curriculum Name ");
		lblCurriculumName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurriculumName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCurriculumName.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblCurriculumName.setBounds(10, 147, 139, 24);
		getContentPane().add(lblCurriculumName);
		
		JLabel lblSemyear = new JLabel("Sem/Year");
		lblSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblSemyear.setBounds(10, 218, 139, 24);
		getContentPane().add(lblSemyear);
		
		curriculumcodefield = new HintTextField("");
		curriculumcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		curriculumcodefield.setBounds(157, 72, 292, 40);
		getContentPane().add(curriculumcodefield);
		curriculumcodefield.setColumns(10);
		
		curriculumnamefield = new HintTextField("");
		curriculumnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		curriculumnamefield.setColumns(10);
		curriculumnamefield.setBounds(159, 141, 292, 40);
		getContentPane().add(curriculumnamefield);
		
		totalsemoryearfield = new HintTextField("");
		totalsemoryearfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		totalsemoryearfield.setColumns(10);
		totalsemoryearfield.setBounds(157, 278, 292, 40);
		getContentPane().add(totalsemoryearfield);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Select Sem/Year---", "sem", "year"}));
		semoryearcombo.setBounds(159, 210, 292, 40);
		getContentPane().add(semoryearcombo);
		
		JLabel lblTotalSemyear = new JLabel("Total Sem/Year");
		lblTotalSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblTotalSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblTotalSemyear.setBounds(10, 284, 139, 24);
		getContentPane().add(lblTotalSemyear);
		
		JButton addcurriculumbutton = new JButton("Add Curriculum");
		addcurriculumbutton.setBackground(new Color(32, 178, 170));
		addcurriculumbutton.setForeground(new Color(255, 255, 255));
		addcurriculumbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		addcurriculumbutton.setBounds(310, 373, 139, 37);
		addcurriculumbutton.addActionListener(this);
		getContentPane().add(addcurriculumbutton);
		
		lblError=new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(157,115,355,21);
		getContentPane().add(lblError);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("This is required question !");
		String curriculumname=curriculumnamefield.getText();
		String curriculumcode=curriculumcodefield.getText();
		String semoryear=(String) semoryearcombo.getSelectedItem();
		String strtotalsemoryear=totalsemoryearfield.getText();
	 	if(curriculumcode.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(curriculumcodefield.getX(), curriculumcodefield.getY()+curriculumcodefield.getHeight(), lblError.getWidth(), 21);
			curriculumcodefield.setFocusable(true);
		}
	
		else if(curriculumname.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(curriculumnamefield.getX(), curriculumnamefield.getY()+curriculumnamefield.getHeight(), lblError.getWidth(), 21);
			curriculumnamefield.setFocusable(true);
		}
		else if(semoryearcombo.getSelectedIndex()==0)
		{
			lblError.setVisible(true);
			lblError.setBounds(semoryearcombo.getX(), semoryearcombo.getY()+semoryearcombo.getHeight(),  lblError.getWidth(), 21);
			
		}
		else if(strtotalsemoryear.isEmpty())
		{
			
			lblError.setVisible(true);
			lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
			totalsemoryearfield.setFocusable(true);
		}
		
		else
		{
			 
				try
				{
					int totalsemoryear=Integer.parseInt(strtotalsemoryear);
					if(new CurriculumData().isCurriculumCodeExist(curriculumcode.toUpperCase()))
					{
						lblError.setVisible(true);
						lblError.setBounds(curriculumcodefield.getX(), curriculumcodefield.getY()+curriculumcodefield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Curriculum code already exist !");
					}
					else if(new CurriculumData().isCurriculumNameExist(curriculumname))
					{
						lblError.setVisible(true);
						lblError.setBounds(curriculumnamefield.getX(), curriculumnamefield.getY()+curriculumnamefield.getHeight(), lblError.getWidth(), 21);
						curriculumnamefield.setFocusable(true);
						lblError.setText("Curriculum name already exist !");
					}
					else if(totalsemoryear<1)
					{
						lblError.setVisible(true);
						lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Minimun 1 sem/year required !");
					}
					else
					{
						CurriculumData c=new CurriculumData();
						int result=c.addCurriculum(curriculumcode, curriculumname, semoryear, totalsemoryear);
						if(result>0)
						{
							
							if(curriculumpanel!=null)
							{
							curriculumpanel.updatetableData();
							}
							this.dispose();
						}
						
					}
				}
				catch(NumberFormatException nexp)
				{
					lblError.setVisible(true);
					lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
					lblError.setText("Characters are not allowed !");
				}
				
		
		}
		
	}
	
}
