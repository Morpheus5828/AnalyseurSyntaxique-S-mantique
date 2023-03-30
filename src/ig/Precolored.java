package ig;

import nasm.*;

public class Precolored implements NasmVisitor<Void> {
    private final Nasm nasm;
    private final int regNb;
    private int[] precolored;

    public Precolored(Nasm nasm, int regNb) {
        this.nasm = nasm;
        this.regNb = regNb;
    }

    public int[] computePrecolored() {
        this.precolored = new int[regNb];
        for (int index = 0; index < regNb; index++) {
            this.precolored[index] = Nasm.REG_UNK;
        }


        return precolored;
    }


    @Override
    public Void visit(NasmAdd inst) {
        return null;
    }

    @Override
    public Void visit(NasmCall inst) {
        return null;
    }

    @Override
    public Void visit(NasmDiv inst) {
        return null;
    }

    @Override
    public Void visit(NasmJe inst) {
        return null;
    }

    @Override
    public Void visit(NasmJle inst) {
        return null;
    }

    @Override
    public Void visit(NasmJne inst) {
        return null;
    }

    @Override
    public Void visit(NasmMul inst) {
        return null;
    }

    @Override
    public Void visit(NasmOr inst) {
        return null;
    }

    @Override
    public Void visit(NasmCmp inst) {
        return null;
    }

    @Override
    public Void visit(NasmInst inst) {
        return null;
    }

    @Override
    public Void visit(NasmJge inst) {
        return null;
    }

    @Override
    public Void visit(NasmJl inst) {
        return null;
    }

    @Override
    public Void visit(NasmNot inst) {
        return null;
    }

    @Override
    public Void visit(NasmPop inst) {
        return null;
    }

    @Override
    public Void visit(NasmRet inst) {
        return null;
    }

    @Override
    public Void visit(NasmXor inst) {
        return null;
    }

    @Override
    public Void visit(NasmAnd inst) {
        return null;
    }

    @Override
    public Void visit(NasmJg inst) {
        return null;
    }

    @Override
    public Void visit(NasmJmp inst) {
        return null;
    }

    @Override
    public Void visit(NasmMov inst) {
        return null;
    }

    @Override
    public Void visit(NasmPush inst) {
        return null;
    }

    @Override
    public Void visit(NasmSub inst) {
        return null;
    }

    @Override
    public Void visit(NasmEmpty inst) {
        return null;
    }

    @Override
    public Void visit(NasmInt inst) {
        return null;
    }

    @Override
    public Void visit(NasmAddress operand) {
        return null;
    }

    @Override
    public Void visit(NasmConstant operand) {
        return null;
    }

    @Override
    public Void visit(NasmLabel operand) {
        return null;
    }

    @Override
    public Void visit(NasmRegister operand) {
        return null;
    }

    @Override
    public Void visit(NasmResb pseudoInst) {
        return null;
    }

    @Override
    public Void visit(NasmResw pseudoInst) {
        return null;
    }

    @Override
    public Void visit(NasmResd pseudoInst) {
        return null;
    }

    @Override
    public Void visit(NasmResq pseudoInst) {
        return null;
    }

    @Override
    public Void visit(NasmRest pseudoInst) {
        return null;
    }
}
