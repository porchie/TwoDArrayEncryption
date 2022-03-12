import java.util.ArrayList;
import java.util.Arrays;

public class TwoDArrayEncryptor {

    private int rowShift;
    private int colShift;
    private String key;

    public TwoDArrayEncryptor(int rowShift, int colShift, String key) {

        this.rowShift = rowShift;
        this.colShift = colShift;
        this.key = key;
    }

    public String encrypt(String message) {
        char[][] letterMatrix = strToCharTwoD(message);
        shiftCharTwoDRight(letterMatrix,rowShift);
        shiftColDownMod(letterMatrix,colShift);
        String str = charTwoDToString(letterMatrix);
        return vigenere(str,key);
    }

    public String decrypt(String message)
    {
        message = decryptVigenere(message,key);
        char[][] letterMatrix = strToCharTwoD(message);
        int rowLen = letterMatrix.length;
        int colLen = letterMatrix[0].length;
        shiftColDownMod(letterMatrix,rowLen-(colShift%rowLen));
        shiftCharTwoDRight(letterMatrix,colLen-(rowShift%colLen));
        String decrypted = charTwoDToString(letterMatrix);
        return decrypted;
    }

    public static void shiftCharTwoDRight(char[][] matrix, int shift)
    {
        for (int r = 0; r < matrix.length; r++) {
            shiftCharRightModify(matrix[r],shift);
        }
    }

    public static char[][] strToCharTwoD(String str)
    {
        char[][] matrix;
        int length = str.length();
        int[] rowCols = squarestFactors(length);
        int row = rowCols[0];
        int col = rowCols[1];
        matrix = new char[row][col];

        int idx = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                matrix[r][c] = str.charAt(idx);
                idx++;
            }
        }
        return matrix;
    }

    public static int[] squarestFactors(int num)
    {
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i = 1;i<= num;i++)
        {
            if(num%i==0) factors.add(i);
            if(num/i==i) return new int[]{i,i};
        }
        int[] squarest = new int[2];
        squarest[0] = factors.get(factors.size()/2-1);
        squarest[1] = factors.get(factors.size()/2);
        return squarest;
    }

    public static String charTwoDToPrintableString(char[][] letterMatrix)
    {
        String str = "";
        for(char[] c:letterMatrix)
        {
            str += Arrays.toString(c) + "\n";
        }
        return str;
    }

    public static String charTwoDToString(char[][] letterMatrix)
    {
        String str = "";
        for(char[] c:letterMatrix)
        {
            for(char ch:c)
            {
                str+= ch;
            }
        }
        return str;
    }

    public static String vigenere(String str, String key)
    {
        String keyString = "";
        String s = "";
        str = str.toUpperCase();
        for (int i = 0; i<str.length();i++)
        {
            if(str.charAt(i)<=90 && str.charAt(i)>=65)
                keyString += (key.substring(i%key.length(),i%key.length()+1)).toUpperCase();
            else
                keyString += str.charAt(i);
        }
        for (int i = 0; i<str.length();i++)
        {

            if(!(str.charAt(i)<=90 && str.charAt(i)>=65))
            {
                s += str.charAt(i);

            }
            else{
                int keyCh = (int)keyString.charAt(i) - 65;
                int ch = (int)str.charAt(i) - 65;
                char chr = (char)((keyCh+ch)%26+65);
                s += chr;
            }
        }
        return s;
    }

    public static String decryptVigenere(String str, String key)
    {
        String keyString = "";
        String s = "";
        str = str.toUpperCase();
        for (int i = 0; i<str.length();i++)
        {
            if(str.charAt(i)<=90 && str.charAt(i)>=65)
                keyString += (key.substring(i%key.length(),i%key.length()+1)).toUpperCase();
            else
                keyString += str.charAt(i);

        }
        for (int i = 0; i<str.length();i++)
        {
            if(!(str.charAt(i)<=90 && str.charAt(i)>=65))
            {
                s += str.charAt(i);
                continue;
            }
            int keyCh = (int)keyString.charAt(i) - 65;
            int ch = (int)str.charAt(i) - 65;
            int num = ch - keyCh;
            char chr =  (char)((num+26)%26+65);
            s += chr;
        }
        return s;
    }


    public static void shiftColDownMod(char[][] arr, int shiftNum)
    {
        int colLen = arr[0].length;
        int rowLen = arr.length;
        for (int c = 0; c < colLen; c++) {
            char[] col = new char[rowLen];
            for (int r = 0; r < rowLen; r++) { //gets all elements of a col
                col[r] = arr[r][c];
            }

            shiftCharRightModify(col,shiftNum); //shifts the column

            for (int r = 0; r < rowLen; r++) { // reassigns
                arr[r][c] = col[r];
            }
        }
    }


    public static void shiftCharRightModify(char[] numList, int shiftNum)
    {
        shiftNum %= numList.length;
        char[] temp = new char[shiftNum];
        for (int i = numList.length - shiftNum; i<numList.length; i++)
        {
            temp[i-(numList.length-shiftNum)] = numList[i];
        }
        char[] temp2 = new char[numList.length - shiftNum];
        for (int i = 0; i < numList.length - shiftNum;i++)
        {
            temp2[i] = numList[i];
        }
        for (int i = shiftNum; i<numList.length;i++)
        {
            numList[i] = temp2[i - shiftNum];
        }
        for (int i = 0; i < shiftNum;i++)
        {
            numList[i] = temp[i];
        }
    }

    public static void copyArr(char[] arr1,char[] arr2)
    {
        arr2 = new char[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
    }

}
