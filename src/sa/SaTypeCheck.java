package sa;
import util.Type;
import util.Error;
import ts.*;

public class SaTypeCheck extends SaDepthFirstVisitor <Void>{
    private TsItemFct fonctionCourante;
    private SaLDecVar argumentCourant;

    public SaTypeCheck(SaNode root)
    {
	try{
	root.accept(this);
	}
	catch(ErrorException e){
	    System.err.print("ERREUR DE TYPAGE : ");
	    System.err.println(e.getMessage());
	    System.exit(e.getCode());
	}
	catch(Exception e){}
    }

    public void defaultIn(SaNode node)
    {
	// System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
	// System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    @Override
    public Void visit(SaInstTantQue node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getTest().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Le test d'une boucle 'tant que' doit être de type 'bool', pas de type '" + node.getTest().getType() + "'.");
        node.getTest().accept(this);
        if (node.getFaire() != null)
            node.getFaire().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaDecFonc node) throws Exception
    {
        defaultIn(node);
        fonctionCourante = node.tsItem;
        if(node.getParametres() != null) node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstAffect node) throws Exception
    {
        defaultIn(node);
        TsItemVar item = node.getLhs().getTsItem();
        if (!Type.checkCompatibility(item.getType(), node.getRhs().getType()))
            throw new ErrorException(Error.TYPE, "Impossible d'affecter l'expression de type '" + node.getRhs().getType() + "' à la variable '" + item.getIdentif() + "' de type '" + item.getType() + "'.");
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaAppel node) throws Exception
    {
        defaultIn(node);
        fonctionCourante = node.tsItem;
        argumentCourant = fonctionCourante.saDecFonc.getParametres();
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpAdd node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'addition.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'addition.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpSub node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la soustraction.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la soustraction.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpMult node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la multiplication.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la multiplication.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpDiv node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la division.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de la division.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpInf node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'opération 'inférieur'.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'opération 'inférieur'.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpEqual node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'égalité.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.ENTIER + "' de l'égalité.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpAnd node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.BOOL + "' de l'opérande 'et'.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type '" + Type.BOOL + "' de l'opérande 'et'.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }


    @Override
    public Void visit(SaExpOr node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type " + Type.BOOL + " de l'opérande 'ou'.");
        if (!Type.checkCompatibility(node.getOp2().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp2().getType() + "' incompatible avec le type " + Type.BOOL + " de l'opérande 'ou'.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpNot node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getOp1().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Type '" + node.getOp1().getType() + "' incompatible avec le type '" + Type.BOOL + "' de l'opérande 'non'.");
        node.getOp1().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstSi node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getTest().getType(), Type.BOOL))
            throw new ErrorException(Error.TYPE, "Le test de la conditionnelle 'si' doit être de type 'bool', pas de type '" + node.getTest().getType() + "'.");
        node.getTest().accept(this);
        if (node.getAlors() != null)
            node.getAlors().accept(this);
        if(node.getSinon() != null) node.getSinon().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstRetour node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getType(), fonctionCourante.typeRetour))
            throw new ErrorException(Error.TYPE, "Le type '" + node.getType() + "' retourné par la fonction '" + fonctionCourante.getIdentif() + "' n'est incompatible avec le type  de retour '" + fonctionCourante.typeRetour + "' demandé.");
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaLExp node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getTete().getType(), argumentCourant.getTete().getType()))
            throw new ErrorException(Error.TYPE, "Le type d'argument '" + node.getTete().getType() + "' de l'appel de la fonction '" + fonctionCourante.getIdentif() + "' ne correspond pas avec le type d'argument '" + argumentCourant.getTete().getType() + "' demandé.");
         node.getTete().accept(this);
        if(node.getQueue() != null)
            argumentCourant = argumentCourant.getQueue();
            node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaVarIndicee node) throws Exception
    {
        defaultIn(node);
        if (!Type.checkCompatibility(node.getIndice().getType(), Type.ENTIER))
            throw new ErrorException(Error.TYPE, "L'indice du tableau" + node.getNom() + " doit être de type 'entier', pas de type '" + node.getIndice().getType() + "'.");
        node.getIndice().accept(this);
        defaultOut(node);
        return null;
    }

}
