package com.itheima78.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class IndexBar extends View {

	private Paint paint;//画笔
	private int indexBarWidth;//indexBar的宽度
	
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private float cellHeight;
	
	public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public IndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public IndexBar(Context context) {
		super(context);
		init();
	}
	/**
	 * 初始化方法
	 */
	private void init(){
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.CENTER);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		indexBarWidth = this.getMeasuredWidth();
		cellHeight = this.getMeasuredHeight() * 1.0f / indexArr.length;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for(int i=0;i<indexArr.length;i++){
			
			if(lastIndex == i){
				paint.setColor(Color.BLACK);
			}else{
				paint.setColor(Color.WHITE);
			}
			
			canvas.drawText(indexArr[i], indexBarWidth/2 ,
					cellHeight/2 + getTextHeight(indexArr[i])/2 + i*cellHeight, 
					paint);
			
		}
	}
	
	int lastIndex = -1;//上一次的index值
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			int index = (int) (event.getY()/cellHeight);
			if(index != lastIndex){//求出点击到的那个字母对应索引
//				Log.e("tag","==============index="+indexArr[index]);
				if(onSelectedLetterListener!=null){
					onSelectedLetterListener.onSelecteLetter(indexArr[index]);
				}
					
			}
			lastIndex = index;
			break;
		case MotionEvent.ACTION_UP:
			lastIndex = -1;//恢复初始的值
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}
	
	/**
	 * 得到一个字符串对应像素的高度
	 * @param text
	 * @return
	 */
	private int getTextHeight(String text){
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
		
	}
	
	/**
	 *  选中某个字母的监听器 
	 */
	public interface OnSelectedLetterListener{
		public void onSelecteLetter(String letter);//参数letter 是选中的那个字母
	}
	
	private OnSelectedLetterListener onSelectedLetterListener;
	
	public void setOnSelectedLetterListener(OnSelectedLetterListener onSelectedLetterListener){
		this.onSelectedLetterListener = onSelectedLetterListener;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
