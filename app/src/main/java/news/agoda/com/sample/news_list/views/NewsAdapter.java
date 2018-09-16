package news.agoda.com.sample.news_list.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.news_list.listeners.NewsItemClickListener;
import news.agoda.com.sample.news_list.model.NewsEntityModel;
import news.agoda.com.sample.news_list.viewholders.NewsHolder;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Adapter for rendering the News on the UI.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    /**
     * News to render.
     */
    private final List<NewsEntityModel> mNewsList;
    /**
     * To lister to the view clicks so that the UI can change accordingly.
     */
    private NewsItemClickListener mNewsClickCallback;

    NewsAdapter(List<NewsEntityModel> newsList, NewsItemClickListener newsClickCallback) {
        mNewsList = newsList;
        mNewsClickCallback = newsClickCallback;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        holder.bind(mNewsList.get(position), mNewsClickCallback);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public NewsEntityModel getItemAt(int pos) {
        return mNewsList.get(pos);
    }

    public List<NewsEntityModel> getNews() {
        return mNewsList;
    }
}