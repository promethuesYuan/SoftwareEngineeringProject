import DButils.DBmanager;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestDB {

    public static void main(String[] args) throws Exception {

        //���ݿ����� �����ݱ��ʼ��
        DBmanager dbmanager = new DBmanager();

        /*
        //�������ݿ�  user ����
        Map map1 = new HashMap();
        map1.put("name" , "pky");
        map1.put("password" , "123456");
        dbmanager.add_user(map1);

        // �������ݿ�  workout_data ����
        Map map2 = new HashMap();
        map2.put("user_name" , "pky");
        map2.put("item" , "����");
        map2.put("kilo" , 12.5);
        dbmanager.add_workout_item(map2);

        // �������ݿ�   get_all_records
        String[][] s = dbmanager.get_all_records() ;

        Map map2 = new HashMap();
        map2.put("item" , "�ܲ�");

        List<String> L = dbmanager.fuzzy_query_item(map2);

        if ( L==null) System.out.println("������һ��null��");
        else if (L.isEmpty()) System.out.println("������һ�����б�");
        else System.out.println(L.toString());
        */

        // ���� ���ݿ� �õ� ����ͼ����
//        try {
//            List <Integer> result = dbmanager.return_Line_data();
//            for (Integer data : result){
//                System.out.println(data);
//            }
//        }catch (SQLException e){
//            System.out.println("erro ");
//        }

//        // �������ݿ� �õ� ��ͼ����
//
//        Map map = dbmanager.return_Pie_data();
//        for(Object key : map.keySet()){
//            String Key = key.toString();
//            int value = Integer.parseInt(map.get(Key).toString());
//            System.out.println(key+":"+value);
//        }

//        // �������ݿ�  �õ� ����ͼ����
//        String username = "pky";
//        List <Float>result = dbmanager.return_bar_data(username);
//        for (Float strenth : result){
//
//            System.out.println(strenth);
//        }

        // ���� �Ƚ�ͼ
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��

        //user ����
        String [] users = {"pky","yzb","tjl"};
        String [] passwords = {"123456" ,"abcd" ,"efgds"};

        for (int i =0 ; i<users.length ;i++){
            Map map1 = new HashMap();
            map1.put("name" , users[i]);
            map1.put("password" , passwords[i]);
            dbmanager.add_user(map1);
        }


        String [] items = {"����" ,"����" , "���Գ�" , "��������" , "����"};
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
        // �������ݿ�  workout_data ����









        // �������ݿ�ر�
        dbmanager.closeAll();
    }


    // �������ݿ�   get_all_records

    //�������ݿ� ģ����ѯ   fuzzy_query_item







}
