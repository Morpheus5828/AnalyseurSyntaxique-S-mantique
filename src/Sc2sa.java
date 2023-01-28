import sc.analysis.*;
import sc.node.*;
import sa.*;
import util.Type;

public class Sc2sa extends DepthFirstAdapter
{
    private SaNode returnValue;
    private Type returnType;

    public void defaultIn(Node node) {
	    System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(Node node) {
	    System.out.println("</" + node.getClass().getSimpleName() + ">");
    }
    
    public SaNode getRoot() {
	    return this.returnValue;
    }

    public void inAProgramme(AProgramme node)
    {
        defaultIn(node);
    }

    public void outAProgramme(AProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgramme(AProgramme node) {
        inAProgramme(node);

        SaLDecVar variable;
        SaLDecFonc fonction;

        node.getListeDeclarationVar().apply(this);
        variable = (SaLDecVar) this.returnValue;

        node.getListeDeclarationFonc().apply(this);
        fonction = (SaLDecFonc) this.returnValue;

        this.returnValue = new SaProg(variable, fonction);

        outAProgramme(node);
    }

    public void inAListeDeclarationVar(AListeDeclarationVar node)
    {
        defaultIn(node);
    }

    public void outAListeDeclarationVar(AListeDeclarationVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeDeclarationVar(AListeDeclarationVar node) {
        inAListeDeclarationVar(node);

        SaDecVar tete;
        SaLDecVar queue;

        node.getDeclarationVar().apply(this);
        tete = (SaDecVar) this.returnValue;

        node.getListeDeclarationVarPrime().apply(this);
        queue = (SaLDecVar) this.returnValue;

        this.returnValue = new SaLDecVar(tete, queue);

        outAListeDeclarationVar(node);
    }

    public void inAVideListeDeclarationVar(AVideListeDeclarationVar node)
    {
        defaultIn(node);
    }

    public void outAVideListeDeclarationVar(AVideListeDeclarationVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeDeclarationVar(AVideListeDeclarationVar node) {
        inAVideListeDeclarationVar(node);
        this.returnValue = null;
        outAVideListeDeclarationVar(node);
    }

    public void inAListeDeclarationVarPrime(AListeDeclarationVarPrime node)
    {
        defaultIn(node);
    }

    public void outAListeDeclarationVarPrime(AListeDeclarationVarPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeDeclarationVarPrime(AListeDeclarationVarPrime node) {
        inAListeDeclarationVarPrime(node);

        SaDecVar tete;
        SaLDecVar queue;

        node.getDeclarationVar().apply(this);
        tete = (SaDecVar) this.returnValue;

        node.getListeDeclarationVarPrime().apply(this);
        queue = (SaLDecVar) this.returnValue;

        this.returnValue = new SaLDecVar(tete, queue);

        outAListeDeclarationVarPrime(node);
    }

    public void inAVideListeDeclarationVarPrime(AVideListeDeclarationVarPrime node)
    {
        defaultIn(node);
    }

    public void outAVideListeDeclarationVarPrime(AVideListeDeclarationVarPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeDeclarationVarPrime(AVideListeDeclarationVarPrime node) {
        inAVideListeDeclarationVarPrime(node);
        this.returnValue = null;
        outAVideListeDeclarationVarPrime(node);
    }

    public void inADeclarationVar(ADeclarationVar node)
    {
        defaultIn(node);
    }

    public void outADeclarationVar(ADeclarationVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationVar(ADeclarationVar node) {
        inADeclarationVar(node);

        String nomVariable = node.getIdentif().getText();
        Type typeVariable;

        node.getType().apply(this);
        typeVariable = this.returnType;

        this.returnValue = new SaDecVarSimple(nomVariable, typeVariable);

        outADeclarationVar(node);
    }

    public void inATableauDeclarationVar(ATableauDeclarationVar node)
    {
        defaultIn(node);
    }

    public void outATableauDeclarationVar(ATableauDeclarationVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATableauDeclarationVar(ATableauDeclarationVar node) {
        inATableauDeclarationVar(node);

        String nomVariable = node.getIdentif().getText();
        int taille = Integer.parseInt(node.getNombre().getText());
        Type typeVariable;

        node.getType().apply(this);
        typeVariable = this.returnType;

        this.returnValue = new SaDecTab(nomVariable, typeVariable, taille);

        outATableauDeclarationVar(node);
    }

    public void inAListeDeclarationFonc(AListeDeclarationFonc node)
    {
        defaultIn(node);
    }

    public void outAListeDeclarationFonc(AListeDeclarationFonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeDeclarationFonc(AListeDeclarationFonc node) {
        inAListeDeclarationFonc(node);

        SaDecFonc tete;
        SaLDecFonc queue;

        node.getDeclarationFonc().apply(this);
        tete = (SaDecFonc) this.returnValue;

        node.getListeDeclarationFonc().apply(this);
        queue = (SaLDecFonc) this.returnValue;

        this.returnValue = new SaLDecFonc(tete, queue);

        outAListeDeclarationFonc(node);
    }

    public void inAVideListeDeclarationFonc(AVideListeDeclarationFonc node)
    {
        defaultIn(node);
    }

    public void outAVideListeDeclarationFonc(AVideListeDeclarationFonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeDeclarationFonc(AVideListeDeclarationFonc node) {
        inAVideListeDeclarationFonc(node);
        this.returnValue = null;
        outAVideListeDeclarationFonc(node);
    }

    public void inADeclarationFonc(ADeclarationFonc node)
    {
        defaultIn(node);
    }

    public void outADeclarationFonc(ADeclarationFonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationFonc(ADeclarationFonc node) {
        inADeclarationFonc(node);

        String nom = node.getIdentif().getText();
        Type typeDeRetour;
        SaLDecVar parametres;
        SaLDecVar variables;
        SaInst corps;

        node.getTypeOptionnel().apply(this);
        typeDeRetour = (Type) this.returnType;

        node.getArgument().apply(this);
        parametres = (SaLDecVar) this.returnValue;

        node.getVarLocale().apply(this);
        variables = (SaLDecVar) this.returnValue;

        node.getBlocInstruction().apply(this);
        corps = (SaInst) this.returnValue;

        this.returnValue = new SaDecFonc(nom, typeDeRetour, parametres, variables, corps);

        outADeclarationFonc(node);
    }

    public void inABoolType(ABoolType node)
    {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node) {
        inABoolType(node);
        this.returnType = Type.BOOL;
        outABoolType(node);
    }

    public void inAEntierType(AEntierType node)
    {
        defaultIn(node);
    }

    public void outAEntierType(AEntierType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntierType(AEntierType node) {
        inAEntierType(node);
        this.returnType = Type.ENTIER;
        outAEntierType(node);
    }

    public void inATypeTypeOptionnel(ATypeTypeOptionnel node)
    {
        defaultIn(node);
    }

    public void outATypeTypeOptionnel(ATypeTypeOptionnel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATypeTypeOptionnel(ATypeTypeOptionnel node) {
        inATypeTypeOptionnel(node);
        if(node.getType() != null) node.getType().apply(this);
        outATypeTypeOptionnel(node);
    }

    public void inAVideTypeOptionnel(AVideTypeOptionnel node)
    {
        defaultIn(node);
    }

    public void outAVideTypeOptionnel(AVideTypeOptionnel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideTypeOptionnel(AVideTypeOptionnel node) {
        inAVideTypeOptionnel(node);
        this.returnType = null;
        outAVideTypeOptionnel(node);
    }

    public void inABlocInstruction(ABlocInstruction node)
    {
        defaultIn(node);
    }

    public void outABlocInstruction(ABlocInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlocInstruction(ABlocInstruction node) {
        inABlocInstruction(node);

        SaLInst saLInst;
        node.getListeInstruction().apply(this);
        saLInst = (SaLInst) this.returnValue;
        this.returnValue = new SaInstBloc(saLInst);

        outABlocInstruction(node);
    }

    public void inAListeInstruction(AListeInstruction node)
    {
        defaultIn(node);
    }

    public void outAListeInstruction(AListeInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeInstruction(AListeInstruction node) {
        inAListeInstruction(node);

        SaInst tete;
        SaLInst queue;

        node.getInstruction().apply(this);
        tete = (SaInst) this.returnValue;

        node.getListeInstruction().apply(this);
        queue = (SaLInst) this.returnValue;

        this.returnValue = new SaLInst(tete, queue);

        outAListeInstruction(node);
    }

    public void inAVideListeInstruction(AVideListeInstruction node)
    {
        defaultIn(node);
    }

    public void outAVideListeInstruction(AVideListeInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeInstruction(AVideListeInstruction node) {
        inAVideListeInstruction(node);
        this.returnValue = null;
        outAVideListeInstruction(node);
    }

    public void inAAffectationInstruction(AAffectationInstruction node)
    {
        defaultIn(node);
    }

    public void outAAffectationInstruction(AAffectationInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAffectationInstruction(AAffectationInstruction node) {
        inAAffectationInstruction(node);

        SaVar lhs;
        SaExp rhs;

        node.getVar().apply(this);
        lhs = (SaVar) this.returnValue;

        node.getExp().apply(this);
        rhs = (SaExp) this.returnValue;

        this.returnValue = new SaInstAffect(lhs, rhs);

        outAAffectationInstruction(node);
    }

    public void inASiInstruction(ASiInstruction node)
    {
        defaultIn(node);
    }

    public void outASiInstruction(ASiInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASiInstruction(ASiInstruction node)
    {
        inASiInstruction(node);

        SaExp test;
        SaInst alors;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getBlocInstruction().apply(this);
        alors = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(test, alors, null);

        outASiInstruction(node);
    }

    public void inASiSinonInstruction(ASiSinonInstruction node)
    {
        defaultIn(node);
    }

    public void outASiSinonInstruction(ASiSinonInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASiSinonInstruction(ASiSinonInstruction node)
    {
        inASiSinonInstruction(node);

        SaExp test;
        SaInst alors;
        SaInst sinon;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getSiInstruction().apply(this);
        alors = (SaInst) this.returnValue;

        node.getSinonInstruction().apply(this);
        sinon = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(test, alors, sinon);

        outASiSinonInstruction(node);
    }

    public void inATantQueInstruction(ATantQueInstruction node)
    {
        defaultIn(node);
    }

    public void outATantQueInstruction(ATantQueInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATantQueInstruction(ATantQueInstruction node) {
        inATantQueInstruction(node);

        SaExp test;
        SaInst faire;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getBlocInstruction().apply(this);
        faire = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(test, faire);

        outATantQueInstruction(node);
    }

    public void inARetourInstruction(ARetourInstruction node)
    {
        defaultIn(node);
    }

    public void outARetourInstruction(ARetourInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetourInstruction(ARetourInstruction node) {
        inARetourInstruction(node);

        SaExp val;
        node.getExp().apply(this);
        val = (SaExp) this.returnValue;
        this.returnValue = new SaInstRetour(val);

        outARetourInstruction(node);
    }

    public void inAAppelFonctionInstruction(AAppelFonctionInstruction node)
    {
        defaultIn(node);
    }

    public void outAAppelFonctionInstruction(AAppelFonctionInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelFonctionInstruction(AAppelFonctionInstruction node) {
        inAAppelFonctionInstruction(node);

        String nom = node.getIdentif().getText();
        SaLExp arguments;

        node.getListeExp().apply(this);
        arguments = (SaLExp) this.returnValue;

        this.returnValue = new SaAppel(nom, arguments);

        outAAppelFonctionInstruction(node);
    }

    public void inAEcrireInstruction(AEcrireInstruction node)
    {
        defaultIn(node);
    }

    public void outAEcrireInstruction(AEcrireInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEcrireInstruction(AEcrireInstruction node) {
        inAEcrireInstruction(node);

        SaExp arg;
        node.getExp().apply(this);
        arg = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(arg);

        outAEcrireInstruction(node);
    }

    public void inAVar(AVar node)
    {
        defaultIn(node);
    }

    public void outAVar(AVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVar(AVar node) {
        inAVar(node);

        String nom = node.getIdentif().getText();
        this.returnValue = new SaVarSimple(nom);

        outAVar(node);
    }

    public void inATableauVar(ATableauVar node)
    {
        defaultIn(node);
    }

    public void outATableauVar(ATableauVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATableauVar(ATableauVar node) {
        inATableauVar(node);

        String nom = node.getIdentif().getText();
        SaExp indice;

        node.getExp().apply(this);
        indice = (SaExp) this.returnValue;

        this.returnValue = new SaVarIndicee(nom, indice);

        outATableauVar(node);
    }

    public void inAListeExp(AListeExp node)
    {
        defaultIn(node);
    }

    public void outAListeExp(AListeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeExp(AListeExp node) {
        inAListeExp(node);

        SaExp tete;
        SaLExp queue;

        node.getExp().apply(this);
        tete = (SaExp) this.returnValue;

        node.getListeExpPrime().apply(this);
        queue = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(tete, queue);

        outAListeExp(node);
    }

    public void inAVideListeExp(AVideListeExp node)
    {
        defaultIn(node);
    }

    public void outAVideListeExp(AVideListeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeExp(AVideListeExp node) {
        inAVideListeExp(node);
        this.returnValue = null;
        outAVideListeExp(node);
    }

    public void inAListeExpPrime(AListeExpPrime node)
    {
        defaultIn(node);
    }

    public void outAListeExpPrime(AListeExpPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeExpPrime(AListeExpPrime node) {
        inAListeExpPrime(node);

        SaExp tete;
        SaLExp queue;

        node.getExp().apply(this);
        tete = (SaExp) this.returnValue;

        node.getListeExpPrime().apply(this);
        queue = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(tete, queue);

        outAListeExpPrime(node);
    }

    public void inAVideListeExpPrime(AVideListeExpPrime node)
    {
        defaultIn(node);
    }

    public void outAVideListeExpPrime(AVideListeExpPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeExpPrime(AVideListeExpPrime node) {
        inAVideListeExpPrime(node);
        this.returnValue = null;
        outAVideListeExpPrime(node);
    }

    public void inAOuExp(AOuExp node)
    {
        defaultIn(node);
    }

    public void outAOuExp(AOuExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuExp(AOuExp node) {
        inAOuExp(node);

        SaExp op1;
        SaExp op2;

        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpOr(op1, op2);

        outAOuExp(node);
    }

    public void inAExp1Exp(AExp1Exp node)
    {
        defaultIn(node);
    }

    public void outAExp1Exp(AExp1Exp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp1Exp(AExp1Exp node) {
        inAExp1Exp(node);
        if(node.getExp1() != null) node.getExp1().apply(this);
        outAExp1Exp(node);
    }

    public void inAEtExp1(AEtExp1 node)
    {
        defaultIn(node);
    }

    public void outAEtExp1(AEtExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtExp1(AEtExp1 node) {
        inAEtExp1(node);

        SaExp op1;
        SaExp op2;

        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAnd(op1, op2);

        outAEtExp1(node);
    }

    public void inAExp2Exp1(AExp2Exp1 node)
    {
        defaultIn(node);
    }

    public void outAExp2Exp1(AExp2Exp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp2Exp1(AExp2Exp1 node) {
        inAExp2Exp1(node);
        if(node.getExp2() != null) node.getExp2().apply(this);
        outAExp2Exp1(node);
    }

    public void inAEgalExp2(AEgalExp2 node)
    {
        defaultIn(node);
    }

    public void outAEgalExp2(AEgalExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalExp2(AEgalExp2 node) {
        inAEgalExp2(node);

        SaExp op1;
        SaExp op2;

        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpEqual(op1, op2);

        outAEgalExp2(node);
    }

    public void inAInfExp2(AInfExp2 node)
    {
        defaultIn(node);
    }

    public void outAInfExp2(AInfExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfExp2(AInfExp2 node) {
        inAInfExp2(node);

        SaExp op1;
        SaExp op2;

        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpInf(op1, op2);

        outAInfExp2(node);
    }

    public void inAExp3Exp2(AExp3Exp2 node)
    {
        defaultIn(node);
    }

    public void outAExp3Exp2(AExp3Exp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node) {
        inAExp3Exp2(node);
        if(node.getExp3() != null) node.getExp3().apply(this);
        outAExp3Exp2(node);
    }

    public void inAPlusExp3(APlusExp3 node)
    {
        defaultIn(node);
    }

    public void outAPlusExp3(APlusExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExp3(APlusExp3 node) {
        inAPlusExp3(node);
        SaExp op1;
        SaExp op2;

        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAdd(op1, op2);

        outAPlusExp3(node);
    }

    public void inAMoinsExp3(AMoinsExp3 node)
    {
        defaultIn(node);
    }

    public void outAMoinsExp3(AMoinsExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoinsExp3(AMoinsExp3 node) {
        inAMoinsExp3(node);

        SaExp op1;
        SaExp op2;

        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpSub(op1, op2);

        outAMoinsExp3(node);
    }

    public void inAExp4Exp3(AExp4Exp3 node)
    {
        defaultIn(node);
    }

    public void outAExp4Exp3(AExp4Exp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node) {
        inAExp4Exp3(node);
        if(node.getExp4() != null) node.getExp4().apply(this);
        outAExp4Exp3(node);
    }

    public void inAMultExp4(AMultExp4 node)
    {
        defaultIn(node);
    }

    public void outAMultExp4(AMultExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultExp4(AMultExp4 node) {
        inAMultExp4(node);

        SaExp op1;
        SaExp op2;

        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpMult(op1, op2);

        outAMultExp4(node);
    }

    public void inADivExp4(ADivExp4 node)
    {
        defaultIn(node);
    }

    public void outADivExp4(ADivExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExp4(ADivExp4 node) {
        inADivExp4(node);

        SaExp op1;
        SaExp op2;

        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpDiv(op1, op2);

        outADivExp4(node);
    }

    public void inAExp5Exp4(AExp5Exp4 node)
    {
        defaultIn(node);
    }

    public void outAExp5Exp4(AExp5Exp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node) {
        inAExp5Exp4(node);
        if(node.getExp5() != null) node.getExp5().apply(this);
        outAExp5Exp4(node);
    }

    public void inANonExp5(ANonExp5 node)
    {
        defaultIn(node);
    }

    public void outANonExp5(ANonExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonExp5(ANonExp5 node) {
        inANonExp5(node);

        SaExp op1;
        node.getExp5().apply(this);
        op1 = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op1);

        outANonExp5(node);
    }

    public void inAExp6Exp5(AExp6Exp5 node)
    {
        defaultIn(node);
    }

    public void outAExp6Exp5(AExp6Exp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node) {
        inAExp6Exp5(node);
        if(node.getExp6() != null) node.getExp6().apply(this);
        outAExp6Exp5(node);
    }

    public void inAEntreParanthesesExp6(AEntreParanthesesExp6 node)
    {
        defaultIn(node);
    }

    public void outAEntreParanthesesExp6(AEntreParanthesesExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntreParanthesesExp6(AEntreParanthesesExp6 node) {
        inAEntreParanthesesExp6(node);
        if(node.getExp() != null) node.getExp().apply(this);
        outAEntreParanthesesExp6(node);
    }

    public void inANombreExp6(ANombreExp6 node)
    {
        defaultIn(node);
    }

    public void outANombreExp6(ANombreExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombreExp6(ANombreExp6 node) {
        inANombreExp6(node);
        int entier = 0;

        if(node.getNombre() != null) {
            node.getNombre().apply(this);
            entier = Integer.parseInt(node.getNombre().getText());
        }

        this.returnValue = new SaExpInt(entier);
        outANombreExp6(node);
    }

    public void inAVraiExp6(AVraiExp6 node)
    {
        defaultIn(node);
    }

    public void outAVraiExp6(AVraiExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVraiExp6(AVraiExp6 node) {
        inAVraiExp6(node);

        if(node.getVrai() != null) node.getVrai().apply(this);
        this.returnValue = new SaExpVrai();

        outAVraiExp6(node);
    }

    public void inAFauxExp6(AFauxExp6 node)
    {
        defaultIn(node);
    }

    public void outAFauxExp6(AFauxExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFauxExp6(AFauxExp6 node) {
        inAFauxExp6(node);

        if(node.getFaux() != null) node.getFaux().apply(this);
        this.returnValue = new SaExpFaux();

        outAFauxExp6(node);
    }

    public void inAAppelFonctionExp6(AAppelFonctionExp6 node) {defaultIn(node);}

    public void outAAppelFonctionExp6(AAppelFonctionExp6 node) {defaultOut(node);}

    @Override
    public void caseAAppelFonctionExp6(AAppelFonctionExp6 node) {
        inAAppelFonctionExp6(node);

        String nom = node.getIdentif().getText();
        SaLExp arguments;

        node.getListeExp().apply(this);
        arguments = (SaLExp) this.returnValue;

        this.returnValue = new SaExpAppel(new SaAppel(nom, arguments));

        outAAppelFonctionExp6(node);
    }

    public void inALireExp6(ALireExp6 node) {defaultIn(node);}

    public void outALireExp6(ALireExp6 node) {defaultOut(node);}

    @Override
    public void caseALireExp6(ALireExp6 node) {
        inALireExp6(node);
        this.returnValue = new SaExpLire();
        outALireExp6(node);
    }

    public void inAVariableExp6(AVariableExp6 node) {defaultIn(node);}

    public void outAVariableExp6(AVariableExp6 node) {defaultOut(node);}

    @Override
    public void caseAVariableExp6(AVariableExp6 node) {
        inAVariableExp6(node);

        SaVar var;
        node.getVar().apply(this);
        var = (SaVar) this.returnValue;
        this.returnValue = new SaExpVar(var);

        outAVariableExp6(node);
    }

}
