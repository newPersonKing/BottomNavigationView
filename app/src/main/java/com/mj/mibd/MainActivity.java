package com.mj.mibd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import adapter.ViewPagerAdapter;
import dialog.CenterDialog;
import home1.NetWorkDiskFragment;
import home2.ShareFragment;
import home3.LifeFragment;
import home4.MoreFragment;
import until.BottomNavigationViewHelper;
import view.MyPopupwindow;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private MenuItem menuItem;

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getItemId());
                bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                return false;
            }
        });

        setupViewPager();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new NetWorkDiskFragment());
        adapter.addFragment(new ShareFragment());
        adapter.addFragment(new LifeFragment());
        adapter.addFragment(new MoreFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomSwitchState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    private void changeFragment(int itemId){


        switch (itemId){
            case R.id.item_network_disk:
                viewPager.setCurrentItem(0);
                menuItem=bottomNavigationView.getMenu().getItem(0);
                break;
            case R.id.item_share:
                viewPager.setCurrentItem(1);
                menuItem=bottomNavigationView.getMenu().getItem(0);
                break;
            case R.id.item_life:
                viewPager.setCurrentItem(2);
                menuItem=bottomNavigationView.getMenu().getItem(0);
                break;
            case R.id.item_more:
                viewPager.setCurrentItem(3);
                menuItem=bottomNavigationView.getMenu().getItem(0);
                break;
        }

    }

    private void bottomSwitchState(int i){
        if (menuItem!=null){
            menuItem.setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(i).setChecked(true);
    }


}
