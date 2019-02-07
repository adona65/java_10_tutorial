package tutorial_000.languageNewFeatures;

public class _001_TimeBasedReleaseVersioning {
	public static void main(String[] args) {
		/*
		 * With adoption of time based release cycle, Oracle changed the version-string scheme of the Java SE Platform and the JDK, and related versioning 
		 * information, for present and future time-based release models. The new pattern of the Version number is : $FEATURE.$INTERIM.$UPDATE.$PATCH
		 * 
		 * - $FEATURE : counter will be incremented every 6 months and will be based on feature release versions, e.g: JDK 10, JDK 11.
		 * - $INTERIM : counter will be incremented for non-feature releases that contain compatible bug fixes and enhancements but no incompatible changes. 
		 * 		Usually this will be zero, as there will be no interim release in a six month period. This kept for future revision to the release model.
		 * - $UPDATE : counter will be incremented for compatible update releases that fix security issues, regressions, and bugs in newer features. This is 
		 * 		updated one month after the feature release and every 3 months thereafter. The April 2018 release is JDK 10.0.1, the July release is JDK 10.0.2, 
		 * 		and so on. 
		 * - $PATCH : counter will be incremented for an emergency release to fix a critical issue.
		 * 
		 * New API's have been added to get these counter values programmatically :
		 */
		java.lang.Runtime.Version version = Runtime.version();
		System.out.println(version.feature());
		System.out.println(version.interim());
		System.out.println(version.update());
		System.out.println(version.patch());
		
		/*
		 * We also can get informations in command line via "java -version". It will display for example :
		 "
		 	java version "10" 2018-03-20
			Java(TM) SE Runtime Environment 18.3 (build 10+46)
			Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10+46, mixed mode)
		 "
		 *
		 * The version number format is "10" as there's no other counter which is other than zero. The date of release is added. 18.3 can be read as Year 
		 * 2018 & 3rd Month, build 10+46 is 46th build for version 10. For a hypothetical build 93 of JDK 10.0.1, the build would be 10.0.1+93.
		 */
	}
}
