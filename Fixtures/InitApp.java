package Fixtures;

public class InitApp {

    private static String queryCreateUsers =
            "create table users(" +
                    "id int auto_increment," +
                    "username varchar(255)," +
                    "email varchar (255) unique," +
                    "password varchar (255)," +
                    "user_group_id int," +
                    "primary key (id)," +
                    "foreign key (user_group_id) references user_group (id))" +
                    ");";

    private static String queryCreateUser_Group =
            "create table user_group(" +
                    "id int auto_increment," +
                    "name varchar(255)," +
                    "primary key (id)" +
                    ");";

    private static String queryCreateExercise =
            "create table exercise(" +
                    "id int auto_increment," +
                    "title varchar(255)," +
                    "description text," +
                    "primary key (id)" +
                    ");";

    private static String queryCreateSolution =
            "create table solution(" +
                    "id int auto_increment," +
                    "created DATETIME," +
                    "updated DATETIME," +
                    "description text," +
                    "exercise_id int," +
                    "users_id BIGINT," +
                    "primary key (id)," +
                    "foreign key (exercise_id) references exercise (id)," +
                    "foreign key (users_id) references users (id)" +
                    ");";






}
