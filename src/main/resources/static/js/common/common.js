(function(w, d) {
/*
	set Left Menu
*/
w.setMenu = (list) =>{
	let i = list.length;
	let ul = d.getElementById("left_nav");
	
	while(i--){
		let li = d.createElement("li");
		li.classList.add("left_navItem");
		
		let a = d.createElement("a");
		if(list[i].class){
			a.classList.add(list[i].class);
		}
		if(list[i].id){
			a.id = list[i].id;
		}
		if(list[i].data){
			let keys = Object.keys(list[i].data);
			let k = keys.length;
			while(k--){
				a.dataset[keys[k]] = list[i].data[k];
				console.log(keys[k], list[i].data[keys[k]]);
			}
			
		}
		if(list[i].link){
			a.href = list[i].link;
		}
		if(list[i].name){
			a.innerHTML = list[i].name;
		}
		
		li.appendChild(a);
		ul.appendChild(li);
	}
}
//td 데이터 가져오기
w.getData = (formId) => {
	let form = d.getElementById(formId);
	let list = new Object();
	let eleItemList = getEleDataList(form);
		
	for(eleItem of eleItemList){
		if(list[eleItem.name]){
			let tmpVal = list[eleItem.value];
			let tmpList = [];
			tmpList.push(tmpVal);
			tmpList.push(eleItem.value);
			list[eleItem.name] = tmpList;
		}else{
			list[eleItem.name] = eleItem.value;
		}
	}
	return list;
}

//Element Name, Value 가져오기
getEleDataList = (form) => {
	let itemList = [];
	let inputList = form.querySelectorAll("input[type='text'], input[type='hidden'], textarea");
	let selList = form.querySelectorAll("select");
	for(let input of inputList){
		let item = new Object();
		item.name = input.name;
		item.value = input.value;
		itemList.push(item);
	}
	for(let sel of selList){
		let item = new Object();
		let selOpt = sel.options[sel.selectedIndex];
		item.name = sel.name;
		item.value = selOpt.value;
		itemList.push(item);
	}
	return itemList;
}

/*
* validate : form 내 input validation
* view 형식
<div class="group_row">
	<div class="row">
		<label for="email">이메일</label>
	</div>
	<div class="row">
		<input type="text" name="email" th:field="*{email}" placeholder="test@naver.com" maxlength="20"/>
	</div>
</div>
* parameter 소개
* name : input name
* required : true 시 필수값으로 인식
* check : function으로 정의 리턴이 false 시 에러 메세지 출력
* checkMsg : 에러 메세지
* return : 에러가 없으면 true
 */
w.validate = (form, defInput) => {
	let inputs = form.querySelectorAll('input, textarea');
	for(let input of inputs){
		input.addEventListener("change", function(){
			let groupRow = input.parentElement.parentElement;
			let di = getDefInput(defInput, input);
			
			if(di){
				if(di.required && !input.value){
					let chk1 = checkError(groupRow, input.value, "필수값입니다.");
					if(!chk1) return;
				}
				if(di.equalTo){
					let pw = form.querySelector(di.equalTo);
					checkError(groupRow, input.value == pw.value, di.checkMsg);
					return;
				}
				if(di.check){
					checkError(groupRow, di.check(input), di.checkMsg);
					return;
				}
			}
			deleteErrorSpan(groupRow);
		});
	}
}
/*
* checkValidate : form submit 전 form validation
*/
w.checkValidate = (form, defInput) => {
	let inputs = form.querySelectorAll('input, textarea');
	for(let input of inputs){
		let groupRow = input.parentElement.parentElement;
		let di = getDefInput(defInput, input);
		
		if(di){
			if(di.required){
				let chk1 = checkError(groupRow, input.value, "필수값입니다.");
				if(!chk1) return false;
			}
			if(di.equalTo){
				let pw = form.querySelector(di.equalTo);
				let chk1 = checkError(groupRow, input.value == pw.value, di.checkMsg);
				if(!chk1) return false;
			}
			if(di.check){
				let chk1 = checkError(groupRow, di.check(input), di.checkMsg);
				if(!chk1) return false;
			}
		}
		
		deleteErrorSpan(groupRow);
	}
	return true;
}

w.sendGetData = (url) => {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onload = function(){
		if(xhr.status = 200){
			var response = JSON.parse(xhr.responseText);
			openNotice(response.msg, response.url);
		}else{
			openNotice(response.msg);
		}
	}
	xhr.send();
}

w.sendPostData = (url, data) => {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onload = function(){
		if(xhr.status = 200){
			var response = JSON.parse(xhr.responseText);
			openNotice(response.msg, response.url);
		}else{
			openNotice(response.msg);
		}
	}
	data = JSON.stringify(data);
	xhr.send(data);
}

//openNotice
w.openNotice = function(msg, url) {
	if (!msg) {
		return;
	}
	alert(msg);
	
	//url
	if (url == "close") {
		let modal = document.querySelector(".modal");
		if(modal){
			closeModal();
		}else{
			modal = parent.document.querySelector(".modal");
			if(modal){
				closeModal();
			}else{
				w.close();
			}
		}
		
	} else if (url == "closeAndReload") {
		let modal = document.querySelector(".modal");
		if(modal){
			location.reload();
		}else{
			modal = parent.document.querySelector(".modal");
			if(modal){
				parent.location.reload();
			}else{
				w.close();
				location.reload();
			}
		}

	} else if (url == "reload") {
		location.reload();
		
	} else if (url) {
		location.href = url;
	}
}

//getDefInput
let getDefInput = (defInput, input) => {
	for(let di of defInput){
		if(input.name === di.name){
			return di;
		}
	}
}

//checkError
let checkError = (groupRow, check, errorMsg) => {
	if (!check) {
		createErrorSpan(groupRow, errorMsg);
		return false;
	}else{
		deleteErrorSpan(groupRow);
		return true;
	}
}

let createErrorSpan = (groupRow, errorMsg) =>{
	let errorSpan = groupRow.querySelector(".error_msg");
	if(errorSpan){
		errorSpan.innerHTML = errorMsg;
		return;
	}else{
		errorSpan = d.createElement("span");
	}
	let errorDiv = d.createElement("div");
	errorDiv.classList.add("error_msg_row");
	errorSpan.classList.add("error_msg");
	errorSpan.innerHTML = errorMsg;
	errorDiv.appendChild(errorSpan);
	groupRow.appendChild(errorDiv);
}
let deleteErrorSpan = (groupRow) => {
	let errorDiv = groupRow.querySelector(".error_msg_row");
	if(!errorDiv){
		return;
	}
	errorDiv.remove();
}
})(window, document);