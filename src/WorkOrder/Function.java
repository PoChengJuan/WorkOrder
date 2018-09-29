package WorkOrder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Function {

	JFrame MessageBox;
	Database db = new Database();
	String NewType = null;
	String NewNum = null;
	public Function(String NewTypeStr, String NewNumStr)
	{
		try{
			db.connect();
			//System.out.print(db.getdata(NewTypeStr,NewNumStr));
			if(db.getdata(NewTypeStr, NewNumStr)) {
				db.setdata(NewTypeStr, NewNumStr);
			}else {
				//MessageBox.
				JOptionPane.showMessageDialog(MessageBox, "This Machine Number is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
