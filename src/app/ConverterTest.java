package app;

import parser.converter.MathConverter;

import java.util.Scanner;

/**
 * Created by waps12b on 2016. 11. 9..
 */
public class ConverterTest {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String latex = sc.nextLine().trim();

        String output = MathConverter.convertLaTex2MathML(latex);
        if(output == null)
        {
            output = "[Error!]";
        }
        System.out.println(output);
    }
}
