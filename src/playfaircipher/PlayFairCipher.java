//Author: Lucas Coutinho de Almeida

package playfaircipher;

import java.util.ArrayList;
import java.util.List;

public class PlayFairCipher {
    
    public static void main(String[] args) {
        String plainText = "passaro";
        List<String> bigramicBlock = new ArrayList<>();
        int aux = 0;
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
        
        System.out.println(bigramicBlock);
        System.out.println(bigramicBlock.get(0));
    }
    
}
