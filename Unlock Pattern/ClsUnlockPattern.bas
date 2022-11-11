Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=2.71
@EndOfDesignText@
'Class module ClsUnlockPattern
'
' v 1.0 - drachmad 1.07.2103
'
Sub Class_Globals
	Private mTarget As Object
	Private mEventName As String
	Private holder As Panel
	
	Private pnlMain, pnlButtons As Panel
	Private btnOk, btnCancel As Button
	Private mButton As Int = 0
	Private btnHeight, btnWidth, btnSpace, lblHeight As Int = 0
	Private lblTitle As Label
	Private strTitle As String '= "Enter the Pattern"
	Private intTextSize As Int = 15
	
	' 1st layer
	Private Cvs As Canvas			
	Private pnlMiddle As Panel
	' 2nd layer
	Private CvsX As Canvas			
	Private pnlMiddleX As Panel
	
	Type Center(x As Float, y As Float)
	Private Point(9) As Center
	Private Radius As Float
	Private PointSelected As String
	Private LastPoint As Int
	Private PointLen As Int = 5
	Private PnlColor As Int = Colors.Black
	Private LineColor As Int = Colors.ARGB(150, 255, 0, 0)
	Private ClearColor As Int = Colors.ARGB(0, 0, 0, 0)
			
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize (Target As Object, EventName As String)
	mTarget = Target
	mEventName = EventName
	
	If SubExists(mTarget, mEventName & "_Closed") = False Then
		ToastMessageShow("There is no " & mEventName & "_Closed", False)
		Return
	End If
	
End Sub

#Region  Property 

' Sets the Number of Point that can be selected
' 0 - arbitraty number (Max. 9), <b> validity should be checked by the application </b>
'     NoOfButton will be default to 1 (OK only).
' 10 - Ok button will be executed on touch-up, <b> validity should be checked by the application </b>.
'	  NoOfButton will be set to 0 (No button)
' Default 5
Public Sub setNoOfPoint(NoOfPoint As Int)
	PointLen  = NoOfPoint
End Sub

' Sets the Number of Button shown
' 0 - No button, NoOfPoint should be set > 0 
' 1 - OK button only
' 2 - Cancel &amp; OK buttons
Public Sub setNoOfButton(NoOfButton As Int)
	mButton = NoOfButton
End Sub

Public Sub setTitle(Title As String)
	strTitle  = Title
End Sub

Public Sub setTextSize(Size As Int)
	intTextSize  = Size
End Sub

#End Region 

Public Sub AddView(Parent As Activity, Left As Int, Top As Int, Width As Int, Height As Int)
		
	holder.Initialize("holder")
	Hide_holder
	holder.Color = Colors.ARGB(102, 0, 0, 0)
	Parent.AddView(holder, 0, 0, 100%x, 100%y)
	
	Dim gdw As GradientDrawable
	gdw.Initialize("LEFT_RIGHT", Array As Int(Colors.White, Colors.Gray, Colors.White))

	If PointLen = 0 And mButton = 0 Then
		mButton = 1		' minimum button if the point is unlimited
	End If
	
	If PointLen = 10 Then
		mButton = 0		' no button is required, the Ok is executed on touch-up
	End If

	lblHeight = 50dip
	If mButton > 0 Then
		btnSpace = 1dip	
		btnHeight = 50dip
		btnWidth = Width/mButton-btnSpace
	End If
		
	pnlMain.Initialize("pnlMain")
	pnlMain.Color = Colors.RGB(248, 248, 255)
	
	holder.AddView(pnlMain, Left, Top, Width, Height + btnHeight + lblHeight + 8dip)
	
	If strTitle <> "" Then
		lblTitle.Initialize("")
		lblTitle.Color = Colors.RGB(51, 181, 229) 
		lblTitle.TextSize = intTextSize
		lblTitle.TextColor = Colors.Black
		lblTitle.Text = strTitle
		lblTitle.Gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
		pnlMain.AddView(lblTitle, 0, 0, pnlMain.Width, lblHeight)
	Else
		pnlMain.Height = pnlMain.Height - lblHeight
		lblHeight = 0
	End If
	
	pnlMiddle.Initialize("")
	pnlMiddle.Color = PnlColor
	pnlMain.AddView(pnlMiddle, 0, lblHeight + 4dip, pnlMain.Width, Height)
	pnlMiddleX.Initialize("")
	pnlMain.AddView(pnlMiddleX, 0, lblHeight + 4dip, pnlMain.Width, Height)
		
	If mButton > 0 Then
		pnlButtons.Initialize("")
		pnlButtons.Background = gdw
		pnlMain.AddView(pnlButtons, 0, pnlMain.Height - btnHeight, pnlMain.Width, btnHeight)
		
		btnOk.Initialize("btnOK")
		btnOk.Text = "O K"
		btnOk.color = Colors.RGB(248, 248, 255)
		btnOk.TextSize = intTextSize
		pnlButtons.AddView(btnOk, pnlButtons.Width - btnWidth, 2dip, btnWidth, btnHeight-2dip)

		If mButton = 2 Then
			btnCancel.Initialize("btnCancel")
			btnCancel.Text = "Cancel"
			btnCancel.color = Colors.RGB(248, 248, 255)
			btnCancel.TextSize = intTextSize
			pnlButtons.AddView(btnCancel, 0, 2dip, btnWidth, btnHeight-2dip)
		End If
	End If
	
	Private GD As GestureDetector
	GD.SetOnGestureListener(pnlMiddle, "Gesture")
	GD.EnableLongPress(False)
	
	Dim CenterPoint As Center
	CenterPoint.Initialize
	
	Dim minDim As Float = Min(pnlMiddle.Width,pnlMiddle.Height)
	CenterPoint.x = .5 * pnlMiddle.Width
	CenterPoint.y = .5 * pnlMiddle.Height
	Dim Offset As Float = .3 * minDim
	Radius = .1 * minDim
	Dim Offset As Float = .3 * minDim
	Radius = .08 * minDim
	
	For i = 0 To Point.Length - 1
		Point(i).Initialize
		Dim FactorX As Int = (i Mod 3)-1
		Point(i).x = CenterPoint.x + FactorX * Offset
		Dim FactorY As Int = Floor(i/3)-1
		Point(i).y = CenterPoint.y + FactorY * Offset
	Next
						
	Cvs.Initialize(pnlMiddle)
	For i = 0 To Point.Length-1
		Cvs.DrawCircle(Point(i).x, Point(i).y, Radius, Colors.Green, False, 3dip)
	Next
	pnlMiddle.Invalidate
	
	CvsX.Initialize(pnlMiddleX)
	
End Sub

Public Sub Show

	PointSelected = ""
	CvsX.DrawColor(ClearColor)
	pnlMiddleX.Invalidate
	
	holder.Visible = True
	
End Sub

Private Sub Hide_holder
	holder.Visible = False
End Sub

Private Sub WithInPoint(x As Float, y As Float) As Int

	Dim a As Float
    Dim b As Float
	Dim c As Float
	If PointLen = 0 OR PointSelected.Length < PointLen Then
		For i = 0 To Point.Length - 1
			a = Point(i).x - x 
			b = Point(i).y - y
			c = (a * a + b * b)
			If c < Radius * Radius Then
				Return i+1
			End If
		Next
		Return 0
	End If
	Return 99
			
End Sub

Private Sub DrawPath(NewPoint As Int)
'	Log("DrawPath: "  & PointSelected.Length & " " & LastPoint & " " & NewPoint)
	If PointSelected.Length = 0 OR LastPoint <> NewPoint Then
		' Just delete from here to ...
		If PointSelected.Length = 0 Then
			' Just draw the Dot
			CvsX.DrawCircle(Point(NewPoint-1).x,Point(NewPoint-1).y, 6dip, LineColor, True, 3dip)
			PointSelected = PointSelected & NewPoint
			LastPoint = NewPoint
		Else 
			Dim intPos As Int = PointSelected.IndexOf("" & NewPoint)
			If intPos = -1 Then		' the point never selected
		' ... here if you want to allow same point use twice.
		' But it will confused the user.
				PointSelected = PointSelected & NewPoint
				'Log("Selected: " & PointSelected)
				'Log("Point: " & NewPoint)
				If PointSelected.Length > 1 Then
					Dim S As Int = LastPoint - 1
					Dim E As Int = NewPoint - 1
					
					CvsX.DrawCircle(Point(E).x,Point(E).y, 6dip, LineColor, True, 3dip)
					CvsX.DrawLine(Point(S).x, Point(S).y , Point(E).x,Point(E).y, LineColor, 5dip)
				End If
				LastPoint = NewPoint
			Else	' cancel the last path
				If PointSelected.Length > 1 Then
					Dim xLastPoint As Int = PointSelected.SubString2( PointSelected.length-2,PointSelected.length-1)
					' delete only if the new point selected is the previous point
					If xLastPoint = NewPoint Then	
						'Log("cancel the last path")
						PointSelected =  PointSelected.SubString2(0,PointSelected.length-1)
						LastPoint = xLastPoint
						
						' Redraw all path, can not draw partial path only,
						' it depends on criss cross of the lines. 
						CvsX.DrawColor(ClearColor)
						If PointSelected.Length = 1 Then
							Dim S As Int = PointSelected.SubString2(0,1) - 1
							CvsX.DrawCircle(Point(S).x, Point(S).y , 6dip, LineColor, True, 3dip)
						Else
							For i = 0 To PointSelected.Length - 2
								Dim S As Int = PointSelected.SubString2(i,i+1) - 1
								CvsX.DrawCircle(Point(S).x, Point(S).y , 6dip, LineColor, True, 3dip)
								Dim E As Int = PointSelected.SubString2(i+1,i+2) - 1
								CvsX.DrawCircle(Point(E).x, Point(E).y , 6dip, LineColor, True, 3dip)
								CvsX.DrawLine(Point(S).x, Point(S).y , Point(E).x,Point(E).y, LineColor, 5dip)
							Next
						End If
					End If
				End If
			End If
		End If
	End If
	pnlMiddleX.Invalidate	
	
End Sub

Private Sub Gesture_onTouch(Action As Int, X As Float, Y As Float, MotionEvent As Object)
'	Log("onTouch action=" & Action & ", x=" & X & ", y=" & Y & ", ev=" & MotionEvent)
'	If holder.NumberOfViews > 0 Then
'	If holder.visible Then
		If Action = 1 AND PointLen = 10 Then
			btnOK_click
		Else
			Dim intPoint As Int = WithInPoint(X,Y)
			If intPoint = 99 Then
				If mButton = 0 Then
					btnOK_click
				End If
			Else
				If intPoint > 0 Then
					DrawPath(intPoint)
	'				Log("Gesture_onTouch: " & intPoint)
				End If
			End If
		End If
'	End If
End Sub

Private Sub Gesture_onFling(velocityX As Float, velocityY As Float, MotionEvent1 As Object, MotionEvent2 As Object)
'	Log("   onFling velocityX = " & velocityX & ", velocityY = " & velocityY & ", ev1 = " & MotionEvent1 & ", ev2 = " & MotionEvent2)
	If PointSelected.Length = 1 AND (velocityX > 50 OR  velocityY > 50) Then
		Dim LastPoint As Int = PointSelected - 1
		CvsX.DrawCircle(Point(LastPoint).x,Point(LastPoint).y, 6dip, PnlColor, True, 3dip)
		pnlMiddleX.Invalidate
		PointSelected = ""
	End If
	
End Sub

Private Sub btnCancel_Click

	Hide_holder
	CallSubDelayed3(mTarget, mEventName & "_Closed", True, PointSelected)
	
End Sub

Private Sub btnOK_click

'	Log("Selected: " & PointSelected)
'	holder.RemoveView
	If PointLen = 0 OR PointSelected.Length = PointLen OR PointLen = 10 Then
		Hide_holder
		CallSubDelayed3(mTarget, mEventName & "_Closed", False, PointSelected)
	Else
		ToastMessageShow("Number of Point must be " & PointLen, False)
	End If
	
End Sub