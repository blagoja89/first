<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<xsl:template match="/market" name="addingPage">

		<xsl:param name="catName" />
		<xsl:param name="subcatName" />

		<xsl:param name="model" />
		<xsl:param name="color" />
		<xsl:param name="dateOfIssue" />
		<xsl:param name="price" />
		<xsl:param name="producer" />
		<xsl:param name="notInStock" />

		<xsl:param name="modelError" />
		<xsl:param name="colorError" />
		<xsl:param name="dateError" />
		<xsl:param name="priceError" />
		<xsl:param name="producerError" />

		<html>
			<head>
				<link href="css/addProduct.css" rel="stylesheet" />
				<link href="css/style.css" rel="stylesheet" />
				<script src='js/jquery-1.9.1.js'></script>
				<script src='js/jquery.validate.min.js'></script>
				<script src='js/validation.js'></script>
			</head>
<!-- 			method=saveProduct for struts. method=save_Product for Controller. And change action.-->
			<body>
			
				<div id="main">
					<form id="product_form"
						action="marketAction.do?method=saveProduct&amp;catName={$catName}&amp;subcatName={$subcatName}"
						method="POST">

						<div class="cont">
							<label class="lab" for="model">Model(2 letters and 3 digits)</label>
							<input type="text" id="model" name="model" value="{$model}" />
							<div class="errorM">
								<xsl:value-of select="$modelError" />

							</div>
						</div>

						<div class="cont">
							<label class="lab" for="color">Color</label>
							<input type="text" id="color" name="color" value="{$color}" />
							<div class="errorM">
								<xsl:value-of select="$colorError" />
							</div>
						</div>

						<div class="cont">
							<label class="lab" for="dateOfIssue">Date of issue(dd-mm-yyyy)</label>
							<input type="text" id="dateOfIssue" name="dateOfIssue"
								value="{$dateOfIssue}" />
							<div class="errorM">
								<xsl:value-of select="$dateError" />
							</div>
						</div>

						<div class="cont">
							<label class="lab" for="producer">Producer</label>
							<input type="text" id="producer" name="producer" value="{$producer}"></input>
							<div class="errorM">
								<xsl:value-of select="$producerError" />
							</div>
						</div>

						<div class="cont">
							<label class="lab" for="price">Price(float number)</label>
							<input type="text" name="price" value="{$price}" id="price" />
							<input type="radio" id="notInStockPrice" name="notInStock" checked="checked"
								value="false"/>
							<div class="errorM">
								<xsl:value-of select="$priceError" />
							</div>
						</div>

						<div class="cont">
							<label class="lab">Not in stock</label>
							<input type="radio" id="notInStockId" name="notInStock" value="true"/>
						</div>

						<div class="buttonsBar">

							<a class="myButton"
								href="marketAction.do?method=productlist&amp;catIndex=${productForm.catIndex}&amp;subcatIndex=${subcatIndex}">back</a>

							<a class="myButton" 
								href="" onclick="document.getElementById('product_form').submit(); return false;">save</a>
						</div>
					</form>
					
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
