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
public final class FieldInfo {
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    public static final int ACC_PUBLIC    = 0x0001;
    public static final int ACC_PRIVATE   = 0x0002;
    public static final int ACC_PROTECTED = 0x0004;
    public static final int ACC_STATIC    = 0x0008;
    public static final int ACC_FINAL     = 0x0010;
    public static final int ACC_VOLATILE  = 0x0040;
    public static final int ACC_TRANSIENT = 0x0080;
    public static final int ACC_SYNTHETIC = 0x0100;
    public static final int ACC_ENUM      = 0x4000;

    public FieldInfo(int accessFlags, int nameIndex, int descriptorIndex, int attributesCount, AttributeInfo[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    public static FieldInfo create(ConstantPoolInfo[] constantPoolInfos , ClassReader classReader) {
        int accessFlags = classReader.readU2();
        int nameIndex = classReader.readU2();
        int descriptorIndex = classReader.readU2();
        int attributesCount = classReader.readU2();

        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            int attributeNameIndex = classReader.readU2();
            attributes[i] = AttributeInfo.create(attributeNameIndex, constantPoolInfos, classReader);
        }

        return new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
