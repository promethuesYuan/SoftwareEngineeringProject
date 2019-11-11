import DButils.DBmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDB {

    public static void main(String[] args) throws Exception {

        //数据库连接 及数据表初始化
        DBmanager dbmanager = new DBmanager();

        //测试数据库  user 插入
        Map map1 = new HashMap();
        map1.put("name", "pky");
        map1.put("password", "123456");
        dbmanager.add_user(map1);  // 调用


        // 测试数据库  workout_data 插入
        Map map2 = new HashMap();
        map2.put("user_name", "pky");
        map2.put("item", "杠铃");
        map2.put("kilo", 12.5);
        dbmanager.add_workout_item(map2);  // 调用


        // 测试数据库   get_all_records
        String[][] s = dbmanager.get_all_records();   // 调用


        //测试数据库 模糊查询   fuzzy_query_item ：  在用户插入 健身数据之前调用 ， 检查数据库中是否有相应 item
        Map map3 = new HashMap();
        map3.put("item", "跑步");

        List<String> L = dbmanager.fuzzy_query_item(map3);  // 调用

        if (L == null) System.out.println("返回了一个null型");        // 表示 item 中有跑步 ，后续可以插入
        else if (L.isEmpty())
            System.out.println("返回了一个空列表"); // 表示 item中无跑步， 且模糊查询无推荐输入项目  ， 此时应该提示用户自定义 [“锻炼部位” ， “部位1” ， “部位2”]
        else
            System.out.println(L.toString());                      // 返回列表非空，且不为 null  ， 表示item中无跑步，但有模糊查询得到的推荐输入， 应提示推荐项目



        // 测试数据库 插入 item (用户自定义功能)
        String[] items_musle = {"下蹲", "大腿", "臀部", "小腿"};
        dbmanager.add_item(items_musle);             // 调用

//        // 测试 数据库 得到 折线图数据
//        try {
//            List <Integer> result = dbmanager.return_Line_data();
//            for (Integer data : result){
//                System.out.println(data);
//            }
//        }catch (SQLException e){
//            System.out.println("erro ");
//        }
//
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
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
//        String date = df.format(new Date());// new Date()为获取当前系统时间
//
//        //user 插入
//        String [] users = {"pky","yzb","tjl"};
//        String [] passwords = {"123456" ,"abcd" ,"efgds"};
//
//        for (int i =0 ; i<users.length ;i++){
//            Map map1 = new HashMap();
//            map1.put("name" , users[i]);
//            map1.put("password" , passwords[i]);
//            dbmanager.add_user(map1);
//        }
//
//
//        String [] items = {"举重" ,"杠铃" , "俯卧撑" , "引体向上" , "卧推"};
//        Integer [] times = {1,3,2,4,3,2,5,3} ;
//        Integer [] group = {10,12,4,6,4,1,4,6,7,3,12,22};
//
//        for (int i =0 ; i < 100 ;i++){
//            Map map2 = new HashMap();
//            map2.put("user_name" , users[i%3]);
//            map2.put("item" , items[i%5]);
//            map2.put("group_num", group[i%12]);
//            map2.put("times_num",times[i%8]);
//            map2.put("kilo" , 12.5);
//            map2.put("date",date);
//            dbmanager.add_workout_item(map2);
//        }

//        // 测试数据库关闭
//
//

        dbmanager.closeAll();
    }



}
