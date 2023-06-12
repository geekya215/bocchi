package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_Dynamic_info {
//     u1 tag;
//     u2 bootstrap_method_attr_index;
//     u2 name_and_type_index;
// }
//
public final class ConstantDynamicInfo extends ConstantPoolInfo {
    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;

    private ConstantDynamicInfo(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(ConstantPoolInfo.TAG_DYNAMIC);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public static ConstantDynamicInfo create(ClassReader classReader) {
        int bootstrapMethodAttrIndex = classReader.readU2();
        int nameAndTypeIndex = classReader.readU2();
        return new ConstantDynamicInfo(bootstrapMethodAttrIndex, nameAndTypeIndex);
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
