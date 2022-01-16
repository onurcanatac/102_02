/**
 * This class creates polynomials and perform methods with them.
 * @author Onurcan Ataç / 22002194
 */
public class Polynomial {
    
    //Properties

    private int d; //exponent
    private double c; //coefficient
    private double[] coefficientsArray;

    //Constructors

    public Polynomial( int d, double c ) {

        this.d = d;
        this.c = c;
        coefficientsArray = new double[d+1];//Since indexes start from 0, I had to make the size d+1.
        coefficientsArray[d] = c;
    }

    public Polynomial() {

        this.d = 0;
        this.c = 0;
        coefficientsArray = new double[1]; //Has 1 element and it's 0 by default. So, 0 at index 0.

    }

    public Polynomial( double[] coefficients) {
        
        this.coefficientsArray = coefficients;
    
    }

    //Methods

    /**
     * This method gets and returns the coefficient of specified degree.
     *  @param degree
     *  @return coefficientsArray[degree]
     */
    public double getCoefficient( int degree ) {

        if ( coefficientsArray.length > degree )
        {
            return coefficientsArray[degree];
        }
        else
        {
            return 0;//If it isn't in the polynomial its coefficient is 0.
        }
    }

    /**
     * This method returns the degree of the polynomial. 
     * @return degree 
     */
    public int getDegree() {

        //Since I used indexes for exponents, I tried to find the largest index without 0 component.
        for( int i = coefficientsArray.length - 1; i >= 0 ; i--)
        {
            if( coefficientsArray[i] != 0 )
            {
                double degree = i;
                return (int) degree;
            }
        }
        return 0;//Degree of zero polynomial is 0.
    }

    /**
     * This method returns the String version of the polynomial.
     * @return translatedToString
     */
    public String toString() {

        String translatedToString = "";
        /*printedBefore is used to recognize if it's the first term and positive,
        if so print it without "+" like the example in lab paper.*/
        boolean printedBefore = false; 
        for( int i = 0; i < coefficientsArray.length; i++)
        {
            if(coefficientsArray[i] > 0)
            {
                if (i == 0)
                {
                    String partOfString = coefficientsArray[i] + " ";
                    translatedToString = translatedToString + partOfString; 
                }
                else if( printedBefore == false )
                {
                    String partOfString = coefficientsArray[i] + "x^" + i + " ";
                    translatedToString = translatedToString + partOfString;               
                }
                else
                {
                    String partOfString = "+ " + coefficientsArray[i] + "x^" + i + " ";
                    translatedToString = translatedToString + partOfString; 
                }
                printedBefore = true;
            }
            else if(coefficientsArray[i] == 0)
            {
                boolean containsOnlyZeros = true;
                for( double a : coefficientsArray)
                {
                    if( a != 0 )
                    {
                        containsOnlyZeros = false;
                    }
                }
                if(containsOnlyZeros == true)
                {
                    String partOfString = "0.0";
                    translatedToString = translatedToString + partOfString;
                    /*Because a polynomial contains only zeros, it is sufficient just to write 0.0 for the String version.
                      I used next line of code to get out of the loop and stop errors.*/
                    i = coefficientsArray.length;
                }
                else
                {
                    String partOfString = "";
                    translatedToString = translatedToString + partOfString; 
                }
            }
            else
            {
                if(i == 0)
                {
                    String partOfString = "- " + Math.abs(coefficientsArray[i]) + " ";
                    translatedToString = translatedToString + partOfString; 
                }
                else
                {
                    String partOfString = "- " + Math.abs(coefficientsArray[i]) + "x^" + i + " ";
                    translatedToString = translatedToString + partOfString; 
                }
                printedBefore = true;
            }
        }
        return translatedToString;
    }

    /**
     * This method evaluates the result of the polynomial for value x and returns it.
     * @param x
     * @return totalResult
     */
    public double eval( double x ) {
        /*Use Math.pow( double a, double b ) method to evaluate 
        each term individually and the polynomial as a sum of the
        terms.*/
        double totalResult = 0;
        for( int i = 0; i < coefficientsArray.length; i++)
        {
            int exponent = i;
            double coefficient = coefficientsArray[i];
            double result = coefficient * Math.pow( x, ( double )exponent );
            totalResult += result;
        }
        return totalResult;
    }

    /**
     * This method evaluates the result of the polynomial by using Horner's Method for value x and returns it.
     * @param x
     * @return result
     */
    public double eval2( double x ) {
        
        //Evaluate the polynomial by using Horner's Method.
        double result = coefficientsArray[ coefficientsArray.length - 1 ];
        for( int i = coefficientsArray.length - 1 ; i >= 0 ; i--)
        {
            if(i >= 1)
            {
                result = result * x + coefficientsArray[i - 1];
            }
        }
        return result;
    }

    //Lab02 Part

    /**
     * This method returns a polynomial which is sum of 
     * two other polynomials.
     * @param p2
     * @return sumPoly
     */
    public Polynomial add( Polynomial p2 ) {

        Polynomial sumPoly;
        double[] sumCoefficients; 
        double coefficientsSum;
        int lengthOfTheLargerArray;

        //Check the lengths of arrays of polynomials, select the one with
        //the bigger array to be the length of sum polynomials array.
        if (this.getDegree() >= p2.getDegree())
        {
            lengthOfTheLargerArray = coefficientsArray.length;
        }
        else
        {
            lengthOfTheLargerArray = p2.getDegree() + 1;
        }
        
        sumCoefficients = new double [lengthOfTheLargerArray];
        
        for( int index = 0; index < lengthOfTheLargerArray; index++ )
        {    
            //Find the sum of coefficients on same indexes.
            //Not a problem if index doesn't exist in any polynomial's arrays,
            //because getCoefficient(index) returns 0 in that case.
            coefficientsSum = this.getCoefficient( index ) + p2.getCoefficient( index );
  
            //Put the sum of each exponent into correct indexes of sum array.
            sumCoefficients[index] = coefficientsSum;
        }
        
        sumPoly = new Polynomial(sumCoefficients);

        return sumPoly;
    }

    /**
     * This method returns p1-p2 (subtracting).
     * (p1 is the polynomial the method is called for)
     * @param p2
     * @return subPoly
     */
    public Polynomial sub( Polynomial p2 ) {

        Polynomial justMinusOne;
        Polynomial subPoly;
        double[] minusOneArray = {-1};

        justMinusOne = new Polynomial( minusOneArray);

        subPoly = this.add(justMinusOne.mul(p2));

        return subPoly;
    
    }

    /**
     * This method multiplies two polynomials with
     * each other and returns the new polynomial.
     * @param p2
     * @return mulPoly
     */
    public Polynomial mul( Polynomial p2 ) {
        
        Polynomial mulPoly;
        double[] mulCoefficients; 
        int lengthOfTheArray;
        double result;

        //Find length of the array.
        lengthOfTheArray = p2.getDegree() + this.getDegree() + 1;

        mulCoefficients = new double[lengthOfTheArray];

        //For each coefficient of method called polynomial, multiply the coefficient with every other 
        //coefficient of p2 and put the results to right indexes of new array mulCoefficients.
        for( int coefficientsOfThisPoly = 0; coefficientsOfThisPoly < coefficientsArray.length; coefficientsOfThisPoly++ )
        {
            for( int coefficientsOfp2 = 0; coefficientsOfp2 <= p2.getDegree(); coefficientsOfp2++ )
            {
                result = coefficientsArray[coefficientsOfThisPoly] * p2.getCoefficient( coefficientsOfp2);
                //x^2 * x^3 = x^5 so 2 + 3 = 5
                mulCoefficients[coefficientsOfThisPoly + coefficientsOfp2] += result;
            }
        }
        mulPoly = new Polynomial( mulCoefficients );

        return mulPoly;
    }

    /**
     * This method returns the composition of two polynomials.
     * @param p2
     * @return composedPolly
     */
    public Polynomial compose( Polynomial p2 ) {

        double [] composedCoefficients;
        int arrayLength;
        Polynomial result;
        Polynomial helpForCoefficientMultiplication;
        Polynomial composedPolly;
        
        //Find what should be the length of the new composed array.
        arrayLength = this.getDegree() * p2.getDegree() + 1;
        composedCoefficients = new double[arrayLength];
        
        //For each index (power) of the first polynomial, find the result 
        //and put them into right indexes of composedCoefficients array.
        for( int index = 0; index < coefficientsArray.length; index++)
        {
            //result is the polynomial I used for storing the results of each index of 
            //first polynomial and putting them into the indexes of composedCoefficients.
            result = p2;

            //I used helpForCoefficientMul to multiply the power part 
            //with the coefficients of first polynomial.
            double[] helpForCoefficientMul = { coefficientsArray[index]};
            helpForCoefficientMultiplication = new Polynomial( helpForCoefficientMul);
            
            if( index == 0)
            {
                if(p2.toString() != "0.0")
                {
                    composedCoefficients[0] += 1 * helpForCoefficientMul[0];
                }
            } 
            else if(index == 1)
            {
                //Multiply the coefficients of p2 with coefficient of first 
                //polynomial and put them into correct indexes.( i.e. 5(x + 1)^1) 
                result = p2.mul(helpForCoefficientMultiplication);

                for( int indexOfresult = 0; indexOfresult <= result.getDegree(); indexOfresult++)
                {
                    composedCoefficients[indexOfresult] += result.getCoefficient( indexOfresult);
                }
            }
            else
            {
                //Multiply index times p2 with itself. 
                for(int counter = 1; counter < index; counter++)
                {
                    result = result.mul(p2);
                }
                
                //Multiply the power part with the coefficient.
                result = result.mul( helpForCoefficientMultiplication);    
         
                //Add results to the array.
                for( int indexOfresult = 0; indexOfresult <= result.getDegree(); indexOfresult++)
                {
                    composedCoefficients[indexOfresult] += result.getCoefficient( indexOfresult);
                }   
            }

        }
        composedPolly = new Polynomial( composedCoefficients);
        return composedPolly;
    }

    /**
     * This method returns the result of the polynomial
     * division of two polynomials.
     * @param p2
     * @return quotient of polynomial division
     */
    public Polynomial div( Polynomial p2) {

        Polynomial dividedLeadTerms;
        Polynomial copyOfP1;
        Polynomial divPolly;
        Polynomial zeroPoly;
        double[] divisionCoefficients;

        //If p2's degree is bigger return a zero polynomial.
        if( p2.getDegree() > this.getDegree() )
        {
            zeroPoly = new Polynomial();
            return zeroPoly;
        }

        //Because we store dividedLeadTerms, and degree of p1 minus 
        //degree of p2 is the biggest it gets.(+1 for the index)
        divisionCoefficients = new double[ this.getDegree() - p2.getDegree() + 1];
        
        //Created a copy of p1 to see and trace code better.
        copyOfP1 = new Polynomial( coefficientsArray);
        
        //until degree of p2 passes degree of copyOf1
        while( copyOfP1.getDegree() >= p2.getDegree() )
        {
            //Store the division of leading terms into a polynomial.
            dividedLeadTerms = new Polynomial(copyOfP1.getDegree() - p2.getDegree(), copyOfP1.getCoefficient(copyOfP1.getDegree() ) / p2.getCoefficient(p2.getDegree())); 
        
            //P(x) = P(x) - Q(x) * T(x)
            //Q(x)-->p2 and T(x)--> dividedLeadTerms 
            copyOfP1 = copyOfP1.sub( p2.mul( dividedLeadTerms));

            for( int index = 0; index <= dividedLeadTerms.getDegree(); index++)
            {
                divisionCoefficients[index] += dividedLeadTerms.getCoefficient(index);
            }
        }

        divPolly = new Polynomial( divisionCoefficients);

        return divPolly;

    }
}