package com.jbf.ylyg.SETTING;
import android.graphics.*;

public class Bullet_Data
{
	private PointF mMapPointF;
	private PointF mScreenPointF;
	private boolean IfAlived;
	private PointF mStartPointF;
	private PointF mEndPointF;
	private int mSpeed;
	private int mRotate;
	private int mAtt;
	private int mAtt_power;
	public Bullet_Data()
	{
		mAtt = 0;
		mAtt_power=0;
		this.IfAlived = true;
		mSpeed=0;
		mEndPointF.set(0,0);
		mScreenPointF.set(0,0);
		mStartPointF.set(0,0);
		mMapPointF.set(0,0);
		mRotate=0;
	}

	
}
