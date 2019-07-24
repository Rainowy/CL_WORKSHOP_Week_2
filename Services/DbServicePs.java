package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbServicePs {

    private static String dbName = "programming_school";

    private static String createDB = "Create database `"+dbName+"` Character set utf8 collate utf8_unicode_ci";

    public static void createDb(){

        try {
            DbService.executeQuery(createDB,null,"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String query, String[] params) {
        try {
            DbService.executeQuery(query, params, dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int executeInsert(String query, String[] params) {
        int newId = 0;
        try {
            newId = DbService.executeInsert(query, params, dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newId;
    }

    public static List<String[]> getData(String query, String[] params) {
        List<String[]> data = new ArrayList<>();
        try {
            data = DbService.getData(query, params, dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void printList(List<String[]> list) {
        for (String[] s : list) {
            System.out.println(Arrays.toString(s));
        }
    }
}


