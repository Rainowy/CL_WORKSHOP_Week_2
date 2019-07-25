package App;

import Dao.UserDao;
import Dao.UserGroupDao;
import Entity.UserGroup;
import Entity.Users;
import Services.DbService;
import Services.DbServicePs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<Users> all = UserDao.getAll();

        for(Users u : all){
            System.out.println(u);
        }
    }
}
