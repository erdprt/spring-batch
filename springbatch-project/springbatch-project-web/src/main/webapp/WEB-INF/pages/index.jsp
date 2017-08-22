<html>
<body>
	<form action="/springbatch-project-web/execute" method="get">
	<table width="80%" border="1" align="center">
		<tr>
			<td colspan="2" align="center">settings</td>		
		</tr>
		<tr>
			<td width="20%">job</td>
			<td  width="80%">
				<select name="job">
					<option value="inventoryExportPartitionningJob">inventoryExportPartitionningJob</option>
					<option value="categoryImportJob">categoryImportJob</option>
					<option value="inventoryImportJob">inventoryImportJob</option>
					<option value="inventoryExportJob">inventoryExportJob</option>
					<option value="ordersImportJob">ordersImportJob</option>
					<option value="dummyJob">dummyJob</option>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%">elementName</td>
			<td  width="80%">
				<select name="elementName">
					<option value="category">category</option>
					<option value="product">product</option>					
					<option value="person">order</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>inputFile</td>
			<td><input type="text" name="inputFile"  value="" size="60"></td>
		</tr>
		<tr>
			<td width="20%">commit.interval</td>
			<td  width="80%">
				<input name="commit.interval" value="">
			</td>
		</tr>
		<tr>
			<td width="20%">inventoryPartitioningExportJob.grid.size</td>
			<td  width="80%">
				<input name="inventoryPartitioningExportJob.grid.size" value="">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="submit" value="Launch">
			</td>
			<td align="center">
				<A HREF="/springbatch-project-web/default">Default Page</A>
			</td>		
		</tr>
	</table>
	</form>
</body>
</html>
