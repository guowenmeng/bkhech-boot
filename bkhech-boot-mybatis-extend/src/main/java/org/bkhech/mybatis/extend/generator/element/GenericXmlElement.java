package org.bkhech.mybatis.extend.generator.element;

import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by guowm on 2017/11/2.
 */
public class GenericXmlElement extends XmlElement {

    public GenericXmlElement(String name) {
        super(name);
    }

    public GenericXmlElement(XmlElement xmlElement) {
        super(xmlElement.getName());
        putElements(xmlElement.getElements());
        putAttrs(xmlElement.getAttributes());
    }

    public void putElements(List<Element> elements) {
        for (Element element : elements) {
            addElement(element);
        }
    }

    public void putAttrs(List<Attribute> attributes) {
        for (Attribute attr : attributes) {
            addAttribute(attr);
        }
    }

    @Override
    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append('<');
        sb.append(getName());

        for (Attribute att : getAttributes()) {
            sb.append(' ');
            sb.append(att.getFormattedContent());
        }

        if (getElements().size() > 0) {
            sb.append(" >"); //$NON-NLS-1$
            for (Element element : getElements()) {
                OutputUtilities.newLine(sb);
                if (element instanceof XmlElement) {
                    GenericXmlElement xml = new GenericXmlElement((XmlElement) element);
                    sb.append(xml.getFormattedContent(indentLevel + 1));
                } else if (element instanceof TextElement) {
                    GenericTextElement text = new GenericTextElement(((TextElement) element).getContent());
                    sb.append(text.getFormattedContent(indentLevel + 1));
                } else {
                    sb.append(element.getFormattedContent(indentLevel + 1));
                }
            }
            OutputUtilities.newLine(sb);
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append("</"); //$NON-NLS-1$
            sb.append(getName());
            sb.append('>');

        } else {
            sb.append(" />"); //$NON-NLS-1$
        }
        return sb.toString();
    }
}
