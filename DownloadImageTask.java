package findmypromoter.viggo.com.br.findmypromoter.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Lucas Marciano on 03/05/2016.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> implements Keys{
    ImageView bmImage;
    Context context;

    public DownloadImageTask(ImageView bmImage, Context context) {
        this.bmImage = bmImage;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEVICE_WIDTH, Context.MODE_PRIVATE);
        int width = sharedPreferences.getInt("device_width",0);
        //bmImage.setImageBitmap(result);
        bmImage.setImageBitmap(UtilMethods.resizeImage(context, result, width, width));
        bmImage.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
