package com.example.yalantis.y1.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.TaskStatusPagerAdapter;
import com.example.yalantis.y1.interfaces.IShowedFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract {

    @BindView(R.id.tbMain)
    Toolbar mToolbar;
    @BindView(R.id.tlTaskStatus)
    TabLayout mTlTaskStatus;
    @BindView(R.id.vpTaskStatus)
    ViewPager mVpTaskStatus;

    private TaskStatusPagerAdapter mAdapter;
    private MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initDrawer();

        initViews();

        initPresenter();

        loadSectionsTabs();

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
    @Override
    public void initViews() {
        ButterKnife.bind(this);
//        mTlTaskStatus = (TabLayout) findViewById(R.id.tlTaskStatus);
//        mVpTaskStatus = (ViewPager) findViewById(R.id.vpTaskStatus);
    }

    @Override
    public void initPresenter() {
        mMainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void loadSectionsTabs() {
        mMainPresenter.loadSectionsTabs();
    }

    /**
     * Initialize pager adapter in current activity.
     */
    @Override
    public void initPagerAdapter(List<String> listTitleTabs) {
        for (int i=0; i<listTitleTabs.size(); i++) {
            mTlTaskStatus.addTab(mTlTaskStatus.newTab().setText(listTitleTabs.get(i)));
        }
        mTlTaskStatus.setTabGravity(TabLayout.GRAVITY_FILL);
        mAdapter = new TaskStatusPagerAdapter
                (getSupportFragmentManager(), mTlTaskStatus.getTabCount());

        mVpTaskStatus.setAdapter(mAdapter);
    }

    /**
     * Initialize view listeners in current activity.
     */
    @Override
    public void initListeners() {
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
        mVpTaskStatus.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = (Fragment) mAdapter.instantiateItem(mVpTaskStatus, position);
                if (fragment instanceof IShowedFragment) {
                    ((IShowedFragment) fragment).onShowedFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
