package com.example.japanesequizappversion2.Adapter;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.japanesequizappversion2.Dekiru.grama.GramaAllCardFragment;
import com.example.japanesequizappversion2.Dekiru.grama.GramaMemorizedCardFragment;
import com.example.japanesequizappversion2.Dekiru.grama.GramaNotMemorizedFragment;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiAllCardFragment;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiMemorizedCardFragment;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiNotMemorizedFragment;

public class DekiruGramaViewPagerAdapter extends FragmentStateAdapter {

    private Fragment[] mFragments = new Fragment[] {
            new GramaNotMemorizedFragment(),
            new GramaAllCardFragment(),
            new GramaMemorizedCardFragment()
    };

    public DekiruGramaViewPagerAdapter(Fragment fragment) {
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
