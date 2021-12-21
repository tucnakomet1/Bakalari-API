package com.company.marks;

import java.util.ArrayList;

public class Average {
    public Average(){}

    public static ArrayList<Float> getAverage(ArrayList<String> subjects_list, ArrayList<Boolean> points_list, ArrayList<ArrayList<Float>> marks_list, ArrayList<ArrayList<Integer>> weight_list) {
        ArrayList<Float> average = new ArrayList<>();

        for (int i = 0; i < subjects_list.size(); i++) {
            float mark_count = 0;
            float weight_count = 0;

            if (!points_list.get(i)) {
                for (int j = 0; j < marks_list.get(i).size(); j++) {
                    float mark = marks_list.get(i).get(j);
                    float weight = weight_list.get(i).get(j);

                    mark_count = mark_count + (mark * weight);
                    weight_count = weight_count + weight;
                }
                float res = (float) (Math.round((mark_count /weight_count)* 100d) / 100d);
                average.add(res);

            } else {
                for (int j = 0; j < marks_list.get(i).size(); j++) {
                    float mark = marks_list.get(i).get(j);
                    float weight = weight_list.get(i).get(j);

                    mark_count = mark_count + mark;
                    weight_count = weight_count + weight;
                }
                float res = (float) (Math.round(((mark_count /weight_count)*100)* 100) / 100);
                average.add(res);
            }
        }
        return average;
    }
}
