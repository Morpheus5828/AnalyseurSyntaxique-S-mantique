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
		defaultIn(node);
		switch (context) {
			case LOCAL:
				if(tableLocaleCourante.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addVar(node.getNom(), node.getType()));
				break;
			case PARAM:
				if(tableLocaleCourante.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableLocaleCourante.addParam(node.getNom(), node.getType()));
				break;
			case GLOBAL:
				if(tableGlobale.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Varibale: " + node.getNom() + " existe déjà");
				node.setTsItem(tableGlobale.addVar(node.getNom(), node.getType()));
				break;

		}
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaDecTab node) throws Exception {
		defaultIn(node);
		switch (context) {
			case LOCAL:
				if(tableLocaleCourante.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				TsItemVar item = tableLocaleCourante.addTab(node.getNom(), node.getType(), node.getTaille());
				item.isParam = false;
				node.setTsItem(item);
				break;
			case PARAM:
				if(tableLocaleCourante.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				TsItemVar item1 = tableLocaleCourante.addTab(node.getNom(), node.getType(), node.getTaille());
				item1.isParam = true;
				node.setTsItem(item1);
				break;
			case GLOBAL:
				if(tableGlobale.getVar(node.getNom()) == null)
					throw new ErrorException(Error.TS, "Tableau: " + node.getNom() + " existe déjà");
				TsItemVar item2 = tableGlobale.addTab(node.getNom(), node.getType(), node.getTaille());
				item2.isParam = false;
				node.setTsItem(item2);
				break;
		}
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaDecFonc node) throws Exception {
		defaultIn(node);
		if(tableGlobale.getFct(node.getNom()) != null)
			throw new ErrorException(Error.TS, "Fonction: " + node.getNom() + " existe déjà");
		tableLocaleCourante = new Ts();
		node.tsItem = tableGlobale.addFct(node.getNom(), node.getTypeRetour(), ,tableLocaleCourante, node);

		if(node.getParametres() != null) {
			context = Context.PARAM;
			node.getParametres().accept(this);
		}

		if(node.getVariable() != null) {
			context = Context.LOCAL;
			node.getVariable().accept(this);
		}

		if(node.getCorps() != null) {
			context = Context.LOCAL;
			node.getCorps().accept(this);
		}
		context = Context.GLOBAL;
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaVarSimple node) throws Exception {
		defaultIn(node);
		if(tableLocaleCourante.getVar(node.getNom()) == null)
			throw new ErrorException(Error.TS, "Variable: " + node.getNom() + " n'existe pas");
		node.tsItem = new TsItemVarSimple(node.getNom(), tableLocaleCourante.getVar(node.getNom()).getType());
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaVarIndicee node) throws Exception {
		defaultIn(node);
		node.getIndice().accept(this);
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaAppel node) throws Exception {
		defaultIn(node);
		if(node.getArguments() != null) node.getArguments().accept(this);
		defaultOut(node);
		return null;
	}

}
