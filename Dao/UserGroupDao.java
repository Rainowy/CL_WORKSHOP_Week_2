package Dao;

import Entity.UserGroup;
import Services.DbService;
import Services.DbServicePs;

import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {

    public static void save(UserGroup userGroup) {
        if (userGroup.getId() == 0) {
            saveToDb(userGroup);
        } else {
            updateInDb(userGroup);
        }
    }

    public static UserGroup getById(int id) {

        String query = "select * from user_group where id =?";
        String[] params = {String.valueOf(id)};

        return getSingleData(query, params);
    }

    private static UserGroup getSingleData(String query, String[] params) {
        List<String[]> data = DbServicePs.getData(query, params);
        if (data.size() > 0) {
            String[] firstRow = data.get(0);
            UserGroup tmp = new UserGroup(Integer.valueOf(firstRow[0]), firstRow[1]);
            return tmp;
        }
        return null;
    }

    public static UserGroup getByName(String name) {

        String query = "select * from user_group where name =?;";
        String[] params = {name};

        return getSingleData(query, params);
    }

    private static void saveToDb(UserGroup userGroup) {

        String query = "insert into user_group values(null,?);";
        String[] params = {userGroup.getName()};

        int newId = DbServicePs.executeInsert(query, params);
        userGroup.setId(newId);
    }

    private static void updateInDb(UserGroup userGroup) {
        String query = "update user_group set name = ? where id = ?;";
        String[] params = {userGroup.getName(), String.valueOf(userGroup.getId())};
        DbServicePs.executeQuery(query, params);
    }

    public static void delete(UserGroup userGroup) {
        String query = "delete from user_group where id= ?";
        String[] params = {String.valueOf(userGroup.getId())};
        DbServicePs.executeQuery(query, params);
    }

    public static void delete(int id) {
        UserGroup byId = UserGroupDao.getById(id);
        delete(byId);
    }

    public static List<UserGroup> getAll() {
        String query = "select * from user_group;";

        List<String[]> data = DbServicePs.getData(query, null);
        List<UserGroup> result = new ArrayList<>();

        if (data.size() > 0) {
            for (String[] firstRow : data) {
                // String [] firstRow = data.get(0);
                UserGroup tmp = new UserGroup(firstRow[1]);
                tmp.setId(Integer.valueOf(firstRow[0]));
                result.add(tmp);
            }
            return result;
        }
        return null;
    }
}
