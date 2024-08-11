package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private List<User> users;
    Scanner sc ;

    public UserManager() {
        this.users = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public boolean registerUser(String userId, String password) {
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                System.out.println("Username already exists. Please choose a different username .");
                return false;
            }
        }
        users.add(new User(userId, password));

        return true;
    }

    public boolean userNmaeChecker( String userid ){
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userid)) {
                return true;
            }
        }
        return false;
    }


    public void changeUserId(String userId , String password){
        boolean check = false ;
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equalsIgnoreCase(password)) {

               do {
                   String userid = sc.next();
                   if (!userNmaeChecker(userid) ){
                       user.setUserId(userid);
                       System.out.println("User name change sussesfully");
                       check = true ;
                   }else {
                       System.out.println("Username already exists. Please choose a different username .");
                   }
               }while (!check);
            }
        }


    }
    public void changeUserPass(String userId, String password) {
        int t = 3;
        do {
            for (User user : users) {
                if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equalsIgnoreCase(password)) {


                    System.out.print("type new passwoed : ");
                    String newPass = sc.next();
                    System.out.print("Conform new passwoed : ");
                    String comformPass = sc.next();
                    if (newPass.equalsIgnoreCase(comformPass)) {
                        user.setPassword(newPass);
                        t = 0;
                        System.out.println("\nPassword change sussesfully");
                    } else {
                        System.out.println("NewPass and Conform pass dosen't mach");
                        t--;
                    }
                }else {
                    t--;
                    System.out.println("Invalid User name or password. Try again left( "+t+" ) attempt ");
                }
            }
        } while (t > 0);
    }

    public User loginUser(String userId, String password) {
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }
}