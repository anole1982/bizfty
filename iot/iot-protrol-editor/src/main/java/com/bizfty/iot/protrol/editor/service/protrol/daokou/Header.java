package com.bizfty.iot.protrol.editor.service.protrol.daokou;

public class Header {
    private static final byte HEART = 0x0F;
    private static final byte DATA = (byte)0x8F;
    private byte[] head = { 0x71,0x6B,0x6E,0x65,0x74 };
    private byte code = 0x02;
    private byte version;
    private byte type;
    private byte[] length;
    private byte[] tail = {(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF };

}
