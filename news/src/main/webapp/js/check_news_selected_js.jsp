<%@page contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

		function checknewsSelected() {
		var ids = document.getElementsByName("newsSelected");
		for (index = 0; index < ids.length; ++index) {
			if (ids.item(index).checked) {
				return confirm("<bean:message
                key="removal.news"/>");
			}
		}
		alert("<bean:message
                key="checking.for.null.selection"/>")
		return false;
	}
	
	 function areyousure(){
        return confirm('<bean:message key='removal.news'/>');
    }