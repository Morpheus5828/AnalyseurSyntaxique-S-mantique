package c3a;
import java.util.*;
import ts.*;
import sa.*;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    int indentation;
    public C3a getC3a(){return this.c3a;}
    
    public Sa2c3a(SaNode root, Ts tableGlobale){
	c3a = new C3a();
	C3aTemp result = c3a.newTemp();
	C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
	c3a.ajouteInst(new C3aInstCall(fct, result, ""));
	c3a.ajouteInst(new C3aInstStop(result, ""));
	
	indentation = 0;
	try{
	    root.accept(this);
	}
	catch(Exception e){}
    }

    public void defaultIn(SaNode node) {
	//for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//indentation++;
	//System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node) {
	//indentation--;
	//	for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//	System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    public C3aOperand visit(SaDecFonc node) throws Exception {
        defaultIn(node);
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem, ""));
        //c3a.ajouteInst(new C3aInstParam(node.getParametres().accept(this), ""));
        c3a.ajouteInst(new C3aInstFEnd(""));
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaExpOr node) throws Exception {
        defaultIn(node);
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(1), faux, ""));
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, new C3aConstant(1), suite, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.ajouteInst(new C3aInstJump(suite, ""));
        c3a.addLabelToNextInst(faux);
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.addLabelToNextInst(suite);
        return result;
    }

    public C3aOperand visit(SaExpAnd node) throws Exception {
        defaultIn(node);
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, new C3aConstant(0), faux, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJump(suite, ""));
        c3a.addLabelToNextInst(faux);
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(suite);
        return result;
    }

    public C3aOperand visit(SaExpVrai node) throws Exception {
        return c3a.True;
    }

    public C3aOperand visit(SaExpFaux node) throws Exception {
        return c3a.False;
    }

    public C3aOperand visit(SaExpEqual node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        C3aLabel suite = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, suite, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(suite);
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpNot node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aTemp result = c3a.newTemp();
        C3aLabel suite = c3a.newAutoLabel();
        C3aLabel faux = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.ajouteInst(new C3aInstJump(suite, ""));
        c3a.addLabelToNextInst(faux);
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.addLabelToNextInst(suite);
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpInf node) throws Exception {
        C3aLabel suite = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp1().accept(this);
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, result, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(suite);
        return result;
    }

    public C3aOperand visit(SaExpInt node) throws Exception {
        return new C3aConstant(node.getVal());
    }

    public C3aOperand visit(SaExpMult node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpDiv node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpSub node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpAdd node) throws Exception {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpAppel node) throws Exception {

    }

    public C3aOperand visit(SaExpLire node) throws Exception {
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(result, ""));
        return result;
    }





}
