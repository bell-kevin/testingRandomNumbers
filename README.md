# TestingRandomNumbersProject
S DEV 1060 Project 

A senior developer is working on a project and has asked you to do some testing for him. One of the methods in his project creates a random number between supplied minimum and maximum values. He has written the method that will create that random number, run it a few times, and thinks it looks good, but just to make sure, he has asked you to thoroughly test the method. That method looks like this

In Java:

  public static int getRN(int min, int max) {           
      Random rng = new Random( );           
      int answer = rng.nextInt((max - min + 1) + min);           
      return answer;      
  }  
      
In C#:       

  public static int getRN(int min, int max) {           
      Random rng = new Random( );           
      int answer = rng.Next(min, max + 1);           
      return answer;      
  }  
      
You put it into a small program to test it. Note that the program does not need any code in the main method, because you are this single method. Add it in the main class file, below the main method, not in a separate class. It would be a good practice to add code in the main class that asks the user for minimum and maximum values, or that has hard-coded min and max values, then calls this random number generator method, and displays the result. That is a good way to make sure the method runs as expected in your program and environment (it checks if you typed everything correctly). Since you are testing just the single method, and have no idea what it is being used for in the main method, there is no need for any code in the main method -- other than to test that your version of the method under test was typed correctly and will compile.  

In a test method, how can you assert that a random number is equal to a specific number? You can't, because the number should be random, so you can't predict the expected result. But you can assert that the random number falls within the range expected. You have written assertions with variables for expected and actual results, or literal values. You can also write expressions in the assertion. For example, if the method under test adds 2 numbers together and returns the sum, you could write an assertion like this:  

(Java) assertEquals(n1 + n2, actualResult)  

or  

(C#) Assert.AreEqual(n1 + n2, actualResult)  

The phrase "n1 + n2" is an expression that calculates the expected result in this example.  

Another example is checking for valid input, where the input number needs to be between 1 and 100 inclusive -- that means 1 and 100 are both valid results. How would you write that code to validate the input? It could look like this:  

if (inputValue >= 1 &amp;&amp; inputValue &lt;= 100) { do something }  

You can use that same type of expression in an assertion. That test would look like this as an assertion:  

(Java)    assertTrue(inputValue >= 1 &amp;&amp; inputValue &lt;= 100)  

(C#)    Assert.IsTrue(inputValue >= 1 &amp;&amp; inputValue &lt;= 100)  

If the relational tests (input value is greater than, equal, less than, etc) and the relational test (AND) are all true, the assertion passes, which means that the input value is valid, in this example.   

For random numbers, you can't specify an expected result, but you can check that the result is within the acceptable range. If it is true that the random number falls in the range, then the method worked correctly.  

The "arrange" stage of this test needs to know the range for the random numbers.  

What is the "act" stage of this test? You need to run the method from the other developer (above) and get the result, the random number. You learned how to test a static method in a previous assignment; you will do the same here. The method being tested returns the random number, which is the "actual" value that needs to be compared to the "expected" value. In this case, you will need to check if the random number falls within the expected range. For this assignment, that range is the numbers 20 to 29 inclusive.  

What is the "assert" stage of this test? You will assert that it is true that the actual result from the method is within the range. Be sure to add the optional message that will display if the test fails, and have it display the generated random number, which isn't in the range and caused the test to fail.

Make sure you type the method exactly as you see it above as the static method in the main class. For this assignment, you want 10 possible random numbers starting at 20, so the possible numbers are 20, 21, 22, 23, 24, 25, 26, 27, 28, 29.  

Run this test. Does it pass or fail? The programmer who gave it to you said he had run it a few times and it looked good. Does it? If it does, will it suffice to run it a few times? No, that is not at all thorough for testing the happy path and edge values -- you have no way of being sure the edges were ever tested with random numbers. You need to test it many many times, repeatedly calling the random number method to check it. How do you get repetition in any program? Use a loop. A unit test is code just like all the code you've written in any program, with the addition of the Assert methods. You know how to write loops, you can add a loop within the unit test method. As part of the act step, use a For loop that runs many times (20 or 500 or 1,000 times, whatever seems appropriate); inside the loop, run the test you just created, where it calls the method and then asserts that it is true that the result is within the range given.  

If you have a bunch of assertions, if any one fails, none of the following ones will run. If you use a loop of 20 executions of the test, and it fails on the first one, it didn't run any others. You will not see reports like "passed 10 and failed 10" -- if it fails once, it's over. You have to keep fixing and running the code until all tests are successful.  

Run the test 200 times in a loop, to test the method multiple times. When you run the test, it should fail, there is an error in that code. See the discussion below to figure out the problem, fix it, and test it again. Keep working on it until all the tests are successful. Then run the test 5,000 times, to make sure it works as it should.  

Take a screenshot of your fixed code for the method, of your test code, and of the last successful test that ran 5,000 times.   

Submission: screenshots specified and the root folder for the project.    

Discussion  

Random numbers have a minimum and maximum value. In some languages, you need to specify the number of numbers (Java) or scope (C#) and the starting value (Java) or shift (C#). The methods above have a phrase to get the number of numbers -- max - min + 1, that seems like it would be appropriate.  

In Java: The formula for generating random numbers is rng.nextInt(number of numbers). If there is a starting value, such as 1 or 50 or whatever, it needs to be added to the random number. So the formula for Java is: startingValue + rng.nextInt(number of numbers). It could also be written with the starting value at the end: rng.nextInt(number of numbers) + startingValue. Look carefully at the pattern provided above:  

rng.nextInt (  (max - min + 1) + min )  

If we resolve some of that code, do the math in the inner parentheses, it becomes: 

rng.nextInt (  (num) + min )  

Once the math inside the inner parentheses is done, we can drop those parentheses, and this becomes:

rng.nextInt ( num + min )  

which becomes: 

rng.nextInt (someNumber)  

There is no term for the starting number to be added to this random number -- it was used inside of the parentheses for the parameter for rng.nextInt(). 

In the formula above, the number of numbers, inside the parentheses that follow rng.nextInt, does more than provide the number of numbers, it also adds the min or starting point to that number of numbers. If the random number generator is supposed to use 10 numbers that start with 20, that would be the numbers 20, 21, 22, 23, ... 29. The formula above says that the number of numbers is that 10 plus the min value of 20, so the number of numbers is now 30. And there is no starting value, so the random number generator will create a number from 0 to 30. Thus it is possible to get numbers that are less than the intended starting point of 20, and one higher than the intended max value of 29.  

How do you fix that pattern so the parameter for rng.nextInt() has only the number of numbers in it, and the starting value is separate, added to that random number?  

In C#: The pattern for random numbers in C# is a little different from the pattern for Java. They are so similar in so much code, but there is a difference for creating random numbers. There are several patterns, but the one intended here is  

(int) rng.Next(starting value, ending value + 1)  

The rng.Next() method always returns a double, so it must have the cast to an integer at the beginning. There are 2 parameters, the shift (starting or min value) and the scope. That scope or max value will never be included in the possible random numbers, hence the "+1" in the pattern. If the code was:  

(int) rng.Next(20, 30)  

it would create a random number with the smallest possible number of 20 (the starting point) and the highest number will be 29, because it cannot go to 30. That value of 30 is the next integer after the highest one allowed. So this pattern also uses min and max values, but the shift and scope are represented differently. If min is 20 and max is 30, look at how the code provided works out:  (int) rng.Next(max - min + 1, min)  becomes: 

(int) rng.Next( 30 - 20 + 1, 20)  

which becomes: 

(int) rng.Next(11, 20)  

That means that the smallest number can be 11, and the largest number is one less than 20, or 19. The range of 11 to 19 is nothing at all like the intended range of 10 numbers starting at 20, or 20 to 29. None of the values generated with that pattern in the code provided would be valid.  

How do you fix the pattern in the code provided above?

== We're Using GitHub Under Protest ==

This project is currently hosted on GitHub.  This is not ideal; GitHub is a
proprietary, trade-secret system that is not Free and Open Souce Software
(FOSS).  We are deeply concerned about using a proprietary system like GitHub
to develop our FOSS project.  We have an
[open {bug ticket, mailing list thread, etc.} ](INSERT_LINK) where the
project contributors are actively discussing how we can move away from GitHub
in the long term.  We urge you to read about the
[Give up GitHub](https://GiveUpGitHub.org) campaign from
[the Software Freedom Conservancy](https://sfconservancy.org) to understand
some of the reasons why GitHub is not a good place to host FOSS projects.

If you are a contributor who personally has already quit using GitHub, please
[check this resource](INSERT_LINK) for how to send us contributions without
using GitHub directly

Any use of this project's code by GitHub Copilot, past or present, is done
without our permission.  We do not consent to GitHub's use of this project's
code in Copilot

![Logo of the GiveUpGitHub campaign](https://sfconservancy.org/img/GiveUpGitHub.png)
