package com.jbf.ylyg.MODEL;
import android.graphics.*;
import java.util.*;
import android.animation.*;

public class pp
{
	
		private Canvas mCanvas=new Canvas();
		private Paint mPaint=new Paint();

		private PointF mMapPoint=new PointF();
		private PointF mScreenPoint=new PointF();
		private float mRotate=0;
		private int mSpeed=150;
		private boolean sShot=true;
		private String test="player";
		private int lv,exp,money;
		private Equipment gun;
		private Equipment dizuo;
		private Equipment soul;
		private Equipment baohuzhao;
		private Equipment driver;
		private Equipment turning;
		private Equipment seeking;
		private Equipment power;
		private Equipment hello1,hello2;
		private Path mPath;
		private Path mDrawPath;







		public pp()
		{

			this.mRotate = 0;
			this.mPaint = new Paint();
			this.mMapPoint = new PointF();
			this.mScreenPoint = new PointF();
			mPaint.setColor(Color.BLACK);
			this.mScreenPoint.set(500, 500);//测试用
			sShot = false;
			mPath = new Path();
			mPath.addCircle(200, 300, 150, Path.Direction.CW);

			mDrawPath = new Path();
			setRandomPath();

		}

		public void setMMapPoint(PointF mMapPoint)
		{
			this.mMapPoint = mMapPoint;
		}

		public PointF getMMapPoint()
		{
			return mMapPoint;
		}

		public void setMScreenPoint(PointF mScreenPoint)
		{

			this.mScreenPoint = mScreenPoint;
		}

		public PointF getMScreenPoint()
		{
			return mScreenPoint;
		}
		public void setMPain(Paint mPaint)
		{
			this.mPaint = mPaint;
		}
		public void setMRotate(int mRotate)
		{
			this.mRotate = mRotate;
		}
		public void draw(Canvas canvas)
		{
			mCanvas = canvas;
			mPaint.setTextSize(70);
			mPaint.setColor(Color.WHITE);

			//mCanvas.save();
			//mCanvas.rotate(mScreenPoint.x,mScreenPoint.y,mRotate);
			//mCanvas.drawText(test, mScreenPoint.x, mScreenPoint.y, mPaint);
			mCanvas.drawText(test, mCurrentPosition[0], mCurrentPosition[1], mPaint);
			//mCanvas.restore();

		}
		public void setMove(double x, double y, double rotate)
		{

			this.mRotate = (int)rotate;
			this.mMapPoint.x += x * mSpeed / 30;
			this.mMapPoint.y += y * mSpeed / 30;
			this.mScreenPoint.x = (float)x * mSpeed / 30 + this.mScreenPoint.x;
			this.mScreenPoint.y = (float)y * mSpeed / 30 + this.mScreenPoint.y;


		}
		public void Shoot()
		{


		}


		//根据路径mPath制定行走路径
		//设置行走路径
		public void setPath(Path path)
		{
			mPath.reset();
			mPath = path;
		}
		//生成随机路径
		private Random mRandom=new Random();
		public void setRandomPath()
		{

			int i=mRandom.nextInt(9);
			mPath.reset();
			mPath.moveTo(500,500);
			switch (i)
			{

				case 0:
					{

						mPath.lineTo(mRandom.nextFloat(), mRandom.nextFloat());
						break;
					}
				case 1:
					{
						break;

					}
				default :
					{
						break;
					}
			}
		}
		//
		private float[] mCurrentPosition=new float[2];
		private PathMeasure mPathMeasure;
		private ValueAnimator mValueAnimator;
		private Animator mAnimator;
		private void PathInit()
		{
			setRandomPath();
			mPathMeasure=new PathMeasure(mPath,true);
			mValueAnimator=ValueAnimator.ofFloat(0,mPathMeasure.getLength());
			mValueAnimator.setDuration((long)(mPathMeasure.getLength()/(float)mSpeed));
			mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
				{

					@Override
					public void onAnimationUpdate(ValueAnimator animotar)
					{
						// TODO: Implement this method
						float value=(float)animotar.getAnimatedValue();
						mPathMeasure.getPosTan(value,mCurrentPosition,null);
					}






				});

		}

	





	
	
	
	
	
}
