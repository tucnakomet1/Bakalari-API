package com.company;

import com.company.get_marks.Average;
import com.company.get_marks.GetMarks;
import com.company.get_school.GetList;
import com.company.get_school.GetSpecificCity;
import com.company.get_school.GetUserInfo;

import java.util.Scanner;

public class Main {

    public static String school = "https://gymjbc.bakalari.cz";
    public static String username = "velic22190";
    public static String password = "40nf5btm";


    public static void main(String[] args) {
        /*System.exit(1);

        Scanner scanner = new Scanner(System.in);

        new GetList();
        System.out.println("\nVyberte město a napište ho: ");
        String city = scanner.nextLine();

        new GetSpecificCity(city);
        System.out.println("\nVyberte URL školy a napište ho: ");
        String school = scanner.nextLine();

        System.out.println("\nUživatelské jméno: ");
        String username = scanner.nextLine();

        System.out.println("\nUživatelské heslo: ");
        String password = scanner.nextLine();
*/

        new GetUserInfo(username, password, school);

        System.out.println("\n" + info.getApiVersion());
        System.out.println(info.getAppVersion());
        System.out.println(info.getUserId());
        System.out.println(info.getAccess_token());
        System.out.println(info.getToken_type());
        System.out.println(info.getExpires_in());
        System.out.println(info.getScope());
        System.out.println(info.getRefresh_token());

        String auth = info.getAccess_token();
        System.out.println(auth);
        new GetMarks(school, auth);
        new Average();


    }
}
