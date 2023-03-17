package fg;
import nasm.*;
import util.graph.*;
import java.util.*;
import java.io.*;

public class Fg implements NasmVisitor <Void> {
    public Nasm nasm;
    public Graph graph;
    Map< NasmInst, Node> inst2Node;
    Map< Node, NasmInst> node2Inst;
    Map< String, NasmInst> label2Inst;

    public Fg(Nasm nasm){
	this.nasm = nasm;
	this.inst2Node = new HashMap< NasmInst, Node>();
	this.node2Inst = new HashMap< Node, NasmInst>();
	this.label2Inst = new HashMap< String, NasmInst>();
	this.graph = new Graph();

	/* on construit les noeuds */
	for(NasmInst nasmInst : nasm.sectionText){
	    //	    System.out.println("<" + nasmInst.getClass().getSimpleName() + ">");
	    if(nasmInst.label != null) this.label2Inst.put(((NasmLabel)nasmInst.label).val, nasmInst);
	    Node n = this.graph.newNode();
	    this.inst2Node.put(nasmInst, n);
	    this.node2Inst.put(n, nasmInst);
	}
	/* on construit les arcs */
	int instNb = nasm.sectionText.size();
	for (int i = 0; i < instNb; i++) {
	    NasmInst nasmInst = nasm.sectionText.get(i);
	}
    }


    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".fg";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	for(NasmInst nasmInst : nasm.sectionText){
	    Node n = this.inst2Node.get(nasmInst);
	    out.print(n + " : ( ");
	    for(NodeList q=n.succ(); q!=null; q=q.tail) {
		out.print(q.head.toString());
		out.print(" ");
	    }
	    out.println(")\t" + nasmInst);
	}
	
    }
    
    public Void visit(NasmAdd inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;
    }
    public Void visit(NasmCall inst){
        Node node = inst2Node.get(inst);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmDiv inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmJe inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmJle inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmJne inst){Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmMul inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmOr inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmCmp inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);return null;}
    public Void visit(NasmInst inst){return null;}
    public Void visit(NasmJge inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmJl inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmNot inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        return null;}
    public Void visit(NasmPop inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmRet inst){return null;}
    public Void visit(NasmXor inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmAnd inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmJg inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node, node.succ().head);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmJmp inst){
        Node node = inst2Node.get(inst);
        NasmInst dest = label2Inst.get(inst.address.toString());
        graph.addEdge(node, inst2Node.get(dest));
        return null;}
    public Void visit(NasmMov inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmPush inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmSub inst){
        Node node = inst2Node.get(inst);
        graph.addEdge(node,node.succ().head);
        return null;}
    public Void visit(NasmEmpty inst){return null;}
    public Void visit(NasmInt inst){return null;}

    public Void visit(NasmAddress operand){return null;}
    public Void visit(NasmConstant operand){return null;}
    public Void visit(NasmLabel operand){return null;}
    public Void visit(NasmRegister operand){return null;}

    public Void visit(NasmResb pseudoInst){return null;}
    public Void visit(NasmResw pseudoInst){return null;}
    public Void visit(NasmResd pseudoInst){return null;}
    public Void visit(NasmResq pseudoInst){return null;}
    public Void visit(NasmRest pseudoInst){return null;}

}
