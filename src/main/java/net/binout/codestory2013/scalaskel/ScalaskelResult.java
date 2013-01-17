package net.binout.codestory2013.scalaskel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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

    public void setBar(Integer bar) {
        this.bar = bar;
    }

    public Integer getQix() {
        return qix;
    }

    public void setQix(Integer qix) {
        this.qix = qix;
    }

    public Integer getBaz() {
        return baz;
    }

    public void setBaz(Integer baz) {
        this.baz = baz;
    }
}
