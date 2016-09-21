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
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName(name);// ��̬����mysql����
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
           // sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
           // int result = stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
            int result = 0;
            long l = System.currentTimeMillis();
            for (int i = 0; i < num; i++) {
            	sql = "insert into table_1(key_1,value_1) values('��" + i + "��','��" + i + "��������')";
                result = stmt.executeUpdate(sql);
			}
            System.out.println(System.currentTimeMillis() - l);
//                sql = "select * from student";
//                ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
//                System.out.println("ѧ��\t����");
//                while (rs.next()) {
//                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));// ��������ص���int���Ϳ�����getInt()
//                }
//            }
        } catch (SQLException e) {
            System.out.println("MySQL��������");
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
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName(name);// ��̬����mysql����
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
            int result = 0;
            long l = System.currentTimeMillis();
            sql = "select * from table_1";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
            
            while (rs.next()) {
//                System.out.println(rs.getString(1) + "\t" + rs.getString(2));// ��������ص���int���Ϳ�����getInt()
            }
            System.out.println(System.currentTimeMillis() - l);
        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
	}
}
