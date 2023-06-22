package api.utilities;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class ExtentReportManager implements ITestListener {

	    public ExtentReports extent;
	    public ExtentTest test;//for making entries in report
	    
	    String repName;

	   
	    public void onStart(ITestContext testContext) {
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        repName="Test-Report-"+timestamp + ".html";
	        
	       
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
	        //ExtentSparkReporter is used for UI for report
	        
	        sparkReporter.config().setDocumentTitle("RestAssuredProject");
	        sparkReporter.config().setReportName("Pet Store User API");
	        
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "Pest Store Users API");
	        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	        extent.setSystemInfo("Environment", "QA");
	    }

	   
	    public void onFinish(ITestContext iTestContext) {
	        extent.flush();
	    }

	  
//	    public void onTestStart(ITestResult result) {
//	        test = extent.createTest(result.getName());
//	        test.assignCategory(result.getMethod().getGroups());
//	        test.createNode(result.getName());
//	        test.log(Status.PASS, "Test Passed");
//	    }

	   
	    public void onTestSuccess(ITestResult result) {
	    	  test = extent.createTest(result.getName());
		        test.assignCategory(result.getMethod().getGroups());
		        test.createNode(result.getName());
		        test.log(Status.PASS, "Test Passed");
	    }

	   
	    public void onTestFailure(ITestResult result) {
	    	test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.createNode(result.getName());
	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, result.getThrowable().getMessage());
	    	
	    }

	   
	    public void onTestSkipped(ITestResult result) {
	    	test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.createNode(result.getName());
	        test.log(Status.SKIP, "Test Skipped");
	        test.log(Status.SKIP, result.getThrowable().getMessage());
	    }

//		public void onFinish1(ITestContext testCOntext) {
//			extent.flush();
//		
//			
//		}


		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	


}
