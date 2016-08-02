package net.derohimat.samplebasemvp.view.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.fragment.BaseFragment;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;
import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.model.forecast.Forecast;
import net.derohimat.samplebasemvp.model.forecast.List;
import net.derohimat.samplebasemvp.util.DialogFactory;

import butterknife.Bind;

public class DetailFragment extends BaseFragment implements DetailMvpView {

    private static final String ARG_EXAMPLE = "ARG_EXAMPLE";

    @Bind(R.id.recyclerview_details)
    BaseRecyclerView mRecyclerView;

    private DetailPresenter mPresenter;
    private DetailRecyclerAdapter mAdapter;

    public static DetailFragment newInstance(int example_argument) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EXAMPLE, example_argument);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.detail_fragment;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        setUpAdapter();
        setUpRecyclerView();
        setUpPresenter();
        int example_argument = getArguments().getInt(ARG_EXAMPLE);

        //for recreation of the toolbar
        setHasOptionsMenu(true);
    }

    private void setUpPresenter() {
        mPresenter = new DetailPresenter(getActivity());
        mPresenter.attachView(this);
        mPresenter.loadForcast("Bandung");
    }

    private void setUpAdapter() {
        mAdapter = new DetailRecyclerAdapter(mContext);
        mAdapter.setOnItemClickListener((view, position) -> {
            List selectedItem = mAdapter.getDatas().get(position - 1);
            DialogFactory.createSimpleOkDialog(mContext, getString(R.string.app_name),
                    selectedItem.getDtTxt() + " position " + (position - 1)).show();
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadForcast("Bandung");
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void showForecast(Forecast forecast) {
        mAdapter.addAll(forecast.getList());
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_fragment_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_detail_refresh:
                mPresenter.loadForcast("Bandung");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
