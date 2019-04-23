package org.bkhech.mybatis.extend.generator.generat;

import org.bkhech.mybatis.extend.generator.element.GenericTextElement;
import org.bkhech.mybatis.extend.generator.element.GenericXmlElement;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by guowm on 2017/11/3.
 */
public class SqlMapExtendMethodGenerator {

    public static final String WHERE_CLAUSE_ID = "model_where_clause";

    private XmlElement parentElement;
    private IntrospectedTable introspectedTable;

    public SqlMapExtendMethodGenerator(XmlElement parentElement, IntrospectedTable introspectedTable) {
        this.parentElement = parentElement;
        this.introspectedTable = introspectedTable;
    }

    public XmlElement buildSelectElement(String selectId) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        XmlElement element = new XmlElement("select");
        element.addAttribute(new Attribute("id", selectId));

        boolean hasBLOBColumns = introspectedTable.getBLOBColumns().size() > 0;

        element.addAttribute(new Attribute("resultMap", hasBLOBColumns ? introspectedTable.getResultMapWithBLOBsId() : introspectedTable.getBaseResultMapId()));
        element.addElement(new GenericTextElement("select"));

        String colum = "<include refid=\"" + introspectedTable.getBaseColumnListId() + "\" />";
        if (hasBLOBColumns) {
            colum += ", <include refid=\"" + introspectedTable.getBlobColumnListId()+ "\" />";
        }
        element.addElement(new GenericTextElement(colum));
        element.addElement(new GenericTextElement("from " + tableName));
        return element;
    }

    private void buildWhereCase(XmlElement element) {
        element.addElement(new GenericTextElement("<include refid=\"" + WHERE_CLAUSE_ID + "\"/>"));
        parentElement.addElement(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addModelWhereCase() {

        GenericXmlElement whereCase = new GenericXmlElement("sql");
        whereCase.addAttribute(new Attribute("id", WHERE_CLAUSE_ID));

        GenericXmlElement whereElement = new GenericXmlElement("where");
        whereCase.addElement(whereElement);

        GenericXmlElement modelIf = new GenericXmlElement("if");
        modelIf.addAttribute(new Attribute("test", "model != null"));
        whereElement.addElement(modelIf);
        String alias = introspectedTable.getTableConfiguration().getAlias();
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            GenericXmlElement ifElement = new GenericXmlElement("if");
            String test = "model." + column.getJavaProperty() + " != null";
            ifElement.addAttribute(new Attribute("test", test));

            String cases = "and " + alias + "." + column.getActualColumnName() + " = #{model." + column.getJavaProperty() + "}";
            GenericTextElement caseElement = new GenericTextElement(cases);
            ifElement.addElement(caseElement);
            modelIf.addElement(ifElement);
        }
        parentElement.addElement(whereCase);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelectAll() {
        XmlElement element = buildSelectElement("selectAll");
        parentElement.addElement(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelect() {
        XmlElement element = buildSelectElement("select");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
        GenericXmlElement modelIf = new GenericXmlElement("if");
        modelIf.addAttribute(new Attribute("test", "model == null"));
        modelIf.addElement(new GenericTextElement("where 0 = 1"));
        element.addElement(modelIf);
    }

    public void addSelectOne() {
        XmlElement element = buildSelectElement("selectOne");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
        element.addElement(new GenericTextElement("limit 1"));
    }

    public void addSelectPageList() {
        XmlElement element = buildSelectElement("selectPageList");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
    }

    public void addSelectCount() {
        XmlElement element = new XmlElement("select");
        element.addAttribute(new Attribute("id", "selectCount"));
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        element.addAttribute(new Attribute("resultType", "java.lang.Integer"));
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        element.addElement(new GenericTextElement("select count(1) from " + tableName));
        buildWhereCase(element);
    }

    public void addBatchInsert() {
        XmlElement element = new XmlElement("insert");
        element.addAttribute(new Attribute("id", "batchInsert"));
        element.addAttribute(new Attribute("parameterType", "java.util.List"));
        List<IntrospectedColumn> pkColumns = introspectedTable.getPrimaryKeyColumns();
        if (pkColumns.size() == 1) {
            element.addAttribute(new Attribute("useGeneratedKeys", "true"));
            element.addAttribute(new Attribute("keyProperty", pkColumns.get(0).getJavaProperty()));
        }

        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        element.addElement(new GenericTextElement("insert into " + tableName + " ("));
        List<IntrospectedColumn> columns = introspectedTable.getNonPrimaryKeyColumns();
        int len = 3;

        for (int i = 0; i < columns.size(); i += len) {
            String values = "";
            for (int j = i; j < i + len; j++) {
                if (j < columns.size()) {
                    IntrospectedColumn column = columns.get(j);
                    values += column.getActualColumnName() + (j < columns.size() - 1 ? ", " : "");
                }
            }
            element.addElement(new GenericTextElement("    " + values));
        }

        element.addElement(new GenericTextElement(") values"));
        element.addElement(new GenericTextElement("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">"));
        element.addElement(new GenericTextElement("("));

        for (int i = 0; i < columns.size(); i += len) {
            String values = "";
            for (int j = i; j < i + len; j++) {
                if (j < columns.size()) {
                    IntrospectedColumn column = columns.get(j);
                    values += "#{item." + column.getJavaProperty() + "}" + (j < columns.size() - 1 ? ", " : "");
                }
            }
            element.addElement(new GenericTextElement("    " + values));
        }
        element.addElement(new GenericTextElement(")"));
        element.addElement(new GenericTextElement("</foreach>"));
        parentElement.addElement(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addDeleteByIds() {

        XmlElement element = new XmlElement("delete");
        element.addAttribute(new Attribute("id", "deleteByIds"));
        element.addAttribute(new Attribute("parameterType", "java.util.List"));

        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        int pkSize = introspectedTable.getPrimaryKeyColumns().size();

        element.addElement(new GenericTextElement("delete from " + tableName));
        if (pkSize > 1) {
            element.addElement(new GenericTextElement("where 0 = 1"));
        } else if (pkSize == 1) {
            element.addElement(new GenericTextElement("where " + introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty() + " in"));
            element.addElement(new GenericTextElement("<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\" close=\")\" separator=\",\">"));
            element.addElement(new GenericTextElement("    #{item}"));
            element.addElement(new GenericTextElement("</foreach>"));
        }

        if (pkSize > 0) {
            parentElement.addElement(element);
            parentElement.addElement(new TextElement(""));
        }
    }
}
