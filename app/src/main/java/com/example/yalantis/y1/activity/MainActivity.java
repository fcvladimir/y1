package com.example.yalantis.y1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.TaskStatusPagerAdapter;

//[Comment] Wrong status bar color
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TabLayout mTlTaskStatus;
    private ViewPager mVpTaskStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initDrawer();

        initViews();

        initPagerAdapter();

        initListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Initialize Toolbar.
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tbMain);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        setTitle(R.string.all_notices);
    }

    /**
     * Initialize drawer in current activity.
     */
    @SuppressWarnings("deprecation")
    private void initDrawer() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawerLayout != null) {
            drawerLayout.setDrawerListener(toggle);
        }
        toggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Initialize views in current activity.
     */
    private void initViews() {
        mTlTaskStatus = (TabLayout) findViewById(R.id.tlTaskStatus);
        mVpTaskStatus = (ViewPager) findViewById(R.id.vpTaskStatus);
    }

    /**
     * Initialize pager adapter in current activity.
     */
    private void initPagerAdapter() {
        mTlTaskStatus.addTab(mTlTaskStatus.newTab().setText(getString(R.string.tab_status_work)));
        mTlTaskStatus.addTab(mTlTaskStatus.newTab().setText(getString(R.string.tab_status_done)));
        mTlTaskStatus.addTab(mTlTaskStatus.newTab().setText(getString(R.string.tab_status_pending)));
        mTlTaskStatus.setTabGravity(TabLayout.GRAVITY_FILL);
        TaskStatusPagerAdapter adapter = new TaskStatusPagerAdapter
                (getSupportFragmentManager(), mTlTaskStatus.getTabCount());

        mVpTaskStatus.setAdapter(adapter);
    }

    /**
     * Initialize view listeners in current activity.
     */
    private void initListeners() {
        mVpTaskStatus.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTlTaskStatus));
        mTlTaskStatus.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpTaskStatus.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
