package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MathUtilsTest {
	
	MathUtils mathUtils;
	
	//BeforeAll annotation runs even before the MathUtilsTest instance is created and therefore needs to be static
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("Starting the tests now.....");
	}
	
	//BeforeEach annotation makes the method run before every test
	@BeforeEach
	void init() {
		mathUtils = new MathUtils();
	}
	
	//AfterEach annotation makes the method run after each test is completed
	@AfterEach
	void afterAllCleanUp() {
		System.out.println("Test has been finished.....");
	}
	
	//Fail the test: Use case when we don't want a certain part of a code to run
	@Test
	@Disabled
	@DisplayName("Just an example. Remove the Disabled annotation to see the effects.")
	void test() {
		fail("Not yet implemented");
	}
	
	//Not failing is success
	@Test
	@DisplayName("Test passes because it doesn't fail.")
	void notFailingisSuccess() {
		System.out.println("This test runs");
	}
	
	//Testing the add method using AssertEquals
	@Test
	@DisplayName("Add test for +ve integers")
	void addMethodTest() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, "The add method should add two positive integers");
	}
	
	//Testing the method that calculates the area of a square
	@Test
	@DisplayName("Square Area Test for +ve side")
	void computeSquareAreaTest() {
		int expected = 25;
		int actual = mathUtils.computeSquareArea(5);
		assertEquals(expected, actual, "This method should compute the area of the square given the side");
	}
	
	//Testing the divide method on division by zero
	@Test
	@DisplayName("Test for Division by 0")
	void divideTest() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "On division by zero, an arithmetic exception should be thrown");
	}
	
	//Assumptions
	@Test
	@DisplayName("A test that only makes sense if the server is up")
	void retrieveFileTest() {
		boolean isServerUp = false; //False will come from server status checking logic
		assumeTrue(isServerUp);
		
		//Some test that would run in case the server was up
	}
	
	//Trying out AssertAll
	@Test
	@DisplayName("Test for the multiply method")
	void multiplyTest() {
		assertAll(
				() -> assertEquals(0, mathUtils.multiply(0, 2)),
				() -> assertEquals(6, mathUtils.multiply(-2, -3)),
				() -> assertEquals(8, mathUtils.multiply(4, 2))
				);
	}
	
	//Trying out nested tests using a nested class
	@Nested
	class SubtractTest{
		@Test
		@DisplayName("Subtract with both nums +ve")
		void subtractTestPositive() {
			assertEquals(5, mathUtils.subtract(10, 5), "This method should subtract two numbers");
		}
		
		@Test
		@DisplayName("Subtract with both nums -ve")
		void subtractTestNegative() {
			assertEquals(-1, mathUtils.subtract(-2, -1), "This method should subtract two numbers");
		}
	}
	
	//Using lambdas for assert message, computationally better
	@Test
	@DisplayName("Lambda on assert message")
	void addTestLambda() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, () -> "The method should have returned " + expected + " but it returned " + actual);
	}
}
