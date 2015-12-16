package com.haikuowuya.core.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/**
 * 颜色工具类
 */
public class ColorUtils
{
    /***
     * 根据primary color 来生成primaryDark color
     *
     * @param primaryColor primary color
     * @return primaryDark color
     */
    @ColorInt
    public static int getDarkerColor(@ColorInt int primaryColor)
    {
        int alpha = Color.alpha(primaryColor);
        int red = Color.red(primaryColor);
        int green = Color.green(primaryColor);
        int blue = Color.blue(primaryColor);
        return Color.argb(alpha, (int) (red * 0.9), (int) (green * 0.9), (int) (blue * 0.9));
    }

    /****
     * 判断给定的颜色是否是亮色
     *
     * @param color color
     * @return boolean
     */
    public static boolean isLightColor(@ColorInt int color)
    {
        return color == Color.WHITE || color != Color.BLACK && ((Color.red(color) * 0.2126f) + (Color.green(color) * 0.7152f) + (Color.blue(color) * 0.0722f)) > 160f;
    }

//    public static void setEdgeGlowColor(final RecyclerView recyclerView, final int color)
//    {
//        if (Build.VERSION.SDK_INT >= 21)
//        {
//            try
//            {
//                final Class<?> clazz = RecyclerView.class;
//                for (final String name : new String[]{"ensureTopGlow", "ensureBottomGlow"})
//                {
//                    Method method = clazz.getDeclaredMethod(name);
//                    method.setAccessible(true);
//                    method.invoke(recyclerView);
//                }
//                for (final String name : new String[]{"mTopGlow", "mBottomGlow"})
//                {
//                    final Field field = clazz.getDeclaredField(name);
//                    field.setAccessible(true);
//                    final Object edge = field.get(recyclerView); // android.support.v4.widget.EdgeEffectCompat
//                    final Field fEdgeEffect = edge.getClass().getDeclaredField("mEdgeEffect");
//                    fEdgeEffect.setAccessible(true);
//                    ((EdgeEffect) fEdgeEffect.get(edge)).setColor(color);
//                }
//            } catch (final Exception ignored)
//            {
//            }
//        }
//    }

}
