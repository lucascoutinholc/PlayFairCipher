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
//        for (int i = 0; i < 5; i++) { //print
//            for (int j = 0; j < 5; j++) {
//                System.out.print(cipherAlphabetMatrix[i][j]);
//            }
//            System.out.println("");
//        }
        
        List encryptedText = new ArrayList();
        for (int i = 0; i < bigramicBlock.size(); i++) {
            int a = 0, b = 0, c = 0, d = 0;
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (cipherAlphabetMatrix[k][l] == bigramicBlock.get(i).charAt(j)) {
                                a = k;
                                b = l;
                            }
                        }
                    }
                }
                if (j == 1) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (cipherAlphabetMatrix[k][l] == bigramicBlock.get(i).charAt(j)) {
                                c = k;
                                d = l;
                                
                                if (a != c && b != d) {
                                    encryptedText.add(cipherAlphabetMatrix[a][d]);
                                    encryptedText.add(cipherAlphabetMatrix[c][b]);
                                }
                                if (a == c) {
                                    if (b == 4) {
                                        b = 0;
                                        encryptedText.add(cipherAlphabetMatrix[a][b]);
                                        encryptedText.add(cipherAlphabetMatrix[c][d+1]);
                                    } else if (d == 4) {
                                        d = 0;
                                        encryptedText.add(cipherAlphabetMatrix[a][b+1]);
                                        encryptedText.add(cipherAlphabetMatrix[c][d]);
                                    } else {
                                        encryptedText.add(cipherAlphabetMatrix[a][b+1]);
                                        encryptedText.add(cipherAlphabetMatrix[c][d+1]);
                                    }
                                }
                                if (b == d) {
                                    if (a == 4) {
                                        a = 0;
                                        encryptedText.add(cipherAlphabetMatrix[a][b]);
                                        encryptedText.add(cipherAlphabetMatrix[c+1][d]);
                                    } else if (c == 4) {
                                        c = 0;
                                        encryptedText.add(cipherAlphabetMatrix[a+1][b]);
                                        encryptedText.add(cipherAlphabetMatrix[c][d]);
                                    } else {
                                        encryptedText.add(cipherAlphabetMatrix[a+1][b]);
                                        encryptedText.add(cipherAlphabetMatrix[c+1][d]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        String encryptedPlainText = "";
        for (int i = 0; i < encryptedText.size(); i++) {
            encryptedPlainText += encryptedText.get(i);
        }
        System.out.println("Encrypted plaint text: " + encryptedPlainText);
    }

}
