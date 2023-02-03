package ts;
import sa.*;
import util.Error;
import util.Type;

public class Sa2ts extends SaDepthFirstVisitor <Void> {
    enum Context {
	LOCAL,
	GLOBAL,
	PARAM
    }
    
    private Ts tableGlobale;
    private Ts tableLocaleCourante;
    private Context context;
    
    public Ts getTableGlobale(){return this.tableGlobale;}

    public Sa2ts(SaNode root)
    {
	tableGlobale = new Ts();
	tableLocaleCourante = null;
	context = Context.GLOBAL;
	try{
	    root.accept(this);
		if(tableGlobale.getFct("main") == null)
		throw new ErrorException(Error.TS, "la fonction main n'est pas définie");
	}
	catch(ErrorException e){
	    System.err.print("ERREUR TABLE DES SYMBOLES : ");
	    System.err.println(e.getMessage());
	    System.exit(e.getCode());
	}
	catch(Exception e){}
    }

	@Override
	public Void visit(SaDecVar node) throws Exception {
		switch (context) {
			case LOCAL:
				if(tableLocaleCourante.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addVar(node.getNom(), node.getType()));
				break;
			case PARAM:
				if(tableLocaleCourante.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addParam(node.getNom(), node.getType()));
				break;
			case GLOBAL:
				if(tableGlobale.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableGlobale.addVar(node.getNom(), node.getType()));
				break;

		}
		return null;
	}

	@Override
	public Void visit(SaDecTab node) throws Exception {
		switch (context) {
			case LOCAL:
				if(tableLocaleCourante.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addTab(node.getNom(), node.getType(), node.getTaille()));
				break;
			case PARAM:
				if(tableLocaleCourante.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addTab(node.getNom(), node.getType(), node.getTaille()));
				break;
			case GLOBAL:
				if(tableGlobale.containVar(node.getNom()))
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				node.setTsItem(tableGlobale.addTab(node.getNom(), node.getType(), node.getTaille()));
				break;

		}
		return null;
	}

	@Override
	public Void visit(SaDecFonc node) throws Exception {
		return null;
	}

	@Override
	public Void visit(SaVarSimple node) throws Exception {
		return null;
	}

	@Override
	public Void visit(SaVarIndicee node) throws Exception {
		return null;
	}

	@Override
	public Void visit(SaAppel node) throws Exception {
		return null;
	}

}
