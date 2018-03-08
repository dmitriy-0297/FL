import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class test {

    public static void Write_File(String table_sel [][], int n) throws IOException {
        FileWriter filewriter = new FileWriter(new File("Table1.txt"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                filewriter.write(table_sel[i][j] + " ");
                filewriter.flush();
            }
            filewriter.write("\n");
        }
    }

    public static String rnd_inn(){
        int num1;
        int num2;
        int num3;
        int max1 = 95; int max2 = 10000; int max3 = 10000;
        int min1 = 10; int min2 = 99999; int min3 = 99999;
        max1 -= min1;
        num1 = (int) (Math.random() * ++max1) + min1;
        max2 -= min2;
        num2 = (int) (Math.random() * ++max2) + min2;
        max3 -= min3;
        num3 = (int) (Math.random() * ++max3) + min3;
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        String str3 = String.valueOf(num3);
        String inn_str = str1 + str2 + str3;
        return inn_str;
    }

    public static String rnd_kpp(String inn){
       String str1 = new String();
       int num;
       int min = 10000;
       int max = 99999;
       for (int i = 0; i < 4; i++){
          str1 += String.valueOf(inn.charAt(i));
        }
        max -= min;
        num = (int) (Math.random() * ++max) + min;
        String str2 = String.valueOf(num);
        String kpp_str = str1 + str2;
        return kpp_str;
    }

    public static String rnd_sum(){
        double min = 10000000;
        double max = 99999999;
        double num; double sum_rnd;
        max -= min;
        num = (Math.random() * ++max) + min;
        sum_rnd = num / 100;
        sum_rnd = sum_rnd * 1000;
        int i = (int) Math.round(sum_rnd);
        sum_rnd = (double)i / 1000;
        String str_sum = String.valueOf(sum_rnd);
        return str_sum;
    }

    public static String rnd_nds(String sum_str){
        double sum;
        sum = Double.parseDouble(sum_str);
        double nds;
        nds = sum * 18/100;
        nds = nds * 1000;
        int i = (int) Math.round(nds);
        nds = (double)i / 1000;
        String str_nds = String.valueOf(nds);
        return str_nds;
    }

    public static void rnd(int n) throws IOException {
        String[][] table_sell;
        table_sell = new String[n][6];
        for (int i = 0; i < n; i++) {
            int j = 0;
            table_sell[i][j] = rnd_inn();
            j++;
            table_sell[i][j] = rnd_kpp(table_sell[i][j-1]);
            j++;
            table_sell[i][j] = rnd_inn();
            j++;
            table_sell[i][j] = rnd_kpp(table_sell[i][j-1]);
            j++;
            table_sell[i][j] = rnd_sum();
            j++;
            table_sell[i][j] = rnd_nds(table_sell[i][j-1]);
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 6; j++) {
                System.out.print(table_sell[i][j] + "  ");
            }
            System.out.println();
        }
        Write_File(table_sell, n);
    }

    public static void main(String[] args) throws IOException {
        int n = 10;
        rnd(n);
    }
}
