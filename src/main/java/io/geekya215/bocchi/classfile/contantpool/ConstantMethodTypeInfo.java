package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_MethodType_info {
//     u1 tag;
//     u2 descriptor_index;
// }
//
public final class ConstantMethodTypeInfo extends ConstantPoolInfo {
    private final int descriptorIndex;

    private ConstantMethodTypeInfo(int descriptorIndex) {
        super(ConstantPoolInfo.TAG_METHOD_TYPE);
        this.descriptorIndex = descriptorIndex;
    }

    public static ConstantMethodTypeInfo create(ClassReader classReader) {
        int descriptorIndex = classReader.readU2();
        return new ConstantMethodTypeInfo(descriptorIndex);
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
