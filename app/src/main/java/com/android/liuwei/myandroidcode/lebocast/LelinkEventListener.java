package com.android.liuwei.myandroidcode.lebocast;

import com.hpplay.sdk.source.browse.api.IBrowseListener;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import java.util.List;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-06-20
 * Time: 14:50
 */
public class LelinkEventListener implements IBrowseListener
{
    private List<LelinkServiceInfo> mLelinkServiceInfoList;
    private boolean mStartSearchDevice = false;
    private boolean mLinkServiceInfoChanged = false;

    @Override
    public void onBrowse(int i, List<LelinkServiceInfo> list)
    {
        mLelinkServiceInfoList = list;
    }

    private class FindDeviceThread extends Thread
    {
        @Override
        public void run()
        {
            while (mStartSearchDevice)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
