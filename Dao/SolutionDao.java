package Dao;

import Entity.Solution;
import Services.DbServicePs;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SolutionDao {

    public static void save(Solution solution) {
        if (solution.getId() == 0) {
            addToDb(solution);
        } else {
            //  updateInDb(solution);
        }
    }

    private static void addToDb(Solution solution) {
        String query = "insert into solution values (null,?,?,?,?,?);";
        String[] params = new String[5];
        params[0] = String.valueOf(solution.getCreated());
        params[1] = String.valueOf(solution.getUpdated());
        params[2] = solution.getDescription();
        params[3] = String.valueOf(solution.getExercise().getId());
        params[4] = String.valueOf(solution.getUsers().getId());
        int newId = DbServicePs.executeInsert(query, params);
        solution.setId(newId);

    }

    public static void getById(int id) {
        String query = "select * from solution where id=?;";
        String[] params = {String.valueOf(id)};
        List<String[]> data = DbServicePs.getData(query, params);
         getSingleData(data);
    }

    private static Solution getSingleData(List<String[]> data) {

        Solution tmp = new Solution();
        String[] firstRow = data.get(0);
        tmp.setId(Integer.valueOf(firstRow[0]));
        String strCreated = firstRow[1];
        Date created = Date.valueOf(strCreated);
        tmp.setCreated(created);
        String strUpdated = firstRow[2];
        Date updated = Date.valueOf(strUpdated);
        tmp.setUpdated(updated);
        tmp.setExercise(ExerciseDao.getByName(firstRow[3]));
        tmp.setUsers(UserDao.getByUserName(firstRow[4]));
        return tmp;


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date3 = new Date(0);

//        try {
//            date3 = (Date) format.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        tmp.setCreated(date3);


    }


}
