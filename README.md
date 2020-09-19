## Description
### Kata Steps
1. Create a String calculator with a method int Add(string numbers)
    * The method can take 0, 1, or 2 numbers and will return their sum.
    * An empty string will return 0.
    * For Example inputs: `“”, “1”, or “1,2”`
    * Start with the simplest test case of an empty string. Then 1 number. Then 2 numbers.
    * Remember to solve things as simply as possible, forcing yourself to write tests for things you didn’t think about.
    * Remember to refactor after each passing test.
2. Allow the Add method to handle an unknown number of arguments/numbers.
3. Allow the Add method to handle new lines between numbers (instead of commas).
    * The following input is ok: “1\n2,3” (will equal 6)
    * The following input is NOT ok: “1,\n” (not need to prove it - just clarifying)
4. Support different delimiters
    * To change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
    * The first line is optional. all existing scenarios should still be supported
5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.if there are multiple negatives, show all of them in the exception message
   
6.  Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2

7.  Delimiters can be of any length with the following format: “//[delimiter]\n” for example: “//[]\n12***3” should return 6

8.  Allow multiple delimiters like this: “//[delim1][delim2]\n” for example “//[][%]\n12%3” should return 6.

9.  Make sure you can also handle multiple delimiters with length longer than one char