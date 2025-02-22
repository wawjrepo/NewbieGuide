package com.app.hubert.guide.model;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.app.hubert.guide.util.LogUtil;
import com.app.hubert.guide.util.ViewUtils;

/**
 * Created by hubert
 * <p>
 * Created on 2017/7/27.
 */
public class HighlightView implements HighLight {

    private View mHole;
    private Shape shape;
    private int round;
    /**
     * 高亮相对view的padding
     */
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private HighlightOptions options;
    private RectF rectF;

    public HighlightView(View mHole, Shape shape, int round, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.mHole = mHole;
        this.shape = shape;
        this.round = round;
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
    }

    public void setOptions(HighlightOptions options) {
        this.options = options;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public HighlightOptions getOptions() {
        return options;
    }

    @Override
    public float getRadius() {
        if (mHole == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        return Math.max(mHole.getWidth() / 2, mHole.getHeight() / 2) + (float) (paddingLeft + paddingRight) / 2;
    }

    @Override
    public RectF getRectF(View target) {
        if (mHole == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        if (rectF == null) {
            rectF = fetchLocation(target);
        } else if (options != null && options.fetchLocationEveryTime) {
            rectF = fetchLocation(target);
        }
        LogUtil.i(mHole.getClass().getSimpleName() + "'s location:" + rectF);
        return rectF;
    }

    private RectF fetchLocation(View target) {
        RectF location = new RectF();
        Rect locationInView = ViewUtils.getLocationInView(target, mHole);
        location.left = locationInView.left - paddingLeft;
        location.top = locationInView.top - paddingTop;
        location.right = locationInView.right + paddingRight;
        location.bottom = locationInView.bottom + paddingBottom;
        return location;
    }

}