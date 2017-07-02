package com.icm.calapp.custom;

import com.icm.calapp.R;

public class CategotyIcon {
    public static final int TYPE_CHICKEN = 1;
    public static final int TYPE_SHRIMP = 2;
    public static final int TYPE_PORK = 3;
    public static final int TYPE_CRAB = 4;
    public static final int TYPE_WATER = 5;
    public static final int TYPE_YOGURT = 6;
    public static final int TYPE_MILK = 7;

    public static int getIcon(int type) {
        int icon = 0;
        switch (type) {
            case TYPE_CHICKEN:
                icon = R.drawable.img_chicken2x;
                break;
            case TYPE_SHRIMP:
                icon = R.drawable.img_shrimp2x;
                break;
            case TYPE_PORK:
                icon = R.drawable.img_pork2x;
                break;
            case TYPE_CRAB:
                icon = R.drawable.img_crab2x;
                break;
            case TYPE_WATER:
                icon = R.drawable.img_water2x;
                break;
            case TYPE_YOGURT:
                icon = R.drawable.img_yogurt2x;
                break;
            case TYPE_MILK:
                icon = R.drawable.img_milk2x;
                break;
            default:
                break;

        }
        return icon;
    }
}
