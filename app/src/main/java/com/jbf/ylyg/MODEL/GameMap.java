package com.jbf.ylyg.MODEL;

public class GameMap
{
	private int weight;
	private int height;
	private float a;
	private int gametype;
	private float drop;
	private float f;
	private int NumberOfDaluandou;

	public void setNumberOfDaluandou(int numberOfDaluandou)
	{
		NumberOfDaluandou = numberOfDaluandou;
	}

	public int getNumberOfDaluandou()
	{
		return NumberOfDaluandou;
	}
	

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}

	public void setA(float a)
	{
		this.a = a;
	}

	public float getA()
	{
		return a;
	}

	public void setGametype(int gametype)
	{
		this.gametype = gametype;
	}

	public int getGametype()
	{
		return gametype;
	}

	public void setDrop(float drop)
	{
		this.drop = drop;
	}

	public float getDrop()
	{
		return drop;
	}

	public void setF(float f)
	{
		this.f = f;
	}

	public float getF()
	{
		return f;
	}
	class gametype
	{
		static final int nomal=1;
		static final int daluandou=2;
		static final int one2one=3;
	}
}
