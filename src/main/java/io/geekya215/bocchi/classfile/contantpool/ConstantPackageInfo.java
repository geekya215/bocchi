package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Package_info {
//     u1 tag;
//     u2 name_index;
// }
//
public final class ConstantPackageInfo extends ConstantPoolInfo {
    private final int nameIndex;

    private ConstantPackageInfo(int nameIndex) {
        super(ConstantPoolInfo.TAG_PACKAGE);
        this.nameIndex = nameIndex;
    }

    public static ConstantPackageInfo create(ClassReader classReader) {
        int nameIndex = classReader.readU2();
        return new ConstantPackageInfo(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
