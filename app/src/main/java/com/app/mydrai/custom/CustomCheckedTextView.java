package com.app.mydrai.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.app.mydrai.R;

public class CustomCheckedTextView extends CheckedTextView {

    public CustomCheckedTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            try {
                TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextFont);
                String font = typedArray.getString(R.styleable.CustomTextFont_fontName);
                setTypeface(FontCache.getTypeface(getContext(), font));

                typedArray.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setTypeface(FontCache.getTypeface(getContext()));
        }


    } }