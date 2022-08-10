public class Main_pattern {

    /**
     * Finds a word in a matrix.
     * @param crossword
     * @param target
     * @return an array of length 3.
     */
    public static int[] find(final char[][] crossword, final String target) {
        int row = -1;
        int column = -1;
        int direction = -1;

        //horizontal testing
        for(int i = 0; i < crossword.length; i++){
            String regular = "";
            for(int j = 0; j < crossword.length; j++){
                regular = regular + crossword[i][j];
            }
            regular = regular + regular;
            String reversed = new StringBuilder(regular).reverse().toString();
            if(KMP.find(regular, target) != -1){
                direction = 2;
                if(KMP.find(regular, target) < crossword.length){
                    column = KMP.find(regular, target);
                } else {
                    column = KMP.find(regular, target) - crossword.length;
                }
                row = i;
            }
            if(KMP.find(reversed, target) != -1){
                direction = 6;
                if(KMP.find(reversed, target) < crossword.length){
                    column = crossword.length - 1 - KMP.find(reversed, target);
                } else {
                    column = KMP.find(reversed, target);
                }
                row = i;
            }
        }

        //vertical testing
        for(int j = 0; j < crossword.length; j++){
            String regular = "";
            for(int i = 0; i < crossword.length; i++){
                regular = regular + crossword[i][j];
            }
            regular = regular + regular;
            String reversed = new StringBuilder(regular).reverse().toString();
            if(KMP.find(regular, target) != -1){
                direction = 0;
                if(KMP.find(regular, target) < crossword.length){
                    row = KMP.find(regular, target);
                } else {
                    row = KMP.find(regular, target) - crossword.length;
                }
                column = j;
            }
            if(KMP.find(reversed, target) != -1){
                direction = 4;
                if(KMP.find(reversed, target) < crossword.length){
                    row = crossword.length - 1 - KMP.find(reversed, target);
                } else {
                    row = KMP.find(reversed, target);
                }
                column = j;
            }
        }

        //slanted testing
        for(int j = 0; j < crossword.length; j++) {
            String regular = "";
            int startR = 0;
            int startC = j;
            int count = 0;
            for(int k = 0; k < crossword.length; k++) {
                if(startC + k >= crossword.length){
                    regular = regular + crossword[startR + k][count];
                    count ++;
                } else {
                    regular = regular + crossword[startR + k][startC + k];
                }
            }
            regular = regular + regular;
            String reversed = new StringBuilder(regular).reverse().toString();
            if(KMP.find(regular, target) != -1){
                direction = 1;
                if(KMP.find(regular, target) < crossword.length){
                    row = KMP.find(regular, target);
                } else {
                    row = KMP.find(regular, target) - crossword.length;
                }
                if(j + KMP.find(regular, target) < crossword.length){
                    column = j + KMP.find(regular, target);
                } else {
                    column = j + KMP.find(regular, target) - crossword.length;
                }
            }
            if(KMP.find(reversed, target) != -1) {
                direction = 5;
                if (KMP.find(reversed, target) < crossword.length) {
                    row = crossword.length - 1 - KMP.find(reversed, target);
                } else {
                    row = KMP.find(reversed, target) - crossword.length;
                }
                column = ((crossword.length - 1 - (KMP.find(reversed, target)) + j)) % crossword.length;
            }
        }


        for(int j = crossword.length - 1; j >= 0; j--) {
            String regular = "";
            int startR = 0;
            int startC = j;
            int count = crossword.length - 1;
            for(int k = 0; k < crossword.length; k++) {
                if(startC - k < 0){
                    regular = regular + crossword[startR + k][count];
                    count --;
                } else {
                    regular = regular + crossword[startR + k][startC - k];
                }
            }

            regular = regular + regular;
            String reversed = new StringBuilder(regular).reverse().toString();
            if(KMP.find(regular, target) != -1){
                direction = 7;
                if(KMP.find(regular, target) < crossword.length){
                    row = KMP.find(regular, target);
                } else {
                    row = KMP.find(regular, target) - crossword.length;
                }
                column = Math.abs(j - KMP.find(regular, target)) % crossword.length;
            }
            if(KMP.find(reversed, target) != -1) {
                direction = 3;
                if (KMP.find(reversed, target) < crossword.length) {
                    row = crossword.length - 1 - KMP.find(reversed, target);
                } else {
                    row = KMP.find(reversed, target) - crossword.length;
                }
                column = (crossword.length  + KMP.find(reversed, target) - (crossword.length - 1 - j))  % crossword.length;
            }
        }
        int[] arr = new int[3];
        arr[0] = row;
        arr[1] = column;
        arr[2] = direction;
        return arr;
    }

}
