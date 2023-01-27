/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class TFaux extends Token
{
    public TFaux()
    {
        super.setText("faux");
    }

    public TFaux(int line, int pos)
    {
        super.setText("faux");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFaux(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFaux(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TFaux text.");
    }
}
