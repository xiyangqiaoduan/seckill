<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 	<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>编号</th>
					<td><input name="id" type="text" class="span2" value="${id }" readonly="readonly"></td>
					<th>资源名称</th>
					<td><input name="name" type="text" placeholder="请输入资源名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td><input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox span2" value=""></td>
					<th>资源类型</th>
					<td>
						<select name="typeId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option>菜单</option>	
							<option>地址</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>排序</th>
					<td><input name="seq" value="100" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false,min:100"></td>
					<th>上级资源</th>
					<td><select id="pid" name="pid" style="width: 140px; height: 29px;"></select><img src="/seckill/resources/css/images/extjs_icons/cut_red.png" onclick="$('#pid').combotree('clear');" /></td>
				</tr>
				<tr>
					<th>菜单图标</th>
					<td colspan="3"><input id="iconCls" name="iconCls" style="width: 375px; height: 29px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="remark" style="width: 375px; height: 104px;"></textarea></td>
				</tr>
			</table>
		</form>
 		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/resource-add.js"></script>
		