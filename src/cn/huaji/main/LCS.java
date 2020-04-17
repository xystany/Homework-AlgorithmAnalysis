package cn.huaji.main;

/**
 * @ClassName LCS
 * @Description 最长公共子序列问题
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/4/17
 * @version 1.0
 */
public class LCS {
    //存储长度
    static int[][] dp;
    //存储方向 只存1/2/3  1表示从左上角来的 2表示来自左边  3表示来自上边
    static int[][] direction;

    static String a = "ABCDAB";
    static String b = "BDCABA";

    public static void main(String[] args) {
        lcs(a, b);
        print(a.length(), b.length());

    }

    /*
     * @Title lcs
     * @Description 构造dp数组 存储公共子序列的长度 最右下角的元素就是最大公共子序列的长度
     * @author 滑技工厂
     * @Date 2020/4/17
     * @param [s1, s2]
     * @return void
     * @throws
     */
    public static void lcs(String a, String b) {

        dp = new int[a.length() + 1][b.length() + 1];
        direction = new int[a.length() + 1][b.length() + 1];
        //第0行和第0列的元素都是零（因为从空字符串开始比较的）
        //每一行
        for (int i = 1; i < dp.length; i++) {
            //每一列
            for (int j = 1; j < dp[0].length; j++) {
                //如果相等，则从a[i-1]与b[j-1]的LCS基础上加上这个元素 即数量+1
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //来自于左上
                    direction[i][j] = 1;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {//否则，从a[i-1]b[j] or a[i]b[j-1]中挑一个LCS最大的进行赋值
                    dp[i][j] = dp[i - 1][j];
                    //来自于左边
                    direction[i][j] = 2;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    //来自于上面
                    direction[i][j] = 3;
                }

            }
        }
        System.out.println(dp[a.length()][b.length()]);
    }

    public static void print(int i, int j) {
        if (i == 0 || j == 0)
            return;
        else if (direction[i][j] == 1) {
            print(i - 1, j - 1);
            //因为是从1开始记录的，所有i-1才是对应字符串的正确字符
            System.out.print(a.charAt(i - 1));
        } else if (direction[i][j] == 2)
            print(i - 1, j);
        else if (direction[i][j] == 3)
            print(i, j - 1);
    }
}
