package com.jbf.ylyg.MODEL;

public class ParsePassward
{

	private String step1_change2numbers(String a)
	{
		/*
		 加密原理:根据下面矩阵变换数字，然后使用用它除以质数，从2开始逐渐除以下一个，但最后剩4个数位置，最后面两位加上次数
		 //01234567
		 0  jAiBhCg
		 1 SkTzUyDf
		 2 Rl49VxEe
		 3 Qm38WwFd
		 4 Pn27XvGc
		 5 Oo16YuHb
		 6 Np05ZtIa
		 7 MqLrKsJ
		 */
		String temp="3";
		for (int i=0;i <= a.length() - 1;i++)
		{
			switch (a.charAt(i))
			{
				case 'A':temp = temp + "02";break;
				case 'a':temp = temp + "67";break;
				case 'B':temp = temp + "04";break;
				case 'b':temp = temp + "57";break;
				case 'C':temp = temp + "06";break;
				case 'c':temp = temp + "47";break;
				case 'D':temp = temp + "16";break;
				case 'd':temp = temp + "37";break;
				case 'E':temp = temp + "26";break;
				case 'e':temp = temp + "27";break;
				case 'F':temp = temp + "36";break;
				case 'f':temp = temp + "17";break;
				case 'G':temp = temp + "46";break;
				case 'g':temp = temp + "07";break;
				case 'H':temp = temp + "56";break;
				case 'h':temp = temp + "05";break;
				case 'I':temp = temp + "66";break;
				case 'i':temp = temp + "03";break;
				case 'J':temp = temp + "76";break;
				case 'j':temp = temp + "01";break;
				case 'K':temp = temp + "74";break;
				case 'k':temp = temp + "11";break;
				case 'L':temp = temp + "72";break;
				case 'l':temp = temp + "21";break;
				case 'M':temp = temp + "70";break;
				case 'm':temp = temp + "31";break;
				case 'N':temp = temp + "60";break;
				case 'n':temp = temp + "41";break;
				case 'O':temp = temp + "50";break;
				case 'o':temp = temp + "51";break;
				case 'P':temp = temp + "40";break;
				case 'p':temp = temp + "61";break;
				case 'Q':temp = temp + "30";break;
				case 'q':temp = temp + "71";break;
				case 'R':temp = temp + "20";break;
				case 'r':temp = temp + "73";break;
				case 'S':temp = temp + "10";break;
				case 's':temp = temp + "75";break;
				case 'T':temp = temp + "12";break;
				case 't':temp = temp + "65";break;
				case 'U':temp = temp + "14";break;
				case 'u':temp = temp + "55";break;
				case 'V':temp = temp + "24";break;
				case 'v':temp = temp + "45";break;
				case 'W':temp = temp + "34";break;
				case 'w':temp = temp + "35";break;
				case 'X':temp = temp + "44";break;
				case 'x':temp = temp + "25";break;
				case 'Y':temp = temp + "54";break;
				case 'y':temp = temp + "15";break;
				case 'Z':temp = temp + "64";break;
				case 'z':temp = temp + "13";break;
				case '0':temp = temp + "62";break;
				case '1':temp = temp + "52";break;
				case '2':temp = temp + "42";break;
				case '3':temp = temp + "32";break;
				case '4':temp = temp + "22";break;
				case '5':temp = temp + "63";break;
				case '6':temp = temp + "53";break;
				case '7':temp = temp + "43";break;
				case '8':temp = temp + "33";break;
				case '9':temp = temp + "23";break;
			}
		}
		//  Toast toast=Toast.makeText(getApplicationContext(),Integer.toString(m),2000);
		return temp;
	}

	private String step2_changelength(String a)
	{//返回22位数字
		String outputString="";
		if (a.length() == 22)
		{
			return a;
		}
		if (a.length() > 22)
		{
			String temp1=a.substring(21);
			String temp2=a.substring(0, 21);
			char temp[]=temp2.toCharArray();
			boolean upflag=false;     
			for (int i=0;i < temp1.length();i++)
			{
				int change=temp1.charAt(temp1.length() - 1 - i) - '0' + temp2.charAt(temp2.length() - 1 - i) - '0';
				if (upflag)
				{
					change += 1;
					upflag = false;
				}
				if (change > 9)
				{
					upflag = true;
					change = change - 10;
				}
				temp[temp2.length() - i - 1] = (char)(change + 48);
			}
			if (upflag)
			{
				temp[temp2.length() - temp1.length()] ++;
				upflag = false;
			}
			outputString = String.valueOf(temp);
		}
		if (a.length() < 22)
		{
			int key[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
			outputString = a;
			int length=outputString.length();
			int i=0;
			while (outputString.length() < 21)
			{
				int temp=0;
				temp = outputString.charAt(length - 1 - i) - '0' + key[i];
				while (temp > 9)
				{
					if (temp >= 10)
					{
						temp = temp % 10 + temp / 10;
					}
				}
				i++;
				if (i >= 24)
				{
					i = 0;
				}
				outputString += temp;
			}
		}
		return outputString;
	}
	private String step3_change2string(String a)
	{
		//数字除以62，取余数匹配字母跟数字
		String back_string="";
		String temp_string="";
		temp_string = a;
		while (temp_string.length() > 2)
		{


			while (!temp_string.equals("0"))
			{
				String changing="";
				int x=0;
				int y=0;
				while (temp_string.length() > 2)
				{
					int temp_int=(temp_string.charAt(0) - '0') * 100
						+ (temp_string.charAt(1) - '0') * 10
						+ (temp_string.charAt(2) - '0');
					x = temp_int / 62;
					y = temp_int % 62;
					String temp=String.valueOf(y) + temp_string.substring(3);
					changing = changing + String.valueOf(x);
					temp_string = temp;
				}
				if (Integer.parseInt(temp_string) > 61)
				{
					changing += "1";
					back_string += number2char(String.valueOf(Integer.parseInt(temp_string) - 62));
				}
				else
				{
					changing += "0";
					back_string += number2char(String.valueOf(Integer.parseInt(temp_string)));
				}
				temp_string = changing;
			}
		}
		return back_string;
	}
	private String number2char(String a)
	{

		switch (Integer.valueOf(a))
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:return a;
			case 10:return "A";
			case 11:return "a";
			case 12:return "b";
			case 13:return "B";
			case 14:return "C";
			case 15:return "c";
			case 16:return "d";
			case 17:return "D";
			case 18:return "E";
			case 19:return "e";
			case 20:return "F";
			case 21:return "f";
			case 22:return "g";
			case 23:return "G";
			case 24:return "H";
			case 25:return "h";
			case 26:return "i";
			case 27:return "I";
			case 28:return "J";
			case 29:return "j";
			case 58:return "K";
			case 59:return "k";
			case 30:return "l";
			case 31:return "L";
			case 32:return "M";
			case 33:return "m";
			case 34:return "n";
			case 35:return "N";
			case 36:return "O";
			case 37:return "o";
			case 38:return "p";
			case 39:return "P";
			case 40:return "Q";
			case 41:return "q";
			case 42:return "r";
			case 43:return "R";
			case 44:return "S";
			case 45:return "s";
			case 46:return "t";
			case 47:return "T";
			case 48:return "U";
			case 49:return "u";
			case 50:return "v";
			case 51:return "V";
			case 52:return "w";
			case 53:return "W";
			case 54:return "X";
			case 55:return "x";
			case 56:return "y";
			case 57:return "Y";
			case 60:return "Z";
			case 61:return "z";
			default:return null;
		}

	}
	public String getkey(String a)
	{
		return (step3_change2string(step2_changelength(step1_change2numbers(a))));
	}
}

