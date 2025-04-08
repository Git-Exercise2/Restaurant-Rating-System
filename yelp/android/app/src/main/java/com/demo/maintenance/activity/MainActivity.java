package com.demo.maintenance.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.demo.maintenance.R;
import com.demo.maintenance.fragment.BaseFragment;
import com.demo.maintenance.fragment.HomeFragment;
import com.demo.maintenance.fragment.MyFragment;
import com.demo.maintenance.utils.ActivityManager;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * 主页
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;
    private MyFragment myFragment;
    private HomeFragment homeFragment;
    private BaseFragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_main);

        initBottomNavigationBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initBottomNavigationBar() {
        /*1.首先进行fvb*/
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_nav_bar);
        /*2.进行必要的设置*/
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);//适应大小
        /*3.添加Tab*/
        BottomNavigationItem item1 = new BottomNavigationItem(R.mipmap.icon_home_tab_1, R.string.bottom_nav_1)
                .setInactiveIconResource(R.mipmap.icon_home_tab_1_1)
                .setInActiveColor(R.color.color_1296db)
                .setActiveColorResource(R.color.color_333333);

        BottomNavigationItem item3 = new BottomNavigationItem(
                R.mipmap.icon_home_tab_4, R.string.bottom_nav_2)
                .setInactiveIconResource(R.mipmap.icon_home_tab_4_1)
                .setInActiveColor(R.color.color_1296db)
                .setActiveColorResource(R.color.color_333333);


        bottomNavigationBar.addItem(item1)
                .addItem(item3)
                .setFirstSelectedPosition(0)
                .initialise();//初始化

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        currentFragment = homeFragment;
        transaction.replace(R.id.fl_container, homeFragment).commit();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        switchContent(homeFragment);
                        break;
                    case 1:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                        }
                        switchContent(myFragment);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    public void switchContent(BaseFragment to) {
        if (currentFragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                transaction.hide(currentFragment).add(R.id.fl_container, to).commit();
            } else {
                transaction.hide(currentFragment).show(to).commit();
            }
            currentFragment = to;
        }
    }


    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentFragment != null){
                currentFragment.onBackPressed();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        ActivityManager.activityBackPressed(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
