package WorkOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class Database {

    private Connection conn;

	public void connect() throws Exception{
		
		if(conn != null) return;

        try {
			//連接MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("連接成功MySQLToJava");
        } catch (ClassNotFoundException e) {
            throw new Exception("No database");
        }
      //建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
		String DB_URL = "jdbc:mysql://114.35.249.80/WorkOrder?autoReconnect=true&useSSL=false";
		String USER = "pcj";
		String PASSWORD = "brrtpdhc76";
		System.out.println("連接成功MySQL");
        conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);  

		
	}
	public boolean getdata(MachineData data) throws SQLException {
		DecimalFormat decimalformat = new DecimalFormat("#############.#############");
		try {
			Statement st = conn.createStatement();
			//撈出資料
			st.execute("SELECT * FROM `WorkOrder` WHERE 1");
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
			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				if(rs.getString("M_Number").equals(data.Num)) {
					System.out.println("OK");
					return true;
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

}
