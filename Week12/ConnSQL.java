package Week12;

import java.sql.*;
import java.util.List;

public class ConnSQL {
     private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
     private static final String USER_NAME = "root";
     private static final String PASSWORD = "20172111678";
     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
     public Connection conn;
     public PreparedStatement prestmt;
     public ResultSet resultSet;
     public ConnSQL(){
          try {
               Class.forName(JDBC_DRIVER);
               this.conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
          } catch (ClassNotFoundException | SQLException e) {
               e.printStackTrace();
          }
     }
     public boolean insert(List<Student> students){
          try {
               prestmt = conn.prepareStatement("insert into stuclass values (?,?,?,?,?)");
               for (Student a: students) {
                    prestmt.setString(1,a.getXh());
                    prestmt.setString(2,a.getXm());
                    prestmt.setString(3,a.getXb());
                    prestmt.setString(4,a.getZym());
                    prestmt.setString(5,a.getYxm());
                    if(prestmt.execute()) return false;
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          if(conn != null){
               try {
                    conn.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
          if(prestmt != null){
               try {
                    prestmt.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
          return true;
     }
     public void select(){
          try {
               prestmt = conn.prepareStatement("select*from stuclass inner join score on stuclass.xh=score.xh");
               resultSet = prestmt.executeQuery();
               if(resultSet != null){
                    while(resultSet.next()){
                         System.out.println("姓名：" + resultSet.getString("xm") + " 学号：" + resultSet.getString("xh") + " 高数成绩：" +
                          resultSet.getInt("gs") + " 英语：" + resultSet.getInt("en") + " c语言：" + resultSet.getInt("c"));
                    }
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }

     }
}
