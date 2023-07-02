import java.util.LinkedHashMap;
import java.util.Map;

public class Ex11_20220808040 {
    public static void main(String[] args) {

    }
    public static int numOfTriplets(int[] arr, int sum) {
        int total = 0;
        sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currSum = arr[i] + arr[left] + arr[right];
                if (currSum >= sum) {
                    right--;
                } else {
                    total += right - left;
                    left++;
                }
            }
        }
        return total;
    }
    public static void sort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j = i + 1;j< array.length;j++){
                if(array[j]<array[i]){
                    int t=array[j];
                    array[j]=array[i];
                    array[i]=t;
                }
            }
        }
    }
    public static int kthSmallest(int[] arr,int k){
        for (int i=arr.length/2 - 1;i>=0;i--){
            helperMethod(arr,arr.length,i);
        }
        for(int i=0;i<k-1;i++){
            helperMethod(arr);
        }
        return helperMethod(arr);
    }

    public static void helperMethod(int[] array, int length, int startIndex) {
        int smallest = startIndex;
        int left = (startIndex * 2) + 1;
        int right = (startIndex * 2) + 2;

        if (left < length && array[left] < array[smallest]) {
            smallest = left;
        }

        if (right < length && array[right] < array[smallest]) {
            smallest = right;
        }

        if (smallest != startIndex) {
            mySwap(array, startIndex, smallest);
            helperMethod(array, length, smallest);
        }
    }

    public static void mySwap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static int helperMethod(int[] array){
        int keeper= array[0];
        array[0]=array[array.length-1];
        helperMethod(array,array.length - 1,0);
        return keeper;
    }
    public static String subSequence(String str) {
        int maximum = 0;
        char[] chars = str.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > maximum) {
                maximum = chars[i];
                result += chars[i];
            }
        }
        System.out.println("n");
        return result;
    }
    public static int isSubString(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return -1;
        }
        int str1Index = 0,str2Index = 0;
        while (str1Index < str1.length() && str2Index < str2.length()) {
            if (str1.charAt(str1Index) == str2.charAt(str2Index)) {
                str1Index++;
                str2Index++;
            } else {
                str2Index = 0;
            }
        }
        if (str2Index == str2.length()) {
            return str1Index-str2Index;
        } else {
            return -1;
        }
    }
    public static void findRepeats(int[] arr,int n){
        LinkedHashMap<Integer,Integer> keeper=new LinkedHashMap<>();
        for(int i=0;i<arr.length;i++){
            keeper.put(i,keeper.getOrDefault(i,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : keeper.entrySet()) {
            if (entry.getValue() > n) {
                System.out.println(entry.getKey());
            }
        }
    }

}