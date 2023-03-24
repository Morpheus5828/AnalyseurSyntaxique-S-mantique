package fg;
import nasm.*;
import util.graph.Node;
import util.graph.NodeList;
import util.intset.*;
import java.io.*;
import java.util.*;

public class FgSolution implements NasmVisitor<Void> {
    int nasmNum = 0;
	int iterNum = 0;
	boolean isUsed;
	boolean isDef;
    public Nasm nasm;
    Fg fg;
    public Map< NasmInst, IntSet> use;
    public Map< NasmInst, IntSet> def;
    public Map< NasmInst, IntSet> in;
    public Map< NasmInst, IntSet> out;
    
    public FgSolution(Nasm nasm, Fg fg){
		this.nasm = nasm;
		this.fg = fg;

		use = new HashMap<>();
		def = new HashMap<>();
		in = new HashMap<>();
		out = new HashMap<>();

		int instNb = nasm.sectionText.size();
		for (int i = 0; i < instNb; i++) {
			nasmNum = i;
			NasmInst nasmInst = nasm.sectionText.get(i);

			use.put(nasmInst, new IntSet(nasm.getTempCounter()));
			def.put(nasmInst, new IntSet(nasm.getTempCounter()));
			in.put(nasmInst, new IntSet(nasm.getTempCounter()));
			out.put(nasmInst, new IntSet(nasm.getTempCounter()));

			nasmInst.accept(this);
		}

		FgSolve();
	}
    
    public void FgSolve() {
		boolean isChanged = true;

		while (isChanged) {
			isChanged = false;
			iterNum++;
			for (NasmInst inst : nasm.sectionText) {
				final IntSet in = this.in.get(inst);
				final IntSet out = this.out.get(inst);

				final IntSet inCopy = in.copy();
				final IntSet outCopy = out.copy();

				in.union(out.minus(this.def.get(inst)).union(this.use.get(inst)));

				if (fg.inst2Node.get(inst).succ() != null) {
					Node successor = fg.inst2Node.get(inst).succ().head;
					NodeList successors = fg.inst2Node.get(inst).succ().tail;
					out.union(this.in.get(fg.node2Inst.get(successor)));
					while (successors != null) {
						successor = successors.head;
						successors = successors.tail;
						out.union(this.in.get(fg.node2Inst.get(successor)));
					}
				}
			
				if (!in.equal(inCopy) || !out.equal(outCopy)) isChanged = true;
			}
		}
    }

    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".fgs";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	out.println("iter num = " + nasmNum);
	for(NasmInst nasmInst : this.nasm.sectionText){
	    out.println("use = "+ this.use.get(nasmInst) + " def = "+ this.def.get(nasmInst) + "\tin = " + this.in.get(nasmInst) + "\t \tout = " + this.out.get(nasmInst) + "\t \t" + nasmInst);
	}
	
    }


	@Override
	public Void visit(NasmAdd inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmCall inst) {
		return null;
	}

	@Override
	public Void visit(NasmDiv inst) {
		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
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
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmOr inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmCmp inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
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
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmPop inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmRet inst) {
		return null;
	}

	@Override
	public Void visit(NasmXor inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmAnd inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
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
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmPush inst) {
		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
		return null;
	}

	@Override
	public Void visit(NasmSub inst) {
		isDef = inst.destDef;
		isUsed = inst.destUse;
		inst.destination.accept(this);

		isDef = inst.srcDef;
		isUsed = inst.srcUse;
		inst.source.accept(this);
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
		isDef = false;
		isUsed = true;
		operand.base.accept(this);
		operand.offset.accept(this);
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
		if (!operand.isGeneralRegister()) return null;
		NasmInst inst = nasm.sectionText.get(nasmNum);

		if (isUsed) use.get(inst).add(operand.val);
		if (isDef) def.get(inst).add(operand.val);
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
