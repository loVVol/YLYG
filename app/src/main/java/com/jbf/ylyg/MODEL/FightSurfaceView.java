package com.jbf.ylyg.MODEL;
import android.view.*;
import android.view.Window.*;
import android.content.Context;
import android.graphics.*;
import java.util.*;
import android.util.*;
import android.app.*;
import android.text.method.*;
import android.widget.*;
import com.jbf.ylyg.UI.*;

public class FightSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable
{

	
	
 	/**每30帧刷新一次屏幕**/
	public static final int TIME_IN_FRAME = 60;
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

	private PointF mLeftButtonPointCenter;
	private PointF mLeftButtonPointSmallCenter;
	private PointF mRightButtonPointCenter;
	private PointF mAutoPlayButtonPointCenter;
	private PointF mSmallMapPointCenter;
	private Path mAutoPlayButtonPath;
	private Path mAutoPlayPath;
	private Path mNotAutoPlayPath;
	private Path mSmallMapPath;
	private int mLeftButtonSize;
	private int mLeftButtonSmallSize;
	private int mRightButtonSize;
	private int mSmallMapSize;
	private int mAutoPlayButtonSize;
	private Player mPlayer;
	Canvas mCanvas;
	Canvas mplayerCanvas;

	public String testa=null;
	public String testb=null;


	public FightSurfaceView(Context context)
	{
		
		super(context);
		
		initEverything();
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
		mSmallMapPointCenter = new PointF();
		mAutoPlayButtonPointCenter = new PointF();
		mLeftButtonPointCenter = new PointF();
		mRightButtonPointCenter = new PointF();
		mLeftButtonPointSmallCenter = new PointF();
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
		mLeftButtonSmallSize = mLeftButtonSize / 3;
		mRightButtonSize = x * 3 / 32;
		mAutoPlayButtonSize = x / 64;
		mSmallMapSize = x / 16;
		mLeftButtonPointCenter.set(x * 3 / 100 + mLeftButtonSize, y - x * 2 / 100 - y / 100 - mLeftButtonSize);
		mLeftButtonPointSmallCenter.set(x * 3 / 100 + mLeftButtonSize, y - x * 2 / 100 - y / 100 - mLeftButtonSize);
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
		mNotAutoPlayPath.addRect(x / 100, y / 100, x * 99 / 100, y * 99 / 100 , Path.Direction.CW);
		mPlayer = new Player();


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


			/**取得更新之前的时间**/
			long startTime = System.currentTimeMillis();

			/**在这里加上线程安全锁**/
			synchronized (mSurfaceHolder)
			{

				AI();
				render();
			}
			/**取得更新结束的时间**/
			long endTime = System.currentTimeMillis();

			/**计算出一次更新的毫秒数**/
			int diffTime  = (int)(endTime - startTime);

			/**确保每次更新时间为30帧**/
			while (diffTime <= 1000 / TIME_IN_FRAME)
			{
				diffTime = (int)(System.currentTimeMillis() - startTime);
				/**线程等待**/
				Thread.yield();

			}
			// TODO: Implement this method
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		//boolean mLeftButtonDown;

		float xf=event.getX();
		float yf=event.getY();
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				{
					if (Math.pow((xf - mLeftButtonPointCenter.x), 2)
						+ Math.pow((yf - mLeftButtonPointCenter.y), 2)
						<= Math.pow((mLeftButtonSize * 2), 2))
					{
						mIfLeftButtonDown = true;
						setLeftButtonDown(xf, yf);

					}
					if(Math.pow((xf - mLeftButtonPointCenter.x), 2)
					   + Math.pow((yf - mLeftButtonPointCenter.y), 2)
					   <= Math.pow((mLeftButtonSize * 2), 2))
					   {
						   
					   }
					break;

				}
			case MotionEvent.ACTION_POINTER_DOWN:
				{

				}

			case MotionEvent.ACTION_MOVE:
				{
					if (mIfLeftButtonDown)
					{
						setLeftButtonDown(xf, yf);
					}
					break;
				}
			case MotionEvent.ACTION_POINTER_UP:
				{

				}
			case MotionEvent.ACTION_UP:
				{
					mIfLeftButtonDown = false;
					mLeftButtonPointSmallCenter.set(mLeftButtonPointCenter.x, mLeftButtonPointCenter.y);
					break;
				}
			case MotionEvent.ACTION_CANCEL:
				{

				}
		}
		
		return true;
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

			mCanvas.drawCircle(mLeftButtonPointSmallCenter.x, mLeftButtonPointSmallCenter.y, mLeftButtonSmallSize, mPaint);
			mPaint.setTextSize(30);
			if (testa != null && testb != null)
			{


				mCanvas.drawText(testa, 50, 50, mPaint);
				mCanvas.drawText(testb, 50, 80, mPaint);
			}

		}
		mCanvas.drawPath(mSmallMapPath, mPaint);
		mCanvas.drawPath(mAutoPlayButtonPath, mPaint);
		mPlayer.draw(mCanvas);


	}
	private void render()
	{
		mCanvas = mSurfaceHolder.lockCanvas();
		drawUI();
		try
		{
			if (mCanvas != null)
			{
				//mCanvas.save();
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
	{
		if(mIfLeftButtonDown){
			double a= (mLeftButtonPointSmallCenter.x - mLeftButtonPointCenter.x) / mLeftButtonSize;
			double b=(mLeftButtonPointSmallCenter.y - mLeftButtonPointCenter.y) / mLeftButtonSize;
			double c=Math.atan(mLeftButtonPointSmallCenter.y / mLeftButtonPointSmallCenter.x);
			mPlayer.setMove(a, b, c);
			//testa = String.valueOf(xf);
			//testb = String.valueOf(yf);
			}
	}

	private void setLeftButtonDown(float xf, float yf)
	{
		if (Math.pow((xf - mLeftButtonPointCenter.x), 2)
			+ Math.pow((yf - mLeftButtonPointCenter.y), 2)
			< Math.pow((mLeftButtonSize), 2))
		{
			mLeftButtonPointSmallCenter.set((int)xf, (int)yf);

		}
		else
		{
			if (xf == mLeftButtonPointCenter.x)
			{
				if (yf > mLeftButtonPointCenter.y)
				{
					mLeftButtonPointSmallCenter.set(mLeftButtonPointCenter.x, mLeftButtonPointCenter.y + mLeftButtonSize);
				}
				else
				{
					mLeftButtonPointSmallCenter.set(mLeftButtonPointCenter.x, mLeftButtonPointCenter.y - mLeftButtonSize);
				}
			}
			else if (yf == mLeftButtonPointCenter.y)
			{
				if (xf > mLeftButtonPointCenter.x)
				{
					mLeftButtonPointSmallCenter.set(mLeftButtonPointCenter.x + mLeftButtonSize, mLeftButtonPointCenter.y);
				}
				else
				{
					mLeftButtonPointSmallCenter.set(mLeftButtonPointCenter.x - mLeftButtonSize, mLeftButtonPointCenter.y);
				}
			}
			else 
			{
				double ddx=Math.sqrt(Math.pow(mLeftButtonSize, 2) / (1 + Math.pow((yf - mLeftButtonPointCenter.y) / (xf - mLeftButtonPointCenter.x), 2)));
				double x=0;
				if (xf > mLeftButtonPointCenter.x)
				{
					x = mLeftButtonPointCenter.x + ddx;
				}
				else
				{
					x = mLeftButtonPointCenter.x - ddx;
				}
				double y=mLeftButtonPointCenter.y + (yf - mLeftButtonPointCenter.y) * (x - mLeftButtonPointCenter.x) / (xf - mLeftButtonPointCenter.x);
				mLeftButtonPointSmallCenter.set((int)x, (int)y);
			}

			
		}
		double mmm=Math.sqrt(Math.pow((mLeftButtonPointSmallCenter.x - mLeftButtonPointCenter.x), 2) + Math.pow((mLeftButtonPointSmallCenter.y - mLeftButtonPointCenter.y), 2)) / mLeftButtonSize;
		
	}


}
