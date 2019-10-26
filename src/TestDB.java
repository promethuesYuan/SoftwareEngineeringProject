import DButils.DBmanager;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestDB {

    public static void main(String[] args) throws Exception {

        //数据库连接 及数据表初始化
        DBmanager dbmanager = new DBmanager();

        /*
        //测试数据库  user 插入
        Map map1 = new HashMap();
        map1.put("name" , "pky");
        map1.put("password" , "123456");
        dbmanager.add_user(map1);

        // 测试数据库  workout_data 插入
        Map map2 = new HashMap();
        map2.put("user_name" , "pky");
        map2.put("item" , "杠铃");
        map2.put("kilo" , 12.5);
        dbmanager.add_workout_item(map2);

        // 测试数据库   get_all_records
        String[][] s = dbmanager.get_all_records() ;

        Map map2 = new HashMap();
        map2.put("item" , "跑步");

        List<String> L = dbmanager.fuzzy_query_item(map2);

        if ( L==null) System.out.println("返回了一个null型");
        else if (L.isEmpty()) System.out.println("返回了一个空列表");
        else System.out.println(L.toString());
        */

        // 测试 数据库 得到 折线图数据
//        try {
//            List <Integer> result = dbmanager.return_Line_data();
//            for (Integer data : result){
//                System.out.println(data);
//            }
//        }catch (SQLException e){
//            System.out.println("erro ");
//        }

//        // 测试数据库 得到 饼图数据
//
//        Map map = dbmanager.return_Pie_data();
//        for(Object key : map.keySet()){
//            String Key = key.toString();
//            int value = Integer.parseInt(map.get(Key).toString());
//            System.out.println(key+":"+value);
//        }

//        // 测试数据库  得到 条形图数据
//        String username = "pky";
//        List <Float>result = dbmanager.return_bar_data(username);
//        for (Float strenth : result){
//
//            System.out.println(strenth);
//        }

        // 测试 比较图
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间

        //user 插入
        String [] users = {"pky","yzb","tjl"};
        String [] passwords = {"123456" ,"abcd" ,"efgds"};

        for (int i =0 ; i<users.length ;i++){
            Map map1 = new HashMap();
            map1.put("name" , users[i]);
            map1.put("password" , passwords[i]);
            dbmanager.add_user(map1);
        }


        String [] items = {"举重" ,"杠铃" , "俯卧撑" , "引体向上" , "卧推"};
        Integer [] times = {1,3,2,4,3,2,5,3} ;
        Integer [] group = {10,12,4,6,4,1,4,6,7,3,12,22};

        for (int i =0 ; i < 100 ;i++){
            Map map2 = new HashMap();
            map2.put("user_name" , users[i%3]);
            map2.put("item" , items[i%5]);
            map2.put("group_num", group[i%12]);
            map2.put("times_num",times[i%8]);
            map2.put("kilo" , 12.5);
            map2.put("date",date);
            dbmanager.add_workout_item(map2);
        }
        // 测试数据库  workout_data 插入









        // 测试数据库关闭
        dbmanager.closeAll();
    }


    // 测试数据库   get_all_records

    //测试数据库 模糊查询   fuzzy_query_item







}
