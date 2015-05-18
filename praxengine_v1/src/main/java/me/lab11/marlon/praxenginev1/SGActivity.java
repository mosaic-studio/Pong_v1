package me.lab11.marlon.praxenginev1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Marlon on 17/05/2015.
 */
public class SGActivity extends Activity {
    public static final String TAG = "PraxGameEngineV1";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TAG, "onDestroy() chamado.");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "onPause() chamado.");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.v(TAG, "onRestart() chamado.");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAG, "onResume() chamado.");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "onStart() chamado.");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "onStop() chamado.");
    }
}
