package poi;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/17
 * @description
 */
public class EntityUtil {
    private String className;
    private String tableName;
    private List<Propertie> properties;

    public EntityUtil(String className,String tableName, List<Propertie> properties) {
        this.className = className;
        this.tableName=tableName;
        this.properties = properties;
    }

    public void getBeanByData() throws Exception {
        StringBuffer classString = new StringBuffer();
        classString.append("@Entity\n" +
                "@Table(name="+tableName+")\n" +
                "public class "+className+"{\n");
        for(Propertie pro:properties){
            classString.append("\tprivate "+pro.getReflexPropertiesType()+" "+pro.getPropertiesName()+";\n\n");
        }
        for(Propertie pro:properties){
            classString.append("\tpublic "+pro.getReflexPropertiesType()+" get"+changeMethodName(pro.getPropertiesName())+"(){\n" +
                    "\t\treturn "+pro.getPropertiesName()+"\n\t}\n\n"
            );

            classString.append("\tpublic void set"+changeMethodName(pro.getPropertiesName())+"(" +
                    "" +pro.getReflexPropertiesType()+" "+pro.getPropertiesName()+
                    "){\n" +
                    "\t\tthis."+pro.getPropertiesName()+"="+pro.getPropertiesName()+"\n\t}\n\n"
            );

        }
        classString.append("}");
        FileOperation.writeTxtFile(classString.toString(),new File("D:\\entity\\entity\\"+className+".java"));
    }

    public void getSqlByData() throws Exception {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("/*==============================================================*/\n" +
                "/* DBMS name:      ORACLE Version 11g                           */\n" +
                "/* Created on:     2017/3/17 17:00:56                           */\n" +
                "/*==============================================================*/\n" +
                "\n" +
                "\n" +
                "drop table "+tableName+" cascade constraints;\n" +
                "\n" +
                "drop table "+tableName+" cascade constraints;\n" +
                "\n" +
                "/*==============================================================*/\n" +
                "/* Table: "+tableName+"                                                  */\n" +
                "/*==============================================================*/\n" +
                "create table "+tableName+" \n" +
                "(");
        StringBuffer content = new StringBuffer();
        for(Propertie pro:properties){
            content.append("\t"+pro.formString(pro.getPropertiesName(),8)+"\t\t"
                    +pro.formString(pro.getReflexPropertiesSqlType()+"("+pro.getPropertiesNumber()+")"
                    +(pro.getPropertiesConstraint().equals("M")?"":","),8)
                    +(pro.getPropertiesConstraint().equals("M")?"\t\tnot null,":"")+"\n");
        }
        String contents = content.toString();
        sqlString.append(contents.toString().substring(0,contents.length()-2)+"\n);\n\n");

        for(Propertie pro:properties){
            sqlString.append("comment on table "+pro.getPropertiesName()+" is\n" +
                    "'"+pro.getPropertiesDescribe()+"';\n\n");
        }
        FileOperation.writeTxtFile(sqlString.toString(),new File("D:\\entity\\sql\\"+className+".sql"));
    }

    private String changeMethodName(String methodName){
        if(methodName!=null&&methodName.length()>1){
            return methodName.substring(0,1).toUpperCase()+methodName.substring(1);
        }
        if(methodName.length()==1){
            return methodName.substring(0,1).toUpperCase();
        }
        return null;
    }

    static class Propertie{
        private String propertiesName;
        private String propertiesType;
        private String propertiesConstraint;
        private String propertiesDescribe;
        private String propertiesNumber;
        private static Map<String,String> reflexType;
        private static Map<String,String> reflexSqlType;
        static {
            reflexType = new HashMap<String,String>();
            reflexSqlType = new HashMap<String,String>();
            reflexType.put("Char","String");
            reflexType.put("Float","float");
            reflexType.put("Varchar","String");
            reflexType.put("Int","int");
            reflexType.put("Date","String");
            reflexSqlType.put("Char","CHAR");
            reflexSqlType.put("Float","FLOAT");
            reflexSqlType.put("Int","INT");
            reflexSqlType.put("Date","DATE");
            reflexSqlType.put("Varchar","VARCHAR");
            reflexSqlType.put("Varbin","VARBIN");
        }

        public Propertie(){
        }

        public String getPropertiesName() {
            return propertiesName;
        }

        public String getPropertiesType() {
            return propertiesType;
        }

        private String getReflexPropertiesType(){
            return reflexType.get(getPropertiesType());
        }

        private String getReflexPropertiesSqlType(){
            return reflexSqlType.get(getPropertiesType());
        }

        private String formString(String param,int len){
            StringBuffer realSqlName = new StringBuffer();
            realSqlName.append(param);
            if(param.length()<len){
                for(int i=0;i<len-param.length();i++){
                    realSqlName.append(" ");
                }
            }
            return realSqlName.toString();
        }


        public String getPropertiesConstraint() {
            return propertiesConstraint;
        }

        public String getPropertiesDescribe() {
            return propertiesDescribe;
        }

        public String getPropertiesNumber() {
            return propertiesNumber;
        }

        public void setPropertiesName(String propertiesName) {
            this.propertiesName = propertiesName;
        }

        public void setPropertiesType(String propertiesType) {
            this.propertiesType = propertiesType;
        }

        public void setPropertiesConstraint(String propertiesConstraint) {
            this.propertiesConstraint = propertiesConstraint;
        }

        public void setPropertiesDescribe(String propertiesDescribe) {
            this.propertiesDescribe = propertiesDescribe;
        }

        public void setPropertiesNumber(String propertiesNumber) {
            this.propertiesNumber = propertiesNumber;
        }
    }


}
