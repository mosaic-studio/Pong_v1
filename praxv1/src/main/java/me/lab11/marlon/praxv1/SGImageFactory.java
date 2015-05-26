package me.lab11.marlon.praxv1;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by marlon on 21/05/15.
 */
public class SGImageFactory {
    protected Context mContext          = null;

    public SGImageFactory(Context c){
        mContext = c;
    }

    public SGImage createImage(String filename){
        Bitmap bitmap = null;
        try{
            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open(filename);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }catch (IOException e){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SGImageFactory.createImage(): arquivo ");
            stringBuilder.append(filename);
            stringBuilder.append(" não encontrado");

            Log.d("SimpleGameEngine", stringBuilder.toString());

            return null;
        }
        SGImage image = new SGImage(bitmap);
        return image;
    }

    public SGImage createImage(int resourceId){
        Bitmap bitmap = null;
        try {
            Resources resources = mContext.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);

            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }catch (IOException e){

        }
        SGImage image = new SGImage(bitmap);
        return image;
    }

    public Context getContext(){ return mContext; }
}
