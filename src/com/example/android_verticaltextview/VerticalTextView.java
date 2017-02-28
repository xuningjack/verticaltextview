package com.example.android_verticaltextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;



/**
 * 竖向TextView控件
 * @author Jack
 * @version 创建时间：2014-1-7  上午11:35:08
 */
public class VerticalTextView extends TextView {

	/**文本输出的顺序：从下到上或从上到下*/
	private boolean topDown = false;
	/**设置渐变色的范围，避免在onDraw()的过程中声明对象*/
	Shader shader = new LinearGradient(0, 0, 100, 0, new int[]{Color.GREEN,Color.RED}, new float[]{0, 1}, TileMode.CLAMP);
			
	
	
	public VerticalTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		final int gravity = getGravity();
		if (Gravity.isVertical(gravity) && (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {
			
			setGravity((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
			topDown = false;
		} else{
			
			topDown = true;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		TextPaint textPaint = getPaint();
		textPaint.setColor(getCurrentTextColor());
		textPaint.drawableState = getDrawableState();
		textPaint.setShader(shader);
		canvas.save();
		if (topDown) {  //从上到下的输出顺序
			canvas.translate(getWidth(), 0);   
			canvas.rotate(90);
		} else {  //从下到上的输出顺序
			canvas.translate(0, getHeight());
			canvas.rotate(-90);
		}
		canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
		getLayout().draw(canvas);
		//This call balances a previous call to save(), 
		//and is used to remove all modifications to the matrix/clip state since the last save call. 
		canvas.restore();   
	}
}



