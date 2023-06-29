package io.geekya215.bocchi.classfile;

import io.geekya215.bocchi.classfile.attribute.AttributeInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;

public class MemberInfo {
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    protected MemberInfo(int accessFlags,
                         int nameIndex,
                         int descriptorIndex,
                         int attributesCount,
                         AttributeInfo[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    protected static MemberInfo create(ConstantPoolInfo[] constantPoolInfos , ClassReader classReader, boolean isField) {
        int accessFlags = classReader.readU2();
        int nameIndex = classReader.readU2();
        int descriptorIndex = classReader.readU2();
        int attributesCount = classReader.readU2();

        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            int attributeNameIndex = classReader.readU2();
            attributes[i] = AttributeInfo.create(attributeNameIndex, constantPoolInfos, classReader);
        }

        if (isField) {
            return new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
        } else {
            return new MethodInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
        }

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
