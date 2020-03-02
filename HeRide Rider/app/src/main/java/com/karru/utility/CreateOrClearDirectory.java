package com.karru.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * <h>CreateOrClearDirectory</h>
 * <p>
 *     Singleton Class to create new directory or clear already
 *     created directory
 * </p>
 * @since 26/07/16.
 */
public class CreateOrClearDirectory
{

    private static CreateOrClearDirectory myImageHandler;

    private CreateOrClearDirectory()
    {

    }
    /***********************************************************/


    /**
     * <h2>getInstance</h2>
     * <p>
     * method to get single instance of this class
     * </p>
     * @return: instance of this class
     */
    public static CreateOrClearDirectory getInstance()
    {
        if(myImageHandler == null)
        {
            myImageHandler = new CreateOrClearDirectory();
        }
        return myImageHandler;
    }
    /***********************************************************/


    /**
     * <h2>getAlbumStorageDir</h2>
     * <p>
     * custom method to create &/or clear directory
     *  </p>
     * @param mActivity: Calling activity reference
     * @param folderNameAndPath: name of folder with the path where to be created
     * @param isToClearDir: true if need to clear the directory
     * @return: return the created file
     */
    public File getAlbumStorageDir(Context mActivity, String folderNameAndPath, boolean isToClearDir)
    {
        File newDir;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
        {
           // newDir = new File(Environment.getExternalStorageDirectory() + "/" + folderNameAndPath,takenNewImage);
            newDir = new File(Environment.getExternalStorageDirectory()+"/"+ folderNameAndPath);
        }
        else
        {
           // newDir = new File(mActivity.getFilesDir() + "/" + folderNameAndPath/,takenNewImage);
            newDir = new File(mActivity.getFilesDir()+"/"+folderNameAndPath);


        }

        if (!newDir.isDirectory())
        {
            newDir.mkdirs();
            Log.i("CreateOrClearDirectory", "getAlbumStorageDir created successfully: ");
        }
        else if (isToClearDir)
        {
            File[] newDirectory = newDir.listFiles();

            Log.i("CreateOrClearDirectory", "getAlbumStorageDir to clear dir files : " + newDirectory.length);

            if (newDirectory.length > 0)
            {
                for (int i = 0; i < newDirectory.length; i++)
                {
                    newDirectory[i].delete();
                }
                Log.i("CreateOrClearDirectory", "getAlbumStorageDir to clear dir successfully:");
            }
            else
            {
                Log.i("CreateOrClearDirectory", "getAlbumStorageDir to clear dir no pics: " + newDirectory.length);
            }
        }
        else
        {
            Log.i("CreateOrClearDirectory", "getAlbumStorageDir not to clear dir: ");
        }
        return newDir;
    }
    /***********************************************************/


    /**
     * <h2>addBitmapToSdCardFromURL</h2>
     * <p>
     *     method to download the user profile images from our server
     *     and save it to local directory
     * </p>
     * @param murl: url of image to be downloaded
     * @param mFile: downloaded image file
     */

    public void addBitmapToSdCardFromURL(String murl, File mFile)
    {
        try
        {
            URL url = new URL(murl);
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar

            // download the file
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(mFile.getAbsolutePath());

            byte data[] = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1)
            {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /*************************************************************/


    /**
     * <h2>getCircleCroppedBitmap</h2>
     * <p>
     * custom method to transform inage into circle
     * </p>
     * @param bitmap image bitmap
     * @return bitmap of circular
     */
    public Bitmap getCircleCroppedBitmap(Bitmap bitmap)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    /*****************************************************/
}
