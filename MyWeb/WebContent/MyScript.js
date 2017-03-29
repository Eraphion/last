var xmlHttpRequest = new XMLHttpRequest();
function toServer()
{
	xmlHttpRequest.overrideMimeType("text/xml");
	xmlHttpRequest.open("POST","MyServlet?name="+document.getElementById('test').value,true);
	xmlHttpRequest.onreadystatechange = fromServer;
	xmlHttpRequest.send();
}
function fromServer()
{
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status==200)
		{
			var arr = xmlHttpRequest.responseXML.getElementsByTagName("tag");
			if(arr.length > 100)
				document.getElementById('uni').rows = 100;
			else	
				document.getElementById('uni').rows = arr.length;
			document.getElementById('uni').value = "";
			for(var i = 0; i < arr.length; i++)
				document.getElementById('uni').value += (arr[i].textContent+'\n');
		}
}
