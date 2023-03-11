package io.geekya215.bocchi;

public class Bocchi {
    private static final Instruction[] instructions = new Instruction[256];

    // initial instruction table
    static {
        for (Instruction inst : Instruction.values()) {
            instructions[inst.getOpcode()] = inst;
        }
    }

    public Value eval(final byte[] ops) {
        OperandStack eval = new OperandStack();

        int pc = 0;
        for (; pc < ops.length; ++pc) {
            byte op = ops[pc];
            Instruction inst = instructions[op & 0xFF];

            switch (inst) {
                case ICONST_M1 -> eval.iconst(-1);
                case ICONST_0 -> eval.iconst(0);
                case ICONST_1 -> eval.iconst(1);
                case ICONST_2 -> eval.iconst(2);
                case ICONST_3 -> eval.iconst(3);
                case ICONST_4 -> eval.iconst(4);
                case ICONST_5 -> eval.iconst(5);

                case IADD -> eval.iadd();
                case ISUB -> eval.isub();
                case IMUL -> eval.imul();
                case IDIV -> eval.idiv();
                case IREM -> eval.irem();
                case INEG -> eval.ineg();

                case IAND -> eval.iand();
                case IOR -> eval.ior();
                case IXOR -> eval.ixor();

                case IRETURN -> {
                    return eval.pop();
                }

                default -> {
                    System.err.printf("unimplemented instruction: %s, Opcode: 0x%x", inst, (op & 0xFF));
                    System.exit(1);
                }
            }
        }

        // dummy return
        return null;
    }
}
