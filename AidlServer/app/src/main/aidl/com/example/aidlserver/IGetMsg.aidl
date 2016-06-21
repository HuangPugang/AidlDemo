// IGetMsg.aidl
package com.example.aidlserver;
import com.example.aidlserver.Message2;
import com.example.aidlserver.User;
// Declare any non-default types here with import statements

interface IGetMsg {
    List<Message2> getMsg(in User us);
}
