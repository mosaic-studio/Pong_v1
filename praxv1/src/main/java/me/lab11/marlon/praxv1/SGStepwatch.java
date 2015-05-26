package me.lab11.marlon.praxv1;

import android.os.SystemClock;

/**
 * Created by marlon on 21/05/15.
 */
public class SGStepwatch {
    protected long mCurrentTime;
    protected long mLastTime;
    protected float mElapsedTime;

    protected float tick(){
        if(mCurrentTime == 0){
            mLastTime = mCurrentTime = SystemClock.uptimeMillis();
        }else{
            mCurrentTime = SystemClock.uptimeMillis();
        }
        mElapsedTime = (mCurrentTime - mLastTime) / 1000.0f;

        mLastTime = mCurrentTime;

        return mElapsedTime;
    }
}
