package WorkOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public boolean getdata(String newTypeStr, String newNumStr) throws SQLException {
		try {
			Statement st = conn.createStatement();
			//撈出資料
			st.execute("SELECT * FROM `WorkOrderDB` WHERE `Type` LIKE 'iPalur'");
			ResultSet rs = st.getResultSet();
			
			while(rs.next())
			{
				if(rs.getString("Num").equals(newNumStr))
				{
					//System.out.println("same num");
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
	
	public void setdata(String newTypeStr, String newNumStr) throws SQLException {
		Statement st = conn.createStatement();
		//撈出資料
		String out = "INSERT INTO `WorkOrderDB` (`AUTO_INCREMENT`, `Type`, `Num`, `Note`) VALUES (NULL, '"+newTypeStr+"', '"+newNumStr+"', '')";
		st.execute(out);
		//ResultSet rs = st.getResultSet();
	}
}
