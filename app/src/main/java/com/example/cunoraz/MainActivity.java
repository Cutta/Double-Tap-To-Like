package com.example.cunoraz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.cunoraz.like.R;

public class MainActivity extends Activity {

    ViewPager viewPager;
    PagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);
        int[] myImageList = new int[]{R.drawable.cat, R.drawable.alpaka,R.drawable.puffin,R.drawable.merkit,R.drawable.otter};

        Toast.makeText(MainActivity.this,"Duble top to like",Toast.LENGTH_LONG).show();
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(MainActivity.this,myImageList);
        viewPager.setAdapter(adapter);

    }
}