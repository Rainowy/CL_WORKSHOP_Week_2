package App;

import Services.DbService;
import Services.DbServicePs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static String queryInsertIntoAddress = "insert into tickets (`quantity`,`price`,`name`) values (?,?,?);";
    private static String queryGetAll = "select * from tickets;";


    public static void main(String[] args) {

//        String [] params = new String[3];
//        params[0] = "79";
//        params[1] = "2.8";
//        params[2] = "KONTEMPLACJA";

//        int newID =0;
//        try {
//           newID = DbService.executeInsert(queryInsertIntoAddress, params, "cinemas_ex");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("new ID to: " + newID );

//        List<String[]> getAll = new ArrayList<>();
//        try {
//            getAll = DbService.getData(queryGetAll,null, "cinemas_ex");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        for(String[] s: getAll){
//            System.out.println(Arrays.toString(s));
//        }
        DbServicePs.printList(DbServicePs.getData(queryGetAll,null));
    }
}
