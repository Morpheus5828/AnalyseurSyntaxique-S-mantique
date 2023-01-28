gpackage sc;

import sa.*;
import sc.analysis.AnalysisAdapter;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;
import util.Type;

public class Sc2Sa extends DepthFirstAdapter {
    private SaNode returnValue;
    private Type returnType;

    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgramme().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAAxiomeProgramme(AAxiomeProgramme node)
    {
        defaultIn(node);
    }

    public void outAAxiomeProgramme(AAxiomeProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAxiomeProgramme(AAxiomeProgramme node) {
        SaLDecVar variable;
        SaLDecFonc fonction;

        node.getListeVar().apply(this);
        variable = (SaLDecVar) this.returnValue;
        node.getListeFonc().apply(this);
        fonction = (SaLDecFonc) this.returnValue;

        this.returnValue = new SaProg(variable, fonction);

    }

    public void inAListeVar(AListeVar node)
    {
        defaultIn(node);
    }

    public void outAListeVar(AListeVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeVar(AListeVar node) {
        SaDecVar tete;
        SaLDecVar queue;
        node.getDeclarationVar().apply(this);
        tete = (SaDecVar) this.returnValue;
        node.getListeVarPrime().apply(this);
        queue = (SaLDecVar) this.returnValue;
        this.returnValue = new SaLDecVar(tete, queue);
    }

    public void inAVideListeVar(AVideListeVar node)
    {
        defaultIn(node);
    }

    public void outAVideListeVar(AVideListeVar node)
    {
        defaultOut(node);
    }
    @Override
    public void caseAVideListeVar(AVideListeVar node) {}

    public void inAListeVarPrime(AListeVarPrime node)
    {
        defaultIn(node);
    }

    public void outAListeVarPrime(AListeVarPrime node)
    {
        defaultOut(node);
    }

    //TODO on verra
    @Override
    public void caseAListeVarPrime(AListeVarPrime node) {
        SaDecVar tete;
        SaLDecVar queue;
        node.getDeclarationVar().apply(this);
        tete = (SaDecVar) this.returnValue;
        node.getListeVarPrime().apply(this);
        queue = (SaLDecVar) this.returnValue;
        this.returnValue = new SaLDecVar(tete, queue);
    }

    public void inAVideListeVarPrime(AVideListeVarPrime node)
    {
        defaultIn(node);
    }

    public void outAVideListeVarPrime(AVideListeVarPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeVarPrime(AVideListeVarPrime node)
    {
        inAVideListeVarPrime(node);
        outAVideListeVarPrime(node);
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
        String nomVariable = null;
        Type typeVariable;
        inADeclarationVar(node);
        node.getType().apply(this);
        typeVariable = this.returnType;
        nomVariable = node.getIdentif().getText();
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
        String nomVariable = null;
        int taille = Integer.parseInt(node.getNombre().getText());
        Type typeVariable;
        inATableauDeclarationVar(node);
        node.getType().apply(this);
        typeVariable = this.returnType;
        nomVariable = node.getIdentif().getText();
        this.returnValue = new SaDecTab(nomVariable, typeVariable, taille);
        outATableauDeclarationVar(node);
    }

    public void inAListeFonc(AListeFonc node) {
        defaultIn(node);
    }

    public void outAListeFonc(AListeFonc node) {
        defaultOut(node);
    }

    @Override
    public void caseAListeFonc(AListeFonc node) {
        SaDecFonc tete;
        SaLDecFonc queue;
        node.getDeclarationFonc().apply(this);
        tete = (SaDecFonc) this.returnValue;
        node.getDeclarationFonc().apply(this);
        queue = (SaLDecFonc) this.returnValue;
        this.returnValue = new SaLDecFonc(tete, queue);
    }

    public void inAVideListeFonc(AVideListeFonc node) {
        defaultIn(node);
    }

    public void outAVideListeFonc(AVideListeFonc node) {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeFonc(AVideListeFonc node) {

    }

    public void inADeclarationFonc(ADeclarationFonc node) {
        defaultIn(node);
    }

    public void outADeclarationFonc(ADeclarationFonc node) {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationFonc(ADeclarationFonc node) {
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
    }

    public void inABoolType(ABoolType node) {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node) {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node) {
        inABoolType(node);
        this.returnType = Type.BOOL;
        outABoolType(node);
    }

    public void inAEntierType(AEntierType node) {
        defaultIn(node);
    }

    public void outAEntierType(AEntierType node) {
        defaultOut(node);
    }

    @Override
    public void caseAEntierType(AEntierType node) {
        inAEntierType(node);
        this.returnType = Type.ENTIER;
        outAEntierType(node);
    }

    public void inATypeTypeOptionnel(ATypeTypeOptionnel node) {
        defaultIn(node);
    }

    public void outATypeTypeOptionnel(ATypeTypeOptionnel node) {
        defaultOut(node);
    }

    @Override
    public void caseATypeTypeOptionnel(ATypeTypeOptionnel node) {
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
    }

    public void inAVideTypeOptionnel(AVideTypeOptionnel node) {
        defaultIn(node);
    }

    public void outAVideTypeOptionnel(AVideTypeOptionnel node) {
        defaultOut(node);
    }

    @Override
    public void caseAVideTypeOptionnel(AVideTypeOptionnel node)
    {
        this.returnType = null;
    }

    public void inABlocInstruction(ABlocInstruction node) {
        defaultIn(node);
    }

    public void outABlocInstruction(ABlocInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseABlocInstruction(ABlocInstruction node) {
        SaLInst saLInst;
        node.getListeInstruction().apply(this);
        saLInst = (SaLInst) this.returnValue;
        this.returnValue = new SaInstBloc(saLInst);
    }

    public void inAListeInstruction(AListeInstruction node) {
        defaultIn(node);
    }

    public void outAListeInstruction(AListeInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseAListeInstruction(AListeInstruction node) {
        SaInst tete;
        SaLInst queue;
        node.getInstruction().apply(this);
        tete = (SaInst) this.returnValue;
        node.getListeInstruction().apply(this);
        queue = (SaLInst) this.returnValue;
        this.returnValue = new SaLInst(tete, queue);
    }

    public void inAVideListeInstruction(AVideListeInstruction node) {
        defaultIn(node);
    }

    public void outAVideListeInstruction(AVideListeInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeInstruction(AVideListeInstruction node) {
        inAVideListeInstruction(node);
        outAVideListeInstruction(node);
        this.returnValue = null;
    }

    public void inAAffectationInstruction(AAffectationInstruction node) {
        defaultIn(node);
    }

    public void outAAffectationInstruction(AAffectationInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseAAffectationInstruction(AAffectationInstruction node) {
        SaVar lhs;
        SaExp rhs;
        node.getVar().apply(this);
        lhs = (SaVar) this.returnValue;
        node.getExpr().apply(this);
        rhs = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(lhs, rhs);
    }

    public void inASiInstruction(ASiInstruction node) {
        defaultIn(node);
    }

    public void outASiInstruction(ASiInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseASiInstruction(ASiInstruction node) {
        SaExp test;
        SaInst alors;
        node.getExpr().apply(this);
        test = (SaExp) this.returnValue;
        node.getBlocInstruction().apply(this);
        alors = (SaInst) this.returnValue;
        this.returnValue = new SaInstSi(test, alors, null);
    }

    public void inASiSinonInstruction(ASiSinonInstruction node) {
        defaultIn(node);
    }

    public void outASiSinonInstruction(ASiSinonInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseASiSinonInstruction(ASiSinonInstruction node) {
        SaExp test;
        SaInst alors;
        SaInst sinon;
        node.getExpr().apply(this);
        test = (SaExp) this.returnValue;
        node.getSiInstruction().apply(this);
        alors = (SaInst) this.returnValue;
        node.getSinonInstruction().apply(this);
        sinon = (SaInst) this.returnValue;
        this.returnValue = new SaInstSi(test, alors, sinon);
    }

    public void inATantQueInstruction(ATantQueInstruction node) {
        defaultIn(node);
    }

    public void outATantQueInstruction(ATantQueInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseATantQueInstruction(ATantQueInstruction node) {
        SaExp test;
        SaInst faire;
        node.getExpr().apply(this);
        test = (SaExp) this.returnValue;
        node.getBlocInstruction().apply(this);
        faire = (SaInst) this.returnValue;
        this.returnValue = new SaInstTantQue(test, faire);
    }

    public void inARetourInstruction(ARetourInstruction node) {
        defaultIn(node);
    }

    public void outARetourInstruction(ARetourInstruction node) {
        defaultOut(node);
    }

    @Override
    public void caseARetourInstruction(ARetourInstruction node) {
        SaExp val;
        node.getExpr().apply(this);
        val = (SaExp) this.returnValue;
        this.returnValue = new SaInstRetour(val);
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
        String nom = node.getIdentif().getText();
        SaLExp arguments;

        node.getListeExpr().apply(this);
        arguments = (SaLExp) this.returnValue;
        this.returnValue = new SaAppel(nom, arguments);
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
    public void caseAEcrireInstruction(AEcrireInstruction node)
    {
        SaExp arg;
        node.getExpr().apply(this);
        arg = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(arg);
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
    public void caseAVar(AVar node)
    {
        String nom = node.getIdentif().getText();
        this.returnValue = new SaVarSimple(nom);
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
    public void caseATableauVar(ATableauVar node)
    {
        String nom = node.getIdentif().getText();
        SaExp indice;
        node.getExpr().apply(this);
        indice = (SaExp) this.returnValue;
        this.returnValue = new SaVarIndicee(nom, indice);
    }

    public void inAListeExpr(AListeExpr node)
    {
        defaultIn(node);
    }

    public void outAListeExpr(AListeExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeExpr(AListeExpr node)
    {
        SaExp tete;
        SaLExp queue;
        node.getExpr().apply(this);
        tete = (SaExp) this.returnValue;
        node.getListeExprPrime().apply(this);
        queue = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(tete, queue);
    }

    public void inAVideListeExpr(AVideListeExpr node)
    {
        defaultIn(node);
    }

    public void outAVideListeExpr(AVideListeExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeExpr(AVideListeExpr node)
    {
        inAVideListeExpr(node);
        outAVideListeExpr(node);
    }

    public void inAListeExprPrime(AListeExprPrime node)
    {
        defaultIn(node);
    }

    public void outAListeExprPrime(AListeExprPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeExprPrime(AListeExprPrime node)
    {
        inAListeExprPrime(node);
        outAListeExprPrime(node);
    }

    public void inAVideListeExprPrime(AVideListeExprPrime node)
    {
        defaultIn(node);
    }

    public void outAVideListeExprPrime(AVideListeExprPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeExprPrime(AVideListeExprPrime node)
    {
        inAVideListeExprPrime(node);
        outAVideListeExprPrime(node);
    }

    public void inAOuExpr(AOuExpr node)
    {
        defaultIn(node);
    }

    public void outAOuExpr(AOuExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuExpr(AOuExpr node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr2().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2);
    }

    public void inAPrioriteSuivanteExpr(APrioriteSuivanteExpr node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr(APrioriteSuivanteExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr(APrioriteSuivanteExpr node) {
        if(node.getExpr2() != null) node.getExpr2().apply(this);

    }

    public void inAEtExpr2(AEtExpr2 node)
    {
        defaultIn(node);
    }

    public void outAEtExpr2(AEtExpr2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtExpr2(AEtExpr2 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAnd(op1, op2);
    }

    @Override
    public void caseAPrioriteSuivanteExpr2(APrioriteSuivanteExpr2 node)
    {
        if(node.getExpr3() != null) node.getExpr3().apply(this);
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2);
    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2);
    }

    public void inAPrioriteSuivanteExpr3(APrioriteSuivanteExpr3 node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr3(APrioriteSuivanteExpr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr3(APrioriteSuivanteExpr3 node)
    {
        if(node.getExpr4() != null) node.getExpr4().apply(this);
    }

    public void inAPlusExpr4(APlusExpr4 node)
    {
        defaultIn(node);
    }

    public void outAPlusExpr4(APlusExpr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpr4(APlusExpr4 node) {
        SaExp op1;
        SaExp op2;
        node.getExpr4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2);
    }

    public void caseAMoinsExpr4(AMoinsExpr4 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2);
    }


    @Override
    public void caseAPrioriteSuivanteExpr4(APrioriteSuivanteExpr4 node)
    {
        if(node.getExpr5() != null) node.getExpr5().apply(this);
    }


    @Override
    public void caseAMultExpr5(AMultExpr5 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr5().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr6().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2);
    }


    @Override
    public void caseADivExpr5(ADivExpr5 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExpr5().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr6().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
    }

    @Override
    public void caseAPrioriteSuivanteExpr5(APrioriteSuivanteExpr5 node)
    {
        if(node.getExpr6() != null) node.getExpr6().apply(this);
    }


    @Override
    public void caseANonExpr6(ANonExpr6 node)
    {
        SaExp op1;
        node.getExpr6().apply(this);
        op1 = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op1);
    }

    public void inAPrioriteSuivanteExpr6(APrioriteSuivanteExpr6 node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr6(APrioriteSuivanteExpr6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr6(APrioriteSuivanteExpr6 node)
    {
        if(node.getExpr7() != null) node.getExpr7().apply(this);
    }

    @Override
    public void caseAEntreParanthesesExpr7(AEntreParanthesesExpr7 node) {
        if(node.getExpr() != null) node.getExpr().apply(this);
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node)
    {
        int entier = 0;
        inANombreExpr7(node);
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
            entier = Integer.parseInt(node.getNombre().getText());
        }
        this.returnValue = new SaExpInt(entier);
        outANombreExpr7(node);
    }

    public void inAVraiExpr7(AVraiExpr7 node)
    {
        defaultIn(node);
    }

    public void outAVraiExpr7(AVraiExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVraiExpr7(AVraiExpr7 node)
    {
        inAVraiExpr7(node);
        if(node.getVrai() != null)
        {
            node.getVrai().apply(this);
        }
        this.returnValue = new SaExpVrai();
        outAVraiExpr7(node);
    }

    public void inAFauxExpr7(AFauxExpr7 node)
    {
        defaultIn(node);
    }

    public void outAFauxExpr7(AFauxExpr7 node) {
        defaultOut(node);
    }

    @Override
    public void caseAFauxExpr7(AFauxExpr7 node)
    {
        inAFauxExpr7(node);
        if(node.getFaux() != null)
        {
            node.getFaux().apply(this);
        }
        this.returnValue = new SaExpFaux();
        outAFauxExpr7(node);
    }

    public void inAAppelFonctionExpr7(AAppelFonctionExpr7 node)
    {
        defaultIn(node);
    }

    public void outAAppelFonctionExpr7(AAppelFonctionExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelFonctionExpr7(AAppelFonctionExpr7 node)
    {
        String nom = node.getIdentif().getText();
        SaLExp arguments;
        node.getListeExpr().apply(this);
        arguments = (SaLExp) this.returnValue;
        this.returnValue = new SaExpAppel(new SaAppel(nom, arguments));

    }

    public void inALireExpr7(ALireExpr7 node)
    {
        defaultIn(node);
    }

    public void outALireExpr7(ALireExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireExpr7(ALireExpr7 node)
    {
        this.returnValue = new SaExpLire();
    }

    public void inAVariableExpr7(AVariableExpr7 node)
    {
        defaultIn(node);
    }

    public void outAVariableExpr7(AVariableExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableExpr7(AVariableExpr7 node)
    {
        SaVar var;
        node.getVar().apply(this);
        var = (SaVar) this.returnValue;
        this.returnValue = new SaExpVar(var);
    }



}
