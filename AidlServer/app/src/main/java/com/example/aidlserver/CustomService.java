package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 16/6/21.
 */
public class CustomService extends Service {
    private static final String TAG = "main";
    private MsgBinder msgBinder=null;

    @Override
    public void onCreate() {
        super.onCreate();
        msgBinder = new MsgBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        msgBinder = null;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return msgBinder;
    }


    private class MsgBinder extends IGetMsg.Stub{
        @Override
        public List<Message2> getMsg(User us) throws RemoteException {
            List<Message2> list = new ArrayList<>();
            if (us.getId()==0){
                for (int i=0;i<1;i++){
                    list.add(new Message2(0,"我是0的消息","0",""));
                }
            }else if (us.getId()==1){
                for (int i=0;i<2;i++){
                    list.add(new Message2(1,"我是1的消息","1",""));
                }
            }
            return list;
        }
    }
}
