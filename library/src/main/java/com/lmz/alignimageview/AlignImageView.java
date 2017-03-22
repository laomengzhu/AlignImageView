package com.lmz.alignimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class AlignImageView extends AppCompatImageView {

    public static final int NONE = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;

    @IntDef({NONE, LEFT, TOP, RIGHT, BOTTOM})
    public @interface AlignOrientation {
    }

    private int w = 0;
    private int h = 0;

    private int mAlignOrientation = NONE;
    private int drawableWidth;
    private int drawableHeight;
    private boolean needResetBounds = false;

    public AlignImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    public AlignImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public AlignImageView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.FIT_XY);
        if (attrs == null) {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AlignImageView);
        mAlignOrientation = ta.getInt(R.styleable.AlignImageView_alignOrientation, NONE);
        ta.recycle();
    }

    public boolean setAlignOrientation(@AlignOrientation int orientation) {
        if (mAlignOrientation != orientation) {
            mAlignOrientation = orientation;
            needResetBounds = true;
            return true;
        }
        return false;
    }

    @Deprecated
    @Override
    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
    }

    @AlignOrientation
    public int getAlignOrientation() {
        return mAlignOrientation;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.w != w || this.h != h) {
            this.w = w;
            this.h = h;
            needResetBounds = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            int drawableWidth = getDrawable().getIntrinsicWidth();
            int drawableHeight = getDrawable().getIntrinsicHeight();
            if (!needResetBounds && (this.drawableWidth != drawableWidth || this.drawableHeight != drawableHeight)) {
                needResetBounds = true;
                this.drawableWidth = drawableWidth;
                this.drawableHeight = drawableHeight;
            }
            if (needResetBounds) {
                if (mAlignOrientation == LEFT) {
                    getDrawable().setBounds(0, 0, Math.max(h * drawableWidth / drawableHeight, w), h);
                } else if (mAlignOrientation == TOP) {
                    getDrawable().setBounds(0, 0, w, Math.max(w * drawableHeight / drawableWidth, h));
                } else if (mAlignOrientation == RIGHT) {
                    getDrawable().setBounds(Math.min(0, w - h * drawableWidth / drawableHeight), 0, w, h);
                } else if (mAlignOrientation == BOTTOM) {
                    getDrawable().setBounds(0, Math.min(0, h - w * drawableHeight / drawableWidth), w, h);
                } else {
                    getDrawable().setBounds(0, 0, w, h);
                }
            }
        }
        super.onDraw(canvas);
    }
}
