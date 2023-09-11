package com.example.japanesequizappversion2.Adapter;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.japanesequizappversion2.Dekiru.kenji.KenjiAllCardFragment;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiMemorizedCardFragment;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiNotMemorizedFragment;

public class DekiruKanjiViewPagerAdapter extends FragmentStateAdapter {

    private Fragment[] mFragments = new Fragment[] {
            new KenjiNotMemorizedFragment(),
            new KenjiAllCardFragment(),
            new KenjiMemorizedCardFragment()
    };

    public DekiruKanjiViewPagerAdapter(Fragment fragment) {
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
