package App;

import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;
import Entity.Solution;
import Services.DbService;
import Services.DbServicePs;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SolutionManagement {
    private static Scanner scan = new Scanner(System.in);


    public static void management() {

        System.out.println("Wybierz jedną z opcji: 'add', 'view', 'quit'");
        String answer;
        do {
            answer = scan.next();
            switch (answer) {
                case "add":
                    addSolution();
                    break;
                case "view":
                    viewSolution();
                    break;

            }
            if ((answer.equalsIgnoreCase("quit"))) {
                System.out.println("EXIT PROGRAM");
            } else if (!answer.equalsIgnoreCase("add") && (!answer.equalsIgnoreCase("view") && (!answer.equalsIgnoreCase("quit")))) {
                System.out.println("Nie wybrano prawidłowej odpowiedzi, spróbuj ponownie");
            } else {
                System.out.println("Wybierz jedną z opcji: 'add', 'view', 'quit'");
            }

            scan.nextLine();    //TO JEST WAŻNE, KASUJE POPRZEDNI SCAN
        }
        while (!answer.equalsIgnoreCase("quit"));
    }

    private static void addSolution() {
        //wyświetlam listę użytkowników

        System.out.println("WYŚWIETLAM UŻYTKOWNIKÓW");
        System.out.println();
        UserManagement.printAllUsers();
        System.out.println();
        System.out.println("Wybierz użytkownika");

        //używam metody validateId w UserManagement

        int userId = UserManagement.validateId();
        System.out.println("WYŚWIETLAM WSZYSTKIE ZADANIA");
        System.out.println();
        ExerciseManagement.printAllExercises();
        System.out.println();
        System.out.println("wybierz zadanie");
        int exerciseId = ExerciseManagement.validateId();
        System.out.println("zadanie to: " + exerciseId);
        Timestamp current = new Timestamp(new java.util.Date().getTime());

        // methodTosetTimestampToNull(userId, exerciseId, current);


        addSolution(userId, exerciseId, current);


    }

    public static void addSolution(int userId, int exerciseId, Timestamp current) {

        /** tak to powinno wyglądać ale nie wiem jak dodać w tej metodzie poprzez obiekty Timestamp na null **/

        Solution newSolution = new Solution();

        newSolution.setCreated(current);
        newSolution.setUpdated(current);
        newSolution.setDescription(null);
        newSolution.setExercise(ExerciseDao.getById(exerciseId));
        newSolution.setUsers(UserDao.getById(userId));
        SolutionDao.save(newSolution);
    }

    private static void methodTosetTimestampToNull(int userId, int exerciseId, Timestamp current) {

        /** metoda na około, nie powinno to tak być zrobione ale na takim niskim poziomie mogę dodać Datetime jako null **/

        String query = "insert into solution values (null,?,?,?,?,?);";

        try {
            Connection conn = DbService.getConnection("programming_school");

            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, String.valueOf(current));
            st.setNull(2, Types.DATE);
            st.setString(3, null);
            st.setInt(4, exerciseId);
            st.setInt(5, userId);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewSolution() {

        System.out.println("WYŚWIETLAM UŻYTKOWNIKÓW");
        System.out.println();
        UserManagement.printAllUsers();
        System.out.println();
        System.out.println("Wybierz użytkownika");

        //używam metody validateId w UserManagement

        int userId = UserManagement.validateId();
        System.out.println("Wyświetlam dane: ");
        System.out.println();
        List<String[]> allByUserId = SolutionDao.findAllByUserId(userId);
        DbServicePs.printList(allByUserId);

    }

}

