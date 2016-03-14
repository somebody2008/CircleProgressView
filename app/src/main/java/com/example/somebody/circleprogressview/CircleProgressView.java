package com.example.somebody.circleprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by somebody on 2016/3/14.
 */
public class CircleProgressView extends View {
    private static final String TAG = "CircleProgressBar";
    private int mMaxProgress = 100;
    private int mProgress = 30;
    private final int mCircleLineStrokeWidth = 8;
    private final int mTxtStrokeWidth = 2;
    private final Paint mPaint;
    private final RectF mRectF;
    private String mTxtHint1;
    private String mTxtHint2;
    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        if(width != height){
            int min = Math.min(width,height);
            width = min;
            height = min;
        }
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mRectF.left = mCircleLineStrokeWidth/2;
        mRectF.top = mCircleLineStrokeWidth/2;
        mRectF.right = width - mCircleLineStrokeWidth/2;
        mRectF.bottom = height - mCircleLineStrokeWidth/2;

        canvas.drawArc(mRectF, -90, 360, false, mPaint);

        mPaint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);

        mPaint.setStrokeWidth(mTxtStrokeWidth);
        String text = mProgress +"%";
        int textHeight = height /4;
        mPaint.setTextSize(textHeight);
        int textWidth = (int)mPaint.measureText(text,0,text.length());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,width/2 - textWidth/2,height/2 + textHeight/2,mPaint);

        if (!TextUtils.isEmpty(mTxtHint1)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint1;
            textHeight = height / 8;
            mPaint.setTextSize(textHeight);
            mPaint.setColor(Color.rgb(0x99, 0x99, 0x99));
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, height / 4 + textHeight / 2, mPaint);
        }

        if (!TextUtils.isEmpty(mTxtHint2)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint2;
            textHeight = height / 8;
            mPaint.setTextSize(textHeight);
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, mPaint);
        }
    }
    public void setProgress(int progress){
        this.mProgress = progress;
        this.invalidate();
    }
    public void setmTxtHint1(String mTxtHint1){
        this.mTxtHint1 = mTxtHint1;
    }
    public void setmTxtHint2(String mTxtHint2){
        this.mTxtHint2 = mTxtHint2;
    }

    public void setProgressNotInUiThread(int progress){
        this.mProgress = progress;
        this.postInvalidate();
    }
}
