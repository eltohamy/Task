package de.suitepad.menu.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.suitepad.menu.R;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    private static final int[] iconsResId = {R.drawable.ic_appetizer, R.drawable.ic_main_course, R.drawable.ic_drink};
    private String typesStringArray[];
    private Context context;
    private Fragment[] fragments;

    public FragmentsPagerAdapter(FragmentManager fm, Context context, Fragment[] fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        typesStringArray = context.getResources().getStringArray(R.array.types);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typesStringArray[position];
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tabIcon);
        tabIcon.setImageResource(iconsResId[position]);
        TextView tabTitle = (TextView) view.findViewById(R.id.tabTitle);
        tabTitle.setText(typesStringArray[position]);
        return view;
    }
}
