import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FL_1 {

    public static void Write_File(String table_sel [][], int n, String name) throws IOException {
        FileWriter filewriter = new FileWriter(new File(name));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                filewriter.write(table_sel[i][j]);
                if (j < 5){
                    filewriter.write(",");
                }
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
        sum_rnd = sum_rnd * 100;
        int i = (int) Math.round(sum_rnd);
        sum_rnd = (double)i / 100;
        String str_sum = String.valueOf(sum_rnd);
        return str_sum;
    }

    public static String rnd_nds(String sum_str){
        double sum;
        sum = Double.parseDouble(sum_str);
        double nds;
        nds = sum * 18/100;
        nds = nds * 100;
        int i = (int) Math.round(nds);
        nds = (double)i / 100;
        String str_nds = String.valueOf(nds);
        return str_nds;
    }

    private static void createError(double percentError, String[][] table, int min, int max) {
        int error;
        for (int j = 0; j < percentError; j++){
            Random rnd = new Random(System.currentTimeMillis());
            error = min + rnd.nextInt(max - min + 1);
            //System.out.print(error + " ");
            table[error][0] = rnd_inn();
            j++;
            error = min + rnd.nextInt(max - min + 1);
            //System.out.print(error + " ");
            table[error][1] = rnd_kpp(table[error][0]);
            j++;
            error = min + rnd.nextInt(max - min + 1);
            //System.out.print(error + " ");
            table[error][2] = rnd_inn();
            j++;
            error = min + rnd.nextInt(max - min + 1);
            //System.out.print(error + " ");
            table[error][3] = rnd_kpp(table[error][2]);
            //System.out.print(" ");
        }
    }
    //за проход портим записи продавца и записи покупателя
    public static void createTableErr(int rowNumber, String[][] table_purchase, String[][] table_sale, double percentError) throws IOException{
        String[][] table_purchase_err;
        String[][] table_sale_err;
        table_purchase_err = new String[table_purchase.length][table_purchase[0].length];
        table_sale_err = new String[table_sale.length][table_sale[0].length];
        int countErr = (int)(table_purchase.length * percentError / 100);
        for (int i = 0; i < table_purchase.length; i++){
            for (int j = 0; j < table_purchase[0].length; j++){
                table_purchase_err[i][j] = table_purchase[i][j];
            }
        }
        for (int i = 0; i < table_sale.length; i++) {
            for (int j = 0; j < table_sale[0].length; j++) {
                table_sale_err[i][j] = table_sale[i][j];
            }
        }
        int min = 1;
        int max = table_sale.length;
        createError(countErr, table_sale_err, min, max);
        min += 1;
        max -= 1;
        createError(countErr, table_purchase_err, min, max);
        Write_File(table_purchase_err, rowNumber, "TableError1.csv");
        Write_File(table_sale_err, rowNumber, "TableError2.csv");
    }


    public static void createRndTable(int rowNumber, int columnNumber, double percentErrors) throws IOException {
        String[][] table_purchase;
        String[][] table_sale;
        table_purchase = new String[rowNumber][columnNumber];
        table_sale = new String[rowNumber][columnNumber];
        table_purchase[0][0] = "INN_SALE";
        table_purchase[0][1] = "KPP_SALE";
        table_purchase[0][2] = "INN_PURCHASE";
        table_purchase[0][3] = "KPP_PURCHASE";
        table_purchase[0][4] = "SUM_TABLE_PURCHASE";
        table_purchase[0][5] = "NDS_TABLE_PURCHASE";
        for (int i = 1; i < rowNumber; i++) {
            int j = 0;
            table_purchase[i][j] = rnd_inn();
            j++;
            table_purchase[i][j] = rnd_kpp(table_purchase[i][j-1]);
            j++;
            table_purchase[i][j] = rnd_inn();
            j++;
            table_purchase[i][j] = rnd_kpp(table_purchase[i][j-1]);
            j++;
            table_purchase[i][j] = rnd_sum();
            j++;
            table_purchase[i][j] = rnd_nds(table_purchase[i][j-1]);
        }
        Write_File(table_purchase, rowNumber, "Table1.csv");
        table_sale[0][0] = "INN_PURCHASE";
        table_sale[0][1] = "KPP_PURCHASE";
        table_sale[0][2] = "INN_SALE";
        table_sale[0][3] = "KPP_SALE";
        table_sale[0][4] = "SUM_TABLE_SALE";
        table_sale[0][5] = "NDS_TABLE_SALE";
        for (int i = 1; i < rowNumber; i++){
            int j =0;
            table_sale[i][j] = table_purchase[i][j+2];
            j++;
            table_sale[i][j] = table_purchase[i][j+2];
            j++;
            table_sale[i][j] = table_purchase[i][j-2];
            j++;
            table_sale[i][j] = table_purchase[i][j-2];
            j++;
            table_sale[i][j] = rnd_sum();
            j++;
            table_sale[i][j] = rnd_nds(table_sale[i][j-1]);
        }
        Write_File(table_sale, rowNumber, "Table2.csv");
        createTableErr(rowNumber, table_purchase, table_sale, percentErrors);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter precent errors : ");
        double percentErrors = in.nextDouble();
        int rowNumber = 11; // 10, +1 Т.К. заголовки таблицы
        int columnNumber = 6;
        createRndTable(rowNumber, columnNumber, percentErrors);
    }
}
