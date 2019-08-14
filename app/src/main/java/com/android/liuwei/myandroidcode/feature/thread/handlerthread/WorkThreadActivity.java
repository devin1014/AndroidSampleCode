package com.android.liuwei.myandroidcode.feature.thread.handlerthread;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;
import com.android.liuwei.myandroidcode.base.LogUtil;

import butterknife.OnClick;

public class WorkThreadActivity extends BaseActivity
{
    private WorkThread mWorkThread;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mWorkThread = new WorkThread();

        mWorkThread.start();
    }

    @OnClick(R.id.handler_post_work)
    void onPostButtonClick()
    {
        for (int msg = 1; msg <= 5; msg++)
        {
            LogUtil.info(this, String.format("sendEmptyMessage: %s", msg));
            mWorkThread.getHandler().sendEmptyMessage(msg);
        }
    }

    @OnClick(R.id.handler_exit)
    void onExitButtonClick()
    {
        mWorkThread.quit();
    }
}
