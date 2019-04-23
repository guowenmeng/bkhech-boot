package org.bkhech.mybatis.extend.generator.plugins;

import org.bkhech.mybatis.extend.generator.element.GenericTextElement;
import org.bkhech.mybatis.extend.generator.element.GenericXmlElement;
import org.bkhech.mybatis.extend.generator.util.GeneratorUtil;
import org.bkhech.mybatis.extend.generator.constant.GeneratorConstant;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 生成继承自GenericMapper的Mapper接口
 *
 * Created by guowm on 2017/2/13.
 */
public class GenericMapperGeneratorPlugin extends PluginAdapter {

    private String mapperTargetDir;
    private String mapperTargetPackage;

    private ShellCallback shellCallback = null;

    private java.lang.reflect.Field isMergeable = null;

    public GenericMapperGeneratorPlugin() throws NoSuchFieldException {
        this.shellCallback = new DefaultShellCallback(false);
        if (isMergeable == null) {
            isMergeable = GeneratedXmlFile.class.getDeclaredField("isMergeable");
            isMergeable.setAccessible(true);
        }
    }

    @Override
    public boolean validate(List<String> list) {
        String mapperTargetDir = this.properties.getProperty("mapperTargetDir");
        this.mapperTargetDir = mapperTargetDir;
        String mapperTargetPackage = this.properties.getProperty("mapperTargetPackage");
        this.mapperTargetPackage = mapperTargetPackage;
        return StringUtility.stringHasValue(mapperTargetDir) && StringUtility.stringHasValue(mapperTargetPackage);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        ArrayList<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        JavaFormatter javaFormatter = this.context.getJavaFormatter();

        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();

        String packageName = introspectedTable.getFullyQualifiedTable().getSubPackage(true);

        Iterator<GeneratedJavaFile> javaFilesIterator = introspectedTable.getGeneratedJavaFiles().iterator();
        while (javaFilesIterator.hasNext()) {
            GeneratedJavaFile javaFile = javaFilesIterator.next();
            CompilationUnit unit = javaFile.getCompilationUnit();
            FullyQualifiedJavaType modelJavaType = unit.getType();
            String shortName = modelJavaType.getShortName();

            if(shortName.equals(introspectedTable.getFullyQualifiedTable().getDomainObjectName()) && !shortName.endsWith("Example") && !shortName.endsWith("Mapper") && !shortName.endsWith("SqlProvider")) {
                String mapperName = shortName + "Mapper";
                Interface mapperInterface = new Interface(this.mapperTargetPackage + packageName + "." + mapperName);
                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
                mapperInterface.addImportedType(modelJavaType);
                FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(GeneratorConstant.MAPPER_CLASS_PATH);
                mapperInterface.addImportedType(mapper);

                mapperInterface.addJavaDocLine("/**");
                mapperInterface.addJavaDocLine(" * Mapper: " + mapperName);
                mapperInterface.addJavaDocLine(" * Model : " + shortName);
                mapperInterface.addJavaDocLine(" * Table : " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
                mapperInterface.addJavaDocLine(" *");
                mapperInterface.addJavaDocLine(" * This Mapper generated by MyBatis Generator Extend at " + GeneratorUtil.now());
                mapperInterface.addJavaDocLine(" */");

                if (primaryKeyColumns.size() > 0) {
                    FullyQualifiedJavaType pkType = primaryKeyColumns.size() > 1
                            ? unit.getSuperClass() : primaryKeyColumns.get(0).getFullyQualifiedJavaType();

                    FullyQualifiedJavaType superInterfaceType = new FullyQualifiedJavaType(GeneratorConstant.GENERIC_MAPPER_CLASS_PATH);
                    mapperInterface.addImportedType(superInterfaceType);
                    superInterfaceType.addTypeArgument(modelJavaType);
                    superInterfaceType.addTypeArgument(pkType);
                    mapperInterface.addSuperInterface(superInterfaceType);
                } else {
                    FullyQualifiedJavaType superInterfaceType = new FullyQualifiedJavaType(GeneratorConstant.GENERIC_WITHOUT_PRIMARY_KEY_MAPPER_CLASS_PATH);
                    mapperInterface.addImportedType(superInterfaceType);
                    superInterfaceType.addTypeArgument(modelJavaType);
                    mapperInterface.addSuperInterface(superInterfaceType);
                }
                mapperInterface.addAnnotation("@Mapper");

                try {
                    GeneratedJavaFile file = new GeneratedJavaFile(mapperInterface, this.mapperTargetDir, javaFormatter);
                    File mapperDir = this.shellCallback.getDirectory(this.mapperTargetDir, this.mapperTargetPackage + packageName);
                    File mapperFile = new File(mapperDir, file.getFileName());
                    if(!mapperFile.exists()) {
                        mapperJavaFiles.add(file);
                    }
                } catch (ShellException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapperJavaFiles;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

        List<GeneratedXmlFile> extXmlFiles = new ArrayList<GeneratedXmlFile>(1);
        List<GeneratedXmlFile> xmlFiles = introspectedTable.getGeneratedXmlFiles();

        for (GeneratedXmlFile xmlFile : xmlFiles) {
            try {
                isMergeable.set(xmlFile, false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
            XmlElement root = new XmlElement("mapper");
            document.setRootElement(root);

            String namespace = this.mapperTargetPackage + "." + introspectedTable.getTableConfiguration().getDomainObjectName() + "Mapper";
            root.addAttribute(new Attribute("namespace", namespace));
            root.addElement(new TextElement("  <!--"));
            root.addElement(new TextElement("      This Mapper File generated by MyBatis Generator Extend at " + GeneratorUtil.now()));
            root.addElement(new TextElement("  -->"));
            root.addElement(new TextElement("  "));

            String fileName = xmlFile.getFileName();
            String targetProject = xmlFile.getTargetProject();
            String targetPackage = xmlFile.getTargetPackage() + ".extend";

            try {
                File directory = shellCallback.getDirectory(targetProject, targetPackage);
                File targetFile = new File(directory, fileName);
                if (!targetFile.exists()) {
                    GeneratedXmlFile gxf = new GeneratedXmlFile(document, fileName, targetPackage, targetProject,
                            true, context.getXmlFormatter());
                    extXmlFiles.add(gxf);
                }
            } catch (ShellException e) {
                e.printStackTrace();
            }
            extXmlFiles.add(xmlFile);
        }

        return extXmlFiles;
    }

    /**
     * 修改mapper的namespace
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String nameSpace = mapperTargetPackage + "." + introspectedTable.getTableConfiguration().getDomainObjectName() + "Mapper";

        XmlElement root = document.getRootElement();
        GenericXmlElement newRoot = new GenericXmlElement(root.getName());

        for (Attribute attr : root.getAttributes()) {
            newRoot.addAttribute(attr);
        }

        for (Element element : root.getElements()) {
            if (element instanceof XmlElement) {
                newRoot.addElement(new GenericXmlElement((XmlElement) element));
                newRoot.addElement(new TextElement(""));
            } else if (element instanceof TextElement){
                GenericTextElement text = new GenericTextElement(((TextElement) element).getContent());
                newRoot.addElement(text);
            } else {
                newRoot.addElement(element);
            }
        }

        document.setRootElement(newRoot);
        introspectedTable.setMyBatis3FallbackSqlMapNamespace(nameSpace);
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

}
