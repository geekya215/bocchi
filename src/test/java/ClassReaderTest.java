import io.geekya215.bocchi.AccessFlags;
import io.geekya215.bocchi.classfile.ClassFile;
import io.geekya215.bocchi.classfile.ClassReader;
import io.geekya215.bocchi.classfile.FieldInfo;
import io.geekya215.bocchi.classfile.MethodInfo;
import io.geekya215.bocchi.classfile.attribute.AttributeInfo;
import io.geekya215.bocchi.classfile.attribute.ConstantValueAttribute;
import io.geekya215.bocchi.classfile.attribute.SourceFileAttribute;
import io.geekya215.bocchi.classfile.contantpool.ConstantClassInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantPoolInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantStringInfo;
import io.geekya215.bocchi.classfile.contantpool.ConstantUTF8Info;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.geekya215.bocchi.AccessFlags.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassReaderTest {
    static ClassFile classfile;
    static ConstantPoolInfo[] cp;

    @BeforeAll
    static void setup() throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of("src/test/resources", "Bocchi.class"));
        ClassReader classReader = new ClassReader(bytes);
        classfile = classReader.parse();
        cp = classfile.getConstantPool();
    }

    void checkUTF8(int nameIndex, String expected) {
        ConstantPoolInfo cpInfo = cp[nameIndex];
        assertEquals(ConstantPoolInfo.TAG_UTF8, cpInfo.getTag());
        assertEquals(expected, new String(((ConstantUTF8Info) cpInfo).getBytes()));
    }

    void checkString(int nameIndex, String expected) {
        ConstantPoolInfo cpString = cp[nameIndex];
        assertEquals(ConstantPoolInfo.TAG_STRING, cpString.getTag());
        checkUTF8(((ConstantStringInfo) cpString).getStringIndex(), expected);
    }

    @Test
    void testMagicNumber() {
        long actual = classfile.getMagic();
        long expected = 0xCAFEBABEL;
        assertEquals(expected, actual);
    }

    @Test
    void testMinorVersion() {
        int actual = classfile.getMinorVersion();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void testMajorVersion() {
        int actual = classfile.getMajorVersion();
        // for JDK 17
        int expected = 61;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantPoolCount() {
        int actual = classfile.getConstantPoolCount();
        int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantPool() {
        ConstantPoolInfo[] constantPool = classfile.getConstantPool();
        // Todo
    }

    @Test
    void testAccessFlags() {
        int actual = classfile.getAccessFlags();
        int expected = CLASS_ACC_PUBLIC | CLASS_ACC_FINAL | CLASS_ACC_SUPER;
        assertEquals(expected, actual);
    }

    @Test
    void testThisClass() {
        int thisClassIndex = classfile.getThisClass();
        ConstantPoolInfo thisClass = cp[thisClassIndex];
        assertEquals(ConstantPoolInfo.TAG_CLASS, thisClass.getTag());

        checkUTF8(((ConstantClassInfo) thisClass).getNameIndex(), "Bocchi");
    }

    @Test
    void testSuperClass() {
        int superClassIndex = classfile.getSuperClass();
        ConstantPoolInfo superClass = cp[superClassIndex];
        assertEquals(ConstantPoolInfo.TAG_CLASS, superClass.getTag());

        checkUTF8(((ConstantClassInfo) superClass).getNameIndex(), "java/lang/Object");
    }

    @Test
    void testInterfaceCount() {
        int actual = classfile.getInterfacesCount();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void testInterface() {
        int[] actual = classfile.getInterfaces();
        int[] expected = new int[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void testFieldsCount() {
        int actual = classfile.getFieldsCount();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void testFields() {
        FieldInfo[] fields = classfile.getFields();
        assertEquals(5, fields.length);

        FieldInfo guitarist = fields[0];
        assertEquals(FIELD_ACC_PRIVATE, guitarist.getAccessFlags());
        checkUTF8(guitarist.getNameIndex(), "guitarist");
        checkUTF8(guitarist.getDescriptorIndex(), "Ljava/lang/String;");

        FieldInfo bassist = fields[1];
        assertEquals(FIELD_ACC_PUBLIC | FIELD_ACC_FINAL, bassist.getAccessFlags());
        checkUTF8(bassist.getNameIndex(), "bassist");
        checkUTF8(bassist.getDescriptorIndex(), "Ljava/lang/String;");
        assertEquals(1, bassist.getAttributesCount());
        AttributeInfo bassistAttribute = bassist.getAttributes()[0];
        checkUTF8(bassistAttribute.getNameIndex(), "ConstantValue");
        checkString(((ConstantValueAttribute) bassistAttribute).getConstantValueIndex(), "Ryou");

        FieldInfo drummer = fields[2];
        assertEquals(FIELD_ACC_PUBLIC | FIELD_ACC_FINAL, drummer.getAccessFlags());
        checkUTF8(drummer.getNameIndex(), "drummer");
        checkUTF8(drummer.getDescriptorIndex(), "Ljava/lang/String;");
        assertEquals(1, drummer.getAttributesCount());
        AttributeInfo drummerAttribute = drummer.getAttributes()[0];
        checkUTF8(drummerAttribute.getNameIndex(), "ConstantValue");
        checkString(((ConstantValueAttribute) drummerAttribute).getConstantValueIndex(), "Nijika");


        FieldInfo vocalist = fields[3];
        assertEquals(FIELD_ACC_PUBLIC | FIELD_ACC_FINAL, vocalist.getAccessFlags());
        checkUTF8(vocalist.getNameIndex(), "vocalist");
        checkUTF8(vocalist.getDescriptorIndex(), "Ljava/lang/String;");
        assertEquals(1, vocalist.getAttributesCount());
        AttributeInfo vocalistAttribute  = vocalist.getAttributes()[0];
        checkUTF8(vocalistAttribute.getNameIndex(), "ConstantValue");
        checkString(((ConstantValueAttribute) vocalistAttribute).getConstantValueIndex(), "Ikuyo");

        FieldInfo number = fields[4];
        assertEquals(FIELD_ACC_PUBLIC, number.getAccessFlags());
        checkUTF8(number.getNameIndex(), "number");
        checkUTF8(number.getDescriptorIndex(), "I");
        assertEquals(0, number.getAttributesCount());
    }


    @Test
    void testMethodsCount() {
        int actual = classfile.getMethodsCount();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void testMethods() {
        MethodInfo[] methods = classfile.getMethods();
        assertEquals(3, methods.length);

        MethodInfo init = methods[0];
        assertEquals(METHOD_ACC_PUBLIC, init.getAccessFlags());
        checkUTF8(init.getNameIndex(), "<init>");
        checkUTF8(init.getDescriptorIndex(), "()V");

        MethodInfo location = methods[1];
        assertEquals(METHOD_ACC_PUBLIC, location.getAccessFlags());
        checkUTF8(location.getNameIndex(), "location");
        checkUTF8(location.getDescriptorIndex(), "()Ljava/lang/String;");

        MethodInfo main = methods[2];
        assertEquals(METHOD_ACC_PUBLIC | METHOD_ACC_STATIC, main.getAccessFlags());
        checkUTF8(main.getNameIndex(), "main");
        checkUTF8(main.getDescriptorIndex(), "([Ljava/lang/String;)V");
    }

    @Test
    void testAttributesCount() {
        int actual = classfile.getAttributesCount();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void testAttributes() {
        AttributeInfo[] attributes = classfile.getAttributes();
        assertEquals(1, attributes.length);
        AttributeInfo sourceFile = attributes[0];
        checkUTF8(sourceFile.getNameIndex(), "SourceFile");
        checkUTF8(((SourceFileAttribute) sourceFile).getSourceFileIndex(), "Bocchi.java");
    }
}
