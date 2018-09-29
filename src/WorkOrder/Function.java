package WorkOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Function {

	public Function()
	{
		Connection conn = null;
		try{
			System.out.print("adfadfad");
			
			//連接MySQL
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("連接成功MySQLToJava");
			//建立讀取資料庫 (test 為資料庫名稱; user 為MySQL使用者名稱; passwrod 為MySQL使用者密碼)
			//String datasource = "jdbc:mysql://localhost/test?user=xxx&password=xxx";
			String DB_URL = "jdbc:mysql://114.35.249.80/WorkOrder";
			String USER = "pcj";
			String PASSWORD = "brrtpdhc76";
			//以下的資料庫操作請參考本blog中: "使用 Java 連結與存取 access 資料庫 (JDBC)"
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("連接成功MySQL");
			Statement st = conn.createStatement();
			//撈出剛剛新增的資料
			st.execute("SELECT * FROM member");
			ResultSet rs = st.getResultSet();
			while(rs.next())
			{
				System.out.println(rs.getString("account")+" "+rs.getString("pwd"));
			}
			
		}catch(Exception e){
			
		}
	}
}
