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
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainUI {

	private JFrame frame;
	private JTextField NumField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setLocationRelativeTo(null);
					window.frame.setTitle("WorkOrder");
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
		frame.setBounds(100, 100, 394, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		System.out.println(System.getProperty("os.name").toString());
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 376, 198);
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
		panel.add(NumField);
		NumField.setColumns(10);
		
		JComboBox<String> CreatorBox = new JComboBox<>();
		CreatorBox.setBounds(116, 13, 130, 25);
		panel.add(CreatorBox);
		
		JComboBox<String> TypeBox = new JComboBox<>();
		TypeBox.setBounds(116, 51, 130, 27);
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
						TypeBox.addItem(data);
						break;
					case 2:
						CreatorBox.addItem(data);
						break;
					}
				}
				/*
				if(data.equals("[Type]")) {
					while((data = br.readLine())!=null)
					{
						TypeBox.addItem(data);
					}
				}else if(data.equals("[Creator]"))
				{
					while((data = br.readLine())!=null)
					{
						CreatorBox.addItem(data);
					}
				}*/
				
			}
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
		btnSend.setBounds(129, 149, 117, 36);
		panel.add(btnSend);
		
		JRadioButton DualButton = new JRadioButton("Dual AMC PS");
		DualButton.setBounds(256, 51, 127, 27);
		panel.add(DualButton);
		
		JLabel lblCreat = new JLabel("Creator");
		lblCreat.setBounds(14, 16, 57, 19);
		panel.add(lblCreat);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NewType = null;
				String NewNum = null;
				String Creator = null;
				Creator = CreatorBox.getSelectedItem().toString();
				NewType = TypeBox.getSelectedItem().toString();
				NewNum = NumField.getText();
				System.out.println(NewType);
				new Function(Creator,NewType,NewNum,DualButton);
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
}
