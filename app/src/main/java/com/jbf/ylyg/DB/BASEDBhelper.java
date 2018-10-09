package com.jbf.ylyg.DB;
import android.database.sqlite.*;
import android.content.*;
import com.jbf.ylyg.SETTING.*;
//包含几个不会更改的基本数据库，分别为
//“地图MAP_BASE”，“装备EQUIPMENT_BASE”，
//“图画DRAW_BASE”，“头领BOSS_BASE”，
//“怪物ENEMY_BASE”，

	public class BASEDBhelper extends SQLiteOpenHelper
	{
		private static final int VERSION=1;
		private static String dbname=GameSetting.DBaddress;
		BASEDBhelper(Context context)
		{
			super(context, dbname, null, VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{//基本游戏数据
			//地图
			//序号,地图名,地图等级,掉宝率,地面阻力,经验倍率,金钱倍率,同时出现敌人数,总敌人数,BOSS序号
			String strSQL_user = "create table MAP_BASE"
				+ "(tid integer primary key autoincrement,"
				+ "MAPNAME text,"
				+"MAP_LV integer,"
				+ "ITEM_DROP integer,"
				+ "MAP_DRAG integer,"
				+ "MAP_EXP_COEFFICIENT integer,"
				+ "MAP_MONEY_COEFFICIENT integer,"
				+ "MAP_ENEMY_AMOUNT_OUT integer,"
				+ "MAP_ENEMY text,"
				+ "MAP_BOSS_NUMBER integer,";
				
			//画图
			//序号,上层序号,图画
			String strSQL_user_item = "create table "
				+ GameSetting.userdb_name.USER_ITEM
				+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";

			String strSQL_user_pat= "create table "
				+ GameSetting.userdb_name.USER_PAT
				+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";

			String strSQL_user_equit = "create table "
				+ GameSetting.userdb_name.USER_EQUIPMENT
				+ "(tid integer primary key autoincrement,username text,password varchar(40),start_init boolean)";
//			战斗中自动保存
//			
			
			
			String strSQL_autosave="create table "
				+ GameSetting.userdb_name.AUTO_SAVE
				+ "(tid integer primary key autoincrement,ID integer)";
			db.execSQL(strSQL_user);
			db.execSQL(strSQL_user_pat);
			db.execSQL(strSQL_user_equit);
			db.execSQL(strSQL_user_item);
			db.execSQL(strSQL_autosave);
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
