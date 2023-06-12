package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_InterfaceMethodref_info {
//     u1 tag;
//     u2 class_index;
//     u2 name_and_type_index;
// }
//
public final class ConstantInterfaceMethodRefInfo extends ConstantPoolInfo {
    private final int classIndex;
    private final int nameAndTypeIndex;

    private ConstantInterfaceMethodRefInfo(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolInfo.TAG_INTERFACE_METHOD_REF);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public static ConstantInterfaceMethodRefInfo create(ClassReader classReader) {
        int classIndex = classReader.readU2();
        int nameAndTypeIndex = classReader.readU2();
        return new ConstantInterfaceMethodRefInfo(classIndex, nameAndTypeIndex);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
