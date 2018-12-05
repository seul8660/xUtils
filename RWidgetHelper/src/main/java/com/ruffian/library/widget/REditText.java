package com.ruffian.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.ruffian.library.widget.helper.RTextViewHelper;
import com.ruffian.library.widget.iface.RHelper;

/**
 * REditText
 *
 * @author ZhongDaFeng
 */
@SuppressLint("AppCompatCustomView")
public class REditText extends EditText implements RHelper<RTextViewHelper> {

    private RTextViewHelper mHelper;

    public REditText(Context context) {
        this(context, null);
    }

    public REditText(Context context, AttributeSet attrs) {
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
