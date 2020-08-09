给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

 

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/restore-ip-addresses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int len=s.length();
        if(len>12||len<4) return res;
        back(s,0,new ArrayList<>(),4,len);
        return res;
    }
    private void back(String s,int begin,List<String> list,int count,int len){
        if(begin==len&&count==0){
            res.add(String.join(".",list));
            return;
        }
        if(begin==len||count==0){
            return;
        }
        
        for(int i=begin;i<len&&i<begin+3;i++){
            if((len-i-1)>3*count){
                continue;
            }
            if(isValid(s,begin,i)){
                count--;
                list.add(s.substring(begin,i+1));
                back(s,i+1,list,count,len);
                count++;
                list.remove(list.size()-1);
            }
        }
    }

    private boolean isValid(String s,int left,int right){
        if(right-left>0&&s.charAt(left)=='0'){
            return false;
        }
        return Integer.parseInt(s.substring(left,right+1))<=255;
    }
}