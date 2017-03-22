package com.lmz.alignimageview.sample;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewSize minViewSize;
    private ViewSize maxViewSize;
    private MyAlignImageView alignImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alignImageView = (MyAlignImageView) findViewById(R.id.alignImageView);
    }

    @Override
    public void onClick(View v) {
        if (minViewSize == null || maxViewSize == null) {
            minViewSize = new ViewSize(alignImageView.getMeasuredWidth(), 0);
            maxViewSize = new ViewSize(alignImageView.getMeasuredWidth(), alignImageView
                    .getMeasuredHeight());
        }
        ObjectAnimator animator = ObjectAnimator.ofObject(alignImageView, "viewSize",
                new ViewSizeEvaluator(), minViewSize, maxViewSize);
        animator.setDuration(600);
        animator.start();
    }
}
