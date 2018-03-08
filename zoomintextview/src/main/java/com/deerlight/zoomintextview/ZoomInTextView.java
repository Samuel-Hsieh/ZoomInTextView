package com.deerlight.zoomintextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.v7.widget.AppCompatTextView;

import static com.deerlight.zoomintextview.DefaultConfig.*;

/**
 * Created by samuel_hsieh on 2018/3/6.
 */
public class ZoomInTextView extends AppCompatTextView {

    private String Text = TEXT;
    private float TextSize = TEXT_SIZE;
    private int TextColorRes = TEXT_COLOR_RES;
    private float TextMaxSize = TEXT_MAX_SIZE;
    private int Duration = DURATION;
    private boolean isTrigger = false;

    public ZoomInTextView(Context context, Builder builder) {
        super(context);
        readBuilder(builder);
        initialize();
        setResources();
    }

    public ZoomInTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(attrs);
        initialize();
    }

    public ZoomInTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(attrs);
        initialize();
    }

    private void readAttributes(AttributeSet attr) {
        TypedArray type = getContext().obtainStyledAttributes(attr, R.styleable.ZoomInTextView, 0, 0);
        TextMaxSize = type.getDimension(R.styleable.ZoomInTextView_textMaxSize, TextSize);
        Duration = type.getInteger(R.styleable.ZoomInTextView_duration, 1000);
        TextSize = getTextSize();
    }

    private void readBuilder(Builder builder) {
        Text = builder.Text;
        TextSize = builder.TextSize;
        TextColorRes = builder.TextColorRes;
        TextMaxSize = builder.TextMaxSize;
        Duration = builder.Duration;
    }

//    private void inflateView() {
//        View.inflate(getContext(), R.layout.view_zoom_in_textview, null);
//    }

    private void initialize() {
//        inflateView();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrigger) {
                    Stop(true);
                } else {
                    Start(true);
                }
            }
        });
    }

    private void Start(Boolean animate) {
        StartInternal(animate);
    }

    private void Stop(Boolean animate) {
        StopInternal(animate);
    }

    private void StartInternal(final Boolean animate) {
        if (animate) {
            ValueAnimator paddingAnimator = ValueAnimator.ofFloat(0, 1);
            paddingAnimator.setDuration(Duration);
            paddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float m = TextMaxSize - TextSize;
                    if (m < 0) {
                        return;
                    }
                    float reSize = TextSize + animation.getAnimatedFraction() * m;
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, reSize);
                }
            });
            paddingAnimator.start();
        }
        isTrigger = true;
    }

    private void StopInternal(Boolean animate) {
        if (animate) {
            ValueAnimator paddingAnimator = ValueAnimator.ofFloat(0, 1);
            paddingAnimator.setDuration(Duration);
            paddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float m = TextMaxSize - TextSize;
                    if (m < 0) {
                        return;
                    }
                    float reSize = TextMaxSize - animation.getAnimatedFraction() * m;
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, reSize);
                }
            });
            paddingAnimator.start();
        }
        isTrigger = false;
    }

    private void setResources() {
        if (Text != null) {
            setText(Text);
        }
        if (TextSize != 12) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize);
        }
        if (TextColorRes != -1) {
            setTextColorRes(TextColorRes, null);
        }
        if (TextMaxSize > TextSize) {
            setTextMaxSize(TextMaxSize);
        }
        if (Duration != 1000) {
            setDuration(Duration);
        }
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        TextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, getResources().getDisplayMetrics());
    }

    public void setTextSize(@DimenRes int size) {
        TextSize = getResources().getDimension(size);
        super.setTextSize(TextSize);
    }

    public void setTextMaxSize(float size) {
        TextMaxSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, getResources().getDisplayMetrics());
    }

    public void setTextMaxSize(@DimenRes int size) {
        TextMaxSize = getResources().getDimension(size);
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public void setTextColorRes(@ColorRes int color, @Nullable Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextColor(getResources().getColor(color, theme));
        } else {
            setTextColor(getResources().getColor(color));
        }
    }

    public static class Builder {

        private String Text = TEXT;
        private float TextSize = TEXT_SIZE;
        private int TextColorRes = TEXT_COLOR_RES;
        private float TextMaxSize = TEXT_MAX_SIZE;
        private int Duration = DURATION;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder Text(String val) {
            Text = val;
            return this;
        }

        public Builder Text(@StringRes int val) {
            Text = context.getString(val);
            return this;
        }

        public Builder TextSize(float val) {
            TextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, val,
                    context.getResources().getDisplayMetrics());
            return this;
        }

        public Builder TextSize(@DimenRes int val) {
            TextSize = context.getResources().getDimension(val);
            return this;
        }

        public Builder TextColorRes(@ColorRes int val) {
            TextColorRes = val;
            return this;
        }

        public Builder TextMaxSize(float val) {
            TextMaxSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, val,
                    context.getResources().getDisplayMetrics());
            return this;
        }

        public Builder TextMaxSize(@DimenRes int val) {
            TextMaxSize = context.getResources().getDimension(val);
            return this;
        }

        public Builder Duration(int val) {
            Duration = val;
            return this;
        }

        public ZoomInTextView build() {
            return new ZoomInTextView(context, this);
        }
    }
}
