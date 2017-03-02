package app.heuristy.dev.travelcanvas.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.controller.fragment.AddTravelFrag1;
import app.heuristy.dev.travelcanvas.controller.fragment.AddTravelFrag2;
import app.heuristy.dev.travelcanvas.controller.fragment.AddTravelFrag3;
import app.heuristy.dev.travelcanvas.view.NonSwipeableViewPager;
import app.heuristy.dev.travelcanvas.R;

public class AddTravelActivity extends AppCompatActivity {

    private NonSwipeableViewPager viewPager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        viewPager = (NonSwipeableViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(NonSwipeableViewPager viewPager){
        viewPager.setOffscreenPageLimit(10);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddTravelFrag1().setViewPager(viewPager), "First");
        adapter.addFragment(new AddTravelFrag2().setViewPager(viewPager), "Second");
        adapter.addFragment(new AddTravelFrag3().setViewPager(viewPager), "Third");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0)
                    radioGroup.check(R.id.radioBtn1);
                else if(position == 1)
                    radioGroup.check(R.id.radioBtn2);
                else
                    radioGroup.check(R.id.radioBtn3);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position){
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
