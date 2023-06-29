package io.geekya215.bocchi.classfile;

import io.geekya215.bocchi.ByteReader;
import io.geekya215.bocchi.classfile.attribute.AttributeInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;

public final class ClassReader {
    private final ByteReader byteReader;

    public ClassReader(byte[] bytes) {
        this.byteReader = ByteReader.create(bytes);
    }

    public ClassFile parse() {
        ClassFile classFile = new ClassFile();
        parseMagic(classFile);

        parseMinorVersion(classFile);
        parseMajorVersion(classFile);

        parseConstantPoolCount(classFile);
        parseConstantPool(classFile);

        parseAccessFlag(classFile);

        parseThisClass(classFile);
        parseSuperClass(classFile);

        parseInterfaceCount(classFile);
        parseInterfaces(classFile);

        parseFieldCount(classFile);
        parseFields(classFile);

        parseMethodsCount(classFile);
        parseMethods(classFile);

        parseAttributeCount(classFile);
        parseAttributes(classFile);

        return classFile;
    }

    private void parseMagic(ClassFile classFile) {
        long magic = readU4();
        if (magic != 0xCAFEBABEL) {
            throw new LinkageError("Incompatible magic value " + magic);
        }
        classFile.setMagic(magic);
    }

    private void parseMinorVersion(ClassFile classFile) {
        int minorVersion = readU2();
        classFile.setMinorVersion(minorVersion);
    }

    private void parseMajorVersion(ClassFile classFile) {
        int majorVersion = readU2();
        // only support to jdk 17
        if (majorVersion < 45 || majorVersion > 61) {
            throw new UnsupportedClassVersionError();
        }
        classFile.setMajorVersion(majorVersion);
    }

    private void parseConstantPoolCount(ClassFile classFile) {
        int constantPoolCount = readU2();
        classFile.setConstantPoolCount(constantPoolCount);
    }

    private void parseConstantPool(ClassFile classFile) {
        int constantPoolCount = classFile.getConstantPoolCount();
        ConstantPoolInfo[] constantPoolInfos = new ConstantPoolInfo[constantPoolCount];

        // first pass (parse)
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = readU1();
            ConstantPoolInfo constantPoolInfo = ConstantPoolInfo.create(tag, this);
            constantPoolInfos[i] = constantPoolInfo;
        }

        // Todo
        // second pass (valid)

        classFile.setConstantPool(constantPoolInfos);
    }

    private void parseAccessFlag(ClassFile classFile) {
        int accessFlag = readU2();
        classFile.setAccessFlags(accessFlag);
    }

    private void parseThisClass(ClassFile classFile) {
        int thisClass = readU2();
        classFile.setThisClass(thisClass);
    }

    private void parseSuperClass(ClassFile classFile) {
        int superClass = readU2();
        classFile.setSuperClass(superClass);
    }

    private void parseInterfaceCount(ClassFile classFile) {
        int interfacesCount = readU2();
        classFile.setInterfacesCount(interfacesCount);
    }

    private void parseInterfaces(ClassFile classFile) {
        int interfacesCount = classFile.getInterfacesCount();
        int[] interfaces = new int[interfacesCount];

        for (int i = 0; i < interfacesCount; i++) {
            interfaces[i] = readU2();
        }

        classFile.setInterfaces(interfaces);
    }

    private void parseFieldCount(ClassFile classFile) {
        int fieldCount = readU2();
        classFile.setFieldsCount(fieldCount);
    }

    private void parseFields(ClassFile classFile) {
        int fieldsCount = classFile.getFieldsCount();
        FieldInfo[] fields = new FieldInfo[fieldsCount];

        for (int i = 0; i < fieldsCount; i++) {
            fields[i] = FieldInfo.build(classFile.getConstantPool(), this);
        }

        classFile.setFields(fields);
    }

    private void parseMethodsCount(ClassFile classFile) {
        int methodsCount = readU2();
        classFile.setMethodsCount(methodsCount);
    }

    private void parseMethods(ClassFile classFile) {
        int methodsCount = classFile.getMethodsCount();
        MethodInfo[] methods = new MethodInfo[methodsCount];

        for (int i = 0; i < methodsCount; i++) {
            methods[i] = MethodInfo.build(classFile.getConstantPool(), this);
        }

        classFile.setMethods(methods);
    }

    private void parseAttributeCount(ClassFile classFile) {
        int attributesCount = readU2();
        classFile.setAttributesCount(attributesCount);
    }

    private void parseAttributes(ClassFile classFile) {
        int attributesCount = classFile.getAttributesCount();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];

        for (int i = 0; i < attributesCount; i++) {
            int nameIndex = readU2();
            attributes[i] = AttributeInfo.create(nameIndex, classFile.getConstantPool(), this);
        }


        classFile.setAttributes(attributes);
    }

    public int readU1() {
        return byteReader.readU1();
    }

    public int readU2() {
        return byteReader.readU2();
    }

    public long readU4() {
        return byteReader.readU4();
    }

    public byte[] readNBytes(int size) {
        return byteReader.readNBytes(size);
    }
}
