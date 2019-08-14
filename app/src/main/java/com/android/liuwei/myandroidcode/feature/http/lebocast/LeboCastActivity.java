package com.android.liuwei.myandroidcode.feature.http.lebocast;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.liuwei.myandroidcode.R;
import com.hpplay.sdk.source.api.IConnectListener;
import com.hpplay.sdk.source.api.ILelinkPlayerListener;
import com.hpplay.sdk.source.api.LelinkPlayer;
import com.hpplay.sdk.source.api.LelinkPlayerInfo;
import com.hpplay.sdk.source.browse.api.IBrowseListener;
import com.hpplay.sdk.source.browse.api.ILelinkServiceManager;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;
import com.hpplay.sdk.source.browse.api.LelinkServiceManager;
import com.hpplay.sdk.source.browse.api.LelinkSetting;
import com.hpplay.sdk.source.browse.api.LelinkSetting.LelinkSettingBuilder;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.android.liuwei.myandroidcode.base.BaseActivity;

/**
 * User: liuwei
 * Date: 2018-06-19
 * Time: 19:06
 */
public class LeboCastActivity extends BaseActivity
{
    private static final String APPKEY = "10320";
    private static final String APPSECRET = "5d46c002f45875c3f3b3c05d4d2cdc72";

    @BindView(R.id.cast_devices_list)
    RecyclerView mRecyclerView;
    private DeviceListAdapter mDeviceListAdapter;

    private ILelinkServiceManager mLelinkServiceManager;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_lebo_cast;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mDeviceListAdapter = new DeviceListAdapter(getLayoutInflater(), mOnClickListener));

        LelinkSetting lelinkSetting = new LelinkSettingBuilder(APPKEY, APPSECRET).build();
        mLelinkServiceManager = LelinkServiceManager.getInstance(this);
        mLelinkServiceManager.setDebug(true);
        mLelinkServiceManager.setLelinkSetting(lelinkSetting);
        mLelinkServiceManager.setOnBrowseListener(mIBrowseListener);

        List<LelinkServiceInfo> list = mLelinkServiceManager.getLelinkServiceInfos();
        if (list != null)
        {
            mDeviceListAdapter.setLelinkList(list);
        }
    }

    private Handler mHandler = new Handler();

    private IBrowseListener mIBrowseListener = new IBrowseListener()
    {
        @Override
        public void onBrowse(int resultCode, final List<LelinkServiceInfo> list)
        {
            //Logger.d(getClass().getSimpleName() + " onBrowse(int resultCode, List<LelinkServiceInfo> list): %s,%s", resultCode, list != null ? list.size() : 0);

            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    if (mDeviceListAdapter != null)
                    {
                        mDeviceListAdapter.setLelinkList(list);
                    }
                }
            });
        }
    };

    private OnClickListener mOnClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Object tag = v.getTag();

            if (tag instanceof LelinkServiceInfo)
            {
                LelinkServiceInfo lelinkServiceInfo = (LelinkServiceInfo) tag;

                if (lelinkServiceInfo.isOnLine())
                {
                    if (!lelinkServiceInfo.isConnect())
                    {
                        connect(lelinkServiceInfo);
                    }
                    else
                    {
                        disconnect(lelinkServiceInfo);
                    }
                }
            }
        }
    };

    @OnClick(R.id.cast_find_start)
    public void startBrowse()
    {
        mLelinkServiceManager.browse(ILelinkServiceManager.TYPE_DLNA);
    }

    @OnClick(R.id.cast_find_stop)
    public void stopBrowse()
    {
        mLelinkServiceManager.stopBrowse();
    }

    @OnClick(R.id.cast_disconnect)
    public void disconnect()
    {
        if (null != mLelinkServiceManager)
        {
            mLelinkServiceManager.release();
        }
    }

    @OnClick(R.id.cast_stop_play)
    public void stopPlay()
    {
        if (null != mLelinkPlayer)
        {
            mLelinkPlayer.stop();
        }
    }

    private LelinkPlayer mLelinkPlayer;

    private void connect(LelinkServiceInfo info)
    {
        LelinkPlayer leLinkPlayer = new LelinkPlayer(this);
        leLinkPlayer.setConnectListener(mIConnectListener);
        leLinkPlayer.setPlayerListener(mILelinkPlayerListener);
        //leLinkPlayer.connect(info);
        LelinkPlayerInfo lelinkPlayerInfo = new LelinkPlayerInfo();
        lelinkPlayerInfo.setLelinkServiceInfo(info);
        lelinkPlayerInfo.setType(LelinkPlayerInfo.TYPE_VIDEO);
        lelinkPlayerInfo.setUrl("https://neulionds1898lon-a.akamaihd.net/nldsu/osn/live1/as/live/nlncp/live1_hd_ipad.m3u8");
        leLinkPlayer.setDataSource(lelinkPlayerInfo);
        leLinkPlayer.start();

        mLelinkPlayer = leLinkPlayer;
    }

    private void disconnect(LelinkServiceInfo info)
    {
        if (mLelinkPlayer != null)
        {
            mLelinkPlayer.disConnect(info);
        }
    }

    private IConnectListener mIConnectListener = new IConnectListener()
    {
        @Override
        public void onConnect(LelinkServiceInfo lelinkServiceInfo, int i)
        {
            Toast.makeText(LeboCastActivity.this, "连接成功：" + lelinkServiceInfo.getName(), Toast.LENGTH_SHORT).show();

            Logger.w("连接成功 " + lelinkServiceInfo.getName() + " %s", i);

            //            LelinkPlayer leLinkPlayer = mLelinkPlayer;
            //            if (leLinkPlayer != null)
            //            {
            //                LelinkPlayerInfo lelinkPlayerInfo = new LelinkPlayerInfo();
            //                lelinkPlayerInfo.setLelinkServiceInfo(lelinkServiceInfo);
            //                lelinkPlayerInfo.setType(LelinkPlayerInfo.TYPE_VIDEO);
            //                //lelinkPlayerInfo.setUrl("https://playerdemo.freewheel.tv/hls/stream/live.m3u8");
            //                lelinkPlayerInfo.setUrl("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
            //                leLinkPlayer.setDataSource(lelinkPlayerInfo);
            //                leLinkPlayer.start();
            //            }
        }

        @Override
        public void onDisconnect(LelinkServiceInfo lelinkServiceInfo, int i, int i1)
        {
            Toast.makeText(LeboCastActivity.this, "断开连接：" + lelinkServiceInfo.getName(), Toast.LENGTH_SHORT).show();

            Logger.w("断开连接 " + lelinkServiceInfo.getName() + " %s,%s", i, i1);
        }
    };

    private ILelinkPlayerListener mILelinkPlayerListener = new ILelinkPlayerListener()
    {
        @Override
        public void onStart()
        {
            Logger.d("onStart");
        }

        @Override
        public void onPause()
        {
            Logger.d("onPause");
        }

        @Override
        public void onCompletion()
        {
            Logger.d("onCompletion");
        }

        @Override
        public void onStop()
        {
            Logger.d("onStop");
        }

        @Override
        public void onSeekComplete(int i)
        {
            Logger.d("onSeekComplete");
        }

        @Override
        public void onInfo(int i, int i1)
        {
            Logger.w("onInfo: %s,%s", i, i1);
        }

        @Override
        public void onError(int i, int i1)
        {
            Logger.w("onError: %s,%s", i, i1);
        }

        @Override
        public void onVolumeChanged(float v)
        {
            Logger.d("onStart");
        }

        @Override
        public void onPositionUpdate(long l, long l1)
        {
            //Logger.d("onStart");
        }
    };

    @Override
    protected void onDestroy()
    {
        if (null != mLelinkServiceManager)
        {
            mLelinkServiceManager.release();
        }

        super.onDestroy();
    }
}
