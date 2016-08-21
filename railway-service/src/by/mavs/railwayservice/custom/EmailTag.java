package by.mavs.railwayservice.custom;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class EmailTag extends TagSupport {

	private String email;

	public void setEmail(String email) {
		this.email = email;
	}

	public int doStartTag() throws JspException {
		try {
			if (email != null && !email.isEmpty()) {
				pageContext.getOut().write("Email: " + email);
			}
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
