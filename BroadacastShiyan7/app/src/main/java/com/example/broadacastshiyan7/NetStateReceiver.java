package com.example.broadacastshiyan7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1)
    {
        //判断当前是否有网络连接
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        //先判断有无网络
        if(isConnected == false)
        {
            Toast.makeText(context, "当前无网络，请检查移动设备的网络连接", Toast.LENGTH_SHORT)
                    .show();
            Log.i("TAG","网络未连接");
        }
        /*activeNetwork中各种参数的用法：
        activeNetwork.getTypeName(); 以何种方式连线
        activeNetwork.isAvailable(); 当前网络是否可用(true)
        activeNetwork.isFailover();网络有问题*/
        else
        {
           //判断网络可不可用
            if (!activeNetwork.isAvailable()
                    || activeNetwork.isFailover())
            {
                Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show();
                Log.i("TAG","当前网络不可用");
            }
            else
            {
                //判断连接网络的类型
                boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                if (!isWiFi)
                {
                    Toast.makeText(context, "已连接上移动数据", Toast.LENGTH_SHORT)
                            .show();
                    Log.i("TAG","已成功连接网络。网络类型为：移动数据");
                } else
                {
                    Toast.makeText(context, "已连接上WIFI数据", Toast.LENGTH_SHORT)
                            .show();
                    Log.i("TAG","已成功连接网络。网络类型为：WIFI数据");
                }
            }
        }
    }
}
