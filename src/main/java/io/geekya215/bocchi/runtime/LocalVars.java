package io.geekya215.bocchi.runtime;

// NOTICE
// all method do not check index
public final class LocalVars {
    private final Slot[] slots;

    public LocalVars(int maxLocals) {
        this.slots = new Slot[maxLocals];
        init();
    }

    // Todo
    // to improve performance do not create new object,
    // instead of using mutable objects
    private void init() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int value) {
        slots[index].setValue(value);
    }

    public int getInt(int index) {
        return slots[index].getValue();
    }

    public void setFloat(int index, float value) {
        setInt(index, Float.floatToIntBits(value));
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(getInt(index));
    }

    public void setLong(int index, long value) {
        int high = (int) (value >> 32);
        int low = (int) value;
        setInt(index, high);
        setInt(index + 1, low);
    }

    public long getLong(int index) {
        int high = getInt(index);
        int low = getInt(index + 1);
        return ((high & 0xffffffffL) << 32) | (low & 0xffffffffL);
    }

    public void setDouble(int index, double value) {
        setLong(index, Double.doubleToLongBits(value));
    }

    public double getDouble(int index) {
        return Double.longBitsToDouble(getLong(index));
    }

    public void setRef(int index, Object ref) {
        slots[index].setRef(ref);
    }

    public Object getRef(int index) {
        return slots[index].getRef();
    }
}
