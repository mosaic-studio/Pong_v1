package me.lab11.marlon.praxv1;

import android.view.MotionEvent;

/**
 * Created by marlon on 22/05/15.
 */
public interface SGInputSubscriber {
    void onScroll(MotionEvent downEvent, MotionEvent moveEvent,
                         float distanceX, float distanceY);

    void onUp(MotionEvent event);

    void onDown(MotionEvent event);
}
