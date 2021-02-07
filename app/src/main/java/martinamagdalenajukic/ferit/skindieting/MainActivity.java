package martinamagdalenajukic.ferit.skindieting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ChooseClickListener, RemoveAllListner, RemoveSwipeListener, NextClickListener, InsertClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private IntroductionFragment introductionFragment;
    private ChooseFragment chooseFragment;
    private GroceryFragment groceryFragment;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private martinamagdalenajukic.ferit.skindieting.PagerAdapter adapter;
    private static final String PREFERENCE_NAME="preference";
    private static final String PREFERENCES_KEY="firstStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        introductionFragment=new IntroductionFragment();
        chooseFragment=new ChooseFragment();
        GroceryDBHelper dbHelper=new GroceryDBHelper(this);
        groceryFragment=new GroceryFragment(dbHelper);
        fragmentManager=getSupportFragmentManager();
        SharedPreferences preferences=getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean firstStart=preferences.getBoolean(PREFERENCES_KEY, true);
        setUpViewPager(firstStart);
    }

    private void setUpViewPager(boolean firstStart) {
        fragmentList=new ArrayList<>();
        if (firstStart) {
            fragmentList.add(introductionFragment);
            SharedPreferences preferences=getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putBoolean(PREFERENCES_KEY, false);
            editor.apply();
        }
        fragmentList.add(chooseFragment);
        fragmentList.add(groceryFragment);
        adapter= new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (fragmentList.size()==3){
            tabLayout.getTabAt(0).setIcon(R.drawable.infoicon24);
            tabLayout.getTabAt(1).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(2).setIcon(R.drawable.listicon);
        }else {
            tabLayout.getTabAt(0).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(1).setIcon(R.drawable.listicon);
        }
    }

    @Override
    public void onChoose(int option) {
        int position=fragmentList.size()-2;
        fragmentList.remove(position);
        if (option==1){
          fragmentList.add(position, RecipeFragment.newInstance(new Random().nextInt(2-0+1)+0));
        }
        else if (option==2){
            fragmentList.add(position,  RecipeFragment.newInstance(new Random().nextInt(5-3+1)+3));
        }
        else if (option==3){
            fragmentList.add(position, RecipeFragment.newInstance(new Random().nextInt(8-6+1)+6));
        }
        adapter=new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position);
       if (fragmentList.size()==3){
            tabLayout.getTabAt(0).setIcon(R.drawable.infoicon24);
            tabLayout.getTabAt(1).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(2).setIcon(R.drawable.listicon);
        }else {
            tabLayout.getTabAt(0).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(1).setIcon(R.drawable.listicon);
        }

    }

    @Override
    public void onRemoveAll() {
            groceryFragment.removeAll();
    }

    @Override
    public void onNextListener(int id) {
        int position=fragmentList.size()-2;
        fragmentList.remove(position);
        fragmentList.add(position, RecipeFragment.newInstance(id));
        adapter=new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position);
        if (fragmentList.size()==3){
            tabLayout.getTabAt(0).setIcon(R.drawable.infoicon24);
            tabLayout.getTabAt(1).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(2).setIcon(R.drawable.listicon);
        }else {
            tabLayout.getTabAt(0).setIcon(R.drawable.foodicon);
            tabLayout.getTabAt(1).setIcon(R.drawable.listicon);
        }
    }

    @Override
    public void onInsertClick(String item) {
        groceryFragment.addNewItem(item);
    }

    @Override
    public void oneSwipeRemoved(long id) {
        groceryFragment.removeItem(id);
    }
}