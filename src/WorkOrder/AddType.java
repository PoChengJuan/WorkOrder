package WorkOrder;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.sun.glass.events.WindowEvent;

import javax.swing.JScrollPane;

public class AddType {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddType window = new AddType();
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
	public AddType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 114, 180);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea TypeArea = new JTextArea();
		TypeArea.setBounds(6, 6, 102, 146);
		//TypeArea.scrollRectToVisible(null);
		frame.getContentPane().add(TypeArea);
		
		JScrollPane scrollPane = new JScrollPane(TypeArea);
		scrollPane.setBounds(6, 6, 102, 146);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowHandler(frame,TypeArea));
		
		
	}

}
class WindowHandler extends WindowAdapter {
	  JFrame f;
	  public WindowHandler(JFrame f, JTextArea typeArea) {
		  if(f.isVisible())
		  {
			  
		  }else {
			  String[] list = {};
			  list[0] = typeArea.toString();
			  System.out.println(list[0]);
		  }
		  
	  }
}
