package me.lab11.marlon.pong_v1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import me.lab11.marlon.praxv1.SGView;

/**
 * Created by Marlon on 17/05/2015.
 */
public class GameView extends SGView {
    private final static int BALL_SIZE              = 16 * 3; // 16
    private final static int DISTANCE_FROM_EDGE     = 16 * 3; // 16
    private final static int PADDLE_HEIGHT          = 92 * 3; // 92
    private final static int PADDLE_WIDTH           = 23 * 3; // 23

    private Rect mBallDestination                   = new Rect();
    private Rect mOpponentDestination               = new Rect();
    private Rect mPlayerDestination                 = new Rect();

    private Paint mTempPaint                         = new Paint();

    public GameView(Context context) {
        super(context);
    }

    @Override
    public void step(Canvas canvas){
        mTempPaint.setColor(Color.RED);

        canvas.drawRect(mPlayerDestination, mTempPaint);
        canvas.drawRect(mBallDestination, mTempPaint);
        canvas.drawRect(mOpponentDestination, mTempPaint);
    }

    @Override
    protected void setup(){
        Point viewDimensions = getDimensions();
        Point viewCenter = new Point(viewDimensions.x / 2, viewDimensions.y / 2);

        int halfBall = BALL_SIZE / 2;
        int halfPaddleHeight = PADDLE_HEIGHT / 2;

        mBallDestination.set(viewCenter.x - halfBall, // Esquerda
                viewCenter.y - halfBall, // Topo
                viewCenter.x + halfBall, // Direita
                viewCenter.y + halfBall); // Base

        mPlayerDestination.set(DISTANCE_FROM_EDGE,  // Esquerda
                viewCenter.y - halfPaddleHeight, // Topo
                DISTANCE_FROM_EDGE + PADDLE_WIDTH, // Direita
                viewCenter.y + halfPaddleHeight); // Base

        mOpponentDestination.set(
                viewDimensions.x - (DISTANCE_FROM_EDGE + PADDLE_WIDTH), // Esquerda
                viewCenter.y - halfPaddleHeight, // Topo
                viewDimensions.x - DISTANCE_FROM_EDGE, // Direita
                viewCenter.y + halfPaddleHeight); // Base
    }
}
