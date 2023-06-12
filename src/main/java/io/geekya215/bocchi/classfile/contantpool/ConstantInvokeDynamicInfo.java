package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_InvokeDynamic_info {
//     u1 tag;
//     u2 bootstrap_method_attr_index;
//     u2 name_and_type_index;
// }
//
public final class ConstantInvokeDynamicInfo extends ConstantPoolInfo {
    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;

    private ConstantInvokeDynamicInfo(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(ConstantPoolInfo.TAG_INVOKE_DYNAMIC);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public static ConstantInvokeDynamicInfo create(ClassReader classReader) {
        int bootstrapMethodAttrIndex = classReader.readU2();
        int nameAndTypeIndex = classReader.readU2();
        return new ConstantInvokeDynamicInfo(bootstrapMethodAttrIndex, nameAndTypeIndex);
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
