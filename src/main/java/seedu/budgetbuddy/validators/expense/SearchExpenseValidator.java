package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.expense.SearchExpenseCommand;

public class SearchExpenseValidator {
    /**
     * Validates command input by user to see if string input has a keyword provided as a descriptor
     * @param command
     * @return new SearchExpenseCommand object.
     */
    public static Command processCommand(String command){
        assert command != null: "Command cannot be null";
        if (command.equals("search expense")){
            return new SearchExpenseCommand();
        }
        String trimmedCommand = command.substring("search expense ".length());
        return new SearchExpenseCommand(trimmedCommand);
    }
}