package net.derohimat.samplebasemvp.view.fragment.detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;
import net.derohimat.baseapp.ui.view.BaseImageView;
import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.data.remote.model.forecast.List;
import net.derohimat.samplebasemvp.view.activity.main.MainActivity;

import butterknife.Bind;


/**
 * Created by deni rohimat on 17/02/15.
 */
public class DetailHolder extends BaseItemViewHolder<List> {

    @Bind(R.id.row_textView_forecast)
    TextView row_textView_forecast;

    @Bind(R.id.row_imageView_forecast)
    BaseImageView row_imageView_forecast;

    @Bind(R.id.row_details_cardview)
    CardView row_details_cardview;

    public DetailHolder(Context context, View itemView, BaseRecyclerAdapter.OnItemClickListener itemClickListener,
                        BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
        this.mContext = context;
    }

    @Override
    public void bind(List mForecast) {

        row_imageView_forecast.setImageResource(MainActivity.getIcon(mForecast.getWeather().get(0).getId()));

        row_textView_forecast.setText(String.format("%s %s %s", mForecast.getDtTxt(), mForecast.getMain().getTempMin(), mForecast.getMain().getTempMax()));
        row_details_cardview.setTag(mForecast);
    }
}
