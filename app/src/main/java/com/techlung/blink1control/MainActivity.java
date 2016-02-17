package com.techlung.blink1control;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Arrays;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    TextView connectResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.connectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBlink();
            }
        });

        connectResult = (TextView) findViewById(R.id.connectResult);
    }

    private void connectBlink() {
        connectUsb();
    }

    private void connectUsb() {


            String result = "";

        HidBridge bridge = new HidBridge(this, 493, 10168);

            result += bridge.OpenDevice() + "\n";

            byte[] bytes = new byte[8];

            bytes[0] = (byte)0x01; // 1
            bytes[1] = (byte)0x63; // 'c' for color
            bytes[2] = (byte)0xff;  // r
            bytes[3] = (byte)0x00;  // g
            bytes[4] = (byte)0x00;  // b
            bytes[5] = (byte)0x01;
            bytes[6] = (byte)0xff;
            bytes[7] = (byte)0x00;

            result += Arrays.toString(bytes) + "\n";
            result += bridge.WriteData(bytes) + "\n";

            bridge.CloseTheDevice();

            connectResult.setText(result);


        /*
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();

        connectResult.setText("Nothing");

        String results = "";
        for (Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {

            UsbDevice device = entry.getValue();


            results += "KEY: " + entry.getKey() + " \n ";
            results += "getDeviceName " + entry.getValue().getDeviceName()+ " \n ";
            //results += "getManufacturerName " + entry.getValue().getManufacturerName();
            //results += "getSerialNumber " + entry.getValue().getSerialNumber();
            // results += "getProductName " + entry.getValue().getProductName();
            // results += "getVersion " + entry.getValue().getVersion();
            results += "getDeviceClass " + entry.getValue().getDeviceClass()+ " \n ";
            results += "getDeviceId " + entry.getValue().getDeviceId()+ " \n ";
            results += "getDeviceProtocol " + entry.getValue().getDeviceProtocol()+ " \n ";
            results += "getDeviceSubclass " + entry.getValue().getDeviceSubclass()+ " \n ";
            results += "getConfigurationCount " + entry.getValue().getConfigurationCount();
            results += "getInterfaceCount " + entry.getValue().getInterfaceCount()+ " \n ";
            results += "getProductId " + entry.getValue().getProductId()+ " \n ";
            results += "getVendorId " + entry.getValue().getVendorId()+ " \n ";
            results += "-----------------------------------\n ";




            if (device.getProductId() == 493 && device.getVendorId() == 10168) {

                UsbConfiguration config = device.getConfiguration(0);

                results += "config.getInterfaceCount " + config.getInterfaceCount()+ " \n ";
                UsbInterface usbInterface = config.getInterface(0);


                results += "usbInterface.getEndpointCount() " + usbInterface.getEndpointCount()+ " \n ";

            }


        }
*/

        //connectResult.setText(results);


    }
}
