package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// CONSTANT_String_info {
//     u1 tag;
//     u2 string_index;
// }
//
public final class ConstantStringInfo extends ConstantPoolInfo {
    private final int stringIndex;

    private ConstantStringInfo(int stringIndex) {
        super(ConstantPoolInfo.TAG_STRING);
        this.stringIndex = stringIndex;
    }

    public static ConstantStringInfo create(ClassReader classReader) {
        int stringIndex = classReader.readU2();
        return new ConstantStringInfo(stringIndex);
    }

    public int getStringIndex() {
        return stringIndex;
    }
}
