/**
 * This class tests the other class Polynomial.
 * @author Onurcan Ataç / 222002194
 */
public class PolynomialTester {

    public static void main(String[] args) {
 
        //Testing for constructors and getDegree() method.

        Polynomial poly1 = new Polynomial( 3, 7);
        System.out.println( poly1.getDegree() );
       
        Polynomial poly2 = new Polynomial();
        System.out.println( poly2.getDegree() );

        double[] newArray = { 2, 5, -3, 7, 0, -1};
        Polynomial poly3 = new Polynomial( newArray );
        System.out.println( poly3.getDegree() );
              
        //Testing for getCoefficients method.

        System.out.println( poly1.getCoefficient(3) );
        
        System.out.println( poly2.getCoefficient(0) );        
        
        System.out.println( poly3.getCoefficient(2) );

        //Testing for toString method.

        String string1 = poly1.toString();
        System.out.println( string1 );

        String string2 = poly2.toString();
        System.out.println( string2 );

        String string3 = poly3.toString();
        System.out.println( string3);

        //Testing for eval method.

        System.out.println( poly1.eval(3) );

        System.out.println( poly2.eval(3) );

        System.out.println( poly3.eval(3) );

        //Testing for eval2 method.

        System.out.println( poly1.eval2(3) );

        System.out.println( poly2.eval2(3) );

        System.out.println( poly3.eval2(3) );

        //LAB02 PART
        
        System.out.println();
        System.out.println("Start of Lab02 testing...");
        System.out.println();
        
        //Testing for add method.
        
        //Test1
        double[] array1 = { 3, 4, 5, 2};
        Polynomial p1 = new Polynomial( array1);

        double[] array2 = { 2, 4, 1};
        Polynomial p2 = new Polynomial( array2);

        Polynomial p3 = new Polynomial();
        p3 = p1.add( p2);

        System.out.println("Tests for add method:");

        System.out.println(p3.toString());

        //Test2
        Polynomial p4 = new Polynomial();
        
        p4 = p2.add( p1);
        System.out.println( p4.toString());

        System.out.println();

        //Testing for sub method.

        System.out.println("Tests for sub method:");
        //Test1
        p3 = p1.sub(p2);
        System.out.println(p3.toString());

        //Test2
        p4 = p2.sub(p1);
        System.out.println(p4.toString());

        System.out.println();

        //Testing for mul method.
        
        System.out.println("Tests for mul method:");
        //Test1
        p3 = p1.mul( p2);
        System.out.println( p3.toString());

        //Test 2
        p4 = p2.mul( p1);
        System.out.println( p4.toString());

        System.out.println();

        //Testing for compose method.

        double[] array5 = { 3, 4, 1};
        Polynomial p5 = new Polynomial( array5);

        double[] array6 = { 2, 1};
        Polynomial p6 = new Polynomial( array6);

        System.out.println("Tests for compose method:");
        
        Polynomial p7 = new Polynomial();
        p7 = p5.compose( p6);
        System.out.println( p7.toString());

        System.out.println();
        
        //Testing for div method.

        double[] array8 = { 3, 4, 1, 3, 0, 2};
        Polynomial p8 = new Polynomial( array8);

        double[] array9 = { 2, 1};
        Polynomial p9 = new Polynomial( array9);

        Polynomial p10 = new Polynomial();

        System.out.println("Test for div method:");

        p10 = p8.div( p9);
        System.out.println( p10.toString());

    }
    
}
