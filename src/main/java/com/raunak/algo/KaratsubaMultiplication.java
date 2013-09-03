package com.raunak.algo;

public class KaratsubaMultiplication {

    public static void main(String[] args) {

        int x = 100;
        int y = 100;
        
        System.out.println(computeMultiplier(x,y));
    }

    private static int computeMultiplier(int x, int y) {
        
        int n = 0;
        if(x > y){
            n = getNoOfDigit(y);
        }else{
            n = getNoOfDigit(x);
        }
        
        if(n == 1){
            return x * y;
        }
        //to cover for uneven numbers.
        if(n % 2 == 1){
            n = n +1;
        }
        int m = n/2;
        int a = (int) (x / Math.pow(10, (m)));
        int b = (int) (x % Math.pow(10, (m)));
        int c = (int) (y / Math.pow(10, (m)));
        int d = (int) (y % Math.pow(10, (m)));
        
        int ac = computeMultiplier(a, c);
        int bd = computeMultiplier(b, d);
        
        int sumProduct = computeMultiplier((a+b),(c+d));
        
        int product = ((int)Math.pow(10, n) * ac) + bd + (int)Math.pow(10, m) * (sumProduct - ac -bd) ;
        return product;
    }

    public static int getNoOfDigit(int x) {
        
        int digitPlace = 1;
        while(true){
            
            int quotient = (int) (x/(Math.pow(10, digitPlace)));
            if(quotient == 0){
                return digitPlace;
            }
            else{
                digitPlace++;
            }
        }
    }
}
