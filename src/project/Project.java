package project;

import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Ben
 */
public class Project {

    private final ArrayList<KeyFinder> data = new ArrayList<>();
    private String errors = "";

    public Project() {
    }

    /**
     * This accepts a String representing a filename/path and tries to open the
     * file and read in each line and stores it as tokenized Strings in a
     * String[] array. That String[] is then passed through
     * parseInputData(String[])
     *
     * @param path is a String representing file name and/or path
     * @throws FileNotFoundException if file path is not recognized
     * @throws NumberFormatException if Id or Balance are not valid numbers
     */
    public void getInputData(String path) throws FileNotFoundException {
        try {
            File inputFile = new File(path);
            Scanner inputData = new Scanner(inputFile);
            int lineNum = 1;
            while (inputData.hasNext()) {

                String line = inputData.nextLine();
                // Tokenize the line of data
                String[] dataArray = line.split(",");
                // Trim all leading and trailing whitespace
                for (int i = 0; i < dataArray.length; i++) {
                    dataArray[i] = dataArray[i].trim();
                }
                try {
                    if (dataArray.length == 3) {
                        // try to create Student Object with tokens from file line
                        Course course = new Course(Integer.parseInt(dataArray[0]), dataArray[1],
                                Integer.parseInt(dataArray[2]));

                        data.add(course);
                        course = null; //Clear Course Object

                    } else if (dataArray.length == 8) {
                        // try to create Student Object with tokens from file line
                        Student student = new Student(dataArray[1], dataArray[0],
                                dataArray[3], dataArray[2], Integer.parseInt(dataArray[4]),
                                dataArray[5].replaceAll(" ", ""), dataArray[6],
                                Float.parseFloat(dataArray[7]));

                        data.add(student);
                        student = null; //Clear Student Object

                    } else if (dataArray.length == 7) {
                        // try to create a NoMajor Student Object with tokens from file line if requirements met present
                        if (!dataArray[5].equals("Professor") && !dataArray[5].equals("Adjunct")) {
                            Student student = new Student(dataArray[1], dataArray[0],
                                    dataArray[3], dataArray[2], Integer.parseInt(dataArray[4]),
                                    dataArray[5].replaceAll(" ", ""), Float.parseFloat(dataArray[6]));

                            data.add(student);
                            student = null; //Clear Student Object

                            // try to create a Faculty Object  
                        } else {

                            Faculty faculty = new Faculty(dataArray[1], dataArray[0],
                                    dataArray[3], dataArray[2], Integer.parseInt(dataArray[4]),
                                    dataArray[5].replaceAll(" ", ""), dataArray[6]);

                            data.add(faculty);
                            faculty = null; //Clear Faculty Object
                        }
                    } else {
                        throw new Exception("Insufficient Data!");
                    }
                } catch (NumberFormatException e) {
                    addErrorCode(lineNum + ": Invalid BannerId or Balance!");
                } catch (Exception e) {
                    addErrorCode(lineNum + ": " + e.getMessage());
                }
                lineNum++; //Iterate the line next number
            }
        } catch (FileNotFoundException fnfE) {
            JOptionPane.showMessageDialog(null, "Specified File/Path Not Found!", "Error", 1);
        }
        // If errors exist, display them through an ErrorGUI Object
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, errors, "Errors:", 1); // create new errors window
            errors = ""; // set errors to empty after displayed to user
        }

    }

    /**
     * This outputs data to a specified file/path.
     *
     * @param path file path for data output
     * @throws FileNotFoundException
     */
    public void outputData(String path) throws FileNotFoundException {

        // Open output file for writing output data to
        PrintWriter output = new PrintWriter(path);

        // Print out each line, as is, in data ArrayList
        for (KeyFinder datum : data) {
            output.print(datum.toString() + "\n");
        }

        // this creates a second list of KeyFinder objects in the data list
        // and casts them as student, and adds them to a second student only list
        // for sorting.
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) instanceof Student) {
                Student student = (Student) data.get(i);
                students.add(student);
            }
        }
        output.println("\n____Students Sorted By Last Name___");
        Collections.sort(students);
        for (Student student : students) {
            output.println(student.toString());
        }

        // The following prints the data sorted by key (either BannerId or CRN)
        output.print("\n____Data Sorted By Key____\n");
        // Sort the data ArrayList by the key of each object
        ArrayList<KeyFinder> dataSorted = new ArrayList<>(data);
        Collections.copy(dataSorted, data);
        Collections.sort(dataSorted, (a, b) -> a.getKey() - b.getKey());
        // Print out each line to output file
        for (KeyFinder datum : dataSorted) {
            output.print(datum.toString() + "\n");
        }
        //Collections.sort(data, Person.getLastName());
        output.close();
    }

    /**
     * this allows exception messages to be added to the errors String
     *
     * @param e the exception thrown
     */
    public void addErrorCode(String e) {
        errors += (e + "\n");
    }

    /**
     * this returns the error codes collected
     *
     * @return
     */
    public String getErrorCodes() {
        return errors;
    }

    /**
     * this takes a student object and adds it to the data ArrayList
     *
     * @param student is the Student Object desired
     */
    public void addStudent(Student student) {
        data.add(student);
    }

    /**
     * This takes an int as argument and returns a KeyFinder that has a matching
     * key(bannerId or crn)
     *
     * @param keySearch desired key
     * @return KeyFinder with matching key
     */
    public KeyFinder findKey(int keySearch) {
        KeyFinder keyFound = null;
        for (KeyFinder key : data) {
            if (key.sameKey(keySearch)) {
                keyFound = key;
            }
        }
        return keyFound;
    }

    public static void main(String[] args) throws Exception {
        Project project = new Project();
        FinalProjectGUI gui = new FinalProjectGUI(project);

    }

}
