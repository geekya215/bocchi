package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Class_info {
//     u1 tag;
//     u2 name_index;
// }
//
public final class ConstantClassInfo extends ConstantPoolInfo {
    private final int nameIndex;

    private ConstantClassInfo(int nameIndex) {
        super(ConstantPoolInfo.TAG_CLASS);
        this.nameIndex = nameIndex;
    }

    public static ConstantClassInfo create(ClassReader classReader) {
        int nameIndex = classReader.readU2();
        return new ConstantClassInfo(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
