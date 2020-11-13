/**
 * 
 */
package hw7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jun Xu & Bowen Chen; wrote 13 tests, each of which contains 3 cases;
 *
 */
class FractionTest {
	/**
	 * Gives a reasonable range for delta, used 0.000001 here.
	 */
	double delta;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() {
		this.delta = 0.000001;
	}

	/**
	 * Test method for Fraction(); check if the cases were permanently reduced;
	 */
	@Test
	void testFraction() {
		Fraction fraction1 = new Fraction(0, 1);
		assertEquals(fraction1.numerator, 0);
		assertEquals(fraction1.denominator, 1);

		Fraction fraction2 = new Fraction(0, -10);
		assertEquals(fraction2.numerator, 0);
		assertEquals(fraction2.denominator, 10);

		Fraction fraction3 = new Fraction(-1, -10);
		assertEquals(fraction3.numerator, 1);
		assertEquals(fraction3.denominator, 10);

	}

	/**
	 * Test method for reduceToLowestForm()}; 
	 * check if input has been reduced to the simplest form;
	 */
	@Test
	void testReduceToLowestForm() {
		Fraction fraction1 = new Fraction(41,69);
		fraction1.reduceToLowestForm();
		assertEquals(fraction1.numerator, 41);
		assertEquals(fraction1.denominator, 69);

		Fraction fraction2 = new Fraction(5, -25);
		fraction2.reduceToLowestForm();
		assertEquals(fraction2.numerator, -1);
		assertEquals(fraction2.denominator, 5);

		Fraction fraction3 = new Fraction(0, -16);
		fraction3.reduceToLowestForm();
		assertEquals(fraction3.numerator, 0);
		assertEquals(fraction3.denominator, 1);

	}

	/**
	 * Test method for Add();
	 */
	@Test
	void testAdd() {
		Fraction fraction1 = new Fraction(5, 25);
		Fraction fraction2 = new Fraction(-5, 25);
		Fraction nF = fraction1.add(fraction2);
		assertEquals(0, nF.numerator);
		assertEquals(1, nF.denominator);
		assertNotSame(nF, fraction1);
		assertNotSame(nF, fraction2);

		fraction1 = new Fraction(25, 100);
		fraction2 = new Fraction(25, 100);
		nF = fraction1.add(fraction2);
		assertEquals(1, nF.numerator);
		assertEquals(2, nF.denominator);
		assertNotSame(nF, fraction1);
		assertNotSame(nF, fraction2);

		fraction1 = new Fraction(25, 100);
		fraction2 = new Fraction(-125, 100);
		nF = fraction1.add(fraction2);
		assertEquals(-1, nF.numerator);
		assertEquals(1, nF.denominator);
		assertNotSame(nF, fraction1);
		assertNotSame(nF, fraction2);
	}

	/**
	 * Test method for subtract;
	 */
	@Test
	void testSubtract() {
		Fraction fraction1 = new Fraction(50, 100);
		Fraction fraction2 = new Fraction(50, 100);
		Fraction nF = fraction1.subtract(fraction2);
		assertEquals(0, nF.numerator);
		assertEquals(1, nF.denominator);

		fraction1 = new Fraction(100, 10);
		fraction2 = new Fraction(80, 10);
		nF = fraction1.subtract(fraction2);
		assertEquals(2, nF.numerator);
		assertEquals(1, nF.denominator);

		fraction1 = new Fraction(25, 100);
		fraction2 = new Fraction(50, 100);
		nF = fraction1.subtract(fraction2);
		assertEquals(-1, nF.numerator);
		assertEquals(4, nF.denominator);
	}

	/**
	 * Test method for multiplication;
	 */
	@Test
	void testMul() {
		Fraction fraction1 = new Fraction(0, 100);
		Fraction fraction2 = new Fraction(10, 100);
		Fraction nF = fraction1.mul(fraction2);
		assertEquals(0, nF.numerator);
		assertEquals(1, nF.denominator);

		fraction1 = new Fraction(1, -1);
		fraction2 = new Fraction(10, 100);
		nF = fraction1.mul(fraction2);
		assertEquals(-1, nF.numerator);
		assertEquals(10, nF.denominator);

		fraction1 = new Fraction(100, -10);
		fraction2 = new Fraction(-10, 100);
		nF = fraction1.mul(fraction2);
		assertEquals(1, nF.numerator);
		assertEquals(1, nF.denominator);
	}

	/**
	 * Test method for division;
	 */
	@Test
	void testDiv() {
		Fraction fraction1 = new Fraction(20, 100);
		Fraction fraction2 = new Fraction(20, 100);
		Fraction nF = fraction1.div(fraction2);
		assertEquals(1, nF.numerator);
		assertEquals(1, nF.denominator);

		fraction1 = new Fraction(-19, 100);
		fraction2 = new Fraction(19, 100);
		nF = fraction1.div(fraction2);
		assertEquals(-1, nF.numerator);
		assertEquals(1, nF.denominator);

		fraction1 = new Fraction(19, -100);
		fraction2 = new Fraction(19, 100);
		nF = fraction1.div(fraction2);
		assertEquals(-1, nF.numerator);
		assertEquals(1, nF.denominator);

	}

	/**
	 * Test method for decimal;
	 */
	@Test
	void testDecimal() {
		Fraction fraction1 = new Fraction(0, 1);
		double dec = fraction1.decimal();
		assertEquals(0, dec, this.delta);

		fraction1 = new Fraction(-1, -1);
		dec = fraction1.decimal();
		assertEquals(1, dec, this.delta);

		fraction1 = new Fraction(1, 9);
		dec = fraction1.decimal();
		assertEquals(0.11111111111, dec, this.delta);
	}

	/**
	 * Test method for the squaring function;
	 */
	@Test
	void testSqr() {
		Fraction fraction1 = new Fraction(0, 123456);
		fraction1.sqr();
		assertEquals(0, fraction1.numerator);
		assertEquals(1, fraction1.denominator);

		fraction1 = new Fraction(19, -19);
		fraction1.sqr();
		assertEquals(1, fraction1.numerator);
		assertEquals(1, fraction1.denominator);

		fraction1 = new Fraction(-41, 41);
		fraction1.sqr();
		assertEquals(1, fraction1.numerator);
		assertEquals(1, fraction1.denominator);
	}

	/**
	 * Test method for average of two int;
	 */
	@Test
	void testAverageFraction() {
		Fraction fraction1 = new Fraction(50, 50);
		Fraction otherFraction = new Fraction(0, 1);
		Fraction avgFraction = fraction1.average(otherFraction);

		assertEquals(1, avgFraction.numerator);
		assertEquals(2, avgFraction.denominator);

		fraction1 = new Fraction(28, 29);
		otherFraction = new Fraction(30, 31);
		avgFraction = fraction1.average(otherFraction);

		assertEquals(869, avgFraction.numerator);
		assertEquals(899, avgFraction.denominator);

		fraction1 = new Fraction(25, 29);
		otherFraction = new Fraction(-25, 29);
		avgFraction = fraction1.average(otherFraction);

		assertEquals(0, avgFraction.numerator);
		assertEquals(1, avgFraction.denominator);
	}

	/**
	 * Test method for AverageFractionArray();
	 */
	@Test
	void testAverageFractionArray() {
		Fraction fraction1 = new Fraction(5, -15);

		Fraction[] fractions = {};
		Fraction avgFraction = Fraction.average(fractions);
		assertEquals(0, avgFraction.numerator);
		assertEquals(1, avgFraction.denominator);

		Fraction[] fractions1 = { fraction1 };
		avgFraction = Fraction.average(fractions1);
		assertEquals(-1, avgFraction.numerator);
		assertEquals(3, avgFraction.denominator);

		Fraction[] fractions2 = { new Fraction(-5, 5), new Fraction(-5, 5), new Fraction(-5, 5), new Fraction(-5, 5) };
		avgFraction = Fraction.average(fractions2);
		assertEquals(-1, avgFraction.numerator);
		assertEquals(1, avgFraction.denominator);

	}

	/**
	 * Test method for AverageIntArray();
	 */
	@Test
	void testAverageIntArray() {
		int[] intArray = {};
		Fraction avgFraction = Fraction.average(intArray);
		assertEquals(avgFraction.numerator, 0);
		assertEquals(avgFraction.denominator, 1);

		int[] intArray1 = {-4, -2, 0, 2, 4};
		avgFraction = Fraction.average(intArray1);
		assertEquals(avgFraction.numerator, 0);
		assertEquals(avgFraction.denominator, 1);

		int[] intArray2 = { 0 };
		avgFraction = Fraction.average(intArray2);
		assertEquals(avgFraction.numerator, 0);
		assertEquals(avgFraction.denominator, 1);

		int[] intArray3 = { 2, 4, 6, 8, 10};
		avgFraction = Fraction.average(intArray3);
		assertEquals(avgFraction.numerator, 6);
		assertEquals(avgFraction.denominator, 1);

	}

	/**
	 * Test method for Equals function;
	 */
	@Test
	 void testEquals() {
	  Fraction fraction1 = new Fraction(0, 9);
	  Fraction fraction2 = new Fraction(0, -13579);

	  Fraction fraction3 = new Fraction(4, 24);
	  Fraction fraction4 = new Fraction(-4, 24);

	  assertEquals(fraction1, fraction2);

	  assertEquals(fraction3.equals(fraction4), false);
	  assertEquals(fraction1.numerator, 0);
	  assertEquals(fraction1.denominator, 9);

	  assertEquals(fraction2.numerator, 0);
	  assertEquals(fraction2.denominator, 13579);

	  assertEquals(fraction3.numerator, 4);
	  assertEquals(fraction3.denominator, 24);

	  assertEquals(fraction4.numerator, -4);
	  assertEquals(fraction4.denominator, 24);

	 }

	/**
	 * Test method for {@link hw7.Fraction#toString()};
	 */
	@Test
	void testToString() {
		Fraction fraction1 = new Fraction(1, -5);
		String str = fraction1.toString();
		assertEquals("-1/5", str);

		fraction1 = new Fraction(0, -1);
		str = fraction1.toString();
		assertEquals("0/1", str);

		fraction1 = new Fraction(-8, -2);
		str = fraction1.toString();
		assertEquals("8/2", str);
	}

}
