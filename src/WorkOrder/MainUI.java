package WorkOrder;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI {

	private JFrame frame;
	private JTextField TypeField;
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
		frame.setBounds(100, 100, 256, 167);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 244, 132);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Machine Type");
		lblNewLabel.setBounds(6, 22, 93, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Machine Num.");
		lblNewLabel_1.setBounds(6, 50, 93, 16);
		panel.add(lblNewLabel_1);
		
		TypeField = new JTextField();
		TypeField.setBounds(111, 17, 130, 26);
		panel.add(TypeField);
		TypeField.setColumns(10);
		
		NumField = new JTextField();
		NumField.setBounds(111, 45, 130, 26);
		panel.add(NumField);
		NumField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NewType = null;
				String NewNum = null;
				NewType = TypeField.getText();
				NewNum = NumField.getText();
				new Function(NewType,NewNum);
			}
		});
		btnNewButton.setBounds(63, 83, 117, 41);
		panel.add(btnNewButton);
	}
}
