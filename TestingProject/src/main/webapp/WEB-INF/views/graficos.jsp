<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<meta charset="utf-8">
<style>

.bar {
	fill: steelblue;
}

.bar:hover {
	fill: brown;
}

.axis {
	font: 10px sans-serif;
}

.axis path,.axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}

.x.axis path {
	display: none;
}

</style>
<body>
  <div id="vis"></div>
<script src='<c:url value="/resources/js/d3.js"></c:url>'></script>
<script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
<script>

function drawGraph(data1) {
	var width = 640, height = 400;
	var margin = {top: 20, right: 20, bottom: 30, left: 40};

	//x and y Scales
	var xScale = d3.scale.ordinal()
	    .rangeRoundBands([0, width], .1);

	var yScale = d3.scale.linear()
	    .range([height, 0]);

	xScale.domain(data1.map(function(d) { return d.x; }));
	yScale.domain([0, d3.max(data1, function(d) { return d.y; })]);

	//x and y Axes
	var xAxis = d3.svg.axis()
	    .scale(xScale)
	    .orient("bottom");

	var yAxis = d3.svg.axis()
	    .scale(yScale)
	    .orient("left")
	    .ticks(10);

	//create svg container
	var svg = d3.select("#vis")
	    .append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	    .append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	var refreshGraph = function() {

		xScale.domain(data1.map(function(d) { return d.x; }));
		yScale.domain([0, d3.max(data1, function(d) { return d.y; })]);

		//create bars
		var bars = svg.selectAll(".bar")
		    .data(data1);

		bars.transition()
			.duration(100)
			.attr("cx", function(d) { return xScale(d.x); })
			.attr("cy", function(d) { return yScale(d.y); });

		bars.enter()
		    .append("rect")
		    .attr("class", "bar")
		    .attr("x", function(d) { return xScale(d.x); })
		    .attr("width", xScale.rangeBand())
		    .attr("y", function(d) { return yScale(d.y); })
		    .attr("height", function(d) { return height - yScale(d.y); });
	};

	refreshGraph();

	//drawing the x axis on svg
	svg.append("g")
	    .attr("class", "x axis")
	    .attr("transform", "translate(0," + height + ")")
	    .call(xAxis);

	//drawing the y axis on svg
	svg.append("g")
	    .attr("class", "y axis")
	    .call(yAxis)
	    .append("text")
	    .attr("transform", "rotate(-90)")
	    .attr("y", 6)
	    .attr("dy", ".71em")
	    .style("text-anchor", "end")
	    .text("Frequency");

};

var data1;
var medida =  '<%=(String) request.getAttribute("medida")%>';
path=window.location.host;
temp=$.getJSON("https://"+path+"/obterMedida/" + medida ,function(data){data1=data;}).done(function (){next()});
function next()
{
	temp=JSON.stringify(data1);
	temp=temp.replace(/data/g, "x");
	temp=temp.replace(/valor/g, "y");
	data1=JSON.parse(temp);
	drawGraph(data1);
	}

</script>
