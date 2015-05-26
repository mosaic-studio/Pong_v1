package me.lab11.marlon.praxv1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Marlon on 17/05/2015.
 */
public class SGView extends View {
    private SGStepwatch mStepwatch          = new SGStepwatch();
    private Point mDimensions               = new Point();
    private boolean mHasStarted             = false;
    private SGImageFactory mImageFactory;
    private SGRenderer mRenderer;

    public SGView(Context context) {
        super(context);
        mImageFactory = new SGImageFactory(context);
        mRenderer = new SGRenderer();
    }

    @Override
    public void onDraw(Canvas canvas){
        //canvas.drawColor(Color.LTGRAY);
        step(canvas, mStepwatch.tick());

        invalidate();
    }

    public void step(Canvas canvas, float elapsedTimeInSeconds){

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        mDimensions.set(width, height);

        if(!mHasStarted){
            setup();

            mHasStarted = true;
        }
    }

    protected void setup(){ }

    public Point getDimensions(){
        return mDimensions;
    }

    public SGImageFactory getImageFactory(){
        return mImageFactory;
    }

    public SGRenderer getRenderer(){ return mRenderer; }

}
