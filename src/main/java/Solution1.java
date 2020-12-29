import java.util.Stack;

/**
 * @author lin.wang
 * @date 2020/10/10
 */
public class Solution1 {
    public static void main(String[] args) {
        System.out.println(isValid("{((1() or (2))}"));;
    }
    public static boolean isValid(String s) {
        char[] list = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for(char temp : list){
            if(temp=='(' || temp=='[' || temp=='{'){
                stack.push(temp);
            }
            if(temp=='}' && (stack.isEmpty()||stack.pop()!='{')){
                return false;
            }
            if(temp==')' && (stack.isEmpty()||stack.pop()!='(')){
                return false;
            }
            if(temp==']'&& (stack.isEmpty()||stack.pop()!='[')){
                return false;
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
