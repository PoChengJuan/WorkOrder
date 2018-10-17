package WorkOrder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

interface method {
	public static final int ADD_FUNCTION = 1;
	public static final int CHECK_FUNCTION = 2;
	public static final int TypePath = 1;
	public static final int LogoPath = 2;
	public default String getFilePath(int path_select) throws IOException{
		StringBuffer path = new StringBuffer();
		String out = null;
		File f = new File(".");
		//System.out.println(f.getCanonicalPath());
		
		if(path_select == TypePath) {
			path.append(f.getCanonicalPath());
			path.append("/ref/Type.txt");
			out = path.toString();
		}else if(path_select == LogoPath) {
			path.append(f.getCanonicalPath());
			path.append("/ref/logo.png");
			out = path.toString();
		}
		
		return out;
	}
}

public class Function implements method{

	JFrame MessageBox;
	Database db = new Database();
	String NewType = null;
	String NewNum = null;
	public Function(MachineData data)
	{
		try{
			if( data.Method == ADD_FUNCTION )
			{
				db.connect();
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
