package com.ucu.ecourse.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ucu.ecourse.R;
import com.ucu.ecourse.adapter.FragmentAdapter;
import com.ucu.ecourse.fragment.CardsFragment;
import com.ucu.ecourse.fragment.DialogsFragment;
import com.ucu.ecourse.fragment.WidgetsFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    private FloatingActionButton fab;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private RelativeLayout relative_main;
    private ImageView img_page_start;

    private static boolean isShowPageStart = true;
    private final int MESSAGE_SHOW_DRAWER_LAYOUT = 0x001;
    private final int MESSAGE_SHOW_START_PAGE = 0x002;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initViewPager();




        SharedPreferences sharedPreferences = getSharedPreferences("app", MODE_PRIVATE);



    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);



        relative_main = findViewById(R.id.relative_main);
        img_page_start = findViewById(R.id.img_page_start);
    }

    private void initViewPager() {
        mTabLayout = findViewById(R.id.tab_layout_main);
        mViewPager = findViewById(R.id.view_pager_main);

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.tab_title_main_1));
        titles.add(getString(R.string.tab_title_main_2));
        titles.add(getString(R.string.tab_title_main_3));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CardsFragment());
        fragments.add(new DialogsFragment());
        fragments.add(new WidgetsFragment());

        mViewPager.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_main_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {
            case R.id.nav_recycler_and_swipe_refresh:
                intent.setClass(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;


            case R.id.nav_about:
                intent.setClass(this, AboutActivity.class);
                startActivity(intent);
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
