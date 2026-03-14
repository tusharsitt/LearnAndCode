# Java Coding Guidelines

## 1. Naming Conventions
*   **Classes and Interfaces:** Use `PascalCase` (UpperCamelCase). Nouns are preferred for classes.
    *   *Example:* `CustomerAccount`, `UserRepository`, `PaymentProcessor`
*   **Methods:** Use `camelCase` (lowerCamelCase). Verbs are preferred.
    *   *Example:* `calculateTotal()`, `getUserName()`, `saveData()`
*   **Variables:** Use `camelCase`. Names should be descriptive and intention-revealing (avoid single-letter variables except in short loops).
    *   *Example:* `orderCount`, `customerName`, `isActive`
*   **Constants:** Use `SCREAMING_SNAKE_CASE` (all uppercase letters separated by underscores). Should be declared as `static final`.
    *   *Example:* `MAX_RETRY_COUNT`, `DEFAULT_TIMEOUT_MS`
*   **Packages:** Use entirely lowercase letters, without underscores or camel case.
    *   *Example:* `com.company.application.billing`

## 2. Formatting & Structure
*   **Indentation:** Use 4 spaces for indentation (avoid using tabs).
*   **Braces:** The opening brace is placed on the same line as the declaration, and the closing brace is on a new line.
    *   *Example:*
        ```java
        public void processOrder(Order order) {
            if (order.isValid()) {
                save(order);
            } else {
                reject(order);
            }
        }
        ```
*   **Line Length:** Limit code lines to 100 or 120 characters to avoid horizontal scrolling.
*   **Blank Lines:** Use blank lines to separate logical sections of code within a method.

## 3. General Best Practices
*   **Immutability:** Use the `final` keyword for variables, fields, and method parameters when their values should not change after initialization.
    *   *Example:* `final String defaultLanguage = "English";`
*   **Avoid Magic Numbers:** Replace hardcoded numbers or strings with named constants to provide context.
    *   *Bad:* `if (status == 2) { ... }`
    *   *Good:* `if (status == STATUS_COMPLETED) { ... }`
*   **Access Modifiers:** Default to the most restrictive access level (`private`) and only widen it (`protected`, `public`) when absolutely necessary.
*   **Declare variables close to use:** Declare local variables exactly where they are first needed, rather than at the top of the method.
*   **Avoid Boolean Parameters:** Boolean parameters make methods harder to understand.
*   **Limit Method Parameters:** Avoid methods with many parameters. If more than 3–4 parameters, consider using an object.
*   **Keep Methods Small:** Methods should perform one logical task. Prefer 5–20 lines per method.

## 4. Exception Handling
*   **Catch Specific Exceptions:** Always catch the most specific exception first rather than using a generic `catch (Exception e)`.
*   **Never Swallow Exceptions:** Avoid leaving catch blocks empty. At a minimum, log the exception.
    *   *Example:*
        ```java
        try {
            readFile(filePath);
        } catch (IOException e) {
            logger.error("Failed to read the configuration file", e);
            throw new ConfigurationException("Unreadable config", e);
        }
        ```

## 5. Comments and Documentation
*   **Javadoc:** Use Javadoc (`/** ... */`) to document the public API: classes, interfaces, and public methods. Describe parameters, return values, and exceptions thrown.
*   **Inline Comments:** Use `//` to explain *why* a particular approach was taken if it isn't obvious. The code itself should be readable enough to explain *what* it is doing.
