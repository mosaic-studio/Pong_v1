package me.lab11.marlon.pong_v1;

import android.os.Bundle;
import android.util.Log;

import me.lab11.marlon.praxv1.SGActivity;
import me.lab11.marlon.praxv1.SGPreferences;

/**
 * Created by Marlon on 17/05/2015.
 */
public class GameActivity extends SGActivity {
    public static final String TAG          = "PongV1";
    private GameView mView                  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        enableFullScreen();
        enableKeepScreenOn();

        mView = new GameView(this);

        setContentView(mView);

        SGPreferences preferences = getPreferences();

        if(preferences.getInt("first_time", -1) == -1){
            preferences.begin()
                    .putInt("first_time", 1)
                    .putInt("dificulty", 0)
                    .putInt("high_score", 15)
                    .end();

            Log.d(TAG, "Primeira Inicialização.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nível de dificuldade.");
        stringBuilder.append(preferences.getInt("difficulty", 0));
        Log.d(TAG, stringBuilder.toString());

        stringBuilder.setLength(0);
        stringBuilder.append("High Score: ");
        stringBuilder.append(preferences.getInt("high_score", 0));
        Log.d(TAG, stringBuilder.toString());
    }
}
