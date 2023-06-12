package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Module_info {
//     u1 tag;
//     u2 name_index;
// }
//
public final class ConstantModuleInfo extends ConstantPoolInfo {
    private final int nameIndex;

    private ConstantModuleInfo(int nameIndex) {
        super(ConstantPoolInfo.TAG_MODULE);
        this.nameIndex = nameIndex;
    }

    public static ConstantModuleInfo create(ClassReader classReader) {
        int nameIndex = classReader.readU2();
        return new ConstantModuleInfo(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
