package org.tothought.pdf;

import java.util.Arrays;

import org.htmlcleaner.TagNode;

public class NodeUtil {

	public static boolean isParent(TagNode node, String parentName){		
		if(node == null){
			System.out.println("Hit Root");
			return false;
		}else{
			if(NodeUtil.hasId(node, parentName)){
				System.out.println("Parent Found");
				return true;
			}else{
				return NodeUtil.isParent(node.getParent(), parentName);
			}
		}		
	}
	
	public static boolean hasId(TagNode node, String id){
		return NodeUtil.attributeEquals(node, "id", id);
	}
	
	public static boolean hasClass(TagNode node, String className){
		return NodeUtil.attributeEquals(node, "class", className);
	}
	
	public static boolean attributeEquals(TagNode node, String attributeName, String attributeValue){
	String attribute = node.getAttributeByName(attributeName);
		
		if(attribute != null){
			return Arrays.asList(attribute.split(" ")).contains(attributeValue);
		}
		
		return false;
	}
	
	public static boolean isTag(TagNode node, String tagName){
		return tagName.equalsIgnoreCase(node.getName());
	}
}
