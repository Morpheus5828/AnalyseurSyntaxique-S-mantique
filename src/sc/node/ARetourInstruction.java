/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ARetourInstruction extends PInstruction
{
    private TRetour _retour_;
    private PExpr _expr_;
    private TPointVirgule _pointVirgule_;

    public ARetourInstruction()
    {
        // Constructor
    }

    public ARetourInstruction(
        @SuppressWarnings("hiding") TRetour _retour_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TPointVirgule _pointVirgule_)
    {
        // Constructor
        setRetour(_retour_);

        setExpr(_expr_);

        setPointVirgule(_pointVirgule_);

    }

    @Override
    public Object clone()
    {
        return new ARetourInstruction(
            cloneNode(this._retour_),
            cloneNode(this._expr_),
            cloneNode(this._pointVirgule_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARetourInstruction(this);
    }

    public TRetour getRetour()
    {
        return this._retour_;
    }

    public void setRetour(TRetour node)
    {
        if(this._retour_ != null)
        {
            this._retour_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._retour_ = node;
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
            + toString(this._retour_)
            + toString(this._expr_)
            + toString(this._pointVirgule_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._retour_ == child)
        {
            this._retour_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
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
        if(this._retour_ == oldChild)
        {
            setRetour((TRetour) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
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