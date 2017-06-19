package com.diucity.dingding.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class PhoneEditText extends android.support.v7.widget.AppCompatEditText {
    private boolean insert;
    private boolean isRun;
    private OnTextChange listener;

    public PhoneEditText(Context context) {
        this(context, null);
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //count 1 增加  0 减少 else 添加空格
                if (start == length() - 1 && count == 1) {
                    insert = false;
                } else if (start == length() && count == 0) {
                    insert = false;
                } else {
                    insert = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isRun) {
                    isRun = false;
                    return;
                }
                if (listener != null) {
                    listener.onTextChange(s);
                }
                if (insert) {
                    String contents = getTextString();
                    int length = contents.length();
                    if (length >= 8) {
                        isRun = true;
                        contents = contents.substring(0, 3) + " " + contents.substring(3, 7) + " " + contents.substring(7);
                        setText(contents);
                        setSelection(contents.length());
                    } else if (length >= 4) {
                        isRun = true;
                        contents = contents.substring(0, 3) + " " + contents.substring(3);
                        setText(contents);
                        setSelection(contents.length());
                    }
                } else {
                    String contents = s.toString();
                    int length = contents.length();
                    if (length == 4) {
                        if (contents.substring(3).equals(" ")) { // -
                            isRun = true;
                            contents = contents.substring(0, 3);
                            setText(contents);
                            setSelection(contents.length());
                        } else { // +
                            isRun = true;
                            contents = contents.substring(0, 3) + " " + contents.substring(3);
                            setText(contents);
                            setSelection(contents.length());
                        }
                    } else if (length == 9) {
                        if (contents.substring(8).equals(" ")) { // -
                            isRun = true;
                            contents = contents.substring(0, 8);
                            setText(contents);
                            setSelection(contents.length());
                        } else {// +
                            isRun = true;
                            contents = contents.substring(0, 8) + " " + contents.substring(8);
                            setText(contents);
                            setSelection(contents.length());
                        }
                    }
                }

            }
        });
    }

    public String getTextString() {
        String s = this.getText().toString();
        return s.replaceAll(" ", "");
    }

    public void setListener(OnTextChange listener) {
        this.listener = listener;
    }

    public interface OnTextChange {
        void onTextChange(CharSequence s);
    }
}
