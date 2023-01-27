/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
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
        node.getEOF().apply(this);
        node.getPProgramme().apply(this);
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
    public void caseAAxiomeProgramme(AAxiomeProgramme node)
    {
        inAAxiomeProgramme(node);
        if(node.getListeFonc() != null)
        {
            node.getListeFonc().apply(this);
        }
        if(node.getListeVar() != null)
        {
            node.getListeVar().apply(this);
        }
        outAAxiomeProgramme(node);
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
    public void caseAListeVar(AListeVar node)
    {
        inAListeVar(node);
        if(node.getListeVarPrime() != null)
        {
            node.getListeVarPrime().apply(this);
        }
        if(node.getDeclarationVar() != null)
        {
            node.getDeclarationVar().apply(this);
        }
        outAListeVar(node);
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
    public void caseAVideListeVar(AVideListeVar node)
    {
        inAVideListeVar(node);
        outAVideListeVar(node);
    }

    public void inAListeVarPrime(AListeVarPrime node)
    {
        defaultIn(node);
    }

    public void outAListeVarPrime(AListeVarPrime node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeVarPrime(AListeVarPrime node)
    {
        inAListeVarPrime(node);
        if(node.getListeVarPrime() != null)
        {
            node.getListeVarPrime().apply(this);
        }
        if(node.getDeclarationVar() != null)
        {
            node.getDeclarationVar().apply(this);
        }
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        outAListeVarPrime(node);
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
    public void caseADeclarationVar(ADeclarationVar node)
    {
        inADeclarationVar(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
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
    public void caseATableauDeclarationVar(ATableauDeclarationVar node)
    {
        inATableauDeclarationVar(node);
        if(node.getCrochetDroit() != null)
        {
            node.getCrochetDroit().apply(this);
        }
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        if(node.getCrochetGauche() != null)
        {
            node.getCrochetGauche().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outATableauDeclarationVar(node);
    }

    public void inAListeFonc(AListeFonc node)
    {
        defaultIn(node);
    }

    public void outAListeFonc(AListeFonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeFonc(AListeFonc node)
    {
        inAListeFonc(node);
        if(node.getListeFonc() != null)
        {
            node.getListeFonc().apply(this);
        }
        if(node.getDeclarationFonc() != null)
        {
            node.getDeclarationFonc().apply(this);
        }
        outAListeFonc(node);
    }

    public void inAVideListeFonc(AVideListeFonc node)
    {
        defaultIn(node);
    }

    public void outAVideListeFonc(AVideListeFonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListeFonc(AVideListeFonc node)
    {
        inAVideListeFonc(node);
        outAVideListeFonc(node);
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
    public void caseADeclarationFonc(ADeclarationFonc node)
    {
        inADeclarationFonc(node);
        if(node.getBlocInstruction() != null)
        {
            node.getBlocInstruction().apply(this);
        }
        if(node.getVarLocale() != null)
        {
            node.getVarLocale().apply(this);
        }
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getArgument() != null)
        {
            node.getArgument().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getTypeOptionnel() != null)
        {
            node.getTypeOptionnel().apply(this);
        }
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
    public void caseABoolType(ABoolType node)
    {
        inABoolType(node);
        if(node.getBool() != null)
        {
            node.getBool().apply(this);
        }
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
    public void caseAEntierType(AEntierType node)
    {
        inAEntierType(node);
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
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
    public void caseATypeTypeOptionnel(ATypeTypeOptionnel node)
    {
        inATypeTypeOptionnel(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
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
    public void caseAVideTypeOptionnel(AVideTypeOptionnel node)
    {
        inAVideTypeOptionnel(node);
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
    public void caseABlocInstruction(ABlocInstruction node)
    {
        inABlocInstruction(node);
        if(node.getAccoladeDroite() != null)
        {
            node.getAccoladeDroite().apply(this);
        }
        if(node.getListeInstruction() != null)
        {
            node.getListeInstruction().apply(this);
        }
        if(node.getAccoladeGauche() != null)
        {
            node.getAccoladeGauche().apply(this);
        }
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
    public void caseAListeInstruction(AListeInstruction node)
    {
        inAListeInstruction(node);
        if(node.getListeInstruction() != null)
        {
            node.getListeInstruction().apply(this);
        }
        if(node.getInstruction() != null)
        {
            node.getInstruction().apply(this);
        }
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
    public void caseAVideListeInstruction(AVideListeInstruction node)
    {
        inAVideListeInstruction(node);
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
    public void caseAAffectationInstruction(AAffectationInstruction node)
    {
        inAAffectationInstruction(node);
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
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
        if(node.getBlocInstruction() != null)
        {
            node.getBlocInstruction().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
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
        if(node.getSinonInstruction() != null)
        {
            node.getSinonInstruction().apply(this);
        }
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getSiInstruction() != null)
        {
            node.getSiInstruction().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
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
    public void caseATantQueInstruction(ATantQueInstruction node)
    {
        inATantQueInstruction(node);
        if(node.getBlocInstruction() != null)
        {
            node.getBlocInstruction().apply(this);
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getTantQue() != null)
        {
            node.getTantQue().apply(this);
        }
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
    public void caseARetourInstruction(ARetourInstruction node)
    {
        inARetourInstruction(node);
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRetour() != null)
        {
            node.getRetour().apply(this);
        }
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
    public void caseAAppelFonctionInstruction(AAppelFonctionInstruction node)
    {
        inAAppelFonctionInstruction(node);
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getListeExpr() != null)
        {
            node.getListeExpr().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
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
    public void caseAEcrireInstruction(AEcrireInstruction node)
    {
        inAEcrireInstruction(node);
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
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
    public void caseAVar(AVar node)
    {
        inAVar(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
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
    public void caseATableauVar(ATableauVar node)
    {
        inATableauVar(node);
        if(node.getCrochetDroit() != null)
        {
            node.getCrochetDroit().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getCrochetGauche() != null)
        {
            node.getCrochetGauche().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        outATableauVar(node);
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
        inAListeExpr(node);
        if(node.getListeExprPrime() != null)
        {
            node.getListeExprPrime().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAListeExpr(node);
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
        if(node.getListeExprPrime() != null)
        {
            node.getListeExprPrime().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
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
        inAOuExpr(node);
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAOuExpr(node);
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
    public void caseAPrioriteSuivanteExpr(APrioriteSuivanteExpr node)
    {
        inAPrioriteSuivanteExpr(node);
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        outAPrioriteSuivanteExpr(node);
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
        inAEtExpr2(node);
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        outAEtExpr2(node);
    }

    public void inAPrioriteSuivanteExpr2(APrioriteSuivanteExpr2 node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr2(APrioriteSuivanteExpr2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr2(APrioriteSuivanteExpr2 node)
    {
        inAPrioriteSuivanteExpr2(node);
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        outAPrioriteSuivanteExpr2(node);
    }

    public void inAEgalExpr3(AEgalExpr3 node)
    {
        defaultIn(node);
    }

    public void outAEgalExpr3(AEgalExpr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node)
    {
        inAEgalExpr3(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        outAEgalExpr3(node);
    }

    public void inAInfExpr3(AInfExpr3 node)
    {
        defaultIn(node);
    }

    public void outAInfExpr3(AInfExpr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node)
    {
        inAInfExpr3(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        outAInfExpr3(node);
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
        inAPrioriteSuivanteExpr3(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAPrioriteSuivanteExpr3(node);
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
    public void caseAPlusExpr4(APlusExpr4 node)
    {
        inAPlusExpr4(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAPlusExpr4(node);
    }

    public void inAMoinsExpr4(AMoinsExpr4 node)
    {
        defaultIn(node);
    }

    public void outAMoinsExpr4(AMoinsExpr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node)
    {
        inAMoinsExpr4(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        if(node.getMoins() != null)
        {
            node.getMoins().apply(this);
        }
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAMoinsExpr4(node);
    }

    public void inAPrioriteSuivanteExpr4(APrioriteSuivanteExpr4 node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr4(APrioriteSuivanteExpr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr4(APrioriteSuivanteExpr4 node)
    {
        inAPrioriteSuivanteExpr4(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outAPrioriteSuivanteExpr4(node);
    }

    public void inAMultExpr5(AMultExpr5 node)
    {
        defaultIn(node);
    }

    public void outAMultExpr5(AMultExpr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultExpr5(AMultExpr5 node)
    {
        inAMultExpr5(node);
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outAMultExpr5(node);
    }

    public void inADivExpr5(ADivExpr5 node)
    {
        defaultIn(node);
    }

    public void outADivExpr5(ADivExpr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExpr5(ADivExpr5 node)
    {
        inADivExpr5(node);
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outADivExpr5(node);
    }

    public void inAPrioriteSuivanteExpr5(APrioriteSuivanteExpr5 node)
    {
        defaultIn(node);
    }

    public void outAPrioriteSuivanteExpr5(APrioriteSuivanteExpr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrioriteSuivanteExpr5(APrioriteSuivanteExpr5 node)
    {
        inAPrioriteSuivanteExpr5(node);
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        outAPrioriteSuivanteExpr5(node);
    }

    public void inANonExpr6(ANonExpr6 node)
    {
        defaultIn(node);
    }

    public void outANonExpr6(ANonExpr6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node)
    {
        inANonExpr6(node);
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        if(node.getExclamation() != null)
        {
            node.getExclamation().apply(this);
        }
        outANonExpr6(node);
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
        inAPrioriteSuivanteExpr6(node);
        if(node.getExpr7() != null)
        {
            node.getExpr7().apply(this);
        }
        outAPrioriteSuivanteExpr6(node);
    }

    public void inAEntreParanthesesExpr7(AEntreParanthesesExpr7 node)
    {
        defaultIn(node);
    }

    public void outAEntreParanthesesExpr7(AEntreParanthesesExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntreParanthesesExpr7(AEntreParanthesesExpr7 node)
    {
        inAEntreParanthesesExpr7(node);
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        outAEntreParanthesesExpr7(node);
    }

    public void inANombreExpr7(ANombreExpr7 node)
    {
        defaultIn(node);
    }

    public void outANombreExpr7(ANombreExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node)
    {
        inANombreExpr7(node);
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
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
        outAVraiExpr7(node);
    }

    public void inAFauxExpr7(AFauxExpr7 node)
    {
        defaultIn(node);
    }

    public void outAFauxExpr7(AFauxExpr7 node)
    {
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
        inAAppelFonctionExpr7(node);
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getListeExpr() != null)
        {
            node.getListeExpr().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        outAAppelFonctionExpr7(node);
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
        inALireExpr7(node);
        if(node.getParentheseDroite() != null)
        {
            node.getParentheseDroite().apply(this);
        }
        if(node.getParentheseGauche() != null)
        {
            node.getParentheseGauche().apply(this);
        }
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        outALireExpr7(node);
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
        inAVariableExpr7(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVariableExpr7(node);
    }
}
