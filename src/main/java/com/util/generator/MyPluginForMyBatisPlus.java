package com.util.generator;

import java.io.*;
import java.util.*;

public class MyPluginForMyBatisPlus {
    public static void main(String[] args) {
        Map<String, String> modelMapping = new HashMap<>();
        modelMapping.put("dao", "dao");
        modelMapping.put("model", "entity");
        modelMapping.put("mapper", "dao\\mapper");
        modelMapping.put("service", "service");
        modelMapping.put("impl", "service\\impl");
        modelMapping.put("controller", "controller");
        build("chenva.code", modelMapping, true);
    }

    /**
     * 重新构建实体类, 接口文件以及mapper映射文件的路径位置<br />
     * 以每个文件的数据模型为文件夹的名字, 重新定位<br />
     * 程序会自行判断当前项目为maven项目还是web项目<br />
     *
     * @param pack 文件所在的上一级包名, 比如TestModel的包声明为 package com.test.model; , 那这个参数为com.test
     * @param flag 文件重构之后是否删除原目录(即使设置为true, 如果原目录不为空, 也不会删除)
     * @example 在com.test.model包下有
     * AModel.java
     * BModel.java
     * CModel.java
     * <p>
     * 运行之后会生成
     * com.test.amodel.model.AModel.java
     * com.test.bmodel.model.BModel.java
     * com.test.cmodel.model.CModel.java
     */
    public static void build(String pack, boolean flag) {
        Map<String, String> packageName = new HashMap<>();
        packageName.put("dao", "dao");
        packageName.put("model", "model");
        packageName.put("mapper", "dao\\mapper");
        packageName.put("service", "service");
        packageName.put("impl", "service\\impl");
        packageName.put("controller", "controller");
        build(pack, packageName, flag);
    }

    public static void build(String pack, Map<String, String> packageNameMapping, boolean flag) {
        if (validMapping(packageNameMapping)) {
            build(pack,
                    packageNameMapping.get("dao"),
                    packageNameMapping.get("model"),
                    packageNameMapping.get("mapper"),
                    packageNameMapping.get("service"),
                    packageNameMapping.get("impl"),
                    packageNameMapping.get("controller"),
                    flag);
        }
    }

    /**
     * 重新构建实体类, 接口文件以及mapper映射文件的路径位置<br />
     * 以每个文件的数据模型为文件夹的名字, 重新定位<br />
     * 程序会自行判断当前项目为maven项目还是web项目<br />
     *
     * @param pack   文件所在的上一级包名, 比如TestModel的包声明为 package com.test.model; , 那这个参数为com.test
     * @param dao    指定的接口文件位置, 格式必须为"folder", 多层的为"folder1\\folder2"
     * @param model  指定的实体类文件位置, 格式必须为"folder", 多层的为"folder1\\folder2"
     * @param mapper 指定的mybatis映射文件位置, 格式必须为"folder", 多层的为"folder1\\folder2"
     * @param flag   文件重构之后是否删除原目录(即使设置为true, 如果原目录不为空, 也不会删除)
     * @example 在com.test.model包下有
     * AModel.java
     * BModel.java
     * CModel.java
     * <p>
     * 运行之后会生成
     * com.test.amodel.model.AModel.java
     * com.test.bmodel.model.BModel.java
     * com.test.cmodel.model.CModel.java
     */
    private static void build(String pack, String dao, String model, String mapper, String service, String impl, String controller, boolean flag) {
        String p = packName2Path(pack);
        String rootPath = "src\\main\\java" + p;
        if (validPathLog(rootPath + dao)
                && validPathLog(rootPath + mapper)
                && validPathLog(rootPath + model)) {
            System.out.println("开始构建.......");
            mapper(rootPath, mapper, p, flag);
            client(rootPath, dao, model, service, impl, controller, p, flag);
        }
    }

    /**
     * 移动映射文件位置
     *
     * @param rootPath 根目录
     * @param mapper   移动的文件类型, 指明是接口, 实体类还是映射文件
     * @param p        路径名, 由传入的包名转换
     * @param flag     文件重构之后是否删除原目录(即使设置为true, 如果原目录不为空, 也不会删除)
     */
    private static void mapper(String rootPath, String mapper, String p, boolean flag) {
        String ext = ".xml";
        move(rootPath, ext, mapper, p, flag);
    }

    /**
     * 移动映射文件位置
     *
     * @param rootPath 根目录
     * @param dao      移动的文件类型, 指明是接口, 实体类还是映射文件(因为接口与实体类一般同为.java文件, 所以两者使用同一种方法)
     * @param p        路径名, 由传入的包名转换
     * @param flag     文件重构之后是否删除原目录(即使设置为true, 如果原目录不为空, 也不会删除)
     */
    private static void client(String rootPath, String dao, String model, String service, String impl, String controller, String p, boolean flag) {
        String ext = ".java";
        move(rootPath, ext, dao, p, flag);
        move(rootPath, ext, model, p, flag);
        move(rootPath, ext, service, p, flag);
        move(rootPath, ext, impl, p, flag);
        move(rootPath, ext, controller, p, flag);
    }

    /**
     * 移动文件的方法
     *
     * @param rootPath 根目录
     * @param ext      文件后缀名
     * @param subPath  子包名, 即程序会在这个包下将所有符合类型的文件搜索出来
     * @param p        路径名, 由传入的包名转换
     * @param flag     文件重构之后是否删除原目录(即使设置为true, 如果原目录不为空, 也不会删除)
     */
    private static void move(String rootPath, String ext, String subPath, String p, boolean flag) {
        File root = new File(rootPath + File.separator + subPath);
        // 获取指定目录下所有的文件
        File[] files = root.listFiles();
        if (files == null || files.length == 0) {
            System.out.println(rootPath + "目录下未找到任何文件");
        } else {
            for (File f : files) {
                String fname = f.getName();
                if (fname.endsWith(ext)) {

                    String result = "正在移动" + fname;

                    /* 获取子包的文件名 start */
                    String sub = fname.substring(0, fname.lastIndexOf(ext)).toLowerCase();
                    if (sub.lastIndexOf("mapper") > 0) { // 如果是接口或者映射文件, 将结尾的mapper去掉
                        sub = sub.substring(0, sub.lastIndexOf("mapper"));
                    }
                    if (sub.lastIndexOf("service") > 0) { // 如果是service接口文件, 将结尾的service去掉
                        sub = sub.substring(0, sub.lastIndexOf("service"));
                    }
                    if (sub.lastIndexOf("controller") > 0) { // 如果是controller文件, 将结尾的controller去掉
                        sub = sub.substring(0, sub.lastIndexOf("controller"));
                    }
                    if (sub.lastIndexOf("serviceimpl") > 0) { // 如果是service的实现, 将结尾的serviceimpl去掉
                        sub = sub.substring(0, sub.lastIndexOf("serviceimpl"));
                    }
                    /* 获取子包的文件名 end */

                    /* 生成子包 start */
                    String subDir = rootPath + sub + "\\" + subPath;
                    String posString = path2PackName(p.substring(1));
                    String insert = "." + sub;
                    if (!validPath(subDir)) {
                        boolean mkdirs = new File(subDir).mkdirs();
                        if (mkdirs) {
                            System.out.println("创建" + subDir + "子目录成功 !");
                        } else {
                            System.err.println(subDir + "子目录创建失败 !");
                        }
                    }
                    /* 生成子包 end */

                    // 移动文件
                    boolean b = f.renameTo(new File(subDir + File.separator + fname)); // 移动文件
                    result += b ? "...成功 !" : "...失败 !";
                    if (b) {
                        System.out.println(result);
                    } else {
                        System.err.println(result);
                    }

                    // 替换引用名
                    renamePackageOrNamespace(subDir, fname, posString, insert);
                }
            }
        }
        // 删除原目录
        if (flag) {
            files = root.listFiles();
            if (files == null || files.length == 0) {
                if (root.delete()) {
                    System.err.println(root.getPath() + "为空目录, 已删除...");
                }
            }
        }
    }

    /**
     * 文件位置改变, 包名以及类文件的引用也同样需要更换
     * 因为是更换了包名, 所以位置并没有变化太多
     * 规律: 比如原先是com.test.model, 那更换之后就是com.test.amodel.model
     *
     * @param subDir    移动之后的目录
     * @param fname     移动之后的文件
     * @param posString 需要更换的位置字符串
     * @param insert    需要添加的字符串, 此字符串会插入在posString的后面
     */
    public static void renamePackageOrNamespace(String subDir, String fname, String posString, String insert) {
        File file = new File(subDir + File.separator + fname);
        if (file.exists()) {
            System.out.println("正在替换package或者namespace...");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                // 声明一个内存流
                CharArrayWriter tempStream = new CharArrayWriter();
                String line;
                while ((line = br.readLine()) != null) {
                    StringBuilder sb = new StringBuilder(line);
                    // 如果能包含标识位置的字符串, 执行插入操作
                    if (sb.toString().contains(posString)) {
                        sb.insert(line.indexOf(posString) + posString.length(), insert);
                        String newline = sb.toString();
                        tempStream.write(newline); // 写入内存
                    } else { // 将使用原文本
                        tempStream.write(line); // 写入内存
                    }
                    // 追加换行符
                    tempStream.append(System.getProperty("line.separator"));
                }

                // 将内存记录的内容写入源文件
                FileWriter writer = new FileWriter(file);
                tempStream.writeTo(writer);

                // 关闭资源
                writer.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("替换完毕 !");
        }
    }

    /**
     * 将包名转换成路径名, com.test => com/test
     *
     * @param pack 包名的字符串
     * @return
     */
    private static String packName2Path(String pack) {
        StringBuilder path = new StringBuilder("\\");
        String[] paths = pack.split("\\.");
        for (String s : paths) {
            path.append(s);
            path.append(File.separator);
        }
        return path.toString();
    }

    /**
     * 将路径名转换成包名, com/test => com.test
     *
     * @param path 路径的字符串
     * @return
     */
    private static String path2PackName(String path) {
        StringBuilder pack = new StringBuilder();
        String[] packs = path.split("[\\\\/]");
        int idx = 0;
        for (String s : packs) {
            idx++;
            pack.append(s);
            if (idx != packs.length) {
                pack.append('.');
            }
        }
        return pack.toString();
    }

    /**
     * 判断当前项目是否为maven项目
     * 通过是否包含pom.xml文件判断
     *
     * @return
     */
    private static boolean validMaven() {
        String pom = "pom.xml";
        return new File(pom).exists();
    }

    /**
     * 验证传入的路径是否存在
     *
     * @param path
     * @return
     */
    private static boolean validPath(String path) {
        return new File(path).exists();
    }

    /**
     * 验证传入的路径是否存在, 将会在Console打印出结果
     *
     * @param path
     * @return
     */
    private static boolean validPathLog(String path) {
        boolean v = validPath(path);
        if (!v) {
            System.err.println(path + "不存在");
        }
        return v;
    }

    /**
     * 验证传入的包名对应列中是否包含该属性
     *
     * @param pnm
     * @return
     */
    private static boolean validMapping(Map<String, String> pnm) {
        Set<String> keys = pnm.keySet();
        List<String> tempList = Arrays.asList("dao", "model", "mapper", "service", "impl", "controller");
        for (String s : tempList) {
            if (!keys.contains(s)) {
                System.err.println("包名对应列中没有包含\"" + s + "\"属性");
                return false;
            }
        }
        return true;
    }
}
