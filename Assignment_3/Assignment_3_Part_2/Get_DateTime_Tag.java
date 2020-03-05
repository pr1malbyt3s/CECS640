package edu.louisville.cecs640.customTags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/* Get_DateTime_Tag Class used as a custom tag.
 * Used to display the current date and time in bold, red, helvetica font.
 */
public class Get_DateTime_Tag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	// Using doStartTag for bodiless tag.
	public int doStartTag() throws JspException {
		// Initiate JspWriter
		JspWriter out = pageContext.getOut();
		// Create new Date object
		Date now = new Date();
		// Create SimpleDateFormat object to format the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a");
		try {
			// Format the text to be displayed using a paragraph tag.
			out.print("<p style=\"font-family:helvetica;color:red;font-style:bold;\">");
			// Print the formatted date and time.
			out.print(dateFormat.format(now));
			// Close the paragraph.
			out.print("</p>");
		}
		catch (IOException e) {
			throw new JspTagException("Get_DateTime_Tag: " + e.getMessage());
		}
		return SKIP_BODY;
	}
}
