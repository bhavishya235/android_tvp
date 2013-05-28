package com.example.paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	

	
	PaintView paintView;
	LinearLayout ll;
//	final int width = 130;
//	final int length = 150;
//	int cwidth = width+70;
//	int hspace;
//	final int vspace = 50;
	
	int n =3;
	int width;
	int length;
	int cwidth;
	int hspace;
	int vspace = 50;

	int dpHeight;
	int dpWidth;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);

	    float density  = getResources().getDisplayMetrics().density;
	    dpHeight = (int)(outMetrics.heightPixels);
	    dpWidth  = (int)(outMetrics.widthPixels);
        
	    cwidth = dpWidth/2;
	    width = (int)(0.6 * cwidth);
	    
	    vspace = (int)(dpHeight/15);
	    
	    length = (int)(3*dpHeight/15);
	    
	    hspace = dpWidth - 2*cwidth; 
	    		
	    Log.v("screen size",""+dpHeight+"X"+dpWidth);
	    
		paintView = new PaintView(this,cwidth,vspace,length,hspace,width);
		paintView.setId(999999);
	//	paintView.setY(50);
		paintView.setBackgroundColor(Color.WHITE);
		paintView.setNumColumns(2);
		paintView.setColumnWidth(cwidth);
		paintView.setVerticalSpacing(vspace);
		paintView.setHorizontalSpacing(hspace);
		paintView.setStretchMode(GridView.NO_STRETCH);
		paintView.setGravity(Gravity.CENTER);
		paintView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,dpHeight-2*vspace-5));
		paintView.setAdapter(new ImageAdapter(this));
		paintView.requestFocus();
		
		setContentView(R.layout.activity_main);
		View linearLayout =  findViewById(R.id.info);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);


        TextView valueTV = new TextView(this);
        valueTV.setText("Match The Following");	//""+dpHeight+"X"+dpWidth+" d"+density
        valueTV.setHeight(vspace);
        valueTV.setTextSize(vspace/2);
        valueTV.setGravity(Gravity.CENTER);
        valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));

        Button b = new Button(this);
        b.setText("next");
        b.setGravity(Gravity.CENTER);
        b.setPadding(vspace/10, vspace/10, vspace/10, vspace/10);
        
        ((LinearLayout) linearLayout).addView(valueTV);
        ((LinearLayout) linearLayout).addView(paintView);
        ((LinearLayout) linearLayout).addView(b);
        
	}
	
	//IMAGE ADAPTER STARTS
		public class ImageAdapter extends BaseAdapter {
			
			 // references to our images
		    private Integer[] mThumbIds = new Integer[6];
		    private int n = 3;
		    
		    private Context mContext;

		    public ImageAdapter(Context c) {
		        mContext = c;
		        int i;
		      
		        List<Integer> ques = new ArrayList<Integer>(6);
		        List<Integer> ques2 = new ArrayList<Integer>(3);
		        List<Integer> ques3 = new ArrayList<Integer>(3);
		        
		        ques.add(R.drawable.sidea_0);
		        ques.add(R.drawable.sidea_1);
		        ques.add(R.drawable.sidea_2);
		        ques.add(R.drawable.sidea_3);
		        ques.add(R.drawable.sidea_4);
		        ques.add(R.drawable.sidea_5);
		        
		        Random rn = new Random();
	        	Collections.shuffle(ques, rn);
	        	ques2.addAll(ques.subList(0,3));
	        	ques3.addAll(ques.subList(0,3));
	        	Collections.shuffle(ques3, rn);
		        
	        	for(i=0;i<n;i++)
		        {
		        		mThumbIds[2*i]=ques2.get(i);
		        		mThumbIds[2*i+1]=ques3.get(i);
		        }
		    }

		    public int getCount() {
		        return mThumbIds.length;
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return 0;
		    }

		 // create a new ImageView for each item referenced by the Adapter
			public View getView(int position, View convertView, ViewGroup parent) {
		        ImageView imageView;
		        if (convertView == null) {  // if it's not recycled, initialise some attributes
		        	 imageView = new ImageView(mContext);
		            if(position != 1 && position!=0)
		            {
		            	imageView.setLayoutParams(new GridView.LayoutParams(width, length));
		            	imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		            	imageView.setPadding(6, 6, 6, 6);
		            	imageView.setBackgroundColor(Color.LTGRAY);
		            	imageView.setId(position);
		            }
		            else
		            {
		            	imageView.setLayoutParams(new GridView.LayoutParams(width, length+vspace));
		            	imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		            	imageView.setPadding(6, vspace+6, 6, 6);
		            	imageView.setBackgroundColor(Color.LTGRAY);
		            	imageView.setId(position);
		            }
		            
		        } else {
		            imageView = (ImageView) convertView;
		        }

		        imageView.setImageResource(mThumbIds[position]);
		        return imageView;
		    }
		    
		}
	//IMAGE ADAPTER ENDS
}