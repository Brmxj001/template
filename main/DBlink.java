
package main;



public class DBlink {
	/*try {
		Class.forName("com.mysql.jdbc.Driver");//加载驱动
		String url = "jdbc:mysql://127.0.0.1:3306/test1";//指定连接到哪个数据库，127.0.0.1是本机
		connection=DriverManager.getConnection(url, "root", "root");获取数据库连接
		statement = connection.createStatement();
		long result = statement.executeLargeUpdate(sql);
		statement.close();//如果上面代码出现异常，则该行代码及其下面代码无法执行，所以资源无法释放；比如sql语句语法错误，则statement和connection无法释放
    	connection.close();*/
	
	
	


	public boolean exist(String sql) {
		Connection connection =null;
		Statement statement =null;
		ResultSet resultSet =null;
		
		try {		
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet.next();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null){
				resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(resultSet,statement,connection);
		
		}
		return false;
	}
	
	
	
	Connection connection=null;
	Statement statement=null;
	public boolean update(String sql) {
		try {


			connection=getConnection();
			statement = connection.createStatement();
			long result = statement.executeLargeUpdate(sql);
			
			return result>0;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			close(statement,connection);
		}
		return false;
	}
	
	
	
	private static void close(ResultSet resultSet,Statement statement,Connection connection) {//重载
		try {
			if(resultSet!=null){
			resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close(statement,connection);
	
	}

	public static boolean upDate(String sql) {
		return false;
	}



	
	
}

