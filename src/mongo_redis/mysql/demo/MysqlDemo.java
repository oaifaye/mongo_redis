package mongo_redis.mysql.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDemo {

	 public static final String url = "jdbc:mysql://10.0.100.250:3306/insert_test";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root@admin";  
    
    private static int num = 10000;
	
	public static void main(String[] args) throws SQLException {
//		insert1000();
		select10000();
	}
	
	public static void insert1000() throws SQLException{
		Connection conn = null;
        String sql;
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName(name);// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
           // sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
           // int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            int result = 0;
            long l = System.currentTimeMillis();
            for (int i = 0; i < num; i++) {
            	sql = "insert into table_1(key_1,value_1) values('第" + i + "条','第" + i + "条的内容')";
                result = stmt.executeUpdate(sql);
			}
            System.out.println(System.currentTimeMillis() - l);
//                sql = "select * from student";
//                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
//                System.out.println("学号\t姓名");
//                while (rs.next()) {
//                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
//                }
//            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
	}
	
	public static void select10000() throws SQLException{
		Connection conn = null;
        String sql;
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName(name);// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            int result = 0;
            long l = System.currentTimeMillis();
            sql = "select * from table_1";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            
            while (rs.next()) {
//                System.out.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
            }
            System.out.println(System.currentTimeMillis() - l);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
	}
}
