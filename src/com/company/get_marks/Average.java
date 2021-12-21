package com.company.get_marks;

import java.util.ArrayList;
import java.util.Scanner;

public class Average {
    public static ArrayList<String> subjects_list = GetMarks.subjects_list;
    public static ArrayList<Boolean> points_list = GetMarks.points_list;
    public static ArrayList<ArrayList<Float>> marks_list = GetMarks.marks_list;
    public static ArrayList<ArrayList<Integer>> weight_list = GetMarks.weight_list;


    public Average(){
        for (int i = 0; i < subjects_list.size(); i++) {
            System.out.println(subjects_list.get(i));
            System.out.println(marks_list.get(i));

            float mark_count = 0;
            float weight_count = 0;

            if (!points_list.get(i)) {
                for (int j = 0; j < marks_list.get(i).size(); j++) {
                    float mark = marks_list.get(i).get(j);
                    float weight = weight_list.get(i).get(j);

                    System.out.println(mark_count + " + " + mark + " * " + weight);
                    mark_count = mark_count + (mark * weight);
                    weight_count = weight_count + weight;
                }
                System.out.println(mark_count + " / " + marks_list.get(i).size());
                float res = mark_count /weight_count;

                System.out.println(res + "\n");
            } else {
                for (int j = 0; j < marks_list.get(i).size(); j++) {
                    float mark = marks_list.get(i).get(j);
                    float weight = weight_list.get(i).get(j);

                    System.out.println(mark_count + " + " + mark + " * " + weight);
                    mark_count = mark_count + mark;
                    weight_count = weight_count + weight;
                }
                System.out.println(mark_count + " / " + weight_count);
                float res = (mark_count /weight_count)*100;

                System.out.println(res + "% \n");
            }
        }
    }

    //    public static Float getAverage(Float[] marks, boolean percent) {
    public static Float getAverage() {
        float res = 0;
        for (int i = 0; i<subjects_list.size(); i++) {
            System.out.println(subjects_list.get(i));
            System.out.println(marks_list.get(i));
            
            float count = 0;
            
            for (int j = 0; j < marks_list.get(i).size(); j++) {
                count = count + marks_list.get(i).get(j);
            }

            res = count/marks_list.get(i).size();

            System.out.println(res);
        }

        return res;

    }
}
