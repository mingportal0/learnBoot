(function(w, d) {
d.addEventListener("DOMContentLoaded", function(){
	let form = d.getElementById('saveDoc');
	validate(form, defInput);
	
});

let defInput = [
	{
		name: "title",
		required : true,
	},
	{
		name: "content",
		required : true,
	},
];

/**
 * saveUser
 */
w.saveDoc = (formId) => {
	let form = d.getElementById(formId);
	let data = getData(formId);
	
	if(!checkValidate(form, defInput)){
		return;
	}
	if(!confirm("등록 하시겠습니까?")){
		return;
	}
	console.log(data);
	let url = "/doc/saveDocAction";
	sendPostData(url, data);
}

let sendGetData = (url) => {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onload = function(){
		if(xhr.status = 200){
			alert("Request Success. Response Text is " + xhr.responseText);
		}else{
			alert("Request failed. Returned Status of " + xhr.status);
		}
	}
	xhr.send();
}

let sendPostData = (url, data) => {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onload = function(){
		if(xhr.status = 200){
			var response = JSON.parse(xhr.responseText);
			let modal = d.querySelector(".modal");
			console.log(modal);
			openNotice(response.msg, response.url);
		}else{
			openNotice(response.msg);
		}
	}
	data = JSON.stringify(data);
	xhr.send(data);
}


})(window, document);