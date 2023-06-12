package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Utf8_info {
//     u1 tag;
//     u2 length;
//     u1 bytes[length];
// }
//
public final class ConstantUTF8Info extends ConstantPoolInfo {
    private final int length;
    private final byte[] bytes;

    private ConstantUTF8Info(int length, byte[] bytes) {
        super(TAG_UTF8);
        this.length = length;
        this.bytes = bytes;
    }

    public static ConstantUTF8Info create(ClassReader classReader) {
        int length = classReader.readU2();
        byte[] bytes = classReader.readNBytes(length);
        return new ConstantUTF8Info(length, bytes);
    }

    public int getLength() {
        return length;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
