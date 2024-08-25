package arraysAndHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ValidAnagram {

    public HashMap<String, Boolean> testCase() {
        String s1 = "anagram";
        String t1 = "nagaram";

        String s2 = "cat";
        String t2 = "rat";

        HashMap<String, Boolean> result = new HashMap<>();
        result.put((s1+"_"+t1), true);
        result.put((s2+"_"+t2), false);

        return result;
    }
    public boolean solution(String s, String t) {
        HashMap<String, Integer> map = new HashMap<>();

        Arrays.stream(s.split(""))
                .forEach(key -> {
                    if(map.containsKey(key)) {
                        map.put(key, map.get(key)+1);
                    } else {
                        map.put(key, 1);
                    }
                });
        List<String> testString = Arrays.stream(t.split("")).toList();
        for(String key : testString) {
            if(map.containsKey(key)) {
                map.put(key, map.get(key)-1);
                if(map.get(key) == 0) map.remove(key);
            } else {
                return false;
            }
        };
        return map.size() == 0;
    }
    public static void main(String[] args) {
        ValidAnagram va = new ValidAnagram();
        va.testCase().forEach((key,value) -> {
            String str[] = key.split("_");
            System.out.println("The Strings: " + key + " are valid anagram : " + (va.solution(str[0], str[1]) == value));
        });
    }
}
