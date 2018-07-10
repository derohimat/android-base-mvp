package net.derohimat.baseapp.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BaseImageView extends AppCompatImageView {
    private String mImageUrl;

    public BaseImageView(Context context) {
        super(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(String url, int errorResourceId) {
        mImageUrl = url;
        Picasso.get()
                .load(url)
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderResourceId, int errorResourceId) {
        mImageUrl = url;
        Picasso.get()
                .load(url)
                .placeholder(placeHolderResourceId)
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderDrawable, Drawable errorDrawable) {
        mImageUrl = url;
        Picasso.get()
                .load(url)
                .placeholder(placeHolderDrawable)
                .error(errorDrawable)
                .into(this);
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String url) {
        mImageUrl = url;
        Picasso.get()
                .load(url)
                .into(this);
    }
}
