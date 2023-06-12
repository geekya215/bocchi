package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Double_info {
//      u1 tag;
//      u4 high_bytes;
//      u4 low_bytes;
// }
//
public final class ConstantDoubleInfo extends ConstantPoolInfo {
    private final long highBytes;
    private final long lowBytes;

    private ConstantDoubleInfo(long highBytes, long lowBytes) {
        super(ConstantPoolInfo.TAG_DOUBLE);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    public static ConstantDoubleInfo create(ClassReader classReader) {
        long highBytes = classReader.readU4();
        long lowBytes = classReader.readU4();
        return new ConstantDoubleInfo(highBytes, lowBytes);
    }

    public long getHighBytes() {
        return highBytes;
    }

    public long getLowBytes() {
        return lowBytes;
    }
}
