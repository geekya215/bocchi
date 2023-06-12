package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Integer_info {
//     u1 tag;
//     u4 bytes;
// }
//
public final class ConstantIntegerInfo extends ConstantPoolInfo {
    private final long bytes;

    private ConstantIntegerInfo(long bytes) {
        super(ConstantPoolInfo.TAG_INTEGER);
        this.bytes = bytes;
    }

    public static ConstantIntegerInfo create(ClassReader classReader) {
        long bytes = classReader.readU4();
        return new ConstantIntegerInfo(bytes);
    }

    public long getBytes() {
        return bytes;
    }
}
