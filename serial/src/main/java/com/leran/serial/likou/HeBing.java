package com.leran.serial.likou;

import java.util.ArrayList;
import java.util.List;

/**
 * 合并字符串
 * 示例 1：
 *
 * 输入：word1 = "abc", word2 = "pqr"
 * 输出："apbqcr"
 * 解释：字符串合并情况如下所示：
 * word1：  a   b   c
 * word2：    p   q   r
 * 合并后：  a p b q c r
 * 示例 2：
 *
 * 输入：word1 = "ab", word2 = "pqrs"
 * 输出："apbqrs"
 * 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
 * word1：  a   b
 * word2：    p   q   r   s
 * 合并后：  a p b q   r   s
 * 示例 3：
 *
 * 输入：word1 = "abcd", word2 = "pq"
 * 输出："apbqcd"
 * 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
 * word1：  a   b   c   d
 * word2：    p   q
 * 合并后：  a p b q c   d
 */
public class HeBing {
    public static void main(String[] args) {
        kidsWithCandies(new int[]{2, 3, 5, 1, 3},3);

    }
    public String mergeAlternately(String word1, String word2) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] a1 = word1.toCharArray();
        char[] a2 = word2.toCharArray();
        int al1 = a1.length;
        int al2 = a2.length;
        for (int i= 0; i<(al1 > al2 ? al1: al2); i++) {
            if (i<(al1 < al2 ? al1: al2)) {
                stringBuilder.append(a1[i]).append(a2[i]);
            }else {
                if (al1 < al2) {
                    stringBuilder.append(a2[i]);
                }else {
                    stringBuilder.append(a1[i]);
                }
            }
        }
        return stringBuilder.toString();
    }

    //最大公约数
    public String gcdOfStrings(String str1, String str2) {
        if(str1.indexOf(str2)<=-1) {
            return "";
        }
        return str2;
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = candies[0];
        for (int i=1 ;i<candies.length ;i++ ) {
            max = max< candies[i]? candies[i] : max;
        }
        System.out.println(max);
        List<Boolean> result = new ArrayList<>();
        for (int i : candies) {
            System.out.println((i+extraCandies)>=max);
            result.add((i+extraCandies)>max);
        }
        return result;
    }
}
