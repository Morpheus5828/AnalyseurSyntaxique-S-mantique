package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

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
		// algo 1
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
				for(int j = 0; i < fgs.out.get(node).getSize(); j++) {
					if(i != j) {
						if (fgs.out.get(node).isMember(i) && fgs.out.get(node).isMember(j)) {
						    graph.addEdge(this.int2Node[i], this.int2Node[j]);
						}
					}
				}
			}
		}

    }

    public void allocateRegisters(){
		// pour toutes les instructions du code pre nasm
		for(NasmInst node : this.nasm.sectionText) {

		}
    }

	public int[] getPrecoloredTemporaries() {
		final int[] precolored = new int[regNb];



		return precolored;
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
	    for(NodeList q=n.succ(); q!=null; q=q.tail) {
		out.print(q.head.toString());
		out.print(" ");
	    }
	    out.println(")");
	}
    }
}
	    
    

    
    
