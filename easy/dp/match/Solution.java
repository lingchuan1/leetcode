package easy.dp.match;

public class Solution {
    /**
     * 正则表达式匹配10
     *
     * @param s
     * @param p
     * @return
     * @data 2019/6/30
     */
    public boolean isMatch(String s, String p) {
        //从头匹配，递归
        if (p.isEmpty()) {
            //p为空时，若s也为空，true,否则false
            return s.isEmpty();
        }
        if (p.length() == 1) {
            //p长度为1时，若s长度不为1，返回false,否则看p和s是否相等或p为点
            if (s.length() == 1)
                return p.charAt(0) == s.charAt(0) || p.charAt(0) == '.';
            return false;
        }
        if (p.charAt(1) != '*') {
            //p的第二个字符不是*时，若第一个字符相同或p为点，继续递归判断，否则false
            if (s.length() == 0)
                return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }
        //p的第二个字符是*时
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2)))
                return true;
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }

    //??
    public boolean isMatch2(String s,String p){
        if(p.isEmpty())
            return s.isEmpty();
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 0;i < p.length();i ++){
            if(p.charAt(i) == '*' && dp[0][i - 1])
                dp[0][i + 1] = true;
        }
        for(int i = 0;i < s.length();i ++){
            for(int j = 0;j < p.length();j ++){
                if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))
                    dp[i + 1][j + 1] = dp[i][j];
                if(p.charAt(j) == '*'){
                    if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.')
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    else dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("bbaa", "a..."));
    }
}
