package net.derohimat.baseapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.derohimat.baseapp.ui.adapter.viewholder.BaseListViewHolder;

import java.util.ArrayList;
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
public abstract class BaseListAdapter<Data, Holder extends BaseListViewHolder> extends
        BaseAdapter {
    public static String PAGE = "BaseListAdapter.Page";
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<Data> mDatas;

    public BaseListAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
        Timber.tag(getClass().getSimpleName());
    }

    public BaseListAdapter(Context context, List<Data> data) {
        this.mContext = context;
        this.mDatas = data;
        this.mInflater = LayoutInflater.from(mContext);
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public int getCount() {
        try {
            return mDatas.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Data getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        Holder holder;
        if (itemView == null) {
            itemView = LayoutInflater.from(mContext).inflate(getItemView(), parent, false);
            holder = onCreateViewHolder(itemView);
            itemView.setTag(holder);
        } else {
            holder = (Holder) itemView.getTag();
        }

        holder.bind(mDatas.get(position));

        return itemView;
    }

    protected abstract int getItemView();

    public abstract Holder onCreateViewHolder(View itemView);

    public List<Data> getDatas() {
        return mDatas;
    }

    public void add(Data item) {
        mDatas.add(item);
        notifyDataSetChanged();
    }

    public void add(Data item, int position) {
        mDatas.add(position, item);
        notifyDataSetChanged();
    }

    public void add(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            mDatas.add(items.get(i));
        }
        notifyDataSetChanged();
    }

    public void addOrUpdate(Data item) {
        int i = mDatas.indexOf(item);
        if (i >= 0) {
            mDatas.set(i, item);
            notifyDataSetChanged();
        } else {
            add(item);
        }
    }

    public void addOrUpdate(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            Data item = items.get(i);
            int x = mDatas.indexOf(item);
            if (x >= 0) {
                mDatas.set(x, item);
            } else {
                add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position >= 0 && position < mDatas.size()) {
            mDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(Data item) {
        int position = mDatas.indexOf(item);
        remove(position);
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }
}
