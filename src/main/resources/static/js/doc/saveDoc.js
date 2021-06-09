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
	//console.log(data);
	let url = "/doc/saveDocAction";
	sendPostData(url, data);
}

})(window, document);