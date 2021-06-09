import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Master {

	public static void main(String[] args) {
		System.out.println("*********************************");
		System.out.println("*\t\t\t\t*");
		System.out.println("*\t欢迎使用学生信息管理系统\t*");
		System.out.println("*\t\t\t\t*");
		System.out.println("*********************************");
		System.out.println("输入sql语句进行查询");//name		
		Scanner scanner = new Scanner(System.in);
		String sql = scanner.next();	
		System.out.println(sql);
		ResultSet res = select(sql);
		System.out.println(res);

		scanner.close();
	}





	private static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/timetable";
			return DriverManager.getConnection(url, "root","admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet select(String sql) {//接口无法创建对象，所以rowMapper参数一定指向IRowMapper接口实现类对象，rowMapper上转型对象
		//此处的rowMapper就是一个形参，到时候接口实现类里的实参再传给它
		Connection connection =null;
		Statement statement =null;
		ResultSet resultSet =null;
		
		try {
			
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet;	
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (Exception e) {
				
			}
			
		}
		return null;
	}
}
