package io.geekya215.bocchi;

public class Bocchi {
    private static final Instruction[] instructions = new Instruction[256];

    // initial instruction table
    static {
        for (Instruction inst : Instruction.values()) {
            instructions[inst.getOpcode()] = inst;
        }
    }

    public Value eval(final byte[] ops, final LocalVars lvs) {
        OperandStack eval = new OperandStack();

        int pc = 0;
        for (; pc < ops.length; ++pc) {
            byte op = ops[pc];
            Instruction inst = instructions[op & 0xFF];

            switch (inst) {
                case NOP -> {}
                case ICONST_M1 -> eval.iconst(-1);
                case ICONST_0 -> eval.iconst(0);
                case ICONST_1 -> eval.iconst(1);
                case ICONST_2 -> eval.iconst(2);
                case ICONST_3 -> eval.iconst(3);
                case ICONST_4 -> eval.iconst(4);
                case ICONST_5 -> eval.iconst(5);

                case BIPUSH -> eval.iconst(ops[++pc]);

                case ILOAD -> eval.push(lvs.iload(ops[++pc]));
                case ILOAD_0 -> eval.push(lvs.iload((byte) 0));
                case ILOAD_1 -> eval.push(lvs.iload((byte) 1));
                case ILOAD_2 -> eval.push(lvs.iload((byte) 2));
                case ILOAD_3 -> eval.push(lvs.iload((byte) 3));

                case ISTORE -> lvs.istore(ops[++pc], eval.pop());
                case ISTORE_0 -> lvs.istore((byte) 0, eval.pop());
                case ISTORE_1 -> lvs.istore((byte) 1, eval.pop());
                case ISTORE_2 -> lvs.istore((byte) 2, eval.pop());
                case ISTORE_3 -> lvs.istore((byte) 3, eval.pop());

                case POP -> eval.pop();
                case POP2 -> eval.pop2();

                case DUP -> eval.dup();
                case SWAP -> eval.swap();

                case IADD -> eval.iadd();
                case ISUB -> eval.isub();
                case IMUL -> eval.imul();
                case IDIV -> eval.idiv();
                case IREM -> eval.irem();
                case INEG -> eval.ineg();

                case ISHL -> eval.ishl();
                case ISHR -> eval.ishr();
                case IUSHR -> eval.iushr();

                case IAND -> eval.iand();
                case IOR -> eval.ior();
                case IXOR -> eval.ixor();

                case IINC -> lvs.iinc(ops[++pc], ops[++pc]);

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
