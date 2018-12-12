package com.jbf.ylyg.MODEL;


import android.graphics.*;
import android.view.*;
import android.view.View.*;
import android.content.*;
import java.util.*;
import android.app.*;
import android.animation.*;

/*
 玩家模块，自动转向，自动射击，自动移动
 */

public class Personal
{
	//about draw
	private Canvas mCanvas;
	private Paint mPaint;
	private float mRotate,mRotateGun;
	private float mapX,mapY,screenX,screenY;

	//about map
	private float aX,aY,vX,vY,fX,fY;
	private int TypeOfMap;
	private int screenweight,screenheight;

	//about self
	private float att,attP,def,defP,speed,speedS,heavy,heavyA;
	private int LV,exp,money;
	private Path mPathOfDizuo,mPathOfGun,mPathOfHP,mPathOfMp,mPathOfBaohuzhao;

	//about others
	private boolean sShot;
	private Equipment gun;
	private Equipment dizuo;
	private Equipment soul;
	private Equipment baohuzhao;
	private Equipment driver;
	private Equipment turning;
	private Equipment seeking;
	private Equipment power;
	private Equipment hello1,hello2;
	private Path mMovePath;
	private boolean mIfIsPlayer;
	private int mKindOfEmerny;
	private GameMap mGameMap;
	private Random mRandom=new Random();
	private SharedPreferences mSP;

	//about path
	private Path mPath;







	public Personal(GameMap gamemap, boolean ifisplayer)
	{
		mCanvas = new Canvas();
		mPathOfDizuo = new Path();
		mPathOfGun = new Path();
		mPathOfBaohuzhao = new Path();
		mPathOfHP = new Path();
		mPathOfMp = new Path();
		mPathOfDizuo = new Path();
		this.mRotate = 0;
		this.mPaint = new Paint();
		sShot = false;
		setRandomPath();
		testinit();
		mGameMap = gamemap;
		mIfIsPlayer = ifisplayer;
		screenheight = mSP.getInt("screenheight", 1080);
		screenweight = mSP.getInt("screenweight", 1920);
		if (mIfIsPlayer)
		{
			screenX = screenweight / 2;
			screenY = screenheight / 2;
			mapX = screenweight / 2;
			mapY = screenheight / 2;
			mKindOfEmerny = 0;
		}
		else
		{
			mapX = mRandom.nextInt(mGameMap.getWeight());
			mapY = mRandom.nextInt(mGameMap.getHeight());
			screenX = -1000;
			screenY = -1000;
			switch (mGameMap.getGametype())
			{

				case GameMap.gametype.one2one:
				case GameMap.gametype.nomal:
					{
						mKindOfEmerny = 1;
						break;
					}
				case GameMap.gametype.daluandou:
					{
						mKindOfEmerny = mRandom.nextInt(mGameMap.getNumberOfDaluandou());
						break;
					}
				default:
					break;
			}
		}
		fX=0;fY=0;aX=0;aY=0;vX=0;vY=0;

	}

	public void setmKindOfEmeny(int i)
	{
		mKindOfEmerny = i;
	}
	public int getmKindOfEmeny()
	{
		return mKindOfEmerny;
	}
	private void testinit()
	{
		//draw dizuo
		mPathOfDizuo.setFillType(Path.FillType.INVERSE_WINDING);
		mPathOfDizuo.addCircle(0, 0, 200, Path.Direction.CW);
		mPathOfGun.setFillType(Path.FillType.INVERSE_WINDING);
		mPathOfGun.addRect(-10, -10, 50, 50, Path.Direction.CW);
		mPathOfBaohuzhao.setFillType(Path.FillType.INVERSE_WINDING);
		mPathOfBaohuzhao.addCircle(0, 0, 250, Path.Direction.CW);




	}
	public void draw(Canvas canvas)
	{
		mCanvas = canvas;
		mPaint.setTextSize(70);
		mPaint.setColor(Color.WHITE);
		canvas.save();
		canvas.translate(mapX, mapY);
		canvas.drawPath(mPathOfDizuo, mPaint);
		canvas.drawPath(mPathOfBaohuzhao, mPaint);
		canvas.rotate(mRotateGun);
		canvas.drawPath(mPathOfGun, mPaint);
		canvas.restore();

		//mCanvas.save();
		//mCanvas.rotate(mScreenPoint.x,mScreenPoint.y,mRotate);
		//mCanvas.drawText(test, mScreenPoint.x, mScreenPoint.y, mPaint);

		//mCanvas.restore();

	}
//	public void setMove(double x, double y, double rotate)
//	{
//
//		this.mRotate = (int)rotate;
//		this.mMapPoint.x += x * mSpeed / 30;
//		this.mMapPoint.y += y * mSpeed / 30;
//		this.mScreenPoint.x = (float)x * mSpeed / 30 + this.mScreenPoint.x;
//		this.mScreenPoint.y = (float)y * mSpeed / 30 + this.mScreenPoint.y;
//
//
//	}
	public void Shoot()
	{


	}



	//根据路径mPath制定行走路径
	//设置行走路径
	public void setPath(float fx, float fy)
	{
		if((Math.pow(fx,2)+Math.pow(fy,2))>Math.pow( mGameMap.getF(),2))
		{
			fX=mGameMap.getF()*fx/(float)(Math.sqrt(Math.pow(fx,2)+Math.pow(fy,2)));
			fY=mGameMap.getF()*fy/(float)(Math.sqrt(Math.pow(fx,2)+Math.pow(fy,2)));
		}
		else
		{
			fX=fx;
			fY=fy;
		}
		aX=fX/heavy;
		aY=fY/heavy;

		mPath.reset();
		//mPath = path;
	}
	//生成随机路径

	public void setRandomPath()
	{

		int i=mRandom.nextInt(9);
		mPath.reset();
		mPath.moveTo(mapY, mapY);
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
	private Animator manimator;
	private void PathInit()
	{
		setRandomPath();
		mPathMeasure = new PathMeasure(mPath, true);
		mValueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
		mValueAnimator.setDuration((long)(mPathMeasure.getLength() / speed));
		mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
			{

				@Override
				public void onAnimationUpdate(ValueAnimator animotar)
				{
					// TODO: Implement this method
					float value=(float)animotar.getAnimatedValue();
					mPathMeasure.getPosTan(value, mCurrentPosition, null);
				}






			});

	}

	public void update()
	{
		vX+=aX;
		vY+=aY;
		speed=
	}
	
}






