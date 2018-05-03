package util;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author luzj
 * @description: 生成随机名字 和 年龄10-80
 * @date 2018/4/26
 */
public class GeneratorRand {
    private static final String SPACE = " ";
    private static String[] first;
    private static String[] last;

    /**
     * 填充姓氏数组
     *
     * @return
     * @throws IOException
     */
    private static String[] fillFirst() throws IOException {
        InputStream in = GeneratorRand.class.getResourceAsStream("/name/firstname.txt");

        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<String> firstName = new ArrayList<>();
        String line;
        while (!StringUtils.isEmpty((line = bufferedReader.readLine()))) {
            String[] temp = line.split(SPACE);
            for (int i = 0; i < temp.length; i++) {
                firstName.add(temp[i]);
            }
        }
        String[] arr = (String[]) firstName.toArray(new String[firstName.size()]);
        return arr;
    }

    /**
     * 填充名字数组
     *
     * @return
     * @throws IOException
     */
    private static String[] fillLast() throws IOException {
        InputStream in = GeneratorRand.class.getResourceAsStream("/name/lastname.txt");
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<String> lastName = new ArrayList<>();
        String line;
        while (!StringUtils.isEmpty((line = bufferedReader.readLine()))) {
            lastName.add(line);
        }

        String[] arr = lastName.toArray(new String[lastName.size()]);
        return arr;
    }

    /**
     * 生成随机名字
     *
     * @return
     */
    public static String generatorName() {
        Random random = new Random();
        int length = random.nextInt(3);
        String name = "";
        try {
            first = fillFirst();
            last = fillLast();
        } catch (IOException e) {
            System.err.println("读取文件失败...");
            e.printStackTrace();
        }

        int s = random.nextInt(first.length);
        name += first[s];

        s = random.nextInt(last.length);
        name += last[s];
        if (length == 2) {
            s = random.nextInt(last.length);
            name += last[s];
        }
        return name;
    }

    /**
     * 生成随机年龄，10-80
     * @return
     */
    public static int generatorAge() {
        Random random = new Random();
        return random.nextInt(70) + 10;
    }

    public static void main(String[] args) {
        String name = generatorName();
        System.out.println(name);

    }
}