<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<meta content="no-cache" http-equiv="Cache-Control">
<meta content="0" http-equiv="Expires">
<%@ include file="include.jsp" %>
<script type='text/javascript' src='<c:url value="/resources/js/idc/spr/SPRProduct.js"/>' ></script>
<script>
	// makes sure the whole site is loaded
	jQuery(window).load(function() {
	        // will first fade out the loading animation
		jQuery("#status").fadeOut();
	        // will fade out the whole DIV that covers the website.
		jQuery("#preloader").delay(500).fadeOut("slow");
	});
</script>
<style type="text/css">
#preloader  {
     position: absolute;
     top: 0;
     left: 0;
     right: 0;
     bottom: 0;
     background-color:#000;
     opacity:0.4;
     filter: alpha(opacity=40); /* For IE8 and earlier */
     z-index: 99;
    height: 100%;
 }

#status  {
     width: 200px;
     height: 200px;
     position: absolute;
     left: 50%;
     top: 50%;
     background-image: url(./resources/images/loading2.gif);
     background-repeat: no-repeat;
     background-position: center;
     margin: -100px 0 0 -100px;
 }
</style>
</head>
<body class="skin-blue">
<div style="margin-top: 50px">
	<%@ include file="Header.jsp" %>
  
<!-- ====================== Content Wrapper. Contains page content ========================= -->	      
	  <div > 
	  <!-- Content Header (Page header) -->
		<section class="content-header">
          <h1>
            Blank page
            <small>it all starts here</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Examples</a></li>
            <li class="active">Blank page</li>
          </ol>
        </section>	  
	  <!-- Main content -->
		<section class="content">

          <!-- Default box -->
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Title</h3>
              <div class="box-tools pull-right">
                <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                <button class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
				<form:form id="form1" method="POST"	action="${contextPath}/productForm"	commandName="form1">
				<input type="submit" value="ok" />
				<input type="button" value="GetData" name="btnGetData" id="btnGetData" />
				<table cellpadding="0" cellspacing="0" border="0"  
						class="table table-hover table-bordered table-responsive table-striped display" 
						id="example2">
					<thead>
						<tr class="info">
							<th width="8%">รหัสสินค้า</th>
							<th width="8%">สินค้า</th>
							<th width="14%">ราคา</th>    
						</tr>
					</thead>
					<tbody>
					    <c:if test="${fn:length(productForm.al) > 0}" >
					    	<c:forEach items="${productForm.al}" var="form1" varStatus="loop">
					    	<tr ${loop.index %2==0}>			    	
								<td><c:out value="${form1.productId}"/></td>
								<td class="center"><c:out value="${form1.productName}"/></td>
								<td class="left"><c:out value="${form1.priceSale}"/></td>
							</tr>
					    	</c:forEach>
					    </c:if>
					</tbody>
				</table>
				<div id="dialog" title="Message" style="display: none">
					<div id="message"></div>
				</div>
				<div class="log" style="display: none"></div>
				<div id='msgbox' title='' style='display:none'></div>
				<div id="preloader">
				  <div id="status">&nbsp;</div>
				</div>
				</form:form>
            </div><!-- /.box-body -->
            <div class="box-footer">
              Footer
            </div><!-- /.box-footer-->
          </div><!-- /.box -->

        </section>	  
	  
      </div>
      
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 2.0
        </div>
        <strong>Copyright © 2014-2015 <a href="#">xDreamsBox && iDreamsBox</a>.</strong> All rights reserved.
      </footer>
</div>

</body>
</html>