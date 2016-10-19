package com.hejunlin.tvsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejunlin.tvsample.widget.MetroViewBorderImpl;
import com.hejunlin.tvsample.widget.AutoLayoutManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/**
 * Created by hejunlin on 2015/10/16.
 */
public class DetailListActivity extends Activity {

    private MetroViewBorderImpl mMetroViewBorderImpl;
    private static final String TAG = DetailListActivity.class.getSimpleName();
    private static final String URL = "http://gvod.video.51togic.com/api/v1/programs?mobile=false&version_code=102&device_id=419000000000000000005cc6d0b7e7e80000000000000000&city=%E5%8C%97%E4%BA%AC&vip=0&show_top_recommend=0";

    private final List<DetailInfo> mDetailInfoList = new CopyOnWriteArrayList<>();
    private OnDataFinishedListener mOnDataFinishedListener;

    public static interface StrProcessor {
        void OnParserJsonString(String line);
    }

    public static interface OnDataFinishedListener {
        void onPerformData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaillist);
        mMetroViewBorderImpl = new MetroViewBorderImpl(this);
        mMetroViewBorderImpl.setBackgroundResource(R.drawable.border_color);
        loadRecyclerViewMenuItem();
        final StrProcessor parser = new StrProcessor() {
            @Override
            public void OnParserJsonString(String json) {
                parseJson(json);
            }
        };

        mOnDataFinishedListener = new OnDataFinishedListener() {
            @Override
            public void onPerformData() {
                loadDataForRecyclerViewGridLayout();
            }
        };

        HttpUtils.asyncGet(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, ">> onFailure : e" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    return;
                }
                try {
                    String content = response.body().string();
                    Log.d(TAG, "detailInfo : content" + content);
                    parser.OnParserJsonString(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void parseJson(String content) {
        JSONObject jason = JSONObject.parseObject(content);
        JSONArray data = jason.getJSONArray("items");
        int totalCount = Integer.parseInt(jason.getString("count"));
        mDetailInfoList.clear();
        for (int i = 0; i < data.size(); i++) {
            DetailInfo info = new DetailInfo();
            JSONObject jsonObj = (JSONObject) data.get(i);
            String infotext = jsonObj.getString("infotext");
            String url = jsonObj.getString("poster");
            String title = jsonObj.getString("title");
            info.setPoster(url);
            info.setInfotext(infotext);
            info.setTitle(title);
            mDetailInfoList.add(info);
            Log.d(TAG, "parseJson mDetailInfoList " + mDetailInfoList.size());
        }
        for (int j = 0; j < mDetailInfoList.size(); j++) {
            Log.d(TAG, "parseJson mDetailInfoList : " + j + "content : " + mDetailInfoList.get(j).toString());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, ">> performData");
                mOnDataFinishedListener.onPerformData();
            }
        });
    }

    private void loadRecyclerViewMenuItem() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_menu_item);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        mMetroViewBorderImpl.attachTo(recyclerView);
        createOptionItemData(recyclerView, R.layout.detail_menu_item);
    }

    private void loadDataForRecyclerViewGridLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_detail_list);
        GridLayoutManager gridlayoutManager = new AutoLayoutManager(this, 4);
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridlayoutManager);
        recyclerView.setFocusable(false);
        mMetroViewBorderImpl.attachTo(recyclerView);
        createData(recyclerView, R.layout.detail_list_item);
    }

    private void createData(RecyclerView recyclerView, int id) {
        MyAdapter adapter = new MyAdapter(this, mDetailInfoList, id);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

    private void createOptionItemData(RecyclerView recyclerView, int id) {
        OptionItemAdapter adapter = new OptionItemAdapter(this, id);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

}
