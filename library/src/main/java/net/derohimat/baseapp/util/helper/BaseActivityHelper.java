package net.derohimat.baseapp.util.helper;

import android.support.v7.app.ActionBar;

import net.derohimat.baseapp.ui.BaseActivity;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BaseActivityHelper {

    public static void setTitle(BaseActivity baseActivity, int title) {
        setTitle(baseActivity, baseActivity.getString(title));
    }

    public static void setTitle(BaseActivity baseActivity, String title) {
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(title);
        else
            baseActivity.setTitle(title);
    }

    public static void setToolbarVisibility(BaseActivity baseActivity, boolean isVisible) {
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            if (isVisible)
                actionBar.show();
            else
                actionBar.hide();
        }
    }

    public static boolean isToolbarVisible(BaseActivity baseActivity) {
        ActionBar actionBar = baseActivity.getSupportActionBar();
        return actionBar != null && actionBar.isShowing();
    }

    public static ActionBar getActionBar(BaseActivity baseActivity) {
        return baseActivity.getSupportActionBar();
    }
}
