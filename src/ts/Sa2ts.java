package ts;
import sa.*;
import util.Error;

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
		throw new ErrorException(Error.TS, "La fonction main n'est pas définie.");
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
				if(tableLocaleCourante.getVar(node.getNom()) != null)
					throw new ErrorException(Error.TS, "La variable '" + node.getNom() + "' a déjà été déclarée.");
				node.setTsItem(tableLocaleCourante.addVar(node.getNom(), node.getType()));
				break;
			case PARAM:
				if(tableLocaleCourante.getVar(node.getNom()) != null)
					throw new ErrorException(Error.TS, "La variable '" + node.getNom() + "' a déjà été déclarée.");
				node.setTsItem(tableLocaleCourante.addParam(node.getNom(), node.getType()));
				break;
			case GLOBAL:
				if(tableGlobale.getVar(node.getNom()) != null)
					throw new ErrorException(Error.TS, "La variable '" + node.getNom() + "' a déjà été déclarée.");
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
				throw new ErrorException(Error.TS, "Impossible de déclarer le tableau '" + node.getNom() + "' en tant que variable locale. Un tableau doit être toujours déclaré en tant que variable globale.");
			case PARAM:
				throw new ErrorException(Error.TS, "Impossible de déclarer le tableau '" + node.getNom() + "' en tant que paramètre. Un tableau doit être toujours déclaré en tant que variable globale.");
			case GLOBAL:
				if(tableGlobale.getVar(node.getNom()) != null)
					throw new ErrorException(Error.TS, "Le tableau '" + node.getNom() + "' a déjà été déclaré.");
				TsItemVar item = tableGlobale.addTab(node.getNom(), node.getType(), node.getTaille());
				item.isParam = false;
				node.setTsItem(item);
				break;
		}
		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaDecFonc node) throws Exception {
		defaultIn(node);
		if(tableGlobale.getFct(node.getNom()) != null)
			throw new ErrorException(Error.TS, "La fonction '" + node.getNom() + "' a déjà été déclaré.");
		tableLocaleCourante = new Ts();

		int argCount = 0;
		if(node.getParametres() != null) {
			argCount = node.getParametres().length();
			context = Context.PARAM;
			node.getParametres().accept(this);
		}
		node.tsItem = tableGlobale.addFct(node.getNom(), node.getTypeRetour(), argCount, tableLocaleCourante, node);

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
		if(tableLocaleCourante.getVar(node.getNom()) == null) {
			if (tableGlobale.getVar(node.getNom()) == null)
				throw new ErrorException(Error.TS, "La variable '" + node.getNom() + "' n'a pas été déclarée.");
			try {
				node.tsItem = (TsItemVarSimple) tableGlobale.getVar(node.getNom());
			} catch (ClassCastException e) {
				throw new ErrorException(Error.TS, "Le tableau '" + node.getNom() + "' n'est pas indicé.");
			}

		} else
			node.tsItem = (TsItemVarSimple) tableLocaleCourante.getVar(node.getNom());

		defaultOut(node);
		return null;
	}

	@Override
	public Void visit(SaVarIndicee node) throws Exception {
		defaultIn(node);
		if(tableGlobale.getVar(node.getNom()) == null)
			throw new ErrorException(Error.TS, "Le tableau: '" + node.getNom() + "' n'a pas été déclaré.");
		try {
			node.tsItem = (TsItemVarTab) tableGlobale.getVar(node.getNom());
		} catch (ClassCastException e) {
			new ErrorException(Error.TS, "La variable simple '" + node.getNom() + "' ne peut pas être indicée.");
		}
		node.getIndice().accept(this);
		defaultOut(node);
		return null;
	}



	@Override
	public Void visit(SaAppel node) throws Exception {
		defaultIn(node);
		if(tableGlobale.getFct(node.getNom()) == null)
			throw new ErrorException(Error.TS, "La fonction '" + node.getNom() + "' n'a pas été déclarée.");
		node.tsItem = tableGlobale.getFct(node.getNom());

		int argCount = 0;
		if(node.getArguments() != null) {
			argCount = node.getArguments().length();
			context = Context.PARAM;
			node.getArguments().accept(this);
		}
		if (argCount != node.tsItem.getNbArgs())
			throw new ErrorException(Error.TS, argCount + " arguments ont été fournis au lieu de " + node.tsItem.getNbArgs() + " lors de l'appel de la fonction '" + node.getNom() + "'.");

		context = Context.LOCAL;
		defaultOut(node);
		return null;
	}

}
