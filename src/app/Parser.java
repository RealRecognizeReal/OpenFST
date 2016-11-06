package app;

import formula.Formula;
import parser.LaTexFormulaParser;
import symbol.base.Symbol;

import java.util.Scanner;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Parser {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String latex;
        System.out.print("Input latex string >> ");
        latex = sc.nextLine().trim();

        LaTexFormulaParser parser = new LaTexFormulaParser(latex);



    }
}
