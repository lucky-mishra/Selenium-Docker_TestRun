package reusableComponents;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TeslinkEngine {

    public static String testProject = "Gmail";
    public static String testPlan = "SampleTestPlan";
    public static String testCase = "GmailLogin1";
    public static String build = "SampleBuild";
    public static String notes = null;
    public static String result = null;

    public static String DEVKEY = "a979258effb5ad0f6508fc7ab6bd3e73";
    public static String URL = "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";

    public static void reportResult(String TestProject, String TestPlan, String Testcase, String Build, String Notes, String Result) throws TestLinkAPIException, TestLinkAPIException {
        TestLinkAPIClient api = new TestLinkAPIClient(DEVKEY, URL);
        api.reportTestCaseResult(testProject, testPlan, testCase, build, notes, result);
    }
}
