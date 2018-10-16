package WorkOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Database {

    private Connection conn;
    JFrame MessageBox;
	public void connect(){
		
		if(conn != null) return;

        
			//連接MySQL
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println("連接成功MySQLToJava");
        
      //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
		String DB_URL = "jdbc:mysql://114.35.249.80/WorkOrder?autoReconnect=true&useSSL=false";
		String USER = "pcj";
		String PASSWORD = "brrtpdhc76";
		System.out.println("連接成功MySQL");
        try {
			conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(MessageBox, "Please check the internet", "Error", JOptionPane.ERROR_MESSAGE);
		}  		
	}
	public boolean getdata(MachineData data) throws SQLException {
		DecimalFormat decimalformat = new DecimalFormat("#############.#############");
		try {
			Statement st = conn.createStatement();
			//撈出資料
			//st.execute("SELECT * FROM `WorkOrder` WHERE 1");
			st.execute("SELECT * FROM `WorkOrder` WHERE `Type` LIKE '"+data.Type+"'");
			ResultSet rs = st.getResultSet();
			System.out.println(data.Num);
			while(rs.next())
			{
				double Num = rs.getDouble("Machine_SN");
				if(decimalformat.format(Num).equals(data.Num))
				{
					//System.out.println("same num");
					System.out.println(rs.getString("Machine_SN"));
					return false;
				}else
				{
					//	System.out.println("new num");
				}

			}
			return true;
		}catch(SQLException se) {
			System.out.print("No Data");
			se.printStackTrace();
			return false;
		}
		//return outString;
	}
	
	public void setdata(MachineData data) throws SQLException {
		Statement st = conn.createStatement();
		//System.out.println(dualButton.isSelected());
		//撈出資料
		String out1 = "INSERT INTO `WorkOrder` (`AUTO_INCREMENT`, `Type`, `WorkOrder_SN`, `Machine_SN`) VALUES (NULL, '"+data.Type+"', '"+data.Type+data.Num+"', '"+data.Num+"')";
		String out2 = "INSERT INTO `Machine_Type_Number` (`ID`, `M_Type`, `M_Number`, `Location`, `Ship_Date`, `Customer_SN`, `MFG_Start_Date`, `Dual_AMS_PS`, `NumberCreator`) "
												+ "VALUES (NULL, '"+data.Type+"', '"+data.Num+"', NULL, NULL, NULL, '"+data.MFG_Start_Date+"', '"+data.Dual_AMU_PS+"', '"+data.Creator+"')";
		st.execute(out1);
		st.execute(out2);
	}
	public boolean checkdata(MachineData data) {
		// TODO Auto-generated method stub
		try {
			Statement st = conn.createStatement();
			//撈出資料
			st.execute("SELECT * FROM `Machine_Type_Number` ORDER BY `Machine_Type_Number`.`ID` DESC");
			//st.execute("SELECT * FROM `Machine_Type_Number` WHERE `M_Number` LIKE '"+data.Num+"' ORDER BY `Machine_Type_Number`.`ID` DESC");
			//st.execute("SELECT * FROM `Machine_Type_Number` WHERE `M_Type` LIKE '"+data.Type+"' ORDER BY `ID` DESC");

			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				if(rs.getString("M_Number").equals(data.Num)) {
					if(rs.getString("M_Type").equals(data.Type)) {
						System.out.println("OK");
						return true;
					}
				}else
				{
					System.out.println("NG");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void CheckLastData(MachineData data) throws SQLException {
		// TODO Auto-generated method stub
		Statement st = conn.createStatement();
		//st.execute("SELECT * FROM `Machine_Type_Number` ORDER BY `Machine_Type_Number`.`ID` DESC");
		st.execute("SELECT * FROM `Machine_Type_Number` WHERE `M_Type` LIKE '"+data.Type+"' ORDER BY `Machine_Type_Number`.`ID` DESC");
		ResultSet rs = st.getResultSet();
		rs.next();
		data.LastType = rs.getString("M_Type").toString();
		data.LastNum = rs.getString("M_Number").toString();
	}

}
