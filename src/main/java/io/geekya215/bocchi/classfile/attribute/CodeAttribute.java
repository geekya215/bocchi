package io.geekya215.bocchi.classfile.attribute;

import io.geekya215.bocchi.classfile.ClassReader;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;

//
// Code_attribute {
//     u2 attribute_name_index;
//     u4 attribute_length;
//     u2 max_stack;
//     u2 max_locals;
//     u4 code_length;
//     u1 code[code_length];
//     u2 exception_table_length;
//     {   u2 start_pc;
//         u2 end_pc;
//         u2 handler_pc;
//         u2 catch_type;
//     } exception_table[exception_table_length];
//     u2 attributes_count;
//     attribute_info attributes[attributes_count];
// }
//
public final class CodeAttribute extends AttributeInfo {
    private final int maxStack;
    private final int maxLocals;
    private final long codeLength;
    private final byte[] code;
    private final int exceptionTableLength;
    private final ExceptionTable[] exceptionTables;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    static class ExceptionTable {
        private final int startPC;
        private final int endPC;
        private final int handlerPc;
        private final int catchType;

        private ExceptionTable(int startPC, int endPC, int handlerPc, int catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getEndPC() {
            return endPC;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }

    private CodeAttribute(int nameIndex,
                          long length,
                          int maxStack,
                          int maxLocals,
                          long codeLength,
                          byte[] code,
                          int exceptionTableLength,
                          ExceptionTable[] exceptionTables,
                          int attributesCount,
                          AttributeInfo[] attributeInfos) {
        super(nameIndex, length);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLength = codeLength;
        this.code = code;
        this.exceptionTableLength = exceptionTableLength;
        this.exceptionTables = exceptionTables;
        this.attributesCount = attributesCount;
        this.attributes = attributeInfos;
    }

    public static CodeAttribute build(int nameIndex,
                                      long length,
                                      ConstantPoolInfo[] constantPoolInfos,
                                      ClassReader classReader) {
        int maxStack = classReader.readU2();
        int maxLocals = classReader.readU2();
        long codeLength = classReader.readU4();
        // Todo
        // assume code length not overflow
        byte[] code = classReader.readNBytes((int) codeLength);

        int exceptionTableLength = classReader.readU2();
        ExceptionTable[] exceptionTables = new ExceptionTable[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            int startPC = classReader.readU2();
            int endPC = classReader.readU2();
            int handlerPC = classReader.readU2();
            int catchType = classReader.readU2();
            exceptionTables[i] = new ExceptionTable(startPC, endPC, handlerPC, catchType);
        }

        int attributesCount = classReader.readU2();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            int subAttributeNameIndex = classReader.readU2();
            attributes[i] = create(subAttributeNameIndex, constantPoolInfos, classReader);
        }

        return new CodeAttribute(nameIndex, length, maxStack, maxLocals, codeLength, code, exceptionTableLength, exceptionTables, attributesCount, attributes);
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public long getCodeLength() {
        return codeLength;
    }

    public byte[] getCode() {
        return code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public ExceptionTable[] getExceptionTables() {
        return exceptionTables;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
