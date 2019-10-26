package DButils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class DBmanager {
    private  static  Statement stmt = null;   // statement�ܵ�
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
            * @api ��Ա��������  Author : PKY
            * @api add_user
            * @api Description: �� user_info ����� user��Ϣ�ĺ���
            * @api Param [m1]:  m1 { "name" : str
            *                        "password" : str }
            * @api returnvoid:
            **/
        String user_name = (String)m1.get("name");
        String password  = (String)m1.get("password");

        try {
            PreparedStatement ps1 = conn.prepareStatement("select * from user_info where name = ?");   // ��ѯ���ݿ����Ƿ��Ѵ����û�
            ps1.setString(1 , user_name);
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()) {
                System.out.println(String.format("%s �û��Ѿ�ע�ᣬ�����ظ�ע�ᣡ" , user_name));
                ps1.close();
                return false;
            }

            PreparedStatement pstmt = conn.prepareStatement("insert into user_info value(?,?);");
            pstmt.setString(1,user_name);
            pstmt.setString(2,password);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            System.out.println(String.format("��user_info ��������ʧ�� ��(%s , %s)" , user_name,password));
        }

        return true ;

    }

    public void add_workout_item(Map m1){
        /**
            * @api ��Ա��������  Author : PKY
            * @api add_workout_item
            * @api Description:  �� workout_data ���� ��ӽ�������
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
            System.out.println(String.format("��workout_data��������ʧ�� ��(%s , %s , %f) : " , user_name,item,kilo)+e.getMessage());
        }

    }

    public  ResultSet query(String[] s ){
        /**
            * @api ��Ա��������  Author : PKY
            * @api query
            * @api Description:
            * @api Param [s]:  �ַ������� { " table_name" , "colomn" , "value" }
            * @api returnjava.sql.ResultSet:   ���ز��ҵ�����
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
            * @api ��Ա��������  Author : PKY
            * @api fuzzy_query_item
            * @api Description:  �� workout_item ��ģ����ѯ�û������ item  �� ������һ����item ����null , ����һ�»�һ��ģ����ѯ�õ����б�
            * @api Param [m1]:
            * @api returnjava.util.List:  ����List<string> or null ;
            **/
        String item = (String) m1.get("item");

        List <String >fuzzy_result =  new ArrayList<>() ;
        String[] s1 = {"workout_item" , "item" , item};
        ResultSet check_item_exit = query( s1 );
        try{
            if (check_item_exit.next()){                     // ����Ƿ�item���� �� ������ֱ�ӷ���null �� ������ ִ��ģ����ѯ
                return null;
            }

        }catch (SQLException e){
                System.out.println("Error : fuzzy_query_item : " + e.getMessage());
        }

        try {                                                              // ģ����ѯ����� item
            for (int i=0 ; i <item.length (); i++) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT item from workout_item WHERE item LIKE ? ");
                pstmt.setString(1 , String.format("%%%s%%",item.charAt(i)));    // %item%
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    fuzzy_result.add(rs.getString(1));          // ����ѯ�������LIST
                }
            }

        }catch (SQLException e){
            System.out.println("Error : fuzzy_query_item : " + e.getMessage());
        }
        return fuzzy_result ;

    }

    public String [][] get_all_records(){
        /**
            * @api ��Ա��������  Author : PKY
            * @api get_all_records
            * @api Description:    ��ȡ���� workout item  , ����  String [][]����
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

            s = new String[row][4];    // ��ֵ
            s = result;
        }catch (SQLException e){
            System.out.println("��ȡ����items ʧ��:"+e.getMessage());
        }
        return s;
    }

    public Map return_Pie_data(String username) throws Exception{
        /**
            * @api ��Ա��������  Author : PKY
            * @api return_Pie_data
            * @api Description:  �����ݿ��� workout_data �л�ñ�ͼ����ļ���Ⱥ�����ֵ�   , ����ʱ�����쳣
            * @api Param []:
            * @api returnjava.util.Map:   { "���ⲿλ" : �������� }

            **/
        Map item_times = new HashMap();
        PreparedStatement pstmt = conn.prepareStatement("SELECT item , group_num , times_num from workout_data where user_name = ?");     // ��ѯ��ǰ�û����н����¼���������Ӧ������Ŀ�Ķ�����������Ϊ�ֵ�
        pstmt.setString(1 ,username);
        ResultSet item_result = pstmt.executeQuery();
        while (item_result.next()){
            String item_name = item_result.getString(1);
            int group_num = item_result.getInt(2);
            int times_num = item_result.getInt(3);
            if (item_times.containsKey(item_name)){
                int exercise_num = (Integer)item_times.get(item_name);
                exercise_num = exercise_num + group_num*times_num ;          // �������ĳ�ֽ�����Ŀ���ܴ���
                item_times.put(item_name,exercise_num);                      // ���¼�ֵ��
            }
            else {
                item_times.put(item_name, group_num*times_num);
            }
        }
        Map muscle_times = new HashMap();
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT item , muscle from workout_item ");  // ��ѯ���ݿ��д�ŵ� ��Ŀ-������Ⱥ���ݣ������ÿ�����ⲿλ�����Ĵ���
        ResultSet item_muscle = pstmt2.executeQuery();
        while (item_muscle.next()){

            String item_name = item_muscle.getString(1);
            if (!item_times.containsKey(item_name))                             // �����������item ���ڶ�������item�У�������
                continue;
            String muscles   = item_muscle.getString(2);
            String [] muscles_arrays =muscles.split(" ");
            for (String x : muscles_arrays){                                 // ���� ������Ŀ ��Ӧ�����ļ��ⲿ��
                if (muscle_times.containsKey(x)){                            //��ǰ���ⲿλ�Ѽ����б�
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
            * @api ��Ա��������  Author : PKY
            * @api return_Line_data
            * @api Description: ��������ͼ����Ҫ������ , ����ʱ�����쳣
            * @api Param []:
            * @api returnjava.util.List: e.g :  [2,4,6,7...] 1�µ����µĶ�������
            **/
        List<Integer> result = new ArrayList<Integer>();
        int[] store = new int[13];
        PreparedStatement pstmt = conn.prepareStatement("SELECT date from workout_data where user_name = ?");     // ��ѯ��ǰ�û����н����¼���������Ӧ������Ŀ�Ķ��������������б�
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
        int now_month = Integer.valueOf(date.split("-")[1]);
        for (int i =1 ; i <=now_month ;i++)
            result.add(store[i]);
        return result;
    }

    public List<Float> return_bar_data(String username) throws  Exception{
        /**
            * @api ��Ա��������  Author : PKY
            * @api return_bar_data
            * @api Description:  ��������ͼ����  List   �����ж��ٸ�Ԫ�ؾʹ����м�¼��ֹ������
            * @api Param []:  null
            * @api returnjava.util.List<java.lang.Float>:
            **/

        List<Float> result = new ArrayList<Float>();
        float[] store = new float[13];
        //String sql = String.format("select * from workout_data where user_name = %s",username) ;
        PreparedStatement pstmt = conn.prepareStatement("select * from workout_data where user_name = ?");     // ��ѯ��ǰ�û����н����¼���������Ӧ������Ŀ�Ķ��������������б�
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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
         * @api ��Ա��������  Author : PKY
         * @api creatTabels
         * @api Description:   �������ݱ�    user_info -- �洢�û���Ϣ   workout_data -- �洢��������
         * @api Param []:
         * @api returnvoid:
         **/

        if (! is_table_exist("user_info")){                       // usr_info ��ṹ
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

            String sql = "create table workout_data(" +                  //wrokout_data ��ṹ
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
         * @api ��Ա��������  Author : PKY
         * @api closeAll
         * @api Description:  �ر����ݿ����ӣ� �ͷ���Դ
         * @api Param []:
         * @api returnvoid:
         **/
        if (resultSet != null){
            try {
                System.out.println(new Date()+"�ر�resultSet������");
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(new Date()+"�ر�resultSet�쳣������");
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                System.out.println(new Date() + "�ر�statement������");
                stmt.close();
            } catch (SQLException e) {
                System.out.println(new Date() + "�ر�statement�쳣������");
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                System.out.println(new Date()+"�ر�connection������");
                conn.close();
            } catch (SQLException e) {
                System.out.println(new Date()+"�ر�connection�쳣������");
                e.printStackTrace();
            }
        }


    }


    private static PreparedStatement createPsStatement( Connection conn , String sql) {

        try {
            System.out.println(new Date() + "����PrepareStatementͨ�����󡣡���");
            PreparedStatement pstm = conn.prepareStatement(sql);
            return pstm;
        } catch (SQLException e) {
            System.out.println(new Date() + "����PrepareStatementͨ������ʧ�ܡ�����");
            e.printStackTrace();
        }
        return null;
    }

    private static void bundle(PreparedStatement pstm , String[] parm){
        //�ж�����Parm�Ƿ�Ϊ��
        if (parm != null){
            //ͨ��forѭ��������������
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
        map2.put("item" , "����");
        map2.put("kilo" , 12.5);
        map2.put("group_num" , 10);
        map2.put("times_num",2);
        dbmanager.add_user(map1);
        dbmanager.add_workout_item(map2);
        System.out.println("���Ա���᲻������");
        closeAll();
    }

}
