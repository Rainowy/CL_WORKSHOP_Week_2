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
        //

//        //Print all users
//        UserManagement.printAllUsers();
//        System.out.println();
//        //Manage Users
//        UserManagement.management();


        /**EXERCISE MANAGEMENT**/
//        //Print all users
//        ExerciseManagement.printAllUsers();
//        System.out.println();
//        Manage Exercise
        //        ExerciseManagement.management();

        /** USER_GROUP MANAGEMENT **/

        UserGroupManagement.printAllUserGroups();
        UserGroupManagement.management();

    }
}
