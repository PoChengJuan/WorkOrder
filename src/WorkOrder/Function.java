package WorkOrder;

import javax.swing.*;

public class Function {

	JFrame MessageBox;
	Database db = new Database();
	String NewType = null;
	String NewNum = null;
	public Function(String NewTypeStr, String NewNumStr)
	{
		try{
			JFrame MessageBox = null;
			JOptionPane.showMessageDialog(MessageBox, "Please check the internet", "Error", JOptionPane.ERROR_MESSAGE);

			//db.connect();
			//System.out.print(db.getdata(NewTypeStr,NewNumStr));
			if(NewNumStr.isEmpty())
			{
				//MessageBox.
				JOptionPane.showMessageDialog(MessageBox, "Please input Machine Number", "Error", JOptionPane.ERROR_MESSAGE);
			}else
			{
				if(db.getdata(NewTypeStr, NewNumStr)) {
					db.setdata(NewTypeStr, NewNumStr);
				}else {
					//MessageBox.
					JOptionPane.showMessageDialog(MessageBox, "This Machine Number is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
