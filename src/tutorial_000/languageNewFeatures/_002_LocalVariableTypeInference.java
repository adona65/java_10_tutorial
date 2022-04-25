package tutorial_000.languageNewFeatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _002_LocalVariableTypeInference {
	
	@SuppressWarnings({ "unused", "rawtypes", "serial" })
	public static void main(String[] args) {
		/*
		 * Java 10 finally introduced the "var" keyword incubated in java 9. You can use var for local variables instead of a typed name (Manifest Type). 
		 * This is done by a new feature which is called Local Variable Type Inference.
		 * 
		 * WHAT IS TYPE INFERENCE ?
		 * 
		 * Type inference is Java compiler's ability to look at each method invocation and corresponding declaration to determine the type argument (or 
		 * arguments) that make the invocation applicable. For local variable declarations with initializer, we can now use a reserved type name "varâ" 
		 * instead of a manifest types :
		 */
		var list = new ArrayList<String>(); // infers ArrayList<String>
		var stream = list.stream(); // infers Stream<String>
		
		/*
		 * MANIFEST TYPE 
		 * 
		 * Explicit identification of type for each variable being declared is called as Manifest Typing. For example, if a variable "actorsâ" is going to 
		 * store a List of Actors, then its type List<Actor> is the manifest type and its must be declared prior to Java 10 :
		 */
		List<Actor> oldarJavaActors =  List.of(new Actor()); // Pre Java 10 
		var java10Actors = List.of(new Actor()); // Java 10 onwards
		
		/*
		 * HOW DOES LOCAL VARIABLE TYPE INFERENCE WORK ?
		 * 
		 * Parsing a var statement, the compiler looks at the right hand side of the declaration, aka initializer, and it infers the type from the right hand 
		 * side (RHS) expression. 
		 * 
		 * but be careful, java isn't a dynamically typed language. It's still a statically typed language. Let take the following example :
		 "
			var fileName = "Sample.txt";
			var line = "";
			var fileReader = new FileReader(fileName);
			var bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		 "
		 * If we compile the code, then decompile it, we will get :
		 "
		 	String fileName = "Sample.txt";
			String line = "";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		 "
		 * Here the compiler properly infers the type of the variable from the right hand side expression and adds that to the bytecode.
		 * 
		 * VAR IS A RESERVED TYPE NAME
		 * 
		 * var is not a keyword, It's a reserved type name. What does it mean? It mean that :
		 * 
		 * - We can create a variable named "varâ". 
		 */
		var var = 5;
		
		/*
		 * - "varâ" as a method name is allowed. 
		 */
		var();
		
		System.out.println("=====================================");
		
		/*
		 * - "varâ" as a package name is allowed. 
		 * - "varâ" cannot be used as the name of a class or interface (but "Var" is allowed as Java is case sensitive).
		 * 
		 * LOCAL VARIABLE TYPE INFERENCE USAGE SCENARIOS
		 * 
		 * Local type inference can be used only in the following scenarios :
		 * - Limited only to Local Variable with initializer
		 * - Indexes of enhanced for loop or indexes
		 * - Local declared in for loop
		 */
		var numbers = List.of(1, 2, 3, 4, 5); // inferred value ArrayList<String>
		// Index of Enhanced For Loop
		for (var number : numbers) {
			System.out.println(number);
		}
		
		System.out.println("-------------------------------------");
		
		// Local variable declared in a loop
		for (var i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		System.out.println("=====================================");
		
		/*
		 * LOCAL VARIABLE TYPE INFERENCE LIMITATIONS
		 * 
		 * - Cannot use "var" on variables without initializer. If there's no initailizer then the compiler will not be able to infer the type.
		 * - Cannot be used for multiple variable definition (eg : var x = 5, y = 10;).
		 * - Null cannot be used as an initializer for var. Null is not a type and hence the compiler cannot infer the type of the RHS expression.
		 * - Cannot have extra array dimension brackets (eg : var actorArr[] = new Actor[10];).
		 * - Expressions that have lambdas, method references, and array initializers, will trigger an error with "var".For the type inference of Lambda 
		 * 		expressions, Method inference and the Array initializers, compiler relies on the left hand side expression or the argument definition of 
		 * 		the method where the expression is passed while var uses RHS, this would form a cyclic inference and hence the compiler generates a compile 
		 * 		time error (eg : "var min = (a, b) -> a < b ? a : b;", "var minimum = Math::min;", "var nums = {1,2,3,4,5};").
		 * 
		 * GENERICS WITH LOCAL VARIABLE TYPE INFERENCE
		 * 
		 * Java has type inference for Generics and to top of it, it also has to do Type Erasure for any generics statement. There are some edge cases which 
		 * should be understood when using local type reference with Generics.
		 *
		 * TYPE ERASURE
		 * 
		 * To implement generics, the Java compiler applies type erasure to replace all type parameters in generic types with their bounds or Object if the 
		 * type parameters are unbounded. 
		 */
		var map1 = new HashMap(); // Inferred as HashMap
		var map2 = new HashMap<>(); // Inferred as HashMap<Object, Object>
		/*
		 * - map1 : Compiler infers the map as HashMap without any generic type.
		 * - map2 : The diamond operator relies on the LHS for the type inference, here the compiler cannot infer the LHS and hence it infers map2 to have 
		 * 		upper bound or super type to which the HashMap can be denoted to. This leads to map2 being inferred as HashMap<Object, Object>.
		 * 
		 * ANONYMOUS CLASS TYPES
		 * 
		 * Allowing variables to have anonymous class types introduces useful shorthand for declaring a singleton instance of a local class. 
		 */
		var runnable = new Runnable() {
			@Override
			public void run() {
				var numbers = List.of(5, 4, 3, 2, 1);
				for (var number : numbers) {
					System.out.println(number);
				}
			}
		};
		runnable.run();
		
		System.out.println("=====================================");
		
		/*
		 * NON DENOTABLE TYPES
		 * 
		 * An expression that cannot be inferred to a specific type is known as Non Denotable Type. Such type can occur for a capture variable type, 
		 * intersection type, or anonymous class type. Let's understand how a Non Denotable Type can be used for local variable type inference : 
		 */
		var map3 = new HashMap<>() { // anonymous class
			int someVar;
		};
		
		/*
		 * Here, when the diamond operator is used with anonymous class type, compiler cannot infer the RHS expression to any specific type. This leads 
		 * to a formation of non-denotable Type. 
		 * 
		 * - Firstly, compiler will get denotable type by using the super type for HashMap<>, which is HashMap<Object, Object>. 
		 * - Secondly, the anonymous class extension is applied. 
		 * - Finally this becomes a Non-denotable type which gets assigned to map3. 
		 * 
		 * A special case of Non Denotable type which was not possible to create earlier in Java, can now be created. Anonymously extending an Object class 
		 * and adding attributes within it creates a POJO like class which can be assigned to a variable to hold context. This can be very useful in using 
		 * a dynamically created object which can have structure within a temporary context. 
		 */
		// Special Case : Non Denotable Type
		var person = new Object() {
			
			class Name {
				String firstName;
				String lastName;
				public Name(String firstName, String lastName) {
					super();
					this.firstName = firstName;
					this.lastName = lastName;
				}
				public String getFirstName() {
					return firstName;
				}
				public void setFirstName(String firstName) {
					this.firstName = firstName;
				}
			}
			
			Name name;
			Actor actor;
			public String displayName() {
				return name.getFirstName() + " " + name.lastName;
			}
		};
		
		person.name = person.new Name("Rakesh", "Kumar");
		
		System.out.println(person.displayName());
	}
	

	public static void var() {
		var var = 5;
		System.out.println(var);
	}

	private static class Actor {}
}
