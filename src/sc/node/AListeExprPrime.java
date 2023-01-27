/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AListeExprPrime extends PListeExprPrime
{
    private TVirgule _virgule_;
    private PExpr _expr_;
    private PListeExprPrime _listeExprPrime_;

    public AListeExprPrime()
    {
        // Constructor
    }

    public AListeExprPrime(
        @SuppressWarnings("hiding") TVirgule _virgule_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") PListeExprPrime _listeExprPrime_)
    {
        // Constructor
        setVirgule(_virgule_);

        setExpr(_expr_);

        setListeExprPrime(_listeExprPrime_);

    }

    @Override
    public Object clone()
    {
        return new AListeExprPrime(
            cloneNode(this._virgule_),
            cloneNode(this._expr_),
            cloneNode(this._listeExprPrime_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListeExprPrime(this);
    }

    public TVirgule getVirgule()
    {
        return this._virgule_;
    }

    public void setVirgule(TVirgule node)
    {
        if(this._virgule_ != null)
        {
            this._virgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._virgule_ = node;
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

    public PListeExprPrime getListeExprPrime()
    {
        return this._listeExprPrime_;
    }

    public void setListeExprPrime(PListeExprPrime node)
    {
        if(this._listeExprPrime_ != null)
        {
            this._listeExprPrime_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listeExprPrime_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._virgule_)
            + toString(this._expr_)
            + toString(this._listeExprPrime_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._virgule_ == child)
        {
            this._virgule_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._listeExprPrime_ == child)
        {
            this._listeExprPrime_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._virgule_ == oldChild)
        {
            setVirgule((TVirgule) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._listeExprPrime_ == oldChild)
        {
            setListeExprPrime((PListeExprPrime) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}