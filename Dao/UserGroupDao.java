package Dao;

import Entity.UserGroup;
import Services.DbServicePs;

import java.util.List;

public class UserGroupDao {

    public static UserGroup getById(int id){

        String query = "select * from user_group where id =?";
        String[] params = {String.valueOf(id)};

        return getSingleData(query, params);
    }

    private static UserGroup getSingleData(String query, String[] params) {
        List<String[]> data = DbServicePs.getData(query, params);
        if(data.size()>0){
            String[] firstRow = data.get(0);
            UserGroup tmp = new UserGroup(Integer.valueOf(firstRow[0]),firstRow[1]);
            return tmp;
        }
        return null;
    }

    public static UserGroup getByName(String name){

        String query = "select * from user_group where name =?;";
        String [] params = {name};

        return getSingleData(query, params);
    }
}
