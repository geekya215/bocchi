package io.geekya215.bocchi.classfile;

import io.geekya215.bocchi.classfile.attribute.AttributeInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;

//
// field_info {
//     u2             access_flags;
//     u2             name_index;
//     u2             descriptor_index;
//     u2             attributes_count;
//     attribute_info attributes[attributes_count];
// }
//
public final class FieldInfo extends MemberInfo {
    public FieldInfo(int accessFlags,
                     int nameIndex,
                     int descriptorIndex,
                     int attributesCount,
                     AttributeInfo[] attributes) {
        super(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
    }

    public static FieldInfo build(ConstantPoolInfo[] constantPoolInfos , ClassReader classReader) {
        return (FieldInfo) create(constantPoolInfos, classReader, true);
    }
}
