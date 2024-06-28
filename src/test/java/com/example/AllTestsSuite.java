// Path: /src/test/java/com/example/AllTestsSuite.java
package com.example;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.example")
@IncludeClassNamePatterns({"^.*Test$"})
public class AllTestsSuite {
}
