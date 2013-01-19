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

    @Override
    public int hashCode() {
        int fooHashCode = this.foo == null ? 0 : this.foo.hashCode();
        int barHashCode = this.bar == null ? 0 : this.bar.hashCode();
        int qixHashCode = this.qix == null ? 0 : this.qix.hashCode();
        int bazHashCode = this.baz == null ? 0 : this.baz.hashCode();
        return ("" + fooHashCode + barHashCode + qixHashCode + bazHashCode).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScalaskelResult) {
            ScalaskelResult r = (ScalaskelResult) obj;
            return isEq(r.getFoo(), this.foo)
                    && isEq(r.getBar(), this.bar)
                    && isEq(r.getQix(), this.qix)
                    && isEq(r.getBaz(), this.baz);
        }
        return false;
    }

    private boolean isEq(Integer i1, Integer i2) {
        if (i1 == null && i2 == null) {
            return true;
        } else if (i1 == null && i2 != null) {
            return false;
        } else if (i1 != null && i2 == null) {
            return  false;
        } else {
            return i1.equals(i2);
        }
    }
}
