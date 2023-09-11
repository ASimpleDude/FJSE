package com.example.japanesequizappversion2.Dekiru.grama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.japanesequizappversion2.Adapter.DekiruGramaViewPagerAdapter;
import com.example.japanesequizappversion2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class GramaMainFragment extends Fragment {

    ViewPager2 vp;
    TabLayout tl;
    private ArrayList<String> listTabLayout = new ArrayList<>();
    private DekiruGramaViewPagerAdapter mDekiruGramaViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grama_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDekiruGramaViewPagerAdapter = new DekiruGramaViewPagerAdapter(this);
        vp = view.findViewById(R.id.viewPager);

        tl = view.findViewById(R.id.tabLayout);

        listTabLayout.add("Chưa nhớ");
        listTabLayout.add("Tất cả");
        listTabLayout.add("Đã nhớ");

        vp.setAdapter(mDekiruGramaViewPagerAdapter);
        vp.setCurrentItem(1);

        new TabLayoutMediator(tl, vp, (tab, position) -> {
            tab.setText(listTabLayout.get(position));
        }).attach();
    }
}