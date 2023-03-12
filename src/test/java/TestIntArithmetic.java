import io.geekya215.bocchi.Bocchi;
import io.geekya215.bocchi.LocalVars;
import io.geekya215.bocchi.Type;
import io.geekya215.bocchi.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.geekya215.bocchi.Instruction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIntArithmetic {
    private Bocchi bocchi;

    @BeforeEach
    public void setup() {
        bocchi = new Bocchi();
    }

    @Test
    public void int_add_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IADD.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(8, (int) res.value(), "Return value should be 8");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), IINC.B(), (byte) 0, (byte) 1, ICONST_4.B(), ILOAD_0.B(), IADD.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(20, (int) res2.value(), "Return value should be 20");
    }

    @Test
    public void int_sub_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISUB.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(-2, (int) res.value(), "Return value should be -2");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), IINC.B(), (byte) 0, (byte) 1, ICONST_4.B(), ILOAD_0.B(), ISUB.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(12, (int) res2.value(), "Return value should be 12");
    }

    @Test
    public void int_mul_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IMUL.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(15, (int) res.value(), "Return value should be 15");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), IINC.B(), (byte) 0, (byte) 1, ICONST_4.B(), ILOAD_0.B(), IMUL.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(64, (int) res2.value(), "Return value should be 64");
    }

    @Test
    public void int_div_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IDIV.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), IINC.B(), (byte) 0, (byte) 1, ICONST_4.B(), ILOAD_0.B(), IDIV.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(4, (int) res2.value(), "Return value should be 4");
    }

    @Test
    public void int_rem_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IREM.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(3, (int) res.value(), "Return value should be 3");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), IINC.B(), (byte) 0, (byte) 1, ICONST_4.B(), ILOAD_0.B(), IREM.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res2.value(), "Return value should be 0");
    }

    @Test
    public void int_neg_works() {
        byte[] buf = {ICONST_5.B(), INEG.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(-5, (int) res.value(), "Return value should be -5");

        byte[] buf2 = {BIPUSH.B(), (byte) 15, ISTORE_0.B(), ICONST_4.B(), ILOAD_0.B(), POP.B(), INEG.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(-4, (int) res2.value(), "Return value should be -4");
    }

    @Test
    public void int_shl_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISHL.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(96, (int) res.value(), "Return value should be 96");

        byte[] buf2 = {ICONST_1.B(), DUP.B(), ISHL.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(2, (int) res2.value(), "Return value should be 2");
    }

    @Test
    public void int_shr_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISHR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");

        byte[] buf2 = {ICONST_4.B(), ICONST_1.B(), SWAP.B(), ISHR.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(2, (int) res2.value(), "Return value should be 2");
    }

    @Test
    public void int_ushr_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IUSHR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");
        byte[] buf2 = {ICONST_4.B(), ICONST_1.B(), ICONST_3.B(), ICONST_2.B(), POP2.B(), SWAP.B(), IUSHR.B(), IRETURN.B()};
        Value res2 = bocchi.eval(buf2, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(2, (int) res2.value(), "Return value should be 2");
    }

    @Test
    public void int_and_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IAND.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(1, (int) res.value(), "Return value should be 1");
    }

    @Test
    public void int_or_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IOR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(7, (int) res.value(), "Return value should be 7");
    }

    @Test
    public void int_xor_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IXOR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf, new LocalVars());
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(6, (int) res.value(), "Return value should be 6");
    }
}
