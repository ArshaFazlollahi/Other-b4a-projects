package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _a = "";
public static int _bp = 0;
public static String _b = "";
public static String _txt = "";
public anywheresoftware.b4a.objects.EditTextWrapper _et = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_snd = null;
public anywheresoftware.b4a.objects.EditTextWrapper _et_n = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
public anywheresoftware.b4a.objects.IntentWrapper _intent1 = null;
public static int _t = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _et_r = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_set = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_set2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _et_b = null;
public static String _td = "";
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"Home\")";
mostCurrent._activity.LoadLayout("Home",mostCurrent.activityBA);
 //BA.debugLineNum = 49;BA.debugLine="et_b.Visible=False";
mostCurrent._et_b.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 50;BA.debugLine="btn_set.Visible=False";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 51;BA.debugLine="btn_set2.Visible=False";
mostCurrent._btn_set2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 52;BA.debugLine="Try";
try { //BA.debugLineNum = 53;BA.debugLine="td= File.ReadString(File.DirRootExternal,\"spam/b";
mostCurrent._td = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn1.txt");
 } 
       catch (Exception e8) {
			processBA.setLastException(e8); //BA.debugLineNum = 55;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 57;BA.debugLine="btn_1.Text=td";
mostCurrent._btn_1.setText(BA.ObjectToCharSequence(mostCurrent._td));
 //BA.debugLineNum = 58;BA.debugLine="Try";
try { //BA.debugLineNum = 59;BA.debugLine="td= File.ReadString(File.DirRootExternal,\"spam/b";
mostCurrent._td = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn2.txt");
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 61;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 63;BA.debugLine="btn_2.Text=td";
mostCurrent._btn_2.setText(BA.ObjectToCharSequence(mostCurrent._td));
 //BA.debugLineNum = 64;BA.debugLine="Try";
try { //BA.debugLineNum = 65;BA.debugLine="td= File.ReadString(File.DirRootExternal,\"spam/b";
mostCurrent._td = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn3.txt");
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); //BA.debugLineNum = 67;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 69;BA.debugLine="btn_3.Text=td";
mostCurrent._btn_3.setText(BA.ObjectToCharSequence(mostCurrent._td));
 //BA.debugLineNum = 70;BA.debugLine="Try";
try { //BA.debugLineNum = 71;BA.debugLine="td= File.ReadString(File.DirRootExternal,\"spam/b";
mostCurrent._td = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn4.txt");
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 73;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 75;BA.debugLine="btn_4.Text=td";
mostCurrent._btn_4.setText(BA.ObjectToCharSequence(mostCurrent._td));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _btn_1_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 148;BA.debugLine="Sub btn_1_Click";
 //BA.debugLineNum = 149;BA.debugLine="txt=btn_1.Text";
mostCurrent._txt = mostCurrent._btn_1.getText();
 //BA.debugLineNum = 150;BA.debugLine="t=et_n.Text";
_t = (int)(Double.parseDouble(mostCurrent._et_n.getText()));
 //BA.debugLineNum = 151;BA.debugLine="a=et_r.Text";
mostCurrent._a = mostCurrent._et_r.getText();
 //BA.debugLineNum = 152;BA.debugLine="For i = 1 To t";
{
final int step4 = 1;
final int limit4 = _t;
_i = (int) (1) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 153;BA.debugLine="Intent1.Initialize(Intent1.ACTION_VIEW, $\"https:";
mostCurrent._intent1.Initialize(mostCurrent._intent1.ACTION_VIEW,("https://api.whatsapp.com/send?phone="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._a))+"&text="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt))+""));
 //BA.debugLineNum = 154;BA.debugLine="StartActivity(Intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._intent1.getObject()));
 }
};
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _btn_1_longclick() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub btn_1_LongClick";
 //BA.debugLineNum = 159;BA.debugLine="et_b.Visible=True";
mostCurrent._et_b.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 160;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 161;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 162;BA.debugLine="bp=1";
_bp = (int) (1);
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _btn_2_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 131;BA.debugLine="Sub btn_2_Click";
 //BA.debugLineNum = 132;BA.debugLine="txt=btn_2.Text";
mostCurrent._txt = mostCurrent._btn_2.getText();
 //BA.debugLineNum = 133;BA.debugLine="t=et_n.Text";
_t = (int)(Double.parseDouble(mostCurrent._et_n.getText()));
 //BA.debugLineNum = 134;BA.debugLine="a=et_r.Text";
mostCurrent._a = mostCurrent._et_r.getText();
 //BA.debugLineNum = 135;BA.debugLine="For i = 1 To t";
{
final int step4 = 1;
final int limit4 = _t;
_i = (int) (1) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 136;BA.debugLine="Intent1.Initialize(Intent1.ACTION_VIEW, $\"https:";
mostCurrent._intent1.Initialize(mostCurrent._intent1.ACTION_VIEW,("https://api.whatsapp.com/send?phone="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._a))+"&text="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt))+""));
 //BA.debugLineNum = 137;BA.debugLine="StartActivity(Intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._intent1.getObject()));
 }
};
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public static String  _btn_2_longclick() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub btn_2_LongClick";
 //BA.debugLineNum = 142;BA.debugLine="et_b.Visible=True";
mostCurrent._et_b.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 143;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 144;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 145;BA.debugLine="bp=2";
_bp = (int) (2);
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _btn_3_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 114;BA.debugLine="Sub btn_3_Click";
 //BA.debugLineNum = 115;BA.debugLine="txt=btn_3.Text";
mostCurrent._txt = mostCurrent._btn_3.getText();
 //BA.debugLineNum = 116;BA.debugLine="t=et_n.Text";
_t = (int)(Double.parseDouble(mostCurrent._et_n.getText()));
 //BA.debugLineNum = 117;BA.debugLine="a=et_r.Text";
mostCurrent._a = mostCurrent._et_r.getText();
 //BA.debugLineNum = 118;BA.debugLine="For i = 1 To t";
{
final int step4 = 1;
final int limit4 = _t;
_i = (int) (1) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 119;BA.debugLine="Intent1.Initialize(Intent1.ACTION_VIEW, $\"https:";
mostCurrent._intent1.Initialize(mostCurrent._intent1.ACTION_VIEW,("https://api.whatsapp.com/send?phone="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._a))+"&text="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt))+""));
 //BA.debugLineNum = 120;BA.debugLine="StartActivity(Intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._intent1.getObject()));
 }
};
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _btn_3_longclick() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub btn_3_LongClick";
 //BA.debugLineNum = 125;BA.debugLine="et_b.Visible=True";
mostCurrent._et_b.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 126;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 127;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 128;BA.debugLine="bp=3";
_bp = (int) (3);
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public static String  _btn_4_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 97;BA.debugLine="Sub btn_4_Click";
 //BA.debugLineNum = 98;BA.debugLine="txt=btn_4.Text";
mostCurrent._txt = mostCurrent._btn_4.getText();
 //BA.debugLineNum = 99;BA.debugLine="t=et_n.Text";
_t = (int)(Double.parseDouble(mostCurrent._et_n.getText()));
 //BA.debugLineNum = 100;BA.debugLine="a=et_r.Text";
mostCurrent._a = mostCurrent._et_r.getText();
 //BA.debugLineNum = 101;BA.debugLine="For i = 1 To t";
{
final int step4 = 1;
final int limit4 = _t;
_i = (int) (1) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 102;BA.debugLine="Intent1.Initialize(Intent1.ACTION_VIEW, $\"https:";
mostCurrent._intent1.Initialize(mostCurrent._intent1.ACTION_VIEW,("https://api.whatsapp.com/send?phone="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._a))+"&text="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt))+""));
 //BA.debugLineNum = 103;BA.debugLine="StartActivity(Intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._intent1.getObject()));
 }
};
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _btn_4_longclick() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub btn_4_LongClick";
 //BA.debugLineNum = 108;BA.debugLine="et_b.Visible=True";
mostCurrent._et_b.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 109;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 110;BA.debugLine="btn_set.Visible=True";
mostCurrent._btn_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 111;BA.debugLine="bp=4";
_bp = (int) (4);
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _btn_set_click() throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub btn_set_Click";
 //BA.debugLineNum = 185;BA.debugLine="btn_set2_Click";
_btn_set2_click();
 //BA.debugLineNum = 186;BA.debugLine="If bp==1 Then";
if (_bp==1) { 
 //BA.debugLineNum = 187;BA.debugLine="btn_1.Text=et_b.Text";
mostCurrent._btn_1.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 188;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn1";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn1.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 190;BA.debugLine="If bp==2 Then";
if (_bp==2) { 
 //BA.debugLineNum = 191;BA.debugLine="btn_2.Text=et_b.Text";
mostCurrent._btn_2.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 192;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn2";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn2.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 194;BA.debugLine="If bp==3 Then";
if (_bp==3) { 
 //BA.debugLineNum = 195;BA.debugLine="btn_3.Text=et_b.Text";
mostCurrent._btn_3.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 196;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn3";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn3.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 198;BA.debugLine="If bp==4 Then";
if (_bp==4) { 
 //BA.debugLineNum = 199;BA.debugLine="btn_4.Text=et_b.Text";
mostCurrent._btn_4.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 200;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn4";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn4.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return "";
}
public static String  _btn_set2_click() throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub btn_set2_Click";
 //BA.debugLineNum = 166;BA.debugLine="If bp==1 Then";
if (_bp==1) { 
 //BA.debugLineNum = 167;BA.debugLine="btn_1.Text=et_b.Text";
mostCurrent._btn_1.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 168;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn1";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn1.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 170;BA.debugLine="If bp==2 Then";
if (_bp==2) { 
 //BA.debugLineNum = 171;BA.debugLine="btn_2.Text=et_b.Text";
mostCurrent._btn_2.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 172;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn2";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn2.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 174;BA.debugLine="If bp==3 Then";
if (_bp==3) { 
 //BA.debugLineNum = 175;BA.debugLine="btn_3.Text=et_b.Text";
mostCurrent._btn_3.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 176;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn3";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn3.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 178;BA.debugLine="If bp==4 Then";
if (_bp==4) { 
 //BA.debugLineNum = 179;BA.debugLine="btn_4.Text=et_b.Text";
mostCurrent._btn_4.setText(BA.ObjectToCharSequence(mostCurrent._et_b.getText()));
 //BA.debugLineNum = 180;BA.debugLine="File.WriteString(File.DirRootExternal,\"spam/btn4";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"spam/btn4.txt",mostCurrent._et_b.getText());
 };
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _btn_snd_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 87;BA.debugLine="Sub btn_snd_Click";
 //BA.debugLineNum = 88;BA.debugLine="txt=et.Text";
mostCurrent._txt = mostCurrent._et.getText();
 //BA.debugLineNum = 89;BA.debugLine="t=et_n.Text";
_t = (int)(Double.parseDouble(mostCurrent._et_n.getText()));
 //BA.debugLineNum = 90;BA.debugLine="a=et_r.Text";
mostCurrent._a = mostCurrent._et_r.getText();
 //BA.debugLineNum = 91;BA.debugLine="For i = 1 To t";
{
final int step4 = 1;
final int limit4 = _t;
_i = (int) (1) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 92;BA.debugLine="Intent1.Initialize(Intent1.ACTION_VIEW, $\"https:";
mostCurrent._intent1.Initialize(mostCurrent._intent1.ACTION_VIEW,("https://api.whatsapp.com/send?phone="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._a))+"&text="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt))+""));
 //BA.debugLineNum = 93;BA.debugLine="StartActivity(Intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._intent1.getObject()));
 }
};
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim a As String";
mostCurrent._a = "";
 //BA.debugLineNum = 25;BA.debugLine="Dim bp As Int";
_bp = 0;
 //BA.debugLineNum = 26;BA.debugLine="Dim b As String";
mostCurrent._b = "";
 //BA.debugLineNum = 27;BA.debugLine="Dim txt As String";
mostCurrent._txt = "";
 //BA.debugLineNum = 28;BA.debugLine="Private et As EditText";
mostCurrent._et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private btn_snd As Button";
mostCurrent._btn_snd = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private et_n As EditText";
mostCurrent._et_n = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private btn_1 As Button";
mostCurrent._btn_1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private btn_2 As Button";
mostCurrent._btn_2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private btn_3 As Button";
mostCurrent._btn_3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private btn_4 As Button";
mostCurrent._btn_4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lbl As Label";
mostCurrent._lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim Intent1 As Intent";
mostCurrent._intent1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim t As Int";
_t = 0;
 //BA.debugLineNum = 38;BA.debugLine="Private et_r As EditText";
mostCurrent._et_r = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lbl_1 As Label";
mostCurrent._lbl_1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private btn_set As Button";
mostCurrent._btn_set = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private btn_set2 As Button";
mostCurrent._btn_set2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private et_b As EditText";
mostCurrent._et_b = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim td As String";
mostCurrent._td = "";
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
