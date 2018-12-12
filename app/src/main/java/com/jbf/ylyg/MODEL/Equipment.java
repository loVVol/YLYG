package com.jbf.ylyg.MODEL;
import com.jbf.ylyg.SETTING.Special_Date;

public class Equipment
{
	private float hp,mp;
	private float att,att_mp;
	private float heavy,totalheavy;
	private float shotspeed,speed;
	private float seekrorato,seeksize;
	private float roratoofbody,roratoofseek;
	private int lv,exp,money;
	private String name;
	private Special_Date special1,Special2,special3,special4;
	public Equipment()
	{
		hp=mp=0;
		att=att_mp=0;
		heavy=totalheavy=0;
		shotspeed=speed=0;
		seeksize=seekrorato=0;
		roratoofbody=roratoofseek=0;
		lv=exp=money=0;
		special1=new Special_Date();
		Special2=new Special_Date();
		special3=new Special_Date();
		special4=new Special_Date();
	}
	
	
}
