/**
 * User
 */
document.addEventListener("DOMContentLoaded", function(){
	let form = document.getElementById('saveUserForm');
	
	//validation
	let inputs = document.querySelectorAll('form input');
	for(let input of inputs){
		input.addEventListener("change", function(){
			let groupRow = input.parentElement.parentElement;
			
			switch(input.name){
				case "email":
					let chkEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					if (input.value.match(chkEmail) == null) {
						createErrorSpan(groupRow, "이메일 양식에 맞지 않습니다.");
					}else{
						deleteErrorSpan(groupRow);
					}
				break;
				case "pw":
					let check1 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$/.test(input.value);   //영문,숫자
					let check2 = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,16}$/.test(input.value);  //영문,특수문자
					let check3 = /^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/.test(input.value);  //특수문자, 숫자
					if(!(check1&&check2&&check3)){
						createErrorSpan(groupRow, "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
					}else{
						deleteErrorSpan(groupRow);
					}
				break;
				case "pwCheck":
					let pw = form.querySelector("input[name=pw]");
					if(!pw.value){
						createErrorSpan(groupRow, "먼저 비밀번호를 입력하세요.");
					}else if(input.value != pw.value){
						createErrorSpan(groupRow, "비밀번호가 일치하지 않습니다.");
					}else{
						deleteErrorSpan(groupRow);
					}
				break;
				case "name":
					if (!input.value) {
						createErrorSpan(groupRow, "필수값입니다.");
					}else{
						deleteErrorSpan(groupRow);
					}
				break;
				case "phone":
					let chkPhone = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/;
					if (input.value.match(chkPhone) == null) {
						createErrorSpan(groupRow, "휴대전화 양식에 맞지 않습니다.");
					}else{
						deleteErrorSpan(groupRow);
					}
				break;
			}
		});
	}
});
let saveUser = (formId) => {
	let form = document.getElementById(formId);
	let reqInput = ["email", "", ""];
	if(!checkValidation()){
		return;
	}
	if(!confirm("회원가입을 하시겠습니까?")){
		return;
	}
	form.submit();
}
let createErrorSpan = (groupRow, errorMsg) =>{
	let errorSpan = groupRow.querySelector(".error_msg");
	if(errorSpan){
		errorSpan.innerHTML = errorMsg;
		return;
	}else{
		errorSpan = document.createElement("span");
	}
	let errorDiv = document.createElement("div");
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
let checkValidation = () => {
	let inputs = document.querySelectorAll('form input');
	for(let input of inputs){
		let groupRow = input.parentElement.parentElement;
		if(!input.value){
			createErrorSpan(groupRow, "필수값입니다.");
		}
		if(groupRow.querySelector(".error_msg_row")){
			return false;
		};
	}
	return true;
}