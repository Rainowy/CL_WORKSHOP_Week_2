package Dao;

import Entity.Exercise;
import Services.DbServicePs;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {

    public static void save(Exercise exercise) {
        if (exercise.getId() == 0) {
            saveToDb(exercise);
        } else {
             updateInDb(exercise);
        }
    }

    public static Exercise getById(int id) {
        String query = "select * from exercise where id = ?;";
        String[] params = {String.valueOf(id)};

        List<String[]> data = DbServicePs.getData(query, params);

        if (data.size() > 0) {
            return getSingleExercise(data);
        }
        return null;
    }

    private static void saveToDb(Exercise exercise) {
        String query = "insert into exercise values (null,?,?);";
        String[] params = {exercise.getTitle(), exercise.getDescription()};
        int newId = DbServicePs.executeInsert(query, params);
        exercise.setId(newId);
    }

    public static Exercise getByName(String name) {
        String query = "select * from exercise where title = ?;";
        String[] params = {name};
        List<String[]> data = DbServicePs.getData(query, params);

        if (data.size() > 0) {
            return getSingleExercise(data);
        }
        return null;
    }

    private static Exercise getSingleExercise(List<String[]> list) {
        Exercise tmp = new Exercise();
        for (String[] firstRow : list) {
            tmp.setId(Integer.valueOf(firstRow[0]));
            tmp.setTitle(firstRow[1]);
            tmp.setDescription(firstRow[2]);
        }
        return tmp;
    }
    private static List<Exercise> getAllExercises(List<String[]> list) {
        List<Exercise> result = new ArrayList<>();
        for (String[] firstRow : list) {
            Exercise tmp = new Exercise();
            tmp.setId(Integer.valueOf(firstRow[0]));
            tmp.setTitle(firstRow[1]);
            tmp.setDescription(firstRow[2]);
            result.add(tmp);
        }
        return result;
    }
    public static void updateInDb(Exercise exercise){
        String query = "update exercise set title = ?, description=? where id =?;";
        String [] params = new String[3];
        params[0] = exercise.getTitle();
        params[1] = exercise.getDescription();
        params[2] = String.valueOf(exercise.getId());
        DbServicePs.executeQuery(query,params);

    }
    public static void delete(Exercise exercise){
        String query = "delete from exercise where id=?;";
        String[] params = {String.valueOf(exercise.getId())};
        DbServicePs.executeQuery(query,params);
    }
    public static void delete(int id){
        delete(ExerciseDao.getById(id));
    }

    public static List<Exercise> getAll(){
        String query = "select * from exercise;";
        List<String[]> data = DbServicePs.getData(query, null);
        return getAllExercises(data);
    }

}
