package by.wink.jsonparsesample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import by.wink.jsonparsesample.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.list_user_btn).setOnClickListener(this);
        findViewById(R.id.sample_api_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.sample_api_btn){
            startActivity(new Intent(this,SampleApiActivity.class));
        }
        if(id == R.id.list_user_btn){
            startActivity(new Intent(this,StudentsActivity.class));
        }

    }
}
