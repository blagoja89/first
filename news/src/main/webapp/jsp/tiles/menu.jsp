<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<div class="sidebar">
    <div id="menuTitle"> <h2><bean:message key="news"/> </h2></div>
    <div id="menuInner"> <ul >
        <li>
            <a href="/news/newsAction.do?method=list"><bean:message key="news.list"/></a>
        </li>
        <li>
            <a href="/news/newsAction.do?method=add"><bean:message key="add.news"/></a>
        </li>
    </ul></div>
</div>