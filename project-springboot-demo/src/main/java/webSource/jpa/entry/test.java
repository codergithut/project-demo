package webSource.jpa.entry;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/21
 * @description
 */
public class test
{
   public static void main(String[] args) {
       String s = "0ttt9";
       StringBuffer string = new StringBuffer();
       StringBuffer number = new StringBuffer();

       int d = 0;
       for(int i=0; i<s.length(); i++) {

           char a =  s.charAt(i);
           int k = (int)a;
           System.out.println(k);
           if((int)a>47 && (int)a<58) {
               number.append((char)(a));
           }else {
               string.append((char)(a));
           }

       }
       System.out.println(string.append(number).toString());
   }

}
