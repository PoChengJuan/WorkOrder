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
		frame.setBounds(100, 100, 288, 149);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 276, 116);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMachineType = new JLabel("Machine Type");
		lblMachineType.setBounds(6, 17, 90, 16);
		panel.add(lblMachineType);
		
		JLabel lblMachineNum = new JLabel("Machine Num.");
		lblMachineNum.setBounds(6, 41, 90, 16);
		panel.add(lblMachineNum);
		
		NumField = new JTextField();
		NumField.setBounds(108, 36, 130, 26);
		panel.add(NumField);
		NumField.setColumns(10);
		JComboBox<String> TypeBox = new JComboBox<>();
		TypeBox.setBounds(108, 13, 130, 27);
		String data = "";
		try {
			FileReader fr = new FileReader("/Users/mac/Documents/JavaCode/eclipse-workspace/WorkOrder/ref/Type.txt");
			BufferedReader br = new BufferedReader(fr);
			while((data = br.readLine())!=null)
			{
				TypeBox.addItem(data);
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
		btnSend.setBounds(62, 69, 117, 36);
		panel.add(btnSend);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddType();
			}
		});
		button.setBounds(233, 12, 43, 29);
		panel.add(button);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NewType = null;
				String NewNum = null;
				NewType = TypeBox.getSelectedItem().toString();
				NewNum = NumField.getText();
				System.out.println(NewType);
				new Function(NewType,NewNum);
			}
		});
	}
}
