package com.example.tienbi.appspyware.mode;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by TienBi on 11/09/2016.
 */
public class WebService {
    public static final String NAME_SPACE="http://demoservice.vn/";
    public static final String METHOD_NAME1="LogIn";
    public static final String METHOD_NAME2="LoadSMSByIdMember";
    public static final String METHOD_NAME3="LoadCallByIdMember";
    public static final String SOAP_ACTION1=NAME_SPACE+METHOD_NAME1;
    public static final String SOAP_ACTION2=NAME_SPACE+METHOD_NAME2;
    public static final String SOAP_ACTION3=NAME_SPACE+METHOD_NAME3;
    public static final String URL="http://demoservice.somee.com/DemoService.asmx";

    public String logIn(String username, String password){
        SoapObject request = new SoapObject(NAME_SPACE,METHOD_NAME1);
        request.addProperty("username",username);
        request.addProperty("password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION1,envelope);
            SoapPrimitive data =(SoapPrimitive)envelope.getResponse();
            return data.toString();
        } catch (Exception e) {
            Log.e("Loi",e.toString());
        }
        return "-1";
    }
    public ArrayList<CallInfo> LoadCallResult(String idmember){
        ArrayList<CallInfo> arr = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE,METHOD_NAME3);
        request.addProperty("idmember",idmember);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION3,envelope);
            SoapObject data =(SoapObject)envelope.getResponse();
            for (int i = 0; i <data.getPropertyCount() ; i++) {
                SoapObject object = (SoapObject) data.getProperty(i);
                CallInfo callInfo=new CallInfo();
                if(object.hasProperty("PhoneNumber"))
                    callInfo.setPhoneNumber(object.getPropertyAsString("PhoneNumber"));
                if(object.hasProperty("Time"))
                    callInfo.setTime(object.getPropertyAsString("Time"));
                if(object.hasProperty("State"))
                    callInfo.setStatus(Integer.parseInt(object.getPropertyAsString("State")));
                arr.add(callInfo);
            }
            return arr;
        } catch (Exception e) {
            Log.e("Loi",e.toString());
        }
        return null;
    }
    public ArrayList<SMSInfo> LoadSmsResult(String idmember){
        ArrayList<SMSInfo> arr = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE,METHOD_NAME2);
        request.addProperty("idmember",idmember);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION2,envelope);
            SoapObject data =(SoapObject)envelope.getResponse();
            for (int i = 0; i <data.getPropertyCount() ; i++) {
                SoapObject object = (SoapObject) data.getProperty(i);
                SMSInfo smsInfo=new SMSInfo();
                if(object.hasProperty("PhoneNumber"))
                    smsInfo.setPhoneNumber(object.getPropertyAsString("PhoneNumber"));
                if(object.hasProperty("TimeStamp"))
                    smsInfo.setTimeStamp(object.getPropertyAsString("TimeStamp"));
                if(object.hasProperty("Body"))
                    smsInfo.setBody(object.getPropertyAsString("Body"));
                arr.add(smsInfo);
            }
            return arr;
        } catch (Exception e) {
            Log.e("Loi",e.toString());
        }
        return null;
    }
}
