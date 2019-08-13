package com.gedgonz.platzikgram.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.gedgonz.platzikgram.R;
import com.gedgonz.platzikgram.post.view.HomeFragment;
import com.gedgonz.platzikgram.view.fragment.ProfileFragment;
import com.gedgonz.platzikgram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.home);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId)
                {
                    case R.id.home:
                        SwitchFragment(new HomeFragment());
                        break;
                    case R.id.profile:
                        SwitchFragment( new ProfileFragment());
                        break;
                    case R.id.search:
                        SwitchFragment(new SearchFragment());
                        break;


                }
            }
        });
    }

    public void SwitchFragment(Fragment fragment)
    {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();

    }
}
