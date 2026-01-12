package framework.runner;

import framework.execution.Execution;
import framework.printer.Printer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtendedTestAutomationRunner extends TestAutomationRunner {
    private final Set<Printer> printers = new HashSet<>();

    public ExtendedTestAutomationRunner(Printer firstPrinter) {
        super(firstPrinter);
        this.printers.add(firstPrinter);
    }

    public void addPrinter(Printer printer) {
        if (printer != null) {
            this.printers.add(printer);
        }
    }

    @Override
    public void run(List<Class<?>> testClasses) {
        // Выполняем тесты (используем логику базового класса)
        List<Execution> executions = testClasses.stream()
                .map(TestAutomationRunner::runTestClass) // предполагаем доступ или копируем логику
                .toList();

        // Печатаем результаты во все зарегистрированные принтеры
        for (Printer printer : printers) {
            printer.write(executions);
        }
    }
}