<%@page contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

		function newsValidation() {
		var errorMessageList = [];
		var newsTitle = document.forms["newsForm"].title.value;
		var newsDate = document.forms["newsForm"].date.value;
		var newsBrief = document.forms["newsForm"].brief.value;
		var newsContent = document.forms["newsForm"].content.value;
		var requiredMessage = "<bean:message key="required"/>";
		var maxlengthTitle = "<bean:message key="max.length"/> 100 "
				+ "<bean:message key="symbols"/>.(<bean:message key="title"/>)";
		var maxlengthBrief = "<bean:message key="max.length"/> 500 "
				+ "<bean:message key="symbols"/>.(<bean:message key="brief"/>)";
		var maxlengthContent = "<bean:message key="max.length"/> 2048 "
				+ "<bean:message key="symbols"/>.(<bean:message key="content"/>)";
		var correctDate = "<bean:message key="correct.date"/>" + "(MM/dd/yyyy)";
		
		
		var title = "(<bean:message key="news.title"/>)";
		var date = "(<bean:message key="news.date"/>)";
		var brief = "(<bean:message key="brief"/>)";
		var content = "(<bean:message key="content"/>)";

		if (newsTitle.length <= 0) {
			errorMessageList.push(requiredMessage.concat(title));
		} else if (newsTitle.length >= 100) {
			errorMessageList.push(maxlengthTitle);
		}

		if (newsDate.length <= 0) {
			errorMessageList.push(requiredMessage.concat(date));
		} else {

			var dateCheck = "^((0?[1-9]|1[012])[/](0?[1-9]|[12][0-9]|3[01])[/](19|20)?[0-9]{2})*$";
			var dateRegExp = new RegExp(dateCheck);
			if (!dateRegExp.test(newsDate)) {
				errorMessageList.push(correctDate);
			}
		}
		if (newsBrief.length <= 0) {
			errorMessageList.push(requiredMessage.concat(brief));
		} else if (newsBrief.length >= 500) {
			errorMessageList.push(maxlengthBrief);
		}

		if (newsContent.length <= 0) {
			errorMessageList.push(requiredMessage.concat(content));
		} else if (newsContent.length >= 2048) {
			errorMessageList.push(maxlengthContent);
		}
		
		if (errorMessageList.length == 0) {
			convertDate();
			return true;
		}

		var errorMessages = "";
		for (var i = 0; i < errorMessageList.length; i++) {
			errorMessages += errorMessageList[i] + "\n";
		}
		alert(errorMessages);

		return false;
	}
