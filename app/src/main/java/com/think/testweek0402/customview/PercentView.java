package com.think.testweek0402.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.think.testweek0402.R;

/**
 * Created by think on 2018/3/13.
 */

public class PercentView extends View {
    private final static String TAG = PercentView.class.getSimpleName();
    private final static String TAG_SIGN = "===>";
    private Paint mPaint;

    //用于定义的圆弧的形状和大小的界限
    private RectF rectF;

    private int backgroundColor = Color.GRAY;
    private int progressColor = Color.BLUE;
    private float radius;
    private int progress;
    private float centerX = 0;
    private float centerY = 0;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    private int gravity = CENTER;

    public PercentView(Context context) {
        super(context);
        init();
    }



    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(context,attrs);
    }

    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttrs(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取测量后的宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //获取高度的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量后的高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.e(TAG, "onMeasure--widthMode-->" + widthMode);
//            03-13 17:37:49.617 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--widthMode-->1073741824

        Log.e(TAG, "onMeasure--widthSize-->" + widthSize);/*450*/

        Log.e(TAG, "onMeasure--heightMode-->" + heightMode);
//            03-13 17:37:49.617 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--heightMode-->1073741824

        Log.e(TAG, "onMeasure--heightSize-->" + heightSize);/*450*/

        Log.e(TAG, "onMeasure--EXACTLY-->" + MeasureSpec.EXACTLY);
        Log.e(TAG, "onMeasure--AT_MOST-->" + MeasureSpec.AT_MOST);
        Log.e(TAG, "onMeasure--UNSPECIFIED-->" + MeasureSpec.UNSPECIFIED);
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--EXACTLY-->1073741824
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--AT_MOST-->-2147483648
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--UNSPECIFIED-->0
        switch (widthMode) {
            case MeasureSpec.EXACTLY:/*1073741824*/
                break;
            case MeasureSpec.AT_MOST:/*2147483648*/
                break;
            case MeasureSpec.UNSPECIFIED:/*0*/
                break;
        }

        //用于定义的圆弧的形状和大小的界限
        //设置矩阵的边界
        //set(float left, float top, float right, float bottom)
        //left-->矩形左侧的X坐标,
        // top-->矩形顶部的Y坐标,
        // right-->矩形右侧的X坐标,
        // bottom-->矩形底部的y坐标
        int with = widthSize;
        rectF.set(
                with / 2 - radius,//坐标为为圆的最最左侧x坐标.
                with / 2 - radius,//坐标为为圆的最最顶侧y坐标.
                with / 2 + radius,//坐标为为圆的最最右侧x坐标.
                with / 2 + radius//坐标为为圆的最最底侧y坐标.
        );
    }

    //布局摆放的监听.
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, TAG_SIGN.concat("onLayout"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setColor(Color.GRAY);
        mPaint.setColor(backgroundColor);
        //设置画笔的样式为填充和描边
        //Paint.Style.FILL--->填充
        //Paint.Style.FILL_AND_STROKE--->填充和描边
        //Paint.Style.STROKE--->描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //获取控件的高度和宽度
        int with = getWidth();
        int height = getHeight();
        //450px*450px,150dp*150dp,设备的dp和px的转换关系:3
        Log.e(TAG, "onDraw---->" + with + "*" + height);

        //设置圆的半径为宽度四分之一(1/4).
//        float radius = with / 4;
        if (radius == 0){
            radius = with / 4;
        }

        //绘制灰色,圆,
        //drawCircle(float cx, float cy, float radius,Paint paint)
        // cx-->圆心x坐标,为宽度的1/2
        // cy-->圆心y坐标,为宽度的1/2
        // radius-->半径,
        // paint-->画笔
        canvas.drawCircle(with / 2, with / 2, radius, mPaint);

        //设置绘制弧度的画笔的颜色
//        mPaint.setColor(Color.BLUE);
        mPaint.setColor(progressColor);


        //根据角度顺时针方向画圆弧
        //drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter,Paint paint)
        //oval==>定义圆弧形状和大小的椭圆的边界
        //startAngle==>起始角角度
        //sweepAngle==>顺时针方向扫描的角度
        //useCenter==>是否在圆弧中包括椭圆的中心,true->是,false->否,
        //paint==>绘制弧度的画笔
//        canvas.drawArc(rectF, 270, 120, true, mPaint);

        //根据进度画圆弧
        double percent = progress * 1.0 / 100;
        int angle = (int) (percent * 360);
        canvas.drawArc(rectF, 270, angle, true, mPaint);
    }


    private void init() {
        mPaint = new Paint();
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        rectF = new RectF();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentView);
        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.PercentView_percent_background_color, Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.PercentView_percent_progress_color, Color.BLUE);
            radius = typedArray.getDimension(R.styleable.PercentView_percent_circle_radius, 0);
            progress = typedArray.getInt(R.styleable.PercentView_percent_circle_progress, 0);
            gravity = typedArray.getInt(R.styleable.PercentView_percent_circle_gravity, CENTER);
            typedArray.recycle();
        }
    }





//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--widthMode-->1073741824
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--widthSize-->450
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--heightMode-->1073741824
//            03-13 17:37:49.587 9586-9586/com.think.testweek0402 E/PercentView: onMeasure--heightSize-->450

}
