package com.jbf.ylyg.MODEL;


import android.graphics.Path;
import android.graphics.PointF;

public class ParsePath
{
	
//	安卓开发 path画图
//	出处：http://m.blog.csdn.net/tianjian4592/article/details/44733123
//
//	根据图形路径的复杂度，生成的path数据复杂度也不一样，但格式也算是非常的清楚，即采用一定的指令把数据点进行拼接；
//	现在有了这些数据点，我们需要做的则是对数据进行解析，封装成我们要的Path；
//	解析的过程也无非是 遇到指令则采用android Path 里的对应方法进行置换，解析方式如下：
//	String mPathString;
//	PointF mCurrentFirstPoint;
//	int mIndex;
//	int mLength;
//	public Path parsePath(String s) throws ParseException {
//        mCurrentFirstPoint.set(Float.NaN, Float.NaN);
//        mPathString = s;
//        mIndex = 0;
//        mLength = mPathString.length();
//
//        PointF tempPoint1 = new PointF();
//        PointF tempPoint2 = new PointF();
//        PointF tempPoint3 = new PointF();
//
//        Path p = new Path();
//        p.setFillType(Path.FillType.WINDING);
//
//        boolean firstMove = true;
//        while (mIndex < mLength) {
//            char command = consumeCommand();
//            boolean relative = (mCurrentToken == TOKEN_RELATIVE_COMMAND);
//            switch (command) {
//                case 'M':
//                case 'm': {
//						// m指令，相当于android 里的 moveTo()
//						boolean firstPoint = true;
//						while (advanceToNextToken() == TOKEN_VALUE) {
//							consumeAndTransformPoint(tempPoint1,
//													 relative && mCurrentFirstPoint.x != Float.NaN);
//							if (firstPoint) {
//								p.moveTo(tempPoint1.x, tempPoint1.y);
//								firstPoint = false;
//								if (firstMove) {
//									mCurrentFirstPoint.set(tempPoint1);
//									firstMove = false;
//								}
//							} else {
//								p.lineTo(tempPoint1.x, tempPoint1.y);
//							}
//						}
//						mCurrentFirstPoint.set(tempPoint1);
//						break;
//					}
//
//                case 'C':
//                case 'c': {
//						// c指令，相当于android 里的 cubicTo()
//						if (mCurrentFirstPoint.x == Float.NaN) {
//							throw new ParseException("Relative commands require current point", mIndex);
//						}
//
//						while (advanceToNextToken() == TOKEN_VALUE) {
//							consumeAndTransformPoint(tempPoint1, relative);
//							consumeAndTransformPoint(tempPoint2, relative);
//							consumeAndTransformPoint(tempPoint3, relative);
//							p.cubicTo(tempPoint1.x, tempPoint1.y, tempPoint2.x, tempPoint2.y,
//									  tempPoint3.x, tempPoint3.y);
//						}
//						mCurrentFirstPoint.set(tempPoint3);
//						break;
//					}
//
//                case 'L':
//                case 'l': {
//						// 相当于lineTo()进行画直线
//						if (mCurrentFirstPoint.x == Float.NaN) {
//							throw new ParseException("Relative commands require current point", mIndex);
//						}
//
//						while (advanceToNextToken() == TOKEN_VALUE) {
//							consumeAndTransformPoint(tempPoint1, relative);
//							p.lineTo(tempPoint1.x, tempPoint1.y);
//						}
//						mCurrentFirstPoint.set(tempPoint1);
//						break;
//					}
//
//                case 'H':
//                case 'h': {
//						// 画水平直线
//						if (mCurrentFirstPoint.x == Float.NaN) {
//							throw new ParseException("Relative commands require current point", mIndex);
//						}
//
//						while (advanceToNextToken() == TOKEN_VALUE) {
//							float x = transformX(consumeValue());
//							if (relative) {
//								x += mCurrentFirstPoint.x;
//							}
//							p.lineTo(x, mCurrentFirstPoint.y);
//						}
//						mCurrentFirstPoint.set(tempPoint1);
//						break;
//					}
//
//                case 'V':
//                case 'v': {
//						// 画竖直直线
//						if (mCurrentFirstPoint.x == Float.NaN) {
//							throw new ParseException("Relative commands require current point", mIndex);
//						}
//
//						while (advanceToNextToken() == TOKEN_VALUE) {
//							float y = transformY(consumeValue());
//							if (relative) {
//								y += mCurrentFirstPoint.y;
//							}
//							p.lineTo(mCurrentFirstPoint.x, y);
//						}
//						mCurrentFirstPoint.set(tempPoint1);
//						break;
//					}
//
//                case 'Z':
//                case 'z': {
//						// 封闭path
//						p.close();
//						break;
//					}
//            }
//
//        }
//
//        return p;
//    }
//	有了图形对应的path，我们只需要按照我们想要的效果进行绘制即可，具体过程不再细讲，大家看代码：
//	@Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (mState == STATE_NOT_STARTED || mGlyphData == null) {
//            return;
//        }
//
//        long t = System.currentTimeMillis() - mStartTime;
//
//        // 绘制出现前的边沿线和跑动过程
//        for (int i = 0; i < mGlyphData.length; i++) {
//            float phase = MathUtil.constrain(0, 1,
//											 (t - (mTraceTime - mTraceTimePerGlyph) * i * 1f / mGlyphData.length)
//											 * 1f / mTraceTimePerGlyph);
//            float distance = INTERPOLATOR.getInterpolation(phase) * mGlyphData[i].length;
//            mGlyphData[i].paint.setColor(mTraceResidueColors[i]);
//            mGlyphData[i].paint.setPathEffect(new DashPathEffect(
//												  new float[] {
//													  distance, mGlyphData[i].length
//												  }, 0));
//            canvas.drawPath(mGlyphData[i].path, mGlyphData[i].paint);
//
//            mGlyphData[i].paint.setColor(mTraceColors[i]);
//            mGlyphData[i].paint.setPathEffect(new DashPathEffect(
//												  new float[] {
//													  0, distance, phase > 0 ? mMarkerLength : 0,
//													  mGlyphData[i].length
//												  }, 0));
//            canvas.drawPath(mGlyphData[i].path, mGlyphData[i].paint);
//        }
//
//        if (t > mFillStart) {
//            if (mState < STATE_FILL_STARTED) {
//                changeState(STATE_FILL_STARTED);
//            }
//
//            // 绘制渐变出现的过程，即改变alpha过程
//            float phase = MathUtil.constrain(0, 1, (t - mFillStart) * 1f / mFillTime);
//            for (int i = 0; i < mGlyphData.length; i++) {
//                GlyphData glyphData = mGlyphData[i];
//                mFillPaint.setARGB((int) (phase * ((float) mFillAlphas[i] / (float) 255) * 255),
//								   mFillReds[i],
//								   mFillGreens[i],
//								   mFillBlues[i]);
//                canvas.drawPath(glyphData.path, mFillPaint);
//            }
//        }
//
//        if (t < mFillStart + mFillTime) {
//            ViewCompat.postInvalidateOnAnimation(this);
//        } else {
//            changeState(STATE_FINISHED);
//        }
//    }
	String mStringPath;
	PointF mCurrentFirstPoint=new PointF();
	PointF mCurrentSecondPoint=new PointF();
	PointF tempPoint1=new PointF();
	PointF tempPoint2=new PointF();
	PointF tempPoint3=new PointF();
	PointF contilPoint1=new PointF();
	PointF contilPoint2=new PointF();
	int mIndex;
	int mLengthOfs;
	Path path=new Path();
	char FirstCode;
	char CurrentCode;
	private PointF place;
	private int realSize;
	private int MapSize;
	public Path parsePath(PointF _point, String s, int realsize, int mapsize)
	{
		this.mStringPath = s;
		this.place = _point;
		this.realSize = realsize;
		this.MapSize = mapsize;
		//this.path.reset();
		mCurrentFirstPoint.set(0f, 0f);
		mCurrentSecondPoint.set(0f, 0f);
		mIndex = 0;
		mLengthOfs = s.length();

		do
		//while (mIndex < mLengthOfs)
		{
			boolean FlagOfCode=false;
			FirstCode = CurrentCode;
			while (!FlagOfCode)
			{
				if (IsCode() || IsNumber())       
				{
					FirstCode = CurrentCode;
					if (IsCode())
					{
						CurrentCode = mStringPath.charAt(mIndex);
					}

					FlagOfCode = true;
				}
				else
				{
					mIndex++;
				}
			}
			switch (CurrentCode)
			{
				case 'M':
				case 'm':
					{
						mCurrentSecondPoint.set(ParsePointF());
						path.moveTo(mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'L':
				case 'l':
					{
						mCurrentSecondPoint.set(ParsePointF());
						path.lineTo(mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'H':
				case 'h':
					{
						mCurrentSecondPoint.set(ParsePointF());
						path.lineTo(mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'V':
				case 'v':
					{
						mCurrentSecondPoint.set(ParsePointF());
						path.lineTo(mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'S':
				case 's':
					{
						if ((FirstCode == 's') || (FirstCode == 'S') || (FirstCode == 'C') || (FirstCode == 'c'))
						{
							contilPoint1.set((2 * mCurrentSecondPoint.x - contilPoint2.x), (2 * mCurrentSecondPoint.y - contilPoint2.y));
							contilPoint2.set(ParsePointF());
						}
						else
						{
							contilPoint1.set(ParsePointF());
							contilPoint2.set(contilPoint1);
						}
						mCurrentSecondPoint.set(ParsePointF());
						path.cubicTo(contilPoint1.x, contilPoint1.y, contilPoint2.x, contilPoint2.y, mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'C':
				case 'c':
					{
						contilPoint1.set(ParsePointF());
						contilPoint2.set(ParsePointF());
						mCurrentSecondPoint.set(ParsePointF());
						path.cubicTo(contilPoint1.x, contilPoint1.y, contilPoint2.x, contilPoint2.y, mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'T':
				case 't':
					{
						if ((FirstCode == 'q') || (FirstCode == 'Q') || (FirstCode == 't') || (FirstCode == 'T'))
						{
							contilPoint2.set((2 * mCurrentSecondPoint.x - contilPoint2.x), (2 * mCurrentSecondPoint.y - contilPoint2.y));
							mCurrentSecondPoint.set(ParsePointF());
							path.quadTo(contilPoint2.x, contilPoint2.y, mCurrentSecondPoint.x, mCurrentSecondPoint.y);

						}
						else
						{
							mCurrentSecondPoint.set(ParsePointF());
							path.lineTo(mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						}
						break;
					}
				case 'Q':
				case 'q':
					{
						contilPoint2.set(ParsePointF());
						mCurrentSecondPoint.set(ParsePointF());
						path.quadTo(contilPoint2.x, contilPoint2.y, mCurrentSecondPoint.x, mCurrentSecondPoint.y);
						break;
					}
				case 'Z':
				case 'z':
					{
						path.close();
						mIndex++;
						break;
					}
				case 'A':
				case 'a':
					{

						//						tempPoint1.set(ParsePointF());
						//						mIndex++;
						//						int aaaa=Integer.valueOf(mStringPath.charAt(mIndex++));
						//						mIndex++;
						//						mCurrentFirstPoint.set(ParsePointF());
						break;
					}
				default:
					{
						mIndex++;
						break;
					}
			}
		}while(mIndex < mLengthOfs);
		return path;
	}
	private PointF ParsePointF()
	{
		float scale=(float)MapSize / (float)realSize;

		PointF pointf=new PointF();
		pointf.set(0f, 0f);
		int _h=2;
		int _v=0;
		if (CurrentCode == 'H')
		{
			_h = 1;
			pointf.y = mCurrentSecondPoint.y;
		}
		if (CurrentCode == 'h')
		{
			_h = 1;
			pointf.y = mCurrentSecondPoint.y + place.y;
		}

		if (CurrentCode == 'V')
		{
			_v = 1;
			pointf.x = mCurrentSecondPoint.x;
		}
		if (CurrentCode == 'v') 
		{
			_v = 1;
			pointf.x = mCurrentSecondPoint.x + place.x;
		}
		for (int i=_v;i < _h;i++)
		{
			float XY=0f;
			Boolean flag=true;
			String str="";
			char mchar;
			while (!IsNumber())
			{
				mIndex++;	
			}
			while (flag)
			{
				mchar = mStringPath.charAt(mIndex);
				if (IsNumber())
				{
					str += mchar;
					mIndex++;
				}
				else
				{
					flag = false;
				}
			}
			XY = Float.parseFloat(str);

			if (i == 0)
			{
				if (IsLittleCode())
				{
					float yy=0f;
					yy = XY + mCurrentSecondPoint.x;
					XY = yy;
				}
				pointf.x = XY * scale + place.x;
			}
			if (i == 1)
			{
				if (IsLittleCode())
				{
					float yy=0f;
					yy = XY + mCurrentSecondPoint.y;
					XY = yy;
				}
				pointf.y = XY * scale + place.y;
			}
		}
		return pointf;
	}
	private boolean IsNumber()
	{
		char checkchar=mStringPath.charAt(mIndex);
		if ((checkchar >= '0') && (checkchar <= '9') || (checkchar == '.') || (checkchar == '-'))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean IsCode()
	{
		char checkchar=mStringPath.charAt(mIndex);
		if (((checkchar >= 'A') && (checkchar <= 'Z')) || ((checkchar >= 'a') && (checkchar <= 'z')))
		{
			return true;
		}
		else
		{                                                   
			return false;
		}
	}
	private boolean IsLittleCode()
	{
		char checkchar=CurrentCode;
		if ((checkchar >= 'a') && (checkchar <= 'z'))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
}
