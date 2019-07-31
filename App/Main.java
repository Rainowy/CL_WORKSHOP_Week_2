package App;

import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;
import Dao.UserGroupDao;
import Entity.Exercise;
import Entity.Solution;
import Entity.UserGroup;
import Entity.Users;
import Services.DbService;
import Services.DbServicePs;


import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) {



        /** USER MANAGEMENT **/
        Scanner scan = new Scanner(System.in);

        //Print all users
        UserManagement.printAllUsers();
        System.out.println();
        //Manage Users
        UserManagement.management();

        //System.out.println(UserManagement.idExistorNot(10));


    }
}
