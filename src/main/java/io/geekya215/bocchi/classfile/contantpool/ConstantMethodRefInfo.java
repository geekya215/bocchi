package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Methodref_info {
//     u1 tag;
//     u2 class_index;
//     u2 name_and_type_index;
// }
//
public final class ConstantMethodRefInfo extends ConstantPoolInfo {
    private final int classIndex;
    private final int nameAndTypeIndex;

    private ConstantMethodRefInfo(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolInfo.TAG_METHOD_REF);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public static ConstantMethodRefInfo create(ClassReader classReader) {
        int classIndex = classReader.readU2();
        int nameAndTypeIndex = classReader.readU2();
        return new ConstantMethodRefInfo(classIndex, nameAndTypeIndex);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
