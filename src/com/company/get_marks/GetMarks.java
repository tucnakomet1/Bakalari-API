package com.company.get_marks;

import com.company.get_school.PostRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetMarks {
    public static ArrayList<String> subjects_list = new ArrayList<>();
    public static ArrayList<Boolean> points_list = new ArrayList<>();
    public static ArrayList<ArrayList<Float>> marks_list = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> weight_list = new ArrayList<>();

    public GetMarks(String school_url, String accessToken) {
        try {
            URL marks_url = new URL(school_url+"/api/3/marks");

            String got = PostRequest.get_request(marks_url, null, accessToken, false);
            System.out.println(got);

            JSONObject obj = new JSONObject(got);

            JSONArray subjects = obj.getJSONArray("Subjects");
            for(int i=0; i<subjects.length(); i++){
                ArrayList<Float> sub_marks = new ArrayList<>();
                ArrayList<Integer> sub_weight = new ArrayList<>();

                JSONObject subject = subjects.getJSONObject(i);

                String con_subject = subject.getJSONObject("Subject").getString("Name");
                JSONArray mark_subject = subject.getJSONArray("Marks");
                boolean is_points = mark_subject.getJSONObject(0).getBoolean("IsPoints");

                subjects_list.add(con_subject);
                points_list.add(is_points);

                System.out.println(con_subject);
                System.out.println(is_points);

                for (int j = 0; j < mark_subject.length(); j++) {
                    JSONObject mark = mark_subject.getJSONObject(j);
                    String str_mark = mark.getString("MarkText");

                    int int_weight;
                    if (is_points) {
                        int_weight = mark.getInt("MaxPoints");
                    } else {
                        int_weight = mark.getInt("Weight");
                    }

                    if (str_mark.contains("-")){
                        str_mark = str_mark.replaceAll("-", ".5");
                    }

                    float res_got_mark = Float.parseFloat(str_mark);
                    sub_marks.add(res_got_mark);
                    sub_weight.add(int_weight);

                    System.out.println(res_got_mark + " (" + int_weight + ")");
                }
                marks_list.add(sub_marks);
                weight_list.add(sub_weight);

            }

            System.out.println(subjects_list);
            System.out.println(marks_list);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
