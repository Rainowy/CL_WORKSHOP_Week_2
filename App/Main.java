package App;

import Services.DbService;

import java.sql.SQLException;

public class Main {

    private static String queryInsertIntoAddress = "insert into tickets (`quantity`,`price`,`name`) values (?,?,?);";



    public static void main(String[] args) {

        String [] params = new String[3];
        params[0] = "15";
        params[1] = "1.9";
        params[2] = "transfprmator";


        try {
            DbService.executeQuery(queryInsertIntoAddress,params,"cinemas_ex");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
