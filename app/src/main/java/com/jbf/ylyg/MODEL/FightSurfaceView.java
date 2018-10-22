package com.jbf.ylyg.MODEL;
import android.view.*;
import android.view.Window.*;
import android.content.Context;
import android.graphics.*;
import java.util.*;
import android.util.*;
import android.app.*;
import android.text.method.*;

public class FightSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable
{
	private long mAutoPlayCount=0;
	private boolean mIsAutoPlay=true;
	private int mScreenWidth=0;
	private int mScreenHeight=0;
	private Thread mThread;
	private SurfaceHolder mSurfaceHolder;
	private Paint mPaint;
	private Random mRandom;
	private boolean mGameIsContinue=true;
	private boolean mIsALive=true;
	private boolean mIfLeftButtonDown;
	private boolean mIfRightButtonDown;
	
	private Point mLeftButtonPointCenter;
	private Point mRightButtonPointCenter;
	private Point mAutoPlayButtonPointCenter;
	private Point mSmallMapPointCenter;
	private Path mAutoPlayButtonPath;
	private Path mAutoPlayPath;
	private Path mNotAutoPlayPath;
	private Path mSmallMapPath;
	private int mLeftButtonSize;
	private int mRightButtonSize;
	private int mSmallMapSize;
	private int mAutoPlayButtonSize;
	Canvas mCanvas;




	public FightSurfaceView(Context context)
	{
		super(context);
		initEverything();
	}

	@Override
	public void surfaceCreated(SurfaceHolder p1)
	{
		mThread = new Thread(this);
		mThread.start();
		// TODO: Implement this method
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
	}

	@Override
	public void run()
	{
		while (true)
		{
			AI();
			render();
		}
		// TODO: Implement this method
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		return super.onTouchEvent(event);
	}

	protected void initEverything()
	{
		setFocusable(true);
		setKeepScreenOn(true);
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		mCanvas = new Canvas();
		mRandom = new Random(System.currentTimeMillis());
		DisplayMetrics dm=new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;

		mPaint = new Paint();
		mAutoPlayPath = new Path();
		mNotAutoPlayPath = new Path();
		mSmallMapPath = new Path();
		mAutoPlayButtonPath = new Path();
		mSmallMapPointCenter = new Point();
		mAutoPlayButtonPointCenter = new Point();
		mLeftButtonPointCenter = new Point();
		mRightButtonPointCenter = new Point();
		int x,y;
		x = mScreenWidth;
		y = mScreenHeight;
		if (x < y)
		{
			int i=0;
			i = x;
			x = y;
			y = i;
		}
		mLeftButtonSize = x * 3 / 32;
		mRightButtonSize = x * 3 / 32;
		mAutoPlayButtonSize = x / 64;
		mSmallMapSize = x / 16;
		mLeftButtonPointCenter.set(x * 3 / 100 + mLeftButtonSize, y - x * 2 / 100 - y / 100 - mLeftButtonSize);
		mRightButtonPointCenter.set(x * 97 / 100 - mRightButtonSize, y - x * 2 / 100 - y / 100 - mRightButtonSize);
		mAutoPlayButtonPointCenter.set(x * 97 / 100 - mAutoPlayButtonSize, y / 2);
		mSmallMapPointCenter.set(x * 98 / 100 - mSmallMapSize, x / 100 + y / 100 + mSmallMapSize);
		
		mAutoPlayButtonPath.addCircle(mAutoPlayButtonPointCenter.x, mAutoPlayButtonPointCenter.y, mAutoPlayButtonSize, Path.Direction.CW);
		
		mSmallMapPath.addRect(mSmallMapPointCenter.x - mSmallMapSize,
							  mSmallMapPointCenter.y - mSmallMapSize,
							  mSmallMapPointCenter.x + mSmallMapSize,
							  mSmallMapPointCenter.y + mSmallMapSize,
							  Path.Direction.CW);
		mAutoPlayPath.addRect(x / 3, y / 100, x * 99 / 100, y * 99 / 100, Path.Direction.CW);
		mNotAutoPlayPath.addRect(x / 100, y / 100, x *99/ 100, y *99/ 100 , Path.Direction.CW);



	}
	private void drawInit()
	{

	}
	private void drawUI()
	{

		mCanvas.drawColor(Color.BLACK);
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Paint.Style.STROKE);


		if (!mIsAutoPlay)
		{

			mCanvas.drawPath(mAutoPlayPath, mPaint);
			

		}
		else
		{

			mCanvas.drawPath(mNotAutoPlayPath, mPaint);
			mCanvas.drawCircle(mLeftButtonPointCenter.x, mLeftButtonPointCenter.y, mLeftButtonSize, mPaint);
			mCanvas.drawCircle(mRightButtonPointCenter.x, mRightButtonPointCenter.y, mRightButtonSize, mPaint);


		}
		mCanvas.drawPath(mSmallMapPath, mPaint);
		mCanvas.drawPath(mAutoPlayButtonPath, mPaint);



	}
	private void render()
	{
		mCanvas = mSurfaceHolder.lockCanvas();
		drawUI();
		try
		{
			if (mCanvas != null)
			{
				mCanvas.save();
				drawInit();
				drawUI();
				if (mGameIsContinue)
				{
					if (true)
					{

					}
				}

			}
		}
		catch (Exception e)
		{

		}
		finally
		{
			if (mCanvas != null)
			{
				mSurfaceHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}
	private void AI()
	{}



}
