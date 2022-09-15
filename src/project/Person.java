/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author Ben
 */
public abstract class Person implements KeyFinder {

    // Instance fields that all Person child classes share
    private static int nextAvailableBannerId; // Static field to keep track of latest avail id
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int bannerId;

    /**
     * Person Constructor requiring following arguments used for reading in
     * existing person data. Update static field @ nextAvailableBannerId
     *
     * @param f person first name
     * @param l person last name
     * @param p person password
     * @param e person email
     * @param b person bannerId
     * @throws Exception if any arguments are invalid
     */
    public Person(String f, String l, String p, String e, int b) throws Exception {
        try {
            firstName = f;
            lastName = l;
            password = p;
            email = e;
            bannerId = b;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (b > nextAvailableBannerId) {
            nextAvailableBannerId = b;
        }
    }

    /**
     * Person Constructor requiring following arguments used for creating new
     * person. Invokes validPassword to ensure password input meets requirements
     * 
     * @param f first name
     * @param l last name
     * @param p password
     * @throws Exception
     */
    public Person(String f, String l, String p) throws Exception {
        firstName = f;
        lastName = l;
        if (validPassword(p)) {
            password = p;
        } else {
            throw new Exception("Invalid Password");
        }
        this.setEmail();
        bannerId = getNextAvailableBannerId();
    }

    // return person's last name
    public String getLastName() {
        return this.lastName;
    }

    // return person's first name
    public String getFirstName() {
        return this.firstName;
    }

    // return person's password
    public String getPassword() {
        return this.password;
    }

    // return person's bannerId
    public int getBannerId() {
        return this.bannerId;
    }

    // return person's email
    public String getEmail() {
        return this.email;
    }

    /**
     * This auto-generates the password using first initial + lastName + domain
     */
    public final void setEmail() {
        String genEmail = Character.toLowerCase(this.firstName.charAt(0)) + this.lastName.toLowerCase() + "@mcc.edu";
        this.email = genEmail;
    }

    /**
     * This returns whether the password meets the proper requirements
     *
     * @param pass the password in question
     * @return whether requirements are met or not;
     */
    public final boolean validPassword(String pass) throws Exception {
        boolean length = false; // verify that the length of the password is 8-10 chars
        if (pass.length() > 7 && pass.length() < 10) {
            length = true;
        } else {
            throw new InvalidPasswordException("Password length not with 8-10 characters");
        }
        boolean noNames = false;
        if (!pass.toUpperCase().contains(lastName.toUpperCase()) && !pass.toUpperCase().contains(firstName.toUpperCase())) {
            noNames = true;// and convert all to same case
        } else {
            throw new InvalidPasswordException("Password must not contain First or Last names");
        }

        boolean containsDigit = false; // verify that the password contains at least one digit
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {
                containsDigit = true;
            }
        }
        if (containsDigit == false) {
            throw new InvalidPasswordException("Password does not contain at least 1 digit");
        }
        boolean containsUpper = false; // verify that password contains at least one upper case letter
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))) {
                containsUpper = true;
            }
        }
        if (containsUpper == false) {
            throw new InvalidPasswordException("Password does not contain at least 1 upper case letter");
        }
        boolean containsLower = false; // verify that password contains at least one lower case letter
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isLowerCase(pass.charAt(i))) {
                containsLower = true;
            }

        }
        if (containsLower == false) {
            throw new InvalidPasswordException("Password does not contain at least 1 lower case letter");
        }
        // if all critera are met, return true else false
        return length && noNames && containsDigit && containsUpper && containsLower;
    }

    /**
     * This iterates the static banner id count and returns it for assignment
     *
     * @return nextAvailableBannerId + 1
     */
    public final static int getNextAvailableBannerId() {
        nextAvailableBannerId++;
        return nextAvailableBannerId;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = String.format("Last Name: %s, First Name: %s, Email: %s, BannerID: %d, ", lastName, firstName, email, bannerId);
        return str;
    }

    @Override
    public abstract int getKey();

    @Override
    public abstract boolean sameKey(int k);

}
