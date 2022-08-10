import java.util.Hashtable;
import java.util.Set;

public class BoyerMoore
{
    /**
     * The lastOccurance function
     * @param S
     * @return
     */
    public static Hashtable<Character, Integer> lastOccurrenceFunction(String S)
    {
        Hashtable<Character, Integer> hash = new Hashtable<>();
        for(int i = S.length() - 1; i >= 0; i--) {
            if(!hash.containsKey(S.charAt(i))){
                hash.put(S.charAt(i),i);
            }
        }
        return hash;
    }

    /**
     * Run the Boyer Moore Pattern Matching
     * @param T
     * @param P
     * @return
     */
    public static int find(String T, String P)
    {
        int n = T.length();
        int m = P.length();
        Hashtable<Character, Integer> L = lastOccurrenceFunction(P);

        int i = m - 1;
        int j = m - 1;

        while(i <= n - 1){
            if(T.charAt(i) == P.charAt(j)) {
                if (j == 0) {
                    return i;
                } else {
                    i = i - 1;
                    j = j - 1;
                }
            } else {
                int l = -1;
                if(L.get(T.charAt(i)) != null) {
                    l = L.get(T.charAt(i));
                }
                i = i + m - Math.min(j, 1 + l);
                j = m - 1;
            }
        }
        return -1;
    }

    public static void main(String []args){
        System.out.println(lastOccurrenceFunction("boilerupboilerup"));
    }
}
