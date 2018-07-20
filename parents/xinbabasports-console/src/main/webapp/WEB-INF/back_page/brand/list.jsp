<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
</head>
<body>
<script type="text/javascript">
function optDelete(name,isDisplay,pageNo){
	var size=$("input[name='ids']:checked").size();
	if(size<=0){
		alert("请至少选择一个");
		return;
	}
	 if(!confirm("确定要删除吗")){
		return;
	} 
	
	//提交表单
	$("#jvForm1").attr("action","/consoleBrand/brand/delete.do?name="+name+"&disPlay="+isDisplay+"&pageNo="+pageNo);
	$("#jvForm1").attr("method","post");
	$("#jvForm1").submit();
	//alert(1);
}
</script>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='add.jsp'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/consoleBrand/brand/list.do" method="post" style="padding-top:5px;">
品牌名称: <input type="text" name="name" value="${name }"/>
	<select name="isDisplay" >
		<option value="1" <c:if test="${isDisplay==1 }">select="selected"</c:if>>是</option>
		<option value="0"<c:if test="${isDisplay==0 }">select="selected"</c:if>>不是</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="jvForm1">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
			<th>品牌ID</th>
			<th>品牌名称</th>
			<th>品牌图片</th>
			<th>品牌描述</th>
			<th>排序</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${pagination.list }" var="brand">
		<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
			<td><input type="checkbox" value="${brand.id }" name="ids"/></td>
			<td align="center">${brand.id }</td>
			<td align="center">${brand.name }</td>
			<td align="center"><img width="40" height="40" src="${brand.imgUrl }"/></td>
			<td align="center">${brand.description }</td>
			<td align="center">${brand.sort }</td>
			<td align="center">
				<c:if test="${brand.isDisplay==1 }">是</c:if>
				<c:if test="${brand.isDisplay==0 }">否</c:if>
			</td>
			<td align="center">
			<a class="pn-opt" href="/consoleBrand/brand/toEdit.do?id=${brand.id }">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="#">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</form>
<div class="page pb15">
	<span class="r inb_a page_b">
	
	<c:forEach items="${pagination.pageView }" var="page">
	${page }
	</c:forEach>
	</span>
</div>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete('${name}','${isDisplay }','${pagination.pageNo }');"/></div>
</div>
<script type="text/javascript">
	function checkBox(name,checked){
		$("input[name='ids']").attr("checked",checked);
	}
</script>
</body>
</html>