public class practice {
    public static int foo ( int a ) {
        int b = a / 10;
        int c = a % 10;
        System . out . println ( " a = " + a + " , b = " + b + " , c = " + c );
        for ( int i = b ; i <= c ; i ++) {
        System . out . println ( " i = " + i );
        if ( b == c ) return i ;
        }
        return c ;
        }
        public static void main ( String [] args ) {
        int a = 14;
        int b = 41;
        int c = 33;
        System . out . println ( " foo ( " + a + " )= " + foo ( a ));
        System . out . println ( " foo ( " + b + " )= " + foo ( b ));
        System . out . println ( " foo ( " + c + " )= " + foo ( c ));
        }
        }