package de.suitepad.menu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.suitepad.menu.R;
import de.suitepad.menu.adapters.FragmentsPagerAdapter;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class AllFragment extends Fragment {
    private View rootView;
    private Fragment[] fragments;
    private ViewPager viewPager;
    private FragmentsPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        fragments = new Fragment[3];
        fragments[0] = MenuItemsFragment.newInstance(1);
        fragments[1] = MenuItemsFragment.newInstance(2);
        fragments[2] = MenuItemsFragment.newInstance(3);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        pagerAdapter =
                new FragmentsPagerAdapter(getChildFragmentManager(), getActivity(), fragments);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
    }
}
