package net.derohimat.samplebasemvp.view.fragment.detail;

import android.content.Context;
import android.view.ViewGroup;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.data.remote.model.forecast.List;


/**
 * Created by deni rohimat on 17/02/15.
 */
public class DetailRecyclerAdapter extends BaseRecyclerAdapter<List, DetailHolder> {

    public DetailRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.details_row;
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

}
