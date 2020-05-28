package com.app.hunterkill.terminaluart;

import com.hoho.android.usbserial.driver.UsbSerialPort;

/**
 * Created by hunterkill on 21/05/2020.
 */

public class SerialValue {
        public static int baudrate = 9600;
        public static int dataBits = UsbSerialPort.DATABITS_8;
        public static int parity = UsbSerialPort.PARITY_NONE;
        public static int stopBits = UsbSerialPort.STOPBITS_1;
}
