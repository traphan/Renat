package com.example.ren95.crimeactivity;

import android.support.v4.app.Fragment;

/**
 * Created by ren95 on 07.08.2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
