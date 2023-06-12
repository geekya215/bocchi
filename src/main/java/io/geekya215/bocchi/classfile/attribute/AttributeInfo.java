package io.geekya215.bocchi.classfile.attribute;

import io.geekya215.bocchi.classfile.ClassReader;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantUTF8Info;

//
// attribute_info {
//     u2 attribute_name_index;
//     u4 attribute_length;
//     u1 info[attribute_length];
// }
//
public abstract class AttributeInfo {
    // Seven attributes are critical to correct interpretation of the class file
    // by the Java Virtual Machine
    static final String ATTR_CONSTANT_VALUE       = "ConstantValue";
    static final String ATTR_CODE                 = "Code";
    static final String ATTR_STACK_MAP_TABLE      = "StackMapTable";
    static final String ATTR_BOOTSTRAP_METHODS    = "BootstrapMethods";
    static final String ATTR_NEST_HOST            = "NestHost";
    static final String ATTR_NEST_MEMBERS         = "NestMembers";
    static final String ATTR_PERMITTED_SUBCLASSES = "PermittedSubclasses";


    // Ten attributes are not critical to correct interpretation of the class file by the
    // Java Virtual Machine, but are either critical to correct interpretation of the class
    // file by the class libraries of the Java SE Platform, or are useful for tools
    static final String ATTR_EXCEPTIONS           = "Exceptions";
    static final String ATTR_INNER_CLASS          = "InnerClasses";
    static final String ATTR_ENCLOSING_METHOD     = "EnclosingMethod";
    static final String ATTR_SYNTHETIC            = "Synthetic";
    static final String ATTR_SIGNATURE            = "Signature";
    static final String ATTR_RECORD               = "Record";
    static final String ATTR_SOURCE_FILE          = "SourceFile";
    static final String ATTR_LINE_NUMBER_TABLE    = "LineNumberTable";
    static final String ATTR_LOCAL_VARIABLE_TABLE = "LocalVariableTable";


    // Thirteen attributes are not critical to correct interpretation of the class file by the
    // Java Virtual Machine, but contain metadata about the class file that is either exposed
    // by the class libraries of the Java SE Platform, or made available by tools
    static final String ATTR_SOURCE_DEBUG_EXTENSION                  = "SourceDebugExtension";
    static final String ATTR_DEPRECATED                              = "Deprecated";
    static final String ATTR_RUNTIME_VISIBLE_ANNOTATIONS             = "RuntimeVisibleAnnotations";
    static final String ATTR_RUNTIME_INVISIBLE_ANNOTATIONS           = "RuntimeInvisibleAnnotations";
    static final String ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS   = "RuntimeVisibleParameterAnnotations";
    static final String ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    static final String ATTR_RUNTIME_VISIBLE_TYPE_ANNOTATIONS        = "RuntimeVisibleTypeAnnotations";
    static final String ATTR_RUNTIME_INVISIBLE_TYPE_ANNOTATIONS      = "RuntimeInvisibleTypeAnnotations";
    static final String ATTR_ANNOTATION_DEFAULT                      = "AnnotationDefault";
    static final String ATTR_METHOD_PARAMETERS                       = "MethodParameters";
    static final String ATTR_MODULE                                  = "Module";
    static final String ATTR_MODULE_PACKAGES                         = "ModulePackages";
    static final String ATTR_MODULE_MAIN_CLASS                       = "ModuleMainClass";

    private final int nameIndex;
    private final long length;

    protected AttributeInfo(int nameIndex, long length) {
        this.nameIndex = nameIndex;
        this.length = length;
    }

    public static AttributeInfo create(int nameIndex, ConstantPoolInfo[] constantPoolInfos, ClassReader classReader) {
        ConstantPoolInfo constant = constantPoolInfos[nameIndex];
        if (constant.getTag() != ConstantPoolInfo.TAG_UTF8) {
            throw new LinkageError("The constant_pool entry at attribute_name_index must be a CONSTANT_Utf8_info structure");
        } else {
            String attributeName = new String(((ConstantUTF8Info) constant).getBytes());
            long length = classReader.readU4();
            return switch (attributeName) {
                case ATTR_CODE -> CodeAttribute.build(nameIndex, length, constantPoolInfos, classReader);
                case ATTR_LINE_NUMBER_TABLE -> LineNumberTableAttribute.build(nameIndex, length, classReader);
                case ATTR_SOURCE_FILE -> SourceFileAttribute.build(nameIndex, length, classReader);
                case ATTR_CONSTANT_VALUE -> ConstantValueAttribute.build(nameIndex, length, classReader);

                default -> throw new LinkageError("Unknown attribute name: " + attributeName);
            };
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public long getLength() {
        return length;
    }
}
