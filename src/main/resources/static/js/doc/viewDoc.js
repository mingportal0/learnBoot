(function(w, d) {

/**
 * deleteDoc
 */
w.deleteDoc = (formId) => {
	let data = getData(formId);
	
	if(!confirm("삭제 하시겠습니까?")){
		return;
	}
	let url = "/doc/deleteDocAction";
	sendPostData(url, data);
}

})(window, document);