package io.geekya215.bocchi.classfile.attribute;

import io.geekya215.bocchi.classfile.ClassReader;

//
// LineNumberTable_attribute {
//     u2 attribute_name_index;
//     u4 attribute_length;
//     u2 line_number_table_length;
//     {   u2 start_pc;
//         u2 line_number;
//     } line_number_table[line_number_table_length];
// }
//
public final class LineNumberTableAttribute extends AttributeInfo {
    private final int lineNumberTableLength;
    private final LineNumberTable[] lineNumberTables;

    static class LineNumberTable {
        private final int startPC;
        private final int lineNumber;

        private LineNumberTable(int startPC, int lineNumber) {
            this.startPC = startPC;
            this.lineNumber = lineNumber;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }

    private LineNumberTableAttribute(int nameIndex, long length, int lineNumberTableLength, LineNumberTable[] lineNumberTables) {
        super(nameIndex, length);
        this.lineNumberTableLength = lineNumberTableLength;
        this.lineNumberTables = lineNumberTables;
    }

    public static LineNumberTableAttribute build(int nameIndex,
                                                 long length,
                                                 ClassReader classReader) {
        int lineNumberTableLength = classReader.readU2();
        LineNumberTable[] lineNumberTables = new LineNumberTable[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            int startPC = classReader.readU2();
            int lineNumber = classReader.readU2();
            lineNumberTables[i] = new LineNumberTable(startPC, lineNumber);
        }
        return new LineNumberTableAttribute(nameIndex, length, lineNumberTableLength, lineNumberTables);
    }

    public int getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public LineNumberTable[] getLineNumberTables() {
        return lineNumberTables;
    }
}
