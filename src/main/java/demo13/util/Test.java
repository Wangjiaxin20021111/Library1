package demo13.util;

/**
 * @author 25043
 */
public class Test {
    public static void main(String[] args) {
        String str="abc";
        StringBuilder stringBuilder=new StringBuilder();
        //实现字符串的拼接
        stringBuilder.append(str);
        for(int i=0;i<100;i++){
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder);
    }
}
