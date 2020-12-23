//Author: Lucas Coutinho de Almeida
package playfaircipher;

import java.util.ArrayList;
import java.util.List;

public class PlayFairCipher {

    public static void main(String[] args) {
        String plainText = "passaro";
        List<String> bigramicBlock = new ArrayList<>();
        if (plainText.length() % 2 != 0) {
            plainText += 'x';
        }
        for (int i = 0; i < plainText.length();) {
            String first = String.valueOf(plainText.charAt(i));
            i++;
            String second = String.valueOf(plainText.charAt(i));
            if (first.equals(second)) {
                first = "";
                first += 'z';
            }
            bigramicBlock.add(first + second);
            i++;
        }

        String alphabet = "abcdefghijlmnopqrstuvwxyz";
        String key = "grito";
        int alphabetSize = alphabet.length();
        int keySize = key.length();
        List firstLine = new ArrayList<>();
        for (int i = 0; i < keySize; i++) {
            firstLine.add(key.charAt(i));
        }
        for (int i = 0; i < alphabetSize; i++) {
            if (!firstLine.contains(alphabet.charAt(i))) {
                firstLine.add(alphabet.charAt(i));
            }
        }
        char[][] cipherAlphabetMatrix = new char[5][5];
        int aux = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cipherAlphabetMatrix[i][j] = (Character) firstLine.get(aux);
                aux++;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(cipherAlphabetMatrix[i][j]);
            }
            System.out.println("");
        }
    }

}
