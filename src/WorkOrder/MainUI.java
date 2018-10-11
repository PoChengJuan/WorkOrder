package WorkOrder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class MainUI implements method{

	private JFrame frame;
	private JTextField NumField;
	private static DefaultComboBoxModel<String> BoxModel = new DefaultComboBoxModel<String>(new String[] {}) ; 
	private final String Version = "0.0.1";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setLocationRelativeTo(null);
					window.frame.setTitle("iPQRS Machine Register");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 394, 253);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		System.out.println(System.getProperty("os.name").toString());
		
		JLabel VersionLabel = new JLabel("New label");
		VersionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		VersionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		VersionLabel.setBounds(217, 198, 157, 16);
		VersionLabel.setText("Version : " + Version);
		frame.getContentPane().add(VersionLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 376, 208);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMachineType = new JLabel("Machine Type");
		lblMachineType.setBounds(14, 56, 90, 16);
		panel.add(lblMachineType);
		
		JLabel lblMachineNum = new JLabel("Machine Num.");
		lblMachineNum.setBounds(14, 95, 90, 16);
		panel.add(lblMachineNum);
		
		
		
		NumField = new JTextField();
		NumField.setBounds(116, 91, 130, 26);
		NumField.setToolTipText("Input the machine Number.");
		panel.add(NumField);
		NumField.setColumns(10);
		
		JComboBox<String> CreatorBox = new JComboBox<>();
		CreatorBox.setBounds(116, 13, 130, 25);
		panel.add(CreatorBox);
		
		JComboBox<String> TypeBox = new JComboBox<>();
		TypeBox.setBounds(116, 51, 130, 27);
		TypeBox.setToolTipText("Chioce the machine type.");
		String data = "";
		try {
			int x = 0;
			
			FileReader fr = new FileReader(getFilePath());
			BufferedReader br = new BufferedReader(fr);
			
			while((data = br.readLine())!=null)
			{
				if(!(data.isEmpty())) {
					if(data.equals("[Type]")) {
						x = 1;
						continue;
					}else if(data.equals("[Creator]")) {
						x = 2;
						continue;
					}
					switch(x) {
					case 1:
						//TypeBox.addItem(data);
						BoxModel.addElement(data);
						break;
					case 2:
						CreatorBox.addItem(data);
						break;
					}
				}
			}
			TypeBox.setModel(BoxModel);
			fr.close();
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(TypeBox);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(129, 163, 117, 36);
		btnSend.setToolTipText("Register the machine Number.");
		panel.add(btnSend);
		
		JRadioButton DualButton = new JRadioButton("Dual AMC PS");
		DualButton.setBounds(256, 90, 127, 27);
		DualButton.setToolTipText("Check Dual AMC PS or not.");
		panel.add(DualButton);
		
		JLabel lblCreat = new JLabel("Creator");
		lblCreat.setBounds(14, 16, 57, 19);
		panel.add(lblCreat);
		
		JButton btnCheck = new JButton("Check The Last Number");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new CheckNum()
				MachineData Data = new MachineData();
				Data.Method = CHECK_FUNCTION;
				Data.Type = TypeBox.getSelectedItem().toString();
				Data.Num = NumField.getText();
				Data.Creator = CreatorBox.getSelectedItem().toString();
				new Function(Data);
			}
		});
		btnCheck.setBounds(92, 124, 191, 27);
		btnCheck.setToolTipText("Check the last machine type and number.");
		panel.add(btnCheck);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1 = "";
				for(int i = 0;i < TypeBox.getItemCount();i++)
				{
					str1 = str1 + TypeBox.getItemAt(i).toString();
					str1 = str1 + "\n";
				}
				new AddType(str1);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Qoo\\eclipse-workspace\\WorkOrder\\ref\\Plus.png"));
		btnNewButton.setBounds(260, 52, 31, 26);
		btnNewButton.setOpaque(false);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date( );
			    SimpleDateFormat date_ft = new SimpleDateFormat ("yyyy-MM-dd");
				MachineData Data = new MachineData();
				
				Data.Method = ADD_FUNCTION;
				Data.Type = TypeBox.getSelectedItem().toString();
				Data.Num = NumField.getText();
				Data.Creator = CreatorBox.getSelectedItem().toString();
				if(Boolean.toString(DualButton.isSelected()).equals("true")) {
					Data.Dual_AMU_PS = "1";
				}else
				{
					Data.Dual_AMU_PS = "0";
				}
				Data.MFG_Start_Date = date_ft.format(date);
				Data.WorkOrder_SN = TypeBox.getSelectedItem().toString() + NumField.getText();

				//new Function(Creator,NewType,NewNum,DualButton);
				new Function(Data);
				//MessageBox.
				
			}
		});
	}
	
	public String getFilePath(){
		StringBuffer path = new StringBuffer();
		String out = null;
		//System.out.println(getClass().getResource("").toString());
		if(System.getProperty("os.name").equals("Mac OS X")){
			path.append(getClass().getResource("").toString());
			path.delete(0,5);
			path.delete(path.length()-14, path.length());
			path.append("ref/Type.txt");
			out = path.toString();
		}else{
			path.append(getClass().getResource("").toString());
			path.delete(0,8);
			System.out.println(path.toString());
			path.delete(path.length()-14, path.length());
			System.out.println(path.toString());
			path.append("ref/Type.txt");
			System.out.println(path.toString());
			out = path.toString();
		}
		
		return out;
	}

	public static void UpdateTypeBox(String str) {
		// TODO Auto-generated method stub
		String[] tokens = str.split("\n");
		BoxModel.removeAllElements();
		for(String token:tokens) {
			System.out.println(token);
			BoxModel.addElement(token);
		}		
	}
}

