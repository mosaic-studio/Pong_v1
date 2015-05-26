package me.lab11.marlon.praxv1;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by marlon on 22/05/15.
 */
public class SGInputPlublisher implements GestureDetector.OnGestureListener {
    public static final String TAG = "SGInputPublisher";

    protected  GestureDetector mGestureDetector         = null;
    protected ArrayList<SGInputSubscriber> mSubscribers =
            new ArrayList<>();

    public SGInputPlublisher(Context context){
        mGestureDetector = new GestureDetector(context, this);
    }

    public void registerSubscriber(SGInputSubscriber listener){
        mSubscribers.add(listener);
    }

    public boolean unregisterSubscribe(SGInputSubscriber listener){
        return mSubscribers.remove(listener);
    }

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionMasked();

        if(action == MotionEvent.ACTION_UP){
            for(int i = 0; i < mSubscribers.size(); i++){
                mSubscribers.get(i).onUp(event);
            }
            return true;
        }
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event){
        for(int i = 0; i < mSubscribers.size(); i++){
            mSubscribers.get(i).onDown(event);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll() chamado.");
        for(int i = 0; i < mSubscribers.size(); i++){
            mSubscribers.get(i).onScroll(downEvent, moveEvent, distanceX, distanceY);
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent,
                           float velocityX, float velocityy){
        return true;
    }

}
