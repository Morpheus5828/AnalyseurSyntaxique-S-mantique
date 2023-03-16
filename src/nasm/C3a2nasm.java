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
		if (currentFct.getTable().containVar(oper.item.identif)) {
			TsItemVar var = currentFct.getTable().getVar(oper.item.identif);
			if (var.isParam)
				return new NasmAddress(ebp, '+', new NasmConstant(8+currentFct.getNbArgs()*4 - var.adresse));
			return new NasmAddress(ebp, '-', new NasmConstant(4 + var.adresse));
		} else {
			if (oper.index == null)
				return new NasmAddress(new NasmLabel(oper.item.identif), '+', new NasmConstant(0));
			else {
				NasmRegister r1 = nasm.newRegister();
				nasm.ajouteInst(new NasmMov(null, r1, oper.index.accept(this), ""));
				nasm.ajouteInst(new NasmMul(null, r1, new NasmConstant(4), ""));
				return new NasmAddress(new NasmLabel(oper.item.identif), '+', r1);
			}
		}
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
		TsItemFct saveCurrentFct = currentFct;
		currentFct = inst.op1.val;
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmSub(label, esp, new NasmConstant(4), "allocation mémoire pour la valeur de retour"));
		nasm.ajouteInst(new NasmCall(null, inst.op1.accept(this), ""));
		nasm.ajouteInst(new NasmPop(null, inst.result.accept(this), "récupération de la valeur de retour"));
		if (inst.op1.val.nbArgs != 0)
			nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(inst.op1.val.nbArgs * 4), "désallocation des arguments"));
		currentFct = saveCurrentFct;
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstFBegin inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);

		NasmRegister reg_ebx = nasm.newRegister();
		reg_ebx.colorRegister(Nasm.REG_EBX);

		NasmRegister reg_ecx = nasm.newRegister();
		reg_ecx.colorRegister(Nasm.REG_ECX);

		NasmRegister reg_edx = nasm.newRegister();
		reg_edx.colorRegister(Nasm.REG_EDX);

		currentFct = inst.val;

		nasm.ajouteInst(new NasmPush(new NasmLabel(inst.val.identif), ebp, "sauvegarde la valeur de ebp"));
		nasm.ajouteInst(new NasmMov(null , ebp, esp, "nouvelle valeur de ebp"));

		nasm.ajouteInst(new NasmPush(null, reg_eax, "sauvegarde de eax"));
		nasm.ajouteInst(new NasmPush(null, reg_ebx, "sauvegarde de ebx"));
		nasm.ajouteInst(new NasmPush(null, reg_ecx, "sauvegarde de ecx"));
		nasm.ajouteInst(new NasmPush(null, reg_edx, "sauvegarde de edx"));

		if (inst.val.saDecFonc.getVariable() == null)
			nasm.ajouteInst(new NasmSub(null , esp, new NasmConstant(0), "allocation des variables locales"));
		else
			nasm.ajouteInst(new NasmSub(null , esp, new NasmConstant(inst.val.saDecFonc.getVariable().length() * 4), "allocation des variables locales"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInst inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfLess inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);

		if (op1 instanceof NasmConstant || (op1 instanceof NasmAddress && op2 instanceof NasmAddress)) {
			NasmRegister r1 = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, r1, op1,"JumpIfLess 1"));
			nasm.ajouteInst(new NasmCmp(null, r1, op2,"on passe par un registre temporaire"));
			nasm.ajouteInst(new NasmJl(null, inst.result.accept(this), "JumpIfLess 2"));
		} else {
			nasm.ajouteInst(new NasmCmp(label, op1, op2,"JumpIfLess 1"));
			nasm.ajouteInst(new NasmJl(null, inst.result.accept(this), "JumpIfLess 2"));
		}
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

		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand result = inst.result.accept(this);

		nasm.ajouteInst(new NasmMov(label, result, op1, "Affect"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstDiv inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);

		NasmRegister reg_edx = nasm.newRegister();
		reg_edx.colorRegister(Nasm.REG_EDX);

		NasmRegister r1 = nasm.newRegister();

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

		nasm.ajouteInst(new NasmMov(label, reg_edx, new NasmConstant(0), "mise à 0 des bits de poids fort du dividende"));
		nasm.ajouteInst(new NasmMov(null, reg_eax, inst.op1.accept(this), "affectation des bits de poids faible du dividende"));
		nasm.ajouteInst(new NasmMov(null, r1, inst.op2.accept(this), ""));
		nasm.ajouteInst(new NasmDiv(null , r1, ""));

		nasm.ajouteInst(new NasmMov(null, reg_edx, reg_edx, "rend explicite l'utilisation de edx pour ne pas que sa valeur soit modifiée"));
		nasm.newRegister();
		nasm.ajouteInst(new NasmMov(null, inst.result.accept(this), reg_eax, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstFEnd inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);

		NasmRegister reg_ebx = nasm.newRegister();
		reg_ebx.colorRegister(Nasm.REG_EBX);

		NasmRegister reg_ecx = nasm.newRegister();
		reg_ecx.colorRegister(Nasm.REG_ECX);

		NasmRegister reg_edx = nasm.newRegister();
		reg_edx.colorRegister(Nasm.REG_EDX);

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		if (currentFct.saDecFonc.getVariable() == null)
			nasm.ajouteInst(new NasmAdd(label, esp, new NasmConstant(0), "désallocation des variables locales"));
		else
			nasm.ajouteInst(new NasmAdd(label, esp, new NasmConstant(4*currentFct.saDecFonc.getVariable().length()), "désallocation des variables locales"));
		nasm.ajouteInst(new NasmPop(null, reg_edx, "restaure edx"));
		nasm.ajouteInst(new NasmPop(null, reg_ecx, "restaure ecx"));
		nasm.ajouteInst(new NasmPop(null, reg_ebx, "restaure ebx"));
		nasm.ajouteInst(new NasmPop(null, reg_eax, "restaure eax"));

		nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp"));
		nasm.ajouteInst(new NasmRet(null, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		
		if (op1 instanceof NasmConstant || (op1 instanceof NasmAddress && op2 instanceof NasmAddress)) {
			NasmRegister r1 = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, r1, op1,"JumpIfEqual 1"));
			nasm.ajouteInst(new NasmCmp(null, r1, op2,"on passe par un registre temporaire"));
			nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), "JumpIfEqual 2"));
		} else {
			nasm.ajouteInst(new NasmCmp(label, op1, op2,"JumpIfEqual 1"));
			nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), "JumpIfEqual 2"));
		}
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);

		if (op1 instanceof NasmConstant || (op1 instanceof NasmAddress && op2 instanceof NasmAddress)) {
			NasmRegister r1 = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, r1, op1,"jumpIfNotEqual 1"));
			nasm.ajouteInst(new NasmCmp(null, r1, op2,"on passe par un registre temporaire"));
			nasm.ajouteInst(new NasmJne(null, inst.result.accept(this), "jumpIfNotEqual 2"));
		} else {
			nasm.ajouteInst(new NasmCmp(label, op1, op2,"jumpIfNotEqual 1"));
			nasm.ajouteInst(new NasmJne(null, inst.result.accept(this), "jumpIfNotEqual 2"));
		}
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJump inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmJmp(label, inst.result.accept(this), "Jump"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstParam inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmPush(label, inst.op1.accept(this), "Param"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, new NasmAddress(ebp, '+', new NasmConstant(8)) ,inst.op1.accept(this), "ecriture de la valeur de retour"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstWrite inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmMov(label, reg_eax, inst.op1.accept(this), "Write 1"));
		nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"), "Write 2"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstStop inst) {
		NasmRegister reg_eax = nasm.newRegister();
		reg_eax.colorRegister(Nasm.REG_EAX);
		NasmRegister reg_ebx = nasm.newRegister();
		reg_ebx.colorRegister(Nasm.REG_EBX);

		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

		nasm.ajouteInst(new NasmMov(label, reg_ebx, new NasmConstant(0), " valeur de retour du programme"));
		nasm.ajouteInst(new NasmMov(null, reg_eax, new NasmConstant(1), " code de sortie"));
		nasm.ajouteInst(new NasmInt(null, ""));
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
