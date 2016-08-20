package by.mavs.railwayservice.custom;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TimeTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String time = "<hr/>Last time update: <b> "
				+ formatDate.format(gc.getTime()) + " </b><hr/>";
		try {
			JspWriter out = pageContext.getOut();
			out.write(time);
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
