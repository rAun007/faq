package com.raunak.cnf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Psuedocode is here: http://cs.jhu.edu/~jason/tutorials/convert-to-CNF
 * 
 * @author raunak
 * 
 */
public class Test {

    public static void main(String[] args) {

        // AndFormula andFormula1 = new AndFormula(new Variable(1), new Variable(2));
        // AndFormula andFormula2 = new AndFormula(new Variable(3), new Variable(4));
        // AndFormula andFormula3 = new AndFormula(new Variable(5), new Variable(6));
        // AndFormula andFormula4 = new AndFormula(new Variable(7), new Variable(8));
        // AndFormula andFormula5 = new AndFormula(new Variable(9), new Variable(10));
        // AndFormula andFormula6 = new AndFormula(new Variable(11), new Variable(12));
        // AndFormula andFormula7 = new AndFormula(new Variable(13), new Variable(14));
        // AndFormula andFormula8 = new AndFormula(new Variable(15), new Variable(16));
        // AndFormula andFormula9 = new AndFormula(new Variable(17), new Variable(18));
        // AndFormula andFormula10 = new AndFormula(new Variable(19), new Variable(20));
        //
        // List<AndFormula> andFormulas = Arrays.asList(andFormula1, andFormula2, andFormula3, andFormula4, andFormula5, andFormula6,
        // andFormula7, andFormula8, andFormula9, andFormula10);
        //
        // OrFormula orFormula = new OrFormula(andFormulas.get(0), andFormulas.get(1));
        //
        // for (int i = 2; i < andFormulas.size(); i++) {
        // orFormula = new OrFormula(orFormula, andFormulas.get(i));
        // }
        //
        // Formula cnfFormula = convertToCNF(orFormula);
        // System.out.println(cnfFormula);
        //
        // System.out.println(print(cnfFormula));

        List<List<Integer>> dnf = new ArrayList<>();
        dnf.add(Arrays.asList(1, 2));
        dnf.add(Arrays.asList(3, 4));
        dnf.add(Arrays.asList(5, 6));
        // dnf.add(Arrays.asList(7, 8));
        // dnf.add(Arrays.asList(9, 10));

        Formula dnfFormula = convertDNFToFormula(dnf);

        Formula cnfFormula = convertToCNF(dnfFormula);
        System.out.println(cnfFormula);
        System.out.println(print(cnfFormula));

        System.out.println(dnfFormula);
    }

    public static List<List<Integer>> print(Formula cnfFormula) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> fList = new ArrayList<>();

        callRecursive(cnfFormula, result, fList);
        if (!fList.isEmpty()) {
            result.add(0, fList);
        }
        return result;

    }

    private static void callRecursive(Formula cnfFormula, List<List<Integer>> arrayList, List<Integer> activeList) {

        if (cnfFormula instanceof Variable) {
            activeList.add(((Variable) cnfFormula).getVariable());
        } else if (cnfFormula instanceof AndFormula) {

            List<Integer> left = new ArrayList<Integer>();
            List<Integer> right = new ArrayList<Integer>();

            callRecursive(((AndFormula) cnfFormula).getP(), arrayList, left);
            callRecursive(((AndFormula) cnfFormula).getQ(), arrayList, right);

            if (!left.isEmpty()) {
                arrayList.add(left);
            }

            if (!right.isEmpty()) {
                arrayList.add(right);
            }
        } else if (cnfFormula instanceof OrFormula) {
            callRecursive(((OrFormula) cnfFormula).getP(), arrayList, activeList);
            callRecursive(((OrFormula) cnfFormula).getQ(), arrayList, activeList);
        }
    }

    private static Formula convertToCNF(Formula formula) {

        if (formula instanceof Variable) {
            return formula;
        }

        if (formula instanceof AndFormula) {

            Formula convertedPToCNF = convertToCNF(((AndFormula) formula).getP());
            Formula convertedQToCNF = convertToCNF(((AndFormula) formula).getQ());

            return new AndFormula(convertedPToCNF, convertedQToCNF);
        }

        if (formula instanceof OrFormula) {

            List<OrFormula> orFormulae = new ArrayList<OrFormula>();
            Formula convertedPToCNF = convertToCNF(((OrFormula) formula).getP());
            Formula convertedQToCNF = convertToCNF(((OrFormula) formula).getQ());

            List<Formula> pVaribles = getLeastFormulaVariable(convertedPToCNF);
            List<Formula> qVariables = getLeastFormulaVariable(convertedQToCNF);

            for (int i = 0; i < pVaribles.size(); i++) {
                for (int j = 0; j < qVariables.size(); j++) {
                    orFormulae.add(new OrFormula(pVaribles.get(i), qVariables.get(j)));
                }
            }

            if (orFormulae.size() < 2) {
                return orFormulae.get(0);
            }
            AndFormula returnVal = new AndFormula(orFormulae.get(0), orFormulae.get(1));

            for (int i = 2; i < orFormulae.size(); i++) {
                returnVal = new AndFormula(returnVal, orFormulae.get(i));
            }

            return returnVal;
        }
        return formula;
    }

    private static List<Formula> getLeastFormulaVariable(Formula formula) {

        Set<Formula> variables = new HashSet<Formula>();

        getAllFormulaVariableRecursive(formula, variables);

        return new ArrayList<>(variables);
    }

    private static void getAllFormulaVariableRecursive(Formula formula, Set<Formula> variables) {

        if (formula instanceof Variable) {
            variables.add(((Variable) formula));
        } else if (formula instanceof AndFormula) {
            getAllFormulaVariableRecursive(((AndFormula) formula).getP(), variables);
            getAllFormulaVariableRecursive(((AndFormula) formula).getQ(), variables);
        } else if (formula instanceof OrFormula) {
            variables.add(formula);
        }
    }

    private static Formula convertDNFToFormula(List<List<Integer>> dnfForm) {

        List<Formula> orFormulas = new ArrayList<>();
        for (List<Integer> innerList : dnfForm) {

            if (innerList.size() == 1) {

                orFormulas.add(new Variable(innerList.get(0)));
            } else if (innerList.size() >= 2) {

                AndFormula andFormula = new AndFormula(new Variable(innerList.get(0)), new Variable(innerList.get(1)));
                for (int i = 2; i < innerList.size(); i++) {
                    andFormula = new AndFormula(andFormula, new Variable(innerList.get(i)));
                }
                orFormulas.add(andFormula);
            }
        }

        if (orFormulas.size() == 1) {
            return orFormulas.get(0);
        }

        if (orFormulas.size() >= 2) {

            OrFormula orFormula = new OrFormula(orFormulas.get(0), orFormulas.get(1));
            for (int i = 2; i < orFormulas.size(); i++) {
                orFormula = new OrFormula(orFormula, orFormulas.get(i));
            }
            return orFormula;
        }
        return null;
    }
}
