package by.wink.jsonparsesample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.models.Student;
import by.wink.jsonparsesample.adapters.StudentsAdapter;

public class StudentsActivity extends AppCompatActivity {
    RecyclerView studentsRv;
    LinearLayoutManager layoutManager;
    StudentsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        studentsRv = (RecyclerView) findViewById(R.id.students_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new  StudentsAdapter();
        studentsRv.setLayoutManager(layoutManager);
        studentsRv.setAdapter(adapter);
        fetchStudentsFromJSON();
    }

    private void fetchStudentsFromJSON() {
        ArrayList<Student> students = new ArrayList<>();
        try {


            JSONArray studentsJsonArray = new JSONArray(readLocalJson());
            for (int i = 0; i < studentsJsonArray.length(); i++) {
                JSONObject jsonStudent = studentsJsonArray.getJSONObject(i);
                students.add(new Student(jsonStudent));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // add dataset to adapter
        adapter.setDataSet(students);

    }


    private String readLocalJson() {

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.students)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
