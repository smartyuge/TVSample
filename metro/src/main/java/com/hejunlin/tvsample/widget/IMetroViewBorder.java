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

import android.view.View;

/**
 * Created by hejunlin on 2016/10/13.
 * blog: http://blog.csdn.net/hejjunlin
 */
public interface IMetroViewBorder {

    void onFocusChanged(View target, View oldFocus, View newFocus);

    void onScrollChanged(View target, View attachView);

    void onLayout(View target, View attachView);

    void onTouchModeChanged(View target, View attachView, boolean isInTouchMode);

    void onAttach(View target, View attachView);

    void OnDetach(View targe, View view);

}
