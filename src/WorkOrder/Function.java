package WorkOrder;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

interface method {
	public static final int ADD_FUNCTION = 1;
	public static final int CHECK_FUNCTION = 2;
}

public class Function implements method{

	JFrame MessageBox;
	Database db = new Database();
	String NewType = null;
	String NewNum = null;
	public Function(MachineData data)
	{
		try{
			//JFrame MessageBox = null;
			//JOptionPane.showMessageDialog(MessageBox, "Please check the internet", "Error", JOptionPane.ERROR_MESSAGE);
			if( data.Method == ADD_FUNCTION )
			{
				db.connect();
				//System.out.print(db.getdata(NewTypeStr,NewNumStr));
				if(data.Num.isEmpty())
				{
					//MessageBox.
					JOptionPane.showMessageDialog(MessageBox, "Please input Machine Number", "Error", JOptionPane.ERROR_MESSAGE);
				}else
				{
					if(data.Num.length() < 4) {
						JOptionPane.showMessageDialog(MessageBox, "This Machine Number is too Short.", "Error", JOptionPane.ERROR_MESSAGE);
					}else
					{
						if(db.getdata(data)) {
							db.setdata(data);
							if(db.checkdata(data))
							{
								JOptionPane.showMessageDialog(MessageBox, "This Machine Number register complete.", "Complete!!", JOptionPane.INFORMATION_MESSAGE);
							}else
							{
								JOptionPane.showMessageDialog(MessageBox, "This Machine Number register Error.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							//MessageBox.
							JOptionPane.showMessageDialog(MessageBox, "This Machine Number is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}else if( data.Method == CHECK_FUNCTION )
			{
				db.connect();
				db.CheckLastData(data);
				System.out.println("LastType:"+data.LastType+"	LastNum:"+data.LastNum);
				JOptionPane.showMessageDialog(MessageBox, "The Last Type : "+data.LastType+"\nThe Last Num. : "+data.LastNum, "", JOptionPane.INFORMATION_MESSAGE);
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
