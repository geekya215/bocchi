package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Float_info {
//     u1 tag;
//     u4 bytes;
// }
//
public final class ConstantFloatInfo extends ConstantPoolInfo {
    private final long bytes;

    private ConstantFloatInfo(long bytes) {
        super(ConstantPoolInfo.TAG_FLOAT);
        this.bytes = bytes;
    }

    public static ConstantFloatInfo create(ClassReader classReader) {
        long bytes = classReader.readU4();
        return new ConstantFloatInfo(bytes);
    }

    public long getBytes() {
        return bytes;
    }
}
