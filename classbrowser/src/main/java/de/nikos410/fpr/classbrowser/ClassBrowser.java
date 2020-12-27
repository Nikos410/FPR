package de.nikos410.fpr.classbrowser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassBrowser implements AutoCloseable {
    private static final String LINE_BREAK = "\n";
    private static final String INDENT = "    ";

    private final InputStreamReader stdInReader = new InputStreamReader(System.in);
    private final BufferedReader bufferedStdInReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try (final ClassBrowser classBrowser = new ClassBrowser()) {
            classBrowser.run();
        } catch (IOException e) {
            throw new IllegalStateException("Could not close input reader.", e);
        }
    }

    public void run() {
        while (true) {
            final String input = readLine("Please enter the qualified class name or 'exit' to exit.");
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Goodbye.");
                break;
            } else {
                printClassInformation(input);
            }
        }
    }


    private void printClassInformation(String qualifiedClassName) {
        try {
            final Class<?> theClass = Class.forName(qualifiedClassName);
            printClassInformation(theClass);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found. Please try again.");
        }
    }

    private void printClassInformation(Class<?> theClass) {
        final String formattedClassInformation = formatClassInformation(theClass);
        System.out.println(formattedClassInformation);
    }

    private String formatClassInformation(Class<?> theClass) {
        return formatClassHeader(theClass) +
                formatConstructors(theClass) +
                LINE_BREAK +
                formatMethods(theClass) +
                LINE_BREAK +
                formatFields(theClass) +
                LINE_BREAK +
                formatClassFooter();
    }

    private String formatClassHeader(Class<?> theClass) {
        return "class " + formatClassName(theClass) + " {" + LINE_BREAK;
    }

    private String formatClassName(Class<?> theClass) {
        final String className = theClass.getName();

        final Class<?> superClass = theClass.getSuperclass();
        if (superClass == null || superClass.equals(Object.class)) {
            return className;
        } else {
            return className + " extends " + superClass.getName();
        }
    }

    private String formatConstructors(Class<?> theClass) {
        return Arrays.stream(theClass.getDeclaredConstructors())
                .map(this::formatConstructor)
                .map(c -> INDENT + c + ";")
                .collect(Collectors.joining(LINE_BREAK))
                + LINE_BREAK;
    }

    private String formatConstructor(Constructor<?> constructor) {
        // TODO
        return constructor.toString();
    }

    private String formatMethods(Class<?> theClass) {
        return Arrays.stream(theClass.getDeclaredMethods())
                .map(this::formatMethod)
                .map(m -> INDENT + m + ";")
                .collect(Collectors.joining(LINE_BREAK))
                + LINE_BREAK;
    }

    private String formatMethod(Method method) {
        // TODO
        return method.toString();
    }

    private String formatFields(Class<?> theClass) {
        return Arrays.stream(theClass.getDeclaredFields())
                .map(this::formatField)
                .map(f -> INDENT + f + ";")
                .collect(Collectors.joining(LINE_BREAK))
                + LINE_BREAK;
    }

    private String formatField(Field field) {
        // TODO
        return field.toString();
    }

    private String formatClassFooter() {
        return "}" + LINE_BREAK;
    }

    private String readLine(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");

        try {
            return bufferedStdInReader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws IOException {
        stdInReader.close();
        bufferedStdInReader.close();
    }
}
