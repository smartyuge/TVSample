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
package com.hejunlin.tvsample;

import com.google.gson.annotations.Expose;

/**
 * Created by hejunlin on 2016/10/15.
 * blog: http://blog.csdn.net/hejjunlin
 */
public class DetailInfo {

    @Expose
    private String title;
    @Expose
    private String poster;
    @Expose
    private String infotext;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfotext() {
        return infotext;
    }

    public void setInfotext(String infotext) {
        this.infotext = infotext;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "DetailInfo{" +
                "mTitle='" + title + '\'' +
                ", mTextDesc=" + infotext +
                ", mPostImageUrl='" + poster + '\'' +
                '}';
    }
}
