package com.jxkj.readapp.util;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * edittext 点击隐藏hint
 * 
 * @author Administrator
 *
 */
public class EditTextFocusHideHint implements OnFocusChangeListener {

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		EditText et = (EditText) v;
		if (!hasFocus) {
			et.setHint(et.getTag().toString());
		} else {
			String hint = et.getHint().toString();
			et.setTag(hint);
			et.setHint("");
			et.setCursorVisible(true);
		}
	}

}
