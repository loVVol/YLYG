package com.jbf.ylyg.DB;
import android.database.sqlite.*;
import com.jbf.ylyg.SETTING.*;
import android.content.*;

public class USERDBhelper extends SQLiteOpenHelper
{
	
	private static final int VERSION=1;
	//private static String dbname="game.db";
	private static String dbname=GameSetting.gDBaddress;

	USERDBhelper(Context context)
	{
		super(context, dbname, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{//用户数据
		//序号，用户名，用户密码,用户验证码，是否新建，创建时间，最终离线时间，名字，等级，经验，金钱，血量，能量，载重，视野，升级潜质，金钱潜质，血量潜质，能量潜质，载重潜质，视野潜质,已玩关卡
//		String strSQL_user = "create table "
//			+ GameSetting.userdb_name.USER
//			+ "(tid integer primary key autoincrement,"
//			+ GameSetting.user_table.USERNAME + " text,"
//			+ GameSetting.user_table._ID+ "integer,"
//			+ GameSetting.user_table._EXP + " long,"
//			+ GameSetting.user_table._HP_BASE + " long,"
//			+ GameSetting.user_table._LV + " integer,"
//			+ GameSetting.user_table._MONEY + " long,"
//			+ GameSetting.user_table._MP_BASE+ " long,"
//			+ GameSetting.user_table._NAME + " text,"
//			+ GameSetting.user_table._SPEED + " int,"
//			+ GameSetting.user_table._VIP_MONEY + " long,"
//			+ GameSetting.user_table.FSTARTTIME + " text,"
//			+ GameSetting.user_table.LEAVETIME + " text,"
//			+ GameSetting.user_table._ID + " int,"
//			+ GameSetting.user_table.IFNEWGAME + " boolean)";
//
//		String strSQL_user_item = "create table "
//			+ GameSetting.userdb_name.USER_ITEM
//			+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";
//
//		String strSQL_user_pat= "create table "
//			+ GameSetting.userdb_name.USER_PAT
//			+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";
//
		String strSQL_user_equit = "create table "
			+ GameSetting.userdb_name.USER_EQUIPMENT
			+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";
//			战斗中自动保存
//			


		String strSQL_autosave="create table "
			+ GameSetting.userdb_name.AUTO_SAVE
			+ "(tid integer primary key autoincrement,ID integer)";
//		db.execSQL(strSQL_user);
//		db.execSQL(strSQL_user_pat);
//		db.execSQL(strSQL_user_equit);
//		db.execSQL(strSQL_user_item);
//		db.execSQL(strSQL_autosave);
		// TODO: Implement this method
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		if (p2 == 1 && p3 == 2)
		{//版本1升级版本2

		}
		// TODO: Implement this method
	}
	
}
