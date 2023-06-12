package io.geekya215.bocchi.classfile.attribute;

import io.geekya215.bocchi.classfile.ClassReader;

//
// SourceFile_attribute {
//     u2 attribute_name_index;
//     u4 attribute_length;
//     u2 sourcefile_index;
// }
//
public final class SourceFileAttribute extends AttributeInfo {
    private final int sourceFileIndex;

    private SourceFileAttribute(int nameIndex, long length, int sourceFileIndex) {
        super(nameIndex, length);
        this.sourceFileIndex = sourceFileIndex;
    }

    public static SourceFileAttribute build(int nameIndex, long length, ClassReader classReader) {
        int sourceFileIndex = classReader.readU2();
        return new SourceFileAttribute(nameIndex, length, sourceFileIndex);
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }
}
