package cn.lxw.simpleviewpagerindicator;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_chat;
    private TextView tv_contact;
    private TextView tv_space;
    private ViewPager vp;
    private List<Fragment> mDatas;
    private int mCurrentPageIndex;
    private ImageView iv_line;
    private int width1_3;//  三分之一的屏幕宽度
    private LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDisPlay();
        init();
    }

    private void initDisPlay() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width1_3 = metrics.widthPixels / 3;
    }

    private void init() {

        //----------------initView------------------
        tv_chat = (TextView) findViewById(R.id.tv_chat);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_space = (TextView) findViewById(R.id.tv_space);
        vp = (ViewPager) findViewById(R.id.vp);
        iv_line = (ImageView) findViewById(R.id.iv_line);
        lp = (LinearLayout.LayoutParams) iv_line.getLayoutParams();
        lp.width = width1_3;
        Toast.makeText(this, "" + width1_3, Toast.LENGTH_LONG).show();
        iv_line.setLayoutParams(lp);

        //----------------initFragment------------------
        ChatFragment fragment_1 = new ChatFragment();
        ContactFragment fragment_2 = new ContactFragment();
        SpaceFragment fragment_3 = new SpaceFragment();
        mDatas = new ArrayList<>();
        mDatas.add(fragment_1);
        mDatas.add(fragment_2);
        mDatas.add(fragment_3);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("ViewPager:", position + "---" + positionOffset + "---" + positionOffsetPixels);

                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_line.getLayoutParams();


                if (position == 0 && mCurrentPageIndex == 0) {//0--->1
                    lp.leftMargin = (int) (mCurrentPageIndex * width1_3 + width1_3 * positionOffset);
                } else if (position == 1 && mCurrentPageIndex == 1) {//1--->2
                    lp.leftMargin = (int) (mCurrentPageIndex * width1_3 + width1_3 * positionOffset);
                } else if (position == 1 && mCurrentPageIndex == 2) {//2--->1
                    lp.leftMargin = (int) (mCurrentPageIndex * width1_3 + width1_3 * (positionOffset - 1));
                } else if (position == 0 && mCurrentPageIndex == 1) {//1--->0
                    lp.leftMargin = (int) (mCurrentPageIndex * width1_3 + width1_3 * (positionOffset - 1));
                }
                iv_line.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
//                切换完毕之后,改变字体的颜色.
                resetTextColor();
                switch (position) {
                    case 0:
                        tv_chat.setTextColor(Color.parseColor("#33ccff"));
                        break;
                    case 1:
                        tv_contact.setTextColor(Color.parseColor("#33ccff"));
                        break;
                    case 2:
                        tv_space.setTextColor(Color.parseColor("#33ccff"));
                        break;
                }

                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void resetTextColor() {
        tv_chat.setTextColor(Color.BLACK);
        tv_contact.setTextColor(Color.BLACK);
        tv_space.setTextColor(Color.BLACK);
    }


    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }
    }
}
