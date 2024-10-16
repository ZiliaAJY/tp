package seedu.budgetbuddy.transaction.expense;

import seedu.budgetbuddy.Ui;

import java.time.YearMonth;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages a list of expenses, providing functionalities to add, delete,
 * and list expenses, as well as tracking the total number of expenses.
 */
public class ExpenseManager {
    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(ExpenseManager.class.getName());

    /**
     * Construct a ExpenseManager of array content incomes
     *
     * @param expenses is the content to be instantiated
     */
    public ExpenseManager(ArrayList<Expense> expenses, int numberOfExpenses) {
        assert numberOfExpenses >= 0: "numberOfExpenses should be greater than 0";
        ExpenseManager.expenses = expenses;
        ExpenseManager.numberOfExpenses = numberOfExpenses;
    }

    /**
     * Adds a new expense to the manager.
     *
     * @param expense The expense to be added.
     */
    public static void addExpense(Expense expense) {
        expenses.add(expense);
        numberOfExpenses++;
        Ui.displayAcknowledgmentMessage(expense.toString(), "added", "expense", numberOfExpenses);
    }

    /**
     * Deletes an expense from the manager at the specified index.
     *
     * @param index The index of the expense to be deleted.
     */
    public static void deleteExpense(int index) {
        numberOfExpenses--;
        Ui.displayAcknowledgmentMessage(expenses.get(index).toString(), "deleted", "expense", numberOfExpenses);
        expenses.remove(index);
    }

    /**
     * Returns the current number of expenses.
     *
     * @return The total number of expenses.
     */
    public static int getNumberOfExpenses() {
        return numberOfExpenses;
    }

    /**
     * Lists all the expenses managed by the manager.
     * Displays each expense with its corresponding number.
     */
    public static void listExpenses() {
        String result = "";
        int counter = 1;
        for (Expense expense : expenses) {
            result += counter + ". " + expense.toString() + "\n";
            counter++;
        }
        LOGGER.log(Level.INFO, "Listing {0} expenses", numberOfExpenses);
        Ui.displayToUser(result);
    }

    /**
     * Display all expense that matches with month & category field
     * Displays each expense with its corresponding number.
     * @param category
     * @param month
     * @return result String to be displayed to user
     */
    public static String displayExpensesWithCategoryAndDate(Category category, YearMonth month) {
        assert category != null : "category cannot be null";
        assert month != null : "month cannot be null";
        String result = "";
        int counter = 1;
        for (Expense expense : expenses) {
            if(category.equals(expense.getCategory()) && month.equals(getYearMonthFromDate(expense.getDate()))) {
                result += counter + ". " + expense.toString() + "\n";
                counter++;
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        }
        return result;
    }

    /**
     * Display all expense that matches with category field
     * Displays each expense with its corresponding number.
     * @param category
     * @return result String to be displayed to user
     */
    public static String displayExpensesWithCategory(Category category) {
        assert category != null : "category cannot be null";
        String result = "";
        int counter = 1;
        for (Expense expense : expenses) {
            if(category.equals(expense.getCategory())) {
                result += counter + ". " + expense.toString() + "\n";
                counter++;
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        }
        return result;
    }

    /**
     * Display all expense that matches with month field
     * Displays each expense with its corresponding number.
     * @param month
     * @return result String to be displayed to user
     */
    public static String displayExpensesWithDate(YearMonth month) {
        assert month != null : "month cannot be null";
        String result = "";
        int counter = 1;
        for (Expense expense : expenses) {
            if(month.equals(getYearMonthFromDate(expense.getDate()))) {
                result += counter + ". " + expense.toString() + "\n";
                counter++;
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        }
        return result;
    }

    /**
     * Filters expenses with descriptions that contain the keyword(s) provided by user.
     * @param keyword
     * @return result String displayed to user
     */
    public static String searchExpenses(String keyword){
        String result = "";
        if (keyword.equals("")){
            result = getEmptyDisplayMessage();
            return result;
        }
        int counter = 1;
        for (Expense expense : expenses) {
            if (expense.getDescription().toLowerCase().contains(keyword.toLowerCase())){
                result += counter + ". " + expense.toString() + "\n";
                counter++;
            }
        }
        if (result.equals("")){
            result = getEmptyDisplayMessage();
        }
        return result;
    }

    /**
     * Extract YearMonth value from date
     * @param date
     * @return
     */
    public static YearMonth getYearMonthFromDate(LocalDate date) {
        return YearMonth.from(date);
    }

    /**
     * Generates a custom empty Display Expense message
     * @return custom display Expense message
     */
    public static String getEmptyDisplayMessage() {
        return "No expense entry with given parameters found, try again with a different parameter.";
    }

    /**
     * A get-function to obtain the information in the current Expense List.
     *
     * @return return the expense ArrayList
     */
    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
