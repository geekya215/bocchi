package io.geekya215.bocchi.classfile.attribute;

import io.geekya215.bocchi.classfile.ClassReader;

//
// ConstantValue_attribute {
//     u2 attribute_name_index;
//     u4 attribute_length;
//     u2 constantvalue_index;
// }
//
public final class ConstantValueAttribute extends AttributeInfo {
    private final int constantValueIndex;

    private ConstantValueAttribute(int nameIndex, long length, int constantValueIndex) {
        super(nameIndex, length);
        this.constantValueIndex = constantValueIndex;
    }

    public static ConstantValueAttribute build(int nameIndex, long length, ClassReader classReader) {
        int constantValueIndex = classReader.readU2();
        return new ConstantValueAttribute(nameIndex, length, constantValueIndex);
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
