/**
 * 
 */
package com.onemap.utl.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author root
 *
 */
public class XMLUtil {

	private static Logger logger = Logger
			.getLogger("com.ibm.sr.policy.context.processor");

	private static final String CLASS_NAME = "XMLUtils";
	static {
		if (logger.isLoggable(Level.FINEST))
			logger.logp(Level.FINEST, "", "", "");
	}

	/**
	 * Check whether the namespace of element1 is equal to the one of element2.
	 * 
	 * @param element1
	 * @param element2
	 * @return boolean
	 */
	public static boolean isEqualByNS(Element element1, Element element2) {
		String METHOD_NAME = "isEqualByNS";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element1 == null && element2 == null)
			isEqual = true;
		else if (element1 != null && element2 != null) {
			String namespaceURI1 = element1.getNamespaceURI();
			String namespaceURI2 = element2.getNamespaceURI();
			if (namespaceURI1 == null && namespaceURI2 == null)
				isEqual = true;
			else if (namespaceURI1 != null && namespaceURI2 != null) {
				if (namespaceURI1.equals(namespaceURI2))
					isEqual = true;
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Check whether the local name of element1 is equal to the one of element2.
	 * 
	 * @param element1
	 * @param element2
	 * @return boolean
	 */
	public static boolean isEqualByLocalName(Element element1, Element element2) {
		String METHOD_NAME = "isEqualByLocalName";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element1 == null && element2 == null)
			isEqual = true;
		else if (element1 != null && element2 != null) {
			String localName1 = element1.getLocalName();
			String localName2 = element2.getLocalName();
			if (localName1 == null && localName2 == null)
				isEqual = true;
			else if (localName1 != null && localName2 != null) {
				if (localName1.equals(localName2))
					isEqual = true;
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Check whether the value of element1 is equal to the one of element2.
	 * 
	 * @param element1
	 * @param element2
	 * @return boolean
	 */
	public static boolean isEqualByValue(Element element1, Element element2) {
		String METHOD_NAME = "isEqualByValue";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element1 == null && element2 == null)
			isEqual = true;
		else if (element1 != null && element2 != null) {
			String nodeValue1 = element1.getNodeValue();
			String nodeValue2 = element2.getNodeValue();
			if (nodeValue1 == null && nodeValue2 == null)
				isEqual = true;
			else if (nodeValue1 != null && nodeValue2 != null) {
				if (nodeValue1.equals(nodeValue2))
					isEqual = true;
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Check whether the attribute value of element1 is equal to the one of
	 * element2 according to the specified namespace URI and attribute local
	 * name.
	 * 
	 * @param element1
	 * @param element2
	 * @param attrNamespaceURI
	 * @param attrLocalName
	 * @return boolean
	 */
	public static boolean isEqualByAttr(Element element1, Element element2,
			String attrNamespaceURI, String attrLocalName) {
		String METHOD_NAME = "isEqualByAttr";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element1 == null && element2 == null)
			isEqual = true;
		else if (element1 != null && element2 != null) {
			String attrValue1 = element1.getAttributeNS(attrNamespaceURI,
					attrLocalName);
			String attrValue2 = element2.getAttributeNS(attrNamespaceURI,
					attrLocalName);
			if (attrValue1 == null && attrValue2 == null)
				isEqual = true;
			else if (attrValue1 != null && attrValue2 != null) {
				if (attrValue1.equals(attrValue2))
					isEqual = true;
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Check whether the attribute value of element1 is equal to the one of
	 * element2 according to the specified attribute name.
	 * 
	 * @param element1
	 * @param element2
	 * @param attrName
	 * @return boolean
	 */
	public static boolean isEqualByAttr(Element element1, Element element2,
			String attrName) {
		String METHOD_NAME = "isEqualByAttr";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element1 == null && element2 == null)
			isEqual = true;
		else if (element1 != null && element2 != null) {
			String attrValue1 = element1.getAttribute(attrName);
			String attrValue2 = element2.getAttribute(attrName);
			if (attrValue1 == null && attrValue2 == null)
				isEqual = true;
			else if (attrValue1 != null && attrValue2 != null) {
				if (attrValue1.equals(attrValue2))
					isEqual = true;
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Insert child Element into parent Element
	 * 
	 * @param parent
	 * @param child
	 */
	public static void insertElement(Element parent, Element child) {
		String METHOD_NAME = "insertElement";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		if (parent != null && child != null) {
			Document doc = parent.getOwnerDocument();
			if (doc != null) {
				parent.appendChild(doc.importNode(child, true));

			}
		}
		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
	}

	/**
	 * Check whether the namespaceURI and local name value of the specified
	 * element is equal to the specified namespace URI and local name.
	 * 
	 * @param element
	 * @param namespaceURI
	 * @param localName
	 * @return boolean
	 */
	public static boolean isEqual(Element element, String namespaceURI,
			String localName) {
		String METHOD_NAME = "isEqual";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		boolean isEqual = false;
		if (element != null) {
			String _namespaceURI = element.getNamespaceURI();
			String _localName = element.getLocalName();
			boolean isEqualByNS = (_namespaceURI == null && namespaceURI == null)
					|| (_namespaceURI != null && _namespaceURI
							.equals(namespaceURI));
			boolean isEqualByLocalName = (_localName == null && localName == null)
					|| (_localName != null && _localName.equals(localName));
			isEqual = isEqualByNS && isEqualByLocalName;
		}
		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return isEqual;
	}

	/**
	 * Serialize DOM element to String
	 * 
	 * @param element
	 *            DOM element to be serialized
	 * @param omitXMLDeclaration
	 *            indicates whether omits the XML declaration
	 * @param indent
	 *            indicates whether need indent
	 * @return String
	 */
	public static String serializeAsString(Element element,
			boolean omitXMLDeclaration, boolean indent) {
		String METHOD_NAME = "serializeAsString";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		StringWriter sw = new StringWriter();
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, indent ? "yes"
					: "no");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					omitXMLDeclaration ? "yes" : "no");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.transform(new DOMSource(element), new StreamResult(sw));
		} catch (Exception e) {			
			logger.severe(e.getMessage());
		}
		String result = sw.toString();

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return result;
	}

	/**
	 * Deserialize the specified InputStream
	 * 
	 * @param is
	 * @return Document
	 */
	public static boolean deserialize(InputStream is) {
		String METHOD_NAME = "deserialize";
		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}
		Document doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			if(doc != null){
				Element rootElem = doc.getDocumentElement();
				if(rootElem != null){
					NodeList children = rootElem.getChildNodes();
					if(children != null && children.getLength() > 0){
						for (int i = 0; i < children.getLength(); i++) {
							Node child = children.item(i);
							if((child instanceof Element) && "body".equals(child.getNodeName().toLowerCase())){
								String content = ((Element)child).getTextContent();
								if(content!=null && content.trim().indexOf("ok") > -1){
									return true;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {		
			logger.severe(e.getMessage());
		}
		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return false;
	}

	/**
	 * create input source based on byte[] content and encoding. UTF-32 does not
	 * work correctly with the parses so it builds various different kinds of
	 * InputSource, otherwise it's basically creating an InputSource out of a an
	 * InputStream.
	 * 
	 * @param content
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputSource createInputSource(byte[] content, String encoding)
			throws UnsupportedEncodingException {
		String METHOD_NAME = "createInputSource";

		// log entry to the method
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, METHOD_NAME);
		}

		InputSource inSource = null;
		if (encoding == null) {
			inSource = new InputSource(new ByteArrayInputStream(content));
		} else {
			final String UTF_32 = "UTF-32";
			final String UTF_32LE = "UTF-32LE";
			final String UTF_32BE = "UTF-32BE";
			if (encoding.startsWith(UTF_32)) {
				if (encoding.equals(UTF_32)) {
					// Look for byte order marker and set the
					// byte order of the encoding accordingly. Java on AIX is
					// happy unless we tell it the byte order
					// Note: don't forget that byte is unsigned in Java so we
					// need to cast all the hex literals in the if test block
					// to byte. This is very annoying.
					byte[] bomLE = new byte[] { (byte) 0xFF, (byte) 0xFE,
							(byte) 0x00, (byte) 0x00 };
					byte[] bomBE = new byte[] { (byte) 0x00, (byte) 0x00,
							(byte) 0xFE, (byte) 0xFF };
					String s;
					if (content.length >= 4 && content[0] == bomLE[0]
							&& content[1] == bomLE[1] && content[2] == bomLE[2]
							&& content[3] == bomLE[3]) {
						s = new String(content, 4, content.length - 4, UTF_32LE);
						inSource = new InputSource(new StringReader(s));
					} else if (content.length >= 4 && content[0] == bomBE[0]
							&& content[1] == bomBE[1] && content[2] == bomBE[2]
							&& content[3] == bomBE[3]) {
						s = new String(content, 4, content.length - 4, UTF_32BE);
						inSource = new InputSource(new StringReader(s));
					} else {
						inSource = new InputSource(new InputStreamReader(
								new ByteArrayInputStream(content), encoding));
					}
				} else {
					// for UTF-32LE and UTF-32BE
					inSource = new InputSource(new InputStreamReader(
							new ByteArrayInputStream(content), encoding));
				}
			} else {
				inSource = new InputSource(new ByteArrayInputStream(content));
			}
		}

		// log exit from the method
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return inSource;
	}

}
