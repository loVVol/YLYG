package com.jbf.ylyg.UI;
import android.app.*;
import android.view.*;
import android.os.*;
import com.jbf.ylyg.MODEL.FightSurfaceView;

public class FightActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new FightSurfaceView(this));
	}
}
