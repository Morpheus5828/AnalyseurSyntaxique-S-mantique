package sa;
import util.Type;
import util.Error;
import ts.*;

public class SaTypeCheck extends SaDepthFirstVisitor <Void>{
    private TsItemFct fonctionCourante;

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
	//			System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
	//		System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    @Override
    public Void visit(SaExpVar node) throws Exception
    {
        defaultIn(node);
        node.getVar().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstEcriture node) throws Exception
    {
        defaultIn(node);
        node.getArg().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstTantQue node) throws Exception
    {
        defaultIn(node);
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
        if(node.getParametres() != null) node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaDecVar node) throws Exception
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstAffect node) throws Exception
    {
        defaultIn(node);
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaLDecVar node) throws Exception
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null) node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaLDecFonc node) throws Exception
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null) node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaVarSimple node) throws Exception
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaAppel node) throws Exception
    {
        defaultIn(node);
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpAppel node) throws Exception
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpAdd node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de l'addition.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de l'addition.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpSub node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de la soustraction.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de la soustraction.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpMult node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de la multiplication.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de la multiplication.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpDiv node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de la division.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de la division.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpInf node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de l'opération inférieur.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de l'opération inférieur.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpEqual node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " de l'égalité.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " de l'égalité.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpAnd node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " du 'et'.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " du 'et'.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }


    @Override
    public Void visit(SaExpOr node) throws Exception
    {
        defaultIn(node);
        if (Type.checkCompatibility(node.getOp1().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp1().getType() + " incompatible avec le type " + node.getType() + " du 'ou'.");
        if (Type.checkCompatibility(node.getOp2().getType(), node.getType()))
            throw new ErrorException(Error.TYPE, "Type " + node.getOp2().getType() + " incompatible avec le type " + node.getType() + " du 'ou'.");
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpNot node) throws Exception
    {
        defaultIn(node);
        node.getOp1().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaExpLire node) throws Exception
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstBloc node) throws Exception
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaInstSi node) throws Exception
    {
        defaultIn(node);
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
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaLExp node) throws Exception
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null)
            node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }
    @Override
    public Void visit(SaVarIndicee node) throws Exception
    {
        defaultIn(node);
        node.getIndice().accept(this);
        defaultOut(node);
        return null;
    }

}
