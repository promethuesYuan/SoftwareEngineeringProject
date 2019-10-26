package DButils;
import java.sql.*;
import java.util.Date;

public class DBconnector {
    /**
    * @api 接口描述 Author PKY:
    * @api DBconnector
    * @api Description : 连接数据库的类 ，加载驱动，连接数据库，创建statment
    * @api Param :
    * @api return:
     **/

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java_db?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "123456";

    // 数据库连接实例 、 数据库游标

    public  Connection conn = null;
    public  Statement stmt = null;

    static{            // 注册 JDBC 驱动
        try {

            Class.forName(JDBC_DRIVER).newInstance();
            System.out.println(new Date() +" : load the mysql drive successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(new Date() +"方法1加载JDBC驱动失败（没有找到驱动程序）。。。");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println(new Date() +"方法1加载JDBC驱动失败（找不到路径）。。。");
            System.exit(2);
        } catch (InstantiationException e) {
            System.out.println(new Date() +"方法1加载JDBC驱动失败（不知道驱动类型）");
            System.exit(3);
        }

    }

    public  DBconnector(){
        getConnection();
        getStatement();
        System.out.println("successfully init DB!");
    }

    private  void getConnection(){
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(new Date()+" : ------succeed to connect to DB  ");
        } catch (SQLException e) {
            System.out.println(new Date() +" :------failed to connect to DB");
            e.printStackTrace();
        }
    }


    private  void getStatement(){
        try{
            stmt = conn.createStatement();
            System.out.println(new Date() +" :------establish statement successfully");
         } catch (SQLException e){
            System.out.println(new Date() +" :------establish statement unsuccessfully! ");
        }

    }


    public Statement return_statment(){
        return stmt;
    }
    public Connection return_conn(){
        return conn;
    }

}