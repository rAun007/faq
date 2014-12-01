package com.raunak.cnf;


public class AndFormula extends Formula {

    private Formula p;

    private Formula q;

    public AndFormula(Formula p, Formula q) {

        this.p = p;
        this.q = q;
    }

    /**
     * @return the p
     */
    public Formula getP() {

        return p;
    }

    /**
     * @param p
     *            the p to set
     */
    public void setP(Formula p) {

        this.p = p;
    }

    /**
     * @return the q
     */
    public Formula getQ() {

        return q;
    }

    /**
     * @param q
     *            the q to set
     */
    public void setQ(Formula q) {

        this.q = q;
    }

    @Override
    public String toString() {
    
        return "(" + p + "^" + q + ")";
    }
}
