package lab5.commands;

import lab5.exceptions.IncorrectArgumentException;

/**
 * This class represents an abstract command.
 *
 * @see lab5.CommandManager
 * @see Clear
 * @see CountByType
 * @see ExecuteScript
 * @see Exit
 * @see FilterStartsWithName
 * @see Help
 * @see Info
 * @see Insert
 * @see RemoveAnyByPerson
 * @see RemoveGreater
 * @see RemoveKey
 * @see RemoveLower
 * @see RemoveLowerKey
 * @see Save
 * @see Show
 * @see Update
 */

public abstract class Command {
    private final String name;
    private final String description;
    private final String pattern;

    /**
     * This constructor is used for complex commands with the argument.
     * All parameters are used to describe to user this command.
     *
     * @param name command name
     * @param description description of the usage of this command
     * @param pattern usage pattern
     */

    public Command(String name, String description, String pattern) {
        this.name = name;
        this.description = description;
        this.pattern = pattern;
    }

    /**
     * This constructor is used for simple commands without any arguments.
     * All parameters are used to describe to user this command.
     *
     * @param name command name
     * @param description description of the usage of this command
     */

    public Command(String name, String description) {
        this.name = this.pattern = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPattern() {
        return pattern;
    }

    /**
     * The abstract method that executes this command
     *
     * @param argument all implementations have one string argument, but they might not use it (in case of simple command)
     * @throws IncorrectArgumentException thrown if the argument is not correct
     */

    abstract public void execute(String argument) throws IncorrectArgumentException;
}
