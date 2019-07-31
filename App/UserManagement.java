package App;

import Dao.UserDao;
import Dao.UserGroupDao;
import Entity.Users;
import Services.DbServicePs;

import java.util.List;
import java.util.Scanner;

public class UserManagement {
    static Scanner scan = new Scanner(System.in);

    public static void printAllUsers() {
        String query = "Select * from users;";
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
                    addUser();
                    break;
                case "edit":
                    editUser();
                    break;
                case "delete":
                    deleteUser();
                    break;
            }
            if((answer.equalsIgnoreCase("quit"))){
                System.out.println("EXIT PROGRAM");
            }
            else if (!answer.equalsIgnoreCase("add") && (!answer.equalsIgnoreCase("edit") && (!answer.equalsIgnoreCase("delete") && (!answer.equalsIgnoreCase("quit")) ))) {
                System.out.println("Nie wybrano prawidłowej odpowiedzi, spróbuj ponownie");
            }
            else {
                System.out.println("Wybierz jedną z opcji: 'add', 'edit', 'delete', 'quit'");
            }

            scan.nextLine();    //TO JEST WAŻNE, KASUJE POPRZEDNI SCAN
        }
        while (!answer.equalsIgnoreCase("quit"));

    }

    private static void addUser() {
        System.out.println("Dodaj nowego użytkownika: ");
        Users newUser = new Users();
        setUserData(newUser);
        System.out.println("DODANO UŻYTKOWNIKA");
    }

    private static void editUser() {
        System.out.println("Zmodyfikuj istniejącego użytkownika");
        System.out.println("Podaj Id użytkownika");
        Users newUser = new Users();
        newUser.setId(validateId());
        setUserData(newUser);
        System.out.println("ZMODYFIKOWANO UŻYTKOWNIKA");
    }

    private static void setUserData(Users newUser) {
        System.out.println("Wprowadź imię");
        newUser.setUserName(scan.next());
        System.out.println("Wprowadź email");
        newUser.setEmail(scan.next());
        System.out.println("Wprowadź hasło");
        newUser.setPassword(scan.next());
        int id;
        do{
            System.out.println("Wprowadź numer grupy: 1 = Admin, 2 = User");
            while (!scan.hasNextInt()){
                scan.next();
                System.out.println("to nie jest numer");
            }
            id = scan.nextInt();
        }
        while(id != 1 && id !=2);
        newUser.setUserGroup(UserGroupDao.getById(id));
        UserDao.save(newUser);
    }

    private static void deleteUser() {
        System.out.println("Skasuj użytkownika o podanym ID");
        String query = "delete from users where id =?;";
        String[] params = {String.valueOf(validateId())};
        DbServicePs.executeQuery(query,params);
        System.out.println("USUNIĘTO UŻYTKOWNIKA");
    }

    private static int validateId() {
        int dbId;
        do{
            while(!scan.hasNextInt()){
                scan.nextLine();
                System.out.println("to nie jest numer");
            }
            dbId = scan.nextInt();
            if(!idExistorNot(dbId)){
                System.out.println("Nie ma użytkownika o podanym Id w bazie");
            }
        }
        while(!idExistorNot(dbId));
        return dbId;
    }

    public static boolean idExistorNot(int id){
        String query = "select id from users;";
        List<String[]> data = DbServicePs.getData(query, null);
        boolean answer= false;
        for(String[] s: data){
            if(s[0].equals(String.valueOf(id))){
                answer = true;
              break;
            }
        }
        return answer;
    }
}
