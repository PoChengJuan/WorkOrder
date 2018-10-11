package WorkOrder;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JTextArea;


import javax.swing.JScrollPane;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddType extends MainUI{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddType window = new AddType("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param str1 
	 */
	public AddType(String str1) {
		initialize(str1);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param str1 
	 */
	private void initialize(String str1) {
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 114, 180);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea TypeArea = new JTextArea();
		TypeArea.setBounds(6, 6, 102, 146);
		TypeArea.setText(str1);
		//TypeArea.scrollRectToVisible(null);
		frame.getContentPane().add(TypeArea);
		
		JScrollPane scrollPane = new JScrollPane(TypeArea);
		scrollPane.setBounds(6, 6, 102, 146);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
		
		//frame.addWindowListener(new WindowHandler(frame,TypeArea));
		
		//frame.addWindowListener(new WindowHandler());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("1111");
				try {
					WritetoDoc(TypeArea.getText());
					MainUI.UpdateTypeBox(TypeArea.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public String WritetoDoc(String str) throws IOException {
		FileReader fr = new FileReader(getFilePath());
		
		BufferedReader br = new BufferedReader(fr);
		String data = "";
		String newData = "";
		while((data = br.readLine())!=null) {
			if(data.equals("[Type]")) {
				newData = newData + data.replace("[Type]", "[Type]\n"+str) + "\n";
				break;
			}else {
				newData = newData + data + "\n";
			}
			
		}
		FileWriter fw = new FileWriter(getFilePath());
		fw.write(newData);
		fr.close();
		br.close();
		fw.close();
		return str;
	}
}
/*
class WindowHandler extends WindowAdapter {
	  JFrame f;
	  public WindowHandler(JFrame frame, JTextArea typeArea) {
		  if(f.isVisible())
		  {
			  
		  }else {
			  String[] list = {};
			  list[0] = typeArea.toString();
			  System.out.println(list[0]);
		  }
		  
	  }
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("1212121");
	}
}*/
