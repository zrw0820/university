package com.mysoft.university.mvp.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mysoft.university.R;

/**
 * ClearEditText
 * <p>
 * Created by Zourw on 2016/8/25.
 */
public class ClearEditText extends AppCompatEditText {
    private Drawable clearIcon;
    private boolean hasFocus;

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearIcon = getCompoundDrawables()[2];
        if (clearIcon == null) {
            clearIcon = ContextCompat.getDrawable(getContext(), R.drawable.clear_edit_text);
        }

        setClearIconVisibility(false);

        setOnFocusChangeListener((v, hasFocus) -> {
            ClearEditText.this.hasFocus = hasFocus;
            setClearIconVisibility(hasFocus && !TextUtils.isEmpty(getText()));
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (hasFocus) {
                    setClearIconVisibility(!TextUtils.isEmpty(s));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setClearIconVisibility(boolean visibility) {
        Drawable[] drawables = getCompoundDrawables();
        if (visibility) {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], clearIcon, drawables[3]);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], null, drawables[3]);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            clearIcon = null;
        }
        super.setEnabled(enabled);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(getText());
    }

    public void setInput(CharSequence input) {
        setText(input);
        setSelection(input.length());
    }

    public String getInput() {
        return getText().toString();
    }
}
