package com.ruffian.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.ruffian.library.widget.helper.RTextViewHelper;
import com.ruffian.library.widget.iface.RHelper;

/**
 * RTextView
 *
 * @author ZhongDaFeng
 */
@SuppressLint("AppCompatCustomView")
public class RTextView extends TextView implements RHelper<RTextViewHelper> {

    private RTextViewHelper mHelper;

    public RTextView(Context context) {
        this(context, null);
    }

    public RTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = new RTextViewHelper(context, this, attrs);
    }

    @Override
    public RTextViewHelper getHelper() {
        return mHelper;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mHelper.setEnabled(enabled);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


}
