
public class InfiniteInteger implements Comparable<InfiniteInteger>
{
    // TO DO: Instance Variables
    // Note that it is a good idea to declare them final to
    // prevent you from accidentally modify them.
    String input;
    boolean isNeg=false;
    int[] num1;
    int size;
    /**
     * Constructor: Constructs this infinite integer from a string
     * representing an integer.
     * @param s  a string represents an integer
     */
    public InfiniteInteger(String s)
    {
        // TO DO: Constructor
        input=s;

        //Takes the negative off, if there is one and sets isNeg to true
        if(input.substring(0,1).equals("-"))
        {
           input=input.substring(1);
            isNeg=true;
        }
        //Trims the zeros
        for(int i=0; i<input.length()-1; i++)
        {
            if(input.substring(i,i+1).equals("0"))
            {
                input=input.substring(i+1);
                i=-1;
            }
            else
                i=input.length();
        }
        size=input.length();
        num1= new int[size];
        for(int i =0; i<size; i++)
        {
           num1[i]=Integer.valueOf(input.substring(i,i+1));
        }


    }

    /**
     * Constructor: Constructs this infinite integer from an integer.
     * @param anInteger  an integer
     */
    public InfiniteInteger(int anInteger)
    {
        // TO DO: Constructor
        //Converts the int to a string, and then implementation is the same as above
        input=Integer.toString(anInteger);

        //Takes the negative off, if there is one and sets isNeg to true
        if(input.substring(0,1).equals("-"))
        {
            input=input.substring(1);
            isNeg=true;
        }
        //Trims the zeros
        for(int i=0; i<input.length()-1; i++)
        {
            if(input.substring(i,i+1).equals("0"))
            {
                input=input.substring(i+1);
                i=-1;
            }
            else
                i=input.length();
        }

        size=input.length();
        num1= new int[size];

        for(int i =0; i<size; i++)
        {
            num1[i]=Integer.valueOf(input.substring(i,i+1));
        }
    }

    /**
     * Gets the number of digits of this infinite integer.
     * @return an integer representing the number of digits
     * of this infinite integer.
     */
    public int getNumberOfDigits()
    {
        // TO DO
        return size;
    }

    /**
     * Checks whether this infinite integer is a negative number.
     * @return true if this infinite integer is a negative number.
     * Otherwise, return false.
     */
    public boolean isNegative()
    {
        // TO DO
        return isNeg;
    }

    /**
     * Calculates the result of this infinite integer plus anInfiniteInteger
     * @param anInfiniteInteger the infinite integer to be added to this
     * infinite integer.
     * @return a NEW infinite integer representing the result of this
     * infinite integer plus anInfiniteInteger
     */
    public InfiniteInteger plus(final InfiniteInteger anInfiniteInteger)
    {
        // TO DO
        String sum="";
        InfiniteInteger longer=this;
        InfiniteInteger shorter= anInfiniteInteger;
        int num=0;
        int over10=0;

        if(this.compareTo(anInfiniteInteger)==0)
        {
        //Easy Mode, if they are equal
            for(int i =size-1; i>-1; i--)
            {

                num= this.num1[i]+anInfiniteInteger.num1[i]%10+over10;
                over10=0;

                if(num>=10 && i!=0)
                {
                    num%=10;
                    over10=1;
                }

                sum=num+sum;
                num=0;
            }
        if(this.isNeg && anInfiniteInteger.isNeg )
            sum="-"+sum;
        InfiniteInteger number= new InfiniteInteger(sum);
        return number;
        }
       if(this.size>anInfiniteInteger.size)
       {
           longer=this;
           shorter=anInfiniteInteger;
       }
       else if(this.size==anInfiniteInteger.size)
       {
           for(int i =0; i<this.size; i++)
           {
               if(this.num1[i]>anInfiniteInteger.num1[i])
               {
                   longer=this;
                   shorter=anInfiniteInteger;
                   break;
               }
               else if(this.num1[i]<anInfiniteInteger.num1[i])
               {
                   longer=anInfiniteInteger;
                   shorter=this;
                   break;
               }

           }

       }
        else
       {
           longer=anInfiniteInteger;
           shorter=this;
       }
        //System.out.println(longer+ " " + shorter);
        //System.out.println(longer + " "+ shorter);

        //If the two are not equal, and are either both positive or negative
        if((longer.isNeg && shorter.isNeg) ||(!longer.isNeg && !shorter.isNeg)) {
            int shorterCounter = shorter.size - 1;

            int [] sumArr = new int[longer.getNumberOfDigits()];

            for(int i =longer.getNumberOfDigits()-1; i>-1; i--) {
                if (shorterCounter > -1) {
                    sumArr[i] = longer.num1[i] + shorter.num1[shorterCounter];
                    shorterCounter--;
                    }
                else{
                    sumArr[i] = longer.num1[i];

                }

            }
            for(int i =sumArr.length-1; i>-1; i--)
            {
                sumArr[i]+=over10;
                    if(sumArr[i]>=10 && i!=0)
                    {
                        sumArr[i]%=10;
                        over10=1;
                    }
                else{
                        over10=0;
                    }
            }
            for(int i=0; i<sumArr.length; i++)
            {
                sum+=sumArr[i];
            }


            if (longer.isNeg) {
                sum = "-" + sum;
            }
            InfiniteInteger number = new InfiniteInteger(sum);

            if(number.isNeg&& number.num1[0]==0)
            {
                number.isNeg=false;
            }
            return number;
        }
        else
        {
            int [] sumArr = new int[longer.getNumberOfDigits()];
            int shortCount= shorter.getNumberOfDigits()-1;

            //System.out.println(longer.getNumberOfDigits()-1);
            for(int i =longer.getNumberOfDigits()-1; i>-1; i--)
            {
                if(shortCount> -1)
                {
                    sumArr[i]=longer.num1[i]-shorter.num1[shortCount];
                    shortCount--;
                }
                else{

                    sumArr[i]=longer.num1[i];
                }
            }
            for(int i=sumArr.length-1; i>-1; i--)
            {

                if(sumArr[i]<0)
                {

                    sumArr[i]+=10;
                    sumArr[i-1]--;
                }
            }
            for(int i=0; i<sumArr.length; i++)
            {
                sum+=sumArr[i];
            }
            if(longer.isNeg)
                sum="-"+sum;

            InfiniteInteger number = new InfiniteInteger(sum);
            if(number.isNeg&& number.num1[0]==0)
            {
                number.isNeg=false;
            }
            return number;
        }
    }

    /**
     * Calculates the result of this infinite integer subtracted by anInfiniteInteger
     * @param anInfiniteInteger the infinite integer to subtract.
     * @return a NEW infinite integer representing the result of this
     * infinite integer subtracted by anInfiniteInteger
     */
    public InfiniteInteger minus(final InfiniteInteger anInfiniteInteger)
    {
        // TO DO
        String orginal =this.toString();
        InfiniteInteger orginal2 = new InfiniteInteger(orginal);
        InfiniteInteger inputNum =anInfiniteInteger;
        inputNum.isNeg=!inputNum.isNeg;
        InfiniteInteger number= orginal2.plus(inputNum);

        return number;
    }

    /**
     * Calculates the result of this infinite integer multiplied by anInfiniteInteger
     * @param anInfiniteInteger the multiplier.
     * @return a NEW infinite integer representing the result of this
     * infinite integer multiplied by anInfiniteInteger.
     */
    public InfiniteInteger multiply(final InfiniteInteger anInfiniteInteger)
    {
        // TO DO

       int num1[]=this.num1;
       int num2[]=anInfiniteInteger.num1;
       int multArr[][] = new int[num2.length][num1.length+num2.length];
       int result[] = new int[num1.length+num2.length+2];
        for(int i=0 ; i<num1.length+num2.length+1 ; i++)
            result[i]=0;

        for (int i=0 ; i<num2.length ; i++){
            for (int x=0 ; x<num1.length+num2.length ; x++){
                multArr[i][x] = 0;
            }
        }

        for (int i=0,row=0 ; i<num2.length ; i++,row++){    //for num2
            for (int x=0 , col=num2.length+num1.length-1-i ; x<num1.length ;col--, x++){  //for num1

                multArr[row][col] = num1[num1.length-1-x]*num2[num2.length-i-1];

            }

        }
        for (int y=num1.length+num2.length-1 ; y>0 ; y--){
            for (int z=0 ; z<num2.length ; z++){
                result[(num1.length+num2.length+1)-(num1.length+num2.length-1)+y] += multArr[z][y];

            }
        }
        for(int q=result.length-1 ; q>0 ; q--){
            if(result[q]>9){
                result[q-1] += result[q]/10;
                result[q]=result[q]%10;
            }
        }
        String product="";

        for(int u=0 ; u<result.length ; u++){
            product += new Integer(result[u]).toString();
        }
        if(this.isNeg!=anInfiniteInteger.isNeg)
            product="-"+product;
        InfiniteInteger number= new InfiniteInteger(product);
        if(number.isNeg&& number.num1[0]==0)
        {
            number.isNeg=false;
        }
        return number;

        /*String product="";
        InfiniteInteger num= new InfiniteInteger(0);
        //System.out.println(this + " " + anInfiniteInteger);
        int counterIn=0;
        int counterOut=0;
        int over10=0;
        for(int i =anInfiniteInteger.size-1; i>-1; i--)
        {
            for(int x=this.size-1; x>-1; x--) {
                //Below Doesn't work for really large numbers
               double n1=1;
               double n2=1;
               for(int q=0; q<counterIn; q++)
               {
                   n1*=10;
               }
                for(int q=0; q<counterOut; q++)
                {
                    n2*=10;
                }
                int z=(int) (anInfiniteInteger.num1[i]*n1*n2);
                z*=this.num1[x];
                counterIn++;
                num=num.plus(new InfiniteInteger(z));
            }
            counterIn=0;
            counterOut++;
        }
        product=num.toString();
        if(this.isNeg!=anInfiniteInteger.isNeg)
            product="-"+product;
        InfiniteInteger number= new InfiniteInteger(product);
        if(number.isNeg&& number.num1[0]==0)
        {
            number.isNeg=false;
        }
        return number;*/
    }

    /**
     * Generates a string representing this infinite integer. If this infinite integer
     * is a negative number a minus symbol should be in the front of numbers. For example,
     * "-12345678901234567890". But if the infinite integer is a positive number, no symbol
     * should be in the front of the numbers (e.g., "12345678901234567890").
     * @return a string representing this infinite integer number.
     */
    public String toString()
    {
        // TO DO
        String number ="";
        for(int i =0; i<size; i++)
        {
            number+= num1[i];
        }
        if(isNeg)
        {
            number="-" + number;
        }
        return number;
    }

    /**
     * Compares this infinite integer with anInfiniteInteger
     * @return either -1, 0, or 1 as follows:
     * If this infinite integer is less than anInfiniteInteger, return -1.
     * If this infinite integer is equal to anInfiniteInteger, return 0.
     * If this infinite integer is greater than anInfiniteInteger, return 1.
     */
    public int compareTo(final InfiniteInteger anInfiniteInteger)
    {
        // TO DO
        if(this.toString().equals(anInfiniteInteger.toString()))
            return 0;
        else
        {
            if(this.size>anInfiniteInteger.size && !isNeg)
                return 1;
            if(this.size>anInfiniteInteger.size && isNeg)
                return -1;
            if(this.size<anInfiniteInteger.size && !anInfiniteInteger.isNeg)
                return -1;
            if(this.size<anInfiniteInteger.size && anInfiniteInteger.isNeg)
                return 1;
            if(size==anInfiniteInteger.size &&(isNeg!=anInfiniteInteger.isNeg))
            {
                if(isNeg)
                    return -1;
                else
                    return 1;
            }
            if(this.size==anInfiniteInteger.size && ((this.isNeg && anInfiniteInteger.isNeg)||(!this.isNeg && !anInfiniteInteger.isNeg)))
            {
                for(int i =0; i<size; i++)
                {
                    if(num1[i]>anInfiniteInteger.num1[i])
                        return 1;
                    else if(num1[i]<anInfiniteInteger.num1[i])
                        return -1;
                }

            }


        }
        return 0;

    }
}