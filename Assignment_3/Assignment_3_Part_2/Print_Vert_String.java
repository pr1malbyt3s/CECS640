package edu.louisville.cecs640.customTags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/* Print_Vert_String Class used as a custom tag.
 * Used to print each letter of a JSP body supplied string vertically.
 */
public class Print_Vert_String extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	// Setup BodyContent super reference.
	public void setBodyContent(BodyContent bc) {
		super.setBodyContent(bc);
	}
	// Using doAfterBody for tag with body.
	public int doAfterBody() {
		try {
			BodyContent bodyContent = super.getBodyContent();
			// Retrieve the string passed as body content.
			String bodyString = bodyContent.getString();
			// Initiate JSP writer.
			JspWriter out = bodyContent.getEnclosingWriter();
			/* For loop used to set bodyString as array.
			 * Prints each character in the array followed by a line break.
			 */
			for(char ch : bodyString.toCharArray()) {
				out.println(ch + "<br>");
			}
		}
		catch (IOException e) {
			System.out.println("Error in doAfterBody: " + e.getMessage());
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
