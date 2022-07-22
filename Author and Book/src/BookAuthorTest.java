/** 
 * Tests Book and Author classes.
 *
 * NOTE: This code will not compile until you have created and
 * implemented the Book and Author classes described in the
 * assignment.
 */
public class BookAuthorTest {

    public static void main(String[] args) { 
        Book [] allTheBooks = new Book[30]; 
        int score = 5; int tScore = 0;
  
        // Test the constructors to make sure they compile and run.
  
        System.out.println( "Attempting constructors:" );
  
        Author a1 = new Author( "Ballard", "J.G." );
        Author a2 = new Author( "Eggers", "Dave" );
        Author a3 = new Author( "Catton", "Eleanor" );
        Author a4 = new Author( "Adler", "Renata" );
  
        System.out.println( "  - Author constructors seem functional:  2/2" );

        allTheBooks[0] = new Book();
        allTheBooks[1] = new Book( "The Circle" );
        allTheBooks[2] = new Book( "The Luminaries", a3 );
        allTheBooks[3] = new Book();
        allTheBooks[4] = new Book( "Pitch Dark" );
        allTheBooks[5] = new Book( "Speedboat" );
        allTheBooks[6] = new Book();
        allTheBooks[7] = new Book( "The Dissident Gardens" );

        System.out.println( "  - Book constructors seem functional:    3/3" );
  
        // Setters/getters
  
        System.out.println( "\nAttempting simple getters/setters:" );
  
        allTheBooks[0].setTitle( "The Complete Stories" );  
        if( "The Complete Stories".equals( allTheBooks[0].getTitle() ) ) { tScore++; }
        if( "The Circle".equals( allTheBooks[1].getTitle() ) ) { tScore++; }
  
        System.out.println( "  - getTitle/setTitle:                  " + tScore + "/2" );
        score += tScore; tScore = 0;
  
        allTheBooks[0].setAuthor( a1 );
        allTheBooks[1].setAuthor( a2 );
        if( a1 == allTheBooks[0].getAuthor() ) { tScore++; }
        if( a3 == allTheBooks[2].getAuthor() ) { tScore++; }
 
        System.out.println( "  - getAuthor/setAuthor:                " + tScore + "/2" );
        score += tScore; tScore = 0;
  
        allTheBooks[0].setYear( 2010 );
        allTheBooks[1].setYear( 2013 );
        if( 2010 == allTheBooks[0].getYear() ) { tScore++; }
        if( 2013 == allTheBooks[1].getYear() ) { tScore++; }
 
        System.out.println( "  - getYear/setYear:                    " + tScore + "/2" );
        score += tScore; tScore = 0;

        allTheBooks[1].setISBN( "9780385351393" );
        allTheBooks[2].setISBN( "9780316074315" );
        if( "9780385351393" == allTheBooks[1].getISBN() ) { tScore++; }
        if( "9780316074315" == allTheBooks[2].getISBN() ) { tScore++; }
 
        System.out.println( "  - getISBN/setISBN:                    " + tScore + "/2" );
        score += tScore; tScore = 0;


        a4.setDates( 1938 );
        a2.setDates( 1970 );
        a1.setDates( 1930, 2009 );
        if( 2009 == a1.getDeath() ) { tScore++; }
        if( 1930 == a1.getBirth() ) { tScore++; }
        if( 1970 == a2.getBirth() ) { tScore++; }
        if( BookConstants.YEAR_NOT_SET == a2.getDeath() ) { tScore++; }
 
        System.out.println( "  - author getBirth/getDeath/setDates:  " + tScore + "/4" );
        score += tScore; tScore = 0;

        // Were the default constants correctly used?

        System.out.println( "\nAttempting un-set values register correctly:" );
  
        if( BookConstants.YEAR_NOT_SET   == allTheBooks[2].getYear() ) { tScore++; }
        if( BookConstants.AUTHOR_NOT_SET == allTheBooks[3].getAuthor() ) { tScore++; }
        if( BookConstants.AUTHOR_NOT_SET == allTheBooks[4].getAuthor() ) { tScore++; }
        if( BookConstants.TITLE_NOT_SET  == allTheBooks[3].getTitle() ) { tScore++; }
        if( BookConstants.ISBN_NOT_SET   == allTheBooks[0].getISBN() ) { tScore++; }
 
        System.out.println( "  - un-set values:  " + tScore + "/5" );
        score += tScore; tScore = 0;  


        // Try to set bad values and see if it correctly rejects them.

        System.out.println( "\nAttempting bad-value setters:" );

        allTheBooks[0].setYear( 2025 );
        if( 2010 == allTheBooks[0].getYear() ) { tScore+=2; }
        System.out.println( "  - year too far in the future rejected:  " + tScore + "/2" );
        score += tScore; tScore = 0;
  
        allTheBooks[0].setTitle( "" );
        if( "The Complete Stories" == allTheBooks[0].getTitle() ) { tScore+=2; }
        System.out.println( "  - empty title rejected:                 " + tScore + "/2" );
        score += tScore; tScore = 0;

        allTheBooks[0].setISBN( "38741" );
        if( BookConstants.ISBN_NOT_SET == allTheBooks[0].getISBN() ) { tScore+=2; }
        System.out.println( "  - ISBN of incorrect length rejected:    " + tScore + "/2" );
        score += tScore; tScore = 0;
 
        a1.setDates( -3000 );
        if( 1930 == a1.getBirth() ) { tScore+=2; }
        System.out.println( "  - year of birth in super past rejected: " + tScore + "/2" );
        score += tScore; tScore = 0;  

        a1.setDates( 1930, 1919 );
        if( 2009 == a1.getDeath() ) { tScore+=2; }
        System.out.println( "  - year of death before birth rejected:  " + tScore + "/2" );
        score += tScore; tScore = 0;  
  
        // Check if stuff is the same
  
        System.out.println( "\nComparisons:" );
  
        Author ax1 = new Author( "Catton", "Eleanor" );
        Author ax2 = new Author( "Catton", "J" );
        Author ax3 = new Author( "C", "Eleanor" );

        Author ax4 = new Author( "Catton", "E" );
        Author ax5 = new Author( "Catton", "Elizabeth" );
  
        if( a3.isSame(ax1) ) { tScore++; }
        if( !a3.isSame(ax2) ) { tScore++; }
        if( !a3.isSame(ax3) ) { tScore++; }
        if( !a3.isSame(ax5) ) { tScore++; }
        System.out.println( "  - author comparison tests:          " + tScore + "/4" );
        score += tScore; tScore = 0;
  
        if( a3.isSame(ax4) ) { tScore+=2; }
        if( ax4.isSame(a3) ) { tScore+=2; }
        if( ax4.isSame(ax5) ) { tScore+=1; }
        System.out.println( "  - *bonus* author comparison tests:  " + tScore + "/5" );
        score += tScore; tScore = 0;
  
  
        allTheBooks[4].setAuthor( a4 );
        allTheBooks[5].setAuthor( a4 );
        if( allTheBooks[4].sameAuthor( allTheBooks[5] ) == true ) { tScore++; }
        if( allTheBooks[4].sameAuthor( allTheBooks[2] ) == false ) { tScore++; }
        System.out.println( "  - sameAuthor (across two books):    " + tScore + "/2" );
        score += tScore; tScore = 0;

        allTheBooks[6].setISBN( "9780316074315" );
        if( !allTheBooks[6].equals( allTheBooks[5] ) ) { tScore++; }
        if( allTheBooks[6].equals( allTheBooks[2] ) ) { tScore++; }
        System.out.println( "  - comparing two books:              " + tScore + "/2" );
        score += tScore; tScore = 0;  
  
        // Check string representations
  
        System.out.println( "\nPrinting authors and books:" );
        String x1 = "Adler, Renata";
        String x2 = "Ballard, J.G.";
        if( x2.equals(allTheBooks[0].getAuthor().toString()) ) { tScore++; }
        if( x1.equals(allTheBooks[4].getAuthor().toString()) ) { tScore++; }
        System.out.println( "  - printing an author's name:         " + tScore + "/2" );
        score += tScore; tScore = 0;

        String x3 = "Catton, Eleanor";
        String x4 = "Ballard, J.G. (1930-2009)";
        String x5 = "Eggers, Dave (b. 1970)";
        if( x3.equals(allTheBooks[2].getAuthor().infoString()) ) { tScore++; }
        if( x4.equals(allTheBooks[0].getAuthor().infoString()) ) { tScore++; }
        if( x5.equals(allTheBooks[1].getAuthor().infoString()) ) { tScore++; }
        System.out.println( "  - printing an author's name + info:  " + tScore + "/3" );
        score += tScore; tScore = 0;
  
        String b1 = "The Circle (2013). Eggers, Dave.";
        String b2 = "The Luminaries. Catton, Eleanor.";
        String b3 = "The Dissident Gardens.";
        if( b1.equals(allTheBooks[1].toString()) ) { tScore++; }
        if( b2.equals(allTheBooks[2].toString()) ) { tScore+=2; }
        if( b3.equals(allTheBooks[7].toString()) ) { tScore+=2; }
        System.out.println( "  - printing a book's name + info:     " + tScore + "/5" );
        score += tScore; tScore = 0;

  
        System.out.println( "\nOverall Score: " + score + "/50  \n   (can be up to 55 with bonus)" ); 
    }
}