package com.app.mydrai.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;



public class FontCache {

    public static final int NORMAL = 1;
    public static final int BOLD = 2;

    private static SparseArray<Typeface> fontCache = new SparseArray<>();


    public static Typeface getTypeface(Context context) {
        return createTypeface(context, NORMAL);
    }

    public static Typeface getTypefacee(Context context, int type) {
        return createTypeface(context, type);
    }


    public static Typeface getTypeface(Context context, String fontType) {
        if (fontType != null) {
            if (fontType.equalsIgnoreCase("bold")) {
                return createTypeface(context, BOLD);
            } else {
                return createTypeface(context, NORMAL);
            }
        }
        return createTypeface(context, NORMAL);
    }


    private static Typeface createTypeface(Context context, int type) {
        Typeface typeface = fontCache.get(type);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + getFontName(type));
            } catch (Exception e) {
                return null;
            }

            fontCache.put(type, typeface);
        }
        return typeface;
    }

    private static String getFontName(int type) {
        switch (type) {
            case NORMAL:
                return "DMSans-Regular.ttf";
            case BOLD:
                return "DMSans-Bold.ttf";
            default:
                return "DMSans-Medium.ttf";
        }
    }

}
