package Dao;

import Entity.Solution;
import Services.DbServicePs;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDao {

    public static void save(Solution solution) {
        if (solution.getId() == 0) {
            addToDb(solution);
        } else {
            updateInDb(solution);
        }
    }

    private static void addToDb(Solution solution) {
        String query = "insert into solution values (null,?,?,?,?,?);";
        String[] params = new String[5];
        InsertParams(solution, params);
        int newId = DbServicePs.executeInsert(query, params);
        solution.setId(newId);

    }

    public static Solution getById(int id) {
        String query = "select * from solution where id=?;";
        String[] params = {String.valueOf(id)};
        List<String[]> data = DbServicePs.getData(query, params);

        return getSingleData(data);
    }

    private static Solution getSingleData(List<String[]> data) {

        Solution tmp = new Solution();
        String[] firstRow = data.get(0);
        setObject(firstRow, tmp);

        return tmp;
    }

    public static Solution getByname(String name) {
        String query = "select * from solution where description like ?";
        String[] params = {"%" + name + "%"};

        List<String[]> data = DbServicePs.getData(query, params);
        String[] firstRow = data.get(0);
        int id = Integer.valueOf(firstRow[0]);
        return getById(id);
    }

    private static void updateInDb(Solution solution) {
        String query = "update solution set created=?, updated=?, description=?, exercise_id=?, users_id=? where id=?;";
        String[] params = new String[6];
        InsertParams(solution, params);
        params[5] = String.valueOf(solution.getId());
        DbServicePs.executeQuery(query, params);
    }

    private static void InsertParams(Solution solution, String[] params) {
        params[0] = String.valueOf(solution.getCreated());
        params[1] = String.valueOf(solution.getUpdated());
        params[2] = solution.getDescription();
        params[3] = String.valueOf(solution.getExercise().getId());
        params[4] = String.valueOf(solution.getUsers().getId());
    }

    public static void delete(Solution solution) {
        String query = "delete from solution where id =?";
        String[] params = {String.valueOf(solution.getId())};
        DbServicePs.executeQuery(query, params);
    }

    public static void delete(int id) {
        Solution byId = SolutionDao.getById(id);
        delete(byId);
    }

    public static List<Solution> getAll() {
        String query = "select * from solution;";
        List<String[]> data = DbServicePs.getData(query, null);
        List<Solution> result = new ArrayList<>();
        if (data.size() > 0) {
            for (String[] firstRow : data) {
                Solution tmp = new Solution();
                setObject(firstRow, tmp);
                result.add(tmp);
            }
            return result;
        }
        return null;
    }

    private static void setObject(String[] firstRow, Solution tmp) {
        tmp.setId(Integer.valueOf(firstRow[0]));
        Timestamp created = Timestamp.valueOf(firstRow[1]);
        tmp.setCreated(created);
        Timestamp updated = Timestamp.valueOf(firstRow[2]);
        tmp.setUpdated(updated);
        tmp.setDescription(firstRow[3]);
        tmp.setExercise(ExerciseDao.getById(Integer.valueOf(firstRow[4])));
        tmp.setUsers(UserDao.getById(Integer.valueOf(firstRow[5])));
    }

    public static List<String[]> findAllByUserId(int id) {
        String query = "select created, updated, description from solution where users_id=?";
        String[] params = {String.valueOf(id)};
        List<String[]> data = DbServicePs.getData(query, params);
        return data;
    }

    public static List<String[]> findAllByExerciseId(int id) {
        String query = "select created, updated, description from solution where exercise_id = ? group by updated desc";
        String[] params = {String.valueOf(id)};
        List<String[]> data = DbServicePs.getData(query, params);
        return data;
    }

    public static List<String[]> findAllByGroupId(int id) {
        String query = "select username,email,password from users where user_group_id = ?";
        String[] params = {String.valueOf(id)};
        List<String[]> data = DbServicePs.getData(query, params);
        return data;
    }


}
