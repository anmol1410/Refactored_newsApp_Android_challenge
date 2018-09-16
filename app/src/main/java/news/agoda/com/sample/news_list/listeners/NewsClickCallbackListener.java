package news.agoda.com.sample.news_list.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import news.agoda.com.sample.R;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Handles the clicks on the News view.
 */
public class NewsClickCallbackListener implements View.OnClickListener {

    /**
     * View holder hosting the views.
     */
    private RecyclerView.ViewHolder mViewHolder;

    /**
     * Callback to receive the notification for the clicks.
     */
    private NewsItemClickListener mNewsClickCallback;

    public NewsClickCallbackListener(RecyclerView.ViewHolder viewHolder, NewsItemClickListener newsClickCallback) {
        mViewHolder = viewHolder;
        mNewsClickCallback = newsClickCallback;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Trigger the callback on various clicks.
            case R.id.ll_item_view:
                // Notify back the result.
                mNewsClickCallback.onNewsClicked(mViewHolder.getAdapterPosition());
                break;
        }
    }
}