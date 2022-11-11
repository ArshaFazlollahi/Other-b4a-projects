package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_home{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Home/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="et.Height=9%y"[Home/General script]
views.get("et").vw.setHeight((int)((9d / 100 * height)));
//BA.debugLineNum = 4;BA.debugLine="et.Width=80%x"[Home/General script]
views.get("et").vw.setWidth((int)((80d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="et.HorizontalCenter=42%x"[Home/General script]
views.get("et").vw.setLeft((int)((42d / 100 * width) - (views.get("et").vw.getWidth() / 2)));
//BA.debugLineNum = 6;BA.debugLine="et.Bottom=95%y"[Home/General script]
views.get("et").vw.setTop((int)((95d / 100 * height) - (views.get("et").vw.getHeight())));
//BA.debugLineNum = 8;BA.debugLine="btn_snd.Height=et.Height"[Home/General script]
views.get("btn_snd").vw.setHeight((int)((views.get("et").vw.getHeight())));
//BA.debugLineNum = 9;BA.debugLine="btn_snd.Width=18%x"[Home/General script]
views.get("btn_snd").vw.setWidth((int)((18d / 100 * width)));
//BA.debugLineNum = 10;BA.debugLine="btn_snd.Bottom=et.Bottom"[Home/General script]
views.get("btn_snd").vw.setTop((int)((views.get("et").vw.getTop() + views.get("et").vw.getHeight()) - (views.get("btn_snd").vw.getHeight())));
//BA.debugLineNum = 11;BA.debugLine="btn_snd.Left=et.Right"[Home/General script]
views.get("btn_snd").vw.setLeft((int)((views.get("et").vw.getLeft() + views.get("et").vw.getWidth())));
//BA.debugLineNum = 13;BA.debugLine="et_n.Height=et.Height"[Home/General script]
views.get("et_n").vw.setHeight((int)((views.get("et").vw.getHeight())));
//BA.debugLineNum = 14;BA.debugLine="et_n.Width=70%x"[Home/General script]
views.get("et_n").vw.setWidth((int)((70d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="et_n.HorizontalCenter=65%x"[Home/General script]
views.get("et_n").vw.setLeft((int)((65d / 100 * width) - (views.get("et_n").vw.getWidth() / 2)));
//BA.debugLineNum = 16;BA.debugLine="et_n.Top=10%y"[Home/General script]
views.get("et_n").vw.setTop((int)((10d / 100 * height)));
//BA.debugLineNum = 18;BA.debugLine="lbl.Height=et_n.Height"[Home/General script]
views.get("lbl").vw.setHeight((int)((views.get("et_n").vw.getHeight())));
//BA.debugLineNum = 19;BA.debugLine="lbl.Width=30%x"[Home/General script]
views.get("lbl").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 20;BA.debugLine="lbl.Top=et_n.Top"[Home/General script]
views.get("lbl").vw.setTop((int)((views.get("et_n").vw.getTop())));
//BA.debugLineNum = 21;BA.debugLine="lbl.Right=et_n.Left"[Home/General script]
views.get("lbl").vw.setLeft((int)((views.get("et_n").vw.getLeft()) - (views.get("lbl").vw.getWidth())));
//BA.debugLineNum = 23;BA.debugLine="btn_1.Height=20%y"[Home/General script]
views.get("btn_1").vw.setHeight((int)((20d / 100 * height)));
//BA.debugLineNum = 24;BA.debugLine="btn_1.Width=20%y"[Home/General script]
views.get("btn_1").vw.setWidth((int)((20d / 100 * height)));
//BA.debugLineNum = 25;BA.debugLine="btn_1.Left=10%x"[Home/General script]
views.get("btn_1").vw.setLeft((int)((10d / 100 * width)));
//BA.debugLineNum = 26;BA.debugLine="btn_1.Top=27%y"[Home/General script]
views.get("btn_1").vw.setTop((int)((27d / 100 * height)));
//BA.debugLineNum = 28;BA.debugLine="btn_2.Height=20%y"[Home/General script]
views.get("btn_2").vw.setHeight((int)((20d / 100 * height)));
//BA.debugLineNum = 29;BA.debugLine="btn_2.Width=20%y"[Home/General script]
views.get("btn_2").vw.setWidth((int)((20d / 100 * height)));
//BA.debugLineNum = 30;BA.debugLine="btn_2.Right=90%x"[Home/General script]
views.get("btn_2").vw.setLeft((int)((90d / 100 * width) - (views.get("btn_2").vw.getWidth())));
//BA.debugLineNum = 31;BA.debugLine="btn_2.Top=27%y"[Home/General script]
views.get("btn_2").vw.setTop((int)((27d / 100 * height)));
//BA.debugLineNum = 33;BA.debugLine="btn_3.Height=20%y"[Home/General script]
views.get("btn_3").vw.setHeight((int)((20d / 100 * height)));
//BA.debugLineNum = 34;BA.debugLine="btn_3.Width=20%y"[Home/General script]
views.get("btn_3").vw.setWidth((int)((20d / 100 * height)));
//BA.debugLineNum = 35;BA.debugLine="btn_3.Left=10%x"[Home/General script]
views.get("btn_3").vw.setLeft((int)((10d / 100 * width)));
//BA.debugLineNum = 36;BA.debugLine="btn_3.Top=57%y"[Home/General script]
views.get("btn_3").vw.setTop((int)((57d / 100 * height)));
//BA.debugLineNum = 38;BA.debugLine="btn_4.Height=20%y"[Home/General script]
views.get("btn_4").vw.setHeight((int)((20d / 100 * height)));
//BA.debugLineNum = 39;BA.debugLine="btn_4.Width=20%y"[Home/General script]
views.get("btn_4").vw.setWidth((int)((20d / 100 * height)));
//BA.debugLineNum = 40;BA.debugLine="btn_4.Right=90%x"[Home/General script]
views.get("btn_4").vw.setLeft((int)((90d / 100 * width) - (views.get("btn_4").vw.getWidth())));
//BA.debugLineNum = 41;BA.debugLine="btn_4.Top=57%y"[Home/General script]
views.get("btn_4").vw.setTop((int)((57d / 100 * height)));
//BA.debugLineNum = 43;BA.debugLine="et_r.Width=et_n.Width"[Home/General script]
views.get("et_r").vw.setWidth((int)((views.get("et_n").vw.getWidth())));
//BA.debugLineNum = 44;BA.debugLine="et_r.Height=et_n.Height"[Home/General script]
views.get("et_r").vw.setHeight((int)((views.get("et_n").vw.getHeight())));
//BA.debugLineNum = 45;BA.debugLine="et_r.Bottom=et_n.Top"[Home/General script]
views.get("et_r").vw.setTop((int)((views.get("et_n").vw.getTop()) - (views.get("et_r").vw.getHeight())));
//BA.debugLineNum = 46;BA.debugLine="et_r.Right=et_n.Right"[Home/General script]
views.get("et_r").vw.setLeft((int)((views.get("et_n").vw.getLeft() + views.get("et_n").vw.getWidth()) - (views.get("et_r").vw.getWidth())));
//BA.debugLineNum = 48;BA.debugLine="lbl_1.Height=et_r.Height"[Home/General script]
views.get("lbl_1").vw.setHeight((int)((views.get("et_r").vw.getHeight())));
//BA.debugLineNum = 49;BA.debugLine="lbl_1.Width=30%x"[Home/General script]
views.get("lbl_1").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 50;BA.debugLine="lbl_1.Top=et_r.Top"[Home/General script]
views.get("lbl_1").vw.setTop((int)((views.get("et_r").vw.getTop())));
//BA.debugLineNum = 51;BA.debugLine="lbl_1.Right=et_r.Left"[Home/General script]
views.get("lbl_1").vw.setLeft((int)((views.get("et_r").vw.getLeft()) - (views.get("lbl_1").vw.getWidth())));
//BA.debugLineNum = 53;BA.debugLine="et_b.Height=10%y"[Home/General script]
views.get("et_b").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 54;BA.debugLine="et_b.Width=40%x"[Home/General script]
views.get("et_b").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 55;BA.debugLine="et_b.HorizontalCenter=50%x"[Home/General script]
views.get("et_b").vw.setLeft((int)((50d / 100 * width) - (views.get("et_b").vw.getWidth() / 2)));
//BA.debugLineNum = 56;BA.debugLine="et_b.VerticalCenter=52%y"[Home/General script]
views.get("et_b").vw.setTop((int)((52d / 100 * height) - (views.get("et_b").vw.getHeight() / 2)));
//BA.debugLineNum = 58;BA.debugLine="btn_set.Height=et_b.Height"[Home/General script]
views.get("btn_set").vw.setHeight((int)((views.get("et_b").vw.getHeight())));
//BA.debugLineNum = 59;BA.debugLine="btn_set.Width=20%x"[Home/General script]
views.get("btn_set").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 60;BA.debugLine="btn_set.Top=et_b.Top"[Home/General script]
views.get("btn_set").vw.setTop((int)((views.get("et_b").vw.getTop())));
//BA.debugLineNum = 61;BA.debugLine="btn_set.Right=et_b.Left"[Home/General script]
views.get("btn_set").vw.setLeft((int)((views.get("et_b").vw.getLeft()) - (views.get("btn_set").vw.getWidth())));
//BA.debugLineNum = 63;BA.debugLine="btn_set2.Height=et_b.Height"[Home/General script]
views.get("btn_set2").vw.setHeight((int)((views.get("et_b").vw.getHeight())));
//BA.debugLineNum = 64;BA.debugLine="btn_set2.Width=20%x"[Home/General script]
views.get("btn_set2").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 65;BA.debugLine="btn_set2.Top=et_b.Top"[Home/General script]
views.get("btn_set2").vw.setTop((int)((views.get("et_b").vw.getTop())));
//BA.debugLineNum = 66;BA.debugLine="btn_set2.Left=et_b.Right"[Home/General script]
views.get("btn_set2").vw.setLeft((int)((views.get("et_b").vw.getLeft() + views.get("et_b").vw.getWidth())));

}
}