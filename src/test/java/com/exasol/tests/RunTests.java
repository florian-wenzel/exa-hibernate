package com.exasol.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import junit.framework.TestCase;


@RunWith(Suite.class)
@SuiteClasses({ExasolMapping.class, ExasolChar.class, ExasolBits.class, ExasolDate.class, ExasolMath.class})
public class RunTests extends TestCase
{
 
}
