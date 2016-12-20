package net.derohimat.baseapp.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import net.derohimat.baseapp.ui.adapter.viewholder.BaseHeaderViewHolder;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseHeaderAdapter<Data, ViewHolder extends BaseItemViewHolder<Data>,
        Header extends BaseHeaderViewHolder> extends
        BaseRecyclerAdapter<Data, BaseItemViewHolder> {
    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;
    protected boolean mHasHeader = true;
    protected Header mHeader;
    protected Bundle mBundle;

    public BaseHeaderAdapter(Context context, Bundle bundle) {
        super(context);
        this.mBundle = bundle;
        if (mHasHeader) {
            mDatas.add(null);
        }
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        if (viewType == TYPE_HEADER) {
            return getHeaderLayout();
        } else {
            return getItemLayout(viewType);
        }
    }

    protected abstract int getHeaderLayout();

    protected abstract int getItemLayout(int viewType);

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (mHasHeader && viewType == TYPE_HEADER) {
            mHeader = onCreateHeaderViewHolder(viewGroup, viewType);
            return mHeader;
        }

        return onCreateItemViewHolder(viewGroup, viewType);
    }

    protected abstract Header onCreateHeaderViewHolder(ViewGroup viewGroup, int viewType);

    public abstract ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(BaseItemViewHolder holder, int position) {
        if (mHasHeader && position == 0) {
            mHeader.show();
            return;
        }
        holder.setHasHeader(mHasHeader);
        super.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHasHeader) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void showHeader() {
        if (!mHasHeader) {
            mHasHeader = true;
            mDatas.add(0, null);
        }
    }

    public void hideHeader() {
        if (mHasHeader) {
            mHasHeader = false;
            mDatas.remove(0);
        }
    }

    public boolean isHasHeader() {
        return mHasHeader;
    }

    @Override
    public void clear() {
        super.clear();
        if (mHasHeader) {
            mDatas.add(null);
        }
    }

    @Override
    public List<Data> getDatas() {
        return mHasHeader ? new ArrayList<>(mDatas.subList(1, mDatas.size())) : super.getDatas();
    }

    public Header getHeader() {
        return mHeader;
    }

    @Override
    public void add(Data item, int position) {
        if (mHasHeader) {
            mDatas.add(position + 1, item);
            notifyItemInserted(position + 1);
        } else {
            super.add(item, position);
        }
    }

    @Override
    public void remove(int position) {
        if (mHasHeader) {
            mDatas.remove(position + 1);
            notifyItemRemoved(position + 1);
        } else {
            super.remove(position);
        }
    }
}
