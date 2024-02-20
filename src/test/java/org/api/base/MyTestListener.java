package org.api.base;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(MyTestListener.class.getName());


    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: " + result.getName());
        Allure.step("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getName());
        Allure.step("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: " + result.getName());
        Allure.step("Test Failed: " + result.getName());
        // You can perform additional actions on test failure
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test Skipped: " + result.getName());
        Allure.step("Test Skipped: " + result.getName());
    }

}
