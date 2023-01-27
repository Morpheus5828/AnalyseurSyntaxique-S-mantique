/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ATantQueInstruction extends PInstruction
{
    private TTantQue _tantQue_;
    private PExpr _expr_;
    private TFaire _faire_;
    private PBlocInstruction _blocInstruction_;

    public ATantQueInstruction()
    {
        // Constructor
    }

    public ATantQueInstruction(
        @SuppressWarnings("hiding") TTantQue _tantQue_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TFaire _faire_,
        @SuppressWarnings("hiding") PBlocInstruction _blocInstruction_)
    {
        // Constructor
        setTantQue(_tantQue_);

        setExpr(_expr_);

        setFaire(_faire_);

        setBlocInstruction(_blocInstruction_);

    }

    @Override
    public Object clone()
    {
        return new ATantQueInstruction(
            cloneNode(this._tantQue_),
            cloneNode(this._expr_),
            cloneNode(this._faire_),
            cloneNode(this._blocInstruction_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATantQueInstruction(this);
    }

    public TTantQue getTantQue()
    {
        return this._tantQue_;
    }

    public void setTantQue(TTantQue node)
    {
        if(this._tantQue_ != null)
        {
            this._tantQue_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tantQue_ = node;
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

    public TFaire getFaire()
    {
        return this._faire_;
    }

    public void setFaire(TFaire node)
    {
        if(this._faire_ != null)
        {
            this._faire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._faire_ = node;
    }

    public PBlocInstruction getBlocInstruction()
    {
        return this._blocInstruction_;
    }

    public void setBlocInstruction(PBlocInstruction node)
    {
        if(this._blocInstruction_ != null)
        {
            this._blocInstruction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._blocInstruction_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._tantQue_)
            + toString(this._expr_)
            + toString(this._faire_)
            + toString(this._blocInstruction_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._tantQue_ == child)
        {
            this._tantQue_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._faire_ == child)
        {
            this._faire_ = null;
            return;
        }

        if(this._blocInstruction_ == child)
        {
            this._blocInstruction_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._tantQue_ == oldChild)
        {
            setTantQue((TTantQue) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._faire_ == oldChild)
        {
            setFaire((TFaire) newChild);
            return;
        }

        if(this._blocInstruction_ == oldChild)
        {
            setBlocInstruction((PBlocInstruction) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}