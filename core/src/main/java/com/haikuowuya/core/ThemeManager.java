package com.haikuowuya.core;

import android.content.Context;

import java.util.ArrayList;

public class ThemeManager
{
    public static final int BASE_DARK = R.style.Base_AppTheme;
    private static final ThemeManager i = new ThemeManager();
    public Boolean useLightTheme;
    public Context cxt;
    int base;
    int colorPrimary;
    int colorAccent;
    int colorGrey;
    int colorComplimentary;
    int colorComplimentaryGrey;
    int colorBackground;
    ThemeItem currentTheme;

    ArrayList<OnThemeChangedListener> listeners;

    private ThemeManager()
    {
    }

    public static ThemeManager get()
    {
        return i;
    }

    public int getBase()
    {
        return BASE_DARK;
    }

    public void setBase(int newBase)
    {
        base = newBase;

    }

    public ThemeManager setContext(Context context)
    {
        cxt = context;
        update();
        return this;
    }

    public void update()
    {

    }

    public int getNavdrawerColor()
    {
        int color;
        if (useLightTheme)
        {
            color = R.color.secondary_text_default_material_light;
        } else
        {
            color = R.color.secondary_text_default_material_dark;
        }
        return color;
    }

    public int getCurrentGreyColorResource()
    {
        int color;
        if (useLightTheme)
        {
            color = 0x8A000000;
        } else
        {
            color = 0xB3ffffff;
        }
        return color;
    }

    public int getCurrentGreyColor()
    {
        return cxt.getResources().getColor(getCurrentGreyColorResource());
    }

    public ThemeItem getTheme()
    {
        return new ThemeItem.Builder().setBase(base).
                setColorPrimary(colorPrimary).setColorAccent(colorAccent).setColorGrey(colorGrey).setColorComplimentary(colorComplimentary).setColorComplimentaryGrey(colorComplimentaryGrey).build();
    }

    public void setTheme(ThemeItem theme)
    {
        for (OnThemeChangedListener l : listeners)
        {
            l.onThemeChanged(theme);
        }
        currentTheme = theme;
    }

    public interface OnThemeChangedListener
    {
        void onThemeChanged(ThemeItem newTheme);
    }

    public static class ThemeItem
    {
        int base;
        int colorPrimary;
        int colorAccent;
        int colorGrey;
        int colorComplimentary;
        int colorComplimentaryGrey;

        int statusBarColor;
        int backgroundColor;

        protected ThemeItem(int base, int primary, int accent, int grey, int complimentary, int complimentaryGrey)
        {
            this.base = base;
            this.colorPrimary = primary;
            this.colorAccent = accent;
            this.colorGrey = grey;
            this.colorComplimentary = complimentary;
            this.colorComplimentaryGrey = complimentaryGrey;
        }

        public int getBase()
        {
            return base;
        }

        public int getColorPrimary()
        {
            return colorPrimary;
        }

        public int getColorAccent()
        {
            return colorAccent;
        }

        public int getColorGrey()
        {
            return colorGrey;
        }

        public int getColorComplimentary()
        {
            return colorComplimentary;
        }

        public int getColorComplimentaryGrey()
        {
            return colorComplimentaryGrey;
        }

        static class Builder
        {
            private int base;
            private int colorPrimary;
            private int colorAccent;
            private int colorGrey;
            private int colorComplimentary;
            private int colorComplimentaryGrey;

            public Builder setBase(int base)
            {
                this.base = base;
                return this;
            }

            public Builder setColorPrimary(int colorPrimary)
            {
                this.colorPrimary = colorPrimary;
                return this;
            }

            public Builder setColorAccent(int colorAccent)
            {
                this.colorAccent = colorAccent;
                return this;
            }

            public Builder setColorGrey(int colorGrey)
            {
                this.colorGrey = colorGrey;
                return this;
            }

            public Builder setColorComplimentary(int colorComplimentary)
            {
                this.colorComplimentary = colorComplimentary;
                return this;
            }

            public Builder setColorComplimentaryGrey(int colorComplimentaryGrey)
            {
                this.colorComplimentaryGrey = colorComplimentaryGrey;
                return this;
            }

            public ThemeItem build()
            {
                return new ThemeItem(base, colorPrimary, colorAccent, colorGrey, colorComplimentary, colorComplimentaryGrey);
            }

        }
    }
}
