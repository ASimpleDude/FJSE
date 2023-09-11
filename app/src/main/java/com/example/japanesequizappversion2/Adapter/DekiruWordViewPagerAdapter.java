package com.example.japanesequizappversion2.Adapter;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.japanesequizappversion2.Dekiru.word.kenji.WordAllCardFragment;
import com.example.japanesequizappversion2.Dekiru.word.kenji.WordMemorizedCardFragment;
import com.example.japanesequizappversion2.Dekiru.word.kenji.WordNotMemorizedFragment;

public class DekiruWordViewPagerAdapter extends FragmentStateAdapter {

    private Fragment[] mFragments = new Fragment[] {
            new WordNotMemorizedFragment(),
            new WordAllCardFragment(),
            new WordMemorizedCardFragment()
    };

    public DekiruWordViewPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
