package ru.job4j.search;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 16.05.2019
 */
public class SearchFiles {

    private static final String PROJECT_NAME = "Search files";

    public static void main(String[] args) {
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init(args);
    }

    public void init(String[] args) {

        Options posixOptions = getOptions();
        CommandLine commandLine = parseParams(args, posixOptions);

        if (commandLine != null) {
            String founded = search(commandLine);
            String fileNameOut = commandLine.getOptionValue("o");
            try {
                Files.write(Paths.get(fileNameOut), founded.getBytes(Charset.forName("UTF-8")), StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String search(CommandLine commandLine) {

        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        String startDir = commandLine.getOptionValue("d");
        Pattern filter = getFilter(commandLine);

        try {
            try (Stream<Path> paths = Files.walk(Paths.get(startDir))) {
                paths
                        .filter(s -> filter.matcher(s.getFileName().toString()).find())
                        .map(s -> s.getFileName().toString())
                        .forEach(stringJoiner::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringJoiner.toString();
    }

    private Pattern getFilter(CommandLine commandLine) {
        String searchPattern = commandLine.getOptionValue("n");
        Pattern pattern = null;
        if (commandLine.hasOption("m")) {
            pattern = Pattern.compile(WildCardToRegEx.convert(searchPattern));
        } else if (commandLine.hasOption("r")) {
            pattern = Pattern.compile(searchPattern);
        } else if (commandLine.hasOption("f")) {
            pattern = Pattern.compile("^" + Pattern.quote(searchPattern) + "$");
        }
        return pattern;
    }

    private Options getOptions() {
        Option optionDir = new Option("d", "dir", true, "start directory");
        optionDir.setRequired(true);
        Option optionName = new Option("n", "name", true, "file name or mask or regexp");
        optionName.setRequired(true);
        Option optionOut = new Option("o", "out", true, "file name to output search results");
        optionName.setRequired(true);

        Option optionMask = new Option("m", "mask", false, "search by mask");
        Option optionFull = new Option("f", "full name", false, "search by full name");
        Option optionReg = new Option("r", "regexp", false, "search by regexp");

        OptionGroup searchType = new OptionGroup()
                .addOption(optionMask)
                .addOption(optionFull)
                .addOption(optionReg);
        searchType.setRequired(true);

        return new Options()
                .addOption(optionDir)
                .addOption(optionName)
                .addOptionGroup(searchType)
                .addOption(optionOut);

    }

    private CommandLine parseParams(String[] commandLineArguments, Options posixOptions) {

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLinePosixParser.parse(posixOptions, commandLineArguments);
        } catch (ParseException e) {
            System.out.format("%s.%n", e.getMessage());
            printHelp(posixOptions);
        }

        return commandLine;
    }

    public void printHelp(Options posixOptions) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(PROJECT_NAME, posixOptions);
    }
}
