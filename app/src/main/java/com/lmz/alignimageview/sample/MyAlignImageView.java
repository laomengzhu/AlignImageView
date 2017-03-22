package com.lmz.alignimageview.sample;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.lmz.alignimageview.AlignImageView;

/**
 * Created by xiaolifan on 16-12-7.
 */

public class MyAlignImageView extends AlignImageView {

    private ViewSize viewSize;

    public MyAlignImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyAlignImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAlignImageView(Context context) {
        super(context);
    }

    @Keep
    public ViewSize getViewSize() {
        return viewSize;
    }

    @Keep
    public void setViewSize(ViewSize viewSize) {
        if (viewSize == null) {
            viewSize = new ViewSize(0, 0);
        }
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        if (lp.width != viewSize.width || lp.height != viewSize.height) {
            this.viewSize = viewSize;
            lp.width = viewSize.width;
            lp.height = viewSize.height;
            requestLayout();
        }
    }
}
