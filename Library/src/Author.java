/* *
 * @version date ( in_ISO_8601 format : 2015 - 11 - 08 )
 * @author Shiva Acharya
 */
public class Author {
    private String firstName;
    private String lastName;
    private int birth;
    private int death;

    public Author(String firstName, String lastName) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.birth = BookConstants.YEAR_NOT_SET;
            this.death = BookConstants.YEAR_NOT_SET;
        }
    }

    public int getBirth() {
        return birth;
    }

    public int getDeath() {
        return death;
    }

    public void setDates(int birth) {
        if (birth > -2000 && birth < 2020)
            this.birth = birth;

    }

    public void setDates(int birth, int death) {
        if (birth > -2000 && birth < 2020)
            this.birth = birth;
        if (death > -2000 && death < 2020 && death > birth)
            this.death = death;

    }

    public Boolean isSame(Author other) {
        boolean fullFirst = firstName == other.firstName;
        boolean fullLast = lastName == other.lastName;
        boolean lastChar = (other.lastName.length() == 1 || lastName.length() == 1)
                ? lastName.charAt(0) == other.lastName.charAt(0) : false;
        if ((fullFirst) && (fullLast || lastChar)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return firstName + ", " + lastName;

    }

    public String infoString() {
        String authorInformation;
        if (birth != BookConstants.YEAR_NOT_SET && death == BookConstants.YEAR_NOT_SET) {
            authorInformation = toString() + " (b. " + birth + ")";
        } else if (birth != BookConstants.YEAR_NOT_SET && death != BookConstants.YEAR_NOT_SET) {
            authorInformation = toString() + " (" + birth + "-" + death + ")";
        } else {
            authorInformation = toString();
        }
        return authorInformation;
    }

}
