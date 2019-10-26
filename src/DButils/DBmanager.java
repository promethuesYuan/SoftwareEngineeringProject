package DButils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class DBmanager {
    private  static  Statement stmt = null;   // statement管道
    private static Connection conn = null ;
    private static ResultSet resultSet = null;

    public  DBmanager(){
        DBconnector DB = new DBconnector();
        stmt =   DB.return_statment();
        conn =   DB.return_conn();
        creatTabels();
    }

    public boolean add_user(Map m1){
        /**
            * @api 成员函数描述  Author : PKY
            * @api add_user
            * @api Description: 向 user_info 表添加 user信息的函数
            * @api Param [m1]:  m1 { "name" : str
            *                        "password" : str }
            * @api returnvoid:
            **/
        String user_name = (String)m1.get("name");
        String password  = (String)m1.get("password");

        try {
            PreparedStatement ps1 = conn.prepareStatement("select * from user_info where name = ?");   // 查询数据库中是否已存在用户
            ps1.setString(1 , user_name);
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()) {
                System.out.println(String.format("%s 用户已经注册，请勿重复注册！" , user_name));
                ps1.close();
                return false;
            }

            PreparedStatement pstmt = conn.prepareStatement("insert into user_info value(?,?);");
            pstmt.setString(1,user_name);
            pstmt.setString(2,password);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            System.out.println(String.format("向　user_info 插入数据失败 ：(%s , %s)" , user_name,password));
        }

        return true ;

    }

    public void add_workout_item(Map m1){
        /**
            * @api 成员函数描述  Author : PKY
            * @api add_workout_item
            * @api Description:  向 workout_data 表中 添加健身数据
            * @api Param [m1]:  m1 { "user_name" : str ,
         *                           "item" : str  ,
         *                            "group_num" : int  ,
         *                            "times_num" : int,
         *                            "kilo" : float ,
         *                            "date" : str }
            * @api returnvoid:
            **/

        String date = (String)m1.get("date");
        String user_name =(String)m1.get("user_name");
        String item =(String)m1.get("item");
        int  group_num = (Integer)m1.get("group_num");
        int  times_num = (Integer)m1.get("times_num");
        float kilo = Float.parseFloat(m1.get("kilo").toString());

        try {
            PreparedStatement pstmt  = conn.prepareStatement("insert into workout_data(user_name, item,group_num,times_num,kilo, date) value(?,?,?,?,?,?);");
            pstmt.setString(1,user_name);
            pstmt.setString(2,item);
            pstmt.setInt(3,group_num);
            pstmt.setInt(4,times_num);
            pstmt.setFloat(5,kilo);
            pstmt.setString(6,date);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(String.format("向workout_data插入数据失败 ：(%s , %s , %f) : " , user_name,item,kilo)+e.getMessage());
        }

    }

    public  ResultSet query(String[] s ){
        /**
            * @api 成员函数描述  Author : PKY
            * @api query
            * @api Description:
            * @api Param [s]:  字符串数组 { " table_name" , "colomn" , "value" }
            * @api returnjava.sql.ResultSet:   返回查找到的行
            **/

        try{
            PreparedStatement pstmt = conn.prepareStatement(String.format("SELECT * from %s WHERE %s = ?",s[0],s[1]));
            pstmt.setString(1 , s[2]);
            ResultSet rs = pstmt.executeQuery();
            return rs ;
        }catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return  null;
    }

    public List <String> fuzzy_query_item(Map m1) {
        /**
            * @api 成员函数描述  Author : PKY
            * @api fuzzy_query_item
            * @api Description:  在 workout_item 中模糊查询用户输入的 item  ， 若存在一样的item 返回null , 返不一致回一个模糊查询得到的列表
            * @api Param [m1]:
            * @api returnjava.util.List:  返回List<string> or null ;
            **/
        String item = (String) m1.get("item");

        List <String >fuzzy_result =  new ArrayList<>() ;
        String[] s1 = {"workout_item" , "item" , item};
        ResultSet check_item_exit = query( s1 );
        try{
            if (check_item_exit.next()){                     // 检查是否item存在 ， 若存在直接返回null ； 不存在 执行模糊查询
                return null;
            }

        }catch (SQLException e){
                System.out.println("Error : fuzzy_query_item : " + e.getMessage());
        }

        try {                                                              // 模糊查询输入的 item
            for (int i=0 ; i <item.length (); i++) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT item from workout_item WHERE item LIKE ? ");
                pstmt.setString(1 , String.format("%%%s%%",item.charAt(i)));    // %item%
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    fuzzy_result.add(rs.getString(1));          // 将查询结果加入LIST
                }
            }

        }catch (SQLException e){
            System.out.println("Error : fuzzy_query_item : " + e.getMessage());
        }
        return fuzzy_result ;

    }

    public String [][] get_all_records(){
        /**
            * @api 成员函数描述  Author : PKY
            * @api get_all_records
            * @api Description:    获取所有 workout item  , 并用  String [][]返回
            * @api Param []:
            * @api returnjava.lang.String[][]:
            **/

        int i = 0 ;
        String [][] s =  null ;
        try {
            PreparedStatement pstmt  = conn.prepareStatement("select * from workout_data;",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.last();
            int row = resultSet.getRow();
            resultSet.beforeFirst();
            String [][] result = new String [row][4] ;
            while (i<row){
                resultSet.next();
                String username = resultSet.getString(1);
                String item  = resultSet.getString(2);
                Integer num = resultSet.getInt(3);
                Float kilo = resultSet.getFloat(4);
                String[] strs = { username, item, num.toString(), kilo.toString() } ;
                result[i++] = strs;
            }

            s = new String[row][4];    // 赋值
            s = result;
        }catch (SQLException e){
            System.out.println("获取所有items 失败:"+e.getMessage());
        }
        return s;
    }

    public Map return_Pie_data(String username) throws Exception{
        /**
            * @api 成员函数描述  Author : PKY
            * @api return_Pie_data
            * @api Description:  从数据库中 workout_data 中获得饼图所需的肌肉群数据字典   , 调用时处理异常
            * @api Param []:
            * @api returnjava.util.Map:   { "肌肉部位" : 锻炼次数 }

            **/
        Map item_times = new HashMap();
        PreparedStatement pstmt = conn.prepareStatement("SELECT item , group_num , times_num from workout_data where user_name = ?");     // 查询当前用户所有健身记录，计算出对应健身项目的锻炼次数，存为字典
        pstmt.setString(1 ,username);
        ResultSet item_result = pstmt.executeQuery();
        while (item_result.next()){
            String item_name = item_result.getString(1);
            int group_num = item_result.getInt(2);
            int times_num = item_result.getInt(3);
            if (item_times.containsKey(item_name)){
                int exercise_num = (Integer)item_times.get(item_name);
                exercise_num = exercise_num + group_num*times_num ;          // 计算进行某种健身项目的总次数
                item_times.put(item_name,exercise_num);                      // 更新键值对
            }
            else {
                item_times.put(item_name, group_num*times_num);
            }
        }
        Map muscle_times = new HashMap();
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT item , muscle from workout_item ");  // 查询数据库中存放的 项目-锻炼肌群数据，计算出每个肌肉部位锻炼的次数
        ResultSet item_muscle = pstmt2.executeQuery();
        while (item_muscle.next()){

            String item_name = item_muscle.getString(1);
            if (!item_times.containsKey(item_name))                             // 如果遍历到的item 不在锻炼过的item中，跳过。
                continue;
            String muscles   = item_muscle.getString(2);
            String [] muscles_arrays =muscles.split(" ");
            for (String x : muscles_arrays){                                 // 遍历 单个项目 对应锻炼的肌肉部分
                if (muscle_times.containsKey(x)){                            //当前肌肉部位已加入列表
                    int m_time = (Integer)muscle_times.get(x);
                    m_time = m_time + (Integer)item_times.get(item_name);
                    muscle_times.put(x,m_time);
                }
                else {
                    int m_time = (Integer)item_times.get(item_name);
                    muscle_times.put(x,m_time);
                }
            }
        }
        return muscle_times;
    }

    public List<Integer> return_Line_data(String username) throws Exception{
        /**
            * @api 成员函数描述  Author : PKY
            * @api return_Line_data
            * @api Description: 返回折线图所需要的数据 , 调用时处理异常
            * @api Param []:
            * @api returnjava.util.List: e.g :  [2,4,6,7...] 1月到当月的锻炼次数
            **/
        List<Integer> result = new ArrayList<Integer>();
        int[] store = new int[13];
        PreparedStatement pstmt = conn.prepareStatement("SELECT date from workout_data where user_name = ?");     // 查询当前用户所有健身记录，计算出对应健身项目的锻炼次数，放入列表
        pstmt.setString(1,username);
        ResultSet date_result = pstmt.executeQuery();
        int[][] month_days = new int[13][32];
        while (date_result.next()){
            String []date = date_result.getString(1).split("-");
            Integer year = Integer.valueOf(date [0]);
            Integer month = Integer.valueOf(date[1]);
            Integer day = Integer.valueOf(date[2].split(" ")[0]);;
            if (month_days[month][day]==(0)){
                month_days[month][day] = 1  ;
                store[month]++;
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        int now_month = Integer.valueOf(date.split("-")[1]);
        for (int i =1 ; i <=now_month ;i++)
            result.add(store[i]);
        return result;
    }

    public List<Float> return_bar_data(String username) throws  Exception{
        /**
            * @api 成员函数描述  Author : PKY
            * @api return_bar_data
            * @api Description:  返回条形图数据  List   其中有多少个元素就代表有记录截止到几月
            * @api Param []:  null
            * @api returnjava.util.List<java.lang.Float>:
            **/

        List<Float> result = new ArrayList<Float>();
        float[] store = new float[13];
        //String sql = String.format("select * from workout_data where user_name = %s",username) ;
        PreparedStatement pstmt = conn.prepareStatement("select * from workout_data where user_name = ?");     // 查询当前用户所有健身记录，计算出对应健身项目的锻炼次数，放入列表
        pstmt.setString(1 , username);
        ResultSet date_result = pstmt.executeQuery();

        while (date_result.next()){
            String []date = date_result.getString(6).split("-");
            Integer month = Integer.valueOf(date[1]);
            int group_num = date_result.getInt(3);
            int times_num = date_result.getInt(4);
            int strenth = group_num*times_num;
            store[month] += strenth;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        int now_month = Integer.valueOf(date.split("-")[1]);
        for (int i =1 ; i <=now_month ;i++)
            result.add(store[i]);
        return result;

    }

    private boolean is_table_exist(String table_name){
        String sql = null;
        sql = String.format("select * from %s", table_name);
        try {
            stmt.executeQuery(sql);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    private void creatTabels(){
        /**
         * @api 成员函数描述  Author : PKY
         * @api creatTabels
         * @api Description:   创建数据表    user_info -- 存储用户信息   workout_data -- 存储健身数据
         * @api Param []:
         * @api returnvoid:
         **/

        if (! is_table_exist("user_info")){                       // usr_info 表结构
            String sql = "create table user_info(" +
                         "name varchar(20) not null primary key," +
                         "password varchar(30)not null )charset=utf8;";

            try {
                stmt.execute(sql);
                System.out.println("Successfully created table : user_info");
            }catch (SQLException e){
                System.out.println("Failed to created table : user_info");
            }

        } else System.out.println("table : user_info has existed !");

        if (! is_table_exist("workout_data")){

            String sql = "create table workout_data(" +                  //wrokout_data 表结构
                    "user_name varchar(20) not null," +
                    "item varchar(30) not null ," +
                    "group_num int(4)," +
                    "times_num int(4)," +
                    "kilo float (4)," +
                    "date varchar(30) )charset=utf8;";
            try {
                stmt.execute(sql);
                System.out.println("Successfully created table : workout_data");
            }catch (SQLException e){
                System.out.println("Failed to created table : workout_data");
            }

            sql="alter table workout_data add constraint FK_ID foreign key (user_name) references user_info(name);" ;
            try {
                stmt.execute(sql);
            }catch (SQLException e) {
                System.out.println("fail to set a foreign key");
            }
        } else System.out.println("table : workout_data has existed !");

        String sql="alter table workout_data add constraint FK_ID foreign key (user_name) references user_info(name);";
        try {
            stmt.execute(sql);
        }catch (SQLException e) {
            System.out.println("fail to set a foreign key");
        }

        if (! is_table_exist("workout_item")){

            String sql2 = "create table workout_item(" +
                          "item varchar(20) not null," +
                          "muscle varchar(100) not null)charset=utf8;" ;

            try {
                stmt.execute(sql2);
                System.out.println("Successfully created table : workout_item");
            }catch (SQLException e){
                System.out.println("Failed to created table : workout_item");
            }

        }
        else System.out.println("table : workout_item has existed !");
    }

    public static void closeAll(){
        /**
         * @api 成员函数描述  Author : PKY
         * @api closeAll
         * @api Description:  关闭数据库连接， 释放资源
         * @api Param []:
         * @api returnvoid:
         **/
        if (resultSet != null){
            try {
                System.out.println(new Date()+"关闭resultSet。。。");
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(new Date()+"关闭resultSet异常。。。");
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                System.out.println(new Date() + "关闭statement。。。");
                stmt.close();
            } catch (SQLException e) {
                System.out.println(new Date() + "关闭statement异常。。。");
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                System.out.println(new Date()+"关闭connection。。。");
                conn.close();
            } catch (SQLException e) {
                System.out.println(new Date()+"关闭connection异常。。。");
                e.printStackTrace();
            }
        }


    }


    private static PreparedStatement createPsStatement( Connection conn , String sql) {

        try {
            System.out.println(new Date() + "创建PrepareStatement通道对象。。。");
            PreparedStatement pstm = conn.prepareStatement(sql);
            return pstm;
        } catch (SQLException e) {
            System.out.println(new Date() + "创建PrepareStatement通道对象失败。。。");
            e.printStackTrace();
        }
        return null;
    }

    private static void bundle(PreparedStatement pstm , String[] parm){
        //判断数组Parm是否为空
        if (parm != null){
            //通过for循环将参数绑定起来
            for (int i = 0; i < parm.length; i++) {
                try {
                    pstm.setString(i+1,parm[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){

        DBmanager dbmanager = new DBmanager();
        Map map1 = new HashMap();
        map1.put("name" , "pky");
        map1.put("password" , "123456");
        Map map2 = new HashMap();
        map2.put("user_name" , "pky");
        map2.put("item" , "杠铃");
        map2.put("kilo" , 12.5);
        map2.put("group_num" , 10);
        map2.put("times_num",2);
        dbmanager.add_user(map1);
        dbmanager.add_workout_item(map2);
        System.out.println("测试编码会不会乱码");
        closeAll();
    }

}
