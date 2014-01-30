package com.raunak.cnf;

public class Variable extends Formula {

    public Variable(int p) {

        this.variable = p;
    }

    private int variable;

    public int getVariable() {

        return variable;
    }

    public void setVariable(int variable) {

        this.variable = variable;
    }

    @Override
    public String toString() {

        return String.valueOf(variable);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + variable;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Variable other = (Variable) obj;
        if (variable != other.variable)
            return false;
        return true;
    }
    
}
