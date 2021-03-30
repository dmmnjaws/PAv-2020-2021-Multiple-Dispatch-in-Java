# Multiple-Dispatch-in-Java

This project is an attempt to implement multiple dispatch in Java.

---
## EXTENSIONS:
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

---
## USEFUL LINKS:
- https://stackoverflow.com/questions/11014917/use-invokedynamic-to-implement-multiple-dispatch