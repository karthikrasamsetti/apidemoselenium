package org.api.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.payload.Booking;
import org.api.payload.BookingDates;
import org.api.payload.User;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Base {
    private static final Logger logger = LogManager.getLogger(Base.class.getName());
    public static ExtentReports extent;
    public User userPayload;
    public Booking booking;
    public Faker faker;
    public static ExtentTest test;
    ObjectMapper objectMapper = new ObjectMapper();
    String json;

    @BeforeTest
    public void setUp() {
        logger.info("Report Setup in each Test");
        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/Extentreport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");
        spark.config().setReportName("Test Report");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @BeforeClass
    public void setupData() throws JsonProcessingException {
        faker = new Faker();

        userPayload = new User();
        booking = new Booking();

        booking.setFirstname(faker.name().username());
        booking.setLastname(faker.name().lastName());
        booking.setAdditionalNeeds(faker.lorem().sentence(5));
        String checkin = "2024-02-20";
        String checkout = "2024-02-25";
        booking.setBookingDates(new BookingDates(checkin, checkout));
        booking.setTotalPrice(faker.number().randomDigit());
        booking.setDepositPaid(faker.bool().bool());
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        logger.info(userPayload);
        logger.info(booking);
    }

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getThrowable() != null) {
            Throwable throwable = result.getThrowable();
            StringWriter error = new StringWriter();
            throwable.printStackTrace(new PrintWriter(error));
            logger.error(error);
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        logger.info("Report Result in each Test");
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, "Test Case Failed");
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed Sucessfully");
        } else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }
}
