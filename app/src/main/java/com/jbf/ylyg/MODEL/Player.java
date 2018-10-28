package com.jbf.ylyg.MODEL;
import android.graphics.*;

public class Player
{
	private Canvas mCanvas;
	private PointF mMapPoint;
	private PointF mScreenPoint;
	private Paint mPaint;
	private float mRotate;
	private int mSpeed=150;
	public Player()
	{
		
		this.mRotate=0;
		this.mPaint=new Paint();
		this.mMapPoint=new PointF();
		this.mScreenPoint=new PointF();
		//
		this.mScreenPoint.set(500,500);//测试用
		//
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
		this.mPaint=mPaint;
	}
	public void setMRotate(int mRotate)
	{
		this.mRotate=mRotate;
	}
	public void draw(Canvas canvas)
	{
		mCanvas=canvas;
		mPaint.setTextSize(70);
		mPaint.setColor(Color.WHITE);
		
		//mCanvas.save();
		//mCanvas.rotate(mScreenPoint.x,mScreenPoint.y,mRotate);
		mCanvas.drawText("我是player",mScreenPoint.x,mScreenPoint.y,mPaint);
		//mCanvas.restore();
		
	}
	public void setMove(double x,double y,double rotate)
	{
		this.mRotate=(int)rotate;
		this.mMapPoint.x+=x*mSpeed/30;
		this.mMapPoint.y+=y*mSpeed/30;
		this.mScreenPoint.x=(float)x*mSpeed/30+this.mScreenPoint.x;
		this.mScreenPoint.y=(float)y*mSpeed/30+this.mScreenPoint.y;
		
		
	}
}
