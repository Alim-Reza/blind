package twoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ValidPalindrome {
    public HashMap<String, Boolean> testCases() {
        HashMap<String, Boolean> map = new HashMap<>();
        String test1 = "A man, a plan, a canal: Panama";
        String test2 = "race a car";
        map.put(test1, true);
        map.put(test2, false);
        return map;
    }
    public boolean isAlphaNumeric(String x) {
        return x.matches("[a-z0-9]+");
    }
    public String cleanUptheString(String str) {
        str = str.toLowerCase();
        String newString = "";
        List<String> stringList =  Arrays.stream(str.split("")).toList();
                for(String x: stringList) {
                    if (isAlphaNumeric(x)) {
                        newString += x;
                    }
                };
        return newString;
    }
    public boolean solution(String key) {
        key = cleanUptheString(key);
        for(int i = 0; i < key.length(); i++){
            if(key.charAt(i) != key.charAt(key.length()-i-1)) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        vp.testCases().forEach((key,value) -> {
            System.out.println("string: " + key + " is a palindrome: " + (vp.solution(key) == value));
        });
    }
}
