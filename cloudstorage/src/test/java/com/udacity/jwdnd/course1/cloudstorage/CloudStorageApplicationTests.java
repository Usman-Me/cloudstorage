package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private String baseURL;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.baseURL = "http://localhost:" + this.port;
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void getSignupPage() {
        driver.get(baseURL+"/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void getHomePage() {
        driver.get(baseURL+"/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testUserSignupLoginAndLogout() {
        String firstName = "Tester123";
        String lastName = "Testing";
        String username = "JavaTester";
        String password = "Java123";

        driver.get(baseURL + "/signup");
        SignupPageTest signupPage = new SignupPageTest(driver);
        signupPage.signup(firstName, lastName, username, password);

        driver.get(baseURL+"/login");
        LoginPageTest loginPage = new LoginPageTest(driver);
        loginPage.login(username, password);

        Assertions.assertEquals(baseURL+"/home", driver.getCurrentUrl());
        Assertions.assertEquals("Home", driver.getTitle());

        driver.get(baseURL+"/home");
        HomePageTest homePage = new HomePageTest(driver);
        homePage.logout();

        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testNotesCRUD() {
        this.authenticateTestUser();
        driver.get(baseURL+"/home");
        HomePageTest homePage = new HomePageTest(driver);

        try {
            // Ein neues Note erstellen
            Note newNote = homePage.createNote("Test", "This is just for a test");
            Assertions.assertEquals("Test", newNote.getNoteTitle());
            Assertions.assertEquals("This is just for a test", newNote.getNoteDescription());

            // Ein Note updaten
            Note updatedNote = homePage.updateNote("TestNote", "Test123");
            Assertions.assertEquals("TestaTestNote", updatedNote.getNoteTitle());
            Assertions.assertEquals("This is just for a testaTest123", updatedNote.getNoteDescription());

            //  Ein Note löschen
            homePage.deleteNote();
            Assertions.assertTrue(homePage.isNoteDeleted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCredentialsCRUD() {
        this.authenticateTestUser();
        driver.get(baseURL+"/home");
        HomePageTest homePage = new HomePageTest(driver);
        String url = "gmx.de";
        String username = "JavaTester";
        String password = "Java123";

        try {
            // Neues Credential Verifizieren
            homePage.createCredential(url, username, password);
            Assertions.assertTrue(homePage.isCredentialCreated(url, username));
            Assertions.assertTrue(homePage.isPasswordEncrypted(password));

            // Credential Updaten
            homePage.updateCredential("JavaTester", "Test123");
            Assertions.assertFalse(homePage.isCredentialUpdated("JavaTester"));

            // Credential Löschen
            homePage.deleteCredential();
            Assertions.assertTrue(homePage.isCredentialDeleted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void authenticateTestUser() {
        driver.get(baseURL + "/signup");
        SignupPageTest signupPage = new SignupPageTest(driver);
        signupPage.signup("Tester123", "Testing", "JavaTester", "Java123");

        driver.get(baseURL+"/login");
        LoginPageTest loginPage = new LoginPageTest(driver);
        loginPage.login("JavaTester", "Java123");
    }

}
