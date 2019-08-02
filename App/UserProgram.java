//package App;

import App.SolutionManagement;
import App.UserManagement;
import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;
import Entity.Exercise;
import Entity.Solution;
import Services.DbServicePs;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserProgram {

    static Scanner scan = new Scanner(System.in);
    static List<String[]> excludedExcercise;
    static List<String[]> data;
    static Timestamp current = new Timestamp(new java.util.Date().getTime());
    static String[] args = new String[1];
    static int userIdStatic;


    public static void main(String[] args) {


//        args = new String[1];             usuń linie jeżeli uruchamiasz z IDE
//        args[0] = String.valueOf(10);


        if (args.length == 0) {
            //System.out.println("wyjście z programu");
            try {
                throw new Exception("NALEŻY PODAĆ PARAMETR, URUCHOM PROGRAM PONOWNIE Z PODANYM PARAMETREM");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        userIdStatic = Integer.valueOf(args[0]);
        if (!UserManagement.idExistorNot(userIdStatic)) {
            try {
                throw new Exception("NO SUCH USER ID IN DATABASE");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);

        }
//        int userId = Integer.valueOf(args[0]);
//        userIdStatic = userId;

        System.out.println("User Id to: " + userIdStatic);

        System.out.println("Lista zadań bez dodanych rozwiązań");
        System.out.println();
        excludedExcercise = ExerciseDao.loadExcluded(userIdStatic);

        //Lista z podanymi zadaniami, do których nie ma jeszcze rozwiązań
        DbServicePs.printList(excludedExcercise);

        init();

    }

    private static void init() {
        System.out.println();
        System.out.println("Choose one of the options: \n\n" + "[a]dd = add solution to the exercise \n" + "[v]iew = show your solutions \n" + "[u]pdate = update solution \n" + "quit = exit program");
        System.out.println();

        String answer;
        do {
            answer = scan.nextLine();
            switch (answer.toLowerCase()) {
                case "a":
                    addSolution();
                    break;
                case "v":
                    if (emptyCheck()) { //sprawdza czy lista z rozwiązaniami pusta
                        System.out.println("THERE IS NO SOLUTIONS FOR USER NR " + userIdStatic);
                        System.out.println();
                    } else {
                        viewSolutions();
                    }
                    break;
                case "u":
                    updateSolutions();
                    break;
            }

            if (answer.equals("quit")) {
                System.out.println("EXIT PROGRAM");
            } else if ((!answer.equals("a") && (!answer.equals("v")) && (!answer.equals("u") && (!answer.equals("quit"))))) {
                System.out.println("YOU MUST ENTER PROPER ANSWER");
            } else {
                System.out.println("Choose one of the options: \n\n" + "[a]dd = add solution to the exercise \n" + "[v]iew = show your solutions \n" + "[u]pdate = update solution \n" + "quit = exit program");
            }
            // scan.nextLine();

        }
        while (!answer.equals("quit"));
    }

    private static void addSolution() {

        System.out.println("Enter excersise id where you want to add solution");

        int id = checkIfExists();

        //System.out.println("zadanie o id istnieje w bazie " + id);

        System.out.println("Add description to your solution");

        scan.nextLine();
        String description = scan.nextLine();

        Solution newSolution = new Solution();

        newSolution.setCreated(current);
        newSolution.setUpdated(current);    //jak  tu wsadzić null
        newSolution.setDescription(description);
        newSolution.setExercise(ExerciseDao.getById(id));
        newSolution.setUsers(UserDao.getById((userIdStatic)));
        SolutionDao.save(newSolution);
    }

    private static int checkIfExists() {
        int id;
        do {
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number!");
            }
            id = scan.nextInt();

            if (!ifExists(id)) {
                System.out.println("Exercise doesnt exist in database");
            }

        }
        while (!ifExists(id));
        return id;
    }

    //przeciążona metoda, parametr a nieużywany
    private static int checkIfExists(int a) {
        int id;
        do {
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number!");
            }
            id = scan.nextInt();

            if (!ifExists(String.valueOf(id))) {
                System.out.println("Exercise doesnt exist in database");
            }

        }
        while (!ifExists(String.valueOf(id)));
        return id;
    }

    private static void viewSolutions() {

        emptyCheck();   //sprawdzam czy lista pusta

        System.out.println();
        System.out.println("List of Solutions from user  " + userIdStatic);
        System.out.println();
        DbServicePs.printList(data);
    }

    private static boolean emptyCheck() {   //sprawdzam czy rozmiar listy z rozwiązaniami = 0

        String query = "select solution.id, description,created,updated from solution where users_id =?";
        String[] params = {String.valueOf(userIdStatic)};
        data = DbServicePs.getData(query, params);
        if (data.size() == 0) {
            return true;
        }
        return false;
    }

    private static void updateSolutions() {

        if (emptyCheck()) {
            System.out.println("THERE IS NO SOLUTIONS FOR USER NR " + userIdStatic);
            System.out.println();
            return;
        }
        viewSolutions();
        System.out.println();
        System.out.println("Enter Solution Id to Update");
        System.out.println();


        int solutionID = checkIfExists(1);


        System.out.println("Enter new Description: ");
        scan.nextLine();
        String description = scan.nextLine();
        //pobieram exercise id
        Solution tmp = SolutionDao.getById(solutionID);
        int exerciseId = tmp.getExercise().getId();
        //
        //pobieram datę
        Date created = tmp.getCreated();
        //
        Solution solution = new Solution();
        solution.setId(solutionID);
        solution.setCreated(created);
        solution.setUpdated(current);
        solution.setDescription(description);
        solution.setExercise(ExerciseDao.getById(exerciseId));
        solution.setUsers(UserDao.getById(userIdStatic));
        SolutionDao.save(solution);


    }

    private static boolean ifExists(int id) {

        boolean exists = false;
        for (String[] s : excludedExcercise) {
            if (s[0].equals(String.valueOf(id))) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private static boolean ifExists(String id) {

        boolean exists = false;
        for (String[] s : data) {
            if (s[0].equals(id)) {
                exists = true;
                break;
            }
        }
        return exists;


    }


}
