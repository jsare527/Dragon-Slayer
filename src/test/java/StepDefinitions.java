import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    //#region Customer Buttons and Inputs
    @When("I click on Customers tab")
    public void i_click_on_customers_tab() throws InterruptedException
    {
        clickOn("Customers");
        TimeUnit.MILLISECONDS.sleep(20);
    }

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
            clickOn(c.getLastName());
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

    @Then("I should see and close message: {string}")
    public void i_should_see_and_close_message(String message)
    {
        List<Window> x = robotContext().getWindowFinder().listWindows();
        int pos = robotContext().getWindowFinder().listWindows().size();
        Window duplicate = robotContext().getWindowFinder().listWindows().get(pos - 1);
        Stage s = (Stage) duplicate;
        assertEquals(message, s.getTitle());
        clickOn("OK");
    }

    @Then("I should only see customers$")
    public void i_should_only_see_customers(DataTable args)
    {
        List<Customer> customers = createCustomerList(args);
        List<Customer> totalCustomers = controller.getCustomerList();

        // Check expected result shows on application
        for(Customer c : customers) {
            clickOn(c.getLastName());
        }

        // Check other customers are not shown
        for (Customer c : totalCustomers)
        {
            if (!customers.contains(c))
            {
                try {
                    clickOn(c.getLastName());
                }
                catch (FxRobotException e)
                {
                    assertEquals("the query \"" + c.getLastName() + "\" returned no nodes.", e.getMessage());
                }
            }
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

    //#region Title Functions

    //#region Title Buttons and Inputs

    @When("I click on Titles tab")
    public void i_click_on_titles_tab()
    {
        clickOn("Titles");
    }

    @When("I add titles$")
    public void i_add_titles(DataTable args)
    {
        List<Title> titles = createTitleList(args);

        for (Title t : titles)
        {
            clickOn("#addTitleButtonMain");

            clickOn("#newTitleTitle");
            write(t.getTitle());
            clickOn("#newTitleProductId");
            write(t.getProductId());
            clickOn("#newTitlePrice");
            write(t.getPriceDollars());
            clickOn("#newTitleNotes");
            write(t.getNotes());

            clickOn("#addTitleButton");
        }
    }

    @When("I edit titles$")
    public void i_edit_titles(DataTable args)
    {
        List<Title> titles = createTitleList(args);

        for(Title t : titles)
        {
            clickOn(t.getTitle());
            clickOn("#editTitleButton");

            doubleClickOn("#updateTitleTitle");
            write(t.getTitle());
            doubleClickOn("#updateTitleProductId");
            write(t.getProductId());
            doubleClickOn("#updateTitlePrice");
            write(t.getPriceDollars());
            doubleClickOn("#updateTitleNotes");
            write(t.getNotes());

            clickOn("#updateTitleButton");
        }
    }

    @When("I delete titles$")
    public void i_delete_titles(DataTable args)
    {
        List<Title> titles = createTitleList(args);

        for(Title t : titles)
        {
            clickOn(t.getTitle());
            clickOn("#deleteTitleButton");
            clickOn("#yesButton");
        }
    }

    @When("I search titles: {string}")
    public void i_search_titles(String title)
    {
        clickOn("#TitleSearch");
        write(title);
        write('\n');
    }

    @When("I update add title with title: {string}")
    public void i_update_add_title_with_title(String title)
    {
        doubleClickOn("#newTitleTitle");
        write(title);
        clickOn("#addTitleButton");
    }

    @When("I flag title: {string}")
    public void i_flag_title(String title)
    {
        i_search_titles(title);
        for (int i = 0; i < 6; i++)
        {
            press(KeyCode.TAB);
            release(KeyCode.TAB);
        }
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        push(new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN));
        clickOn("#saveFlagsButton");
    }

    @When("I click release flags")
    public void i_click_release_flags()
    {
        clickOn("#resetFlagsButton");
    }
    //#endregion

    //#region Titles Data Validation
    @Then("I should see titles$")
    public void i_should_see_titles(DataTable args)
    {
        List<Title> titles = createTitleList(args);
        List<Title> actualTitles = controller.getTitlesList();

        // Check for same number of customers
        assertEquals(titles.size(), actualTitles.size());
        // Check customers were saved correctly in DB
        for (int i = 0; i < titles.size(); i++)
        {
            assertTrue(titles.get(i).equals(actualTitles.get(i)));
        }
        // Check each of the customers shows on the application
        for (Title t : titles)
        {
            clickOn(t.getTitle());
        }
    }

    @Then("I should only see titles$")
    public void i_should_only_see_titles(DataTable args)
    {
        List<Title> titles = createTitleList(args);
        List<Title> totalTitles = controller.getTitlesList();

        // Check expected result shows on application
        for (Title t : titles)
        {
            clickOn(t.getTitle());
        }

        // Check other titles are not shown
        for (Title t : totalTitles)
        {
            if (!titles.contains(t))
            {
                try {
                    clickOn(t.getTitle());
                }
                catch (FxRobotException e)
                {
                    assertEquals("the query \"" + t.getTitle() + "\" returned no nodes.", e.getMessage());
                }
            }
        }
    }

    @Then("I should see title is flagged: {string}")
    public void i_should_see_title_is_flagged(String title)
    {
        List<Title> flagged = controller.getFlaggedList();
        assertTrue(flagged.stream().filter(t -> t.getTitle().equals(title)).count() > 0);
    }

    @Then("I should see title is not flagged: {string}")
    public void i_should_see_title_is_not_flagged(String title)
    {
        List<Title> flagged = controller.getFlaggedList();
        assertEquals(flagged.stream().filter(t -> t.getTitle().equals(title)).count(), 0);
    }
    //#endregion

    //#endregion

    //#region Order Function

    //#region Order Buttons
    @When("I add requests for last name: {string}")
    public void i_add_requests_for_last_name(String customer, DataTable args)
    {
        clickOn(customer);
        List<Order> orders = createOrderList(args);
        for (Order o : orders)
        {
            clickOn("#newOrderButton");
            write(o.getTitleName());
            press(KeyCode.TAB);
            release(KeyCode.TAB);
            write(String.valueOf(o.getIssue()));
            press(KeyCode.TAB);
            release(KeyCode.TAB);
            write(String.valueOf(o.getQuantity()));
            clickOn("#addOrderButton");
        }
    }

    @When("I edit requests for last name: {string}")
    public void i_edit_requests_for_last_name(String customer, DataTable args)
    {
        clickOn(customer);
        List<Order> orders = createOrderList(args);
        for (Order o : orders)
        {
            clickOn("#editOrderButton");
            write(o.getTitleName());
            press(KeyCode.TAB);
            release(KeyCode.TAB);
            write(String.valueOf(o.getIssue()));
            press(KeyCode.TAB);
            release(KeyCode.TAB);
            write(String.valueOf(o.getQuantity()));
            clickOn("#updateOrderButton");
        }
    }

    @When("I delete requests for last name: {string}")
    public void i_delete_requests_for_last_name(String customer, DataTable args)
    {
        // Currently deletes the number of orders as it cant choose specific titles
        clickOn(customer);
        List<Order> orders = createOrderList(args);
        for (Order o : orders)
        {
            clickOn("#customerOrderReqItemsColumn");
            press(KeyCode.UP);
            release(KeyCode.UP);
            clickOn("#deleteOrderButton");
            clickOn("#yesButton");
        }
    }
    //#endregion

    //#region Order Validation
    @Then("I should see orders for last name: {string}")
    public void i_should_see_orders_for_last_name(String customer, DataTable args)
    {
        clickOn(customer);
        List<Order> orders = createOrderList(args);
        List<Order> actualOrders = controller.getOrderListForCustomer(customer);

        // Check orders were saved properly in database
        assertEquals(orders.size(), actualOrders.size());
        for (int i = 0; i < orders.size(); i++)
        {
            Order o1 = orders.get(i);
            Order o2 = actualOrders.get(i);
            assertEquals(o1.getTitleName(), o2.getTitleName());
            assertEquals(o1.getIssue(), o2.getIssue());
            assertEquals(o1.getQuantity(), o2.getQuantity());
        }

        // Check that orders show on application
        for (Order o : orders)
        {
            clickOn(o.getTitleName());
        }
    }

    @Then("I should see orders for title: {string}")
    public void i_should_see_orders_for_title(String title, DataTable args)
    {
        clickOn(title);
        List<RequestTable> orders = createRequestTable(args);
        List<RequestTable> actualOrders = controller.getOrderListForTitle(title);

        // Check orders were saved properly in database
        assertEquals(orders.size(), actualOrders.size());
        for (int i = 0; i < orders.size(); i++)
        {
            orders.get(i).equals(actualOrders.get(i));
        }

        // Check that orders show on application
        for (RequestTable o : orders)
        {
            clickOn(o.getRequestLastName());
        }
    }
    //#endredgion
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

    private List<Title> createTitleList(DataTable t)
    {
        List<Title> titles = new ArrayList<>();
        List<List<String>> rows = t.asLists(String.class);

        for (List<String> columns : rows.subList(1, rows.size())) {
            titles.add(
                    new Title(0,
                            Objects.toString(columns.get(0), ""),
                            Integer.parseInt(dollarsToCents(columns.get(2))),
                            Objects.toString(columns.get(3), ""),
                            Objects.toString(columns.get(1), ""),
                            LocalDate.now()));
        }
        return titles;
    }

    private List<Order> createOrderList(DataTable t)
    {
        List<Order> orders = new ArrayList<>();
        List<List<String>> rows = t.asLists(String.class);
        for (List<String> columns : rows.subList(1, rows.size())) {
            orders.add(
                    new Order(
                            1,
                            1,
                            Objects.toString(columns.get(0), ""),
                            columns.get(1) == null ? 0 : Integer.parseInt(columns.get(1)),
                            columns.get(2) == null ? 0 : Integer.parseInt(columns.get(2))
                    ));
        }
        return orders;
    }

    private List<RequestTable> createRequestTable(DataTable t)
    {
        List<RequestTable> requestTables = new ArrayList<>();
        List<List<String>> rows = t.asLists(String.class);
        for (List<String> columns : rows.subList(1, rows.size())) {
            requestTables.add(
                    new RequestTable(
                            Objects.toString(columns.get(0), ""),
                            Objects.toString(columns.get(1), ""),
                            columns.get(2) == null ? 0 : Integer.parseInt(columns.get(2)),
                            columns.get(3) == null ? 0 : Integer.parseInt(columns.get(3))
                    ));
        }
        return requestTables;
    }

    private String dollarsToCents(String priceDollars) {
        if (priceDollars == "") {
            return null;
        }
        priceDollars = priceDollars.replace(".", "");
        priceDollars = priceDollars.replaceAll(",", "");
        return priceDollars;
    }
}
