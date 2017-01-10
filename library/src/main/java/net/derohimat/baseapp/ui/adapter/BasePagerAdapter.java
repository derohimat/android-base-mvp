package net.derohimat.baseapp.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.derohimat.baseapp.ui.fragment.BaseFragment;

import java.util.List;

import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BasePagerAdapter<Fragment extends BaseFragment> extends
        FragmentStatePagerAdapter {
    protected List<Fragment> mFragments;
    protected List<String> mTitles;

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
        Timber.tag(getClass().getSimpleName());
    }

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public abstract Fragment getItem(int position);

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.mFragments = fragments;
    }

    public List<String> getTitles() {
        return mTitles;
    }

    public void setTitles(List<String> titles) {
        this.mTitles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.size() == mFragments.size() ? mTitles.get(position) : super.getPageTitle(position);
    }
}
