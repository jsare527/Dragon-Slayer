import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StepDefinitions extends ApplicationTest {
    private Controller controller;

    @Before
    public void setUp () throws Exception {
        ApplicationTest.launch(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        fxmlLoader.load();
        controller = fxmlLoader.getController();
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    //#region Customer Functions
    @Given("I click on Customers tab")
    public void i_click_on_customers_tab()
    {
        clickOn("Customers");
    }

    //#region Customer Buttons and Inputs
    @When("I add customers$")
    public void i_add_customers(DataTable args) throws Throwable
    {
        List<Customer> customers = createCustomerList(args);

        for(Customer c : customers)
        {
            clickOn("#addCustomerButtonMain");
            clickOn("#newCustomerFirstName");
            write(c.getFirstName());
            clickOn("#newCustomerLastName");
            write(c.getLastName());
            clickOn("#newCustomerPhone");
            write(c.getPhone());
            clickOn("#newCustomerEmail");
            write(c.getEmail());
            clickOn("#newCustomerNotes");
            write(c.getNotes());
            clickOn("#addCustomerButton");
        }
    }

    @When("I update add customer with phone: {string}")
    public void i_update_add_customer_with_phone(String phoneNumber)
    {
        clickOn("#newCustomerPhone");
        write(phoneNumber);
        clickOn("#addCustomerButton");
    }

    @When("I update add customer with full name: {string}")
    public void i_update_add_customer_with_full_name(String fullname)
    {
        String[] name = fullname.split(" ");
        clickOn("#newCustomerFirstName");
        write(name[0]);
        clickOn("#newCustomerLastName");
        write(name[1]);
        clickOn("#addCustomerButton");
    }

    @When("I edit customers$")
    public void i_edit_customers(DataTable args)
    {
        List<Customer> customers = createCustomerList(args);

        for(Customer c : customers)
        {
            clickOn(c.getLastName());
            clickOn("#editCustomerButton");
            doubleClickOn("#updateCustomerFirstName");
            write(c.getFirstName());
            doubleClickOn("#updateCustomerLastName");
            write(c.getLastName());
            doubleClickOn("#updateCustomerPhone");
            clickOn("#updateCustomerPhone");
            write(c.getPhone());
            doubleClickOn("#updateCustomerEmail");
            clickOn("#updateCustomerEmail");
            write(c.getEmail());
            doubleClickOn("#updateCustomerNotes");
            write(c.getNotes());
            clickOn("#updateCustomerButton");
        }
    }

    @When("I delete customers$")
    public void i_delete_customers(DataTable args) throws Throwable
    {
        List<Customer> customers = createCustomerList(args);

        for(Customer c : customers)
        {
            clickOn(c.getFirstName());
            clickOn("#deleteCustomerButton");
            clickOn("#yesButton");
        }
    }

    @When("I search customers: {string}")
    public void i_search(String search)
    {
        clickOn("#CustomerSearch");
        write(search);
    }

    @When("I mark customer delinquent: {string}")
    public void i_mark_customer_delinquent(String customer)
    {
        clickOn(customer);
        clickOn("#Delinq");
    }

    //#endregion

    //#region Customer Data Validation
    @Then("I should see customers$")
    public void i_should_see_customers(DataTable args) throws Throwable
    {
        List<Customer> customers = createCustomerList(args);
        List<Customer> actualCustomers = controller.getCustomerList();

        // Check for same number of customers
        assertEquals(customers.size(), actualCustomers.size());
        // Check customers were saved correctly in DB
        for (int i = 0; i < customers.size(); i++)
        {
            assertTrue(customers.get(i).equals(actualCustomers.get(i)));
        }
        // Check each of the customers shows on the application
        for (Customer c : customers)
        {
            clickOn(c.getLastName());
        }
    }

    @Then("I should see and close error message: {string}")
    public void i_should_see_and_close_duplicate_customer_entry(String errorMessage)
    {
        List<Window> x = robotContext().getWindowFinder().listWindows();
        int pos = robotContext().getWindowFinder().listWindows().size();
        Window duplicate = robotContext().getWindowFinder().listWindows().get(pos - 1);
        Stage s = (Stage) duplicate;
        assertEquals(errorMessage, s.getTitle());
        clickOn("OK");
    }

    @Then("I should only see customers$")
    public void i_should_only_see_customers(DataTable args)
    {
        List<Customer> customers = createCustomerList(args);

        for(Customer c : customers) {
            clickOn(c.getLastName());
        }
    }

    @Then("I should see delinquent on customer: {string}")
    public void i_should_see_delinquent_on_customer(String customer)
    {
        clickOn(customer);
        clickOn("#delinqNoticeText");
    }

    @Then("I should not see delinquent on customer: {string}")
    public void i_should_not_see_delinquent_on_customer(String customer)
    {
        clickOn(customer);
        try {
            clickOn("#delinqNoticeText");
        }
        catch (FxRobotException e)
        {
            assertEquals("the query \"#delinqNoticeText\" returned 1 nodes, but no nodes were visible.", e.getMessage());
        }
    }

    //#endregion

    //#endregion

    private List<Customer> createCustomerList(DataTable t)
    {
        List<Customer> customers = new ArrayList<>();
        List<List<String>> rows = t.asLists(String.class);

        for (List<String> columns : rows.subList(1, rows.size())) {
            customers.add(
                    new Customer(Objects.toString(columns.get(0), ""),
                            Objects.toString(columns.get(1), ""),
                            Objects.toString(columns.get(2), ""),
                            Objects.toString(columns.get(3), ""),
                            Objects.toString(columns.get(4), ""), false));
        }
        return customers;
    }
}
