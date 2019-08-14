package com.android.liuwei.myandroidcode.feature.thread.scheduler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import com.android.liuwei.myandroidcode.R;
import com.android.liuwei.myandroidcode.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SchedulerActivity extends BaseActivity
{
    private List<ScheduleTask> mTask = new ArrayList<>();
    private ViewGroup mContentGroup;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_scheduler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mContentGroup = findViewById(R.id.schedule_content);

        findViewById(R.id.schedule_add).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final int index = mTask.size();

                ScheduleTask task = new ScheduleTask
                        .Builder()
                        .setScheduleInterval(5000 * (index + 1))
                        .setNotifyInUIThread(true)
                        .builder();

                task.start();

                mTask.add(task);

                final View inflaterView = getLayoutInflater().inflate(R.layout.comp_new_schedule, mContentGroup, false);

                mContentGroup.addView(inflaterView);

                inflaterView.setOnLongClickListener(new OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        mTask.get(index).stop();

                        mTask.remove(index);

                        mContentGroup.removeView(inflaterView);

                        return true;
                    }
                });

                inflaterView.findViewById(R.id.resume).setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mTask.get(index).resume();
                    }
                });

                inflaterView.findViewById(R.id.pause).setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mTask.get(index).pause();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
