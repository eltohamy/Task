package de.suitepad.menu.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.suitepad.menu.R;
import de.suitepad.menu.adapters.MenuItemsAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class MenuItemsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String AUTHORITY = "de.suitepad.support";

    public static final String CONTENT_URI_PATH = "menuitems";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

    private static final String selection = "type = ?";

    private static final String KEY_TYPE = "key_type";
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView emptyTextView;
    private RecyclerView recyclerView;
    private MenuItemsAdapter menuItemsAdapter;
    private AppBarLayout appBarLayout;
    private LinearLayoutManager linearLayoutManager;
    private int type;
    private String[] selectionArgs;

    public static MenuItemsFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        MenuItemsFragment fragment = new MenuItemsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu_items, container, false);
        type = getArguments().getInt(KEY_TYPE);
        selectionArgs = new String[]{String.valueOf(type)};
        initUI();
        return rootView;
    }

    private void initUI() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshLayoutID);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                emptyTextView.setVisibility(View.INVISIBLE);
                getLoaderManager().restartLoader(type, null, MenuItemsFragment.this);
            }
        });
        emptyTextView = (TextView) rootView.findViewById(R.id.empty_textview);

        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appBarLayout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new FlipInBottomXAnimator());
        menuItemsAdapter = new MenuItemsAdapter(getActivity());
        this.getLoaderManager().restartLoader(type, null, this);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(menuItemsAdapter));
        emptyTextView.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            mSwipeRefreshLayout.setEnabled(true);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 1:
                return new CursorLoader(getActivity(), CONTENT_URI, null, selection, selectionArgs, null);
            case 2:
                return new CursorLoader(getActivity(), CONTENT_URI, null, selection, selectionArgs, null);
            case 3:
                return new CursorLoader(getActivity(), CONTENT_URI, null, selection, selectionArgs, null);
        }
        return null;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null && data.getCount() > 0) {
            this.menuItemsAdapter.swapCursor(data);
        } else {
            emptyTextView.setText(R.string.no_items_found);
            emptyTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mSwipeRefreshLayout.setRefreshing(false);
        this.menuItemsAdapter.swapCursor(null);
    }

}
