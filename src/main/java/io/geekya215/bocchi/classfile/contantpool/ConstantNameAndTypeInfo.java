package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_NameAndType_info {
//     u1 tag;
//     u2 name_index;
//     u2 descriptor_index;
// }
//
public final class ConstantNameAndTypeInfo extends ConstantPoolInfo {
    private final int nameIndex;
    private final int descriptorIndex;

    private ConstantNameAndTypeInfo(int nameIndex, int descriptorIndex) {
        super(ConstantPoolInfo.TAG_NAME_AND_TYPE);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public static ConstantNameAndTypeInfo create(ClassReader classReader) {
        int nameIndex = classReader.readU2();
        int descriptorIndex = classReader.readU2();
        return new ConstantNameAndTypeInfo(nameIndex, descriptorIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
