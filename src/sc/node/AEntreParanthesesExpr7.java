/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AEntreParanthesesExpr7 extends PExpr7
{
    private TParentheseGauche _parentheseGauche_;
    private PExpr _expr_;
    private TParentheseDroite _parentheseDroite_;

    public AEntreParanthesesExpr7()
    {
        // Constructor
    }

    public AEntreParanthesesExpr7(
        @SuppressWarnings("hiding") TParentheseGauche _parentheseGauche_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TParentheseDroite _parentheseDroite_)
    {
        // Constructor
        setParentheseGauche(_parentheseGauche_);

        setExpr(_expr_);

        setParentheseDroite(_parentheseDroite_);

    }

    @Override
    public Object clone()
    {
        return new AEntreParanthesesExpr7(
            cloneNode(this._parentheseGauche_),
            cloneNode(this._expr_),
            cloneNode(this._parentheseDroite_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEntreParanthesesExpr7(this);
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._parentheseGauche_)
            + toString(this._expr_)
            + toString(this._parentheseDroite_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
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

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
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

        throw new RuntimeException("Not a child.");
    }
}