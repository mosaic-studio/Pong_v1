package me.lab11.marlon.pong_v1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import me.lab11.marlon.praxv1.SGImage;
import me.lab11.marlon.praxv1.SGImageFactory;
import me.lab11.marlon.praxv1.SGRenderer;
import me.lab11.marlon.praxv1.SGView;

/**
 * Created by Marlon on 17/05/2015.
 */
public class GameView extends SGView {
    private final static int BALL_SIZE              = 16 * 3; // 16
    private final static int DISTANCE_FROM_EDGE     = 16 * 3; // 16
    private final static int PADDLE_HEIGHT          = 92 * 3; // 92
    private final static int PADDLE_WIDTH           = 23 * 3; // 23


    private RectF mBallDestination                  = new RectF();
    private RectF mOpponentDestination              = new RectF();
    private RectF mPlayerDestination                = new RectF();
    private Rect mTempImageSource                   = new Rect();


    private final static int BALL_SPEED             = 120;
    private final static int OPPONENT_SPEED         = 120;

    private boolean mBallMoveRight                  = true;
    private boolean mOpponentMoveDown               = true;


    private SGImage mBallImage;
    private SGImage mOpponentImage;
    private SGImage mPlayerImage;


    public GameView(Context context) {
        super(context);
    }

    @Override
    public void step(Canvas canvas, float elapsedTimeInSeconds){
        moveBall(elapsedTimeInSeconds);
        moveOpponent(elapsedTimeInSeconds);

        SGRenderer renderer = getRenderer();

        renderer.beginDrawing(canvas, Color.BLACK);

        mTempImageSource.set(0, 0, BALL_SIZE, BALL_SIZE);
        renderer.drawImage(mBallImage, mTempImageSource, mBallDestination);

        mTempImageSource.set(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
        renderer.drawImage(mPlayerImage, mTempImageSource, mPlayerDestination);

        mTempImageSource.set(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
        renderer.drawImage(mOpponentImage, mTempImageSource, mOpponentDestination);

        renderer.endDrawing();
    }

    @Override
    protected void setup(){
        SGImageFactory imageFactory = getImageFactory();

        mBallImage = imageFactory.createImage(R.drawable.ball);
        mPlayerImage = imageFactory.createImage("player.png");
        mOpponentImage = imageFactory.createImage("opponent.png");

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

    public void moveBall(float elapsedTimeInSeconds){
        Point viewDimensions = getDimensions();
        if(mBallMoveRight){
            mBallDestination.left += BALL_SPEED * elapsedTimeInSeconds;
            mBallDestination.right += BALL_SPEED * elapsedTimeInSeconds;

            if(mBallDestination.right >= viewDimensions.x){
                mBallDestination.left = viewDimensions.x - BALL_SIZE;
                mBallDestination.right = viewDimensions.x;

                mBallMoveRight = false;
            }
        }else {
            mBallDestination.left -= BALL_SPEED * elapsedTimeInSeconds;
            mBallDestination.right -= BALL_SPEED * elapsedTimeInSeconds;
            if (mBallDestination.left < 0) {

                mBallDestination.left = 0;
                mBallDestination.right = BALL_SIZE;
                mBallMoveRight = true;
            }
        }
    }

    public void moveOpponent(float elapsedTimeInSeconds){
        Point viewDimensions = getDimensions();

        if(mOpponentMoveDown){
            mOpponentDestination.top += OPPONENT_SPEED * elapsedTimeInSeconds;
            mOpponentDestination.bottom += OPPONENT_SPEED * elapsedTimeInSeconds;
            if(mOpponentDestination.bottom >= viewDimensions.y){
                mOpponentDestination.top = viewDimensions.y - PADDLE_HEIGHT;
                mOpponentDestination.bottom = viewDimensions.y;

                mOpponentMoveDown = false;
            }
        }else {
            mOpponentDestination.top -= OPPONENT_SPEED * elapsedTimeInSeconds;
            mOpponentDestination.bottom -= OPPONENT_SPEED * elapsedTimeInSeconds;
            if(mOpponentDestination.top < 0){
                mOpponentDestination.top = 0;
                mOpponentDestination.bottom = PADDLE_HEIGHT;

                mOpponentMoveDown = true;
            }
        }
    }

    public void movePlayer(float x, float y){
        Point viewDimensions = getDimensions();
        mPlayerDestination.top += y;
        mPlayerDestination.bottom += y;

        if(mPlayerDestination.top < 0){
            mPlayerDestination.top = 0;
            mPlayerDestination.bottom = PADDLE_HEIGHT;
        }
        else if(mPlayerDestination.bottom > viewDimensions.y){
            mPlayerDestination.top = viewDimensions.y - PADDLE_HEIGHT;
            mPlayerDestination.bottom = viewDimensions.y;
        }
    }
}
