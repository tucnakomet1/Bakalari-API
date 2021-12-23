package com.company.absence;

import com.company.core.PostRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Absence {
    public static Absence getAbsence;
    public static String school_url, accessToken, json;
    public static double average_absence;
    public static ArrayList<String> subjects_list = new ArrayList<>();
    public static ArrayList<Integer> lessons_list = new ArrayList<>();
    public static ArrayList<Integer> late_list = new ArrayList<>();
    public static ArrayList<Integer> absence_list = new ArrayList<>();
    public static ArrayList<Integer> school_events_list = new ArrayList<>();

    public void getInstance() {
        if (getAbsence == null) {
            getAbsence = new Absence(school_url, accessToken);
        }
    }

    public Absence(String school_url, String accessToken) {
        try {
            if (school_url != null) {
                URL marks_url = new URL(school_url + "api/3/absence/student");
                String got = PostRequest.get_request(marks_url, null, accessToken, false);
                JSONObject obj = new JSONObject(got);

                json = got;
                average_absence = obj.getDouble("PercentageThreshold");

                JSONArray absences = obj.getJSONArray("AbsencesPerSubject");
                for (int i = 0; i < absences.length(); i++) {
                    JSONObject abs = absences.getJSONObject(i);

                    subjects_list.add(abs.getString("SubjectName"));
                    lessons_list.add(abs.getInt("LessonsCount"));
                    late_list.add(abs.getInt("Late"));
                    absence_list.add(abs.getInt("Base"));
                    school_events_list.add(abs.getInt("School"));
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String get_json() {
        return json;
    }
    public static Double get_average_absence() {return average_absence;}
    public static ArrayList<String> get_subjects(){
        return subjects_list;
    }
    public static ArrayList<Integer> get_lessons() {
        return lessons_list;
    }
    public static ArrayList<Integer> get_late() {
        return late_list;
    }
    public static ArrayList<Integer> get_absence() {
        return absence_list;
    }
    public static ArrayList<Integer> get_school_events() {
        return school_events_list;
    }
    public static ArrayList<Float> get_percent(boolean school_events) {
        ArrayList<Float> perc = new ArrayList<>();
        for (int i = 0; i < subjects_list.size(); i++) {
            float proc;
            if (school_events) {
                proc = (float) school_events_list.get(i) / lessons_list.get(i);
            } else {
                proc = (float) absence_list.get(i) / lessons_list.get(i);
            }
            perc.add((float) (Math.round((proc*100)* 100d) / 100d));
        }
        return perc;
    }
}
