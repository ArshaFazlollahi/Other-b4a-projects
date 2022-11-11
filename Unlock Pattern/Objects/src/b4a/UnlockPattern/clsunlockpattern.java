package b4a.UnlockPattern;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clsunlockpattern extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.UnlockPattern.clsunlockpattern");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.UnlockPattern.clsunlockpattern.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _mtarget = null;
public String _meventname = "";
public anywheresoftware.b4a.objects.PanelWrapper _holder = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlbuttons = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btncancel = null;
public int _mbutton = 0;
public int _btnheight = 0;
public int _btnwidth = 0;
public int _btnspace = 0;
public int _lblheight = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public String _strtitle = "";
public int _inttextsize = 0;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmiddle = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvsx = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmiddlex = null;
public b4a.UnlockPattern.clsunlockpattern._center[] _point = null;
public float _radius = 0f;
public String _pointselected = "";
public int _lastpoint = 0;
public int _pointlen = 0;
public int _pnlcolor = 0;
public int _linecolor = 0;
public int _clearcolor = 0;
public b4a.UnlockPattern.main _main = null;
public static class _center{
public boolean IsInitialized;
public float x;
public float y;
public void Initialize() {
IsInitialized = true;
x = 0f;
y = 0f;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public String  _addview(anywheresoftware.b4a.objects.ActivityWrapper _parent,int _left,int _top,int _width,int _height) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gdw = null;
flm.b4a.gesturedetector.GestureDetectorForB4A _gd = null;
b4a.UnlockPattern.clsunlockpattern._center _centerpoint = null;
float _mindim = 0f;
float _offset = 0f;
int _i = 0;
int _factorx = 0;
int _factory = 0;
 //BA.debugLineNum = 79;BA.debugLine="Public Sub AddView(Parent As Activity, Left As Int";
 //BA.debugLineNum = 81;BA.debugLine="holder.Initialize(\"holder\")";
_holder.Initialize(ba,"holder");
 //BA.debugLineNum = 82;BA.debugLine="Hide_holder";
_hide_holder();
 //BA.debugLineNum = 83;BA.debugLine="holder.Color = Colors.ARGB(102, 0, 0, 0)";
_holder.setColor(__c.Colors.ARGB((int) (102),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 84;BA.debugLine="Parent.AddView(holder, 0, 0, 100%x, 100%y)";
_parent.AddView((android.view.View)(_holder.getObject()),(int) (0),(int) (0),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 86;BA.debugLine="Dim gdw As GradientDrawable";
_gdw = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 87;BA.debugLine="gdw.Initialize(\"LEFT_RIGHT\", Array As Int(Colors.";
_gdw.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"LEFT_RIGHT"),new int[]{__c.Colors.White,__c.Colors.Gray,__c.Colors.White});
 //BA.debugLineNum = 89;BA.debugLine="If PointLen = 0 And mButton = 0 Then";
if (_pointlen==0 && _mbutton==0) { 
 //BA.debugLineNum = 90;BA.debugLine="mButton = 1		' minimum button if the point is un";
_mbutton = (int) (1);
 };
 //BA.debugLineNum = 93;BA.debugLine="If PointLen = 10 Then";
if (_pointlen==10) { 
 //BA.debugLineNum = 94;BA.debugLine="mButton = 0		' no button is required, the Ok is";
_mbutton = (int) (0);
 };
 //BA.debugLineNum = 97;BA.debugLine="lblHeight = 50dip";
_lblheight = __c.DipToCurrent((int) (50));
 //BA.debugLineNum = 98;BA.debugLine="If mButton > 0 Then";
if (_mbutton>0) { 
 //BA.debugLineNum = 99;BA.debugLine="btnSpace = 1dip";
_btnspace = __c.DipToCurrent((int) (1));
 //BA.debugLineNum = 100;BA.debugLine="btnHeight = 50dip";
_btnheight = __c.DipToCurrent((int) (50));
 //BA.debugLineNum = 101;BA.debugLine="btnWidth = Width/mButton-btnSpace";
_btnwidth = (int) (_width/(double)_mbutton-_btnspace);
 };
 //BA.debugLineNum = 104;BA.debugLine="pnlMain.Initialize(\"pnlMain\")";
_pnlmain.Initialize(ba,"pnlMain");
 //BA.debugLineNum = 105;BA.debugLine="pnlMain.Color = Colors.RGB(248, 248, 255)";
_pnlmain.setColor(__c.Colors.RGB((int) (248),(int) (248),(int) (255)));
 //BA.debugLineNum = 107;BA.debugLine="holder.AddView(pnlMain, Left, Top, Width, Height";
_holder.AddView((android.view.View)(_pnlmain.getObject()),_left,_top,_width,(int) (_height+_btnheight+_lblheight+__c.DipToCurrent((int) (8))));
 //BA.debugLineNum = 109;BA.debugLine="If strTitle <> \"\" Then";
if ((_strtitle).equals("") == false) { 
 //BA.debugLineNum = 110;BA.debugLine="lblTitle.Initialize(\"\")";
_lbltitle.Initialize(ba,"");
 //BA.debugLineNum = 111;BA.debugLine="lblTitle.Color = Colors.RGB(51, 181, 229)";
_lbltitle.setColor(__c.Colors.RGB((int) (51),(int) (181),(int) (229)));
 //BA.debugLineNum = 112;BA.debugLine="lblTitle.TextSize = intTextSize";
_lbltitle.setTextSize((float) (_inttextsize));
 //BA.debugLineNum = 113;BA.debugLine="lblTitle.TextColor = Colors.Black";
_lbltitle.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 114;BA.debugLine="lblTitle.Text = strTitle";
_lbltitle.setText(BA.ObjectToCharSequence(_strtitle));
 //BA.debugLineNum = 115;BA.debugLine="lblTitle.Gravity = Gravity.CENTER_HORIZONTAL + G";
_lbltitle.setGravity((int) (__c.Gravity.CENTER_HORIZONTAL+__c.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 116;BA.debugLine="pnlMain.AddView(lblTitle, 0, 0, pnlMain.Width, l";
_pnlmain.AddView((android.view.View)(_lbltitle.getObject()),(int) (0),(int) (0),_pnlmain.getWidth(),_lblheight);
 }else {
 //BA.debugLineNum = 118;BA.debugLine="pnlMain.Height = pnlMain.Height - lblHeight";
_pnlmain.setHeight((int) (_pnlmain.getHeight()-_lblheight));
 //BA.debugLineNum = 119;BA.debugLine="lblHeight = 0";
_lblheight = (int) (0);
 };
 //BA.debugLineNum = 122;BA.debugLine="pnlMiddle.Initialize(\"\")";
_pnlmiddle.Initialize(ba,"");
 //BA.debugLineNum = 123;BA.debugLine="pnlMiddle.Color = PnlColor";
_pnlmiddle.setColor(_pnlcolor);
 //BA.debugLineNum = 124;BA.debugLine="pnlMain.AddView(pnlMiddle, 0, lblHeight + 4dip, p";
_pnlmain.AddView((android.view.View)(_pnlmiddle.getObject()),(int) (0),(int) (_lblheight+__c.DipToCurrent((int) (4))),_pnlmain.getWidth(),_height);
 //BA.debugLineNum = 125;BA.debugLine="pnlMiddleX.Initialize(\"\")";
_pnlmiddlex.Initialize(ba,"");
 //BA.debugLineNum = 126;BA.debugLine="pnlMain.AddView(pnlMiddleX, 0, lblHeight + 4dip,";
_pnlmain.AddView((android.view.View)(_pnlmiddlex.getObject()),(int) (0),(int) (_lblheight+__c.DipToCurrent((int) (4))),_pnlmain.getWidth(),_height);
 //BA.debugLineNum = 128;BA.debugLine="If mButton > 0 Then";
if (_mbutton>0) { 
 //BA.debugLineNum = 129;BA.debugLine="pnlButtons.Initialize(\"\")";
_pnlbuttons.Initialize(ba,"");
 //BA.debugLineNum = 130;BA.debugLine="pnlButtons.Background = gdw";
_pnlbuttons.setBackground((android.graphics.drawable.Drawable)(_gdw.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="pnlMain.AddView(pnlButtons, 0, pnlMain.Height -";
_pnlmain.AddView((android.view.View)(_pnlbuttons.getObject()),(int) (0),(int) (_pnlmain.getHeight()-_btnheight),_pnlmain.getWidth(),_btnheight);
 //BA.debugLineNum = 133;BA.debugLine="btnOk.Initialize(\"btnOK\")";
_btnok.Initialize(ba,"btnOK");
 //BA.debugLineNum = 134;BA.debugLine="btnOk.Text = \"O K\"";
_btnok.setText(BA.ObjectToCharSequence("O K"));
 //BA.debugLineNum = 135;BA.debugLine="btnOk.color = Colors.RGB(248, 248, 255)";
_btnok.setColor(__c.Colors.RGB((int) (248),(int) (248),(int) (255)));
 //BA.debugLineNum = 136;BA.debugLine="btnOk.TextSize = intTextSize";
_btnok.setTextSize((float) (_inttextsize));
 //BA.debugLineNum = 137;BA.debugLine="pnlButtons.AddView(btnOk, pnlButtons.Width - btn";
_pnlbuttons.AddView((android.view.View)(_btnok.getObject()),(int) (_pnlbuttons.getWidth()-_btnwidth),__c.DipToCurrent((int) (2)),_btnwidth,(int) (_btnheight-__c.DipToCurrent((int) (2))));
 //BA.debugLineNum = 139;BA.debugLine="If mButton = 2 Then";
if (_mbutton==2) { 
 //BA.debugLineNum = 140;BA.debugLine="btnCancel.Initialize(\"btnCancel\")";
_btncancel.Initialize(ba,"btnCancel");
 //BA.debugLineNum = 141;BA.debugLine="btnCancel.Text = \"Cancel\"";
_btncancel.setText(BA.ObjectToCharSequence("Cancel"));
 //BA.debugLineNum = 142;BA.debugLine="btnCancel.color = Colors.RGB(248, 248, 255)";
_btncancel.setColor(__c.Colors.RGB((int) (248),(int) (248),(int) (255)));
 //BA.debugLineNum = 143;BA.debugLine="btnCancel.TextSize = intTextSize";
_btncancel.setTextSize((float) (_inttextsize));
 //BA.debugLineNum = 144;BA.debugLine="pnlButtons.AddView(btnCancel, 0, 2dip, btnWidth";
_pnlbuttons.AddView((android.view.View)(_btncancel.getObject()),(int) (0),__c.DipToCurrent((int) (2)),_btnwidth,(int) (_btnheight-__c.DipToCurrent((int) (2))));
 };
 };
 //BA.debugLineNum = 148;BA.debugLine="Private GD As GestureDetector";
_gd = new flm.b4a.gesturedetector.GestureDetectorForB4A();
 //BA.debugLineNum = 149;BA.debugLine="GD.SetOnGestureListener(pnlMiddle, \"Gesture\")";
_gd.SetOnGestureListener(ba,(android.view.View)(_pnlmiddle.getObject()),"Gesture");
 //BA.debugLineNum = 150;BA.debugLine="GD.EnableLongPress(False)";
_gd.EnableLongPress(__c.False);
 //BA.debugLineNum = 152;BA.debugLine="Dim CenterPoint As Center";
_centerpoint = new b4a.UnlockPattern.clsunlockpattern._center();
 //BA.debugLineNum = 153;BA.debugLine="CenterPoint.Initialize";
_centerpoint.Initialize();
 //BA.debugLineNum = 155;BA.debugLine="Dim minDim As Float = Min(pnlMiddle.Width,pnlMidd";
_mindim = (float) (__c.Min(_pnlmiddle.getWidth(),_pnlmiddle.getHeight()));
 //BA.debugLineNum = 156;BA.debugLine="CenterPoint.x = .5 * pnlMiddle.Width";
_centerpoint.x = (float) (.5*_pnlmiddle.getWidth());
 //BA.debugLineNum = 157;BA.debugLine="CenterPoint.y = .5 * pnlMiddle.Height";
_centerpoint.y = (float) (.5*_pnlmiddle.getHeight());
 //BA.debugLineNum = 158;BA.debugLine="Dim Offset As Float = .3 * minDim";
_offset = (float) (.3*_mindim);
 //BA.debugLineNum = 159;BA.debugLine="Radius = .1 * minDim";
_radius = (float) (.1*_mindim);
 //BA.debugLineNum = 160;BA.debugLine="Dim Offset As Float = .3 * minDim";
_offset = (float) (.3*_mindim);
 //BA.debugLineNum = 161;BA.debugLine="Radius = .08 * minDim";
_radius = (float) (.08*_mindim);
 //BA.debugLineNum = 163;BA.debugLine="For i = 0 To Point.Length - 1";
{
final int step68 = 1;
final int limit68 = (int) (_point.length-1);
_i = (int) (0) ;
for (;(step68 > 0 && _i <= limit68) || (step68 < 0 && _i >= limit68) ;_i = ((int)(0 + _i + step68))  ) {
 //BA.debugLineNum = 164;BA.debugLine="Point(i).Initialize";
_point[_i].Initialize();
 //BA.debugLineNum = 165;BA.debugLine="Dim FactorX As Int = (i Mod 3)-1";
_factorx = (int) ((_i%3)-1);
 //BA.debugLineNum = 166;BA.debugLine="Point(i).x = CenterPoint.x + FactorX * Offset";
_point[_i].x = (float) (_centerpoint.x+_factorx*_offset);
 //BA.debugLineNum = 167;BA.debugLine="Dim FactorY As Int = Floor(i/3)-1";
_factory = (int) (__c.Floor(_i/(double)3)-1);
 //BA.debugLineNum = 168;BA.debugLine="Point(i).y = CenterPoint.y + FactorY * Offset";
_point[_i].y = (float) (_centerpoint.y+_factory*_offset);
 }
};
 //BA.debugLineNum = 171;BA.debugLine="Cvs.Initialize(pnlMiddle)";
_cvs.Initialize((android.view.View)(_pnlmiddle.getObject()));
 //BA.debugLineNum = 172;BA.debugLine="For i = 0 To Point.Length-1";
{
final int step76 = 1;
final int limit76 = (int) (_point.length-1);
_i = (int) (0) ;
for (;(step76 > 0 && _i <= limit76) || (step76 < 0 && _i >= limit76) ;_i = ((int)(0 + _i + step76))  ) {
 //BA.debugLineNum = 173;BA.debugLine="Cvs.DrawCircle(Point(i).x, Point(i).y, Radius, C";
_cvs.DrawCircle(_point[_i].x,_point[_i].y,_radius,__c.Colors.Green,__c.False,(float) (__c.DipToCurrent((int) (3))));
 }
};
 //BA.debugLineNum = 175;BA.debugLine="pnlMiddle.Invalidate";
_pnlmiddle.Invalidate();
 //BA.debugLineNum = 177;BA.debugLine="CvsX.Initialize(pnlMiddleX)";
_cvsx.Initialize((android.view.View)(_pnlmiddlex.getObject()));
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return "";
}
public String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 306;BA.debugLine="Private Sub btnCancel_Click";
 //BA.debugLineNum = 308;BA.debugLine="Hide_holder";
_hide_holder();
 //BA.debugLineNum = 309;BA.debugLine="CallSubDelayed3(mTarget, mEventName & \"_Closed\",";
__c.CallSubDelayed3(ba,_mtarget,_meventname+"_Closed",(Object)(__c.True),(Object)(_pointselected));
 //BA.debugLineNum = 311;BA.debugLine="End Sub";
return "";
}
public String  _btnok_click() throws Exception{
 //BA.debugLineNum = 313;BA.debugLine="Private Sub btnOK_click";
 //BA.debugLineNum = 317;BA.debugLine="If PointLen = 0 OR PointSelected.Length = PointLe";
if (_pointlen==0 || _pointselected.length()==_pointlen || _pointlen==10) { 
 //BA.debugLineNum = 318;BA.debugLine="Hide_holder";
_hide_holder();
 //BA.debugLineNum = 319;BA.debugLine="CallSubDelayed3(mTarget, mEventName & \"_Closed\",";
__c.CallSubDelayed3(ba,_mtarget,_meventname+"_Closed",(Object)(__c.False),(Object)(_pointselected));
 }else {
 //BA.debugLineNum = 321;BA.debugLine="ToastMessageShow(\"Number of Point must be \" & Po";
__c.ToastMessageShow(BA.ObjectToCharSequence("Number of Point must be "+BA.NumberToString(_pointlen)),__c.False);
 };
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private mTarget As Object";
_mtarget = new Object();
 //BA.debugLineNum = 7;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 8;BA.debugLine="Private holder As Panel";
_holder = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private pnlMain, pnlButtons As Panel";
_pnlmain = new anywheresoftware.b4a.objects.PanelWrapper();
_pnlbuttons = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private btnOk, btnCancel As Button";
_btnok = new anywheresoftware.b4a.objects.ButtonWrapper();
_btncancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private mButton As Int = 0";
_mbutton = (int) (0);
 //BA.debugLineNum = 13;BA.debugLine="Private btnHeight, btnWidth, btnSpace, lblHeight";
_btnheight = 0;
_btnwidth = 0;
_btnspace = 0;
_lblheight = (int) (0);
 //BA.debugLineNum = 14;BA.debugLine="Private lblTitle As Label";
_lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private strTitle As String '= \"Enter the Pattern\"";
_strtitle = "";
 //BA.debugLineNum = 16;BA.debugLine="Private intTextSize As Int = 15";
_inttextsize = (int) (15);
 //BA.debugLineNum = 19;BA.debugLine="Private Cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private pnlMiddle As Panel";
_pnlmiddle = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private CvsX As Canvas";
_cvsx = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private pnlMiddleX As Panel";
_pnlmiddlex = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Type Center(x As Float, y As Float)";
;
 //BA.debugLineNum = 26;BA.debugLine="Private Point(9) As Center";
_point = new b4a.UnlockPattern.clsunlockpattern._center[(int) (9)];
{
int d0 = _point.length;
for (int i0 = 0;i0 < d0;i0++) {
_point[i0] = new b4a.UnlockPattern.clsunlockpattern._center();
}
}
;
 //BA.debugLineNum = 27;BA.debugLine="Private Radius As Float";
_radius = 0f;
 //BA.debugLineNum = 28;BA.debugLine="Private PointSelected As String";
_pointselected = "";
 //BA.debugLineNum = 29;BA.debugLine="Private LastPoint As Int";
_lastpoint = 0;
 //BA.debugLineNum = 30;BA.debugLine="Private PointLen As Int = 5";
_pointlen = (int) (5);
 //BA.debugLineNum = 31;BA.debugLine="Private PnlColor As Int = Colors.Black";
_pnlcolor = __c.Colors.Black;
 //BA.debugLineNum = 32;BA.debugLine="Private LineColor As Int = Colors.ARGB(150, 255,";
_linecolor = __c.Colors.ARGB((int) (150),(int) (255),(int) (0),(int) (0));
 //BA.debugLineNum = 33;BA.debugLine="Private ClearColor As Int = Colors.ARGB(0, 0, 0,";
_clearcolor = __c.Colors.ARGB((int) (0),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public String  _drawpath(int _newpoint) throws Exception{
int _intpos = 0;
int _s = 0;
int _e = 0;
int _xlastpoint = 0;
int _i = 0;
 //BA.debugLineNum = 215;BA.debugLine="Private Sub DrawPath(NewPoint As Int)";
 //BA.debugLineNum = 217;BA.debugLine="If PointSelected.Length = 0 OR LastPoint <> NewPo";
if (_pointselected.length()==0 || _lastpoint!=_newpoint) { 
 //BA.debugLineNum = 219;BA.debugLine="If PointSelected.Length = 0 Then";
if (_pointselected.length()==0) { 
 //BA.debugLineNum = 221;BA.debugLine="CvsX.DrawCircle(Point(NewPoint-1).x,Point(NewPo";
_cvsx.DrawCircle(_point[(int) (_newpoint-1)].x,_point[(int) (_newpoint-1)].y,(float) (__c.DipToCurrent((int) (6))),_linecolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 //BA.debugLineNum = 222;BA.debugLine="PointSelected = PointSelected & NewPoint";
_pointselected = _pointselected+BA.NumberToString(_newpoint);
 //BA.debugLineNum = 223;BA.debugLine="LastPoint = NewPoint";
_lastpoint = _newpoint;
 }else {
 //BA.debugLineNum = 225;BA.debugLine="Dim intPos As Int = PointSelected.IndexOf(\"\" &";
_intpos = _pointselected.indexOf(""+BA.NumberToString(_newpoint));
 //BA.debugLineNum = 226;BA.debugLine="If intPos = -1 Then		' the point never selected";
if (_intpos==-1) { 
 //BA.debugLineNum = 229;BA.debugLine="PointSelected = PointSelected & NewPoint";
_pointselected = _pointselected+BA.NumberToString(_newpoint);
 //BA.debugLineNum = 232;BA.debugLine="If PointSelected.Length > 1 Then";
if (_pointselected.length()>1) { 
 //BA.debugLineNum = 233;BA.debugLine="Dim S As Int = LastPoint - 1";
_s = (int) (_lastpoint-1);
 //BA.debugLineNum = 234;BA.debugLine="Dim E As Int = NewPoint - 1";
_e = (int) (_newpoint-1);
 //BA.debugLineNum = 236;BA.debugLine="CvsX.DrawCircle(Point(E).x,Point(E).y, 6dip,";
_cvsx.DrawCircle(_point[_e].x,_point[_e].y,(float) (__c.DipToCurrent((int) (6))),_linecolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 //BA.debugLineNum = 237;BA.debugLine="CvsX.DrawLine(Point(S).x, Point(S).y , Point(";
_cvsx.DrawLine(_point[_s].x,_point[_s].y,_point[_e].x,_point[_e].y,_linecolor,(float) (__c.DipToCurrent((int) (5))));
 };
 //BA.debugLineNum = 239;BA.debugLine="LastPoint = NewPoint";
_lastpoint = _newpoint;
 }else {
 //BA.debugLineNum = 241;BA.debugLine="If PointSelected.Length > 1 Then";
if (_pointselected.length()>1) { 
 //BA.debugLineNum = 242;BA.debugLine="Dim xLastPoint As Int = PointSelected.SubStri";
_xlastpoint = (int)(Double.parseDouble(_pointselected.substring((int) (_pointselected.length()-2),(int) (_pointselected.length()-1))));
 //BA.debugLineNum = 244;BA.debugLine="If xLastPoint = NewPoint Then";
if (_xlastpoint==_newpoint) { 
 //BA.debugLineNum = 246;BA.debugLine="PointSelected =  PointSelected.SubString2(0,";
_pointselected = _pointselected.substring((int) (0),(int) (_pointselected.length()-1));
 //BA.debugLineNum = 247;BA.debugLine="LastPoint = xLastPoint";
_lastpoint = _xlastpoint;
 //BA.debugLineNum = 251;BA.debugLine="CvsX.DrawColor(ClearColor)";
_cvsx.DrawColor(_clearcolor);
 //BA.debugLineNum = 252;BA.debugLine="If PointSelected.Length = 1 Then";
if (_pointselected.length()==1) { 
 //BA.debugLineNum = 253;BA.debugLine="Dim S As Int = PointSelected.SubString2(0,1";
_s = (int) ((double)(Double.parseDouble(_pointselected.substring((int) (0),(int) (1))))-1);
 //BA.debugLineNum = 254;BA.debugLine="CvsX.DrawCircle(Point(S).x, Point(S).y , 6d";
_cvsx.DrawCircle(_point[_s].x,_point[_s].y,(float) (__c.DipToCurrent((int) (6))),_linecolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 }else {
 //BA.debugLineNum = 256;BA.debugLine="For i = 0 To PointSelected.Length - 2";
{
final int step28 = 1;
final int limit28 = (int) (_pointselected.length()-2);
_i = (int) (0) ;
for (;(step28 > 0 && _i <= limit28) || (step28 < 0 && _i >= limit28) ;_i = ((int)(0 + _i + step28))  ) {
 //BA.debugLineNum = 257;BA.debugLine="Dim S As Int = PointSelected.SubString2(i,";
_s = (int) ((double)(Double.parseDouble(_pointselected.substring(_i,(int) (_i+1))))-1);
 //BA.debugLineNum = 258;BA.debugLine="CvsX.DrawCircle(Point(S).x, Point(S).y , 6";
_cvsx.DrawCircle(_point[_s].x,_point[_s].y,(float) (__c.DipToCurrent((int) (6))),_linecolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 //BA.debugLineNum = 259;BA.debugLine="Dim E As Int = PointSelected.SubString2(i+";
_e = (int) ((double)(Double.parseDouble(_pointselected.substring((int) (_i+1),(int) (_i+2))))-1);
 //BA.debugLineNum = 260;BA.debugLine="CvsX.DrawCircle(Point(E).x, Point(E).y , 6";
_cvsx.DrawCircle(_point[_e].x,_point[_e].y,(float) (__c.DipToCurrent((int) (6))),_linecolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 //BA.debugLineNum = 261;BA.debugLine="CvsX.DrawLine(Point(S).x, Point(S).y , Poi";
_cvsx.DrawLine(_point[_s].x,_point[_s].y,_point[_e].x,_point[_e].y,_linecolor,(float) (__c.DipToCurrent((int) (5))));
 }
};
 };
 };
 };
 };
 };
 };
 //BA.debugLineNum = 269;BA.debugLine="pnlMiddleX.Invalidate";
_pnlmiddlex.Invalidate();
 //BA.debugLineNum = 271;BA.debugLine="End Sub";
return "";
}
public String  _gesture_onfling(float _velocityx,float _velocityy,Object _motionevent1,Object _motionevent2) throws Exception{
 //BA.debugLineNum = 295;BA.debugLine="Private Sub Gesture_onFling(velocityX As Float, ve";
 //BA.debugLineNum = 297;BA.debugLine="If PointSelected.Length = 1 AND (velocityX > 50 O";
if (_pointselected.length()==1 && (_velocityx>50 || _velocityy>50)) { 
 //BA.debugLineNum = 298;BA.debugLine="Dim LastPoint As Int = PointSelected - 1";
_lastpoint = (int) ((double)(Double.parseDouble(_pointselected))-1);
 //BA.debugLineNum = 299;BA.debugLine="CvsX.DrawCircle(Point(LastPoint).x,Point(LastPoi";
_cvsx.DrawCircle(_point[_lastpoint].x,_point[_lastpoint].y,(float) (__c.DipToCurrent((int) (6))),_pnlcolor,__c.True,(float) (__c.DipToCurrent((int) (3))));
 //BA.debugLineNum = 300;BA.debugLine="pnlMiddleX.Invalidate";
_pnlmiddlex.Invalidate();
 //BA.debugLineNum = 301;BA.debugLine="PointSelected = \"\"";
_pointselected = "";
 };
 //BA.debugLineNum = 304;BA.debugLine="End Sub";
return "";
}
public String  _gesture_ontouch(int _action,float _x,float _y,Object _motionevent) throws Exception{
int _intpoint = 0;
 //BA.debugLineNum = 273;BA.debugLine="Private Sub Gesture_onTouch(Action As Int, X As Fl";
 //BA.debugLineNum = 277;BA.debugLine="If Action = 1 AND PointLen = 10 Then";
if (_action==1 && _pointlen==10) { 
 //BA.debugLineNum = 278;BA.debugLine="btnOK_click";
_btnok_click();
 }else {
 //BA.debugLineNum = 280;BA.debugLine="Dim intPoint As Int = WithInPoint(X,Y)";
_intpoint = _withinpoint(_x,_y);
 //BA.debugLineNum = 281;BA.debugLine="If intPoint = 99 Then";
if (_intpoint==99) { 
 //BA.debugLineNum = 282;BA.debugLine="If mButton = 0 Then";
if (_mbutton==0) { 
 //BA.debugLineNum = 283;BA.debugLine="btnOK_click";
_btnok_click();
 };
 }else {
 //BA.debugLineNum = 286;BA.debugLine="If intPoint > 0 Then";
if (_intpoint>0) { 
 //BA.debugLineNum = 287;BA.debugLine="DrawPath(intPoint)";
_drawpath(_intpoint);
 };
 };
 };
 //BA.debugLineNum = 293;BA.debugLine="End Sub";
return "";
}
public String  _hide_holder() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Private Sub Hide_holder";
 //BA.debugLineNum = 192;BA.debugLine="holder.Visible = False";
_holder.setVisible(__c.False);
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _target,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 38;BA.debugLine="Public Sub Initialize (Target As Object, EventName";
 //BA.debugLineNum = 39;BA.debugLine="mTarget = Target";
_mtarget = _target;
 //BA.debugLineNum = 40;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 42;BA.debugLine="If SubExists(mTarget, mEventName & \"_Closed\") = F";
if (__c.SubExists(ba,_mtarget,_meventname+"_Closed")==__c.False) { 
 //BA.debugLineNum = 43;BA.debugLine="ToastMessageShow(\"There is no \" & mEventName & \"";
__c.ToastMessageShow(BA.ObjectToCharSequence("There is no "+_meventname+"_Closed"),__c.False);
 //BA.debugLineNum = 44;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public String  _setnoofbutton(int _noofbutton) throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Public Sub setNoOfButton(NoOfButton As Int)";
 //BA.debugLineNum = 66;BA.debugLine="mButton = NoOfButton";
_mbutton = _noofbutton;
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public String  _setnoofpoint(int _noofpoint) throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Public Sub setNoOfPoint(NoOfPoint As Int)";
 //BA.debugLineNum = 58;BA.debugLine="PointLen  = NoOfPoint";
_pointlen = _noofpoint;
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public String  _settextsize(int _size) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Public Sub setTextSize(Size As Int)";
 //BA.debugLineNum = 74;BA.debugLine="intTextSize  = Size";
_inttextsize = _size;
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public String  _settitle(String _title) throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Public Sub setTitle(Title As String)";
 //BA.debugLineNum = 70;BA.debugLine="strTitle  = Title";
_strtitle = _title;
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public String  _show() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Public Sub Show";
 //BA.debugLineNum = 183;BA.debugLine="PointSelected = \"\"";
_pointselected = "";
 //BA.debugLineNum = 184;BA.debugLine="CvsX.DrawColor(ClearColor)";
_cvsx.DrawColor(_clearcolor);
 //BA.debugLineNum = 185;BA.debugLine="pnlMiddleX.Invalidate";
_pnlmiddlex.Invalidate();
 //BA.debugLineNum = 187;BA.debugLine="holder.Visible = True";
_holder.setVisible(__c.True);
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public int  _withinpoint(float _x,float _y) throws Exception{
float _a = 0f;
float _b = 0f;
float _c = 0f;
int _i = 0;
 //BA.debugLineNum = 195;BA.debugLine="Private Sub WithInPoint(x As Float, y As Float) As";
 //BA.debugLineNum = 197;BA.debugLine="Dim a As Float";
_a = 0f;
 //BA.debugLineNum = 198;BA.debugLine="Dim b As Float";
_b = 0f;
 //BA.debugLineNum = 199;BA.debugLine="Dim c As Float";
_c = 0f;
 //BA.debugLineNum = 200;BA.debugLine="If PointLen = 0 OR PointSelected.Length < PointLe";
if (_pointlen==0 || _pointselected.length()<_pointlen) { 
 //BA.debugLineNum = 201;BA.debugLine="For i = 0 To Point.Length - 1";
{
final int step5 = 1;
final int limit5 = (int) (_point.length-1);
_i = (int) (0) ;
for (;(step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5) ;_i = ((int)(0 + _i + step5))  ) {
 //BA.debugLineNum = 202;BA.debugLine="a = Point(i).x - x";
_a = (float) (_point[_i].x-_x);
 //BA.debugLineNum = 203;BA.debugLine="b = Point(i).y - y";
_b = (float) (_point[_i].y-_y);
 //BA.debugLineNum = 204;BA.debugLine="c = (a * a + b * b)";
_c = (float) ((_a*_a+_b*_b));
 //BA.debugLineNum = 205;BA.debugLine="If c < Radius * Radius Then";
if (_c<_radius*_radius) { 
 //BA.debugLineNum = 206;BA.debugLine="Return i+1";
if (true) return (int) (_i+1);
 };
 }
};
 //BA.debugLineNum = 209;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 211;BA.debugLine="Return 99";
if (true) return (int) (99);
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return 0;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
