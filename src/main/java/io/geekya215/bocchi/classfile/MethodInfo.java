package io.geekya215.bocchi.classfile;

import io.geekya215.bocchi.classfile.attribute.AttributeInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;

//
// method_info {
//     u2             access_flags;
//     u2             name_index;
//     u2             descriptor_index;
//     u2             attributes_count;
//     attribute_info attributes[attributes_count];
// }
//
public final class MethodInfo extends MemberInfo {
    public MethodInfo(int accessFlags,
                      int nameIndex,
                      int descriptorIndex,
                      int attributesCount,
                      AttributeInfo[] attributes) {
        super(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
    }

    public static MethodInfo build(ConstantPoolInfo[] constantPoolInfos , ClassReader classReader) {
        return (MethodInfo) create(constantPoolInfos, classReader, false);
    }
}
