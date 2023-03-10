package nasm;
import java.util.*;
import ts.*;
import c3a.*;

public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private NasmRegister esp;
    private NasmRegister ebp;

    
    public C3a2nasm(C3a c3a, Ts tableGlobale){
	this.c3a = c3a;
	nasm = new Nasm(tableGlobale);
	nasm.setTempCounter(c3a.getTempCounter());
	
	this.tableGlobale = tableGlobale;
	this.currentFct = null;
	esp = new NasmRegister(-1);
	esp.colorRegister(Nasm.REG_ESP);

	ebp = new NasmRegister(-1);
	ebp.colorRegister(Nasm.REG_EBP);

	NasmOperand res;
	for(C3aInst c3aInst : c3a.listeInst){
	    //	   	    System.out.println("<" + c3aInst.getClass().getSimpleName() + ">");
	    res = c3aInst.accept(this);
	}
    }

    public Nasm getNasm(){return nasm;}

    /*--------------------------------------------------------------------------------------------------------------
      transforme une opérande trois adresses en une opérande asm selon les règles suivantes :
      
      C3aConstant -> NasmConstant
      C3aTemp     -> NasmRegister
      C3aLabel    -> NasmLabel
      C3aFunction -> NasmLabel
      C3aVar      -> NasmAddress
      --------------------------------------------------------------------------------------------------------------*/

    public NasmOperand visit(C3aTemp temp){
	return new NasmRegister(temp.num);
    }

	@Override
	public NasmOperand visit(C3aVar oper) {
		if (oper.index == null)
				return new NasmAddress(new NasmConstant(oper.item.adresse), '+', null);
		else
			return new NasmAddress(new NasmConstant(oper.item.adresse), '+', oper.index.accept(this));
	}

	@Override
	public NasmOperand visit(C3aFunction oper) {
		return new NasmLabel(oper.val.identif);
	}

	/*--------------------------------------------------------------------------------------------------------------*/
    
    public NasmOperand visit(C3aInstAdd inst) {
	NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
	nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), ""));
	nasm.ajouteInst(new NasmAdd(null , inst.result.accept(this), inst.op2.accept(this), ""));
	return null;
    }

	@Override
	public NasmOperand visit(C3aInstCall inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmSub(label, esp, new NasmConstant(4), ""));
		nasm.ajouteInst(new NasmCall(null, inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmPop(null, inst.result.accept(this), ""));
		nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(inst.op1.val.nbArgs * 4), ""));

		return null;
	}

	@Override
	public NasmOperand visit(C3aInstFBegin inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmPush(label , ebp, ""));
		nasm.ajouteInst(new NasmMov(null , ebp, esp, ""));
		if (inst.val.saDecFonc.getVariable() == null)
			nasm.ajouteInst(new NasmSub(null , esp, new NasmConstant(0), ""));
		else
			nasm.ajouteInst(new NasmSub(null , esp, new NasmConstant(inst.val.saDecFonc.getVariable().length() * 4), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInst inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfLess inst) {
		NasmRegister r4 = nasm.newRegister();

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, r4, inst.op1.accept(this),""));
		nasm.ajouteInst(new NasmCmp(null, r4, inst.op2.accept(this),""));
		nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstMult inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmMul(null , inst.result.accept(this), inst.op2.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstRead inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);
		NasmRegister r1 = nasm.newRegister();

		nasm.ajouteInst(new NasmMov(label, reg_eax, new NasmLabel("sinput"), ""));
		nasm.ajouteInst(new NasmCall(null, new NasmLabel("readline"),""));
		nasm.ajouteInst(new NasmCall(null, new NasmLabel("atoi"),""));
		nasm.ajouteInst(new NasmMov(null , r1, reg_eax, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstSub inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmSub(null , inst.result.accept(this), inst.op2.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstAffect inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstDiv inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);
		NasmRegister reg_ebx = nasm.newRegister();
		//reg_eax.colorRegister(Nasm.REG_EBX);

		nasm.ajouteInst(new NasmMov(label, reg_eax, inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmMov(label, reg_ebx, inst.op2.accept(this), ""));
		nasm.ajouteInst(new NasmDiv(null , reg_ebx, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstFEnd inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmAdd(label, esp, new NasmConstant(4), ""));
		nasm.ajouteInst(new NasmPop(null, ebp, ""));
		nasm.ajouteInst(new NasmRet(null, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		NasmRegister r4 = nasm.newRegister();
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, r4, inst.op1.accept(this),""));
		nasm.ajouteInst(new NasmCmp(null, r4, inst.op2.accept(this),""));
		nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		NasmRegister r4 = nasm.newRegister();
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, r4, inst.op1.accept(this),""));
		nasm.ajouteInst(new NasmCmp(null, r4, inst.op2.accept(this),""));
		nasm.ajouteInst(new NasmJne(null, inst.result.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJump inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmJmp(label, inst.result.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstParam inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmPush(label, inst.op1.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, new NasmAddress(ebp, '+', new NasmConstant(8)) ,inst.op1.accept(this), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstWrite inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, reg_eax, inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"), ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstStop inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmInt(label, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aConstant oper) {
		return new NasmConstant(oper.val);
	}

	@Override
	public NasmOperand visit(C3aBooleanConstant oper) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aLabel oper) {
		return new NasmLabel(oper.toString());
	}


}
