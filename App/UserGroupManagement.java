package App;

import Dao.UserGroupDao;
import Entity.Exercise;
import Entity.UserGroup;
import Services.DbServicePs;

import java.util.List;
import java.util.Scanner;

public class UserGroupManagement {
    static Scanner scan = new Scanner(System.in);

    public static void printAllUserGroups() {
        String query = "Select * from user_group;";
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
                    addUserGroup();
                    break;
                case "edit":
                    editUserGroup();
                    break;
                case "delete":
                    deleteUserGroup();
                    break;
            }
            if ((answer.equalsIgnoreCase("quit"))) {
                System.out.println("EXIT PROGRAM");
            } else if (!answer.equalsIgnoreCase("add") && (!answer.equalsIgnoreCase("edit") && (!answer.equalsIgnoreCase("delete") && (!answer.equalsIgnoreCase("quit"))))) {
                System.out.println("Nie wybrano prawidłowej odpowiedzi, spróbuj ponownie");
            } else {
                System.out.println("Wybierz jedną z opcji: 'add', 'edit', 'delete', 'quit'");
            }

            scan.nextLine();    //TO JEST WAŻNE, KASUJE POPRZEDNI SCAN
        }
        while (!answer.equalsIgnoreCase("quit"));

    }

    private static void addUserGroup() {
        System.out.println("Dodaj nową grupę: ");
        setUserGroupData();
        System.out.println("DODANO GRUPĘ");
    }

    private static void setUserGroupData() {
        scan.nextLine();
        // UserGroup newUserGroup = new UserGroup();
        System.out.println("Wprowadź nazwę");
        String nazwa = scan.next();
        UserGroup newUserGroup = new UserGroup(nazwa);
        UserGroupDao.save(newUserGroup);
    }

    private static void setUserGroupData(int id) {
        scan.nextLine();
        // UserGroup newUserGroup = new UserGroup();
        System.out.println("Wprowadź nazwę");
        String nazwa = scan.next();
        UserGroup newUserGroup = new UserGroup(id, nazwa);
        UserGroupDao.save(newUserGroup);
    }

    private static void editUserGroup() {
        System.out.println("Zmodyfikuj istniejącą grupę");
        System.out.println("Podaj Id grupy");
        // UserGroup newUserGroup = new UserGroup();
        setUserGroupData(validateId());
        System.out.println("ZMODYFIKOWANO GRUPĘ");

    }

    private static int validateId() {
        int dbId;
        do {
            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("to nie jest numer");
            }
            dbId = scan.nextInt();
            if (!idExistorNot(dbId)) {
                System.out.println("Nie ma grupy o podanym Id w bazie");
            }
        }
        while (!idExistorNot(dbId));
        return dbId;
    }

    public static boolean idExistorNot(int id) {
        String query = "select id from user_group;";
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

    private static void deleteUserGroup() {
        System.out.println("Skasuj grupę o podanym ID");
        String query = "delete from user_group where id =?;";
        String[] params = {String.valueOf(validateId())};
        DbServicePs.executeQuery(query, params);
        System.out.println("USUNIĘTO GRUPĘ");
    }

}
