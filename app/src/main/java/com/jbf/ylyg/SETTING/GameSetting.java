package com.jbf.ylyg.SETTING;
import android.os.*;

public class GameSetting
{

	public static final int gMinLengthOfRegister=6;
	public static final int gMaxLengthOfRegister=20;
	public static final int gMinLengthOfPassword=6;
	public static final int gMaxlengthOfPassword=20;
	public static final String gDBaddress=Environment.getExternalStorageDirectory().getPath() + "/GAME_BASE.db";
	public static final String gUSERDBaddress=Environment.getExternalStorageDirectory().getPath() + "/GAME_USER.db";

	//用户数据库
	public static class userdb_name
	{
		public static final String USER="user_table";
		//public static final String USER_ITEM="user_item_table";
		public static final String USER_PET="user_pet_table";
		public static final String USER_EQUIPMENT="user_equipment_table";
		public static final String AUTO_SAVE="auto_save_table";


		//user_item_table
//		public static class user_item_table
//		{
//			public static final String _ID="id";
//			public static final String _NUMBER="number";
//
//		}

		
		//user_equipment_table
		public static class user_equipment_table
		{
			public static final String _ID="id";
		}
		//pet_table
		public static class user_pet_table
		{
			public static final String _ID="id";
			public static final String _NAME="name";
			public static final String _DRAW_ID="draw_id";
			public static final String _ATT_BASE="att_base";
			public static final String _ATT_UP="att_up";
		}
		//user_table设置
		public static class user_table
		{
			public static final String USERNAME="username";
			public static final String PASSWORD="password";
			public static final String IFNEWGAME="ifnewgame";
			public static final String FSTARTTIME="fstarttime";
			public static final String LEAVETIME="leavetime";
			public static final String _HP_BASE="hp_base";
			public static final String _MP_BASE="mp_base";
			public static final String _HP_UP="hp_up";
			public static final String _MP_UP="mp_up";
			public static final String _LV="lv";
			public static final String _EXP_ALL="exp_all";
			public static final String _EXP="exp";

			public static final String _ATT_BASE="";
			public static final String _ATT_UP="";
			public static final String _ATT_POINT="";
			public static final String _DEF_BASE="";
			public static final String _DEF_UP="";
			public static final String _POINT="";
			public static final String _ATT="";
			//	public static final String _="";
			//public static final String _="";
			public static final String _SPEED="speed";
			public static final String _MONEY="money";
			public static final String _VIP_MONEY="vip_money";
			public static final String _NAME="name";
			public static final String _ID="id";
		}
	}
	//游戏基本数据

	public static class basedb_name
	{
		public static final String EQUIPMENT="Equipment_table";
		public static final String MAP_BASE="Map_table";
		public static final String ENEMY_BASE="Enemy_table";
		public static final String DRAW_BASE="Draw_table";
		public static final String BOSS_BASE="Boss_table";
		//item_table设置
		public static class Equipment_table
		{
			public static final String _ID="id";
			public static final String _NAME="name";
			public static final String _DRAW_ID="draw_id";
			public static final String _DESCRIPTION="description";
			public static final String _DROP_="drop";
			public static final String _PRICE="price";



		}
//enemey_table
		public static class Enemey_table
		{
			public static final String _ID="id";
			public static final String _NAME="name";
			public static final String _DRAW_ID="draw_id";
			public static final String _DROW_POINT="drow_point";
			public static final String _LV="lv";
			public static final String _HP_BASE="hp_base";
			public static final String _MP_BASE="mp_base";
			public static final String _CRITE_POINT="crite_point";
			public static final String _ATT="att";
			public static final String _DEF="def";
			public static final String _SPEED="speed";
			public static final String _ATT_M="att_m";
			public static final String _DEF_M="def_m";
			public static final String _EXP="exp";
			public static final String _MONEY="money";
			public static final String _VIP_MONEY="vip_money";
			public static final String _="";
			//public static final String _="";

		}
		//map_table
		public static class Map_table
		{
			public static final String _ID="id";
			public static final String _NAME="name";
			public static final String _LENGTH="length";
			public static final String _WEIGTH="weigth";
			public static final String _MAXENEMY="max_enemy";
			public static final String _NUMOFBOSS="numofboss";
		}
		//Draw_table
		public static class Draw_table
		{
			public static final String _ID="id";
			public static final String _NAME="name";
			public static final String _UP="up_id";
			public static final String _DRAW="draw_box";
		}



	}








	//equipment_table
	public static class equipment_table
	{
		public static final String _ID="id";
		public static final String _NAME="name";
		public static final String _DRAW_ID="draw_id";
		public static final String _WEAR="wear";
	}

	//auto_save_table(可能不启用)
	public static class auto_save_table
	{
		public static final String _ID="id";
		public static final String _NAME="name";
		public static final String _DRAW_ID="draw_id";
	}

	public static class newstart
	{
		public static final int hp=500;
		public static final int hp_up=20;
		public static final float hp_point=1.2f;
		//hp_now=hp+hp_point*lv*lv+lv*hp_up

	}


	public static int att_min=10;
	public static int att_max=20;
	public static int attspeed_min=50;
	public static int attspeed_max=100;
	//数据库保存地址

	public static class player
	{

	}


}
