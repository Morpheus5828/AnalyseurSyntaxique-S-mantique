package ig;

import fg.FgSolution;
import nasm.*;
import util.graph.ColorGraph;
import util.graph.Graph;
import util.graph.Node;
import util.graph.NodeList;
import util.intset.IntSet;

import java.io.IOException;
import java.io.PrintStream;

public class Ig {
    public Graph graph; // graphe interference
    public FgSolution fgs; // les ensembles in et out
    public int regNb; // le nombre de registres fictifs
    public Nasm nasm; // le pré nasm
    public Node int2Node[];

    public Ig(FgSolution fgs){
        this.fgs = fgs;
        this.graph = new Graph();
        this.nasm = fgs.nasm;
        this.regNb = this.nasm.getTempCounter();
        this.int2Node = new Node[regNb];
        this.build();
    }

    public void build() {
        for(int index = 0; index < regNb; index++)
            this.int2Node[index] = this.graph.newNode();

        for(NasmInst node : this.nasm.sectionText) {

            for(int i = 0; i < fgs.in.get(node).getSize(); i++) {
                for(int j = 0; j < fgs.in.get(node).getSize(); j++) {
                    if(i != j) {
                        if (fgs.in.get(node).isMember(i) && fgs.in.get(node).isMember(j)) {
                            graph.addEdge(this.int2Node[i], this.int2Node[j]);
                        }
                    }
                }
            }

            for(int i = 0; i < fgs.out.get(node).getSize(); i++) {
                for(int j = 0; j < fgs.out.get(node).getSize(); j++) {
                    if(i != j) {
                        if (fgs.out.get(node).isMember(i) && fgs.out.get(node).isMember(j)) {
                            graph.addEdge(this.int2Node[i], this.int2Node[j]);
                        }
                    }
                }
            }
        }
    }

    public int[] getPrecoloredTemporaries() {
        final int[] precolored = new int[regNb];
        for (int index = 0; index < regNb; index++) precolored[index] = Nasm.REG_UNK;
        for (NasmInst inst : nasm.sectionText) {
            if (inst.label != null) handlePrecoloredNasmOperand(precolored, inst.label);
            if (inst.source != null) handlePrecoloredNasmOperand(precolored, inst.source);
            if (inst.destination != null) handlePrecoloredNasmOperand(precolored, inst.destination);
        }
        return precolored;
    }

    public void handlePrecoloredNasmOperand(final int[] precolored, final NasmOperand operand) {
        if (operand instanceof NasmAddress) {
            final NasmAddress address = (NasmAddress) operand;
            if (address.base != null) handlePrecoloredNasmOperand(precolored, address.base);
            if (address.offset != null) handlePrecoloredNasmOperand(precolored, address.offset);
            return;
        }

        if (operand.isGeneralRegister()) {
            final NasmRegister register = (NasmRegister) operand;
            if (register.color != nasm.REG_UNK) precolored[register.val] = register.color;
            return;
        }
    }

    public void allocateRegisters() {
        final int [] precoloredTemporaries = getPrecoloredTemporaries();
        final ColorGraph colorGraph = new ColorGraph(this.graph, 4, precoloredTemporaries);

        // pour toutes les instructions du code pre nasm
        for (NasmInst node : this.nasm.sectionText) {
            if (node.label != null) handleAllocateNasmOperand(colorGraph.color, node.label);
            if (node.source != null) handleAllocateNasmOperand(colorGraph.color, node.source);
            if (node.destination != null) handleAllocateNasmOperand(colorGraph.color, node.destination);
        }
    }

    public void handleAllocateNasmOperand(final int[] color, final NasmOperand operand) {
        if (operand instanceof NasmAddress) {
            final NasmAddress address = (NasmAddress) operand;
            if (address.base != null) handlePrecoloredNasmOperand(color, address.base);
            if (address.offset != null) handlePrecoloredNasmOperand(color, address.offset);
            return;
        }

        if (operand.isGeneralRegister()) {
            final NasmRegister register = (NasmRegister) operand;
            register.colorRegister(color[register.val]);
            return;
        }
    }

    public void affiche(String baseFileName){
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null){
            try {
                baseFileName = baseFileName;
                fileName = baseFileName + ".ig";
                out = new PrintStream(fileName);
            }

            catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        for(int i = 0; i < regNb; i++){
            Node n = this.int2Node[i];
            out.print(n + " : ( ");
            for(NodeList q = n.succ(); q!=null; q=q.tail) {
                out.print(q.head.toString());
                out.print(" ");
            }
            out.println(")");
        }
    }
}
