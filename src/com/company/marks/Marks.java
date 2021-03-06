package com.company.marks;

import com.company.core.PostRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Marks {
    public static Marks getMarks;
    public static String school_url, accessToken, json;
    public static ArrayList<String> subjects_list = new ArrayList<>();
    public static ArrayList<String> abb_subjects_list = new ArrayList<>();
    public static ArrayList<Boolean> points_list = new ArrayList<>();
    public static ArrayList<ArrayList<String>> mark_date_list = new ArrayList<>();
    public static ArrayList<ArrayList<String>> edit_date_list = new ArrayList<>();
    public static ArrayList<ArrayList<String>> caption_list = new ArrayList<>();
    public static ArrayList<ArrayList<Float>> marks_list = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> weight_list = new ArrayList<>();

    public void getInstance() {
        if (getMarks == null) {
            getMarks = new Marks(school_url, accessToken);
        }
    }

    public Marks(String school_url, String accessToken) {
        try {
            if (school_url != null) {
                URL marks_url = new URL(school_url + "api/3/marks");
                String got = PostRequest.get_request(marks_url, null, accessToken, false);
                JSONObject obj = new JSONObject(got);

                json = got;

                // scraping JSON
                JSONArray subjects = obj.getJSONArray("Subjects");
                for (int i = 0; i < subjects.length(); i++) {
                    ArrayList<Float> sub_marks = new ArrayList<>();
                    ArrayList<Integer> sub_weight = new ArrayList<>();
                    ArrayList<String> sub_mDate = new ArrayList<>();
                    ArrayList<String> sub_eDate = new ArrayList<>();
                    ArrayList<String> sub_cap = new ArrayList<>();

                    JSONObject subject = subjects.getJSONObject(i);
                    JSONArray mark_subject = subject.getJSONArray("Marks");
                    boolean is_points = mark_subject.getJSONObject(0).getBoolean("IsPoints");

                    subjects_list.add(subject.getJSONObject("Subject").getString("Name"));
                    abb_subjects_list.add((subject.getJSONObject("Subject").getString("Abbrev")).trim());
                    points_list.add(is_points);

                    for (int j = 0; j < mark_subject.length(); j++) {
                        JSONObject mark = mark_subject.getJSONObject(j);
                        String str_mark = mark.getString("MarkText");

                        int int_weight;
                        if (is_points) {
                            int_weight = mark.getInt("MaxPoints");
                        } else {
                            int_weight = mark.getInt("Weight");
                        }

                        if (str_mark.contains("-")) {
                            str_mark = str_mark.replaceAll("-", ".5");
                        }
                        float res_got_mark = Float.parseFloat(str_mark);

                        sub_marks.add(res_got_mark);
                        sub_weight.add(int_weight);
                        sub_mDate.add(mark.getString("MarkDate"));
                        sub_eDate.add(mark.getString("EditDate"));
                        sub_cap.add(mark.getString("Caption"));
                    }
                    marks_list.add(sub_marks);
                    weight_list.add(sub_weight);
                    mark_date_list.add(sub_mDate);
                    edit_date_list.add(sub_eDate);
                    caption_list.add(sub_cap);
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    // functions
    public static String get_json() {
        return json;
    }
    public static ArrayList<ArrayList<String>> get_mark_date(){
        return mark_date_list;
    }
    public static ArrayList<ArrayList<String>> get_edit_date(){
        return edit_date_list;
    }
    public static ArrayList<ArrayList<String>> get_caption(){
        return caption_list;
    }
    public static ArrayList<ArrayList<Float>> get_marks(){
        return marks_list;
    }
    public static ArrayList<ArrayList<Integer>> get_weight(){
        return weight_list;
    }
    public static ArrayList<Boolean> get_points(){
        return points_list;
    }
    public static ArrayList<String> get_subject(boolean whole){
        if (whole) {
            return subjects_list;
        } else {
            return abb_subjects_list;
        }
    }
    public static ArrayList<Float> get_average(){
        return Average.getAverage(subjects_list, points_list, marks_list, weight_list);
    }
}
