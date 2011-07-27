package com.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class DoodleActivity extends GraphicsActivity {    

  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(new MyView(this));
	
	mPaint = new Paint();
	mPaint.setAntiAlias(true);
	mPaint.setDither(true);
	mPaint.setColor(0xFFFF0000);
	mPaint.setStyle(Paint.Style.STROKE);
	mPaint.setStrokeJoin(Paint.Join.ROUND);
	mPaint.setStrokeCap(Paint.Cap.ROUND);
	mPaint.setStrokeWidth(12);
	
	mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
		0.4f, 6, 3.5f);
	
	mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
  }

  private Paint       mPaint;
  private MaskFilter  mEmboss;
  private MaskFilter  mBlur;
 
  public void colorChanged(int color) {
	mPaint.setColor(color);
  }

  public class MyView extends View {

	private static final float MINP = 0.25f;
	private static final float MAXP = 0.75f;
	
	private Bitmap  mBitmap;
	private Canvas  mCanvas;
	private Path    mPath;
	private Paint   mBitmapPaint;
	
	public MyView(Context c) {
	    super(c);
	    
	    mBitmap = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
	    mCanvas = new Canvas(mBitmap);
	    mPath = new Path();
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	  super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	  canvas.drawColor(0xFFAAAAAA);
	    
	  canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
	    
	  canvas.drawPath(mPath, mPaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
	  mPath.reset();
	  mPath.moveTo(x, y);
	  mX = x;
	  mY = y;
	}
	
	private void touch_move(float x, float y) {
	  float dx = Math.abs(x - mX);
	  float dy = Math.abs(y - mY);
	  
	  if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
	    mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
	    mX = x;
	    mY = y;
	}
  }
	
  private void touch_up() {
	mPath.lineTo(mX, mY);
	// commit the path to our offscreen
	mCanvas.drawPath(mPath, mPaint);
	// kill this so we don't double draw
	mPath.reset();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
	float x = event.getX();
	float y = event.getY();
	    
	switch (event.getAction()) {
	  case MotionEvent.ACTION_DOWN:
	    touch_start(x, y);
	    invalidate();
	    break;
	  case MotionEvent.ACTION_MOVE:
	    touch_move(x, y);
	    invalidate();
	    break;
	  case MotionEvent.ACTION_UP:
	    touch_up();
	    invalidate();
	    break;
	}
	
	return true;
	
	}
  }

  private static final int COLOR_MENU_ID = Menu.FIRST;
  private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
  private static final int BLUR_MENU_ID = Menu.FIRST + 2;
  private static final int ERASE_MENU_ID = Menu.FIRST + 3;
  private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;

  public boolean onOptionsItemSelected(MenuItem item) {
	mPaint.setXfermode(null);
	mPaint.setAlpha(0xFF);
	
	switch (item.getItemId()) {
      case COLOR_MENU_ID:
	    return true;
	  case EMBOSS_MENU_ID:
	    if (mPaint.getMaskFilter() != mEmboss) {
	      mPaint.setMaskFilter(mEmboss);
	    } else {
	      mPaint.setMaskFilter(null);
	    }
	    
	    return true;
	  case BLUR_MENU_ID:
	    if (mPaint.getMaskFilter() != mBlur) {
	      mPaint.setMaskFilter(mBlur);
	    } else {
	      mPaint.setMaskFilter(null);
	    }
	    return true;
	  case ERASE_MENU_ID:
	    mPaint.setXfermode(new PorterDuffXfermode(
	    	PorterDuff.Mode.CLEAR));
	    return true;
	  case SRCATOP_MENU_ID:
	    mPaint.setXfermode(new PorterDuffXfermode(
	    	PorterDuff.Mode.SRC_ATOP));
	    mPaint.setAlpha(0x80);
	    
	    return true;
	}
	
	return super.onOptionsItemSelected(item);
  }
}