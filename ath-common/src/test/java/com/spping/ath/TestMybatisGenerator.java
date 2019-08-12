package com.spping.ath;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @Author huabao.fang
 * @Date 00:18 2019-02-20
 * @Description mybatis类生成器
 *
 *
 **/
public class TestMybatisGenerator {


    public static final String ATH = "ath";

    @Test
    public void generateXml() throws Exception{
        generate();
    }



    public void generate() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getGeneratorConfigXmlFile());
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        List LKlist= new LinkedList();
        //LKlist.forEach();
        synchronized (LKlist) {

        }
        Set ss = new  TreeSet();
        Set sh = new  HashSet();
    }

    public File getGeneratorConfigXmlFile(){
        URL url = TestMybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml");
        return new File(url.getPath());
    }
}
