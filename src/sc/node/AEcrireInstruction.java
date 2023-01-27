/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AEcrireInstruction extends PInstruction
{
    private TEcrire _ecrire_;
    private TParentheseGauche _parentheseGauche_;
    private PExpr _expr_;
    private TParentheseDroite _parentheseDroite_;
    private TPointVirgule _pointVirgule_;

    public AEcrireInstruction()
    {
        // Constructor
    }

    public AEcrireInstruction(
        @SuppressWarnings("hiding") TEcrire _ecrire_,
        @SuppressWarnings("hiding") TParentheseGauche _parentheseGauche_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TParentheseDroite _parentheseDroite_,
        @SuppressWarnings("hiding") TPointVirgule _pointVirgule_)
    {
        // Constructor
        setEcrire(_ecrire_);

        setParentheseGauche(_parentheseGauche_);

        setExpr(_expr_);

        setParentheseDroite(_parentheseDroite_);

        setPointVirgule(_pointVirgule_);

    }

    @Override
    public Object clone()
    {
        return new AEcrireInstruction(
            cloneNode(this._ecrire_),
            cloneNode(this._parentheseGauche_),
            cloneNode(this._expr_),
            cloneNode(this._parentheseDroite_),
            cloneNode(this._pointVirgule_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEcrireInstruction(this);
    }

    public TEcrire getEcrire()
    {
        return this._ecrire_;
    }

    public void setEcrire(TEcrire node)
    {
        if(this._ecrire_ != null)
        {
            this._ecrire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ecrire_ = node;
    }

    public TParentheseGauche getParentheseGauche()
    {
        return this._parentheseGauche_;
    }

    public void setParentheseGauche(TParentheseGauche node)
    {
        if(this._parentheseGauche_ != null)
        {
            this._parentheseGauche_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parentheseGauche_ = node;
    }

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    public TParentheseDroite getParentheseDroite()
    {
        return this._parentheseDroite_;
    }

    public void setParentheseDroite(TParentheseDroite node)
    {
        if(this._parentheseDroite_ != null)
        {
            this._parentheseDroite_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parentheseDroite_ = node;
    }

    public TPointVirgule getPointVirgule()
    {
        return this._pointVirgule_;
    }

    public void setPointVirgule(TPointVirgule node)
    {
        if(this._pointVirgule_ != null)
        {
            this._pointVirgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pointVirgule_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ecrire_)
            + toString(this._parentheseGauche_)
            + toString(this._expr_)
            + toString(this._parentheseDroite_)
            + toString(this._pointVirgule_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ecrire_ == child)
        {
            this._ecrire_ = null;
            return;
        }

        if(this._parentheseGauche_ == child)
        {
            this._parentheseGauche_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._parentheseDroite_ == child)
        {
            this._parentheseDroite_ = null;
            return;
        }

        if(this._pointVirgule_ == child)
        {
            this._pointVirgule_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ecrire_ == oldChild)
        {
            setEcrire((TEcrire) newChild);
            return;
        }

        if(this._parentheseGauche_ == oldChild)
        {
            setParentheseGauche((TParentheseGauche) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._parentheseDroite_ == oldChild)
        {
            setParentheseDroite((TParentheseDroite) newChild);
            return;
        }

        if(this._pointVirgule_ == oldChild)
        {
            setPointVirgule((TPointVirgule) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
