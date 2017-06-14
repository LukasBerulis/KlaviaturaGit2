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

package com.example.android.softkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;

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
        return  SymbolsShift;
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
        super.onDraw(canvas);

        List<Key> keys = getKeyboard().getKeys();
        for (Key key : keys) {
            if (key.codes[0] == -1) {
                if (!Symbols && !SymbolsShift) {
                    int movex = 46;
                    int movey = 36;
                    if (isCaps()) {
                        Drawable npd = getResources().getDrawable(R.drawable.caps);

                        npd.setBounds(key.x + movex, key.y + movey,
                                key.x + npd.getMinimumWidth() + movex, key.y + npd.getMinimumHeight() + movey);
                        npd.draw(canvas);
                    } else if (isShifted()) {
                        Drawable npd = getResources().getDrawable(R.drawable.shift_on);
                        npd.setBounds(key.x + movex, key.y + movey,
                                key.x + npd.getMinimumWidth() + movex, key.y + npd.getMinimumHeight() + movey);
                        npd.draw(canvas);
                    } else {
                        Drawable npd = getResources().getDrawable(R.drawable.shift);
                        npd.setBounds(key.x + movex, key.y + movey,
                                key.x + npd.getMinimumWidth() + movex, key.y + npd.getMinimumHeight() + movey);
                        npd.draw(canvas);
                    }

                }
            }
                if (key.pressed) {
                    if (key.codes[0] == 10) {
                        Drawable npd = getResources().getDrawable(R.drawable.enter_dark_pressed);
//                    Drawable bg = getResources().getDrawable(R.drawable.transparent);
                        int movex = 29;
                        int movey = 45;
                        npd.setBounds(key.x + movex, key.y + movey,
                                key.x + npd.getMinimumWidth() + movex, key.y + npd.getMinimumHeight() + movey);
//                    bg.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                    bg.draw(canvas);
                        npd.draw(canvas);
//                    key.icon.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
//                    key.icon.draw(canvas);
                    } else if (key.codes[0] == 32) {
                        Drawable npd = getResources().getDrawable(R.drawable.spacebar_pressed);
                        int movex = -43;
                        int movey = 7;
                        npd.setBounds(key.x + movex, key.y + movey,
                                key.x + npd.getMinimumWidth() + movex, key.y + npd.getMinimumHeight() + movey);
                        npd.draw(canvas);
                    } else {
                        Drawable bg = getResources().getDrawable(R.drawable.darken);
                        bg.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                        bg.draw(canvas);
                    }
                }

        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(R.drawable.spacebar));
        invalidateAllKeys();
    }

    public  void setShift()
    {

    }

}
