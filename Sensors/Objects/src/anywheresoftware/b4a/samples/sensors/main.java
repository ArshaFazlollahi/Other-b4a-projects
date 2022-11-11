package anywheresoftware.b4a.samples.sensors;


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
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "anywheresoftware.b4a.samples.sensors", "anywheresoftware.b4a.samples.sensors.main");
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
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
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
		activityBA = new BA(this, layout, processBA, "anywheresoftware.b4a.samples.sensors", "anywheresoftware.b4a.samples.sensors.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "anywheresoftware.b4a.samples.sensors.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
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
public static anywheresoftware.b4a.objects.collections.Map _sensorsmap = null;
public anywheresoftware.b4a.objects.collections.Map _sensorslabels = null;
public anywheresoftware.b4a.samples.sensors.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static class _sensordata{
public boolean IsInitialized;
public String Name;
public boolean ThreeValues;
public void Initialize() {
IsInitialized = true;
Name = "";
ThreeValues = false;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneSensors _ps = null;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 26;BA.debugLine="SensorsMap.Initialize";
_sensorsmap.Initialize();
 //BA.debugLineNum = 27;BA.debugLine="Dim ps As PhoneSensors 'This object is only used";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 36;BA.debugLine="AddSensor(18,\"STEP DETECTOR\",False)";
_addsensor((int) (18),"STEP DETECTOR",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 37;BA.debugLine="AddSensor(19,\"STEP COUNTER\",False)";
_addsensor((int) (19),"STEP COUNTER",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 39;BA.debugLine="SensorsLabels.Initialize 'SensorsLabels is not a";
mostCurrent._sensorslabels.Initialize();
 //BA.debugLineNum = 41;BA.debugLine="For i = 0 To SensorsMap.Size - 1";
{
final int step8 = 1;
final int limit8 = (int) (_sensorsmap.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 42;BA.debugLine="Dim ps As PhoneSensors";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 43;BA.debugLine="ps = SensorsMap.GetKeyAt(i)";
_ps = (anywheresoftware.b4a.phone.Phone.PhoneSensors)(_sensorsmap.GetKeyAt(_i));
 //BA.debugLineNum = 44;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 46;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 47;BA.debugLine="Activity.AddView(lbl, 10dip, 10dip + 50dip * i,";
mostCurrent._activity.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))*_i),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 48;BA.debugLine="SensorsLabels.Put(ps, lbl)";
mostCurrent._sensorslabels.Put((Object)(_ps),(Object)(_lbl.getObject()));
 }
};
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
int _i = 0;
anywheresoftware.b4a.phone.Phone.PhoneSensors _ps = null;
 //BA.debugLineNum = 83;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 85;BA.debugLine="For i = 0 To SensorsMap.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_sensorsmap.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 86;BA.debugLine="Dim ps As PhoneSensors";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 87;BA.debugLine="ps = SensorsMap.GetKeyAt(i)";
_ps = (anywheresoftware.b4a.phone.Phone.PhoneSensors)(_sensorsmap.GetKeyAt(_i));
 //BA.debugLineNum = 88;BA.debugLine="ps.StopListening";
_ps.StopListening(processBA);
 }
};
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _i = 0;
anywheresoftware.b4a.phone.Phone.PhoneSensors _ps = null;
anywheresoftware.b4a.samples.sensors.main._sensordata _sd = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 68;BA.debugLine="For i = 0 To SensorsMap.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_sensorsmap.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 69;BA.debugLine="Dim ps As PhoneSensors";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 70;BA.debugLine="Dim sd As SensorData";
_sd = new anywheresoftware.b4a.samples.sensors.main._sensordata();
 //BA.debugLineNum = 71;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="ps = SensorsMap.GetKeyAt(i)";
_ps = (anywheresoftware.b4a.phone.Phone.PhoneSensors)(_sensorsmap.GetKeyAt(_i));
 //BA.debugLineNum = 73;BA.debugLine="sd = SensorsMap.GetValueAt(i)";
_sd = (anywheresoftware.b4a.samples.sensors.main._sensordata)(_sensorsmap.GetValueAt(_i));
 //BA.debugLineNum = 74;BA.debugLine="lbl = SensorsLabels.Get(ps)";
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(mostCurrent._sensorslabels.Get((Object)(_ps))));
 //BA.debugLineNum = 77;BA.debugLine="If ps.StartListening(\"Sensor\") = False Then";
if (_ps.StartListening(processBA,"Sensor")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 78;BA.debugLine="lbl.Text = sd.Name & \" is not supported.\"";
_lbl.setText(BA.ObjectToCharSequence(_sd.Name /*String*/ +" is not supported."));
 };
 }
};
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _addsensor(int _sensortype,String _name,boolean _threevalues) throws Exception{
anywheresoftware.b4a.samples.sensors.main._sensordata _sd = null;
anywheresoftware.b4a.phone.Phone.PhoneSensors _ps = null;
 //BA.debugLineNum = 53;BA.debugLine="Sub AddSensor(SensorType As Int, Name As String, T";
 //BA.debugLineNum = 54;BA.debugLine="Dim sd As SensorData";
_sd = new anywheresoftware.b4a.samples.sensors.main._sensordata();
 //BA.debugLineNum = 55;BA.debugLine="sd.Initialize";
_sd.Initialize();
 //BA.debugLineNum = 56;BA.debugLine="sd.Name = Name";
_sd.Name /*String*/  = _name;
 //BA.debugLineNum = 57;BA.debugLine="sd.ThreeValues = ThreeValues";
_sd.ThreeValues /*boolean*/  = _threevalues;
 //BA.debugLineNum = 58;BA.debugLine="Dim ps As PhoneSensors";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 59;BA.debugLine="ps.Initialize(SensorType)";
_ps.Initialize(_sensortype);
 //BA.debugLineNum = 60;BA.debugLine="SensorsMap.Put(ps, sd)";
_sensorsmap.Put((Object)(_ps),(Object)(_sd));
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim SensorsLabels As Map";
mostCurrent._sensorslabels = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 13;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim SensorsMap As Map";
_sensorsmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 16;BA.debugLine="Type SensorData (Name As String, ThreeValues As B";
;
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _sensor_sensorchanged(float[] _values) throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneSensors _ps = null;
anywheresoftware.b4a.samples.sensors.main._sensordata _sd = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 93;BA.debugLine="Sub Sensor_SensorChanged (Values() As Float)";
 //BA.debugLineNum = 94;BA.debugLine="Dim ps As PhoneSensors";
_ps = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 95;BA.debugLine="Dim sd As SensorData";
_sd = new anywheresoftware.b4a.samples.sensors.main._sensordata();
 //BA.debugLineNum = 96;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 98;BA.debugLine="ps = Sender";
_ps = (anywheresoftware.b4a.phone.Phone.PhoneSensors)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA));
 //BA.debugLineNum = 99;BA.debugLine="sd = SensorsMap.Get(ps) 'Get the associated Senso";
_sd = (anywheresoftware.b4a.samples.sensors.main._sensordata)(_sensorsmap.Get((Object)(_ps)));
 //BA.debugLineNum = 100;BA.debugLine="lbl = SensorsLabels.Get(ps) 'Get the associated L";
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(mostCurrent._sensorslabels.Get((Object)(_ps))));
 //BA.debugLineNum = 102;BA.debugLine="If sd.ThreeValues Then";
if (_sd.ThreeValues /*boolean*/ ) { 
 //BA.debugLineNum = 103;BA.debugLine="lbl.Text = sd.Name & \" X=\" & NumberFormat(Values";
_lbl.setText(BA.ObjectToCharSequence(_sd.Name /*String*/ +" X="+anywheresoftware.b4a.keywords.Common.NumberFormat(_values[(int) (0)],(int) (0),(int) (3))+", Y="+anywheresoftware.b4a.keywords.Common.NumberFormat(_values[(int) (1)],(int) (0),(int) (3))+", Z="+anywheresoftware.b4a.keywords.Common.NumberFormat(_values[(int) (2)],(int) (0),(int) (3))));
 }else {
 //BA.debugLineNum = 106;BA.debugLine="lbl.Text = sd.Name & \" = \" & NumberFormat(Values";
_lbl.setText(BA.ObjectToCharSequence(_sd.Name /*String*/ +" = "+anywheresoftware.b4a.keywords.Common.NumberFormat(_values[(int) (0)],(int) (0),(int) (3))));
 };
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
}
