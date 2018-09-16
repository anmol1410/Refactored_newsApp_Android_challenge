package news.agoda.com.sample.news_list.viewholders;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;
import news.agoda.com.sample.news_list.listeners.NewsClickCallbackListener;
import news.agoda.com.sample.news_list.listeners.NewsItemClickListener;
import news.agoda.com.sample.news_list.model.NewsEntityModel;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * View Manager to render the News UI.
 */
public class NewsHolder extends RecyclerView.ViewHolder {
    private final View mView;
    private final TextView mTvNewtTitle;
    private final DraweeView mIvNewsImage;

    public NewsHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTvNewtTitle = (TextView) itemView.findViewById(R.id.news_title);
        mIvNewsImage = (DraweeView) itemView.findViewById(R.id.news_item_image);
    }

    public void bind(NewsEntityModel newsEntityModel, NewsItemClickListener newsItemClickListener) {
        mView.setOnClickListener(new NewsClickCallbackListener(this, newsItemClickListener));
        mTvNewtTitle.setText(newsEntityModel.getNewsTitle());
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(Uri.parse(newsEntityModel.getNewsImageUrl())))
                .setOldController(mIvNewsImage.getController())
                .build();
        mIvNewsImage.setController(draweeController);
    }
}