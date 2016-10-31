/*
 * Copyright (C) 2016 hejunlin <hejunlin2013@gmail.com>
 * Github:https://github.com/hejunlin2013/TVSample
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
package com.hejunlin.tvsample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Created by hejunlin on 2015/7/19.
 * blog: http://blog.csdn.net/hejjunlin
 */
public class MetroItemFrameLayout extends FrameLayout implements IMetroItemRound {

    private MetroItemRound mMetroItemRound;

    public MetroItemFrameLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MetroItemFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MetroItemFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mMetroItemRound = new MetroItemRound(this, context, attrs, defStyle);
        setWillNotDraw(false);

    }

    @Override
    public void draw(Canvas canvas) {
        mMetroItemRound.draw(canvas);
    }

    @Override
    public MetroItemRound getRoundImpl() {
        return mMetroItemRound;
    }

    @Override
    public void drawRadious(Canvas canvas) {
        super.draw(canvas);
    }
}

