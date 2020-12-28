//Author: Lucas Coutinho de Almeida
package playfaircipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayFairCipher {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Type a word: ");
        String plainText = scan.nextLine().toLowerCase();
        List<String> bigramicBlock = new ArrayList<>();
        if (plainText.length() % 2 != 0) {
            plainText += 'x';
        }
        String repeatedLetter = "";
        for (int i = 0; i < plainText.length();) {
            String first = String.valueOf(plainText.charAt(i));
            i++;
            String second = String.valueOf(plainText.charAt(i));
            if (first.equals(second)) {
                repeatedLetter += first;
                first = "";
                first += 'z';
            }
            bigramicBlock.add(first + second);
            i++;
        }

        String alphabet = "abcdefghijlmnopqrstuvwxyz";
        System.out.print("Type a key: ");
        String key = scan.nextLine().toLowerCase();
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
        
        List<String> bigramicBlockEncrypted = new ArrayList();
        for (int i = 0; i < encryptedPlainText.length();) {
            String firstLetter = String.valueOf(encryptedPlainText.charAt(i));
            i++;
            String secondLetter = String.valueOf(encryptedPlainText.charAt(i));
            bigramicBlockEncrypted.add(firstLetter + secondLetter);
            i++;
        }
        
        List decryptedText = new ArrayList();
        for (int i = 0; i < bigramicBlock.size(); i++) {
            int a = 0, b = 0, c = 0, d = 0;
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (cipherAlphabetMatrix[k][l] == bigramicBlockEncrypted.get(i).charAt(j)) {
                                a = k;
                                b = l;
                            }
                        }
                    }
                }
                if (j == 1) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (cipherAlphabetMatrix[k][l] == bigramicBlockEncrypted.get(i).charAt(j)) {
                                c = k;
                                d = l;
                                
                                if (a != c && b != d) {
                                    decryptedText.add(cipherAlphabetMatrix[a][d]);
                                    decryptedText.add(cipherAlphabetMatrix[c][b]);
                                }
                                if (a == c) {
                                    if (b == 0) {
                                        b = 4;
                                        decryptedText.add(cipherAlphabetMatrix[a][b]);
                                        decryptedText.add(cipherAlphabetMatrix[c][d-1]);
                                    } else if (d == 0) {
                                        d = 4;
                                        decryptedText.add(cipherAlphabetMatrix[a][b-1]);
                                        decryptedText.add(cipherAlphabetMatrix[c][d]);
                                    } else {
                                        decryptedText.add(cipherAlphabetMatrix[a][b-1]);
                                        decryptedText.add(cipherAlphabetMatrix[c][d-1]);
                                    }
                                }
                                if (b == d) {
                                    if (a == 0) {
                                        a = 4;
                                        decryptedText.add(cipherAlphabetMatrix[a][b]);
                                        decryptedText.add(cipherAlphabetMatrix[c-1][d]);
                                    } else if (c == 0) {
                                        c = 4;
                                        decryptedText.add(cipherAlphabetMatrix[a-1][b]);
                                        decryptedText.add(cipherAlphabetMatrix[c][d]);
                                    } else {
                                        decryptedText.add(cipherAlphabetMatrix[a-1][b]);
                                        decryptedText.add(cipherAlphabetMatrix[c-1][d]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        String decryptedPlainText = "";
        for (int i = 0; i < encryptedText.size(); i++) {
            String text = String.valueOf(decryptedText.get(i));
            if (text.equals("z")) {
                text = repeatedLetter;
            }
            decryptedPlainText += text;
        }
        if (decryptedPlainText.endsWith("x")) {
            decryptedPlainText = decryptedPlainText.replace("x", "");
        }
        System.out.println("Decrypted plaint text: " + decryptedPlainText);
    }

}
