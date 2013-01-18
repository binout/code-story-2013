package net.binout.codestory2013.scalaskel;

public class ScalaskelResult {

    private Integer foo;
    private Integer bar;
    private Integer qix;
    private Integer baz;

    public Integer getFoo() {
        return foo;
    }

    public void setFoo(Integer foo) {
        this.foo = foo;
    }

    public Integer getBar() {
        return bar;
    }

    public void addBar(Integer bar) {
        if (this.bar == null) {
            this.bar = new Integer(bar);
        } else {
            this.bar += bar;
        }
    }

    public void setBar(Integer bar) {
        this.bar = bar;
    }

    public Integer getQix() {
        return qix;
    }

    public void addQix(Integer qix) {
        if (this.qix == null) {
            this.qix = new Integer(qix);
        } else {
            this.qix += qix;
        }
    }

    public void setQix(Integer qix) {
        this.qix = qix;
    }

    public Integer getBaz() {
        return baz;
    }

    public void addBaz(Integer baz) {
        if (this.baz == null) {
            this.baz = new Integer(baz);
        } else {
            this.baz += baz;
        }
    }

    public void setBaz(Integer baz) {
        this.baz = baz;
    }
}
