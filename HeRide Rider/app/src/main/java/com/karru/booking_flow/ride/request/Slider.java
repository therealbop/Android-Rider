package com.karru.booking_flow.ride.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatSeekBar;

/**
 * <h1>Slider</h1>
 * This class is used to add the slider for cancel the trip
 * @author  embed
 * @since on 29/12/16.
 */
public class Slider extends AppCompatSeekBar {
    private Drawable mThumb;
    public Slider(Context context) {
        super(context);
    }
    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private SliderProgressCallback sliderProgressCallback;
    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        mThumb = thumb;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (event.getX() >= mThumb.getBounds().left
                    && event.getX() <= mThumb.getBounds().right
                    && event.getY() <= mThumb.getBounds().bottom
                    && event.getY() >= mThumb.getBounds().top) {

                super.onTouchEvent(event);
            } else {
                return false;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            int i = getProgress();
            if(sliderProgressCallback!=null)
            {
                if(i>65)
                {
                    setProgress(100);
                    sliderProgressCallback.onSliderProgressChanged(100);
                }
                else
                {
                    setProgress(0);
                    sliderProgressCallback.onSliderProgressChanged(0);
                }

            }
            return false;
        }
        else
        {
            super.onTouchEvent(event);
        }
        return true;
    }
    public interface SliderProgressCallback
    {
        void onSliderProgressChanged(int progress);
    }
    public void  setSliderProgressCallback(SliderProgressCallback sliderProgressCallback)
    {
        this.sliderProgressCallback=sliderProgressCallback;
    }
}
