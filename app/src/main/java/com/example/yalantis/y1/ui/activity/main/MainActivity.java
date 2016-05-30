package com.example.yalantis.y1.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.Consts;
import com.example.yalantis.y1.R;
import com.example.yalantis.y1.interfaces.IShowedFragment;
import com.example.yalantis.y1.model.ProfileModel;
import com.example.yalantis.y1.ui.activity.profile.ProfileActivity;
import com.example.yalantis.y1.ui.adapter.TaskStatusPagerAdapter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract, NavigationView.OnNavigationItemSelectedListener, FacebookCallback<LoginResult> {

    @BindView(R.id.tbMain)
    Toolbar mToolbar;
    @BindView(R.id.tlTaskStatus)
    TabLayout mTlTaskStatus;
    @BindView(R.id.vpTaskStatus)
    ViewPager mVpTaskStatus;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nvMenu)
    NavigationView mNavMenu;

    private TaskStatusPagerAdapter mAdapter;
    private MainPresenterImpl mMainPresenter;

    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        initToolbar();
        initViews();
        initDrawer();
        initPresenter();
        loadSectionsTabs();
        initListeners();
    }

    @Override
    public void onDestroy() {
        mMainPresenter.onDestroy();
        super.onDestroy();
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (mDrawer != null) {
            mDrawer.setDrawerListener(toggle);
        }
        toggle.syncState();
        mNavMenu.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signin:
                loginWithFacebook();
                break;
        }
        return true;
    }

    private void loginWithFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, this);
        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList(Consts.FACEBOOK_PROFILE));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        final String token = loginResult.getAccessToken().getToken();
        Bundle params = new Bundle();
        params.putString(GraphRequest.FIELDS_PARAM, Consts.FACEBOOK_VALUE);
        params.putString("locale", Consts.FACEBOOK_LOCALE);
        params.putString("language", Consts.FACEBOOK_LANGUAGE);
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                ProfileModel profileModel = new ProfileModel();
                profileModel.setToken(token);
                try {
                    JSONObject jsonObject = new JSONObject(object.toString());
                    if (jsonObject.has("id")) {
                        profileModel.setId(jsonObject.getInt("id"));
                    }
                    if (jsonObject.has("name")) {
                        profileModel.setName(jsonObject.getString("name"));
                    }
                    if (jsonObject.has("picture")) {
                        profileModel.setPicture(jsonObject.getString("picture"));
                    }
                    App.contentManager.saveProfileToDb(profileModel);
                    startProfile();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        request.setParameters(params);
        request.executeAsync();
    }

    private void startProfile() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
}
