package news.agoda.com.sample.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Must have functionality for all Activities.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static boolean isActive(@Nullable final Context context) {
        return context != null && !((Activity) context).isFinishing();
    }
}
