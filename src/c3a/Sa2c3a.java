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

    public C3aOperand visit(SaExpVar node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getVar().accept(this);
        defaultOut(node);
        return op1;
    }

    public C3aOperand visit(SaInstEcriture node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(op1, ""));
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstTantQue node) throws Exception
    {
        defaultIn(node);
        C3aLabel test = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        c3a.addLabelToNextInst(test);
        C3aOperand op1 = node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), suite, ""));

        if (node.getFaire() != null) node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(test, ""));
        c3a.addLabelToNextInst(suite);

        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstAffect node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getLhs().accept(this);
        C3aOperand op2 =node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(op2, op1, ""));
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaVarSimple node) throws Exception
    {
        defaultIn(node);
        defaultOut(node);
        return new C3aVar(node.getTsItem(), null);
    }

    public C3aOperand visit(SaAppel node) throws Exception
    {
        defaultIn(node);
        C3aOperand result = c3a.newTemp();
        if(node.getArguments() != null) node.getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(new C3aFunction(node.tsItem), result, ""));
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaInstSi node) throws Exception
    {
        defaultIn(node);
        if (node.getSinon() == null) {
            C3aLabel suite = c3a.newAutoLabel();
            C3aLabel faux = c3a.newAutoLabel();

            C3aOperand op1 = node.getTest().accept(this);
            c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));
            if (node.getAlors() != null) node.getAlors().accept(this);

            c3a.addLabelToNextInst(faux);
        } else {
            C3aLabel faux = c3a.newAutoLabel();
            C3aLabel suite = c3a.newAutoLabel();

            C3aOperand op1 = node.getTest().accept(this);
            c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));
            if (node.getAlors() != null) node.getAlors().accept(this);

            c3a.ajouteInst(new C3aInstJump(suite, ""));
            c3a.addLabelToNextInst(faux);
            node.getSinon().accept(this);

            if(node.getSinon() != null) c3a.addLabelToNextInst(suite);
        }

        defaultOut(node);
        return null;
    }

    // INST -> ret EXP 
    public C3aOperand visit(SaInstRetour node) throws Exception
    {
        defaultIn(node);
        C3aOperand returnValue = node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(returnValue, ""));
        //Ajout d'un autre fend car retour
        c3a.ajouteInst(new C3aInstFEnd(""));
        defaultOut(node);
        return returnValue;
    }

    public C3aOperand visit(SaVarIndicee node) throws Exception
    {
        defaultIn(node);
        C3aOperand indice = node.getIndice().accept(this);
        defaultOut(node);
        return new C3aVar(node.getTsItem(), indice);
    }

    public C3aOperand visit(SaDecFonc node) throws Exception {
        defaultIn(node);
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "entree fonction"));

        if(node.getParametres() != null) node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);

        c3a.ajouteInst(new C3aInstFEnd(""));
        defaultOut(node);
        return new C3aFunction(node.tsItem);
    }

    public C3aOperand visit(SaExpOr node) throws Exception {
        defaultIn(node);
        C3aLabel suite = c3a.newAutoLabel();
        C3aLabel faux = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, new C3aConstant(0), faux, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2, new C3aConstant(0), faux, ""));

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.ajouteInst(new C3aInstJump(suite, ""));
        c3a.addLabelToNextInst(faux);
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.addLabelToNextInst(suite);
        return result;
    }

    public C3aOperand visit(SaExpAnd node) throws Exception {
        defaultIn(node);
        C3aLabel suite = c3a.newAutoLabel();
        C3aLabel faux = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));
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
        C3aTemp result = c3a.newTemp();
        C3aLabel suite = c3a.newAutoLabel();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, suite, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(suite);
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpNot node) throws Exception {
        defaultIn(node);
        C3aTemp result = c3a.newTemp();
        C3aLabel suite = c3a.newAutoLabel();

        C3aOperand op1 = node.getOp1().accept(this);

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), suite, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(suite);
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpInf node) throws Exception {
        C3aLabel suite = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, suite, ""));
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

    public C3aOperand visit(SaExpAppel node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getVal().accept(this);
        defaultOut(node);
        return op1;
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

    public C3aOperand visit(SaExpLire node) throws Exception {
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(result, ""));
        return result;
    }

    public C3aOperand visit(SaLExp node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getTete().accept(this);
        c3a.ajouteInst(new C3aInstParam(op1, ""));
        if(node.getQueue() != null)
            node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }



}
