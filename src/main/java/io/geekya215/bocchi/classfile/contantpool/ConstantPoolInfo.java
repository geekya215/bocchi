package io.geekya215.bocchi.classfile.contantpool;

import io.geekya215.bocchi.classfile.ClassReader;

//
// Constant Kind               Tag     class file format    Java SE
// CONSTANT_Utf8                1             45.3           1.0.2
// CONSTANT_Integer             3             45.3           1.0.2
// CONSTANT_Float               4             45.3           1.0.2
// CONSTANT_Long                5             45.3           1.0.2
// CONSTANT_Double              6             45.3           1.0.2
// CONSTANT_Class               7             45.3           1.0.2
// CONSTANT_String              8             45.3           1.0.2
// CONSTANT_Fieldref            9             45.3           1.0.2
// CONSTANT_Methodref           10            45.3           1.0.2
// CONSTANT_InterfaceMethodref  11            45.3           1.0.2
// CONSTANT_NameAndType         12            45.3           1.0.2
// CONSTANT_MethodHandle        15            51.0           7
// CONSTANT_MethodType          16            51.0           7
// CONSTANT_Dynamic             17            55.0           11
// CONSTANT_InvokeDynamic       18            51.0           7
// CONSTANT_Module              19            53.0           9
// CONSTANT_Package             20            53.0           9
//
public abstract class ConstantPoolInfo {
    public static final int TAG_UTF8                 = 1;
    public static final int TAG_INTEGER              = 3;
    public static final int TAG_FLOAT                = 4;
    public static final int TAG_LONG                 = 5;
    public static final int TAG_DOUBLE               = 6;
    public static final int TAG_CLASS                = 7;
    public static final int TAG_STRING               = 8;
    public static final int TAG_FIELD_REF            = 9;
    public static final int TAG_METHOD_REF           = 10;
    public static final int TAG_INTERFACE_METHOD_REF = 11;
    public static final int TAG_NAME_AND_TYPE        = 12;
    public static final int TAG_METHOD_HANDLE        = 15;
    public static final int TAG_METHOD_TYPE          = 16;
    public static final int TAG_DYNAMIC              = 17;
    public static final int TAG_INVOKE_DYNAMIC       = 18;
    public static final int TAG_MODULE               = 19;
    public static final int TAG_PACKAGE              = 20;

    private final int tag;

    protected ConstantPoolInfo(int tag) {
        this.tag = tag;
    }

    public static ConstantPoolInfo create(int tag, ClassReader classReader) {
        return switch (tag) {
            case TAG_UTF8 -> ConstantUTF8Info.create(classReader);
            case TAG_INTEGER -> ConstantIntegerInfo.create(classReader);
            case TAG_FLOAT -> ConstantFloatInfo.create(classReader);
            case TAG_DOUBLE -> ConstantDoubleInfo.create(classReader);
            case TAG_CLASS -> ConstantClassInfo.create(classReader);
            case TAG_STRING -> ConstantStringInfo.create(classReader);
            case TAG_FIELD_REF -> ConstantFieldRefInfo.create(classReader);
            case TAG_METHOD_REF -> ConstantMethodRefInfo.create(classReader);
            case TAG_INTERFACE_METHOD_REF -> ConstantInterfaceMethodRefInfo.create(classReader);
            case TAG_NAME_AND_TYPE -> ConstantNameAndTypeInfo.create(classReader);
            case TAG_METHOD_HANDLE -> ConstantMethodHandleInfo.create(classReader);
            case TAG_METHOD_TYPE -> ConstantMethodTypeInfo.create(classReader);
            case TAG_DYNAMIC -> ConstantDynamicInfo.create(classReader);
            case TAG_INVOKE_DYNAMIC -> ConstantInvokeDynamicInfo.create(classReader);
            case TAG_MODULE -> ConstantModuleInfo.create(classReader);
            case TAG_PACKAGE -> ConstantPackageInfo.create(classReader);

            default -> throw new IllegalStateException("Unknown constant tag: " + tag);
        };
    }

    public int getTag() {
        return tag;
    }
}
