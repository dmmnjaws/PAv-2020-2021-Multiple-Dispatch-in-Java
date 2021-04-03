# Multiple-Dispatch-in-Java

This project is an attempt to implement multiple dispatch in Java.

## NOTES:

- The fact we are rounding up to one Method (ore none) from a list of possible methods, calls for a reduce function, but we had a bit of trouble thinking about one, so we came up with an algorithm that works, even though it isn't explicitly a reduce function. 

---
## UsingMultipleDispatch.class DESCRIPTION:

The class `UsingMultipleDispatch.class` as well as the algorithm are described as follows:

   For simplicity the call subject to dispatch will be mentioned as the "original call".

   To use multiple dispatch, one should invoke the static method from the class UsingMultipleDispatch.class: `public static invoke(Object receiver, String methodName, Object... varArgs)`

   As preparation for the algorithm, three data structures are set up:
      - Class[] parameterTypes - the types of the parameters passed for the method one wants to invoke.
      - Class receiverType - the type of the receiver of the method one wants to invoke.
      - String methodName - the name of the method one wants to invoke.

   The method `private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes)` is invoked to start the algorithm. The algorithm is described as follows:

   1. A metric called the "orderOfDispatch" is collected. This metric allows us to know beforehand the number of parameters to be dispatched. Therefore, a double dispatch method call, with one parameter, would produce an orderOfDispatch equal to 1.

   2. The method `private static Method[] getAcceptableReceiverMethods(Class receiverType, String methodName, int orderOfDispatch)` is invoked and implements the following behaviour: All public methods implemented by the receiver, including super methods are fetched into a Stream, which is then filtered, leaving only the methods with the same name and same number of parameters as the original call, equal to the orderOfDispatch, and finally converted to an array and returned. The stream is returned as an array to avoid invalidation due to the use of terminal operations, necessary later on. (Such as knowing the stream's length, which is done thru the method stream.count(), that is terminal operation).

   3. Now we have an array of valid methods (public methods of the receiver, with same name and same number of parameters as original call), let's call it `acceptableReceiverMethods`. A recursive filter is applied to `acceptableReceiverMethods`, where each iteration is going to "crop" more and more unfit methods from the array, until, a single method remains (or a NoSuchMethodException is thrown). Two iterations are in play here: From the 1st to the last parameter (outer iteration, the loop in the `bestMethod` method), and for each parameter, the recursive filtering to find the "qualified" methods. This recursive filtering is described in 4.

   4. The method `private static Method[] filterMethods(Method[] methods, Class parameterType, int parameterIndex)` implements the second degree of filtering. Given an array of qualified methods, the type of a parameter of the original call, and the index of said parameter (the first parameter of the function has index 0), the array of qualified methods is filtered, leaving only the methods whose parameterIndex-nd parameter is of type parameterType. If the filtered array is empty, we call the filterMethods recursively, but with the superclass of parameterType as argument, climbing up the class hierarchy. In the end, the produced result should be a filtered version of `acceptableReceiverMethods`. If the recursive call goes on until the parameterType is identified to be of the type Object.class, a NoSuchMethodException is thrown.

   5. An iteration n of the outer iteration described in 3. runs with the `acceptableReceiverMethods` that resulted from the previous iteration n-1. (With the exception of the first iteration n = 0, which runs with the `acceptableReceiverMethods` that resulted from the step described in 2.)

   6. In the end, there are two possible outputs to this algorithm:
   - One and only one method is returned, in which case, it's the method invoked at the end thru the .invoke() method. It's worth noting that it's impossible that more than one method is returned due to the impossibility in java for a class to have two methods with the exact same signatures.
   - A NoSuchMethodException is thrown and stops the algorithm, which prevents the algorithm in any case to sucessively finish without returning one and only one method.  


---
## EXTENSIONS (GOAL):
- **Dealing not only with the class hierarchy but also the interface hierarchy.**

"Dealing with class hierarchy" means bestMethod() recursively calls itself with the argument's superclass, starting with the most specified class, until it finds the best method. So for instance, if we go further and add a CurvedLine.class that inherits Line.class and we add the relevant methods to print it, and calling invoking them using the dispatch class, the bestMethod() would iterate over the draw method using Shape, then using Line and then using CurvedLine as argument. We need to make this possible for interfaces also. 

- **Dealing with boxing and unboxing of arguments.**

Boxing and Unboxing is the conversion of primitive types to their wrapper classes (int to Integer -> Boxing; Integer to int -> Unboxing). Further explained here: https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html. Need further investigation. 

- **Dealing with variable arity methods.** 

The variable arity of methods relates to the number of arguments a method needs to be invoked. Further explained here: https://www.informit.com/articles/article.aspx?p=2731930&seqNum=8. The MultipleDispatch class created for the project only accounts for methods with fixed arity, being these methods with a fixed number of arguments. To achieve this extension, we need to consider methods with variable arity, or methods with varargs as arguments, such as public method(Object... objs), which receives an arbitrary number of objects.

---
## LOG:

**29 - 03 - 2021**
- basic development environment
- included both basic (for double dispatch testing) and complex (for triple dispatch testing) example domains
- included sample double dispatch invokation class

**30 - 03 - 2021**
- multiple dispatch is fully implemented (tested for up to quadruple dispatch - NIGHTMARE!)
- multiple dispatch algorithm explained in README.md in preparation for presentation

---
## USEFUL LINKS:


StackOverflow thread about implementing multiple dispatch in Java:
- https://stackoverflow.com/questions/11014917/use-invokedynamic-to-implement-multiple-dispatch

Stream terminal and non-terminal methods:
- http://tutorials.jenkov.com/java-functional-programming/streams.html 