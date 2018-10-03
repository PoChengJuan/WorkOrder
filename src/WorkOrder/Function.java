package WorkOrder;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Function {

	JFrame MessageBox;
	Database db = new Database();
	String NewType = null;
	String NewNum = null;
	public Function(String CreatorStr, String NewTypeStr, String NewNumStr, JRadioButton DualButton)
	{
		try{
			//JFrame MessageBox = null;
			//JOptionPane.showMessageDialog(MessageBox, "Please check the internet", "Error", JOptionPane.ERROR_MESSAGE);

			db.connect();
			//System.out.print(db.getdata(NewTypeStr,NewNumStr));
			if(NewNumStr.isEmpty())
			{
				//MessageBox.
				JOptionPane.showMessageDialog(MessageBox, "Please input Machine Number", "Error", JOptionPane.ERROR_MESSAGE);
			}else
			{
				if(db.getdata(NewTypeStr, NewNumStr)) {
					db.setdata(NewTypeStr, NewNumStr,DualButton,getTime());
				}else {
					//MessageBox.
					JOptionPane.showMessageDialog(MessageBox, "This Machine Number is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getTime(){
		String out = "";
		SimpleDateFormat DateFormet = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		out = DateFormet.format(date);
		System.out.println(out);
		return out;
	}

}
