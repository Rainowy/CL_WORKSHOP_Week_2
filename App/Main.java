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
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) {

//        Users firstUser = new Users();
//        firstUser.setUserName("tomek");
//        firstUser.setEmail("tomek@gmail.com");
//        firstUser.setPassword("sabaka");
//        firstUser.setUserGroup(UserGroupDao.getById(1));
//        UserDao.save(firstUser);

//        System.out.println(UserGroupDao.getByName("Admin"));
//        System.out.println(UserGroupDao.getById(2));
//        Users byId = UserDao.getById(1);
//        byId.setUserName("kropek");
//        byId.setEmail("sygfds@o2");
//        byId.setPassword("gfsdf7843");
//        byId.setUserGroup(UserGroupDao.getByName("User"));
//        UserDao.save(byId);

//        Users byId = UserDao.getById(2);
//        System.out.println(byId);

//        Users firstUser = new Users();
//        firstUser.setUserName("patafian");
//        firstUser.setEmail("tomekono@gmail.com");
//        firstUser.setPassword("chrono");
//        firstUser.setUserGroup(UserGroupDao.getById(1));
//        UserDao.save(firstUser);

//        Users byId = UserDao.getById(6);
//        byId.setEmail("zmiana maila");
//        UserDao.save(byId);
//        System.out.println(UserDao.getByUserName("kropek"));
//
//        System.out.println(UserDao.getByUserName("paweł"));
//        UserDao.delete(UserDao.getByUserName("paweł"));
        //UserDao.delete(3);

//        List<Users> all = UserDao.getAll();
//
//        for(Users u : all){
//            System.out.println(u);
//        }

//        UserGroup newUserGroup = new UserGroup("Guest");
//        UserGroupDao.save(newUserGroup);

//        UserGroup guest = UserGroupDao.getByName("Guest");
//        guest.setName("Goście");
//        UserGroupDao.save(guest);

//        UserGroup gluty = new UserGroup("Gluty");
//        UserGroupDao.save(gluty);
//        UserGroup gluty1 = UserGroupDao.getByName("Gluty");
//        UserGroupDao.delete(gluty1);

        //UserGroupDao.delete(4);
        //  System.out.println(UserGroupDao.getById(4));

//        List<UserGroup> all = UserGroupDao.getAll();
//        for (UserGroup u : all) {
//            System.out.println(u);
//        }

//        UserGroup nowy = new UserGroup("Players");
//        UserGroupDao.save(nowy);
//
//        List<UserGroup> all = UserGroupDao.getAll();
//        DbServicePs.printUserGroupList(all);

//        Exercise newExercise = new Exercise();
//        newExercise.setTitle("Arrays");
//        newExercise.setDescription("Basic Array definition and examples");
//        ExerciseDao.save(newExercise);
      //  System.out.println(ExerciseDao.getById(1));
//        Exercise arrays = ExerciseDao.getByName("Arrays");
//        System.out.println(arrays);
//        arrays.setTitle("Arrays and Lists");
//        arrays.setDescription("Arrays and Lists examples");
//        ExerciseDao.save(arrays);

//        Exercise nextOne = new Exercise();
//        nextOne.setTitle("extends");
//        nextOne.setDescription("Basic extends");
//        ExerciseDao.save(nextOne);

//        Exercise loops = ExerciseDao.getByName("Loops");
//
//        ExerciseDao.delete(loops);

       // ExerciseDao.delete(1);

//        Exercise newOne = new Exercise()

//        List<Exercise> all = ExerciseDao.getAll();
//        for(Exercise e: all){
//            System.out.println(e);
//        }
//        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
//        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
//        Exercise exercise = ExerciseDao.getById(5);
//        Users user = UserDao.getById(4);

        String createdDate = "1982-03-16 23:37:50";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH,1,12,35,43);
        Date calDate = calendar.getTime();
        java.sql.Timestamp date3 = new java.sql.Timestamp(calDate.getTime());

//        DateTime toInsert = new DateTime(createdDate);
//        System.out.println(toInsert);



//        Date date1 = new Date(0);
//       // java.sql.Timestamp date2
//
//        System.out.println(sqlDate);
//
//        // date2 = format.parse(createdDate);

//        try {
//             date1 = format.parse(createdDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        java.sql.Timestamp dateTime = new java.sql.Timestamp(date1.getTime());
////
//        Solution solution = new Solution();
//        solution.setCreated(date3);
//        solution.setUpdated(date);
//        solution.setDescription("Solution usivxcvvxndfhgfhfghg for loops");
//        solution.setExercise(exercise);
//        solution.setUsers(user);
//        SolutionDao.save(solution);

        SolutionDao.getById(1);

    }
}
