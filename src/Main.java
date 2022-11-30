import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
            String[] a = isEssay(10, 7, "hello my name is Bessie and this is my essay .");
            for (int i = 0; i < 10; i++) {
                if (a[i] != "")
                    System.out.println(a[i]);
            }
            System.out.println(split("(())()()(())"));
            System.out.println(toCamelCase("hello_gosha"));
            System.out.println(overTime(9, 17, 30, 1.5));
            System.out.println(BMI("55 kilos", "1.65 meters"));
            System.out.println(bugger(999));
            System.out.println(trouble(666789,12345667));
            System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
            toStarShorthand("aaaabbbwhf");
            System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));

        }

        //1. Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк
        //довольно плох, она решает напечатать эссе с помощью текстового процессора.
        //Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет
        //длину от 1 до 15 символов включительно и состоит только из прописных или
        //строчных букв. Согласно инструкции к заданию, эссе должно быть
        //отформатировано очень специфическим образом: каждая строка должна содержать
        //не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый
        //процессор Бесси может справиться с этим требованием, используя следующую
        //стратегию:
        //– Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите
        //его в эту строку. В противном случае поместите слово на следующую строку и
        //продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке
        //все равно должны быть разделены одним пробелом. В конце любой строки не должно
        //быть места.
        //– К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
        //помогите ей правильно оформить свое эссе!
        //Вам будут даны n, k и строка
        public static String[] isEssay(int n, int k, String str) {
            int z = 0;
            int x = 0;
            int index = 0;
            int startIndex = 0;
            int endIndex = 0;
            String[] str2 = new String[n];
            for (int i = 0; i < n; i++) {
                str2[i] = "";
            }
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == ' ' || (i + 1) == str.length()) {
                    x++;
                }
                if (x == 1 && index == 0) {
                    endIndex = i;
                    index = 1;
                }
                if (x == 2) {
                    index = 0;
                    if (i - startIndex - 1 <= k) {
                        str2[z] += str.substring(startIndex, i);
                        startIndex = i + 1;
                        endIndex = 0;
                        z++;
                        x = 0;
                    } else {
                        str2[z] += str.substring(startIndex, endIndex);
                        startIndex = endIndex + 1;
                        endIndex = 0;
                        z++;
                        x = 0;
                    }
                } else if (x == 1 && (i + 1) == str.length()) {
                    str2[z] += str.substring(startIndex, endIndex);
                }
            }
            return str2;
        }

         //2. Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер
         //должен быть сбалансирован.
        public static String split(String str) {
            StringBuffer strBuffer = new StringBuffer(str);
            int z = 0;
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) == ')' && str.charAt(i + 1) == '(') {
                    strBuffer.insert(i + 1 + z, ',');
                    z++;
                }
            }
            return strBuffer.toString();
        }

        //Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет
        //одну строку и преобразует ее либо в camelCase, либо в snake_case.
        public static String toCamelCase(String str) {
                int z = 0;
                if (str.indexOf('_') != -1) {
                    String[] str1 = str.split("_");
                    String s = str1[0];
                    for (int i = 1; i < str1.length; i++) {
                        s += str1[i].substring(0, 1).toUpperCase() + str1[i].substring(1);
                    }
                    return s;
                }
                else if (str.indexOf('_') == -1) {
                    StringBuffer strBuffer = new StringBuffer(str);
                    for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i) <= 90 && str.charAt(i) >= 65) {
                            strBuffer.insert(i + z, '_');
                            z++;
                            int b = str.charAt(i) + 32;
                            char a = (char) b;
                            strBuffer.setCharAt(i + z, a);
                        }
                    }
                    return strBuffer.toString();
                }
                return "false";
        }

        //4. Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную
        //с сверхурочной работой.
        //Работа с 9 до 5: обычные часы работы
        //После 5 вечера это сверхурочная работа
        //Ваша функция получает массив с 4 значениями:
        //– Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
        //– Конец рабочего дня. (Тот же формат)
        //– Почасовая ставка
        //– Множитель сверхурочных работ
        public static double overTime(double a, double b, double c, double d) {
            return (17 - a) * c + (b - 17) * c * d;
        }
        //5. Индекс массы тела (ИМТ) определяется путем измерения вашего веса в
        //килограммах и деления на квадрат вашего роста в метрах. Категории ИМТ таковы:
        //Недостаточный вес: <18,5
        //Нормальный вес: 18.5-24.9
        //Избыточный вес: 25 и более
        //Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах,
        //метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите
        //ИМТ до ближайшей десятой.
        public static String BMI(String str1, String str2) {
            String[] s1 = str1.split(" ");
            String[] s2 = str2.split(" ");
            double x = 0, y = 0;
            if (s1[1] == "pounds") {
                x = Double.parseDouble(s1[0]) / 2.2;
            } else {
                x = Double.parseDouble(s1[0]);
            }
            if (s2[1] == "inches") {
                y = Double.parseDouble(s2[0]) / 39.37;
            } else {
                y = Double.parseDouble(s2[0]);
            }
            double s = x / (y * y);
            String answer = String.format("%.1f", s);
            if (s < 18.5) {
                return answer + "  Underweight";
            } else if (s >= 18.5 && s <= 24.9) {
                return answer + " Normal weight";
            } else {
                return answer + " Overweight";
            }
        }

        //6. Создайте функцию, которая принимает число и возвращает его мультипликативное
        //постоянство, которое представляет собой количество раз, которое вы должны
        //умножать цифры в num, пока не достигнете одной цифры.
        public static double bugger(double a) {
            Vector mas = new Vector<>();
            double s, x = 0;
            if (a < 10 && a >= 0) {
            } else if (a > 9) {
                do {
                    s = a;
                    x = 1;
                    while (s > 0) {
                        x *= s % 10;
                        s = (int) (s / 10);
                    }
                    a = x;
                }
                while (x > 9);
            }

            return x;
        }
    //7 Напишите функцию, которая преобразует строку в звездную стенографию. Если
    //символ повторяется n раз, преобразуйте его в символ*n.
    public static void toStarShorthand(String word){
        char[] words = word.toCharArray();
        int iteration=1;
        String temp="";
        String s1="";
        String s2="";
        for(int i=0;i<words.length-1;i++){
            s1+=words[i];
            s2+=words[i+1];
            if(words[i]==words[i+1] || s1.compareTo(s2)==0){
                iteration++;
            }
            else{
                temp+=words[i];
            }
            s1="";
            s2="";

            if((words[i]!=words[i+1] || i==words.length-2) && iteration>1){
                if(i==words.length-2){
                    temp+=words[i];
                }
                temp+="*"+iteration;
                iteration=1;
                s1="";
                s2="";
            }


        }
        System.out.println(temp);
    }

        //8. Создайте функцию, которая возвращает true, если две строки рифмуются, и false в
        //  противном случае. Для целей этого упражнения две строки рифмуются, если
        //  последнее слово из каждого предложения содержит одни и те же гласные.
        public static boolean doesRhyme(String w1, String w2) {
            String temp="";
            String temp2="";
            String[] words1 = w1.split(" ");
            String[] words2 = w2.split(" ");
            char[] ar = words1[words1.length-1].toCharArray();
            char[] ar2 = words2[words2.length-1].toCharArray();
            for (char c : ar) {
                if (c == 'a' || c == 'o' || c == 'e' || c == 'i' || c == 'u') {
                    temp += c;
                }
            }
            for (char c : ar2) {
                if (c == 'a' || c == 'o' || c == 'e' || c == 'i' || c == 'u') {
                    temp2 += c;
                }
            }

            return temp.compareTo(temp2)==0;
        }
    //9.Создайте функцию, которая принимает два целых числа и возвращает true, если
    //число повторяется три раза подряд в любом месте в num1 и то же самое число
    //повторяется два раза подряд в num2.
    public static boolean trouble(int n1, int n2){
        int temp1, temp2, num1, num2, max1, max2, min1,min2;
        num1=num2=0;
        temp1=temp2=-10;
        min1=min2=1;
        max1=max2=0;
        for(int i=0;n1/10!=0 || n1>0;i++){
            if(n1/10==0){
                temp1=n1;
            }
            temp1=n1%10;
            n1/=10;
            if(temp2==temp1){
                min1++;
            }
            if(min1>max1){
                num1=temp1;
                max1=min1;
            }
            temp2=temp1;

        }
        if(max1<3){
            return false;
        }
        System.out.println(num1+" "+max1);
        temp1=temp2=-10;
        for(int i=0;n2/10!=0 || n2>0;i++){
            if(n2/10==0){
                temp1=n2;
            }
            temp1=n2%10;
            n2/=10;
            if(temp2==temp1){
                min2++;
            }
            if(min2>max2){
                num2=temp1;
                max2=min2;
            }
            temp2=temp1;

        }
        System.out.println(num2+" "+max2);
        if(max2 != 2){
            return false;
        }
        return num1==num2;
    }

        //10. Предположим, что пара одинаковых символов служит концами книги для всех
        //символов между ними. Напишите функцию, которая возвращает общее количество
        //уникальных символов (книг, так сказать) между всеми парами концов книги.
        //Эта функция будет выглядеть следующим образом:
        private static int countUniqueBooks(String chars, char divider) {
            Set<Character> booksSet = new HashSet<>();
            boolean bookOpened = false;
            for (char c : chars.toCharArray()) {
                if (c == divider) {
                    bookOpened = !bookOpened;
                } else if (bookOpened) {
                    booksSet.add(c);
                }
            }
            return booksSet.size();
    }
}