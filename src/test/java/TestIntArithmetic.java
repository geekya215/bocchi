import io.geekya215.bocchi.Bocchi;
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
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(8, (int) res.value(), "Return value should be 8");
    }

    @Test
    public void int_sub_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISUB.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(-2, (int) res.value(), "Return value should be -2");
    }

    @Test
    public void int_mul_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IMUL.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(15, (int) res.value(), "Return value should be 15");
    }
    @Test
    public void int_div_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IDIV.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");
    }

    @Test
    public void int_rem_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IREM.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(3, (int) res.value(), "Return value should be 3");
    }

    @Test
    public void int_neg_works() {
        byte[] buf = {ICONST_5.B(), INEG.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(-5, (int) res.value(), "Return value should be -5");
    }

    @Test
    public void int_shl_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISHL.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(96, (int) res.value(), "Return value should be 96");
    }

    @Test
    public void int_shr_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), ISHR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");
    }

    @Test
    public void int_ushr_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IUSHR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(0, (int) res.value(), "Return value should be 0");
    }

    @Test
    public void int_and_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IAND.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(1, (int) res.value(), "Return value should be 1");
    }

    @Test
    public void int_or_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IOR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(7, (int) res.value(), "Return value should be 7");
    }

    @Test
    public void int_xor_works() {
        byte[] buf = {ICONST_5.B(), ICONST_3.B(), IXOR.B(), IRETURN.B()};
        Value res = bocchi.eval(buf);
        assertEquals(Type.I, res.type(), "Return type is int");
        assertEquals(6, (int) res.value(), "Return value should be 6");
    }
}
