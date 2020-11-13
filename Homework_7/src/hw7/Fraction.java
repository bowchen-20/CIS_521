package hw7;
/**
 * @author Jun Xu & Bowen Chen; 
 *
 */
public class Fraction {
	public int numerator; //define a public integer
	public int denominator;//define a public integer
	
	/**
	 * a constructor for fraction
	 */
	public Fraction(int numerator, int denominator) {
		

		if (numerator<0 && denominator<0) {//if numerator smaller than 0, denomenator smaller than 0
			this.numerator=numerator*-1; //they will both be positive
			this.denominator=denominator*-1;
		}
		
		else if (numerator <=0 && denominator>0) { //if denomenator grater than 0 numerator smaller than 0
			this.denominator=denominator;//nothing changes
			this.numerator=numerator;
		}
		
		else if (numerator >=0 && denominator<0) { //if denomenator smaller than 0 numerator greater than 0
			this.denominator=denominator*-1;//numerator will be smaller than 0 instead of denominator
			this.numerator=numerator*-1;
		}
		
		else if (numerator >0 && denominator>0) { //if both greater than 0
			this.denominator=denominator;//nothing changes
			this.numerator=numerator;
		}
		else if (this.numerator==0) { //if numerator is 0
			this.denominator=denominator; //nothing changes
		}
	}
	
	/**
	 * a function that reduce this in to the lowest form
	 */
		
	public void reduceToLowestForm() {
		if (this.numerator==0) { //if numerator is 0
			this.denominator=1;//denominator becomes 1 now
		}
		else {
			while (true) { //a infinit loop 
			int  commondivisor =0;
			for (int i= 2; i<=Math.abs(this.numerator)&&i<=this.denominator;i++) { //a for loop that loop through all the common denominator
				if (this.numerator%i==0 &&this.denominator%i==0) {//find the smallest commondenomenator
				commondivisor=i;//give it to a variable
				}
			}
			if (commondivisor != 0) {  //if this denominator is not 0
			this.denominator=this.denominator/commondivisor;//divide the common denominator
			this.numerator=this.numerator/commondivisor;//divide the common denominator
			}
			else {//if they don't have commondenominator 
				break;//break the infinit loop
			}
			}
				
			}
		}
	/**
	 * a function that add up two fraction
	 */
	
	public Fraction add(Fraction otherFraction) {
		int commondenomenator=this.denominator * otherFraction.denominator; //find the biggest common multiplier 
		int thisnewnumerator=this.numerator*otherFraction.denominator + otherFraction.numerator*this.denominator; //add up the numerator
		Fraction newfraction=new Fraction(thisnewnumerator,commondenomenator);//define a new fraction and put the new value

		newfraction.reduceToLowestForm(); //reduce that fraction to the lowest form
		return newfraction; //return the new fraction
	}
	/**
	 * a function that substract one fraction from the other
	 */
	public Fraction subtract(Fraction otherFraction) {
		int commondenomenator=this.denominator * otherFraction.denominator; //find the biggest common multiplyer
		int thisnewnumerator=this.numerator*otherFraction.denominator - otherFraction.numerator*this.denominator; //find the difference of two numerator
		Fraction newfraction=new Fraction(thisnewnumerator,commondenomenator); //give the value to a new fraction


		newfraction.reduceToLowestForm(); //reduce it to its lowest form

		return newfraction; //return the new fraction
	}
	/**
	 * a function that multiples two function
	 */
	public Fraction mul(Fraction otherFraction) {
		int thisnewnumerator=this.numerator*otherFraction.numerator;  //multiplies two numerator
		int thisnewdenominator= otherFraction.denominator*this.denominator; //multiples two denominator
		Fraction newfraction=new Fraction(thisnewnumerator,thisnewdenominator); //give the value to a new fraction
		newfraction.reduceToLowestForm();//reduce to its lowest form
		return newfraction; //return the new fraction
	}
	/**
	 * a function that divide one fraction from another
	 */
	public Fraction div(Fraction otherFraction) {
		int thisnewnumerator=this.numerator*otherFraction.denominator; //multiple the numerator to antoher's denominator
		int thisnewdenominator= otherFraction.numerator*this.denominator; //multiple the denominator to another's numerator
		Fraction newfraction=new Fraction(thisnewnumerator,thisnewdenominator); //give new value to the new fraction
		newfraction.reduceToLowestForm();//reduce it to the lowest form
		return newfraction; //return the new fraction
	}
	/**
	 * a function that reduce a fraction to decimals
	 */
	public double decimal() {
		{
			Fraction newone=this;//define a new fraction that contains this fraction
			newone.reduceToLowestForm(); //reduce the new fraction to the lowest form
			return (double)newone.numerator / (double)newone.denominator;//divide the numerator by denominator as double format
		}
	}
	/**
	 * a function that squared one fraction
	 */
	public void sqr() { 
		this.numerator=this.numerator*this.numerator;// squared its numerator
		this.denominator=this.denominator*this.denominator; //squared its denominator
		this.reduceToLowestForm(); //reduce it to the lowest form
		return;
	}
	/**
	 * a function that takes avearge of two fraction
	 */
	public Fraction average(Fraction otherFraction) {
		Fraction NEWFRACT=add(otherFraction); //add two fraction
		NEWFRACT.denominator=NEWFRACT.denominator*2; //divide the sum by 2
		NEWFRACT.reduceToLowestForm(); //reduce it tow the lowest form
		
		return NEWFRACT; //return the new fraction
	}
	/**
	 * a function that takse average of an array of fractions
	 */
	public static Fraction average(Fraction[] fractions) {
		Fraction sumfraction= new Fraction(0,0); //defind the new sum of fractions as 0
		sumfraction.reduceToLowestForm(); //reduce the sum fraction to 0/1
		for(Fraction thefractions:fractions) {//for all fractions in the array
			sumfraction=sumfraction.add(thefractions);	 //add them into the sumfraction
		}
		
		sumfraction.denominator=sumfraction.denominator*fractions.length; //divide the sum with the total number
		sumfraction.reduceToLowestForm(); //reduce the result into its lowest form
		return sumfraction; //return the new value
	}
	/**
	 * a function that find average of two integers in to fraction
	 */
	public static Fraction average(int[] ints) {
		int sum=0; //make sum a variable of 0
		for (int number:ints) { //for all the integers in the array
			sum =sum+ number; //add them together
		}
		Fraction average=new Fraction(sum,ints.length); //divide the sum with the total value
		average.reduceToLowestForm(); //reduce it to the lowest form
		return average;//return average 
	}
	@Override
	/**
	 * a function that test if two objects are equal
	 */
	public boolean equals(Object o) {
		if (o instanceof Fraction) {  //if o is a fraction
		Fraction otherFraction = new Fraction(((Fraction) o).numerator, ((Fraction) o).denominator); //make a new fraction equals to the object
		   Fraction thisFraction = new Fraction(this.numerator, this.denominator);  //make a new fraction equals to this fraction
		   otherFraction.reduceToLowestForm();//reduce both of them
		   thisFraction.reduceToLowestForm();
		   if (thisFraction.numerator == otherFraction.numerator && thisFraction.denominator == otherFraction.denominator) //if their numerator and denominator equals to each other
		    return true; //return true
	}
		return false; //all other cases return false
	}
	/**
	 * a function that convert fraction in to string
	 */
	@Override
	public String toString() {

		String thestring=Integer.toString(this.numerator)+"/"+ Integer.toString(this.denominator); //concert the fraction in to string
		
		return thestring; //return the string
	}

	public static void main(String[] args){ // nothing is here

		
		}
	
}
