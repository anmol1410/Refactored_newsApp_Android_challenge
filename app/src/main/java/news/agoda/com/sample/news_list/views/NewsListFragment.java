package news.agoda.com.sample.news_list.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import news.agoda.com.sample.Injection;
import news.agoda.com.sample.R;
import news.agoda.com.sample.news_list.listeners.NewsInteractionListener;
import news.agoda.com.sample.news_list.listeners.NewsItemClickListener;
import news.agoda.com.sample.news_list.model.NewsEntityModel;
import news.agoda.com.sample.news_list.presenters.NewsContract;
import news.agoda.com.sample.news_list.presenters.NewsListPresenter;
import news.agoda.com.sample.utility.RecyclerViewSpaceItemDecorator;

public class NewsListFragment extends Fragment {
    private NewsContract.NewsPresenter mPresenter;
    private RecyclerView mRvNewsList;
    private NewsInteractionListener mListener;
    private ViewCallbackListener mCallback = new ViewCallbackListener();

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setupUI(view);

        new NewsListPresenter(
                Injection.provideUseCaseHandler(),
                Injection.provideGetNews(),
                mCallback);
    }

    private void setupUI(View view) {
        mRvNewsList = (RecyclerView) view.findViewById(R.id.rvNewsList);
        assert mRvNewsList != null;
        mRvNewsList.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvNewsList.setLayoutManager(layoutManager);
        mRvNewsList.addItemDecoration(new RecyclerViewSpaceItemDecorator(getContext(), R.dimen.rv_divider));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewsInteractionListener) {
            mListener = (NewsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NewsInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mCallback = null;
    }

    private class ViewCallbackListener implements NewsContract.View {
        @Override
        public void setLoadingIndicator(boolean toShow) {
            if (mListener != null) {
                mListener.setLoadingIndicator(toShow);
            }
        }

        @Override
        public void showNews(List<NewsEntityModel> news) {
            mRvNewsList.setAdapter(new NewsAdapter(news, new NewsItemClickCallback()));
        }

        @Override
        public boolean isActive() {
            return NewsActivity.isActive(getContext());
        }

        @Override
        public void showLoadingNewsError(boolean toShowError) {
            if (mListener != null) {
                mListener.showLoadingNewsError(toShowError);
            }
        }

        @Override
        public void setPresenter(NewsContract.NewsPresenter presenter) {
            mPresenter = presenter;
        }
    }

    private class NewsItemClickCallback implements NewsItemClickListener {
        @Override
        public void onNewsClicked(int newsPos) {
            if (mListener != null) {
                NewsEntityModel newsEntityModel = ((NewsAdapter) mRvNewsList.getAdapter()).getItemAt(newsPos);
                mListener.onNewsClicked(newsEntityModel);
            }
        }
    }
}
