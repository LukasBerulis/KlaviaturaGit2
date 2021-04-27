/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.Cassio.android.softkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;

import androidx.core.content.ContextCompat;

import java.util.List;

public class LatinKeyboardView extends KeyboardView {


    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    //public boolean Shift = true;
    private boolean Caps = false;
    private boolean Symbols = false;
    private boolean SymbolsShift = false;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isSymbols() {
        return Symbols;
    }

    public boolean isSymbolsShift() {
        return SymbolsShift;
    }

    public void setSymbols(boolean set) {
        Symbols = set;
    }

    public void setSymbolsShift(boolean set) {
        SymbolsShift = set;
    }

    public boolean isCaps() {
        return Caps;
    }

    public void setCaps(boolean set) {
        Caps = set;
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {

        List<Key> keys = getKeyboard().getKeys();
        for (Key key : keys) {
            int heightOffset = Math.round(Converter.INSTANCE.dpToPx(this, 8));
            int sideMargin = Math.round(Converter.INSTANCE.dpToPx(this, 4));
            if (key.codes[0] == -1 || key.codes[0] == -2) {
                if (key.pressed) {
                    Drawable bg = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special_pressed);
                    bg.setBounds(key.x + sideMargin, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    bg.draw(canvas);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special);
                    drawable.setBounds(key.x + sideMargin, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    drawable.draw(canvas);
                }
            } else if (key.codes[0] == 44 || key.codes[0] == 46) {
                if (key.pressed) {
                    Drawable bg = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special_pressed);
                    bg.setBounds(key.x, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    bg.draw(canvas);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special);
                    drawable.setBounds(key.x, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    drawable.draw(canvas);
                }
            } else if (key.codes[0] == -5) {
                if (key.pressed) {
                    Drawable bg = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special_pressed);
                    bg.setBounds(key.x + sideMargin, key.y + heightOffset, key.x + key.width - sideMargin, key.y + key.height + heightOffset);
                    bg.draw(canvas);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.key_backround_special);
                    drawable.setBounds(key.x, key.y + heightOffset, key.x + key.width - sideMargin, key.y + key.height + heightOffset);
                    drawable.draw(canvas);
                }
            } else if (key.codes[0] != 10) {
                if (key.pressed) {
                    Drawable bg = getResources().getDrawable(R.drawable.key_backround_pressed);
                    bg.setBounds(key.x, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    bg.draw(canvas);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.key_backround);
                    drawable.setBounds(key.x, key.y + heightOffset, key.x + key.width, key.y + key.height + heightOffset);
                    drawable.draw(canvas);
                }
            }
        }
        super.onDraw(canvas);
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard) getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(R.drawable.spacebar));
        invalidateAllKeys();
    }
}
