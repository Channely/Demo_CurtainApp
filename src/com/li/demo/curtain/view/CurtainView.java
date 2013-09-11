package com.li.demo.curtain.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.li.demo.curtain.R;

/**
 * Created by shoubo.lsb on 13-9-10.
 */
public class CurtainView extends RelativeLayout implements GestureDetector.OnGestureListener {

    private static final float CURTAIN_OPEN_HEIGHT = 110f;
    private ImageView imageView;
    private GestureDetectorCompat gestureDetectorCompat;
    private AnimatorSet curtainAnimatorSet;

    public CurtainView(Context context) {
        super(context);
        init();
    }

    public CurtainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurtainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addImage();
        initListener();
        initAnimation();
    }

    private void initAnimation() {
        curtainAnimatorSet = new AnimatorSet();
        ObjectAnimator curtainOpenAnimator = ObjectAnimator.ofFloat(this, "y", 0, -CURTAIN_OPEN_HEIGHT).setDuration(200);
        curtainOpenAnimator.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator curtainBounceAnimation = ObjectAnimator.ofFloat(this, "y", -CURTAIN_OPEN_HEIGHT, 0).setDuration(1000);
        curtainBounceAnimation.setInterpolator(new BounceInterpolator());

        curtainAnimatorSet.playSequentially(curtainOpenAnimator, curtainBounceAnimation);
    }

    private void initListener() {
        gestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
        imageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetectorCompat.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void addImage() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        imageView = new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.img_curtain);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
    }

    private void bounceCurtain() {
        if (curtainAnimatorSet.isStarted()) return;
        curtainAnimatorSet.start();
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        bounceCurtain();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float velocityX, float velocityY) {
        Log.d("Gesture", String.format("Y1:%s, Y2:%s, Vy:%s\n", motionEvent.getY(), motionEvent2.getY(), velocityY));
        return true;
    }
}
