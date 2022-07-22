
/* *
 * @version date ( in_ISO_8601 format : 2015 - 09- 10 )
 * @author Shiva Acharya
 */
import java.util.Scanner;

public class Pig {
    private static Scanner scanIn;

    /*
     * small program makes pig in the console
     */
    public static void main(String[] args) {
        scanIn = new Scanner(System.in);
        int Pturn = 1;
        int permscore1 = 0;
        int permscore2 = 0;
        int tempscore = 0;
        String response = "";
        while (permscore1 < 100 && permscore2 < 100) {
            int rolledNum = (int) (Math.random() * 6) + 1;
            System.out.println("\nScore: " + permscore1 + "-" + permscore2 + "\n" + "Player " + Pturn
                    + ", it is your turn. \n" + "You have rolled a " + rolledNum + ".");
            if (rolledNum != 1) {
                tempscore += rolledNum;
                System.out.println("You have " + tempscore + " points. \n" + "Keep Going? (y or n)");
            if (Pturn==1) {
                 response = scanIn.nextLine();
            }
            if (Pturn==2){
                int a = (int) (Math.random() * 2);
                 response= (a == 1) ? "n" : "y";
                 System.out.println(response);
                
            }
                
        else{
                if (response.equalsIgnoreCase("n")) {
                    if (Pturn == 1) {
                        permscore1 += tempscore;
                        Pturn++;
                        tempscore = 0;
                    } if (Pturn == 0) {
                        permscore2 += tempscore;
                        Pturn--;
                        tempscore = 0;
                    }
                    // if (Pturn == 1) {
                } // else {
                  // Pturn = Pturn;
                  // tempscore = 0;
                  // }
            }
            }

            else {
                if (Pturn == 1) {
                    Pturn++;
                    tempscore = 0;
                } else {
                    Pturn--;
                    tempscore = 0;
                }

            }

        }
        System.out.println(" \n    Game Over. Final Score: " + permscore1 + "-" + permscore2 + ".");
    }

}