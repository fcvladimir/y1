package com.example.yalantis.y1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.ImageRecyclerAdapter;
import com.example.yalantis.y1.model.Photo;
import com.example.yalantis.y1.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

//[Comment] Wrong status bar color
//[Comment] Images should be bigger
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvTaskPhoto;
    private List<Photo> persons;
    private TextView tvTaskTitle;
    private TextView tvTaskStatus;
    private TextView tvTaskCreatedOnText;
    private TextView tvTaskCreatedOnValue;
    private TextView tvTaskRegisteredOnText;
    private TextView tvTaskRegisteredOnValue;
    private TextView tvTaskSolveByText;
    private TextView tvTaskSolveByValue;
    private TextView tvAssignedText;
    private TextView tvAssignedValue;
    private TextView tvTaskDescription; //[Comment] Please use google code style

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBackButton();

        initViews();

        fillPhotoUrl();

        initListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Initialize Back button in actionBar.
     */
    public void showBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * Initialize views in current activity.
     */
    private void initViews() {
        rvTaskPhoto = (RecyclerView)findViewById(R.id.rvTaskPhoto);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTaskPhoto.setLayoutManager(llm);

        tvTaskTitle = (TextView) findViewById(R.id.tvTaskTitle);
        tvTaskStatus = (TextView) findViewById(R.id.tvTaskStatus);
        tvTaskCreatedOnText = (TextView) findViewById(R.id.tvTaskCreatedOnText);
        tvTaskCreatedOnValue = (TextView )findViewById(R.id.tvTaskCreatedOnValue);
        tvTaskRegisteredOnText = (TextView) findViewById(R.id.tvTaskRegisteredOnText);
        tvTaskRegisteredOnValue = (TextView) findViewById(R.id.tvTaskRegisteredOnValue);
        tvTaskSolveByText = (TextView) findViewById(R.id.tvTaskSolveByText);
        tvTaskSolveByValue = (TextView) findViewById(R.id.tvTaskSolveByValue);
        tvAssignedText = (TextView) findViewById(R.id.tvAssignedText);
        tvAssignedValue = (TextView) findViewById(R.id.tvAssignedValue);
        tvTaskDescription = (TextView) findViewById(R.id.tvTaskDescription); //[Comment] findViewById(R.id.tvTaskDescription).setOnClickListener(this);
    }

    /**
     * Get list of photo url.
     */
    private void fillPhotoUrl() {
        persons = new ArrayList<>();
        Photo p = new Photo();
        p.setImageUrl("http://dl1.joxi.net/drive/0005/1477/374213/160320/0a317e11a7.jpg");
        persons.add(p);
        p = new Photo();
        p.setImageUrl("http://dl1.joxi.net/drive/0005/1477/374213/160320/97311de5f6.jpg");
        persons.add(p);
    }

    /**
     * Initialize view listeners in current activity.
     */
    private void initListeners() {
        ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(persons);
        rvTaskPhoto.setAdapter(adapter);

        tvTaskTitle.setOnClickListener(this);
        tvTaskStatus.setOnClickListener(this);
        tvTaskCreatedOnText.setOnClickListener(this);
        tvTaskCreatedOnValue.setOnClickListener(this);
        tvTaskRegisteredOnText.setOnClickListener(this);
        tvTaskRegisteredOnValue.setOnClickListener(this);
        tvTaskSolveByText.setOnClickListener(this);
        tvTaskSolveByValue.setOnClickListener(this);
        tvAssignedText.setOnClickListener(this);
        tvAssignedValue.setOnClickListener(this);
        tvTaskDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String textToShow = "";
        switch (v.getId()) {
            case R.id.tvTaskTitle:
                textToShow = tvTaskTitle.getText().toString(); //[Comment] Wrong information. You should show control name
                break;
            case R.id.tvTaskStatus:
                textToShow = tvTaskStatus.getText().toString();
                break;
            case R.id.tvTaskCreatedOnText:
                textToShow = tvTaskCreatedOnText.getText().toString();
                break;
            case R.id.tvTaskCreatedOnValue:
                textToShow = tvTaskCreatedOnValue.getText().toString();
                break;
            case R.id.tvTaskRegisteredOnText:
                textToShow = tvTaskRegisteredOnText.getText().toString();
                break;
            case R.id.tvTaskRegisteredOnValue:
                textToShow = tvTaskRegisteredOnValue.getText().toString();
                break;
            case R.id.tvTaskSolveByText:
                textToShow = tvTaskSolveByText.getText().toString();
                break;
            case R.id.tvTaskSolveByValue:
                textToShow = tvTaskSolveByValue.getText().toString();
                break;
            case R.id.tvAssignedText:
                textToShow = tvAssignedText.getText().toString();
                break;
            case R.id.tvAssignedValue:
                textToShow = tvAssignedValue.getText().toString();
                break;
            case R.id.tvTaskDescription:
                textToShow = tvTaskDescription.getText().toString();
                break;
        }
        DialogUtil.show(this, textToShow);
    }
}
