package com.company;

import com.company.marks.GetMarks;
import com.company.core.GetList;
import com.company.core.GetSpecificCity;
import com.company.core.LogIn;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //město
        ArrayList<String> cities = GetList.getCities();
        System.out.println(cities);

        System.out.println("\nVyberte město a napište ho: ");
        String city = scanner.nextLine();

        // škola
        ArrayList<ArrayList<String>> schools = GetSpecificCity.getSchools(city);
        System.out.println(schools);

        System.out.println("\nVyberte URL školy a napište ho: ");
        String school = scanner.nextLine();

        // login
        System.out.println("\nUživatelské jméno: ");
        String username = scanner.nextLine();

        System.out.println("Uživatelské heslo: ");
        String password = scanner.nextLine();

        ArrayList<String> userInfo = LogIn.getData(username, password, school);
        String auth = userInfo.get(0);

        // známky
        new GetMarks(school, auth).getInstance();

        System.out.println(GetMarks.get_mark_date());
        System.out.println(GetMarks.get_edit_date());
        System.out.println(GetMarks.get_caption());
        System.out.println(GetMarks.get_marks());
        System.out.println(GetMarks.get_weight());
        System.out.println(GetMarks.get_points());
        System.out.println(GetMarks.get_subject(true));
        System.out.println(GetMarks.get_subject(false));
        System.out.println(GetMarks.get_average());
    }
}
