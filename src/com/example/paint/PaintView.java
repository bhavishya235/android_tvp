package com.example.paint;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.Toast;

public class PaintView extends GridView implements OnTouchListener {
	
	static int n =3;
	static int width;
	static int length;
	static int cwidth;
	static int hspace;
	static int vspace = 50;
	static float[] xarray = new float[4];
	static float[] yarray = new float[6];
	private static final String TAG = "PaintView";
	List<Point> points = new ArrayList<Point>();
	List<Point> points2 = new ArrayList<Point>();
	Paint paint = new Paint();
	Point pfirst = new Point();
	Point psec = new Point();
	int flagpoint = 0;
	
	public PaintView(Context context,int cw, int vs, int l, int hs,int w) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		width = w;
		length = l;
		cwidth = cw;
		hspace = hs;
		vspace = vs;
		xarray[0] = (cw - w)/2;
		xarray[1] = (cw + w)/2;
		xarray[2] = (3*cw - w)/2;
		xarray[3] =	(3*cw + w)/2; 
		yarray[0] = vs;
		yarray[1] = 4*vs;
		yarray[2] = 5*vs;
		yarray[3] =	8*vs;
		yarray[4] = 9*vs;
		yarray[5] = 12*vs;
		
		Log.d("array","xarray->"+xarray[0]+" "+xarray[1]+" y->"+yarray[0]+" "+yarray[1]);
		
		this.setOnTouchListener(this);
		
		paint.setColor(Color.BLUE);
		paint.setAlpha(100);
		paint.setStrokeCap(Cap.ROUND);
		paint.setStrokeWidth(10);
		paint.setAntiAlias(true);
		
		pfirst.x = -1;pfirst.x = -1;
		psec.x = -1;psec.y = -1;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		int c = points.size();
		for(int i=0;i<c-1;i+=2)
		{
			Point point1=points.get(i);
			Point point2 = points.get(i+1);
			//if( abs2(point1.x-point2.x) < 10 && abs2(point1.y-point2.y) < 10)
				canvas.drawLine(point1.x,point1.y,point2.x,point2.y, paint);
		}
		
		int c2 = points2.size();
		for(int i=0;i<c2-1;i+=1)
		{
			Point point1=points2.get(i);
			Point point2 = points2.get(i+1);
			//if( abs2(point1.x-point2.x) < 10 && abs2(point1.y-point2.y) < 10)
				canvas.drawLine(point1.x,point1.y,point2.x,point2.y, paint);
		}
	}
	
	private float abs2(float f) {
		// TODO Auto-generated method stub
		float abs_n;
		abs_n = (f<0) ? -f : f;
		return abs_n;
	}

	public boolean onTouch(View view, MotionEvent event) {
		Point point = new Point();
		
		//point.x = event.getX();
		//point.y = event.getY();
		int eventaction = event.getAction();
		switch (eventaction)
		{
			case MotionEvent.ACTION_DOWN: 
	            // finger touches the screen
				
				point.x = event.getX();
				point.y = event.getY();
				if((point.x >= xarray[0] && point.x <=xarray[1]) && ( (point.y>=yarray[0] && point.y<=yarray[1])||(point.y>=yarray[2] && point.y<=yarray[3])||(point.y>=yarray[4] && point.y<=yarray[5]) ))
				{
					Log.d("hello","bye");
					points.add(point);
					flagpoint = 1;
				}
				else flagpoint = 0;
	            break;
	
	        case MotionEvent.ACTION_MOVE:
	            // finger moves on the screen
	        	point.x = event.getX();
	        	point.y = event.getY();
	        	points2.add(point);
	            break;
	
	        case MotionEvent.ACTION_UP:   
	            // finger leaves the screen
	        	points2.clear();
	        	if(flagpoint == 1)
	        	{
	        		Log.d("hello2","bye2");
	        		point.x = event.getX();
		        	point.y = event.getY();
		        	if((point.x >= xarray[2] && point.x <=xarray[3]) && ( (point.y>=yarray[0] && point.y<=yarray[1])||(point.y>=yarray[2] && point.y<=yarray[3])||(point.y>=yarray[4] && point.y<=yarray[5]) ))
		        		points.add(point);
		        	else points.remove(points.size()-1);
	        	}
	        	break;
		}
		//pfirst.x=pfirst.y=psec.x=psec.y=0;
		invalidate();
		Log.d(TAG, "point: " + point);
		return true;
	}
}

class Point {
	float x, y;
	
	@Override
	public String toString() {
		return x + ", " + y;
	}
}