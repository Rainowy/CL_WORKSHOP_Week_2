package Dao;

import Entity.Users;
import Services.DbServicePs;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static void save(Users users) {
        if (users.getId() == 0) {
            addToDB(users);
        } else {
            updateInDb(users);
        }
    }
    private static void addToDB(Users users) {
        String query = "insert into users values(null,?,?,?,?);";
        String[] params = new String[4];
        params[0] = users.getUserName();
        params[1] = users.getEmail();
        params[2] = users.getPassword();
        params[3] = String.valueOf(users.getUserGroup().getId());
        int newId = DbServicePs.executeInsert(query, params);
        users.setId(newId);
    }

    private static void updateInDb(Users users) {
        String query = "update users set username =?,email=?,password=?,user_group_id=? where id =?;";
        String[] params = new String[5];
        params[0] = users.getUserName();
        params[1] = users.getEmail();
        params[2] = users.getPassword();
        params[3] = String.valueOf(users.getUserGroup().getId());
        params[4] = String.valueOf(users.getId());
        DbServicePs.executeQuery(query, params);
    }
    public static Users getById(int id) {
        String query = "select * from users where id =?;";
        String[] params = {String.valueOf(id)};

        return getSingleData(query, params);
    }

    private static Users getSingleData(String query, String[] params) {
        List<String[]> data = DbServicePs.getData(query, params);
        if (data.size() > 0) {
            String[] firstRow = data.get(0);
            Users tmp = getSingleUserObject(firstRow);
            return tmp;
        }
        return null;
    }

    private static Users getSingleUserObject(String[] firstRow) {
        Users tmp = new Users();
        tmp.setId(Integer.valueOf(firstRow[0]));
        tmp.setUserName(firstRow[1]);
        tmp.setEmail(firstRow[2]);
        tmp.setPassword(firstRow[3]);
        tmp.setUserGroup(UserGroupDao.getById(Integer.valueOf(firstRow[4])));
        return tmp;
    }

    public static Users getByUserName(String name) {
        String query = "select * from users where username = ?;";
        String[] params = {name};

        return getSingleData(query, params);
    }

    public static void delete(Users users) {
        String query = "delete from users where id = ?;";
        String[] params = {String.valueOf(users.getId())};
        DbServicePs.executeQuery(query, params);
    }

    public static void delete(int id) {
        Users tmp = UserDao.getById(id);
        delete(tmp);
    }

    public static List<Users> getAll() {

        String query = "select * from users";
        List<String[]> data = DbServicePs.getData(query, null);

        List<Users> result = new ArrayList<>();

        if (data.size() > 0) {
            for (String[] row : data) {
                Users tmp = getSingleUserObject(row);
                result.add(tmp);
            }
            return result;
        }
        return null;


    }
}
