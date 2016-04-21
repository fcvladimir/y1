package com.example.yalantis.y1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.ImageRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRvTaskPhoto;
    private List<String> mPhotoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

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
        mRvTaskPhoto = (RecyclerView)findViewById(R.id.rvTaskPhoto);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTaskPhoto.setLayoutManager(llm);
    }

    /**
     * Get list of photo url.
     */
    private void fillPhotoUrl() {
        mPhotoList = new ArrayList<>();
        mPhotoList.add("http://dl1.joxi.net/drive/0005/1477/374213/160320/0a317e11a7.jpg");
        mPhotoList.add("http://dl1.joxi.net/drive/0005/1477/374213/160320/97311de5f6.jpg");
    }

    /**
     * Initialize view listeners in current activity.
     */
    private void initListeners() {
        ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(mPhotoList);
        mRvTaskPhoto.setAdapter(adapter);

        findViewById(R.id.tvTaskTitle).setOnClickListener(this);
        findViewById(R.id.tvTaskStatus).setOnClickListener(this);
        findViewById(R.id.tvTaskCreatedOnText).setOnClickListener(this);
        findViewById(R.id.tvTaskCreatedOnValue).setOnClickListener(this);
        findViewById(R.id.tvTaskRegisteredOnText).setOnClickListener(this);
        findViewById(R.id.tvTaskRegisteredOnValue).setOnClickListener(this);
        findViewById(R.id.tvTaskSolveByText).setOnClickListener(this);
        findViewById(R.id.tvTaskSolveByValue).setOnClickListener(this);
        findViewById(R.id.tvAssignedText).setOnClickListener(this);
        findViewById(R.id.tvAssignedValue).setOnClickListener(this);
        findViewById(R.id.tvTaskDescription).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // print view control name. if i understand it correctly
        Toast.makeText(this, String.valueOf(v.getId())
                + "\n" // or
                + v.getResources().getResourceEntryName(v.getId()), Toast.LENGTH_SHORT).show();
    }
}
