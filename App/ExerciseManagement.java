package App;

import Dao.ExerciseDao;
import Entity.Exercise;
import Services.DbServicePs;

import java.util.List;
import java.util.Scanner;

public class ExerciseManagement extends UserManagement {
    static Scanner scan = new Scanner(System.in);

    public static void printAllExercises() {
        String query = "Select * from exercise;";
        List<String[]> data = DbServicePs.getData(query, null);
        DbServicePs.printList(data);
    }

    public static void management() {

        System.out.println("Wybierz jedną z opcji: 'add', 'edit', 'delete', 'quit'");
        String answer;
        do {
            answer = scan.next();
            switch (answer) {
                case "add":
                    addExercise();
                    break;
                case "edit":
                    editExercise();
                    break;
                case "delete":
                    deleteExercise();
                    break;
            }
            if ((answer.equalsIgnoreCase("quit"))) {
                System.out.println("EXIT PROGRAM");
            } else if (!answer.equalsIgnoreCase("add") && (!answer.equalsIgnoreCase("edit") && (!answer.equalsIgnoreCase("delete") && (!answer.equalsIgnoreCase("quit"))))) {
                System.out.println("Nie wybrano prawidłowej odpowiedzi, spróbuj ponownie");
            } else {
                System.out.println("Wybierz jedną z opcji: 'add', 'edit', 'delete', 'quit'");
            }
            //scan.nextLine();    //TO JEST WAŻNE, KASUJE POPRZEDNI SCAN
            //wydaje się, że scan.nextLine() jest potrzebne gdy wcześniejsze skanowanie było wykonane za pomocą scan.next() wtedy ono kasuje nadmiarowy wpis, natomiast gdy ostatnie jest scan.nextLine() to nie trzeba go znowu używać
        }
        while (!answer.equalsIgnoreCase("quit"));

    }

    private static void addExercise() {
        System.out.println("Dodaj nowe zadanie: ");
        Exercise newExercise = new Exercise();
        setExerciseData(newExercise);
        System.out.println("DODANO ZADANIE");
    }

    private static void setExerciseData(Exercise newExercise) {
        scan.nextLine();
        System.out.println("Wprowadź tytuł");
        newExercise.setTitle(scan.nextLine());
        System.out.println("Wprowadź opis");
        newExercise.setDescription(scan.nextLine());
        ExerciseDao.save(newExercise);
    }

    private static void editExercise() {
        System.out.println("Zmodyfikuj istniejące zadanie");
        System.out.println("Podaj Id zadania");
        Exercise newExercise = new Exercise();
        newExercise.setId(validateId());
        setExerciseData(newExercise);
        System.out.println("ZMODYFIKOWANO ZADANIE");

    }

    private static void deleteExercise() {
        System.out.println("Skasuj zadanie o podanym ID");
        String query = "delete from exercise where id =?;";
        String[] params = {String.valueOf(validateId())};
        DbServicePs.executeQuery(query, params);
        System.out.println("USUNIĘTO ZADANIE");
    }

    public static int validateId() {
        int dbId;
        do {
            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("to nie jest numer");
            }
            dbId = scan.nextInt();
            if (!idExistorNot(dbId)) {
                System.out.println("Nie ma zadania o podanym Id w bazie");
            }
        }
        while (!idExistorNot(dbId));
        return dbId;
    }

    public static boolean idExistorNot(int id) {
        String query = "select id from exercise;";
        List<String[]> data = DbServicePs.getData(query, null);
        boolean answer = false;
        for (String[] s : data) {
            if (s[0].equals(String.valueOf(id))) {
                answer = true;
                break;
            }
        }
        return answer;
    }

}
