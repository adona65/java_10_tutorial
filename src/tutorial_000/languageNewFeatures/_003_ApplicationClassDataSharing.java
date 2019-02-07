package tutorial_000.languageNewFeatures;

public class _003_ApplicationClassDataSharing {
	/*
	 * This feature helps in improving the startup footprint, extends the existing Class-Data Sharing (“CDS”) feature to allow application 
	 * classes to be placed in the shared archive.
	 * 
	 * JVM while starting performs some preliminary steps, one of which is loading classes in memory. If there are several jars having multiple 
	 * classes, then the lag in the first request is clearly visible. This becomes an issue with serverless architecture, where boot time is critical. 
	 * In order to bring down application startup time, Application class-data sharing can be used. The idea is to reduce footprint by sharing common 
	 * class metadata across different Java processes. This can be achieved by the following 3 steps :
	 * 
	 * - Determining the classes to archive : Use the java launcher to create a list of files to archive :
	 * 		java -Xshare:off -XX:+UseAppCDS -XX:DumpLoadedClassList=hello.lst -cp hello.jar HelloWorld
	 * 
	 * - Creating the AppCDS archive : Use java launcher to create the archive of the list of files to be used for Application CDS : 
	 * 		java -Xshare:dump -XX:+UseAppCDS -XX:SharedClassListFile=hello.lst -XX:SharedArchiveFile=hello.jsa -cp hello.jar
	 * 
	 * - Using the AppCDS archive : Use Java launcher with the following parameters to use Application CDS :
	 * 		java -Xshare:on -XX:+UseAppCDS -XX:SharedArchiveFile=hello.jsa -cp hello.jar HelloWorld
	 */
}
