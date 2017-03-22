package com.lmz.alignimageview.sample;

import android.animation.TypeEvaluator;


public class ViewSizeEvaluator implements TypeEvaluator<ViewSize> {

    private ViewSize resultViewSize = new ViewSize(0, 0);

    @Override
    public ViewSize evaluate(float fraction, ViewSize startValue, ViewSize endValue) {
        resultViewSize.width = (int) (startValue.width + (endValue.width - startValue.width) * fraction);
        resultViewSize.height = (int) (startValue.height + (endValue.height - startValue.height) * fraction);
        return resultViewSize;
    }
};
