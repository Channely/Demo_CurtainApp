package com.li.demo.curtain.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.li.demo.curtain.R;

/**
 * Created by shoubo.lsb on 13-9-10.
 */
public class CurtainView extends RelativeLayout {

    public CurtainView(Context context) {
        super(context);
        initUI();
    }

    public CurtainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public CurtainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    private void initUI() {
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.img_curtain);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
    }
}
