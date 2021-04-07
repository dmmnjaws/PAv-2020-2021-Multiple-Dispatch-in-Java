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

   2. The method `private static Method[] getAcceptableReceiverMethods(Class receiverType, String methodName, int orderOfDispatch)` is invoked and implements the following behaviour: All public methods implemented by the receiver, including super methods are fetched into a Stream, which is then filtered, leaving only the methods with the same name and same number of parameters as the original call, equal to the orderOfDispatch. Then, all non applicable methods (whose parameters' class hierarchies don't coincide with the class hierarchies of the parameters of the arguments of the original call) are filtered out. Finally, the filtered stream is converted to an array and returned. The stream is returned as an array to avoid invalidation due to the use of terminal operations, necessary later on. (Such as knowing the stream's length, which is done thru the method stream.count(), a terminal operation).

   3. Now we have an array of applicable methods (public methods of the receiver, with same name and same number of parameters as original call), called `acceptableReceiverMethods`. A recursive filter is applied to `acceptableReceiverMethods`, where each iteration is going to "crop" more and more unfit methods from the array, until, a single method remains (or a NoSuchMethodException is thrown). Two iterations are in play here: From the 1st to the last parameter (outer iteration, the loop in the `bestMethod` method), and for each parameter, the recursive filtering to find the "qualified" methods. This recursive filtering is described in 4.

   4. The method `private static Method[] filterMethods(Method[] methods, Class parameterType, int parameterIndex)` implements the second degree of filtering. Given an array of qualified methods, the type of a parameter of the original call, and the index of said parameter (the first parameter of the function has index 0), the array of qualified methods is filtered, leaving only the methods whose parameterIndex-nd parameter is of type parameterType. If the filtered array is empty, we call the filterMethods recursively, but with the superclass of parameterType as argument, climbing up the class hierarchy. In the end, the produced result should be a filtered version of `acceptableReceiverMethods`. If the recursive call goes on until the parameterType is identified to be of the type Object.class, a NoSuchMethodException is thrown.

   5. An iteration n of the outer iteration described in 3. runs with the `acceptableReceiverMethods` that resulted from the previous iteration n-1. (With the exception of the first iteration n = 0, which runs with the `acceptableReceiverMethods` that resulted from the step described in 2.)

   6. In the end, there are two possible outputs to this algorithm:
   - One and only one method is returned, in which case, it's the method invoked at the end thru the .invoke() method. It's worth noting that it's impossible that more than one method is returned due to the impossibility in java for a class to have two methods with the exact same signatures.
   - A NoSuchMethodException is thrown and stops the algorithm, which prevents the algorithm in any case to sucessively finish without returning one and only one method.  


---
## IMPLEMENTED EXTENSIONS:

- **Dealing with variable arity methods.** 

The variable arity of methods relates to the number of arguments a method needs to be invoked. Further explained here: https://www.informit.com/articles/article.aspx?p=2731930&seqNum=8. The MultipleDispatch class created for the project only accounts for methods with fixed arity, being these methods with a fixed number of arguments. To achieve this extension, we needed to consider variable arity methods, such as public method(Integer... integers), which receives an arbitrary number of objects. This behaviour is implemented in the ist.meic.pava.MultipleDispatchExtended package.

- **Memoization of previous invocations.**

Memoization of previous invocation allows circunventing of the algorithm in case a given invocation was already accounted for in a previous invocation of the algorithm. This is achieved thru a simple singleton cache. This behaviour is implemented in the ist.meic.pava.MultipleDispatchExtended package.

---
## EXTENSIONS FOR FUTURE CONSIDERATIONS:

- **Dealing not only with the class hierarchy but also the interface hierarchy.**

"Dealing with class hierarchy" means bestMethod() recursively calls itself with the argument's superclass, starting with the most specified class, until it finds the best method. So for instance, if we go further and add a CurvedLine.class that inherits Line.class and we add the relevant methods to print it, and calling invoking them using the dispatch class, the bestMethod() would iterate over the draw method using Shape, then using Line and then using CurvedLine as argument. We need to make this possible for interfaces also. 

- **Dealing with boxing and unboxing of arguments.**

Boxing and Unboxing is the conversion of primitive types to their wrapper classes (int to Integer -> Boxing; Integer to int -> Unboxing). Further explained here: https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html. If a primitive type is passed as argument to a method, it shouldn't be Boxed prior to the algorithm.

- **Dealing with the passing of null arguments.**

- **Dealing with arguments that take primitive arrays as arguments.**

- **Dealing with ambiguous references.**

---
## Testing

Both UsingMultipleDispatch.class and UsingMultipleDispatchExtended.class were tested as much as possible. A TestNG Suite is available. The TestNG suite uses the domain example in the package ist.meic.pava.domainExample.testSuite.

Testing the Memoization Cache with TestNG was unfeasable, since the methods of the cache are package-protected for security reasons, however, such testing is still possible manually:

During most of the development, in order to facilitate corner-case debugging, UsingMultipleDispatch.class and UsingMultipleDispatchExtended.class were manually tested using the MultipleDispatchMain.class. This class already contains some examples of testing:
   - for tests with double dispatch (`args[0]="double"`), triple dispatch (`args[0]="triple"`) and quadruple dispatch (`args[0]="quadruple"`), it uses the example domain in the package ist.meic.pava.domainExample.quadruple;
   - for the default test, the triple dispatch example test proposed in the project statement (`args[0]="***"`), it uses the example domain in the package ist.meic.pava.domainExample.statement;
   - for tests with variable arity methods (`args[0]="variablearity"`), it uses the example domain in the package ist.meic.pava.domainExample.extensions.
   - other tests can be performed in the else after the switch case in the main. (`args[0].length == 0`).