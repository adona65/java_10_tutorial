package tutorial_000.languageNewFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class _004_ApiChanges {
	
	public static void main(String[] args) {
		/*
		 * Java 10 removed some APIs : http://cr.openjdk.java.net/~iris/se/10/latestSpec/#APIs-removed
		 * 
		 * It also update or add others : http://cr.openjdk.java.net/~iris/se/10/latestSpec/apidiffs/overview-summary.html
		 * 
		 *  We can see for example that List, Map & Set Interfaces get a static copyOf(Collection) method. It returns an unmodifiable List, Map or Set 
		 *  containing the entries provided. For a List, if the given List is subsequently modified, the returned List will not reflect such modifications.
		 */
		List<String> actors = new ArrayList<>();
		actors.add("Jack Nicholson");
		actors.add("Marlon Brando");		
				
		System.out.println(actors); // prints [Jack Nicholson, Marlon Brando]
		
		System.out.println("-------------------------------------");
		
		// New API added - Creates an UnModifiable List from a List.
		List<String> copyOfActors = List.copyOf(actors);
		System.out.println(copyOfActors); // prints [Jack Nicholson, Marlon Brando]
		
		try {
			copyOfActors.add("Robert De Niro");
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException : can't modify immutable list.");
		}
		
		System.out.println("-------------------------------------");
		
		actors.add("Robert De Niro");
		System.out.println(actors); // prints [Jack Nicholson, Marlon Brando, Robert De Niro]
		
		System.out.println("-------------------------------------");
		
		System.out.println(copyOfActors); // prints [Jack Nicholson, Marlon Brando]
		
		System.out.println("=====================================");
		
		/*
		 * Optional & its primitive variations get a method orElseThrow(). This is exactly same as get(), however the java doc states that it is a 
		 * preferred alternative then get().
		 */
		String str = "1";
		Optional<String> name = Optional.ofNullable(str);
		// New API added - is preferred option then get() method
		System.out.println(name.orElseThrow()); // same as name.get()  
		
		System.out.println("=====================================");
		
		/*
		 * Collectors class gets various methods for collecting unmodifiable collections (Set, List, Map). For example :
		 */
		// New API added - Collectors.toUnmodifiableList
		List<String> collect = actors.stream().collect(Collectors.toUnmodifiableList());
		try {
			collect.add("Tom Hanks");
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException : can't modify immutable list.");
		}
	}

}
