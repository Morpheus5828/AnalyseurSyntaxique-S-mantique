/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AVideListeFonc extends PListeFonc
{

    public AVideListeFonc()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AVideListeFonc();
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVideListeFonc(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
