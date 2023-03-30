package fg;
import nasm.*;
import util.graph.Node;
import util.graph.NodeList;
import util.intset.*;
import java.io.*;
import java.util.*;

public class FgSolution {
    int nasmNum = 0;
	int iterNum = 0;
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

		for (NasmInst inst : nasm.sectionText) {
			use.put(inst, new IntSet(nasm.getTempCounter()));
			def.put(inst, new IntSet(nasm.getTempCounter()));
			in.put(inst, new IntSet(nasm.getTempCounter()));
			out.put(inst, new IntSet(nasm.getTempCounter()));

			if (inst.source != null) handleNasmOperand(inst, inst.srcDef, inst.srcUse, inst.source);
			if (inst.destination != null) handleNasmOperand(inst, inst.destDef, inst.destUse, inst.destination);
		}
		FgSolve();
	}

	public void handleNasmOperand(final NasmInst inst, final boolean isDef, final boolean isUsed, final NasmOperand operand) {
		if (operand instanceof NasmAddress) {
			final NasmAddress address = (NasmAddress) operand;
			if (address.base != null) handleNasmOperand(inst, false, true,address.base);
			if (address.offset != null) handleNasmOperand(inst, false, true,address.offset);
			return;
		}

		if (operand.isGeneralRegister()) {
			final NasmRegister register = (NasmRegister) operand;
			if (isDef) def.get(inst).add(register.val);
			if (isUsed) use.get(inst).add(register.val);
			return;
		}
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
}
