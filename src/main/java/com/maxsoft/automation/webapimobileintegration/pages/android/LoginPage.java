package com.maxsoft.automation.webapimobileintegration.pages.android;

import com.maxsoft.automation.webapimobileintegration.common.AndroidAppBase;
import com.maxsoft.automation.webapimobileintegration.util.AndroidDriverSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;


/**
 * Created by Osanda on 03/02/2018.
 */


public class LoginPage extends AndroidAppBase {

    @FindBy(xpath = "//android.widget.TextView[@text='Login']")
    public WebElement LBL_LOGIN_PAGE_TITLE;
    @FindBy(id = "com.pearsoned.smartflashcards:id/editTextUserName")
    public WebElement TXT_USERNAME;
    @FindBy(id = "com.pearsoned.smartflashcards:id/editTextPassword")
    public WebElement TXT_PASSWORD;
    @FindBy(id = "com.pearsoned.smartflashcards:id/buttonSignIn")
    public WebElement BTN_SIGN_IN;
    @FindBy(id = "android:id/alertTitle")
    public WebElement ALERT_TITLE;
    @FindBy(id = "android:id/message")
    public WebElement ALERT_MESSAGE;
    @FindBy(id = "android:id/button1")
    public WebElement BTN_ALERT_OK;
    @FindBy(id = "com.pearsoned.smartflashcards:id/buttonCreateAccount")
    public WebElement BTN_CREATE_ACCOUNT;
    @FindBy(id = "com.pearsoned.smartflashcards:id/toolbar")
    public WebElement TOOL_BAR;
    @FindBy(id = "com.pearsoned.smartflashcards:id/button_skip")
    public WebElement BTN_WELCOME_SKIP;
    @FindBy(id = "com.pearsoned.smartflashcards:id/textView_gotIt")
    public WebElement BTN_GOT_IT;

    private static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    private static final String LOGIN_ERROR_TITLE = "Authentication Failed";
    private static final String LOGIN_ERROR_MESSAGE = "invalid username or password";
    private static final String LOGIN_PAGE_TITLE = "Login";

    public LoginPage() {
        PageFactory.initElements(AndroidDriverSetup.androidDriver, this);
    }

    public void isLoginPageTitleEquals(String pageTitle) throws IOException {
        isPageTitleEquals(LBL_LOGIN_PAGE_TITLE, pageTitle);
    }

    public void login(String username, String password) throws IOException {
        setTextAs(TXT_USERNAME, username);
        setTextAs(TXT_PASSWORD, password);
        tap(BTN_SIGN_IN);
    }

    public void loginByDataStoreValues(String dataStoreType, String usernameVariableName, String passwordVariableName) throws IOException {
        if (dataStoreType.toLowerCase().equals("scenario")){
            setTextAs(TXT_USERNAME, getScenarioDataStoreValue(usernameVariableName));
            setTextAs(TXT_PASSWORD, getScenarioDataStoreValue(passwordVariableName));
        } else {
            setTextAs(TXT_USERNAME, getSpecificationDataStoreValue(usernameVariableName));
            setTextAs(TXT_PASSWORD, getSpecificationDataStoreValue(passwordVariableName));
        }
        tap(BTN_SIGN_IN);
    }

    public void clearTextFields() throws IOException {
        clearText(TXT_USERNAME);
        clearText(TXT_PASSWORD);
    }

    public void skipWelcomeFlow() throws IOException {
        tap(BTN_WELCOME_SKIP);
    }

    public void tapGotItButton() throws IOException {
        tap(BTN_GOT_IT);
    }

    public void isLandingPageTitleEquals(String pageTitle) throws IOException {
        isPageTitleEquals(TOOL_BAR, pageTitle);
    }

    public void isLoginFail() throws IOException {
        isElementNameEquals(ALERT_TITLE, LOGIN_ERROR_TITLE);
        isElementNameEquals(ALERT_MESSAGE, LOGIN_ERROR_MESSAGE);
        tap(BTN_ALERT_OK);
        isPageTitleEquals(LBL_LOGIN_PAGE_TITLE, LOGIN_PAGE_TITLE);
    }

    public void navigateToCreateAccount() throws IOException {
        tap(BTN_CREATE_ACCOUNT);
    }

    public void isCreateAccountButtonVisible() throws IOException {
        isElementTextEquals(BTN_CREATE_ACCOUNT, CREATE_ACCOUNT_BUTTON_LABEL);
    }


}
