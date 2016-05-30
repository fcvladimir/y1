package com.example.yalantis.y1.ui.activity.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.ui.adapter.ImageRecyclerAdapter;
import com.example.yalantis.y1.model.task.TaskModel;
import com.example.yalantis.y1.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskActivity extends AppCompatActivity implements TaskContract, View.OnClickListener {

    @BindView(R.id.rvTaskPhoto)
    RecyclerView mRvTaskPhoto;
    @BindView(R.id.tvTaskTitle)
    TextView mTvTaskTitle;
    @BindView(R.id.tvTaskStatus)
    TextView mTvTaskStatus;
    @BindView(R.id.tvTaskCreatedOnText)
    TextView mTvTaskCreatedOnText;
    @BindView(R.id.tvTaskCreatedOnValue)
    TextView mTvTaskCreatedOnValue;
    @BindView(R.id.tvTaskRegisteredOnText)
    TextView mTvTaskRegisteredOnText;
    @BindView(R.id.tvTaskRegisteredOnValue)
    TextView mTvTaskRegisteredOnValue;
    @BindView(R.id.tvTaskSolveByText)
    TextView mTvTaskSolveByText;
    @BindView(R.id.tvTaskSolveByValue)
    TextView mTvTaskSolveByValue;
    @BindView(R.id.tvAssignedText)
    TextView mTvAssignedText;
    @BindView(R.id.tvAssignedValue)
    TextView mTvAssignedValue;
    @BindView(R.id.tvTaskDescription)
    TextView mTvTaskDescription;

    private TaskPresenterImpl mTaskPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        showBackButton();
        initViews();
        initListeners();
        initPresenter();
        loadTaskById();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTaskPresenter.onDestroy();
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
    @Override
    public void initViews() {
        ButterKnife.bind(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTaskPhoto.setLayoutManager(llm);
    }

    @Override
    public void initPresenter() {
        mTaskPresenter = new TaskPresenterImpl(this);
    }

    @Override
    public void loadTaskById() {
        mTaskPresenter.loadTaskById(getIntent().getExtras());
    }

    /**
     * Get list of photo url.
     */
    @Override
    public void fillTaskDetailsById(TaskModel taskModel) {
        ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(taskModel);
        mRvTaskPhoto.setAdapter(adapter);
        mTvTaskTitle.setText(taskModel.getTitle());
        mTvTaskStatus.setText(taskModel.getState().getName());
        mTvTaskCreatedOnValue.setText(DateUtils.getDateFromMillis(this, taskModel.getCreated_date()));
        mTvTaskRegisteredOnValue.setText(DateUtils.getDateFromMillis(this, taskModel.getStart_date()));
        mTvTaskSolveByValue.setText(DateUtils.getDateFromMillis(this, taskModel.getDeadline()));
        if (taskModel.getPerformers().size() > 0) {
            mTvAssignedValue.setText(taskModel.getPerformers().get(0).getOrganization());
        }
        mTvTaskDescription.setText(taskModel.getBody());
        setTitle(taskModel.getTicket_id());
    }

    /**
     * Initialize view listeners in current activity.
     */
    @Override
    public void initListeners() {
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
