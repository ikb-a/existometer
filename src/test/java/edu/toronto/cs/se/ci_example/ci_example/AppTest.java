package edu.toronto.cs.se.ci_example.ci_example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.existometer.NameAndEmail;
import edu.toronto.cs.se.existometer.sources.NameEmailOnGithub;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	NameEmailOnGithub neog = new NameEmailOnGithub();
    	NameAndEmail mrl = new NameAndEmail("Michael Layzell", "michael@thelayzells.com");
    	try {
    		Opinion<Boolean, Double> opinion = neog.getOpinion(mrl);
			System.out.println(opinion.getValue());
		} catch (UnknownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue( true );
    }
}
