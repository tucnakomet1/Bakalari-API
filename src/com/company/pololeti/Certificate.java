package com.company.pololeti;

import com.company.core.PostRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Certificate {
    public static Certificate pololeti;
    public static String school_url, accessToken, json;
    public static ArrayList<String> semester = new ArrayList<>();
    public static ArrayList<String> achievement_text = new ArrayList<>();
    public static ArrayList<String> school_year = new ArrayList<>();
    public static ArrayList<String> certificate_date = new ArrayList<>();
    public static ArrayList<Integer> not_excused_hours = new ArrayList<>();
    public static ArrayList<Integer> grade = new ArrayList<>();
    public static ArrayList<Integer> absent_hours = new ArrayList<>();
    public static ArrayList<Boolean> repeated = new ArrayList<>();
    public static ArrayList<Boolean> closed = new ArrayList<>();
    public static ArrayList<ArrayList<String>> subjects_list = new ArrayList<>();
    public static ArrayList<ArrayList<String>> abbrev_subjects_list = new ArrayList<>();
    public static ArrayList<ArrayList<String>> marks_list = new ArrayList<>();

    public void getInstance() {
        if (pololeti == null) {
            pololeti = new Certificate(school_url, accessToken);
        }
    }

    public Certificate(String school_url, String accessToken) {
        try {
            if (school_url != null) {
                URL gdpr_url = new URL(school_url + "api/3/marks/final");
                String got = PostRequest.get_request(gdpr_url, null, accessToken, false);
                JSONObject obj = new JSONObject(got);

                json = got;

                JSONArray certificate = obj.getJSONArray("CertificateTerms");
                for (int i = 0; i < certificate.length(); i++) {
                    ArrayList<String> subject = new ArrayList<>();
                    ArrayList<String> abbrev = new ArrayList<>();
                    ArrayList<String> mark = new ArrayList<>();

                    JSONObject cert = certificate.getJSONObject(i);

                    semester.add(cert.getString("Semester"));
                    school_year.add(cert.getString("SchoolYear"));
                    certificate_date.add(cert.getString("CertificateDate"));
                    achievement_text.add((cert.getString("AchievementText")).replaceAll("-", "").trim());
                    not_excused_hours.add((cert.getInt("NotExcusedHours")));
                    grade.add((cert.getInt("Grade")));
                    repeated.add((cert.getBoolean("Repeated")));
                    closed.add((cert.getBoolean("Closed")));
                    absent_hours.add((cert.getInt("AbsentHours")));

                    JSONArray subject_list = cert.getJSONArray("Subjects");
                    JSONArray mark_list = cert.getJSONArray("FinalMarks");

                    for (int j = 0; j < subject_list.length(); j++) {
                        JSONObject subs = subject_list.getJSONObject(j);
                        JSONObject marks = mark_list.getJSONObject(j);

                        subject.add(subs.getString("Name"));
                        abbrev.add(subs.getString("Abbrev"));
                        mark.add(marks.getString("MarkText"));
                    }
                    subjects_list.add(subject);
                    abbrev_subjects_list.add(abbrev);
                    marks_list.add(mark);
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
    public static ArrayList<String> get_semester(){
        return semester;
    }
    public static ArrayList<String> get_achievement(){
        return achievement_text;
    }
    public static ArrayList<String> get_year(){
        return school_year;
    }
    public static ArrayList<String> get_date(){
        return certificate_date;
    }
    public static ArrayList<Integer> get_not_excused(){
        return not_excused_hours;
    }
    public static ArrayList<Integer> get_grade(){
        return grade;
    }
    public static ArrayList<Integer> get_absent(){
        return absent_hours;
    }
    public static ArrayList<Boolean> get_repeated(){
        return repeated;
    }
    public static ArrayList<Boolean> get_closed(){
        return closed;
    }
    public static ArrayList<ArrayList<String>> get_subjects(Boolean shorten){
        if (shorten) {
            return abbrev_subjects_list;
        } else {
            return subjects_list;
        }
    }
    public static ArrayList<ArrayList<String>> get_marks(){
        return marks_list;
    }
}