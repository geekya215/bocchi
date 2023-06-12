package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_MethodHandle_info {
//     u1 tag;
//     u1 reference_kind;
//     u2 reference_index;
// }
public final class ConstantMethodHandleInfo extends ConstantPoolInfo {
    private final int refKind;
    private final int refIndex;

    private ConstantMethodHandleInfo(int refKind, int refIndex) {
        super(ConstantPoolInfo.TAG_METHOD_HANDLE);
        this.refKind = refKind;
        this.refIndex = refIndex;
    }

    public static ConstantMethodHandleInfo create(ClassReader classReader) {
        int refKink = classReader.readU1();
        int refIndex = classReader.readU2();
        return new ConstantMethodHandleInfo(refKink, refIndex);
    }

    public int getRefKind() {
        return refKind;
    }

    public int getRefIndex() {
        return refIndex;
    }
}
